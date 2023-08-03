package com.b5f1.atention.domain.item.service;

import com.b5f1.atention.domain.item.dto.MyItemCreateResponseDto;
import com.b5f1.atention.domain.item.dto.GetMyItemResponseDto;
import com.b5f1.atention.domain.item.repository.ItemRepository;
import com.b5f1.atention.domain.item.repository.MyItemRepository;
import com.b5f1.atention.domain.user.repository.UserRepository;
import com.b5f1.atention.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final MyItemRepository myItemRepository;
    private final UserRepository userRepository;

    /**
     * 모든 아이템 조회 메서드
     * @return HashSet<Item>
     */
    @Override
    public Set<Long> findAllItems() {
        List<Item> allItemList = itemRepository.findAll();
        return allItemList.stream()
                .map(Item::getId)
                .collect(Collectors.toSet());
    }

    /**
     * 나의 보유 아이템 목록 조회 메서드
     * @param userId
     * @return GetMyItemResponseDto
     */
    @Override
    public GetMyItemResponseDto findMyItemList(UUID userId) {
        // userId로 user 찾고
        User user = findUserById(userId);

        // user와 연관관계가 있는 아이템 list 얻기
        List<MyItem> myItemList = user.getMyItemList();

        GetMyItemResponseDto getMyItemResponseDto = GetMyItemResponseDto
                .builder()
                .myItemDtoList(new ArrayList<>())
                .ticket(user.getTicket())
                .build();

        // 내 아이템 리스트에 있는 아이템들을 getMyItemResponseDto 형식에 맞게 담기
        for (MyItem myItem : myItemList) {
            Item item = myItem.getItem();
            getMyItemResponseDto.addItemDto(item);
        }
        return getMyItemResponseDto;
    }

    /**
     * 아이템 뽑기 메서드
     * @param userId
     * @return MyItemCreateResponseDto
     */
    @Override
    public MyItemCreateResponseDto createMyItem(UUID userId) {
        // userId로 user 찾고
        User user = findUserById(userId);
        // ticket 사용(차감)
        user.useTicket();
        // save
        userRepository.save(user);
        // 전체 아이템 set
        Set<Long> allItemSet = findAllItems();
        List<MyItem> myItemList = user.getMyItemList();
        for (MyItem myItem : myItemList) {
            // 전체 아이템에서 내가 가진 아이템 번호만 제거
            allItemSet.remove(myItem.getItem().getId());
        }
        // 랜덤 숫자
        // 남은 아이템 중 (랜덤 숫자)번째 아이템을 뽑게 된다
        // Random().nextInt(n) : 0 ~ n - 1
        Long randomNumber = (long)new Random().nextInt(allItemSet.size());
        Long i = 0L;
        Long newItemNumber = null;
        for (Long itemNumber : allItemSet) {
            if (i == randomNumber) {
                newItemNumber = itemNumber;
                break;
            }
            i ++;
        }
        // 새로 뽑힌 아이템
        Item newItem = findItemById(newItemNumber);

        // createMyItem 메서드로 user의 MyItemList에 update해줌과 동시에
        // db 저장
        myItemRepository.save(new MyItem().createMyItem(user, newItem));
        return MyItemCreateResponseDto.builder()
                .name(newItem.getName())
                .image(newItem.getImage())
                .build();
    }

    /**
     * 아이템 사용 메서드
     * @param userId, itemId
     */
    @Override
    public void useItem(UUID userId, Long itemId) {
        MyItem useMyItem = findMyItemByUserIdAndItemId(userId, itemId);
        Long itemTypeId = useMyItem.getItem().getItemType().getId();
        if (itemTypeId == 1 || itemTypeId == 2) {
            useMyItem.deleted();
            myItemRepository.save(useMyItem);
        }
    }

    // 아래는 서비스 내부 로직

    // userId로 유저를 찾고, 없으면 throw Exception
    // 추후에 Exception 변경 예정
    @Override
    public User findUserById(UUID userId) {
        return userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("해당하는 유저를 찾을 수 없습니다"));
    }

    // itemId로 아이템 찾고, 없으면 throw Exception
    // 추후에 Exception 변경 예정
    @Override
    public Item findItemById(Long itemId) {
        return itemRepository.findByIdAndIsDeletedFalse(itemId)
                .orElseThrow(() -> new RuntimeException("해당하는 아이템을 찾을 수 없습니다"));
    }

    // userId로 사용자 찾고, itemId로 아이템 찾고, 없으면 throw Exception
    // 추후에 Exception 변경 예정
    @Override
    public MyItem findMyItemByUserIdAndItemId(UUID userId, Long itemId) {
        User user = findUserById(userId);
        Item item = findItemById(itemId);
        return myItemRepository.findByUserAndItemAndIsDeletedFalse(user, item)
                .orElseThrow(() -> new RuntimeException("해당하는 아이템을 찾을 수 없습니다"));
    }

}
