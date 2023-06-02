package orm;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Databasebuilder {
	private static Logger log = LoggerFactory.getLogger(Databasebuilder.class);
	
	private static SqlSessionFactory factory;
	private static final String config = "orm/MybatisConfig.xml";
	
	static{
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(config));
		} catch (IOException e) {
			log.info("Mybatis 설정 오류");
			
			e.printStackTrace();
		}
	}	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
