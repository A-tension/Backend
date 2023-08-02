package com.b5f1.atention.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private String description;

    @OneToMany(mappedBy = "item")
    @Builder.Default
    private List<MyItem> myItemList = new ArrayList<>();
}