package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//RestController와의 다른점 controller는 경로 아래 파일을 리턴
//프론트와의 협업에는 restcontroller가 쓰인다
@Controller
public class TempControllerTest {
    //파일 리턴 기본경로 : /src/main/resources/static
    //jsp파일은 정적 파일과 다르게 동적파일이기 때문에(컴파일이 필요하기 떄문에) 기본 경로로는 인식을 못한다.
}
