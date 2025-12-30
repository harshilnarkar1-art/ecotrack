//package com.clean.ecotrack.exceptions;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import jakarta.servlet.http.HttpServletRequest;
//
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(exception = NotFoundException.class)
//	public ResponseEntity<APIError> notFoundExceptionHandler(NotFoundException ex, HttpServletRequest request){
//		APIError apiError = new APIError();
//		apiError.setError(ex.getClass().getSimpleName());
//		apiError.setErroMessage(ex.getMessage());
//		apiError.setPath(request.getRequestURI());
//		
//		return new ResponseEntity<APIError>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	@ExceptionHandler(exception = RuntimeException.class)
//	public ResponseEntity<APIError> notFoundExceptionHandler(RuntimeException ex, HttpServletRequest request){
//		APIError apiError = new APIError();
//		apiError.setError(ex.getClass().getSimpleName());
//		apiError.setErroMessage(ex.getMessage());
//		apiError.setPath(request.getRequestURI());
//		
//		return new ResponseEntity<APIError>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//}
