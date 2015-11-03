package com.tmg.utils;

import com.tmg.utils.DateUtils;
import com.tmg.utils.StringUtils;

import java.io.File;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: garmbrood
 * Time: 2009-5-25 11:15:15
 * Company: 天极传媒集团
 * Descripion: 获取资源、块等的发布路径
 */
public class PathUtil {


    /**
     * web根路径，由项目初始化时在Lisener中赋值
     */
    public static String appPath = "c:\\";
    /**
     * 图片上传目录
     */
    public static final String IMAGE_SAVE_PATH = "/uploadImages/";

    public static final String SOFT_SAVE_PATH ="/uploadSoftware/";

    public static final String VIDEO_SAVE_PATH ="/uploadVideos/";
    
    /**
     * 返回 ROOT 路径
     */
    public static String getAppPath(){
    	return appPath;
    }

    /**
     * 提取文章articleId的第pageNo页的文件路径，如果对应目录不存在则自动建立
     *
     * @param articleId 文章id
     * @param pageNo    分页号
     * @return 物理路径
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
     * 提取文章articleId的第pageNo页的文件路径，如果对应目录不存在则自动建立
     *
     * @param articleId 文章id
     * @param pageNo    分页号
     * @return 物理路径
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
     * 提取文章articleId的第pageNo页的文件路径，如果对应目录不存在则自动建立
     *
     * @param articleId 文章id
     * @param pageNo    分页号
     * @return 物理路径   写入到分众目录的
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

    /** 根据节点id 得到节点的导航链接
     * @param sitemapId 节点id
     * @return 物理路径
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
     * 获取上传文件的随机名称，自动建立父级目录，文件名字按12位随机生成
     * @param s_FileExt 文件后缀，如：.jpg .gif等
     * @return 文件句柄
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
     * 用于获得软件上传的路径
     * 获取上传文件的随机名称，自动建立父级目录，文件名字按12位随机生成
     * @param name 文件名带后缀后缀，如：aaa.jpg bbb.gif等
     * @return 文件句柄
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
     * 获取上传文件的随机名称，自动建立父级目录，文件名字按12位随机生成
     * @param s_FileExt 文件后缀，如：.jpg .gif等
     * @return 文件句柄
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
     * 根据web下的文件句柄返回该文件的url
     * @param s_File 处在web目录下的文件句柄
     * @return 文件的url
     */
    public static String getRandFileUrl(File s_File){
        return new StringBuilder().append("/")
                .append(StringUtils.substringAfter(s_File.getPath(), appPath).replace(File.separator, "/")).toString();
    }

    /**
     * 根据url获取对应磁盘中的文件
     * @param url 资源的url
     * @return 资源对应的文件
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
     * 产生一个指定长度的的guid<br>
     * 由大写字母+数字构成
     * --------------------------------------------------------------------------<br>
     * 创建者：杨思勇<br>
     * 创建日期：2002-12-5<br>
     * <br>
     * 修改者：<br>
     * 修改日期：<br>
     * 修改原因：<br>
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
