package br.com.ttrans.samapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.ttrans.samapp.model.DocumentType;
import br.com.ttrans.samapp.service.DocumentTypeService;

@Controller
@RequestMapping("/document")
public class DocumentController {

	/**
	 * Size of a byte buffer to read/write file  
	 */
	private static final int BUFFER_SIZE = 4096;

	/**
	 * Path of the file to be downloaded, relative to application's directory
	 */
	private String filePath = "document/Passagem_07_10.pdf";

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadfile(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		
		// get absolute path of the application
		String appPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println("appPath = " + appPath);
		
		
		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);
		
		// get MIME type of the file
		String mimeType = request.getSession().getServletContext().getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);
		
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
		downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		
		// get output stream of the response
		OutputStream outStream = response.getOutputStream();
		
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		
		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		
		inputStream.close();
		outStream.close();
		
	}

	@RequestMapping(value = "/upload/uploadfile", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public Map<String, Object> uploadFile(HttpServletRequest request,
			@RequestParam(value = "image", required = false) MultipartFile image) {

		File file = new File(request.getSession().getServletContext()
				.getRealPath("/")
				+ "document/" + image.getOriginalFilename());

		try {

			FileUtils.writeByteArrayToFile(file, image.getBytes());
			System.out
					.println("Go to the location:  "
							+ file.toString()
							+ " on your computer and verify that the image has been stored.");

		} catch (IOException e) {

		}

		Map<String, Object> result = new HashMap<String, Object>();

		return result;
	}

}
