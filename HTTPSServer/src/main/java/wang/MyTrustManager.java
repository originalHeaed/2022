package wang;

import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/*********************************************************
 * 文件名称：TrustManager.java
 * 软件版权: 恒生电子股份有限公司
 * 模块名称：com.hundsun.http
 * 功能说明：信任的服务器证书类
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/8/20 15:41
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class MyTrustManager implements X509TrustManager {

    private X509TrustManager MyX509TrustManager;

    public MyTrustManager(InputStream inputStream, String password) throws Exception {
        TrustManagerFactory managerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, password.toCharArray());
        managerFactory.init(keyStore);
        javax.net.ssl.TrustManager trustManagers[] = managerFactory.getTrustManagers();
        for (int i = 0; i < trustManagers.length; i++) {
            if (trustManagers[i] instanceof X509TrustManager) {
                MyX509TrustManager = (X509TrustManager) trustManagers[i];
                return;
            }
        }
        throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
    }

    /**
     * 校验客户端证书
     */
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        try {
            MyX509TrustManager.checkClientTrusted(x509Certificates, s);
        } catch (Exception e) {
            // 校验失败时的处理 todo
            System.out.println("客户端校验失败");
            throw new RuntimeException("客户端校验失败证书");
        }
    }

    /**
     * 校验服务端证书
     */
    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        try {
            MyX509TrustManager.checkServerTrusted(x509Certificates, s);
        } catch (Exception e) {
            // 校验失败时的处理
            System.out.println("服务端校验失败");
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return MyX509TrustManager.getAcceptedIssuers();
    }
}
