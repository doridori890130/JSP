package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.portlet.PortletFileUpload;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Handler.FileHandler;
import Handler.PagingHandler;
import domain.BoardVO;
import domain.PagingVO;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log= LoggerFactory.getLogger(BoardController.class);
    private RequestDispatcher rdp; //웹의 목적지 주소로 객체를 전달해주는 객체
    private BoardService bsv;
    private String destPage;
    private int isOk;
    //파일경로를 저장할 변수
    private String savePath;
    private final String UTF8="utf-8";// 인코딩 설정시
    
    public BoardController() {
        bsv = new BoardServiceImpl();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	// 캐릭터 인코딩 설정, 컨텐츠 타입 설정
    	request.setCharacterEncoding("utf-8");
    	response.setCharacterEncoding("utf-8");
    	response.setContentType("text/html; charset=utf-8");
    	
    	String uri = request.getRequestURI();
    	log.info(">>>uri>"+uri);
    	String path = uri.substring(uri.lastIndexOf("/")+1);
    	log.info(">>> path>"+path);
    	
    	switch(path) {
    	case "register":
    		destPage="/board/register.jsp";
    		break;
    	case "insert":
    		try {
    			//파일을 업로드할 물리적인 경로를 설정
    			savePath = getServletContext().getRealPath("/_fileUpload");
    			log.info(">>>> 파일 저장 경로 : "+savePath);
    			File fileDir = new File(savePath);
    			
    			DiskFileItemFactory fileitemFactory = new DiskFileItemFactory();
    			 // 파일의 저장위치를 담고있는 객체를 저장
    			fileitemFactory.setRepository(fileDir);
    			 // 파일 저장을 위한 임시 메모리 용량설정 : byte 단위
    			fileitemFactory.setSizeThreshold(2*1024*1024); //2MB
    			
    			BoardVO bvo = new BoardVO();
    			//multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
    			ServletFileUpload fileUpload = new ServletFileUpload(fileitemFactory);
    			
    			List<FileItem> itemList = fileUpload.parseRequest(request);
    			for(FileItem item : itemList) {
    				switch(item.getFieldName()) {
    				case "title" :
    					bvo.setTitle(item.getString(UTF8)); //인코딩 형식을 담아서 변환
    					break;
    				case "writer":
    					bvo.setWriter(item.getString(UTF8)); //인코딩 형식을 담아서 변환

    					break;
    				case "content":
    					bvo.setContent(item.getString(UTF8));
    					break;
    				case "image_file":
    					//이미지가 있는지 없는지 체크
    					if(item.getSize()>0) {	//데이터의 크기를 이용하여 유무 결정
    						String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1);//경로를 포함한 파일이름	
    						fileName = System.currentTimeMillis()+"_"+fileName;
    						log.info(">>> fileName : "+fileName);
    						File uploadFilePath = new File(fileDir+File.separator+fileName);
    						log.info("실제 파일경로 : "+uploadFilePath);
    						
    						//저장
    						try {
								item.write(uploadFilePath); // 자바 개체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								
								//썸네일 작업 : 리스트 페이지에서트래픽을 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
								
							} catch (Exception e) {
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
    					}
    					break;
    				}
    			}
    			isOk = bsv.insert(bvo);
				log.info(">>> register > "+ (isOk>0 ? "성공":"실패"));
    			destPage="page";
    			
    			
//				String tit = request.getParameter("title");
//				String wri = request.getParameter("writer");
//				String cont = request.getParameter("content");
//				
//				
//				BoardVO bvo = new BoardVO(tit,wri,cont);
//				isOk = bsv.insert(bvo);
//				log.info(">>> register > "+ (isOk>0 ? "성공":"실패"));
//				destPage="list";
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break;
    	case "list":
    		try {
				List<BoardVO> list = new ArrayList<BoardVO>();
				list = bsv.list();
				request.setAttribute("list", list);
				log.info("list 성공~!!");
				log.info(">>> "+list.size());
				destPage="/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break;
 
    	case "page":
    		try {
				int pageNo = 1;
				int qty = 10;
				String type ="";
				String keyword="";
				if(request.getParameter("type") != null) {
					type = request.getParameter("type");
					keyword = request.getParameter("keyword");					
					log.info(">>> type > "+type + ">>> keyword > "+keyword);
				}
				if(request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				PagingVO pgvo = new PagingVO(pageNo,qty);
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				log.info(">>>pgvo > "+pgvo);
				//전체 페이지 개수
				int totCount = bsv.getTotal(pgvo);
				log.info("전체 페이지 개수 : "+totCount);
				//limit를 이용한 select List를 호출 (startPage, qty
				// 한페이지에 나와야 하는 리스트
				List<BoardVO> list = bsv.getPageList(pgvo);
				log.info(">>>> 리스트 0번지: "+list.get(0));
				log.info(">>>> list : "+list.size());
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				request.setAttribute("pgh", ph);
				request.setAttribute("list", list);
				log.info("pageList 성공~!!");
				destPage="/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break;
    		
    	case "detail" :
    		try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = new BoardVO();
				bvo = bsv.detail(bno);
				request.setAttribute("bvo", bvo);
				log.info("detail 성공~!");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    		destPage="/board/detail.jsp";
    		break;
    	case "modify":
    		try {
    			int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = new BoardVO();
				bvo = bsv.modify(bno);
				request.setAttribute("bvo", bvo);
				destPage="/board/modify.jsp";//큰트롤러 list에서 db검색 후 객체가지고 list.jsp로 이동
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break;
    		
    	case "edit":
    		try {
    			savePath = getServletContext().getRealPath("/_fileUpload");
    			File fileDir = new File(savePath);
    			
    			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
    			fileItemFactory.setRepository(fileDir);
    			fileItemFactory.setSizeThreshold(2*1024*1024);
    			
    			BoardVO bvo = new BoardVO();
    			
    			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
    			
    			log.info(">> update 준비");
    			
    			List<FileItem> itemList = fileUpload.parseRequest(request);
    			
    			String old_file = null;
    			for(FileItem item : itemList) {
    				switch(item.getFieldName()) {
    				case "bno":
    					bvo.setBno(Integer.parseInt(item.getString(UTF8)));
    					break;
    				case "title":
    					bvo.setTitle(item.getString(UTF8));
    					break;
    				case "content":
    					bvo.setContent(item.getString(UTF8));
    					break;
    				case "image_file":
    					//기존 파일의 이름을 가져와서 담기
    					old_file = item.getString(UTF8);
    					break;
    				case "new_file":
    					if(old_file != null) {    						
	    					if(item.getSize()>0) { // 새로운 파일이 등록됨
	    						//파일 핸들러 호출 (기존 파일을 삭제)
	    						FileHandler fileHandler = new FileHandler();
	    						isOk=fileHandler.deleteFile(old_file, savePath);
	    					}	
	    					//이름 설정 경로+이름
	    					// File.separator = "/" 윈도우같은경우는 같다
	    					String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1);
	    					log.info(">>> new_fileName : "+fileName);
	    					//실제 저장이름
	    					fileName= System.currentTimeMillis()+"_"+fileName;
	    					File uploadFilePath = new File(fileDir+File.separator+fileName);
	    					try {
								item.write(uploadFilePath);
								bvo.setImage_file(fileName);
								log.info(">>> bvo.image_file > "+bvo.getImage_file());
								//썸네일 작업
								Thumbnails.of(uploadFilePath).size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e2) {
								log.info(">>> file update on disk fail");
								e2.printStackTrace();
							}
	    					
    					}else { //새로운파일을 넣지 않았다면...
    						//기존파일을 다시 저장
    						bvo.setImage_file(old_file);
    					}
    					break;
    				}
    			}
    			
    			
//				int bno = Integer.parseInt(request.getParameter("bno"));
//				String tit = request.getParameter("title");
//				String wri = request.getParameter("writer");
//				BoardVO bvo = new BoardVO(bno,tit,wri);
				isOk = bsv.edit(bvo);
				log.info(">>> edit > "+ (isOk>0 ? "성공":"실패"));
				destPage="page"; //컨트롤러 list에서 db검색 후 객체 가지고 list로
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break;
    		
		case "remove":
			try {
				
				int bno = Integer.parseInt(request.getParameter("bno"));
				//파일 삭제 추가
				//image_file 호출 , savePath 호출
				String imageFileName = bsv.getFileName(bno); // 삭제할 파일이름 데이터베이스에서 호출
				log.info(">>> removeFile : "+imageFileName);
				savePath = getServletContext().getRealPath("/_fileUpload");
				FileHandler fh = new FileHandler();
				isOk = fh.deleteFile(imageFileName, savePath);
				log.info(">>> removeFile >>" + (isOk>0 ? "OK":"FAIL"));
				isOk = bsv.delete(bno);
				log.info(">>> allremoveFile >>" + (isOk>0 ? "OK":"FAIL"));
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			destPage="page";
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
