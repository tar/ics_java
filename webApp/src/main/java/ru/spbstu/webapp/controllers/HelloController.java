package ru.spbstu.webapp.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "")
public class HelloController {
	Logger _logger = Logger.getLogger(HelloController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getHelloPage() {
		return new ModelAndView("hello");
	}
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView getHelloPageByRoot() {
		return new ModelAndView("hello");
	}

	@RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
	public void getGreetings(@PathVariable("name") String name,
			HttpServletResponse response) {
		_logger.info("Name = " + name);
		StringBuilder sb = new StringBuilder("{\"greeting\": ");
		sb.append("\"");
		sb.append("Hello ");
		sb.append(name);
		sb.append("! How are you?\"}");
		try {
			response.getWriter().write(sb.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			_logger.error("IO exception occured while sending response");
		}
	}
}
