package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.Session;

import domain.MemberVO;

import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   /* log설정 */ 
   // private static final Logger log = LoggerFactory.getLogger(클래스명);    
   private static final Logger log = LoggerFactory.getLogger(MemberController.class);
   private RequestDispatcher rdp;
   private MemberService msv;
   private int isOk;
   private String destPage; //목적지 주소
   
 
    public MemberController() {
        msv = new MemberServiceImpl();        
    }

   
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // characterEncoding 설정 /contentType / uri
      request.setCharacterEncoding("utf-8");
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      
      String uri = request.getRequestURI();
      	log.info(">>> uri : "+uri);
      
  
      	// /mem/join : 요청에 대한 path만 남기기
      	String path = uri.substring(uri.lastIndexOf("/")+1);
      	log.info(">>>path : "+path);
      	
      switch(path) {
      case "join" :
    	  destPage = "/member/join.jsp";
    	  break;
      case "register":
    	  String id = request.getParameter("id");
    	  String pw = request.getParameter("pw");
    	  String email = request.getParameter("email");
    	  int age =Integer.parseInt(request.getParameter("age"));
    	  //파라미터로 객체 생성
    	  MemberVO mvo = new MemberVO(id,pw,email,age);
    	  isOk =msv.register(mvo);
    	  log.info(">>> Join > "+ (isOk>0 ? "성공":"실패"));
    	  
    	  destPage="/";
    	  break;
    	  
      case "login" :
    	  log.info(">>> login page 이동");
    	  destPage="/member/login.jsp";
    	  break;
      case "login_auth":  //실제 로그인이 일어나는 케이스
    	  
    	  try {
			
    		  String id2 = request.getParameter("id");
    		  String password2 = request.getParameter("password");
    		  MemberVO mvo2 = new MemberVO(id2,password2);
    		  //해당 id, password가 db상에 있는지 체크
    		  // 해당 객체(사용자)를 가져와서
    		  //해당 객체 (사용자)를 세션에 담기
    		  log.info(">> login 요쳥"+mvo2);
    		  MemberVO loginMvo = msv.login(mvo2);
    		  
    		  //같은 정보가 있으면 객체를 리턴, 없다면 null이 리턴
    		  //객체가 있다면 세션에 저장
    		  if(loginMvo!=null) {
    			  //세션을 가져오기. 연결된 세션이 없다면 새로 생성
    			  HttpSession ses = request.getSession(); //세션 있는지 없는지 확인
    			  ses.setAttribute("ses", loginMvo);
    			  //로그인 유지 시간(초단위)
    			  ses.setMaxInactiveInterval(10*60);
    			  
    		  }else {
    			  request.setAttribute("msg_login", 0);
    		  }
    		  destPage="/";
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  break;
      case "logout" :
    	  try {
			//세션 가져오기(연결된 세션)
    		  HttpSession ses = request.getSession();
    		  //마지막 로그인 시간 기록
    		  MemberVO mvo2 = (MemberVO)ses.getAttribute("ses"); //오브젝트 형태로 오기때문에 형변환 시켜줘야함
    		  String id2 = mvo2.getId();
    		  log.info(">>>> login id :"+id2);
    		  // 로그인정보(id)를 주고 마지막 로그인 시간 기록(update)
    		  isOk = msv.lastLogin(id2);
    		  log.info(">>> logout >>>>>>>>>>> "+ (isOk>0 ? "성공":"실패"));
    		  ses.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  destPage="/";
    	  break;
    	  
      case "modify" :
    	  log.info("맴버 수정진입");
  
    	  destPage="/member/modify.jsp";
    	  break;
    	  
      case "edit" :
    	  try {
    		  String id2 = request.getParameter("id");
    		  String pw2 = request.getParameter("password");
    		  String email2 = request.getParameter("email");
    		  int age2 =Integer.parseInt(request.getParameter("age"));
  
    		  MemberVO mvo2 = new MemberVO(id2,pw2,email2,age2);
    		  isOk = msv.edit(mvo2);
    		  log.info(">>> edit >>>>>>>>>>> "+ (isOk>0 ? "성공":"실패"));
    		  
    		  if(isOk>0) {
    			  //case1 session 객체에 값을 담기
    			  HttpSession ses = request.getSession();
    			  ses.setAttribute("ses", mvo2);
    			  
//    			  //case 2 login_auth case로 mvo2 객체보내기
//    			  request.setAttribute("mvo", mvo2);
    		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  destPage = "/";
    	  break;
      case "remove":
    	  try {
    		HttpSession ses = request.getSession();
    		MemberVO mvo2 = (MemberVO)ses.getAttribute("ses");
    		String id2 = mvo2.getId();
			log.info("삭제시작");
			isOk = msv.remove(id2);
			log.info(">>> remove >>>>>>>>>>> "+ (isOk>0 ? "성공":"실패"));
			ses.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 
    	  destPage="/";
    	  break;
      case "list":
    	  try {
    		int i = 0;
			List<MemberVO> list = new ArrayList<MemberVO>();
			list = msv.list();
			request.setAttribute("list", list);
			log.info("list 성공~!");
			destPage="/member/list.jsp";
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  break;
      }
      
      RequestDispatcher rdp = request.getRequestDispatcher(destPage);
      rdp.forward(request, response);
      
   }

   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      service(request, response);
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      service(request, response);
   }

}