package com.b5f1.atention.domain.item.service;

import com.b5f1.atention.domain.item.dto.MyItemCreateResponseDto;
import com.b5f1.atention.domain.item.dto.MyItemResponseDto;
import com.b5f1.atention.entity.Item;
import com.b5f1.atention.entity.User;

import java.util.List;
import java.util.UUID;

public interface ItemService {


    // 모든 종류의 아이템 가져오기
    public List<Item> findAllItems();

    // 나의 보유 아이템 가져오기
    public List<MyItemResponseDto> findMyItemList(UUID userId);

    // 아이템 뽑기
    public MyItemCreateResponseDto createMyItem(UUID userId);

    // 아이템 사용
    public void deleteItem(UUID userId, Long itemId);

    // 내부 로직
    // userId로 User 찾기
    public User findUserById(UUID userId);
    // itemId로 item 찾기
    public Item findItemById(Long itemId);
}
