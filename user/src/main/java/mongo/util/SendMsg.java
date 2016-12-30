package mongo.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * Created by pengshu on 2016/12/28.
 */
public class SendMsg {
    public static void sendMessage(String mobile, String message) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = {new NameValuePair("Uid", "gaoju"), // 注册的用户名
                new NameValuePair("Key", "65e2320345ed2d3f5c71"),
// 注册成功后,登录网站使用的密钥，这个密钥要登录到国建网然后有一个API接口，点进去就有一个key，可以改，那个才是密钥
                new NameValuePair("smsMob", mobile), // 手机号码
                new NameValuePair("smsText", message)};//设置短信内容
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes(
                "gbk"));
        System.out.println(result);
        post.releaseConnection();
    }
}
