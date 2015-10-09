package br.com.ttrans.samapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import br.com.ttrans.samapp.model.Document;
import br.com.ttrans.samapp.model.EquipmentModel;
import br.com.ttrans.samapp.service.EquipmentModelService;

@Controller
@RequestMapping("/document")
public class DocumentController {

	/**
	 * Size of a byte buffer to read/write file  
	 */
	private static final int BUFFER_SIZE = 4096;
	

	private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	private EquipmentModelService modelService;

	/**
	 * Path of the file to be downloaded, relative to application's directory
	 */
	
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public void downloadfile(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam(value = "url", required = false) String url) throws IOException {
		
		// get absolute path of the application
		String appPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println("appPath = " + appPath);
		
		
		
		try{
			// construct the complete absolute path of the file
			String fullPath = appPath + url;
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
		}catch(FileNotFoundException e){
			logger.error("Arquivo: " + appPath + " não localizado.");
		}
		
	}

	@RequestMapping(value = "/upload", headers = "content-type=*/*", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request,
			Authentication authentication,
			@RequestParam(value = "id"	 , required = false) int id,
			@RequestParam(value = "desc" , required = false) String desc,
			@RequestParam(value = "file" , required = false) MultipartFile uploadFile) {

		String filePath = "document/model/" + id + "/" + uploadFile.getOriginalFilename();
		
		File file = new File(request.getSession().getServletContext().getRealPath("/")
				+ filePath);
		
		Map<String, Object> result = new HashMap<String, Object>();

		try {

			FileUtils.writeByteArrayToFile(file, uploadFile.getBytes());
			System.out
					.println("Go to the location:  "
							+ file.toString()
							+ " on your computer and verify that the image has been stored.");
			
			EquipmentModel model = modelService.get(id);
			
			/**
			 * 'Seta' novo documento
			 */
			model.getDocuments().add(new Document(desc,
					filePath,
					authentication.getName(),
					authentication.getName()));
			
			/**
			 * Atualiza model
			 */
			modelService.edit(model, authentication);
			
			result.put("success", true);

		} catch (IOException e) {
			
			
			result.put("success", false);
			
		}

		

		return result;
	}

}
