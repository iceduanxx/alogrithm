package cn.cb.internet;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.http.params.CoreConnectionPNames;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-5-23 Time: 上午9:31 To
 * change this template use File | Settings | File Templates.
 */
public class ApiInvoke {
	final static int API_PORT = 80;
	final static String API_PROTOCL = "http";

	/**
	 * get请求
	 * 
	 * @param api_domain
	 * @return
	 * @throws Exception
	 */
	public static String get(String api_domain) {
		String charset = "UTF-8";
		HttpClient client = new HttpClient();

		// 请求超时
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
		// 读取超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);

		HttpMethod method = new GetMethod(api_domain);
		String responseDate = null;
		byte[] bytes = null;
		try {
			client.executeMethod(method);
			bytes = method.getResponseBody();
			String body = new String(bytes, "UTF-8");
			charset = getCharSetByBody(body);
			responseDate = new String(bytes, charset);
		} catch (IOException e) {

			e.printStackTrace();
		}

		// 打印返回的信息
		method.releaseConnection();

		return responseDate;
	}

	/**
	 * post请求
	 * 
	 * @param api_domain
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String api_domain, Map params) {

		HttpClient client = new HttpClient();

		// 请求超时
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
		// 读取超时
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 6000);

		PostMethod method = new PostMethod(api_domain);
		Set<Map.Entry<String, String>> set = params.entrySet();
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it
				.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry) it.next();
			method.addParameter(entry.getKey(), entry.getValue());
		}
		String responseDate = null;
		byte[] bytes = null;
		String charset = "UTF-8";

		try {
			client.executeMethod(method);
			bytes = method.getResponseBody();
			String body = new String(bytes, "UTF-8");
			charset = getCharSetByBody(body);
			responseDate = new String(bytes, charset);
		} catch (IOException e) {

			e.printStackTrace();
		}

		// 打印返回的信息
		method.releaseConnection();

		return responseDate;
	}

	/**
	 * 根据页面body获取字符编码
	 * 
	 * @param html
	 * @return
	 */
	private static String getCharSetByBody(String html) {
		String charset = null;
		Document document = Jsoup.parse(html);
		Elements elements = document.select("meta");
		for (Element metaElement : elements) {
			if (metaElement != null
					&& StringUtils.isNotBlank(metaElement.attr("http-equiv"))
					&& metaElement.attr("http-equiv").toLowerCase()
							.equals("content-type")) {
				String content = metaElement.attr("content");
				charset = getCharSet(content);
				break;
			}
		}
		return charset;
	}

	/**
	 * 正则获取字符编码
	 * 
	 * @param content
	 * @return
	 */
	private static String getCharSet(String content) {
		String regex = ".*charset=([^;]*).*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		if (matcher.find())
			return matcher.group(1);
		else
			return null;
	}
}
