package com.servinglynk.hmis.warehouse.notification.model;



import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class JSONObjectView  extends MappingJacksonJsonView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String callback = request.getParameter("callback");

		if (callback != null) {

			writeToStream(callback + "(", response);

			super.renderMergedOutputModel(model, request, response);

			writeToStream(")", response);

		} else {

			super.renderMergedOutputModel(model, request, response);

		}

	}



	private void writeToStream(String data, HttpServletResponse response) throws Exception {

		OutputStream out = response.getOutputStream();

		BufferedOutputStream bout = new BufferedOutputStream(out);

		bout.write(data.getBytes());

		bout.flush();

	}

}

