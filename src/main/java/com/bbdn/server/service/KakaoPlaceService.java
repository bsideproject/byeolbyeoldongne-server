package com.bbdn.server.service;

import com.bbdn.server.application.constant.ErrorConstant;
import com.bbdn.server.domain.enums.*;
import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.request.SearchKakaoPlaceRequest;
import com.bbdn.server.domain.interfaces.spec.Place;
import com.bbdn.server.domain.interfaces.vo.AddressLocationVO;
import com.bbdn.server.domain.interfaces.vo.kakao.*;
import com.bbdn.server.handler.exception.BadSearchRequestException;
import com.bbdn.server.handler.exception.KakaoMapClientException;
import com.bbdn.server.domain.interfaces.KakaoMapClient;
import com.bbdn.server.domain.interfaces.dto.QueryParameterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

@Service
@Slf4j
public class KakaoPlaceService {

    private final KakaoMapClient kakaoMapClient;
    private ErrorConstant errorConstant;

    protected KakaoPlaceService(@Value("${kakao.map.api-url}") String serverUrl,
                                      @Value("${kakao.admin-key}") String adminKey) {
        this.kakaoMapClient = new KakaoMapClient(serverUrl, adminKey);
    }

    // 검색어로 장소 찾기
    public SearchPlaceResultDTO searchPlaceByQueryParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {
        try {
            searchKakaoPlaceRequest.setKakaoMapRestUrlEnums(KakaoMapRestUrlEnums.RETRIEVE_KEYWORD_BY_V2);

            QueryParameterDTO queryParameterDTO = this.initiateQueryParameter(searchKakaoPlaceRequest);
            KakaoPlaceVO kakaoPlaceVO = this.kakaoMapClient.searchPlaceByKeyword(queryParameterDTO);

            log.info("searchPlaceByQueryParameter kakaoPlaceVO: " + kakaoPlaceVO.toString());

            return this.kakaoPlaceToSearchResult(kakaoPlaceVO);

        } catch (KakaoMapClientException e) {
            throw new BadSearchRequestException(ErrorCodeEnums.BAD_REQUEST, e.getMessage());
        }
    }

    // 좌표로 장소명 찾기
    public String searchPlaceByPositionParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {
        try {
            searchKakaoPlaceRequest.setKakaoMapRestUrlEnums(KakaoMapRestUrlEnums.RETRIEVE_COORD_ADDRESS_BY_V2);

            KakaoPlaceVO kakaoPlaceVO = this.kakaoMapClient.searchPlaceByPosition(this.initiateQueryParameter(searchKakaoPlaceRequest));

            String roadName = kakaoPlaceVO.getDocuments().get(0).getRoad_address().getRoad_name();
            log.info("searchPlaceByPositionParameter kakaoPlaceVO: " + roadName);
            return roadName;

        } catch (KakaoMapClientException e) {
            throw new BadSearchRequestException(ErrorCodeEnums.BAD_REQUEST, e.getMessage());
        }
    }

    // 주변 X세권 찾기
    public Set<String> searchCategoryGroupListByParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {

        Set<String> kakaoCategoryGroupEnumsList = new HashSet<>();

        searchKakaoPlaceRequest.setKakaoMapRestUrlEnums(KakaoMapRestUrlEnums.RETRIEVE_KEYWORD_BY_V2);
        searchKakaoPlaceRequest.setRadius(500);

        // 카카오로컬에 정의된 카테고리 그룹 조회
        for(KakaoCategoryGroupEnums kakaoCategoryGroup : KakaoCategoryGroupEnums.values()) {
            searchKakaoPlaceRequest.setQuery(kakaoCategoryGroup.getName());
            log.info("searchCategoryGroupListByParameter searchKakaoPlaceRequest: {}:", searchKakaoPlaceRequest.toString());

            KakaoPlaceVO kakaoPlaceVO = this.kakaoMapClient.searchPlaceByKeyword(this.initiateQueryParameter(searchKakaoPlaceRequest));
            log.info("searchCategoryGroupListByParameter kakaoPlaceVO: {}:", kakaoPlaceVO.toString());

            if(kakaoPlaceVO.getMeta().getPageable_count() > 0) {
                kakaoCategoryGroupEnumsList.add(kakaoCategoryGroup.getBbdnGroupCode());
            }
        }

        return kakaoCategoryGroupEnumsList;
    }

