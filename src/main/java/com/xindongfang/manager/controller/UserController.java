package com.xindongfang.manager.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xindongfang.manager.entity.User;
import com.xindongfang.manager.utils.Md5Util;
import com.xindongfang.manager.utils.Result;
import com.xindongfang.manager.utils.ResultCode;
import com.xindongfang.manager.filter.LoginSign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user/")
public class UserController extends AbstractController{
	
	@PostMapping(value = "login")
	public Object login(String name, String password, HttpServletRequest request) {
		if(StringUtils.isEmpty(name)||StringUtils.isEmpty(password)) {
			return Result.error("用户名或者密码错误");
		}
		User login = null;
//	
//		if (login != null) {
//			if(Md5Util.md5HexToAccid(password).equals(login.getPwd()))
//				request.getSession().setAttribute(LoginSign.LOGIN_USER_KEY, login);
//			else 
//				return Result.error("用户名或者密码错误");
//			login.setPwd("");
//			return Result.success(login);
//		}
		return Result.failure(ResultCode.USER_LOGIN_ERROR);

	}

	@PostMapping(value = "logout")
	public Object logout(HttpServletRequest request) {
		
		request.getSession().removeAttribute(LoginSign.LOGIN_USER_KEY);
	
		return Result.success();

	}

}
