/**
 * 
 */
package com.holo.common.exception;

/**
 * @author Holo
 *
 */
public class HoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public HoException() {
		super();
	}
	
	public HoException(String message){
		super(message);
	}
}
