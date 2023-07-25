package com.b5f1.atention.domain;

import com.b5f1.atention.enums.LoginWith;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "user_id")
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column
    private String name;

    @Column(name = "profile_image")
    private String profileImage;

    @Column
    @Enumerated(value = EnumType.STRING)
    private LoginWith oAuth;

    @Column
    @Builder.Default
    private int ticket = 0;

    @Column(name = "meeting_url", nullable = false)
    private String meetingUrl;
}