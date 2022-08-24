package wang;

/*********************************************************
 * 文件名称：ResponseContextHandler.java
 * 软件版权: 恒生电子股份有限公司
 * 模块名称：wang
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/22 10:38
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/

import java.nio.charset.StandardCharsets;

/**
 * 默认响应处理器，实现ResponseContext接口
 */
public class ResponseContextHandler implements ResponseContext {

    private byte[] head;
    private byte[] body;
    private byte[] response;

    @Override
    public byte[] head() {
        if (head != null) return head;
        String headString =
                "HTTP/1.1 200\n" +
                        "Server: nginx/1.17.0\n" +
                        "Date: Tue, 06 Aug 2019 03:33:50 GMT\n" +
                        "Content-Type: text/html;charset=UTF-8\n" +
                        "Content-Length: " + body().length + "\n" +
                        "Connection: keep-alive\n" +
                        "Content-Language: zh-CN\n\n";
        head = headString.getBytes();
        return head;
    }

    @Override
    public byte[] body() {
        if (body != null) return body;
        String bodyString = "qw12121212";
        body = bodyString.getBytes(StandardCharsets.UTF_8);
        return body;
    }

    @Override
    public byte[] response() {
        if (response != null) return response;
        //创建响应字节数组
        response = new byte[head().length + body().length];
        //将响应头复制到完整响应中
        System.arraycopy(head(), 0, response, 0, head().length);
        //将响应体复制到完整响应中
        System.arraycopy(body(), 0, response, head().length, body().length);
        return response;
    }
}
