package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private int id;

    @Column(nullable = false,length=100)
    private String title;

    @Lob //대용량데이터
    private String content; //섬머노트 라이브러리<html>태그가 섞여서 디자인이됨

    @ColumnDefault("0")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    private User user; //DB는 오브젝트를 저장할 수 없다. 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //mappedBy : 연관관계의 주인이 아니다. db에 칼럼 x
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc ")
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
