package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해 Bean에 등록해줌, .IOC를 해준다
@Service
public class BoardService {
//
//	@Autowired
//	private BoardRepository boardRepository;
//
//	@Autowired
//	private ReplyRepository replyRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Transactional
//	public void 글쓰기 (Board board,User user) {
//		board.setCount(0);
//		board.setUser(user);
//		boardRepository.save(board);
//	}
//
//	@Transactional
//	public void 댓글쓰기 (ReplySaveRequestDto replySaveRequestDto) {
//		int result =replyRepository.mySave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
//
//	}
//
//	@Transactional(readOnly =true)
//	public Page<Board>글목록(Pageable pageable){
//		return boardRepository.findAll(pageable);
//	}
//
//
//	@Transactional(readOnly =true)
//	public Board 글상세보기(int id) {
//		return boardRepository.findById(id)
//		.orElseThrow(()->
//		{
//			return new IllegalArgumentException("글상세보기 실패:아이디를 찾을 수 없습니다.");
//		});
//	}
//
//
//	@Transactional
//	public void 삭제하기(int id) {
//		boardRepository.deleteById(id);
//	}
//
//
//	@Transactional
//	public void 글수정하기(int id, Board requestBoard) {
//		Board board = boardRepository.findById(id)
//				.orElseThrow(()->
//				{
//					return new IllegalArgumentException("글상세보기 실패:아이디를 찾을 수 없습니다.");
//				}); //영속화 완료
//		board.setTitle(requestBoard.getTitle());
//		board.setContent(requestBoard.getContent());
//		//해당 함수 종료시 (Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트 db flush
//		}
//
//
//
////	@Transactional(readOnly = true) //select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
////	public User 로그인(User user) {//여러번 selete를 시도해도 같은 값을 유지할 수 있음
////		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
////	}
}
