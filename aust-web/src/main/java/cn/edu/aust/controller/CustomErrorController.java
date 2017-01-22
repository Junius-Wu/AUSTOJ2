package cn.edu.aust.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.MessageFormat;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
class CustomErrorController {
	
	/**
	 * Display an error page, as defined in web.xml <code>custom-error</code> element.
	 */
	@RequestMapping("error")
	public String generalError(HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String exceptionMessage = getExceptionMessage(throwable, statusCode);

		Optional<String> requestUri = Optional.ofNullable(
				(String) request.getAttribute("javax.servlet.error.request_uri"));

		String message = MessageFormat.format("{0} returned for {1} with message <br> {2}",
			statusCode, requestUri.orElse("UnKnown"), exceptionMessage
		);

		model.addAttribute("errorMessage", message);
        return "error";
	}

	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			return throwable.getMessage();
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();
	}
}
