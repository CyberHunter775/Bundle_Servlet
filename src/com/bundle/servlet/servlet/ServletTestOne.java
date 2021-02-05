package com.bundle.servlet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.osgi.framework.BundleContext;

import com.bundle.servlet.bean.BaseServlet;
import com.bundle.servlet.utils.PubUtils;

public class ServletTestOne extends BaseServlet {

	private BundleContext context;

	public ServletTestOne(BundleContext context) {
		this.context = context;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String requestBody = PubUtils.handlerRequest(req);

		System.out.println("请求body内容为：" + requestBody);
		
		// 封装返回
		JSONObject jt = new JSONObject();
		jt.put("requestbody", requestBody);
		String resultStr = success(jt);
		
		// 返回
	    byte[] bytes = resultStr.getBytes("UTF-8");
	    ServletOutputStream os = resp.getOutputStream();
	    os.write(bytes);
	    os.flush();
	    os.close();
	}

}
