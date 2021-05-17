package com.jwt.SpringSecurityWithJwtAndOuth.exception;

/**
 * @author ${Sanjay Vishwakarma}
 *
 */
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	  public MyException(MyException e){
	        super(e);
	    }

		public MyException(String message) {
			super(message);
		}

}
