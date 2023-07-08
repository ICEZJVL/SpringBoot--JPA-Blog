package com.cos.blog.controller.api;

import com.cos.blog.config.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.글쓰기(board,principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);//자바 오브젝트르 json으로 바꿔서 리턴
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> updateById(@PathVariable int id,@RequestBody Board board) {
        boardService.글수정하기(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //데이터 받을때 컨트롤러에서 dto를 만들어서 받는게 좋다.
    @PostMapping("/api/board/{id}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){

        boardService.댓글쓰기(reply, boardId,principal.getUser().getId());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);//자바 오브젝트르 json으로 바꿔서 리턴
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제하기(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
