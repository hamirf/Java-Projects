package id.bootcamp.java310.mipro.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

//Mengemas Response API dari CRUD
//Generic Class
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
	
	private int code;
	private String message;
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
