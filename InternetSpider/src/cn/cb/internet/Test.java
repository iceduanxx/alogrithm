package cn.cb.internet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://www.baidu.com/img/bdlogo.gif");
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			httpConn.connect();
			InputStream cin = httpConn.getInputStream();
			byte[] b = new byte[1024];
			int len = 0;
			File file =new File("D:\\a.jpg");
			BufferedOutputStream out = null;
			out = new BufferedOutputStream(new FileOutputStream(file));

			while ((len = cin.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
