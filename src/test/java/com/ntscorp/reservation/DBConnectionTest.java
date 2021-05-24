package com.ntscorp.reservation;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ntscorp.reservation.configuration.ApplicationConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class DBConnectionTest {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void test() {
		for(int i=0; i<20; i++) {
			System.out.println(Math.random());
		}
	}
	
	@Test
	public void mybatisConnectionTest() {
		try(Connection connection = sqlSessionFactory.openSession().getConnection()) {
			System.out.print("success");
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
