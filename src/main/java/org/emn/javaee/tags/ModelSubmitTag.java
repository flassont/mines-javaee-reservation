package org.emn.javaee.tags;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ModelSubmitTag extends SimpleTagSupport {
	private String model;

	public void setModel(String model)
	{
		this.model = model;
	}

	public void doTag() throws JspException, IOException {
		String display = model == "" ? "Valider" : "Modifier";
		JspWriter out = getJspContext().getOut();
		out.println("<div class='text-center'>"+
				"<button type='submit' class='btn btn-success inline-button'>"+display+"</button>"+
				"</div>");
	}
}