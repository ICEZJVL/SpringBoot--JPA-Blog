package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {


    @GetMapping("/test/hello")
    public String hello(){
        return "<h1>hello spring boot</h1>";
    }

    //인터넷 브라우저 요청으로는 get 요청만 가능하다.
    //get방식으로 데이터를 요청할때는 쿼리스트링 방법말고는 불가능
    //@RequestParam int id, ... 로 하나씩 받거나 클래스로 한번에받을 수 있음
    //MessageConverter(스프링부트)가 변환해줌
    @GetMapping("/http/get")
    public String getTest(Member member){
        return "get 요청"+ member.toString();
    }

    //get요청은 바디로 데이터를 담아 보내지 않고 주소에 보냄
    //나머지는 body에 데이터를 담아보냄
    @PostMapping("/http/get")
    public String postTest(@RequestBody Member member){
        return "post 요청"+ member.toString();
    }
}
