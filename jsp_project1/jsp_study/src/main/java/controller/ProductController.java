package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVO;
import service.ProductService;
import service.Service;

//import 한번씩 확인하기

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;  //서블릿의 시리얼 아이디
       // 상품등록, 상품리스트, 상품상세, 상품수정, 상품삭제
       // 컨트롤러 -> 서비스, 서비스-> DAO 호출 DAO - CBconnection
	private Service svc;
	
 
	//생성자
    public ProductController() {
     
    	svc= new ProductService();
    	
    	
    }
     
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get / post 모든 처리는 service에서 처리함
		// post 방식으로 데이터 처리를 할 때 한글이 깨지는 것을 방지
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();// 요청 경로(원하는 서비스) 컨트롤러주소/요청서비서
		System.out.println(">>>uri : "+uri);
		String context = req.getContextPath(); // 프로젝트명
		System.out.println("context:"+context);
		
		//객체를 보내야하는 목적지 주소
		String destPage="";
		switch(uri) {
		case "/register.pd" : 
			// /폴더명/jsp파일명.jsp
			destPage="/register.jsp";
			break;
		case "/insert.pd":
			//db 연결, 등록처리
			//jsp에서 가져온 객체(이름,가격,정보)를 가지고 왔다.
			//service에게 가져온 데이터를 객체화하여 db에 저장해달라고 요청
			// 1. 객체를 생성한다.
//			ProductVO pvo = new ProductVO();
			String pname = req.getParameter("pname"); 
			int price = Integer.parseInt(req.getParameter("price")); // string 처리
			String madeby = req.getParameter("madeby");
			ProductVO pvo = new ProductVO(pname, price, madeby);
//			생성자가 없을경우
//			ProductVO pvo = new ProductVO();
//			pvo.setPname(pname);
//			pvo.setPrice(price);
//			pvo.setMadeby(madeby);
			
			
			//2. sevice에게 객체주고 요청
			int isOk = svc.register(pvo); // 리턴값 1개의 행이 등록
			System.out.println(">>>상품등록 >"+(isOk>0? "성공":"실패"));
			
			destPage="/index.jsp";
			
			break;
			
		case "/list.pd" :
			List<ProductVO> list = new ArrayList<ProductVO>();
			list = svc.list();
			req.setAttribute("list", list);
			System.out.println(">>상품리스트 성공~!");
			destPage="/list.jsp";
			break;
			
		case "/detail.pd" :
			int pno = Integer.parseInt(req.getParameter("pno"));
			ProductVO p = new ProductVO();
			p = svc.detail(pno);
			req.setAttribute("pvo", p);
			System.out.println("상품상세 성공~!!");
			destPage="/detail.jsp";
			break;
		case "/modify.pd" :
			int pno1 = Integer.parseInt(req.getParameter("pno"));
			req.setAttribute("pvo", svc.detail(pno1) );
			System.out.println("상품수정정보 성공~!!");
			destPage="/modify.jsp";
			break;
			
		case "/edit.pd" :
			int pno2 = Integer.parseInt(req.getParameter("pno"));
			String pname1 = req.getParameter("pname"); 
			int price1 = Integer.parseInt(req.getParameter("price")); // string 처리
			String regdate1 = req.getParameter("regdate");
			String madeby1 = req.getParameter("madeby");
			
			
			ProductVO p1 = new ProductVO(pno2,pname1,price1,regdate1,madeby1);
			int isOk1 = svc.edit(p1);
			
			System.out.println(">>>상품수정 >"+(isOk1>0? "성공":"실패"));
			destPage="/list.pd";
			break;
			
		case "/remove.pd" :
			int pno3 = Integer.parseInt(req.getParameter("pno"));
			int isOk3 = svc.remove(pno3);
			System.out.println(">>>상품삭제 >"+(isOk3>0? "성공":"실패"));
			destPage="/list.pd";
			break;
		}
		
		//웹의 목적지 주소로 연결해주는 객체
		//destpage 로 이동시 응답,요청 객체를 싣고 가야함
		RequestDispatcher rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 오는 값을 체킹
		service(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 방식으로 오는 값을 체킹
		
		service(request, response);
		//모든 요청을 서비스를 통해서 처리함
	}

}