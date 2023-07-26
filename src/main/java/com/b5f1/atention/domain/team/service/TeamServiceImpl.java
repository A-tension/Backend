package com.b5f1.atention.domain.team.service;

import com.b5f1.atention.domain.team.dto.TeamCreateRequestDto;
import com.b5f1.atention.domain.team.dto.TeamResponseDto;
import com.b5f1.atention.domain.team.repository.TeamInvitationRepository;
import com.b5f1.atention.domain.team.repository.TeamParticipantRepository;
import com.b5f1.atention.domain.team.repository.TeamRepository;
import com.b5f1.atention.domain.user.repository.UserRepository;
import com.b5f1.atention.entity.Team;
import com.b5f1.atention.entity.TeamInvitation;
import com.b5f1.atention.entity.TeamParticipant;
import com.b5f1.atention.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamParticipantRepository teamParticipantRepository;
    private final TeamInvitationRepository teamInvitationRepository;
    private final UserRepository userRepository;

    // userRepository 필요 -> 보류
    public List<TeamResponseDto> findMyTeamList() {
        return null;
    }

    /**
     * 로그인한 유저 정보와 초대할 유저 정보를 받아와 팀을 생성하는 메서드
     * @param userId
     * @param teamCreateRequestDto
     * @Return Team
     */
    public Team createTeam(UUID userId, TeamCreateRequestDto teamCreateRequestDto) {
        Team team = Team.builder()
                .name(teamCreateRequestDto.getName())
                .build();
        teamRepository.save(team);

        // 그룹을 생성한 유저 기준으로 TeamParticipant 생성
        User hostUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당하는 유저가 없습니다."));
        teamParticipantRepository.save(new TeamParticipant().createTeamParticipant(hostUser, team, true));

        return team;
    }

    /**
     * 초대받은 유저로 TeamInvitation 생성하는 메서드
     * @param team
     * @param teamCreateRequestDto
     */
    public void inviteUser(Team team, TeamCreateRequestDto teamCreateRequestDto) {
        List<User> userList = userRepository.findAllById(teamCreateRequestDto.getUserIdList());
        for (User invitedUser : userList) {
            TeamInvitation teamInvitation = TeamInvitation.builder()
                    .teamId(team.getId())
                    .userId(invitedUser.getId())
                    .build();
            teamInvitationRepository.save(teamInvitation);
        }
    }


}
