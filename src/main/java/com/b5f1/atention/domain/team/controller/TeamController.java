package com.b5f1.atention.domain.team.controller;

import com.b5f1.atention.domain.team.dto.TeamCreateRequestDto;
import com.b5f1.atention.domain.team.dto.TeamInviteRequestDto;
import com.b5f1.atention.domain.team.dto.TeamParticipantAuthorityDto;
import com.b5f1.atention.domain.team.dto.TeamUpdateRequestDto;
import com.b5f1.atention.domain.team.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
    //  2. return ResponseEntity 변환 후 반환 Object 생성

    @GetMapping
    @Operation(summary = "내 팀 조회", description = "본인 팀 리스트 조회 요청 API 입니다.")
    public void findMyTeamList(Authentication authentication) {
//        teamService.findMyTeamList(authentication.getName());
    }

    @PostMapping
    @Operation(summary = "팀 생성", description = "그룹 생성 요청 API 입니다.")
    public void create(Authentication authentication, @RequestBody TeamCreateRequestDto teamCreateRequestDto) {
//        teamService.createTeam(authentication.getName(), teamCreateRequestDto);
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "특정 팀 조회", description = "특정 팀 조회 요청 API 입니다.")
    public void getTeamDetail(Authentication authentication, @PathVariable("teamId") Long teamId) {
//        teamService.getTeamDetail(authentication.getName(), teamId);
    }

    @PutMapping("/{teamId}")
    @Operation(summary = "팀 수정", description = "팀 수정 요청 API 입니다.")
    public void updateTeam(Authentication authentication, @PathVariable("teamId") Long teamId, @RequestBody TeamUpdateRequestDto teamUpdateRequestDto) {
//        teamService.updateTeam(authentication.getName(), teamId, teamUpdateRequestDto);
    }

    @DeleteMapping("/{teamId}")
    @Operation(summary = "팀 삭제", description = "팀 삭제 요청 API 입니다.")
    public void deleteTeam(Authentication authentication, @PathVariable("teamId") Long teamId) {
//        teamService.deleteTeam(authentication.getName(), teamId);
    }

    @PostMapping("/invite")
    @Operation(summary = "팀 초대", description = "팀 초대 요청 API 입니다.")
    public void inviteTeam(Authentication authentication, @RequestBody TeamInviteRequestDto teamInviteRequestDto) {
//        teamService.inviteTeam(authentication.getName(), teamInviteRequestDto);
    }

    @PostMapping("/accept")
    @Operation(summary = "팀 초대 수락", description = "팀 초대 수락 요청 API 입니다.")
    public void acceptTeam(Authentication authentication, @RequestBody Long teamId) {
//        teamService.acceptTeam(authentication.getName(), teamId);
    }

    @DeleteMapping("/refuse/{teamId}")
    @Operation(summary = "팀 초대 거절", description = "팀 초대 거절 요청 API 입니다.")
    public void refuseTeam(Authentication authentication, @PathVariable Long teamId) {
//        teamService.refuseTeam(authentication.getName(), teamId);
    }

    @DeleteMapping("/leave/{teamId}")
    @Operation(summary = "팀 탈퇴", description = "팀 탈퇴 요청 API 입니다.")
    public void leaveTeam(Authentication authentication, @PathVariable Long teamId) {
//        teamService.leaveTeam(authentication.getName(), teamId);
    }

    @PutMapping("/authority")
    @Operation(summary = "팀 참여자 권한 변경", description = "팀 참여자 권한 변경 요청 API 입니다.")
    public void updateTeamParticipantAuthority(Authentication authentication, @RequestBody TeamParticipantAuthorityDto teamParticipantAuthorityDto) {
//        teamService.updateTeamParticipantAuthority(authentication.getName(), teamParticipantAuthorityDto);
    }

}