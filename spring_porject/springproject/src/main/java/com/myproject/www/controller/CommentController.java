package com.myproject.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.www.domain.CommentVO;
import com.myproject.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/comment/*")
@Slf4j
public class CommentController {

	@Inject
	private CommentService csv;
	
	@PostMapping(value = "/post", consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info("post진입");
		int isOk= csv.register(cvo);
		return isOk > 0? new ResponseEntity<String>("1",HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@GetMapping(value="/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CommentVO>> spread(@PathVariable("bno")int bno){
		log.info(">>>> comment List bno"+bno);
		
		List<CommentVO> list =csv.getList(bno);
		log.info("list"+list.size());
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
	}
	@DeleteMapping(value="/remove/{cno}",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("cno")int cno){
		log.info(">>>cno : "+cno);
		
		int isOk= csv.remove(cno);
		return isOk > 0? new ResponseEntity<String>("1",HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="/{cno}", consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@PathVariable("cno")int cno,@RequestBody CommentVO cvo){
		log.info("modify cno :" +cno);
		log.info("cvo : "+cvo);
		int isOk =csv.modify(cvo);
		return isOk > 0? new ResponseEntity<String>("1",HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
