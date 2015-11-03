package com.tmg.utils;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA. User: garmbrood Company: TianJi Media Group
 * DateTime:2009-4-25 19:40:09 Description:
 * �ļ������,�̳еķ����μ�org.apache.commons.io.FileUtils����ĵ�
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
	// todo �����Զ��巽���ڸ��������
	/**
	 * ��Stringд�뵽�ļ����÷��������ı���ʽд�õ��ļ���<br>
	 * --------------------------------------------------------------------------<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ�ȫ��
	 * @param fileContent
	 *            ����
	 * @param append
	 *            �Ƿ�׷��
	 * @throws IOException
	 *             ����
	 */
	public static void fileWrite(String fileFullName, String fileContent,
			boolean append) throws IOException {
		File f = new File(fileFullName);
		if (!f.getParentFile().exists())
			f.getParentFile().mkdirs();
		fileWrite(f, fileContent, append);
	}

	/**
	 * ��Stringд�뵽�ļ����÷��������ı���ʽд�õ��ļ���<br>
	 * --------------------------------------------------------------------------<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ�ȫ��
	 * @param fileContent
	 *            ����
	 * @param append
	 *            �Ƿ�׷��
	 * @throws IOException
	 *             ����
	 */
	public static void fileWrite(File fileFullName, String fileContent,
			boolean append) throws IOException {
		FileWriter writer = null;
		try {
			// ---------------------------------
			// ���һ���ļ�д��ľ��
			// ---------------------------------
			writer = new FileWriter(fileFullName, append);
			// ---------------------------------
			// д������
			// ---------------------------------
			writer.write(fileContent);
			// ---------------------------------
			// ������д��������
			// ---------------------------------
			writer.flush();
		} finally {
			if (writer != null) {
				// ---------------------------------
				// ��������Ϊ�ա������Ҫ�رվ��
				// ---------------------------------
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * ��byte����д�뵽�ļ������������Զ����Ƶ���ʽд��������<br>
	 * --------------------------------------------------------------------------<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ�ȫ��
	 * @param fileContent
	 *            ����
	 * @param append
	 *            �Ƿ�׷��
	 * @throws IOException
	 *             ����
	 */
	public static void fileWrite(String fileFullName, byte[] fileContent,
			boolean append) throws IOException {
		fileWrite(new File(fileFullName), fileContent, append);
	}

	/**
	 * ��byte����д�뵽�ļ������������Զ����Ƶ���ʽд��������<br>
	 * --------------------------------------------------------------------------<br>
	 * �����ߣ���˼��<br>
	 * �������ڣ�2004-1-5<br>
	 * <br>
	 * �޸��ߣ�<br>
	 * �޸����ڣ�<br>
	 * �޸�ԭ��<br>
	 * --------------------------------------------------------------------------
	 * 
	 * @param fileFullName
	 *            �ļ�ȫ��
	 * @param fileContent
	 *            ����
	 * @param append
	 *            �Ƿ�׷��
	 * @throws IOException
	 *             ����
	 */
	public static void fileWrite(File fileFullName, byte[] fileContent,
			boolean append) throws IOException {
		FileOutputStream outputStream = null;
		try {
			// ---------------------------------
			// ���һ��������д�����ľ��
			// ---------------------------------
			outputStream = new FileOutputStream(fileFullName, append);
			// ---------------------------------
			// д������
			// ---------------------------------
			outputStream.write(fileContent);
			// ---------------------------------
			// ������д��������
			// ---------------------------------
			outputStream.flush();
		} finally {
			if (outputStream != null) {
				// ---------------------------------
				// ��������Ϊ�ա������Ҫ�رվ��
				// ---------------------------------
				try {
					outputStream.close();
					outputStream = null;
				} catch (Exception e) {
				}
			}
		}
	}

	public static void fileWrite(File path, String fileName, String content,
			boolean append) throws IOException {
		if (!path.exists() || !path.isDirectory())
			path.mkdirs();
		File file = new File(path, fileName);
		fileWrite(file.getPath(), content, append);
	}

	public static void fileWrite(File path, String filename, byte[] data,
			boolean append) throws IOException {
		FileOutputStream fos = null;
		try {
			if (!path.exists()) {
				path.mkdirs();
			}
			fos = new FileOutputStream(new File(path, filename));
			fos.write(data);
			fos.close();
		} finally {
			if (fos != null)
				fos.close();
		}
	}

	/**
	 * ����URL��ַ����ȡURL�е�����
	 * 
	 * 
	 * @param path
	 *            URL��ַ
	 * @return URL�е�����
	 */
	public static String getUrlContent(String path) {
		String rtn = "";
		int c;
		try {
			if(path.indexOf("http://") == -1){
				path = "http://" + path;
			}
			java.net.URL l_url = new java.net.URL(path);
			java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url
					.openConnection();
			l_connection.setRequestProperty("User-agent", "Mozilla/4.0");
			l_connection.connect();
			InputStream l_urlStream = l_connection.getInputStream();
			while (((c = l_urlStream.read()) != -1)) {
				int all = l_urlStream.available();
				byte[] b = new byte[all];
				l_urlStream.read(b);
				rtn += new String(b, "UTF-8");
			}
			// Thread.sleep(2000);
			l_urlStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	/**
	 * ����������ȡͼƬURL
	 * @param htmlStr ����
	 * @return ͼƬURL����
	 */
	public static String[] getImgStr(String htmlStr) {
		String img = "", tmp = "";
		java.util.regex.Pattern p_image;
		java.util.regex.Matcher m_image;
		
		String regEx_img = "<img\\s+[^>]*src=['\"]?([^'\"\\s>]*)['\"]?\\s*[^>]*>"; // ͼƬ���ӵ�ַ
		p_image = java.util.regex.Pattern.compile(regEx_img,
				java.util.regex.Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			img = img + "," + m_image.group(1);
		}
		if (img.indexOf(",") >= 0){
			String images = img.substring(1);
			return images.split(",");
		}else{
			return null;
		}
	}

	public static void main(String[] args) {
		String[] images = FileUtils.getImgStr(FileUtils.getUrlContent("http://www.baidu.com"));
		if(images!=null && images.length>0){
			for(int i=0; i<images.length; i++){
				System.out.println(images[i]);
			}
		}
	}

}
