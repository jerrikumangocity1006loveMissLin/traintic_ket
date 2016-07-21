package com.mangocity.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.mangocity.api.ITrainOrderService;
import com.mangocity.response.ResponseMessage;

@WebServlet(name="BookTicketNotifyServlet",urlPatterns={"/service/bookNotify"})
public class BookTicketNotifyServlet extends BaseServlet {
	
	Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = -775263040580167508L;
	@Autowired 
	private ITrainOrderService orderService;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("====Start BookTicketNotifyServlet====");
			announce(request, response);
			logger.info("====BookTicketNotifyServlet end ====");
		} catch (IOException e) {
			logger.info("===BookTicketNotifyServlet异常:", e);
		} catch (ServletException e) {
			logger.info("===BookTicketNotifyServlet异常:", e);
		}
	}

	private void announce(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
		ResponseMessage responseMessage = orderService.bookTicketNotify(param.toString());
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
