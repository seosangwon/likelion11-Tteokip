package com.example.koun.repository;

import com.example.koun.domain.Item;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    // 이름으로 아이템 검색
    Optional<Item> findByItemName(String itemName);


    @Query("SELECT i FROM Item i ORDER BY i.likeNum DESC")
    Page<Item> findTopLikedItems(Pageable pageable);

    @Query("SELECT i FROM Item i ORDER BY i.uploadTime DESC")
    Page<Item> findNewTopLikedItems(Pageable pageable);

}
