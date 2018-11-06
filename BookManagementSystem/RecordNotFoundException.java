package com.scsa.jadv.test;

@SuppressWarnings("serial")
public class RecordNotFoundException extends Exception {
	public RecordNotFoundException() {}
	public RecordNotFoundException(String msg) {
		super(msg);
	}
}
