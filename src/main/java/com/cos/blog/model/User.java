package com.cos.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM: java(object)-> 테이블로 바꿔주는기술

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY: 프로젝트에서 연결된 DB의 넘저링 전략을 따라간다
    private int id;

    @Column(nullable = false,length=30, unique = true)
    private String username;

    @Column(nullable = false,length=100)
    private String password;

    @Column(nullable = false,length=50)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 좋다. manegerrr 이런식으로 오타 방지

    @CreationTimestamp
    private Timestamp createDate;
}
