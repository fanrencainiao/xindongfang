package com.xindongfang.manager.advice;

import java.io.EOFException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xindongfang.manager.ex.ServiceException;
import com.xindongfang.manager.utils.ResponseUtil;
import com.xindongfang.manager.utils.Result;



@ControllerAdvice
public class ExceptionHandlerAdvice {
	protected Log log=LogFactory.getLog(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	public void handleErrors(HttpServletRequest request,
			HttpServletResponse response, Exception e) throws Exception {
		

		int resultCode = 60001;
		String resultMsg = "接口内部异常====>";
		log.info(request.getRequestURI() + "错误：");
		if (e instanceof MissingServletRequestParameterException
				|| e instanceof BindException) {
			resultCode = 10001;
			resultMsg = "请求参数验证失败，缺少必填参数或参数错误";
		} else if (e instanceof ServiceException) {
			ServiceException ex = ((ServiceException) e);

			resultCode = null == ex.getResultCode() ? 0 : ex.getResultCode();
			resultMsg = ex.getMessage();
		} else if (e instanceof ClientAbortException) {
			resultMsg="====> ClientAbortException";
			resultCode=-1;
		}else if(e instanceof EOFException){
			log.info("====》 拦截    EOFException ");
			resultMsg = resultMsg+e.getMessage();
		}else {
			e.printStackTrace();
			resultMsg = resultMsg+e.getMessage();
		}
		log.info(resultMsg);
	
		Result map=new Result();
		map.setCode(resultCode);
		map.setMsg(resultMsg);
		String text = JSONObject.toJSONString(map);

		ResponseUtil.output(response, text);
	}

}
