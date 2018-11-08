package com.scsa.jadv.test;

@SuppressWarnings("serial")
public class DuplicateException extends Exception {
	public DuplicateException() {}
	public DuplicateException(String msg) {
		super(msg);
	}
}
