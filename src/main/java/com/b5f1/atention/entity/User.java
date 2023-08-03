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

    // KAKAO, NAVER, GOOGLE
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)
    private String socialId;

    private String refreshToken;

    public void updateName(String name){
        this.name = name;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}