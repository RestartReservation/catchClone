package com.example.catchclone.store.repository;

import com.example.catchclone.store.entity.StoreCategory;
import org.springframework.data.repository.Repository;

public interface StoreCategoryRepository extends Repository<StoreCategory,Long> {

  void save(StoreCategory storeCategory);

}
