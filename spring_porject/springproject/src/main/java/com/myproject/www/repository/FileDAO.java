package com.myproject.www.repository;

import java.util.List;

import com.myproject.www.domain.FileVO;

public interface FileDAO {

	List<FileVO> getFileList(int bno);

	int insertFile(FileVO fvo);

	int deleteFile(String uuid);

	List<FileVO> getFileallList();

}
