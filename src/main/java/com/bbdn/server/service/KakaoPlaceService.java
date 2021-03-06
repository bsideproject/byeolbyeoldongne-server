package com.bbdn.server.service;

import com.bbdn.server.application.constant.ErrorConstant;
import com.bbdn.server.domain.enums.ErrorCodeEnums;
import com.bbdn.server.domain.enums.KakaoMapRestUrlEnums;
import com.bbdn.server.domain.enums.PlaceServiceTypeEnums;
import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.request.SearchKakaoPlaceRequest;
import com.bbdn.server.domain.interfaces.spec.Place;
import com.bbdn.server.domain.interfaces.vo.kakao.*;
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

    // 장소 찾기
    public SearchPlaceResultDTO searchPlaceByQueryParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {
        try {
            searchKakaoPlaceRequest.setKakaoMapRestUrlEnums(KakaoMapRestUrlEnums.RETRIEVE_PLACE_BY_V2);

            KakaoPlaceVO kakaoPlaceVO = this.kakaoMapClient.searchPlaceByKeyword(this.initiateQueryParameter(searchKakaoPlaceRequest));
            SearchPlaceResultDTO searchPlaceResultDTO = this.kakaoPlaceToSearchResult(kakaoPlaceVO);

            return searchPlaceResultDTO;

        } catch (KakaoMapClientException e) {
            throw new BadSearchRequestException(ErrorCodeEnums.BAD_REQUEST, e.getMessage());
        }
    }

    // 키워드 찾기 (from ~ to 좌표)
    public SearchPlaceResultDTO searchKeywordByQueryParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {
        try {
            searchKakaoPlaceRequest.setKakaoMapRestUrlEnums(KakaoMapRestUrlEnums.RETRIEVE_KEYWORD_BY_V2);
            searchKakaoPlaceRequest.setSize(30);

            KakaoPlaceVO kakaoPlaceVO = this.kakaoMapClient.searchPlaceByKeyword(this.initiateQueryParameter(searchKakaoPlaceRequest));
            log.info("searchKeywordByQueryParameter kakaoPlaceVO: " + kakaoPlaceVO.toString());

//            SearchPlaceResultDTO searchKeywordResultDTO = this.kakaoPlaceToSearchResult(kakaoPlaceVO);
//            log.info("searchKeywordByQueryParameter searchKeywordResultDTO: " + searchKeywordResultDTO.toString());

            return null;

        } catch (KakaoMapClientException e) {
            throw new BadSearchRequestException(ErrorCodeEnums.BAD_REQUEST, e.getMessage());
        }
    }

    private QueryParameterDTO initiateQueryParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {
        QueryParameterDTO queryParameter = new QueryParameterDTO(searchKakaoPlaceRequest.getQuery());
        if (!StringUtils.isEmpty(searchKakaoPlaceRequest.getKakaoCategoryGroupEnums())) {
            try{
                queryParameter.setCategoryGroupCodeEnums(
                        KakaoCategoryGroupEnums.valueOf(searchKakaoPlaceRequest.getKakaoCategoryGroupEnums().getName()));
            } catch(IllegalArgumentException e) {
                throw new BadSearchRequestException(ErrorCodeEnums.BAD_REQUEST, errorConstant.VALIDATOR_NOT_SUPPORT_CATEGORY_GROUP_CODE);
            }

        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getPage())) {
            queryParameter.setPage(searchKakaoPlaceRequest.getPage());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getRadius())) {
            queryParameter.setRadius(searchKakaoPlaceRequest.getRadius());
        }
        if (!StringUtils.isEmpty(searchKakaoPlaceRequest.getRect())) {
            queryParameter.setRect(searchKakaoPlaceRequest.getRect());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getY())) {
            queryParameter.setY(searchKakaoPlaceRequest.getY());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getX())) {
            queryParameter.setX(searchKakaoPlaceRequest.getX());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getSize())) {
            queryParameter.setSize(searchKakaoPlaceRequest.getSize());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getAddressName())) {
            queryParameter.setAddressName(searchKakaoPlaceRequest.getAddressName());
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

    // RETRIEVE_PLACE_BY_V2
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

//    // RETRIEVE_KEYWORD_BY_V2
//    private SearchPlaceResultDTO kakaoKeywordToSearchResult(KakaoPlaceVO kakaoPlace) {
//        List<Place> places = new ArrayList<>();
//        for (DocumentVO document : kakaoPlace.getDocuments()) {
//            places.add(this.documentToDefaultPlace(document));
//        }
//        return SearchPlaceResultDTO.builder()
//                .pagination(PaginationVO.builder()
//                        .totalCount(kakaoPlace.getMeta().getTotal_count())
//                        .pageableCount(kakaoPlace.getMeta().getPageable_count())
//                        .end(kakaoPlace.getMeta().is_end()).build())
//                .region(RegionInfoVO.builder()
//                        .keyword(kakaoPlace.getMeta().getSame_name().getKeyword())
//                        .region(kakaoPlace.getMeta().getSame_name().getRegion())
//                        .selectedRegion(kakaoPlace.getMeta().getSame_name().getSelectedRegion()).build())
//                .places(places)
//                .build();
//    }
}
