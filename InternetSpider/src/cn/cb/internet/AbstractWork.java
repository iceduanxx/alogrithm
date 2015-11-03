package cn.cb.internet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.cb.util.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AbstractWork implements Runnable {

    private static Log log = LogFactory.getLog(HtmlParse.class);

    public AbstractWork(ImgLink imgLink, HtmlNode htmlNode) {
        super();
        this.imgLink = imgLink;
        this.htmlNode = htmlNode;
        this.abstractWork = this;
        this.file = getFile(imgLink);
    }

    private ImgLink imgLink;

    private File file;

    private HtmlNode htmlNode;

    private AbstractWork abstractWork;

    public ImgLink getImgLink() {
        return imgLink;
    }

    public void setImgLink(ImgLink imgLink) {
        this.imgLink = imgLink;
    }

    public HtmlNode getHtmlNode() {
        return htmlNode;
    }

    public void setHtmlNode(HtmlNode htmlNode) {
        this.htmlNode = htmlNode;
    }

    public File getFile(ImgLink imgLink) {
        String fileUrl = imgLink.getLink();
        String filePath = "D:\\imagebaidu\\";

        filePath += htmlNode.getTitle() + "\\" + imgLink.getId()
                + fileUrl.substring(fileUrl.lastIndexOf("."), fileUrl.length());

        /* filePath += htmlNode.getTitle() + "\\" + imgLink.getId() + ".temp"; */

        /*
           * filePath += md5.getMD5ofStr(UUID.randomUUID().toString()) +
           * fileUrl.substring(fileUrl.lastIndexOf("/")+1, fileUrl.length());
           */

        File fileNew = new File(filePath);
        if (!fileNew.getParentFile().exists()) {
            fileNew.getParentFile().mkdirs();
            /*try {
                   fileNew.createNewFile();
               } catch (IOException e) {

                   e.printStackTrace();
               }*/
        }

        return fileNew;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void run() {

        if (!imgLink.isHasDownLoad()) {
            if (imgLink.getTryCount() >= 5) {
                return;
            }

            FileUtils.saveFile(file, "utf-8", false,
                    new OutPutStreamCallback() {

                        @Override
                        public void output(BufferedOutputStream out) {
                            URL url;
                            String fileUrl = imgLink.getLink();
                            InputStream cin = null;
                            try {
                                url = new URL(fileUrl);
                                HttpURLConnection httpConn = (HttpURLConnection) url
                                        .openConnection();
                                cin = httpConn.getInputStream();
                                byte[] b = new byte[1024];
                                int len = 0;

                                while ((len = cin.read(b)) != -1) {
                                    out.write(b, 0, len);
                                }
                                log.info("文件保存的路径" + file.getAbsolutePath());
                                imgLink.setHasDownLoad(true);
                            } catch (Exception e) {
                                imgLink.setTryCount(imgLink.getTryCount() + 1);
                                imgLink.setHasDownLoad(false);
                                WorksMonitor.executorService
                                        .execute(abstractWork);
                            } finally {
                                if (cin != null) {
                                    try {
                                        cin.close();
                                    } catch (IOException e) {

                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                    });

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

    /*
      * @Override public void run() { synchronized (AbstractWork.class) { while
      * (!imgLink.isHasDownLoad()) { if (imgLink.getTryCount() >= 5) { return; }
      * else if (file.getName().lastIndexOf(".temp") != -1) {
      * FileUtils.saveFile(file, "utf-8", false, new OutPutStreamCallback() {
      *
      * @Override public void output(BufferedOutputStream out) { URL url; String
      * fileUrl = imgLink.getLink(); try { url = new URL(fileUrl);
      * HttpURLConnection httpConn = (HttpURLConnection) url .openConnection();
      * InputStream cin = httpConn.getInputStream(); int size = cin.available();
      * byte[] b = new byte[1024]; int len = 0;
      *
      * while ((len = cin.read(b)) != -1) { out.write(b, 0, len); }
      * log.info("文件保存的路径" + file.getAbsolutePath());
      *
      *
      *
      * imgLink.setHasDownLoad(true); } catch (Exception e) {
      * e.printStackTrace(); imgLink.setTryCount(imgLink.getTryCount() + 1);
      * imgLink.setHasDownLoad(false); }finally{ if (out != null) { try {
      * out.close(); } catch (IOException e) { e.printStackTrace(); } } String
      * name = file.getName(); int index = name.lastIndexOf(".tmp"); name =
      * name.substring(0, index) + fileUrl.substring( fileUrl.lastIndexOf("."),
      * fileUrl.length()); renameFile(file, name); }
      *
      * }
      *
      * }); }
      *
      * }
      *
      * }
      *
      * }
      */
}
