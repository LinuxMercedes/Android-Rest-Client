package org.linuxmercedes.AndroidRestClient;

/**
 * Exception for superclassing network communication errors from RestClient
 * @author nathan
 *
 */
public class NetworkException extends Exception
{

	/**
	 * Version number
	 */
	private static final long serialVersionUID = 1L;

	private String m_message;
	private Exception m_child;
	
	/**
	 * Creates a new NetworkException
	 * @param message Error message
	 * @param child The exception we are superclassing
	 */
	public NetworkException(String message, Exception child)
	{
		m_message=message;
		m_child=child;
	}
	
	/**
	 * Returns the error message
	 */
	public String getMessage(){
		return m_message;
	}
	
	/**
	 * Returns the child exception
	 * @return the exception we superclassed
	 */
	public Exception getChild(){
		return m_child;
	}
}
