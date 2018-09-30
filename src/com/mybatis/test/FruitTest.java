package com.mybatis.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.mybatis.dao.fruit.IFruit;
import com.mybatis.model.fruit.Fruit;

public class FruitTest {
	private static SqlSessionFactory sqlSessionFactory;

	static {
		Reader reader;
		try {
			// ��ȡ�����ļ�
			reader = Resources.getResourceAsReader("configuration.xml");
			// ����sqlSessionFactory
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			// getMapper�еĲ���Ϊ��Ӧ�Ľӿ���
			IFruit iFruit = sqlSession.getMapper(IFruit.class);
			// ����ѧ��Ϊ9��ѧ��������ӡ
			Fruit fruit = iFruit.findFruitById("k002");
			Logger.getLogger(Fruit.class).info("id:" + fruit.getId());
			Logger.getLogger(Fruit.class).info("name:" + fruit.getName());
			Logger.getLogger(Fruit.class).info("price:" + fruit.getPrice());
			Logger.getLogger(Fruit.class).info("source:" + fruit.getSource());
			Logger.getLogger(Fruit.class).info("numbers:" + fruit.getNumbers());

			fruit.setName("�ض�ү");
			fruit.setSource("���");
			iFruit.updateFruit(fruit);
			sqlSession.commit();
			String id = "k00%"; 
			IFruit iF = sqlSession.getMapper(IFruit.class);
		    List<Fruit> fruits = iF.selectLike(id);
		    for(Fruit f:fruits){
		    	Logger.getLogger(Fruit.class).info(f.getId()+f.getName());
		    }
		} catch (Exception e) {
			Logger.getLogger(Fruit.class).info(e.getMessage());
		}
		sqlSession.close();
	}

}
