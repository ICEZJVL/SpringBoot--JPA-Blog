package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //Ioc를해준다 1.트랜잭션 관리 2. 서비스 의미때문
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void 회원가입(User user){
        String encodePassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }


    @Transactional
    public void 회원수정(User old) {
        User user= userRepository.findById(old.getId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        user.setPassword(passwordEncoder.encode(old.getPassword()));
        user.setEmail(old.getEmail());


    }

}
