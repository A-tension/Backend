package com.b5f1.atention.domain.team.service;

import com.b5f1.atention.domain.team.dto.TeamCreateRequestDto;
import com.b5f1.atention.domain.team.dto.TeamDetailResponseDto;
import com.b5f1.atention.domain.team.dto.TeamResponseDto;
import com.b5f1.atention.domain.team.dto.TeamUpdateRequestDto;
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
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamParticipantRepository teamParticipantRepository;
    private final TeamInvitationRepository teamInvitationRepository;
    private final UserRepository userRepository;

    /**
     *
     * @param userId
     * @return TeamResponseDto
     */
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
            if (invitedUser.isDeleted()) {
                continue;
            }
            TeamInvitation teamInvitation = TeamInvitation.builder()
                    .teamId(team.getId())
                    .userId(invitedUser.getId())
                    .build();
            teamInvitationRepository.save(teamInvitation);
        }
    }

    /**
     * teamId를 통해 Team 정보와 참여한 User 정보를 return
     * @param teamId
     * @return teamDetailResponseDto
     */
    public TeamDetailResponseDto getTeamDetail(Long teamId) {
        Team team = findTeamById(teamId);
        List<TeamParticipant> teamParticipantList = team.getTeamParticipantList();
        TeamDetailResponseDto teamDetailResponseDto = new TeamDetailResponseDto().toTeamDetailResponseDto(team);
        for (TeamParticipant teamParticipant : teamParticipantList) {
            teamDetailResponseDto.addUserProfileDto(teamParticipant.getUser());
        }
        return teamDetailResponseDto;
    }

    /**
     * 팀 세부 정보를 수정하는 DTO를 받아 성공하면 그대로 변경된 값을 전달
     * @param teamUpdateRequestDto
     * @return teamUpdateRequestDto
     */
    public TeamUpdateRequestDto updateTeam(UUID userId, Long teamId, TeamUpdateRequestDto teamUpdateRequestDto) {
        Team team = findTeamById(teamId);
        User user = findUserById(userId);

        // 유저가 팀에 속하지 않는 경우
        TeamParticipant teamParticipant = teamParticipantRepository.findByUserAndDeletedFalse(user)
                .orElseThrow(() -> new RuntimeException("팀에 속한 유저가 아닙니다."));

        // 유저가 팀을 변경할 권한이 없는 경우
        if (!teamParticipant.isHasAuthority()) {
            throw new RuntimeException("팀을 변경할 권한이 없습니다.");
        }

        // 실제 DB 값을 가져올건지, 그대로 전달할건지 생각해볼 것
//        teamRepository.save(team.updateTeam(teamUpdateRequestDto));
//        return teamUpdateRequestDto;

        team = teamRepository.save(team.updateTeam(teamUpdateRequestDto));
        return TeamUpdateRequestDto
                .builder()
                .name(team.getName())
                .profileImage(team.getProfileImage())
                .description(team.getDescription())
                .build();

    }


    // 아래는 서비스 내부 로직

    // userId로 유저를 찾고, 없으면 throw Exception
    // 추후에 Exception 변경 예정
    public User findUserById(UUID userId) {
        return userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("해당하는 유저를 찾을 수 없습니다"));
    }

    // teamId로 팀을 찾고, 없으면 throw Exception
    public Team findTeamById(Long teamId) {
        return teamRepository.findByIdAndDeletedFalse(teamId)
                .orElseThrow(() -> new RuntimeException("해당하는 유저를 찾을 수 없습니다"));
    }
}
