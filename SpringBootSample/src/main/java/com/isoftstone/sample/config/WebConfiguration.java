package com.isoftstone.sample.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.JSON;
import com.isoftstone.sample.common.Result;
import com.isoftstone.sample.common.ResultCode;
import com.isoftstone.sample.common.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebConfiguration extends WebMvcConfigurationSupport {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedHeaders("*")
				.allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600);
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new HandlerExceptionResolver() {
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception e) {
				Result result = new Result();
				if (e instanceof ServiceException) {
					result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
					log.error("ServiceException", e);
				} else if (e instanceof NoHandlerFoundException) {
					result.setCode(ResultCode.NOT_FOUND)
							.setMessage("Interface [" + request.getRequestURI() + "] not exist.");
				} else if (e instanceof ServletException) {
					result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
					log.error("ServletException", e);
				} else {
					result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("Interface [" + request.getRequestURI()
							+ "] internal error, please contact with administrator.");
					String message;
					if (handler instanceof HandlerMethod) {
						HandlerMethod handlerMethod = (HandlerMethod) handler;
						message = String.format("Interface [%s] error occured, method ：%s.%s，error msg ：%s",
								request.getRequestURI(), handlerMethod.getBean().getClass().getName(),
								handlerMethod.getMethod().getName(), e.getMessage());
					} else {
						message = e.getMessage();
					}
					log.error(message, e);
				}
				responseResult(response, result);
				return new ModelAndView();
			}

		});
	}

	private void responseResult(HttpServletResponse response, Result result) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setStatus(200);
		try {
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
	}

}
