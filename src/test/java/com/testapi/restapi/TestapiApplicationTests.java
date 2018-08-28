package com.testapi.restapi;

import com.testapi.TestapiApplication;
import com.testapi.restapi.controller.UserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestapiApplication.class)
public class TestapiApplicationTests {

	@Test
	public void contextLoads() {
	}
}
