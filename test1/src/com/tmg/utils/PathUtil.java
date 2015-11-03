package com.tmg.utils;

import com.tmg.utils.DateUtils;
import com.tmg.utils.StringUtils;

import java.io.File;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: garmbrood
 * Time: 2009-5-25 11:15:15
 * Company: �켫��ý����
 * Descripion: ��ȡ��Դ����ȵķ���·��
 */
public class PathUtil {


    /**
     * web��·��������Ŀ��ʼ��ʱ��Lisener�и�ֵ
     */
    public static String appPath = "c:\\";
    /**
     * ͼƬ�ϴ�Ŀ¼
     */
    public static final String IMAGE_SAVE_PATH = "/uploadImages/";

    public static final String SOFT_SAVE_PATH ="/uploadSoftware/";

    public static final String VIDEO_SAVE_PATH ="/uploadVideos/";
    
    /**
     * ���� ROOT ·��
     */
    public static String getAppPath(){
    	return appPath;
    }

    /**
     * ��ȡ����articleId�ĵ�pageNoҳ���ļ�·���������ӦĿ¼���������Զ�����
     *
     * @param articleId ����id
     * @param pageNo    ��ҳ��
     * @return ����·��
     */
    public static File getArticleContentPagePath(int articleId, int pageNo) {
        File path = new File(new StringBuilder()
                .append(appPath)
                .append(File.separator)
                .append("art")
                .append(File.separator)
                .append(articleId % 500)
                .append(File.separator)
                .append(articleId).append("_")
                .append(pageNo).append(".html").toString());
        if (!path.getParentFile().exists()) {
            path.getParentFile().mkdirs();
        }
        return path;
    }
        /**
     * ��ȡ����articleId�ĵ�pageNoҳ���ļ�·���������ӦĿ¼���������Զ�����
     *
     * @param articleId ����id
     * @param pageNo    ��ҳ��
     * @return ����·��
     */
    public static File getArticleContentBackupPagePath(int articleId, int pageNo) {
        File path = new File(new StringBuilder()
                .append(appPath)
                .append(File.separator)
                .append("art2storage")
                .append(File.separator)
                .append(articleId % 500)
                .append(File.separator)
                .append(articleId).append("_")
                .append(pageNo).append(".html").toString());
        if (!path.getParentFile().exists()) {
            path.getParentFile().mkdirs();
        }
        return path;
    }
         /**
     * ��ȡ����articleId�ĵ�pageNoҳ���ļ�·���������ӦĿ¼���������Զ�����
     *
     * @param articleId ����id
     * @param pageNo    ��ҳ��
     * @return ����·��   д�뵽����Ŀ¼��
     */
    public static File getArticleContentItcpnPagePath(int articleId, int pageNo) {
        File path = new File(new StringBuilder()
                .append(appPath)
                .append(File.separator)
                .append("artItcpn")
                .append(File.separator)
                .append(articleId % 500)
                .append(File.separator)
                .append(articleId).append("_")
                .append(pageNo).append(".html").toString());
        if (!path.getParentFile().exists()) {
            path.getParentFile().mkdirs();
        }
        return path;
    }

    /** ���ݽڵ�id �õ��ڵ�ĵ�������
     * @param sitemapId �ڵ�id
     * @return ����·��
     * */

    public static File getSitemapNavigation(int sitemapId){
        File path = new File(new StringBuilder()
                .append(appPath)
                .append(File.separator).append("navigation")
                .append(File.separator).append("N").append(sitemapId).append(".html").toString());
        if (!path.getParentFile().exists()) {
            path.getParentFile().mkdirs();
        }
        return path;
    }

