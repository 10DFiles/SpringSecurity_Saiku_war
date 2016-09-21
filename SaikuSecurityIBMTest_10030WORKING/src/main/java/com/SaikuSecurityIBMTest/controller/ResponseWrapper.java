package com.SaikuSecurityIBMTest.controller;

public class ResponseWrapper {

	private String responseError;
	private String responseDenied;

	private String responseSuccess;
	private Object result;
	
	public String getResponseDenied() {
		return responseDenied;
	}
	public void setResponseDenied(String responseDenied) {
		this.responseDenied = responseDenied;
	}
	
	
	public String getResponseError() {
		return responseError;
	}
	public void setResponseError(String responseError) {
		this.responseError = responseError;
	}
	public String getResponseSuccess() {
		return responseSuccess;
	}
	public void setResponseSuccess(String responseSuccess) {
		this.responseSuccess = responseSuccess;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
	
	
}
