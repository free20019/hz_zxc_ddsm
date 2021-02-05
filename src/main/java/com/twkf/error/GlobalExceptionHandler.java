package com.twkf.error;

import com.twkf.entity.BackMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 *
 * 全局异常捕获
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public BackMessage exceptionHandler(RuntimeException e) {
		log.info("异常信息："+e);
		return new BackMessage(0,"服务器运行异常，请稍后重试",e);
	}


	//空指针异常
	@ExceptionHandler(NullPointerException.class)
	public BackMessage nullPointerExceptionHandler(NullPointerException ex) {
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//类型转换异常
	@ExceptionHandler(ClassCastException.class)
	public BackMessage classCastExceptionHandler(ClassCastException ex) {
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//IO异常
	@ExceptionHandler(IOException.class)
	public BackMessage iOExceptionHandler(IOException ex) {
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//未知方法异常
	@ExceptionHandler(NoSuchMethodException.class)
	public BackMessage noSuchMethodExceptionHandler(NoSuchMethodException ex) {
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public BackMessage indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//400错误
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public BackMessage requestNotReadable(HttpMessageNotReadableException ex) {
		System.out.println("400..requestNotReadable");
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//400错误
	@ExceptionHandler({TypeMismatchException.class})
	public BackMessage requestTypeMismatch(TypeMismatchException ex) {
		System.out.println("400..TypeMismatchException");
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//400错误
	@ExceptionHandler({MissingServletRequestParameterException.class})
	public BackMessage requestMissingServletRequest(MissingServletRequestParameterException ex) {
		System.out.println("400..MissingServletRequest");
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//405错误
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public BackMessage request405(HttpRequestMethodNotSupportedException ex) {
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//406错误
	@ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
	public BackMessage request406(HttpMediaTypeNotAcceptableException ex) {
		System.out.println("406...");
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//500错误
	@ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
	public BackMessage server500(RuntimeException ex) {
		System.out.println("500...");
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//栈溢出
	@ExceptionHandler({StackOverflowError.class})
	public BackMessage requestStackOverflow(StackOverflowError ex) {
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}

	//其他错误
	@ExceptionHandler({Exception.class})
	public BackMessage exception(Exception ex) {
		log.info("异常信息："+ex);
		return new BackMessage(0,"服务器运行异常，请稍后重试",ex);
	}
}
