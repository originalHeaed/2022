package wang;

/*********************************************************
 * 文件名称：ServerFactory.java
 * 软件版权: 恒生电子股份有限公司
 * 模块名称：wang
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/22 10:38
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/

import javax.net.ssl.SSLSocket;
import java.net.Socket;

/**
 * 服务器工厂<br/>
 * 提供HTTP服务器和HTTPS服务器<br/>
 * 默认实现HTTPS服务器<br/>
 */
public final class ServerFactory {

    /**
     * 创建Https服务器
     *
     * @return 服务器 Server<SSLSocket>
     */
    public static Server<SSLSocket> httpsServer() {
        return new HttpsServer();
    }

    /**
     * 创建Http服务器，默认未实现
     *
     * @return 服务器 Server<Socket>
     */
    public static Server<Socket> httpServer() {
        return null;
    }

}
