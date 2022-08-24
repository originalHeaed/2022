package wgh.study.entities;

/**
 * Author:   wang
 * Date:     2022/5/4 21:53
 * function: 公共结果返回类
 */
public class CommonResult<T> {

    /**
     * 响应码
     */
    private String respCode;

    /**
     * 响应信息
     */
    private String respMsg;

    /**
     * 返回的数据对象
     */
    private T data;

    /**
     * 满参构造方法
     * @param respCode
     * @param respMsg
     */
    public CommonResult(String respCode, String respMsg, T data) {
        this.data = data;
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    /**
     * 无参构造方法
     */
    public CommonResult() {

    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}