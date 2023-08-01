package com.b5f1.atention.domain.item.service;

import com.b5f1.atention.domain.item.dto.MyItemCreateResponseDto;
import com.b5f1.atention.domain.item.dto.MyItemResponseDto;
import com.b5f1.atention.domain.item.repository.ItemRepository;
import com.b5f1.atention.domain.item.repository.MyItemRepository;
import com.b5f1.atention.domain.user.repository.UserRepository;
import com.b5f1.atention.entity.Item;
import com.b5f1.atention.entity.MyItem;
import com.b5f1.atention.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final MyItemRepository myItemRepository;
    private final UserRepository userRepository;

    /**
     * 모든 아이템 조회 메서드
     * @return List<Item>
     */
    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    /**
     * 나의 보유 아이템 목록 조회 메서드
     * @param userId
     * @return myItemResponseDto
     */
    @Override
    public List<MyItemResponseDto> findMyItemList(UUID userId) {
        // userId로 user 찾고
        User user = findUserById(userId);

        // user와 연관관계가 있는 아이템 list 얻기
        List<MyItem> myItemList = user.getMyItemList();

        // itemResponseDto로 변환
        List<MyItemResponseDto> myItemResponseDtoList = new ArrayList<>();
        for (MyItem myItem : myItemList) {
            MyItemResponseDto myItemResponseDto = new MyItemResponseDto().toItemResponseDto(myItem.getItem());
            myItemResponseDtoList.add(myItemResponseDto);
        }
        return myItemResponseDtoList;
    }

    /**
     * 아이템 뽑기 메서드
     * @param userId
     * @return myItemResponseDto
     */
    @Override
    public MyItemCreateResponseDto createMyItem(UUID userId) {
        // userId로 user 찾고
        User user = findUserById(userId);
        // ticket 사용(차감)
        user.useTicket(user.getTicket());

        return null;
    }

    @Override
    public void deleteItem(UUID userId, Long itemId) {

    }

    // 아래는 서비스 내부 로직

    // userId로 유저를 찾고, 없으면 throw Exception
    // 추후에 Exception 변경 예정
    @Override
    public User findUserById(UUID userId) {
        return userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("해당하는 유저를 찾을 수 없습니다"));
    }

    // itemId로 아이디 찾고, 없으면 throw Exception
    // 추후에 Exception 변경 예정
    @Override
    public Item findItemById(Long itemId) {
        return itemRepository.findByIdAndIsDeletedFalse(itemId)
                .orElseThrow(() -> new RuntimeException("해당하는 아이템을 찾을 수 없습니다"));
    }

}
