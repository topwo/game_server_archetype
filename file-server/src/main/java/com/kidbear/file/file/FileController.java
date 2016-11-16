package com.kidbear.file.file;

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

@Controller
@RequestMapping(value = "/file")
public class FileController {

	public static String uploadPwd = "kidbear123";// 上传文件时的密码
	public static String downloadPwd = "kidbear123";// 下载文件时的密码

	@RequestMapping(value = "/hotfix")
	public void hotfix(HttpServletRequest request, Model model) {
//		FileService service = FileServiceImply.getInstance();
//		List<String> files = service.listFiles();
//		Map<String, String> vers = Redis.getInstance().hgetAll(Redis.GLOBAL_DB,
//				FileServiceImply.HOT_FIX);
//		String startTime = null;
//		if (MemcachedCRUD.getInstance().exist("ServerStartTimeFile")) {
//			startTime = (String) MemcachedCRUD.getInstance().getObject(
//					"ServerStartTimeFile");
//		} else {
//			Date currentTime = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss");
//			startTime = formatter.format(currentTime);
//			MemcachedCRUD.getInstance().saveObject("ServerStartTimeFile",
//					startTime);
//		}
//		model.addAttribute("startTime", startTime);
//		model.addAttribute("verMap", vers);
//		model.addAttribute("files", files);
	}

	@RequestMapping(value = "/doUpdate")
	@ResponseBody
	public String doUpdate(HttpServletRequest request) throws Throwable {
		String base = request.getParameter("base");
		String version = request.getParameter("version");
		String channel = request.getParameter("channel");
		String zipLength = request.getParameter("zipLength");
		if (base == null || base.length() == 0 || version == null
				|| version.length() == 0 || channel == null
				|| channel.length() == 0 || zipLength == null
				|| zipLength.length() == 0) {
			return "failed";
		}
//		Redis.getInstance().hset(Redis.GLOBAL_DB, FileServiceImply.HOT_FIX,
//				channel + "#" + base, version + "#" + zipLength);
		return "success";
	}

	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public String doAdd(HttpServletRequest request) throws Throwable {
		String base = request.getParameter("base");
		String channel = request.getParameter("channel");
		if (base == null || base.length() == 0 || channel == null
				|| channel.length() == 0) {
			return "failed";
		}
//		if (!Redis.getInstance().hexist(Redis.GLOBAL_DB,
//				FileServiceImply.HOT_FIX, channel + "#" + base)) {
//			Redis.getInstance().hset(Redis.GLOBAL_DB, FileServiceImply.HOT_FIX,
//					channel + "#" + base, "1.0.0#0");
//			Redis.getInstance().zadd(Redis.GLOBAL_DB,
//					FileServiceImply.HOT_FIX_BASE + channel,
//					System.currentTimeMillis(), channel + "#" + base);
//			return "success";
//		}
		return "failed";
	}

	@RequestMapping(value = "/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest request) throws Throwable {
		String base = request.getParameter("base");
		String channel = request.getParameter("channel");
		if (base == null || base.length() == 0 || channel == null
				|| channel.length() == 0) {
			return "failed";
		}
//		Redis.getInstance().hdel(Redis.GLOBAL_DB, FileServiceImply.HOT_FIX,
//				channel + "#" + base);
//		Redis.getInstance().zrem(Redis.GLOBAL_DB,
//				FileServiceImply.HOT_FIX_BASE + channel, channel + "#" + base);
		return "success";
	}

	@RequestMapping(value = "/filedownload")
	public String fileDownload(HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		if (!pwd.equals(downloadPwd)) {
			return null;
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		try {
			String path = FileServiceImply.directory;
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

	@RequestMapping(value = "/fileupload")
	@ResponseBody
	public String fileUpload(String filename, HttpServletRequest request,
			@RequestParam("file") MultipartFile file, ModelMap model)
			throws Throwable {
		String base = request.getParameter("base");
		String version = request.getParameter("version");
		String channel = request.getParameter("channel");
		String pwd = request.getParameter("pwd");
		if (!pwd.trim().equals(uploadPwd)) {
			return "pwderr";
		}
		if (filename == null || filename.length() == 0) {
			filename = file.getOriginalFilename();
		}
		if (filename.length() == 0) {
			return "failed";
		}
		FileService service = FileServiceImply.getInstance();
		boolean flag = service.upload(file, channel, base, version);
		if (flag) {
			return "success";
		} else {
			return "fail";
		}
	}
}
