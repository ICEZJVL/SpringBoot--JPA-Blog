package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //Ioc를해준다 1.트랜잭션 관리 2. 서비스 의미때문
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board,User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void 글삭제하기(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //해당 함수로 종료시 트랜잭션 종료 이때 더티체킹이 일어나면서 자동업데이트
    }

    @Transactional
    public void 댓글쓰기(Reply requestReply, int boardId, int userId) {
        User user = userRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 쓰기 실패 : 유저 아이디를 찾을 수 없습니다.");
                }); // 영속화        requestReply.setUser(user);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("댓글 쓰기 실패 : 게시물 아이디를 찾을 수 없습니다.");
                }); // 영속화        requestReply.setUser(user);
        requestReply.setUser(user);
        requestReply.setBoard(board);
        replyRepository.save(requestReply);
    }

    @Transactional
    public void 댓글삭제하기(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
