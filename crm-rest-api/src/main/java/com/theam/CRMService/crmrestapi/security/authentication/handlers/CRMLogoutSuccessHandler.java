package com.theam.CRMService.crmrestapi.security.authentication.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class CRMLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		 	response.setStatus(HttpStatus.OK.value());
		    response.setContentType(MediaType.TEXT_PLAIN_VALUE);
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("Logout Successful");

	}

}
