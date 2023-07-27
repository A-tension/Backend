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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public List<TeamResponseDto> findMyTeamList(UUID userId) {
        User user = findUserById(userId);

        // 유저와 연관관계가 있는 팀 참여자 List 얻기
        List<TeamParticipant> teamParticipantList = user.getTeamParticipantList();

        // TeamResponseDto 변환
        List<TeamResponseDto> teamResponseDtoList = new ArrayList<>();
        for (TeamParticipant teamParticipant : teamParticipantList) {
            TeamResponseDto teamResponseDto = new TeamResponseDto().toTeamResponseDto(teamParticipant.getTeam());
            teamResponseDtoList.add(teamResponseDto);
        }

        return teamResponseDtoList;
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
        User hostUser = findUserById(userId);
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

    // 아래는 서비스 내부 로직

    // userId로 유저를 찾고, 없으면 throw Exception
    // 추후에 Exception 변경 예정
    public User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당하는 유저를 찾을 수 없습니다"));
    }

}
