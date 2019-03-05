package com.pancho.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pancho.web.annotion.Log;
import com.pancho.web.component.SystemLogAspect;


@Controller
@RequestMapping("userController")
public class UserController {

	private  static  final Logger log = LoggerFactory.getLogger(SystemLogAspect. class);  
    
    @RequestMapping("testAOP")
    @Log(operationType="add操作:",operationName="添加用户")  
    public String testAOP(String userName,String password){        
    	log.info("============添加用户操作，用户名:{}，密码{}",userName,password);
    	return "添加用户成功！";

    }
}