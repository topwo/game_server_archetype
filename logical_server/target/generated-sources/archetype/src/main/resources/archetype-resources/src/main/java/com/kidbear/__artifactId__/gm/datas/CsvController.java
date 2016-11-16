#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.gm.datas;

import com.kidbear.${artifactId}.core.GameInit;
import com.kidbear.${artifactId}.core.Router;
import com.kidbear.${artifactId}.util.csv.CsvDataLoader;
import com.kidbear.${artifactId}.util.csv.TempletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Set;

@Controller
@RequestMapping(value = "/csv")
public class CsvController {
	public Logger logger = LoggerFactory.getLogger(CsvController.class);

	@RequestMapping(value = "/csvlist")
	public void csvList(Model model) {
		Set<String> csvNames = TempletService.templetMap.keySet();
		model.addAttribute("csvNames", csvNames);
	}

	@RequestMapping("/csvdownload")
	public String csvDownload(HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = request.getParameter("name");
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		try {
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath()
					+ "csv";// 这个download目录建立在classes下
			InputStream inputStream = new FileInputStream(new File(path
					+ File.separator + fileName));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回值要注意，要不然就出现下面这句错误！
		// java+getOutputStream() has already been called for this response
		return null;
	}

	@RequestMapping(value = "/csvupload")
	@ResponseBody
	public String csvUpload(String fileName, HttpServletRequest request,
			@RequestParam("file") MultipartFile file, ModelMap model) {
		if (fileName == null || fileName.length() == 0) {
			fileName = file.getOriginalFilename();
		}
		if (fileName.length() == 0) {
			return "failed";
		}
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath()
				+ "csv" + File.separator + fileName;
		File localFile = new File(path);
		if (localFile.exists()) {
			localFile.delete();
		}
		try {
			file.transferTo(localFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "failed";
		} catch (IOException e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@RequestMapping(value = "/csvupdate")
	@ResponseBody
	public String csvUpdate() {
		try {
			// 加载CSV数据
			logger.info("重新加载CSV数据配置");
			new CsvDataLoader(GameInit.templatePacket, GameInit.dataConfig)
					.load();
			Router.getInstance().initCsvData();
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
}
