package cc.mobireach.api.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cc.mobireach.api.rest.beans.Message;

@RestController
public class IndexController implements org.springframework.boot.autoconfigure.web.ErrorController {

	private static final String ERROR_PATH = "/error";
	private static final String ERROR_MSG = "Internal Server Error";
	private static final String ACCESS_DENIED_PATH = "/access_denied";
	private static final String ACCESS_DENIED_MSG = "Access Denied";

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	@ResponseBody
	@RequestMapping(ERROR_PATH)
	public Message sendErrorMessage() {

		return new Message(ERROR_MSG);
	}

	@ResponseBody
	@RequestMapping(ACCESS_DENIED_PATH)
	public Message sendAccessDeniedMessage() {

		return new Message(ACCESS_DENIED_MSG);
	}
}
