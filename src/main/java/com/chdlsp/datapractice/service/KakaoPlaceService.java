package com.chdlsp.datapractice.service;

import com.chdlsp.datapractice.application.constant.ErrorConstant;
import com.chdlsp.datapractice.domain.enums.ErrorCodeEnums;
import com.chdlsp.datapractice.domain.enums.KakaoCategoryGroupEnums;
import com.chdlsp.datapractice.domain.enums.PlaceServiceTypeEnums;
import com.chdlsp.datapractice.domain.interfaces.KakaoMapClient;
import com.chdlsp.datapractice.domain.interfaces.dto.QueryParameterDTO;
import com.chdlsp.datapractice.domain.interfaces.request.SearchPlaceRequest;
import com.chdlsp.datapractice.domain.interfaces.dto.SearchPlaceResultDTO;
import com.chdlsp.datapractice.domain.interfaces.spec.Place;
import com.chdlsp.datapractice.domain.interfaces.vo.*;
import com.chdlsp.datapractice.handler.exception.BadSearchRequestException;
import com.chdlsp.datapractice.handler.exception.KakaoMapClientException;
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

    public SearchPlaceResultDTO searchPlaceByQueryParameter(SearchPlaceRequest searchPlaceRequest) {

        try {
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
