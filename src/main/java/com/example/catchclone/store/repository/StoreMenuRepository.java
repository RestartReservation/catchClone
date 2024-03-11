package com.example.catchclone.store.repository;

import com.example.catchclone.store.entity.StoreMenu;
import org.springframework.data.repository.Repository;

public interface StoreMenuRepository extends Repository<StoreMenu,Long> {


  void save(StoreMenu storeMenu);

}
