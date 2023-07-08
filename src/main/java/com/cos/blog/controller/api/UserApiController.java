package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth.joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);//자바 오브젝트르 json으로 바꿔서 리턴
    }

    @PutMapping
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.회원수정(user);
        //db는 변경되어 있지만 세션값은 변경되지 않은 상태

        //세션등록
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);//자바 오브젝트르 json으로 바꿔서 리턴
    }
}
