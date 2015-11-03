package cn.cb.internet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestImgDownload {
	private static Log log = LogFactory.getLog(TestImgDownload.class);
	private ImgLink imgLink;

	private File file;

	private String title;
	
	

	public TestImgDownload() {
		super();
	}

	public TestImgDownload(ImgLink imgLink, String title) {
		super();
		this.imgLink = imgLink;
		this.title = title;
		this.file = getFile(imgLink);
	}

	public File getFile(ImgLink imgLink) {
		String fileUrl = imgLink.getLink();
		String filePath = "D:\\imagesnew\\";

		filePath += title + "\\" + imgLink.getId() + ".temp";

		log.info("文件保存的路径" + filePath);
		File fileNew = new File(filePath);
		if (!fileNew.getParentFile().exists()) {
			fileNew.getParentFile().mkdirs();
			try {
				fileNew.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return fileNew;
	}

	public void saveImgFile() {
		while (!imgLink.isHasDownLoad()) {
			if (imgLink.getTryCount() >= 5) {
				return;
			} else if (file.getName().lastIndexOf(".temp") != -1) {
				FileUtils.saveFile(file, "utf-8", false,
						new OutPutStreamCallback() {

							@Override
							public void output(BufferedOutputStream out) {
								URL url;
								String fileUrl = imgLink.getLink();
								try {
									url = new URL(fileUrl);
									HttpURLConnection httpConn = (HttpURLConnection) url
											.openConnection();
									InputStream cin = httpConn.getInputStream();
									int size = cin.available();
									byte[] b = new byte[1024];
									int len = 0;

									while ((len = cin.read(b)) != -1) {
										out.write(b, 0, len);
									}

									imgLink.setHasDownLoad(true);
								} catch (Exception e) {
									e.printStackTrace();
									imgLink.setTryCount(imgLink.getTryCount() + 1);
									imgLink.setHasDownLoad(false);
								} finally {
									if (out != null) {
										try {
											out.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
									String name = file.getAbsolutePath();
									
									int index = name.lastIndexOf(".");
									name = name.substring(0, index)
											+ fileUrl.substring(
													fileUrl.lastIndexOf("."),
													fileUrl.length());
									renameFile(file, name);

								}

							}

						});
			}

		}

	}

	public void renameFile(File file, String toFile) {

		// 检查要重命名的文件是否存在，是否是文件
		if (!file.exists() || file.isDirectory()) {

			log.info("File does not exist: " + file);
			return;
		}

		File newFile = new File(toFile);

		// 修改文件名
		if (file.renameTo(newFile)) {
			log.info("File has been renamed.");
		} else {
			log.info("Error renmaing file");
		}
	}

	public static void main(String[] args) {
		String title = "older women lovers[8P]";
		ImgLink imgLink = new ImgLink();
		imgLink.setId(1);
		imgLink.setLink("http://ads.hogtied.com/imagedb/11702/i/h/830/0.jpg");
		new TestImgDownload(imgLink, title).saveImgFile();
		
		/*File file = new File("D:\\imagesnew\\older women lovers[8P]\\1.temp");
		new TestImgDownload().renameFile(file, "D:\\imagesnew\\older women lovers[8P]\\1.jpg");*/
	}
}
