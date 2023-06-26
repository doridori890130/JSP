package com.myproject.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.www.domain.BoardDTO;
import com.myproject.www.domain.BoardVO;
import com.myproject.www.domain.CommentVO;
import com.myproject.www.domain.FileVO;
import com.myproject.www.domain.PagingVO;
import com.myproject.www.domain.UserVO;
import com.myproject.www.handler.FileHandler;
import com.myproject.www.handler.PagingHandler;
import com.myproject.www.repository.UserDAO;
import com.myproject.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@Controller
public class BoardController {

	@Inject
	private BoardService bsv;
	@Inject
	private UserDAO udao;
	@Inject
	private FileHandler fhd;
	
	@GetMapping("/register")
	public String registerget(Model m) {
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String registerPost(BoardVO bvo, RedirectAttributes rAttr,
			@RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>> bvo"+bvo.toString());
		log.info(">>>>files : "+files.toString());
		List<FileVO> flist = null;
		
		if(files[0].getSize()>0) { 
			
			flist = fhd.uploadFiles(files);
		}else {
			log.info("file null");
		}
		
		BoardDTO bdto = new BoardDTO(bvo,flist);
		int isOk= bsv.register(bdto);
//		int isOk = bsv.register(bvo);
		log.info(">>board register >>"+(isOk>0? "OK":"FAIL"));
		rAttr.addFlashAttribute("isok",isOk);
		return "redirect:/board/list";
	}
	

	
	
	@GetMapping("/list")
	public String listget(Model m, PagingVO pvo) {
		log.info(">>> pageno : "+pvo.getPageNo());
		log.info(">>> pvo : "+pvo.toString());
		
		List<BoardVO> list = bsv.getlist(pvo);
		m.addAttribute("list",list);
		int totalCount = bsv.getTotalCount(pvo);
		log.info("tot : "+totalCount);
		PagingHandler ph = new PagingHandler(pvo, totalCount);
		m.addAttribute("ph",ph);
		
		return "/board/list";
	}
	
	
	@GetMapping({"/detail","/modify"})
	public void detail(Model m , @RequestParam("bno")int bno, HttpServletRequest request) {
		log.info(">>>> bno :"+bno);
		String mapping = request.getRequestURI();
//		BoardVO bvo= bsv.getDetail(bno);
		BoardDTO bdto = bsv.getDetailFile(bno);
	     log.info("bvo:"+bdto.getBvo());
		String path = mapping.substring(mapping.lastIndexOf("/")+1);
		if(path.equals("detail")) {
			int isOk =bsv.readCount(bno);
		}
		m.addAttribute("bdto",bdto);
//		m.addAttribute("bvo",bdto.getBvo());
//		m.addAttribute("fList",bdto.getFlist());
	}
	
	@PostMapping("/modify")
	public String update(BoardVO bvo,RedirectAttributes rAttr,HttpServletRequest request,
			@RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">> bvo :"+bvo.toString());
		log.info(">> files :"+files.toString());
		
		HttpSession ses = request.getSession();
		UserVO seseUser = (UserVO)ses.getAttribute("ses"); 
//		log.info("sesuser : "+seseUser.toString());
		UserVO user = udao.getUser(seseUser.getId());
		//UserVO user = udao.getUser(bvo.getWriter());
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist = fhd.uploadFiles(files);
		}
		BoardDTO bdto = new BoardDTO(bvo,flist);
//		int isOk= bsv.modify(bvo,user);
		int isOk= bsv.modify(bdto,user);

		log.info(">>board modify >>"+(isOk>0? "OK":"FAIL"));
		rAttr.addFlashAttribute("msg_modify",isOk>0 ? "1":"0");
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno")int bno, RedirectAttributes rAttr, HttpServletRequest request) {
		log.info(">>> bno :"+bno);
		HttpSession ses = request.getSession();
		UserVO seseUser = (UserVO)ses.getAttribute("ses"); //세션객체(현재 로그인한 객체)
		log.info("sesuser : "+seseUser.toString());
		UserVO user = udao.getUser(seseUser.getId());
		
		int isOk=bsv.delete(bno,user);
		rAttr.addFlashAttribute("isOk",isOk);
		return "redirect:/board/list";
	}
	
	
	@DeleteMapping(value="/file/{uuid}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid){
		log.info("uuid: "+uuid);
		return bsv.removeFile(uuid) >0 ?
				new ResponseEntity<String>("1",HttpStatus.OK)
				: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
