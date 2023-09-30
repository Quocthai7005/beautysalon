package com.majestic.nails.configuration;

import javax.persistence.EntityNotFoundException;

import com.majestic.nails.common.response.ResponseBody;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handler for general exception.
	 *
	 * @param request
	 * @param ex
	 */
	@ExceptionHandler({ Exception.class })
	ResponseEntity<Object> handleInternalError(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status;
		if (ex instanceof HttpRequestMethodNotSupportedException) {
			status = HttpStatus.METHOD_NOT_ALLOWED;
		} else if (ex instanceof HttpMediaTypeNotSupportedException) {
			status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
		} else if (ex instanceof HttpMediaTypeNotAcceptableException) {
			status = HttpStatus.NOT_ACCEPTABLE;
		} else if (ex instanceof MissingServletRequestParameterException || ex instanceof ServletRequestBindingException
				|| ex instanceof TypeMismatchException || ex instanceof HttpMessageNotReadableException
				|| ex instanceof MethodArgumentNotValidException || ex instanceof MissingServletRequestPartException
				|| ex instanceof BindException || ex instanceof EmptyResultDataAccessException
				|| ex instanceof EntityNotFoundException) {
			status = HttpStatus.BAD_REQUEST;
		} else if (ex instanceof NoHandlerFoundException) {
			status = HttpStatus.NOT_FOUND;
		} else if (ex instanceof AsyncRequestTimeoutException) {
			status = HttpStatus.SERVICE_UNAVAILABLE;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		ResponseBody<Object> body = new ResponseBody<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return handleExceptionInternal(ex, body, headers, status, request);
	}

}
