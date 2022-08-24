package wang;

/*********************************************************
 * 文件名称：SSLServer.java
 * 软件版权: 恒生电子股份有限公司
 * 模块名称：wang
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/22 10:37
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * SSL服务器抽象类继承于抽象服务器负责处理客户端请求和响应<br/>
 * 定义获取证书密码证书流抽象方法
 */
public abstract class SSLServer extends Server<SSLSocket> {

    /**
     * 获取证书密码
     *
     * @return 证书密码
     */
    protected abstract String certificatePassword();

    /**
     * 客户端证书密码
     * @return
     */
    protected abstract String clientCertificatePassword();


    /**
     * 获取证书流<br/>
     * 可以通过读取文件或者网络等
     *
     * @return 证书
     */
    protected abstract InputStream certificateStream();

    /**
     * 客户端 CA 证书
     * @return
     */
    protected abstract InputStream clientCertificateStream();

    /**
     * SSL服务器端口
     * @return 443
     * 1
     */
    @Override
    public int port() {
        return 443;
    }

    /**
     * 获取SSL上下文实例<br/>
     * 构造SSL上下文实例大概三个流程： <br/>
     * 1.加载秘钥库  <br/>
     * 2.初始化秘钥管理器工厂 <br/>
     * 3.初始化SSL上下文 <br/>
     *
     * @return SSLContext
     * @throws Exception 抛出可能在构造SSL上下文过程中出现的错误，例如：证书不存在证书密码错误等
     */
    protected SSLContext sslContext() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        /* 获取客户端数字整数信息，内涵 CA 颁发证书以及私有 */
        KeyStore keyStore = KeyStore.getInstance("JKS");
        char[] certificatePassword = certificatePassword().toCharArray();
        keyStore.load(certificateStream(), certificatePassword);
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, certificatePassword);
        /* 获取认证的客户端数字证书的 CA 证书 */
        TrustManager trustManagers[] = { new MyTrustManager(clientCertificateStream(), clientCertificatePassword())};
        //初始化SSL上下文
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagers, new SecureRandom());
        return sslContext;
    }

    /**
     * 获取服务器安全套接字实例<br/>
     * 构造服务器安全套接字大概三个流程： <br/>
     * 1.构造SSL上下文  <br/>
     * 2.获取服务器安全套接字工厂<br/>
     * 3.创建绑定服务器套接字<br/>
     *
     * @return SSLServerSocket
     * @throws Exception 抛出可能在构造服务器安全套接字过程中出现的错误，例如：构造SSL上下文对象错误或者绑定端口占用等情况
     */
    protected SSLServerSocket sslServerSocket() throws Exception {
        //SSL上下文
        SSLContext sslContext = sslContext();
        //获取服务器安全套接字工厂
        SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
        //在指定的端口创建服务器安全套接字
        return (SSLServerSocket) serverSocketFactory.createServerSocket(port());
    }


    @Override
    public boolean startup() {
        final SSLServerSocket sslServerSocket;
        try {
            //服务器端套接字实例
            sslServerSocket = sslServerSocket();
            //设置单项认证
            sslServerSocket.setNeedClientAuth(true);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //接收服务
        return acceptService(sslServerSocket);
    }

    @Override
    public ResponseContext responseContext() {
        return ResponseContextFactory.responseContext();
    }

    /**
     * 接受服务
     *
     * @param sslServerSocket 服务器安全套接字
     * @return 是否正常启动接收套接字连接，true正常启动线程，false发生错误
     */
    private boolean acceptService(final SSLServerSocket sslServerSocket) {
        //启动线程开始侦听套接字连接
        new Thread(() -> {
            //判断是否进入下一个套接字连接
            while (nextConnection()) {
                final SSLSocket sslSocket;
                try {
                    sslSocket = (SSLSocket) sslServerSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    //处理套接字连接请求
                    handler(sslSocket);
                } catch (Exception e) {
                    e.printStackTrace();
                    closeSocket(sslSocket);
                }
            }
        }).start();
        return true;
    }
}
