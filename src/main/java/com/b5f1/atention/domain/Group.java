package com.b5f1.atention.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "group")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column
    private String name;

    @Column(name = "profile_image")
    private String profileImage;

    @Column
    private String description;
}