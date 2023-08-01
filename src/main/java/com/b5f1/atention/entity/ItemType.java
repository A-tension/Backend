package com.b5f1.atention.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_type_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    // 아래 코드 없어도 돌아가나 check 해보자
    @OneToMany(mappedBy = "item")
    @Builder.Default
    private List<Item> itemList = new ArrayList<>();
}