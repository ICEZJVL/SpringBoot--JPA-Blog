package com.cos.blog.repository;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//DAO
//자동으로 bean 등록이되서 @Repository를 생략할 수 있다
public interface BoardRepository extends JpaRepository<Board, Integer> {
    //User findByUsernameAndPassword(String username,String password);
}