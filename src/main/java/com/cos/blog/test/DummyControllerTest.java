package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @DeleteMapping
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        }
        catch(IllegalArgumentException e){
            return "삭제실패";
        }
        return "삭제";
    }
    @Transactional//함수 종료시에 자동 commit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser){
        User user=userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다");
        });
        return user;
    }

    // /dummy/user?page=0 ->0번이 시작 페이지
    //Page<User>는 유저 정보 뿐만아니라 페이징 관련 정보를 넘긴다
    //보통은 List<User>로 리턴해준다
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)
                                           Pageable pageable){
        Page<User> pagingUser=userRepository.findAll(pageable);
        if(pagingUser.isLast()){
            //페이지로 받아서 페이지 관련 메서드를 사용할 수 있다.
        }
        List<User> users=pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        //null을 처리하기 위해 jpa에선 optional객체로 감싸서 리턴해줌
        User user=userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없습니다");
        });
        return user;
    }
    //요청 : 웹브라우저
    //user 객체 = 자바 오브젝트
    //변환 (웹 브라우저가 이해할 수 있는 데이터) ->json
    //스프링부트= MessageConverter라는 애가 응답시에 자동 작동
    //만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가
    //user오브젝트를 json으로 변환해서 브라우저에게 리턴해준다

    @PostMapping("/dummy/join")
    public String join(User user){
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
