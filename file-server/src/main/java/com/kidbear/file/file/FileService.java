package com.kidbear.file.file;

import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public List<String> listFiles();

	public boolean upload(MultipartFile file, String channel,
			String baseVersion, String version);

	public void download(String channel, String baseVersion,
			ChannelHandlerContext ctx) throws IOException;

}
