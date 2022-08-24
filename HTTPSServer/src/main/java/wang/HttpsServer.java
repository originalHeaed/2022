package wang;

/*********************************************************
 * 文件名称：HttpsServer.java
 * 软件版权: 恒生电子股份有限公司
 * 模块名称：wang
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/22 10:37
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/

import javax.net.ssl.SSLSocket;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Https服务器
 * 证书放置在 resources 目录下
 */
public class HttpsServer extends SSLServer {

    @Override
    protected String certificatePassword() {
        //证书密码
        return "123456";
    }

    /**
     * 进行双向认证时客户端证书密码
     * @return
     */
    @Override
    protected String clientCertificatePassword() {
        return "123456";
    }

    /**
     * 服务端证书
     * @return
     */
    @Override
    protected InputStream certificateStream() {
        //加载证书
        return getClass().getClassLoader().getResourceAsStream("server.jks");
    }

    /**
     * 加载客户端 CA 认证证书
     * @return
     */
    @Override
    protected InputStream clientCertificateStream() {
        return getClass().getClassLoader().getResourceAsStream("ca.jks");
    }

    @Override
    public void handler(SSLSocket socket) throws Exception {
        byte[] bytes = new byte[1024];
        //获取输入流
        InputStream inputStream = socket.getInputStream();
        //读取1024个字节
        int read = inputStream.read(bytes);
        //如果没有读取到任何数据则结束方法
        if (read == -1) return;
        //打印HTTP请求头
        System.out.println(new String(bytes, 0, read));
        //获取输出流
        OutputStream outputStream = socket.getOutputStream();
        //输入相应信息
        outputStream.write(responseContext().response());
        //关闭输入流
        inputStream.close();
        //关闭输出流
        outputStream.close();
        //关闭套接字
        closeSocket(socket);
    }
}
