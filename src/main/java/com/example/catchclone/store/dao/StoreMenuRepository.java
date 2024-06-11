package com.example.catchclone.store.dao;

import com.example.catchclone.store.entity.Store;
import com.example.catchclone.store.entity.StoreMenu;
import java.util.List;
import org.springframework.data.repository.Repository;

public interface StoreMenuRepository extends Repository<StoreMenu,Long> {


  void save(StoreMenu storeMenu);

  List<StoreMenu> findAllByStore(Store store);
}
