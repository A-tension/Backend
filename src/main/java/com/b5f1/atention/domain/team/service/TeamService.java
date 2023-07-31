package com.b5f1.atention.domain.team.service;

import com.b5f1.atention.domain.team.dto.*;
import com.b5f1.atention.entity.Team;
import com.b5f1.atention.entity.User;

import java.util.List;
import java.util.UUID;

public interface TeamService {

    public List<TeamResponseDto> findMyTeamList(UUID userId);
    public Team createTeam(UUID userId, TeamCreateRequestDto teamCreateRequestDto);
    public void inviteUser(Team team, TeamCreateRequestDto teamCreateRequestDto);
    public TeamDetailResponseDto getTeamDetail(Long teamId);
    public TeamUpdateRequestDto updateTeam(UUID userId, Long teamId, TeamUpdateRequestDto teamUpdateRequestDto);
    public void deleteTeam(Long teamId);

    public void inviteTeam(TeamInviteRequestDto teamInviteRequestDto);

    public User findUserById(UUID userId);
    public Team findTeamById(Long teamId);

}
