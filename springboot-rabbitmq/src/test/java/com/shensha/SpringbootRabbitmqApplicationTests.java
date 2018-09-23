package com.shensha;

import com.shensha.receiver.Receiver;
import com.shensha.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;

	@Test
	public void contextLoads() {
	}

	@Test
	public void hello(){
		sender.send();

	}

	@Test
	public void a(){
		receiver.process("hello");
	}

}
