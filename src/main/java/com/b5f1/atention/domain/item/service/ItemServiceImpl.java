package com.b5f1.atention.domain.item.service;

import com.b5f1.atention.domain.item.repository.ItemRepository;
import com.b5f1.atention.domain.team.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    /**
     * 나의 모든 보유 아이템들을 가져오는 메서드
     * @return List<Item>
     */


}
