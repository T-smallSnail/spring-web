package com.pancho.web.common;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/*import com.bmcc.vgop.common.consts.DataConst;
import com.bmcc.vgop.data.init.InitDao;
import com.bmcc.vgop.data.init.po.TVgopAndMarketConst;
import com.bmcc.vgop.data.init.po.TVgopAndMarketThirdpart;
import com.bmcc.vgop.data.model.TVgopAndMarketOtherProInfo;*/

import ch.qos.logback.classic.Logger;

/**
 * 
 * @author sw
 */
@WebServlet(name = "InitConstsServlet", urlPatterns = { "/init" }, loadOnStartup = 20)
@Service

public class InitConstsServlet extends HttpServlet {
	private final static Logger logger = (Logger) LoggerFactory.getLogger(InitConstsServlet.class);
/*
	InitDao initDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String t = req.getParameter("t");
		if ("reinit".equals(t)) {
			this.initEncrInfo();
		}
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {

		if (initDao == null) {
			WebApplicationContext webApplicationContext = (WebApplicationContext) servletConfig.getServletContext().getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
			initDao = webApplicationContext.getBean(InitDao.class);
		}
		this.initEncrInfo();
		this.initOtherProInfo();
		this.initThirdPartInfo();//初始化第三方产品信息
		
	}

	*//**
	 * 初始化第三方产品信息
	 *//*
	private void initThirdPartInfo() {
		logger.info("初始化第三方产品信息 start");
		DataConst.DATA_SKU_INFO.clear();
		DataConst.DATA_THIRDPART_ID_INFO.clear();
		
		List<TVgopAndMarketThirdpart> thirdPartInfo = initDao.getThirdPartInfo();
		for(TVgopAndMarketThirdpart obj:thirdPartInfo){
			DataConst.DATA_THIRDPART_ID_INFO.put(obj.getServType(), obj.getThirdPartId());
			if(null!=obj.getThirdPartSku()){
				DataConst.DATA_SKU_INFO.put(obj.getServType(), obj.getThirdPartSku());
			}	
		}
		
		logger.info("初始化第三方产品信息 end ");
		
	}

	*//**
	 * 初始化常量表
	 *//*
	private void initEncrInfo() {
		logger.info("初始化内存数据 start");
		DataConst.DATA_MAP.clear();
		 List<TVgopAndMarketConst> list = initDao.getConsts();
		if (list != null && list.size() > 0) {
			for(TVgopAndMarketConst obj:list){
				DataConst.DATA_MAP.put(obj.getConstKey(), obj.getConstValue());
			}
		}
		logger.info("初始化内存数据 end ");
	}
	
	*//**
	 * 初始化第三方业务订购人存储关系表数据
	 *//*
	private void initOtherProInfo() {
		logger.info("初始化第三方业务订购人存储关系表数据 start");
		DataConst.DATA_OTHER_PRO_INFO.clear();
		 List<TVgopAndMarketOtherProInfo> listp = initDao.getOtherPreInfo();
		if (listp != null && listp.size() > 0) {
			for(TVgopAndMarketOtherProInfo obj:listp){
				DataConst.DATA_OTHER_PRO_INFO.put(obj.getServType(), obj.getTName());
			}
		}
		logger.info("初始化第三方业务订购人存储关系表数据 end ");
	}
	*/
	

}
