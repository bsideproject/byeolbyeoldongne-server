package com.bbdn.server.service;

import com.bbdn.server.application.constant.ErrorConstant;
import com.bbdn.server.domain.enums.ErrorCodeEnums;
import com.bbdn.server.domain.enums.KakaoMapRestUrlEnums;
import com.bbdn.server.domain.enums.PlaceServiceTypeEnums;
import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.request.SearchPlaceRequest;
import com.bbdn.server.domain.interfaces.spec.Place;
import com.bbdn.server.domain.interfaces.vo.*;
import com.bbdn.server.handler.exception.BadSearchRequestException;
import com.bbdn.server.handler.exception.KakaoMapClientException;
import com.bbdn.server.domain.enums.KakaoCategoryGroupEnums;
import com.bbdn.server.domain.interfaces.KakaoMapClient;
import com.bbdn.server.domain.interfaces.dto.QueryParameterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class KakaoPlaceService {

    private final KakaoMapClient kakaoMapClient;
    private ErrorConstant errorConstant;

    protected KakaoPlaceService(@Value("${kakao.map.api-url}") String serverUrl,
                                      @Value("${kakao.admin-key}") String adminKey) {
        this.kakaoMapClient = new KakaoMapClient(serverUrl, adminKey);
    }

    // default return method
    public SearchPlaceResultDTO searchPlaceByQueryParameter(SearchPlaceRequest searchPlaceRequest) {

        try {

            searchPlaceRequest.setKakaoMapRestUrlEnums(KakaoMapRestUrlEnums.RETRIEVE_PLACE_BY_KEYWORD_V2);

            KakaoPlaceVO kakaoPlaceVO = this.kakaoMapClient.searchPlaceByKeyword(this.initiateQueryParameter(searchPlaceRequest));
            SearchPlaceResultDTO searchPlaceResultDTO = this.kakaoPlaceToSearchResult(kakaoPlaceVO);

            return searchPlaceResultDTO;

        } catch (KakaoMapClientException e) {
            throw new BadSearchRequestException(ErrorCodeEnums.BAD_REQUEST, e.getMessage());
        }
    }

    private QueryParameterDTO initiateQueryParameter(SearchPlaceRequest searchPlaceRequest) {
        QueryParameterDTO queryParameter = new QueryParameterDTO(searchPlaceRequest.getQuery());
        if (!StringUtils.isEmpty(searchPlaceRequest.getKakaoCategoryGroupEnums())) {
            try{
                queryParameter.setCategoryGroupCodeEnums(
                        KakaoCategoryGroupEnums.valueOf(searchPlaceRequest.getKakaoCategoryGroupEnums().getName()));
            } catch(IllegalArgumentException e) {
                throw new BadSearchRequestException(ErrorCodeEnums.BAD_REQUEST, errorConstant.VALIDATOR_NOT_SUPPORT_CATEGORY_GROUP_CODE);
            }

        }
        if (!Objects.isNull(searchPlaceRequest.getPage())) {
            queryParameter.setPage(searchPlaceRequest.getPage());
        }
        if (!Objects.isNull(searchPlaceRequest.getRadius())) {
            queryParameter.setRadius(searchPlaceRequest.getRadius());
        }
        if (!StringUtils.isEmpty(searchPlaceRequest.getRect())) {
            queryParameter.setRect(searchPlaceRequest.getRect());
        }
        if (!Objects.isNull(searchPlaceRequest.getY())) {
            queryParameter.setY(searchPlaceRequest.getY());
        }
        if (!Objects.isNull(searchPlaceRequest.getX())) {
            queryParameter.setX(searchPlaceRequest.getX());
        }
        if (!Objects.isNull(searchPlaceRequest.getSize())) {
            queryParameter.setSize(searchPlaceRequest.getSize());
        }
        return queryParameter;
    }

    private Place documentToDefaultPlace(DocumentVO document) {
        return DefaultPlaceVO.builder()
                .addressName(document.getAddress_name())
                .category(CategoryVO.builder()
                        .name(document.getCategory_name())
                        .group(CategoryGroupVO.builder()
                                .name(document.getCategory_group_name())
                                .code(document.getCategory_group_code())
                                .placeServiceTypeEnums(PlaceServiceTypeEnums.KAKAO_MAP)
                                .build()).build())
                .distance(document.getDistance())
                .id(document.getId())
                .phone(document.getPhone())
                .placeName(document.getPlace_name())
                .placeUrl(document.getPlace_url())
                .roadAddressName(document.getRoad_address_name())
                .x(document.getX())
                .y(document.getY())
                .build();
    }

    // RETRIEVE_PLACE_BY_KEYWORD_V2
    private SearchPlaceResultDTO kakaoPlaceToSearchResult(KakaoPlaceVO kakaoPlace) {
        List<Place> places = new ArrayList<>();
        for (DocumentVO document : kakaoPlace.getDocuments()) {
            places.add(this.documentToDefaultPlace(document));
        }
        return SearchPlaceResultDTO.builder()
                .pagination(PaginationVO.builder()
                        .totalCount(kakaoPlace.getMeta().getTotal_count())
                        .pageableCount(kakaoPlace.getMeta().getPageable_count())
                        .end(kakaoPlace.getMeta().is_end()).build())
                .region(RegionInfoVO.builder()
                        .keyword(kakaoPlace.getMeta().getSame_name().getKeyword())
                        .region(kakaoPlace.getMeta().getSame_name().getRegion())
                        .selectedRegion(kakaoPlace.getMeta().getSame_name().getSelectedRegion()).build())
                .places(places)
                .build();
    }
}
