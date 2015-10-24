package org.emn.javaee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.emn.javeee.services.BusinessException;

public interface ServletAction {

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
