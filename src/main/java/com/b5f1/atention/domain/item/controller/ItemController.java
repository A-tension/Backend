package com.b5f1.atention.domain.item.controller;

import com.b5f1.atention.common.MessageOnly;
import com.b5f1.atention.common.MessageWithData;
import com.b5f1.atention.domain.item.dto.CreateMyItemResponseDto;
import com.b5f1.atention.domain.item.service.ItemService;
import com.b5f1.atention.domain.team.dto.*;
import com.b5f1.atention.domain.team.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/item")
@Api(tags = "아이템")
@RequiredArgsConstructor
public class ItemController {

   private final ItemService itemService;

   // TODO

    @Operation(summary = "아이템 뽑기 POST", description = "티켓을 사용해 랜덤으로 아이템을 뽑는 API입니다.")
    public ResponseEntity<MessageWithData> getRandomItem(Authentication authentication) {
        CreateMyItemResponseDto data = itemService.createMyItem(UUID.fromString(authentication.getName()));
        return new ResponseEntity<>(new MessageWithData("랜덤 아이템이 생성되었습니다.", data), HttpStatus.OK);
    }

    @Operation(summary = "보유 아이템 조회 GET", description = "본인이 보유한 아이템을 전체 조회하는 API입니다")
    public ResponseEntity<MessageWithData> findMyItemList(Authentication authentication) {

        return null;
    }

    @Operation(summary = "아이템 사용 DELETE", description = "아이템을 사용하는 API입니다")
    public ResponseEntity<MessageWithData> deleteMyItem(Authentication authentication) {

        return null;
    }
}