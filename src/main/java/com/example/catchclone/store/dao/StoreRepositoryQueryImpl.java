package com.example.catchclone.store.dao;

import static com.example.catchclone.like.entity.reviewLike.QReviewLike.reviewLike;
import static com.example.catchclone.review.entity.QReview.review;
import static com.example.catchclone.store.entity.QStore.store;
import static com.example.catchclone.store.entity.QStoreMenu.storeMenu;

import com.example.catchclone.review.dto.ReviewResponseDto;
import com.example.catchclone.store.dto.StoreDetailsResponseDto;
import com.example.catchclone.store.dto.StoreIndexResponseDto;
import com.example.catchclone.store.dto.StoreMenuDto;
import com.example.catchclone.store.dto.StorePageDto;
import com.example.catchclone.store.entity.Store;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreRepositoryQueryImpl implements StoreRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional(readOnly = true)
  public Page<StoreIndexResponseDto> getStores(StorePageDto storePageDto) {
    Pageable pageable = storePageDto.toPageable();
    int starCut = storePageDto.getStarCut();
    List<StoreIndexResponseDto> dtoList;

    if(Objects.nonNull(storePageDto.getSortBy())) dtoList = getStoresAndSortByKeyword(pageable,storePageDto);
    else if (storePageDto.isCutByStarRate()) dtoList = getStoresAndCutByStarRate(pageable, starCut);
    else dtoList = getStoresSortByCreatedAtDesc(pageable);

    long totalSize = storeCountQuery().fetch().get(0);

    return PageableExecutionUtils.getPage(dtoList, pageable, () -> totalSize);
  }

@Override
@Transactional(readOnly = true)
public StoreDetailsResponseDto getStoreDetails(Long storeId) {
  List<StoreMenuDto> storeMenuDtoList = jpaQueryFactory
      .select(
          Projections.bean(
              StoreMenuDto.class,
              storeMenu.menuNm,
              storeMenu.menuUrl,
              storeMenu.menuPrice,
              storeMenu.menuMain
          )
      )
      .from(storeMenu)
      .where(storeMenu.store.id.eq(storeId))
      .fetch();


  StoreDetailsResponseDto storeDetailsResponseDto =  jpaQueryFactory
      .select(
          Projections.bean(
              StoreDetailsResponseDto.class
              , store.storeName
              , store.storeLocation
              , store.starRate
              , store.timeDetail
              , store.storePhoneNumber
              , store.aboutStore
              , store.storeNotification
              , store.reservationTypeFlag
              , store.regularHoliday
              , store.storeHomepage
      )
      )
      .from(store)
      .where(store.id.eq(storeId))
      .fetchFirst();

    storeDetailsResponseDto.setStoreMenuDtoList(storeMenuDtoList);

    return storeDetailsResponseDto;
  }

  private List<StoreIndexResponseDto> getStoresAndSortByKeyword(Pageable pageable,StorePageDto storePageDto) {
    OrderSpecifier<?> orderSpecifier = getOrderSpecifier(storePageDto.getSortBy(),
        storePageDto.isAsc());

    return query()
        .orderBy(orderSpecifier)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }

  private List<StoreIndexResponseDto> getStoresAndCutByStarRate(Pageable pageable, int starCut) {
    return query()
        .where(store.starRate.gt(starCut))
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }

  private List<StoreIndexResponseDto> getStoresSortByCreatedAtDesc(Pageable pageable) {
    return query()
        .orderBy(store.createdAt.desc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }

  private JPAQuery<StoreIndexResponseDto> query(){
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreIndexResponseDto.class
                , store.id
                , store.storeName
                , store.storeLocation
                , store.starRate
            )
        )
        .from(store)
        .setHint("org.hibernate.readOnly", true);
  }
  private JPAQuery<Long> storeCountQuery() {
    return jpaQueryFactory.select(Wildcard.count)
        .from(store);
  }

  private OrderSpecifier<?> getOrderSpecifier(String sortBy, boolean isAsc) {
    PathBuilder<Object> defaultPath = new PathBuilder<>(Store.class, Store.class.getSimpleName());
    return isAsc ? defaultPath.getString(sortBy).asc() : defaultPath.getString(sortBy).desc();
  }

}
