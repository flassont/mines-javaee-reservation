package org.emn.javaee.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Tag to simplify the input in the templates
 */
public class InputTag extends SimpleTagSupport {
	
	private String placeholder;
	private String value;
	private String name;
	private String display;
	private String type;
	private String additionalHtml;
	private String required;

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAdditionalHtml(String additionalHtml) {
		this.additionalHtml = additionalHtml;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public void doTag() throws JspException, IOException {
		String aHtml = additionalHtml == null ? "" : additionalHtml;
		String r = required == null ? "required" : "";
		String t = type == null ? "text" : type;
		JspWriter out = getJspContext().getOut();
		if (t.equals("checkbox")) {
			out.println("<div class=\"checkbox\">" + "<label for=\"" + name + "\"><input type=\"" + t + "\"" + "id=\""
					+ name + "\" name=\"" + name + "\"" + "placeholder=\"" + placeholder + "\" " + r + " " + aHtml
					+ " >" + display + "</label></div>");
		} else {
			out.println(
					"<div class=\"form-group\">" + "<label for=\"" + name + "\">" + display + "</label> <input type=\""
							+ t + "\"" + "class=\"form-control\" value=\"" + value + "\" id=\"" + name + "\" name=\""
							+ name + "\"" + "placeholder=\"" + placeholder + "\" " + r + " " + aHtml + " >" + "</div>");
		}
	}
}