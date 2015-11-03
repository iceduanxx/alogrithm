<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="com.tmg.utils.PathUtil"%>
<%@ page import="com.tmg.utils.ImageUtils" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.awt.*" %>
<%@ page import="com.tmg.utils.FileUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<!--
     processparam:
        图片上传后的处理方式，是否需要制作缩略图
	    格式：生成缩略图的方式(０: 不生成 1 按高等比 2 按宽等比 3 按高宽最适应等比 4 不等比), {[缩略图高度]，[缩略图宽度]}
	    如：2,  500 , 250

-->
<%
    request.setCharacterEncoding("UTF-8");
	int maxPostSize = 100 * 1024 * 1024;
	String lastname = "";
	File file = null;
	String picFrom = "";
	String firstName = "";
	String picTo = "";
    //System.out.println("uploading ....");

    //System.out.println("watermarkno: " + request.getParameter("watermarkno") + " watermarkpos: " + request.getParameter("watermarkpos"));
	
    DiskFileItemFactory factory = new DiskFileItemFactory();
    factory.setSizeThreshold(4096);

    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setSizeMax(maxPostSize);
    String processparam = request.getParameter("processParam");
    try {
        List fileItems = upload.parseRequest(request);
        Iterator iter = fileItems.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            if (!item.isFormField()) {
                String name = item.getName();
                lastname = name.substring(name.lastIndexOf('.'),name.length());
                //如果不是图片类型，就不允许上传
                if(StringUtils.isNotEmpty(lastname)){
                    if(!lastname.equalsIgnoreCase(".jpg")&&!lastname.equalsIgnoreCase(".jpeg")&&!lastname.equalsIgnoreCase(".gif")&&!lastname.equalsIgnoreCase(".png"))
                        continue;
                }
                try {
                	file = PathUtil.getRandFile(lastname);
                    item.write(file);
                    picFrom = file.getPath();
	                    	                
                    if (!"0".equals(processparam)) {//如果没有设置processparam，则flash传过来的参数为0
					String[] params = processparam.split(",");
					if(params.length>0){
	                    firstName = picFrom.substring(0,picFrom.lastIndexOf("."));
	                    picTo = firstName + "_L" + lastname;
	                    if(params.length==3){
	                    	//System.out.println("picFrom="+picFrom+"||||picTo="+picTo);
                            if(Integer.parseInt(params[1])==0||Integer.parseInt(params[2])==0){
                                File srcFile = new File(picFrom);
                                File destFile = new File(picTo);
                                FileUtils.copyFile(srcFile, destFile);
                            } else
	                    	ImageUtils.resize(picFrom,picTo,Integer.parseInt(params[1]),Integer.parseInt(params[2]),true);
	                    }else{
	                    	 if(Integer.parseInt(params[1])==0){
                                File srcFile = new File(picFrom);
                                File destFile = new File(picTo);
                                FileUtils.copyFile(srcFile, destFile);
                            }else
                            ImageUtils.resize(picFrom,picTo,Integer.parseInt(params[1]),0,true);
	                    }
					}
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
    }

    out.clear();
    picFrom = PathUtil.getRandFileUrl(file);
    if (!"0".equals(processparam)) {
        firstName = picFrom.substring(0,picFrom.lastIndexOf("."));
        picTo = firstName + "_L" + lastname;
        out.print(picTo);
        out.flush();
    }else{
        out.print(picFrom);
        out.flush();
        System.out.println("picFrom = " + picFrom);
    }
%>