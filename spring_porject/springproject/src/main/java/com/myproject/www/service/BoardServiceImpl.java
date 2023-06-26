package com.myproject.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myproject.www.domain.BoardDTO;
import com.myproject.www.domain.BoardVO;
import com.myproject.www.domain.FileVO;
import com.myproject.www.domain.PagingVO;
import com.myproject.www.domain.UserVO;
import com.myproject.www.repository.BoardDAO;
import com.myproject.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	@Inject
	private FileDAO fdao;

	@Override
	public int register(BoardVO bvo) {
		log.info("board register 서비스!");
		
		return bdao.insert(bvo);
	}

	

	@Override
	public int getTotalCount(PagingVO pvo) {
		log.info("board TCOUNT 서비스!");
		return bdao.gettotalcount(pvo);
	}



	@Override
	public List<BoardVO> getlist(PagingVO pvo) {
		
		return bdao.selectBoardListPaging(pvo);
	}



	@Override
	public BoardVO getDetail(int bno) {
		
		return bdao.getDetail(bno);
	}



	@Override
	public int readCount(int bno) {
		// TODO Auto-generated method stub
		return bdao.readCount(bno);
	}



	@Override
	public BoardDTO getDetailFile(int bno) {
		log.info(">>>> detail File in");
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bdao.getDetail(bno)); // bvo 호출
		bdto.setFlist(fdao.getFileList(bno)); // fList 호출
		return bdto;
	}



	@Override
	public int register(BoardDTO bdto) {
		log.info("bvo+fList register in");
		
		int isOk = bdao.insert(bdto.getBvo());
		if(bdto.getFlist()==null) { 
			isOk*=1; 
		}else {
			
			if(isOk>0 && bdto.getFlist().size()>0) {
				
				int bno = bdao.selectBno(); 
				
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					log.info(">>>>> insert File : "+fvo.toString());
					isOk *=fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}

	@Override
	public int modify(BoardDTO bdto, UserVO user) {
		log.info("board modifyfile in");
		BoardVO tmpBoard = bdao.getDetail(bdto.getBvo().getBno());//해당 글의 게시글 호출
		if(user == null || !user.getId().equals(tmpBoard.getWriter())) {
			return 0;			
		}
		int isOk=bdao.modify(bdto.getBvo());
		if(bdto.getFlist() ==null) {
			isOk *=1;
		}else {
			if(isOk>0 && bdto.getFlist().size()>0) {
				int bno = bdto.getBvo().getBno();
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					log.info(">>>>> insert File : "+fvo.toString());
					isOk *=fdao.insertFile(fvo);	//추가한파일 추가
					//삭제는 별도로
				}
			}
		}
		return isOk;
	}



	@Override
	public int delete(int bno, UserVO user) {
		BoardVO tmpBoard = bdao.getDetail(bno);
		if(user == null || !user.getId().equals(tmpBoard.getWriter())) {
			return 0;			
		}
		return bdao.delete(bno);
	}



	@Override
	public int removeFile(String uuid) {
		log.info(">>>> remove File in");
		return fdao.deleteFile(uuid);
	}
}
