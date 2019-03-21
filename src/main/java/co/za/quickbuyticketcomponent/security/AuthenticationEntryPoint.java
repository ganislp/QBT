package co.za.quickbuyticketcomponent.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import co.za.quickbuyticketcomponent.modals.RestResponse;
import co.za.quickbuyticketcomponent.utils.Messages;
import co.za.quickbuyticketcomponent.utils.ResponseBuilderAgent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		ResponseBuilderAgent responseBuilder = new ResponseBuilderAgent();
		//ConvertrAgent convertr = new ConvertrAgent();
		
		response.addHeader("WWW-Authenticate", "Basic realm - " + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		RestResponse restResponse = responseBuilder.createFailureResponse(authException, request.getRequestURI(), Messages.UNAUTHORIZED_ERROR_CODE);
		try 
		{
			//writer.println(convertr.ObjectToJson(restResponse));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("MightyJava");
		super.afterPropertiesSet();
	}
}
