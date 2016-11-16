package com.kidbear.file.file;

import com.kidbear.file.util.redis.Redis;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The type File service imply.
 */
public class FileServiceImply implements FileService {
    /**
     * The Logger.
     */
    public Logger logger = LoggerFactory.getLogger(FileServiceImply.class);
    /**
     * The download directory in server.
     */
    public static String directory = "/data/hotfix/";
    /**
     * The constant HOT_FIX.
     */
    public static final String HOT_FIX = "hotfix";
    /**
     * The constant HOT_FIX_BASE.
     */
    public static final String HOT_FIX_BASE = "hotfix_base_channel_";
    /**
     * The constant fileName.
     */
    public static String fileName = "gamename.zip";

    /**
     * The constant inst.
     */
    public static FileServiceImply inst;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static FileServiceImply getInstance() {
        if (inst == null) {
            inst = new FileServiceImply();
        }
        return inst;
    }

    @Override
    public List<String> listFiles() {
        File dir = new File(directory);
        List<String> fileList = new ArrayList<String>();
        File[] files = dir.listFiles();
        for (File file : files) {
            fileList.add(file.getName());
        }
        return fileList;
    }

    @Override
    public boolean upload(MultipartFile file, String channel,
                          String baseVersion, String version) {
        // String name = baseVersion + "." + version + "." + fileName;
        String name = channel + "_" + baseVersion + "_" + version + "_"
                + fileName;
        String path = directory + name;
        File localFile = new File(path);
        if (localFile.exists()) {
            localFile.delete();
        }
        try {
            file.transferTo(localFile);
        } catch (IllegalStateException e) {
            logger.error("file upload", e);
            return false;
        } catch (IOException e) {
            logger.error("file upload", e);
            return false;
        }
        logger.info(path + "上传成功");
        // 存储版本信息到Redis
//		if (!Redis.getInstance().hexist(Redis.GLOBAL_DB, HOT_FIX,
//				channel + "#" + baseVersion)) {
//			Redis.getInstance().zadd(Redis.GLOBAL_DB, HOT_FIX_BASE + channel,
//					System.currentTimeMillis(), channel + "#" + baseVersion);
//		}
//		Redis.getInstance()
//				.hset(Redis.GLOBAL_DB, HOT_FIX, channel + "#" + baseVersion,
//						version + "#" + localFile.length());
        return true;
    }

    @Override
    public void download(String channel, String baseVersion,
                         ChannelHandlerContext ctx) throws IOException {
        String val = Redis.getInstance().hget(Redis.GLOBAL_DB, HOT_FIX,
                channel + "#" + baseVersion);
        if (val == null) {
            logger.error("version not found");
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }
        final String path = directory + channel + "_" + baseVersion + "_"
                + val.split("#")[0] + "_" + fileName;
        // 文件解析逻辑
        File file = new File(path);
        if (file.isHidden() || !file.exists()) {
            logger.error("file not found");
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }
        if (file.isDirectory()) {
            logger.error("file is directory");
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }
        if (!file.isFile()) {
            // 服务端收到了请求，但是拒绝服务
            logger.error("not file");
            sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }
        download(fileName, ctx, file);
    }

    private void download(final String name, ChannelHandlerContext ctx,
                          File file) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            // 以只读的方式打开文件
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }
        long fileLength = randomAccessFile.length();
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK);
        response.headers().set("Content-Length", String.valueOf(fileLength));
        // 设置content-type，这里是下载一个文件
        MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
        response.headers().set("Content-Type",
                mimetypesFileTypeMap.getContentType(file.getPath()));
        response.headers().set("Content-Disposition",
                "attachment;fileName=" + name);
        // 丢出一个响应
        ctx.write(response);
        // 通过netty的ChunkedFile对象直接将文件写入到发送缓冲区中(没有flush代表还未发送出去)
        ChannelFuture sendFileFuture;
        // 下载限制带宽4096K
        sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0,
                fileLength, 4096), ctx.newProgressivePromise());
        sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(
                    ChannelProgressiveFuture channelProgressiveFuture,
                    long progress, long total) throws Exception {
                if (total > 0) {
                    // 记录总的下载程度
                    logger.info("下载进度: " + progress + "/" + total);
                }
            }

            @Override
            public void operationComplete(
                    ChannelProgressiveFuture channelProgressiveFuture)
                    throws Exception {
                logger.info("文件 {} 下载完成", name);
            }
        });
        // 如果是Chunked编码，需要发送一个编码结束的空消息体
        ChannelFuture lastContentFuture = ctx
                .writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        // 如果非keep-alive则发送最后一个包消息后，服务端需要主动关闭连接
        lastContentFuture.addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 处理错误事件
     *
     * @param ctx
     * @param status
     */
    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        // 定义FullHttpResponse响应消息包
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer("Failure: "
                + status.toString() + "\r\n", CharsetUtil.UTF_8));
        response.headers().set("Content-Type", "text/html; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private static final Pattern ALLOWED_FILE_NAME = Pattern
            .compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

    /**
     * 罗列出文件中的文件列表信息
     *
     * @param ctx the ctx
     * @param dir the dir
     */
    public static void sendListing(ChannelHandlerContext ctx, File dir) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set("Content-Type", "text/html;charset=UTF-8");

        // 由于响应打算直接输出一个html流给前台界面直接显示，因此这里直接返回
        StringBuilder buf = new StringBuilder();
        String dirPath = dir.getPath();
        buf.append("<!DOCTYPE html>\r\n");
        buf.append("<html><head><title>");
        // 标题栏显示当前扫描路径
        buf.append(dirPath);
        buf.append(" 目录: ");
        buf.append("</title></head><body>\r\n");
        buf.append("<h3>");
        buf.append(dirPath).append(" 目录: ");
        buf.append("</h3>\r\n");
        buf.append("<ul>");
        buf.append("<li>链接: <a href=\"../\">..<a/></li>\r\n");
        for (File f : dir.listFiles()) {
            if (f.isHidden() || !f.canRead()) {
                continue;
            }
            String name = f.getName();
            if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                continue;
            }
            buf.append("<li>链接: <a href=\"");
            buf.append(name);
            buf.append("\">");
            buf.append(name);
            buf.append("</a></li>\r\n");
        }
        buf.append("</ul></body></html>\r\n");
        // 将解析后的消息体保存到缓存区中
        ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 如果请求uri结尾非/结束则代表重定向至该目录下
     *
     * @param ctx    the ctx
     * @param newUri the new uri
     */
    public static void sendRedirect(ChannelHandlerContext ctx, String newUri) {
        // 要求重定向
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.MOVED_PERMANENTLY);
        response.headers().set("Location", newUri);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
