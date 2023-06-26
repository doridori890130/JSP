package com.myproject.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.myproject.www.domain.FileVO;

@AllArgsConstructor
@Slf4j
@Component
public class FileHandler {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileUpload";
	public List<FileVO> uploadFiles(MultipartFile[] files){
		LocalDate date = LocalDate.now();
		log.info(">>> date : "+date); 
		String today = date.toString(); //2023-06-14 date 객체를 String 변환
		// today = 폴더 구조로 변경 2023\06\14(window) 2023/06/14(linux)
		today = today.replace("-", File.separator);
		// today 폴더 구성
		File folders = new File(UP_DIR, today);
		//폴더가 기존에 있다면 생성x 없다면 생성 o
		if(!folders.exists()) {
			folders.mkdirs(); //폴더 생성 명령
		}
		//경로설정
		List<FileVO> fList = new ArrayList<FileVO>();
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today); // 파일 경로 설정
			fvo.setFile_size(file.getSize()); // 파일 사이즈 설정 return 형태 long
			//경로가 포함될어있을 수도 있는 파일명
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			log.info(">>> onlyFileName : "+onlyFileName); //파일명
			fvo.setFile_name(onlyFileName); // 파일 이름 설정
			
			UUID uuid= UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			// 여기까지 fvo에 저장할 정보 생성 -> set
			
			// 디스크 파일 저장할 객체 생성
			String fullfileName = uuid.toString()+"_"+onlyFileName;
			File storeFile = new File(folders, fullfileName);
			
			
			//생성
			try {
				file.transferTo(storeFile); //원본 객체를 저장을 위한 형태의 객체로 복사
				// 파일 타입 결정
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					File ThumbNail = new File(folders,uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(ThumbNail);
				}
				
			} catch (Exception e) {
				log.info(">>> file 생성 오류~!!");
				e.printStackTrace();
			}
			fList.add(fvo);
		}
		return fList;
	}
	//tika를 사용하여 파일 형식 체크 => 이미지 파일이 맞는지 체크
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); //image/jpg, image/png
		return mimeType.startsWith("image")? true: false;
		
	}

}
