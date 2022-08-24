package wang;

/*********************************************************
 * 文件名称：Server.java
 * 软件版权: 恒生电子股份有限公司
 * 模块名称：wang
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/22 10:37
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/

import java.io.IOException;
import java.net.Socket;

/**
 * 服务器抽象类负责处理客户端请求和响应
 *
 * @param <S> 泛型可以是普通套接字也可以是安全套接字
 */
public abstract class Server<S extends Socket> {

    /**
     * 服务器端口默认80
     *
     * @return 端口
     */
    public int port() {
        return 80;
    }

    /**
     * 是否进入侦听下一个套接字连接
     *
     * @return 默认值：true
     */
    public boolean nextConnection() {
        return true;
    }

    /**
     * 关闭套接字
     *
     * @param socket 套接字
     */
    protected void closeSocket(Socket socket) {
        if (socket == null) return;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动服务器
     *
     * @return 启动成功返回true, 失败返回false
     */
    public abstract boolean startup();

    /**
     * 处理套接字抽象方法套接字等于流通道<br/>
     * 通过套接字获取到输入流来读取客户端向服务器端发送的数据<br/>
     * 通过套接字获取输出流写入数据到客户端<br/>
     * 关闭输入输出流和套接字结束本次请求与响应服务
     *
     * @param socket 套接字
     * @throws Exception 抛出处理套接字过程中一切可能出现的错误，例如：套接字突然中断导致输入输出流不可用
     */
    public abstract void handler(S socket) throws Exception;

    /**
     * 响应上下文，通常封装响应内容
     *
     * @return ResponseContext
     */
    public abstract ResponseContext responseContext();
}
