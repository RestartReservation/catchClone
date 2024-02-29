package com.example.catchclone.store.repository;

import com.example.catchclone.store.entity.Store;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface StoreRepository extends Repository<Store,Long> {

  void save(Store store);

  Optional<Store> findByStoreName(String storeName);

  Optional<Store> findById(Long id);

}
