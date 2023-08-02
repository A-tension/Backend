package com.b5f1.atention.domain.Item;

import com.b5f1.atention.domain.item.repository.ItemRepository;
import com.b5f1.atention.domain.item.repository.ItemTypeRepository;
import com.b5f1.atention.domain.item.repository.MyItemRepository;
import com.b5f1.atention.domain.item.service.ItemServiceImpl;
import com.b5f1.atention.domain.user.repository.UserRepository;
import com.b5f1.atention.entity.Item;
import com.b5f1.atention.entity.ItemType;
import com.b5f1.atention.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ItemTests {
    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MyItemRepository myItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemTypeRepository itemTypeRepository;


    @Test
    public void findAllItemsTest() {
        //given
        // test용 user 생성
//        User user1 = User.builder()
//                .email("testEmail")
//                .meetingUrl("testUrl")
//                .build();
//        // 저장도 하고 자동 반환도 함
//        user1 = userRepository.save(user1);
//
//        // test용 itemType 생성
//        ItemType itemType1 = ItemType.builder()
//                .build();
//        itemType1 = itemTypeRepository.save(itemType1);
//
//        // test용 item 생성
//        Item item1 = Item.builder()
//                .itemType(itemType1)
//                .build();


        //when
        Set<Long> allItems = itemService.findAllItems();

        //then
//        assertThat

    }

    @Test
    public void createItemTest() {
        //given

        //when


        //then

    }

}