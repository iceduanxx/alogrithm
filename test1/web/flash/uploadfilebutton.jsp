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
        ͼƬ�ϴ���Ĵ���ʽ���Ƿ���Ҫ��������ͼ
	    ��ʽ����������ͼ�ķ�ʽ(��: ������ 1 ���ߵȱ� 2 ����ȱ� 3 ���߿�����Ӧ�ȱ� 4 ���ȱ�), {[����ͼ�߶�]��[����ͼ���]}
	    �磺2,  500 , 250

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
                //�������ͼƬ���ͣ��Ͳ������ϴ�
                if(StringUtils.isNotEmpty(lastname)){
                    if(!lastname.equalsIgnoreCase(".jpg")&&!lastname.equalsIgnoreCase(".jpeg")&&!lastname.equalsIgnoreCase(".gif")&&!lastname.equalsIgnoreCase(".png"))
                        continue;
                }
                try {
                	file = PathUtil.getRandFile(lastname);
                    item.write(file);
                    picFrom = file.getPath();
	                    	                
                    if (!"0".equals(processparam)) {//���û������processparam����flash�������Ĳ���Ϊ0
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