    /**
     * ��ȡ�ϴ��ļ���������ƣ��Զ���������Ŀ¼���ļ����ְ�12λ�������
     * @param s_FileExt �ļ���׺���磺.jpg .gif��
     * @return �ļ����
     */
    public static File getRandFile(String s_FileExt) {
        String file = DateUtils.format(new Date(), "yyyy"+File.separator+"DDD"+File.separator) + getGuid(12) + s_FileExt;
        File fileName = new File(new File(appPath , IMAGE_SAVE_PATH), file);
        if (!fileName.getParentFile().exists()) {
            fileName.getParentFile().mkdirs();
        }
        return fileName;
    }
    public static File getVideoRandFile(String s_FileExt) {
        String file = DateUtils.format(new Date(), "yyyy"+File.separator+"DDD"+File.separator) + getGuid(12) + s_FileExt;
        File fileName = new File(new File(appPath , VIDEO_SAVE_PATH), file);
        if (!fileName.getParentFile().exists()) {
            fileName.getParentFile().mkdirs();
        }
        return fileName;
    }
    /**
     * ���ڻ������ϴ���·��
     * ��ȡ�ϴ��ļ���������ƣ��Զ���������Ŀ¼���ļ����ְ�12λ�������
     * @param name �ļ�������׺��׺���磺aaa.jpg bbb.gif��
     * @return �ļ����
     */
    public static File getRandSoftwareFile(String name) {
           String file = DateUtils.format(new Date(), "yyyy"+File.separator+"DDD"+File.separator) + getGuid(12) + "_"+name;
           File fileName = new File(new File(appPath , SOFT_SAVE_PATH), file);
           if (!fileName.getParentFile().exists()) {
               fileName.getParentFile().mkdirs();
           }
           return fileName;
       }

     /**
     * ��ȡ�ϴ��ļ���������ƣ��Զ���������Ŀ¼���ļ����ְ�12λ�������
     * @param s_FileExt �ļ���׺���磺.jpg .gif��
     * @return �ļ����
     */
    public static File getRandFile(String s_FileExt,String name) {
        String file = DateUtils.format(new Date(), "yyyy"+File.separator+"DDD"+File.separator) + getGuid(12) +"_"+name + s_FileExt;
        File fileName = new File(new File(appPath , IMAGE_SAVE_PATH), file);
        if (!fileName.getParentFile().exists()) {
            fileName.getParentFile().mkdirs();
        }
        return fileName;
    }

    public static String justTest(String s){
        return s;
    }

    /**
     * ����web�µ��ļ�������ظ��ļ���url
     * @param s_File ����webĿ¼�µ��ļ����
     * @return �ļ���url
     */
    public static String getRandFileUrl(File s_File){
        return new StringBuilder().append("/")
                .append(StringUtils.substringAfter(s_File.getPath(), appPath).replace(File.separator, "/")).toString();
    }

    /**
     * ����url��ȡ��Ӧ�����е��ļ�
     * @param url ��Դ��url
     * @return ��Դ��Ӧ���ļ�
     */
    public static File getFileFromUrl(String url){
       if(url == null)
           return null;
       if(url.startsWith("/")) {
           return new File(appPath,url);
       }else if(url.startsWith("http")){
           url = StringUtils.substringAfter(url,"http://");
           url = StringUtils.substringAfter(url,"/");
           return new File(appPath,url);
       }else
           return null;
    }

    public static void main(String[] args) {
        File f = getFileFromUrl("http://localhost:9080/video/init.jhtml");
        System.out.println("f = " + f);
    }

     /**
     * ����һ��ָ�����ȵĵ�guid<br>
     * �ɴ�д��ĸ+���ֹ���
     * --------------------------------------------------------------------------<br>
     * �����ߣ���˼��<br>
     * �������ڣ�2002-12-5<br>
     * <br>
     * �޸��ߣ�<br>
     * �޸����ڣ�<br>
     * �޸�ԭ��<br>
     * --------------------------------------------------------------------------
     *
     * @param len
     * @return guid
     */
    public static String getGuid(int len) {
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            int k;
            switch ((int) (Math.random() * 2D)) {
                case 0: // '\0'
                    k = (int) (Math.random() * 10D) + 48;
                    break;

                case 1: // '\001'
                    k = (int) (Math.random() * 26D) + 65;
                    break;

                default:
                    k = 95;
                    break;
            }
            sb.append((char) k);
        }

        return sb.toString();
    }

}
