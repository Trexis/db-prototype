package com.db.dbx.utilities;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.db.dbx.enums.StatusCode;

public class Utils {

	/*
	 *  RESPONSE FUNCTIONS
	 */
	
	//should we throw a exception here maybe?
	public static String convertObjectToJSON(Object object){
		ObjectMapper objectmapper = new ObjectMapper();
		String jsonstring = "{}";
		try {
			jsonstring = objectmapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonstring;
	}
	
	public static String wrapResponseWithDefaultJSON(StatusCode statusCode, String outputJson){
		String response = "{ \"status\": \"" + statusCode.toString().toLowerCase() + "\", ";
		response += "\"data\": " + outputJson;
		response += "}";
		return response;
	}
	
	public static void printJSONToResponse(HttpServletResponse response, String jsonContent) throws IOException{
		response.setContentType("application/json");
		response.getOutputStream().print(jsonContent);
	}

	public static void printHTMLToResponse(HttpServletResponse response, String htmlContent) throws IOException{
		response.setContentType("text/html");
		response.getOutputStream().print(htmlContent);
	}
	
	/*
	 * URL METHODS
	 */
	//http://bank1.db.com:8080/dbx/sitea/
	//getSchema: http
	//getServerName: bank1.db.com
	//getRequestURI: /dbx/sitea/
	//getServerPort:  8080
	//getContextPath: /dbx
	//getServletPath: /sitea/
	
	public static String getAppURLFromRequest(HttpServletRequest request){
		return request.getServerName();
	}
	public static String getLinkURLFromRequest(HttpServletRequest request){
		return request.getServletPath();
	}

}
