package com.mangocity.servlet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.mangocity.api.ITrainTicketService;
import com.mangocity.response.ResponseMessage;

@WebServlet(name="ChangeTicketNotifyServlet",urlPatterns={"/service/changeTicketNotify"})
public class ChangeTicketNotifyServlet extends BaseServlet {

	Logger logger = Logger.getLogger(this.getClass());
	private static final long serialVersionUID = -3362620273816117620L;
	@Autowired
	private ITrainTicketService trainTicketService;
	
	
	public void service(HttpServletRequest request,HttpServletResponse response){
		logger.info("====改签回调接口ChangeTicketNotifyServlet开始====");
		try {
			resultHandler(request,response);
		} catch (Exception e) {
			logger.info("==改签回调接口异常ChangeTicketNotifyServlet==", e);
		}
		logger.info("====改签回调接口ChangeTicketNotifyServlet结束====");
	}
	
	private void resultHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InputStream input = request.getInputStream();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		StringBuffer param = new StringBuffer();
		String read = null;
		while((read = buffer.readLine()) != null && read.length() > 0){
			param.append(read);
		}
		if(null != buffer){
			buffer.close();
		}
		if(null != input){
			input.close();
		}
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");
		ResponseMessage responseMessage = trainTicketService.checkTrainChangeNotify(param.toString());
		PrintWriter pw = response.getWriter();
		Object obj = null;
		if(null != responseMessage){
			Map<String,String> result = new HashMap<String,String>();
			result.put("status", responseMessage.getCode());
			result.put("msg", responseMessage.getMsg());
			obj = JSONObject.toJSON(result);
		}
		pw.write(obj != null ? obj.toString() : "");
		pw.flush();
		if(null != pw){
			pw.close();
		}
	}

}
