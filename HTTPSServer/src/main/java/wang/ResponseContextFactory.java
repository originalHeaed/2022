package wang;


/*********************************************************
 * 文件名称：ResponseContextFactory.java
 * 软件版权: 恒生电子股份有限公司
 * 模块名称：wang
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/22 10:38
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public final class ResponseContextFactory {

    /**
     * 创建默认响应上下文
     *
     * @return ResponseContext
     */
    public static ResponseContext responseContext() {
        return new ResponseContextHandler();
    }

}
