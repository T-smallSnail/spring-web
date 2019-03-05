package com.pancho.web.common;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

//@ControllerAdvice 
public class ExceptionHandlerAdvice { 
	private final static Logger logger = (Logger) LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	@ExceptionHandler(value = Exception.class) 
	public ModelAndView exception(Exception exception, WebRequest request) {
		StringWriter sw = new StringWriter();    
		PrintWriter pw = new PrintWriter(sw);    
		exception.printStackTrace(pw);    
		logger.info(sw.toString());
		ModelAndView modelAndView = new ModelAndView("error/error");
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}
}
