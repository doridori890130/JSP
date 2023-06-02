package Handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHandler {
	private static Logger log=LoggerFactory.getLogger(FileHandler.class);

	public int deleteFile(String imageFileName, String savePath) {
		boolean isDel=false;
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imageFileName);
		File removeThumbFile = new File(fileDir+File.separator+"_th"+imageFileName);
		
		if(removeFile.exists()||removeThumbFile.exists()) {
			isDel = removeFile.delete();
			if(isDel) {
				isDel = removeThumbFile.delete();
			}
		}
		return isDel? 1:0;
	}
}
