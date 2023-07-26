package com.b5f1.atention.domain.Team;

import com.b5f1.atention.domain.team.dto.TeamCreateRequestDto;
import com.b5f1.atention.domain.team.repository.TeamInvitationRepository;
import com.b5f1.atention.domain.team.repository.TeamParticipantRepository;
import com.b5f1.atention.domain.team.repository.TeamRepository;
import com.b5f1.atention.domain.team.service.TeamServiceImpl;
import com.b5f1.atention.domain.user.repository.UserRepository;
import com.b5f1.atention.entity.Team;
import com.b5f1.atention.entity.TeamInvitation;
import com.b5f1.atention.entity.TeamParticipant;
import com.b5f1.atention.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class TeamTests {

    @Autowired
    private TeamServiceImpl teamService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamParticipantRepository teamParticipantRepository;

    @Autowired
    private TeamInvitationRepository teamInvitationRepository;

    @Autowired
    private UserRepository userRepository;

    // 팀 새로 생성 후 초대 테스트
    @Test
    public void createTeamTest() throws Exception{
        //given
        User hostUser = User.builder()
                .email("hostUser")
                .meetingUrl("test")
                .build();
        userRepository.saveAndFlush(hostUser);

        User invitedUser1 = User.builder()
                .email("invitedUser")
                .meetingUrl("test")
                .build();
        userRepository.saveAndFlush(invitedUser1);

        User invitedUser2 = User.builder()
                .email("invitedUser")
                .meetingUrl("test")
                .build();
        userRepository.saveAndFlush(invitedUser2);

        List<User> userList = userRepository.findAllByEmail("invitedUser");
        List<UUID> userIdList = new ArrayList<>();
        for (User user : userList) {
            userIdList.add(user.getId());
        }

        TeamCreateRequestDto teamCreateRequestDto = TeamCreateRequestDto.builder()
                .userIdList(userIdList)
                .name("TestName")
                .build();

        //when
        User user = userRepository.findByEmail("hostUser")
                .orElseThrow(() -> new ArithmeticException("유저를 찾을 수 없습니다"));
        Team createdTeam = teamService.createTeam(user.getId(), teamCreateRequestDto);
        teamService.inviteUser(createdTeam, teamCreateRequestDto);

        //then
        Optional<TeamParticipant> teamParticipant = teamParticipantRepository.findByUser(hostUser);
        Assertions.assertThat(teamParticipant).isNotEqualTo(Optional.empty());
    }
}
