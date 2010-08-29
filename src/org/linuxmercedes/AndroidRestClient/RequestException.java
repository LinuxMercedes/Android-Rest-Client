package org.linuxmercedes.AndroidRestClient;

import org.apache.http.StatusLine;

/**
 * Exception for storing HTTP errors (e.g. 404, 500)
 * @author nathan
 *
 */
public class RequestException extends Exception
{

	/**
	 * Version number
	 */
	private static final long serialVersionUID = 1L;
	
	private int m_errorCode;
	private String m_message;
	
	/**
	 * Creates a new RequestException
	 * @param code the HTTP error code
	 * @param message the associated error message
	 */
	public RequestException(int code, String message)
	{
		m_message = message;
		m_errorCode = code;
	}
	
	/**
	 * Creates a new RequestException from a StatusLine
	 * @param line the StatusLine to create the exception from
	 */
	public RequestException(StatusLine line)
	{
		m_message = line.getReasonPhrase();
		m_errorCode = line.getStatusCode();
	}
	
	/**
	 * @return HTTP error code
	 */
	public int getErrorCode()
	{
		return m_errorCode;
	}
	
	/**
	 * @return the error message
	 */
	public String getMessage()
	{
		return m_message;
	}

}