    // 키워드 찾기 (from ~ to 좌표)
    public List<AddressLocationVO> searchKeywordByQueryParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {

        List<AddressLocationVO> addressLocationVOList = new ArrayList<>();
        int currentPage = 1;
        searchKakaoPlaceRequest.setPage(currentPage);

        try {
            searchKakaoPlaceRequest.setKakaoMapRestUrlEnums(KakaoMapRestUrlEnums.RETRIEVE_KEYWORD_BY_V2);

            KakaoPlaceVO kakaoPlaceVO = this.kakaoMapClient.searchPlaceByKeyword(this.initiateQueryParameter(searchKakaoPlaceRequest));

            log.info("kakaoPlaceVO: {} ", kakaoPlaceVO.toString());
            int pageableCount = kakaoPlaceVO.getMeta().getPageable_count();

            if(pageableCount > 1) {
                addAddressLocation(addressLocationVOList, kakaoPlaceVO);
                ++currentPage;

                for(int i = currentPage; i<pageableCount; i++) {
                    searchKakaoPlaceRequest.setPage(currentPage);
                    kakaoPlaceVO = this.kakaoMapClient.searchPlaceByKeyword(this.initiateQueryParameter(searchKakaoPlaceRequest));
                    addAddressLocation(addressLocationVOList, kakaoPlaceVO);
                }
            }
            return addressLocationVOList;

        } catch (KakaoMapClientException e) {
            throw new BadSearchRequestException(ErrorCodeEnums.BAD_REQUEST, e.getMessage());
        }
    }

    private void addAddressLocation(List<AddressLocationVO> addressLocationVOList, KakaoPlaceVO kakaoPlaceVO) {

        IntStream.range(0, kakaoPlaceVO.getDocuments().size()).forEach(i -> {
            Long placeId = Long.parseLong(kakaoPlaceVO.getDocuments().get(i).getId());
            String addressName = kakaoPlaceVO.getDocuments().get(i).getRoad_address_name();
            Double x = kakaoPlaceVO.getDocuments().get(i).getX();
            Double y = kakaoPlaceVO.getDocuments().get(i).getY();
            AddressLocationVO addressLocationVO = new AddressLocationVO(placeId, addressName, x, y);
            addressLocationVOList.add(addressLocationVO);
        });
    }

    private QueryParameterDTO initiateQueryParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {
        QueryParameterDTO queryParameter = new QueryParameterDTO(searchKakaoPlaceRequest.getQuery());
        if (!StringUtils.isEmpty(searchKakaoPlaceRequest.getKakaoCategoryGroupEnums())) {
            try{
                queryParameter.setCategoryGroupCodeEnums(
                        KakaoCategoryGroupEnums.valueOf(searchKakaoPlaceRequest.getKakaoCategoryGroupEnums()));
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
        if (!Objects.isNull(searchKakaoPlaceRequest.getLat())) {
            queryParameter.setY(searchKakaoPlaceRequest.getLat());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getLng())) {
            queryParameter.setX(searchKakaoPlaceRequest.getLng());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getSize())) {
            queryParameter.setSize(searchKakaoPlaceRequest.getSize());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getAddressName())) {
            queryParameter.setAddressName(searchKakaoPlaceRequest.getAddressName());
        }
        if (!Objects.isNull(searchKakaoPlaceRequest.getKakaoMapRestUrlEnums())) {
            queryParameter.setKakaoMapRestUrlEnums(searchKakaoPlaceRequest.getKakaoMapRestUrlEnums());
        }
        log.info("queryParameter: " + queryParameter.toString());
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

}
