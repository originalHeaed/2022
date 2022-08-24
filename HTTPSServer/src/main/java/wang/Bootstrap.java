package wang;

import javax.net.ssl.SSLSocket;

/*********************************************************
 * 文件名称：Bootstrap.java
 * 软件版权: 恒生电子股份有限公司
 * 模块名称：wang
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/22 10:36
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Bootstrap {
    public static void main(String[] args) {
        //通过服务器工厂获取SSL服务器
        Server<SSLSocket> server = ServerFactory.httpsServer();
        //启动服务器
        server.startup();
    }
}
