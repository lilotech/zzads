/**
 * Copyright 2011 ASTO.
 * All right reserved.
 * Created on 2011-3-31.
 */
package com.zz91.ads.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;

import com.zz91.util.auth.AuthUtils;
import com.zz91.util.auth.SessionUser;

/**
 * 控制器基类,用于封装HttpServletResponse对象用于输出JOSN
 * @author Rolyer(rolyer.live@gmail.com)
 *
 */
public class BaseController {

	/**
	 * 打印JSON字符串
	 * 
	 * @param obj
	 *            1.如果obj为Array类型,则调用JSONArray来进行转换
	 *            2.如果obj为Object类型,则调用JSONObject来进行转换
	 * @param model
	 *            将生成的JSON字符串放到该map里
	 * @throws IOException
	 *             出现IO异常时抛出
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView printJson(Object obj, Map<String, Object> out) {
		String jsonString = "";
		if (obj instanceof List) {
			jsonString = (JSONArray.fromObject(obj).toString());
		} else {
			jsonString = (JSONObject.fromObject(obj).toString());
		}
		out.put("json", jsonString);
		return new ModelAndView("json");
	}
	
	public void setSessionUser(HttpServletRequest request, SessionUser sessionUser) {
		AuthUtils.getInstance().setSessionUser(request, sessionUser, null);
	}

	public void cleanCachedSession(HttpServletRequest request) {
		AuthUtils.getInstance().clearnSessionUser(request, null);
	}
	
	public SessionUser getCachedUser(HttpServletRequest request){
		return AuthUtils.getInstance().getSessionUser(request, null);
	}
}
