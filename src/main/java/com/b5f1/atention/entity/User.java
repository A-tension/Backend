package com.b5f1.atention.entity;

import com.b5f1.atention.entity.enums.Role;
import com.b5f1.atention.entity.enums.SocialType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column
    private String name;

    @Column
    private String profileImage;

    @Column
    @Builder.Default
    private int ticket = 0;

    @Column(nullable = false)
    private String meetingUrl;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<TeamParticipant> teamParticipantList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<MyItem> myItemList = new ArrayList<>();

    @Column(name = "oauth")
    @Enumerated(value = EnumType.STRING)
    private SocialType oAuth;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    private String refreshToken; // 리프레시 토큰

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    // 유저 이름 변경 메서드
    public void updateName(String name){
        this.name = name;
    }

    // refreshToken 갱신 메서드
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}