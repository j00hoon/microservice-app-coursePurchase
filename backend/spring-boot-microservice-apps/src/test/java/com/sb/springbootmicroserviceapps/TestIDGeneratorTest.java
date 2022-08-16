package com.sb.springbootmicroserviceapps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sb.springbootmicroserviceapps.test.TestIDGenerator;
import com.sb.springbootmicroserviceapps.test.TestIDGeneratorRepository;

@SpringBootTest
public class TestIDGeneratorTest 
{
	@Autowired
	private TestIDGeneratorRepository testIDGenRepo;
	
	
	@Test
	public void save_auto_test()
	{
		TestIDGenerator testIDGen = new TestIDGenerator();
		testIDGen.setName("test name1");
		
		testIDGenRepo.save(testIDGen);
	}
}
