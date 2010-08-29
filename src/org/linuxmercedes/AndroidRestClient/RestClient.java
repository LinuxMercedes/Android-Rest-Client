package org.linuxmercedes.AndroidRestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Basic rest client for Android.
 * @author nathan
 *
 */
public class RestClient
{

	/**
	 * Makes a get request to the url
	 * @param url URL for request
	 * @return result of request
	 * @throws NetworkException Exception for network communicaton errors
	 * @throws RequestException Exception for HTTP errors.
	 */
	public static String get(String url) throws NetworkException, RequestException
	{
		HttpGet get = new HttpGet(url);
		return request(get);
	}

	/**
	 * Makes a put request to the url
	 * @param url URL for request
	 * @return result of request
	 * @throws NetworkException Exception for network communicaton errors
	 * @throws RequestException Exception for HTTP errors.
	 */
	public static String put(String url) throws NetworkException, RequestException
	{
		HttpPut put = new HttpPut(url);
		return request(put);
	}

	/**
	 * Makes a post request to the url
	 * @param url URL for request
	 * @return result of request
	 * @throws NetworkException Exception for network communicaton errors
	 * @throws RequestException Exception for HTTP errors.
	 */
	public static String post(String url) throws NetworkException, RequestException
	{
		HttpPost post = new HttpPost(url);
		return request(post);
	}

	private static String request(HttpRequestBase rq) throws NetworkException, RequestException
	{
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		String result = null;

		try
		{
			response = client.execute(rq);
		}
		catch (Exception e)
		{
			throw new NetworkException("Error executing request", e);
		}

		if (response.getStatusLine().getStatusCode() != 200)
		{
			throw new RequestException(response.getStatusLine());
		}

		try
		{
			result = stream2string(response.getEntity().getContent());
		}
		catch (Exception e)
		{
			throw new NetworkException("Error parsing response", e);
		}

		return result;
	}

	private static String stream2string(InputStream stream) throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(stream));
		String result = null;

		while ((result += buf.readLine()) != null)
			;

		return result;
	}
}
