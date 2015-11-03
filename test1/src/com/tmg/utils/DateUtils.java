package com.tmg.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: garmbrood
 * Time: 2009-4-7 16:32:56
 * Company: �켫��ý����
 * Descripion:���ڹ�����,�̳���apache��DateUtils�࣬�̳еķ����μ�org.apache.commons.lang.time.DateUtils���ĵ�
 *            �����˳��������ڸ�ʽ����ʽ��ʱ�Զ�������Ӧ����
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils{


       public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

       public static final String FORMAT_DATE = "yyyy-MM-dd";

       public static final String FORMAT_MONTH = "yyyy-MM";

       public static final String FORMAT_TIME = "HH:mm:ss";

       public static final String FORMAT_SHORT_DATE_TIME = "MM-dd HH:mm";

       public static final String FORMAT_DATE_TIME = FORMAT_DEFAULT;

       public static final String FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm";

       public static final String FORMAT_JAPAN = "MM.dd(EEE) HH";

       public static final String FORMAT_CHINESE_NO_SECOND = "yyyy��MM��dd�� HH:mm";

       public static final String FORMAT_CHINESE_NO_SECOND_1 = "yyyy��MM��dd��HH:mm"; 

       public static final String FORMAT_CHINESE = "yyyy��MM��dd�� HH��mm��";

       public static final int TYPE_HTML_SPACE = 2;

       public static final int TYPE_DECREASE_SYMBOL = 3;

       public static final int TYPE_SPACE = 4;

       public static final int TYPE_NULL = 5;

    public static Map<String, SimpleDateFormat> getFormaters() {
        return formaters;
    }

    private static Map<String, SimpleDateFormat> formaters = new HashMap<String, SimpleDateFormat>();

    static {
        SimpleDateFormat defaultFormater = new SimpleDateFormat(FORMAT_DEFAULT,Locale.CHINA);
        formaters.put(FORMAT_DEFAULT, defaultFormater);
        formaters.put(FORMAT_DATE, new SimpleDateFormat(FORMAT_DATE,Locale.CHINA));
        formaters.put(FORMAT_MONTH, new SimpleDateFormat(FORMAT_MONTH,Locale.CHINA));
        formaters.put(FORMAT_TIME, new SimpleDateFormat(FORMAT_TIME,Locale.CHINA));
        formaters.put(FORMAT_SHORT_DATE_TIME, new SimpleDateFormat(FORMAT_SHORT_DATE_TIME,Locale.CHINA));
        formaters.put(FORMAT_CHINESE_NO_SECOND, new SimpleDateFormat(FORMAT_CHINESE_NO_SECOND,Locale.CHINA));
        formaters.put(FORMAT_CHINESE, new SimpleDateFormat(FORMAT_CHINESE,Locale.CHINA));
        formaters.put(FORMAT_DATE_TIME, defaultFormater);
        formaters.put(FORMAT_NO_SECOND, new SimpleDateFormat(FORMAT_NO_SECOND,Locale.CHINA));
        formaters.put(FORMAT_JAPAN, new SimpleDateFormat(FORMAT_JAPAN, Locale.JAPAN));
        formaters.put(FORMAT_CHINESE_NO_SECOND_1, new SimpleDateFormat(FORMAT_CHINESE_NO_SECOND_1,Locale.CHINA));
    }

    /**
     * ʹ�ø����� pattern �����ڽ���ʽ��Ϊ�ַ���
     *
     * @param date    ����ʽ��������
     * @param pattern ��ʽ�ַ���
     * @return ��ʽ����������ַ���
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat dateFormat;
        if (formaters.containsKey(pattern)) {
            dateFormat = formaters.get(pattern);
        } else {
            dateFormat = new SimpleDateFormat(pattern);
        }
        return dateFormat.format(date);
    }

    /**
     * ��Ĭ�����ڸ�ʽ(yyyy-MM-dd HH:mm:ss)�����ڽ��и�ʽ��
     *
     * @param date ����ʽ��������
     * @return ��ʽ����������ַ���
     */
    public static String format(Date date) {
        return formaters.get(FORMAT_DEFAULT).format(date);
    }

        /**
     * ��ʽ�����ڣ�
     * --------------------------------------------------------------------------<br>
     * �����ߣ���˼��<br>
     * �������ڣ�2002-1-16<br>
     * <br>
     * �޸��ߣ�<br>
     * �޸����ڣ�<br>
     * �޸�ԭ��<br>
     * --------------------------------------------------------------------------
     *
     * @param date   Ҫ��ʽ�������ڶ���
     * @param format ��ʽ
     * @param type   �������Ϊ�գ����巵�ص�����
     * @return ����ֵ�����dateΪ�գ���type���巵�����ͣ������ʽ��ʧ�ܡ��򷵻ؿմ�
     */
    public static String format(Date date,
                                String format,
                                int type) {
        if (date != null) {
            //---------------------------------
            // ���ڲ�Ϊ��ʱ�Ÿ�ʽ
            //---------------------------------
            try {
                //---------------------------------
                // ����SimpleDateFormat����ʽ��
                //---------------------------------
                return new SimpleDateFormat(format).format(date);
            } catch (Exception e) {
                //---------------------------------
                // ��ʽ��ʧ�ܺ󣬷���һ���մ�
                //---------------------------------
                return "";
            }
        } else {
            //---------------------------------
            // �����������Ϊ�գ���������ͷ��ؽ��
            //---------------------------------
            switch (type) {
                case TYPE_HTML_SPACE: // '\002'
                    return "&nbsp;";

                case TYPE_DECREASE_SYMBOL: // '\003'
                    return "-";

                case TYPE_SPACE: // '\004'
                    return " ";

                case TYPE_NULL:
                    return null;

                default:
                    //---------------------------------
                    // Ĭ��Ϊ�մ�
                    //---------------------------------
                    return "";
            }
        }
    }

    /**
     * �������ַ�������Ϊ��Ӧ��ʽ������,ѭ������ʹ��Ԥ��������ڸ�ʽ���н���
     *
     * @param str �������������ַ���
     * @return �����ɹ������ڣ�����ʧ�ܷ���null
     */
    public static Date parse(String str) {
        Date date = null;
        for (String _pattern : formaters.keySet()) {
            if (_pattern.getBytes().length == str.getBytes().length) {
                try {
                    date = formaters.get(_pattern).parse(str);
                    //��ʽ���ɹ����˳�
                    break;
                } catch (ParseException e) {
                    //��ʽ��ʧ�ܣ�����������һ��

                }
            }else if(_pattern.equals(FORMAT_JAPAN)){
                 try {
                    date = formaters.get(_pattern).parse(str);
                    //��ʽ���ɹ����˳�
                    break;
                } catch (ParseException e) {}
            } 
        }
        return date;
    }

    public static Date parse(String str,String pattern){
        SimpleDateFormat dateFormat;
        if (formaters.containsKey(pattern)) {
            dateFormat = formaters.get(pattern);
        } else {
            dateFormat = new SimpleDateFormat(pattern);
        }
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            //�Ǹ�ʽʧ��

        }
        return date;
    }

    /**
     * date2 �Ƿ���date1֮��
     * @param date1 ��������
     * @param date2 �Ƚ�����
     * @return true:�� false:��
     */
    public static boolean isAfter(Date date1, Date date2){
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar1.setTime(date1);
        return calendar2.after(calendar1);
    }

    public static void main(String[] args) {
        String sdate = "2010-08-19 08:40:04";
        Date d = DateUtils.parse(sdate);
        System.out.println("DateUtils.format(d) = " + DateUtils.format(d));
       
    }

    public static String getNowTime(){
        return DateUtils.format(new Date());
    }

     public static Date getUKTime(String timeStr){
       Date date =null;
       DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.UK);
       try {
            date =df.parse(timeStr);
       } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
       }
       return date;
    }

}
