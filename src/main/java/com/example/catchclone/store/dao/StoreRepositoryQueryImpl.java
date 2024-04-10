package com.example.catchclone.store.dao;

import static com.example.catchclone.store.entity.QStore.store;

import com.example.catchclone.store.dto.StoreIndexResponseDto;
import com.example.catchclone.store.dto.StorePageDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreRepositoryQueryImpl implements StoreRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional(readOnly = true)
  public Page<StoreIndexResponseDto> getStores(StorePageDto storePageDto) {
    Pageable pageable = storePageDto.toPageable();
    int starCut = storePageDto.getStarCut();
    List<StoreIndexResponseDto> dtoList;

    if(!Objects.isNull(storePageDto.getSortBy())) dtoList = getStoresAndSortByKeyword(pageable);
    else if(storePageDto.isCutByStarRate()) dtoList =  getStoresAndCutByStarRate(pageable,starCut);
    else dtoList = getStoresSortByDesc(pageable);


    long totalSize = storeCountQuery().fetch().get(0);

    return PageableExecutionUtils.getPage(dtoList, pageable, () -> totalSize);
  }
  private List<StoreIndexResponseDto> getStoresAndSortByKeyword(Pageable pageable){
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreIndexResponseDto.class
                ,store.id
                ,store.storeName
                ,store.storeLocation
                ,store.starRate
            )
        )
        .from(store)
        .setHint("org.hibernate.readOnly", true)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }
  private List<StoreIndexResponseDto> getStoresAndCutByStarRate(Pageable pageable, int starCut){
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreIndexResponseDto.class
                ,store.id
                ,store.storeName
                ,store.storeLocation
                ,store.starRate
            )
        )
        .from(store)
        .where(store.starRate.gt(starCut))
        .setHint("org.hibernate.readOnly", true)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }
  private List<StoreIndexResponseDto> getStoresSortByDesc(Pageable pageable){
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreIndexResponseDto.class
                ,store.id
                ,store.storeName
                ,store.storeLocation
                ,store.starRate
            )
        )
        .from(store)
        .setHint("org.hibernate.readOnly", true)
        .orderBy(store.createdAt.desc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }
  //
  private JPAQuery<Long> storeCountQuery() {
    return jpaQueryFactory.select(Wildcard.count)
        .from(store);
  }
}
