package com.bundle.servlet.utils;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class PubUtils {

	// 处理 post 请求中的body内容
	// 使用ByteArrayOutputStream 字节码转成U8字符串，否则会出现中文乱码问题 
	public static String handlerRequest(HttpServletRequest request) {
		ServletInputStream is = null;
		ByteArrayOutputStream os = null;
		try {
			is = request.getInputStream();
			os = new ByteArrayOutputStream();
			byte[] bytes = new byte[131072];
			int a = -1;
			while ((a = is.read(bytes)) != -1)
				os.write(bytes, 0, a);
			byte[] byteArray = os.toByteArray();
			os.close();
			is.close();
			return new String(byteArray, "UTF-8");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return "";
		} finally {
			if (os != null)
				os = null;
			if (is != null)
				is = null;
		}
	}

}
