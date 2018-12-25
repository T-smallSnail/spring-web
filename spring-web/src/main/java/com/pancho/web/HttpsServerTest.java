package com.pancho.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/server")
public class HttpsServerTest {

	
	@RequestMapping(value = "/getHttps.htm",method = {RequestMethod.POST})
	@ResponseBody
	public String confirmDeal(@RequestBody String para) {
		
		System.out.println(para);
		return "111";
		
	}
}
	
