package com.b5f1.atention.domain.team.controller;

import com.b5f1.atention.common.MessageWithData;
import com.b5f1.atention.domain.team.dto.TeamCreateRequestDto;
import com.b5f1.atention.domain.team.dto.TeamInviteRequestDto;
import com.b5f1.atention.domain.team.dto.TeamParticipantAuthorityDto;
import com.b5f1.atention.domain.team.dto.TeamUpdateRequestDto;
import com.b5f1.atention.domain.team.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/team")
@Api(tags = "팀")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // TODO
    //  1. Spring Security Authentication 처리
    //  2. @AuthenticationPrincipal 처리
    @GetMapping
    @Operation(summary = "내 팀 조회", description = "본인 팀 리스트 조회 요청 API 입니다.")
    public ResponseEntity<MessageWithData> findMyTeamList(Authentication authentication) {
        // return new ResponseEntity<>(new MessageWithData("내 팀 조회가 성공하였습니다.", teamService.findMyTeamList(authentication.getName())));
        return null;
    }

    @PostMapping
    @Operation(summary = "팀 생성", description = "팀 생성 요청 API 입니다.")
    public ResponseEntity<MessageWithData> create(Authentication authentication, @RequestBody TeamCreateRequestDto teamCreateRequestDto) {
        // return new ResponseEntity<>(new MessageWithData("팀 생성이 성공하였습니다. ", teamService.createTeam(authentication.getName(), teamCreateRequestDto)));
        return null;
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "특정 팀 조회", description = "특정 팀 조회 요청 API 입니다.")
    public ResponseEntity<MessageWithData> getTeamDetail(Authentication authentication, @PathVariable("teamId") Long teamId) {
        // return new ResponseEntity<>(new MessageWithData("특정 팀 조회가 성공하였습니다. ", teamService.getTeamDetail(authentication.getName(), teamId)));
        return null;
    }

    @PutMapping("/{teamId}")
    @Operation(summary = "팀 수정", description = "팀 수정 요청 API 입니다.")
    public ResponseEntity<MessageWithData> updateTeam(Authentication authentication, @PathVariable("teamId") Long teamId, @RequestBody TeamUpdateRequestDto teamUpdateRequestDto) {
        // return new ResponseEntity<>(new MessageWithData("팀 수정이 성공하였습니다. ", teamService.updateTeam(authentication.getName(), teamId, teamUpdateRequestDto)));
        return null;
    }

    @DeleteMapping("/{teamId}")
    @Operation(summary = "팀 삭제", description = "팀 삭제 요청 API 입니다.")
    public ResponseEntity<MessageWithData> deleteTeam(Authentication authentication, @PathVariable("teamId") Long teamId) {
        // return new ResponseEntity<>(new MessageWithData("팀 삭제가 성공하였습니다. ", teamService.deleteTeam(authentication.getName(), teamId)));
        return null;
    }

    @PostMapping("/invite")
    @Operation(summary = "팀 초대", description = "팀 초대 요청 API 입니다.")
    public ResponseEntity<MessageWithData> inviteTeam(Authentication authentication, @RequestBody TeamInviteRequestDto teamInviteRequestDto) {
        // return new ResponseEntity<>(new MessageWithData("팀 초대가 성공하였습니다. ", teamService.inviteTeam(authentication.getName(), teamInviteRequestDto)));
        return null;
    }

    @PostMapping("/accept")
    @Operation(summary = "팀 초대 수락", description = "팀 초대 수락 요청 API 입니다.")
    public ResponseEntity<MessageWithData> acceptTeam(Authentication authentication, @RequestBody Long teamId) {
        // return new ResponseEntity<>(new MessageWithData("팀 초대 수락이 성공하였습니다. ", teamService.acceptTeam(authentication.getName(), teamId)));
        return null;
    }

    @DeleteMapping("/refuse/{teamId}")
    @Operation(summary = "팀 초대 거절", description = "팀 초대 거절 요청 API 입니다.")
    public ResponseEntity<MessageWithData> refuseTeam(Authentication authentication, @PathVariable Long teamId) {
        // return new ResponseEntity<>(new MessageWithData("팀 초대 거절이 성공하였습니다. ", teamService.refuseTeam(authentication.getName(), teamId)));
        return null;
    }

    @DeleteMapping("/leave/{teamId}")
    @Operation(summary = "팀 탈퇴", description = "팀 탈퇴 요청 API 입니다.")
    public ResponseEntity<MessageWithData> leaveTeam(Authentication authentication, @PathVariable Long teamId) {
        // return new ResponseEntity<>(new MessageWithData("팀 탈퇴가 성공하였습니다. ", teamService.leaveTeam(authentication.getName(), teamId)));
        return null;
    }

    @PutMapping("/authority")
    @Operation(summary = "팀 참여자 권한 변경", description = "팀 참여자 권한 변경 요청 API 입니다.")
    public ResponseEntity<MessageWithData> updateTeamParticipantAuthority(Authentication authentication, @RequestBody TeamParticipantAuthorityDto teamParticipantAuthorityDto) {
        // return new ResponseEntity<>(new MessageWithData("팀 참여자 권한 변경이 성공하였습니다. ", teamService.updateTeamParticipantAuthority(authentication.getName(), teamParticipantAuthorityDto)));
        return null;
    }

}