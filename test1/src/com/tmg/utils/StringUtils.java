package com.tmg.utils;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User:    garmbrood
 * Company: 天极传媒集团
 * DateTime:2009-4-25 19:37:23
 * Description: 字符串操作工具类,继承的方法参见org.apache.commons.lang.StringUtils相关文档
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    //保存一级字库
    public static HashMap<Integer, String> pinyinMap = new HashMap<Integer, String>();

    static {
        //初始化一级字库
        String pinymap = "0XB0A1:A,0XB0A3:AI,0XB0B0:AN,0XB0B9:ANG,0XB0BC:AO,0XB0C5:BA,0XB0D7:BAI,0XB0DF:BAN,0XB0EE:BANG,0XB0FA:BAO,0XB1AD:BEI,0XB1BC:BEN,0XB1C0:BENG,0XB1C6:BI,0XB1DE:BIAN,0XB1EA:BIAO,0XB1EE:BIE,0XB1F2:BIN,0XB1F8:BING,0XB2A3:BO,0XB2B8:BU,0XB2C1:CA,0XB2C2:CAI,0XB2CD:CAN,0XB2D4:CANG,0XB2D9:CAO,0XB2DE:CE,0XB2E3:CENG,0XB2E5:CHA,0XB2F0:CHAI,0XB2F3:CHAN,0XB2FD:CHANG,0XB3AC:CHAO,0XB3B5:CHE,0XB3BB:CHEN,0XB3C5:CHENG,0XB3D4:CHI,0XB3E4:CHONG,0XB3E9:CHOU,0XB3F5:CHU,0XB4A7:CHUAI,0XB4A8:CHUAN,0XB4AF:CHUANG,0XB4B5:CHUI,0XB4BA:CHUN,0XB4C1:CHUO,0XB4C3:CI,0XB4CF:CONG,0XB4D5:COU,0XB4D6:CU,0XB4DA:CUAN,0XB4DD:CUI,0XB4E5:CUN,0XB4E8:CUO,0XB4EE:DA,0XB4F4:DAI,0XB5A2:DAN,0XB5B1:DANG,0XB5B6:DAO,0XB5C2:DE,0XB5C5:DENG,0XB5CC:DI,0XB5DF:DIAN,0XB5EF:DIAO,0XB5F8:DIE,0XB6A1:DING,0XB6AA:DIU,0XB6AB:DONG,0XB6B5:DOU,0XB6BC:DU,0XB6CB:DUAN,0XB6D1:DUI,0XB6D5:DUN,0XB6DE:DUO,0XB6EA:E,0XB6F7:EN,0XB6F8:ER,0XB7A2:FA,0XB7AA:FAN,0XB7BB:FANG,0XB7C6:FEI,0XB7D2:FEN,0XB7E1:FENG,0XB7F0:FO,0XB7F1:FOU,0XB7F2:FU,0XB8C1:GA,0XB8C3:GAI,0XB8C9:GAN,0XB8D4:GANG,0XB8DD:GAO,0XB8E7:GE,0XB8F8:GEI,0XB8F9:GEN,0XB8FB:GENG,0XB9A4:GONG,0XB9B3:GOU,0XB9BC:GU,0XB9CE:GUA,0XB9D4:GUAI,0XB9D7:GUAN,0XB9E2:GUANG,0XB9E5:GUI,0XB9F5:GUN,0XB9F8:GUO,0XB9FE:HA,0XBAA1:HAI,0XBAA8:HAN,0XBABB:HANG,0XBABE:HAO,0XBAC7:HE,0XBAD9:HEI,0XBADB:HEN,0XBADF:HENG,0XBAE4:HONG,0XBAED:HOU,0XBAF4:HU,0XBBA8:HUA,0XBBB1:HUAI,0XBBB6:HUAN,0XBBC4:HUANG,0XBBD2:HUI,0XBBE7:HUN,0XBBED:HUO,0XBBF7:JI,0XBCCE:JIA,0XBCDF:JIAN,0XBDA9:JIANG,0XBDB6:JIAO,0XBDD2:JIE,0XBDED:JIN,0XBEA3:JING,0XBEBC:JIONG,0XBEBE:JIU,0XBECF:JU,0XBEE8:JUAN,0XBEEF:JUE,0XBEF9:JUN,0XBFA6:KA,0XBFAA:KAI,0XBFAF:KAN,0XBFB5:KANG,0XBFBC:KAO,0XBFC0:KE,0XBFCF:KEN,0XBFD3:KENG,0XBFD5:KONG,0XBFD9:KOU,0XBFDD:KU,0XBFE4:KUA,0XBFE9:KUAI,0XBFED:KUAN,0XBFEF:KUANG,0XBFF7:KUI,0XC0A4:KUN,0XC0A8:KUO,0XC0AC:LA,0XC0B3:LAI,0XC0B6:LAN,0XC0C5:LANG,0XC0CC:LAO,0XC0D5:LE,0XC0D7:LEI,0XC0E2:LENG,0XC0E5:LI,0XC1A9:LIA,0XC1AA:LIAN,0XC1B8:LIANG,0XC1C3:LIAO,0XC1D0:LIE,0XC1D5:LIN,0XC1E1:LING,0XC1EF:LIU,0XC1FA:LONG,0XC2A5:LOU,0XC2AB:LU,0XC2BF:LV,0XC2CD:LUAN,0XC2D3:LUE,0XC2D5:LUN,0XC2DC:LUO,0XC2E8:MA,0XC2F1:MAI,0XC2F7:MAN,0XC3A2:MANG,0XC3A8:MAO,0XC3B4:ME,0XC3B5:MEI,0XC3C5:MEN,0XC3C8:MENG,0XC3D0:MI,0XC3DE:MIAN,0XC3E7:MIAO,0XC3EF:MIE,0XC3F1:MIN,0XC3F7:MING,0XC3FD:MIU,0XC3FE:MO,0XC4B1:MOU,0XC4B4:MU,0XC4C3:NA,0XC4CA:NAI,0XC4CF:NAN,0XC4D2:NANG,0XC4D3:NAO,0XC4D8:NE,0XC4D9:NEI,0XC4DB:NEN,0XC4DC:NENG,0XC4DD:NI,0XC4E8:NIAN,0XC4EF:NIANG,0XC4F1:NIAO,0XC4F3:NIE,0XC4FA:NIN,0XC4FB:NING,0XC5A3:NIU,0XC5A7:NONG,0XC5AB:NU,0XC5AE:NV,0XC5AF:NUAN,0XC5B0:NUE,0XC5B2:NUO,0XC5B6:O,0XC5B7:OU,0XC5BE:PA,0XC5C4:PAI,0XC5CA:PAN,0XC5D2:PANG,0XC5D7:PAO,0XC5DE:PEI,0XC5E7:PEN,0XC5E9:PENG,0XC5F7:PI,0XC6AA:PIAN,0XC6AE:PIAO,0XC6B2:PIE,0XC6B4:PIN,0XC6B9:PING,0XC6C2:PO,0XC6CB:PU,0XC6DA:QI,0XC6FE:QIA,0XC7A3:QIAN,0XC7B9:QIANG,0XC7C1:QIAO,0XC7D0:QIE,0XC7D5:QIN,0XC7E0:QING,0XC7ED:QIONG,0XC7EF:QIU,0XC7F7:QU,0XC8A6:QUAN,0XC8B1:QUE,0XC8B9:QUN,0XC8BB:RAN,0XC8BF:RANG,0XC8C4:RAO,0XC8C7:RE,0XC8C9:REN,0XC8D3:RENG,0XC8D5:RI,0XC8D6:RONG,0XC8E0:ROU,0XC8E3:RU,0XC8ED:RUAN,0XC8EF:RUI,0XC8F2:RUN,0XC8F4:RUO,0XC8F6:SA,0XC8F9:SAI,0XC8FD:SAN,0XC9A3:SANG,0XC9A6:SAO,0XC9AA:SE,0XC9AD:SEN,0XC9AE:SENG,0XC9AF:SHA,0XC9B8:SHAI,0XC9BA:SHAN,0XC9CA:SHANG,0XC9D2:SHAO,0XC9DD:SHE,0XC9E9:SHEN,0XC9F9:SHENG,0XCAA6:SHI,0XCAD5:SHOU,0XCADF:SHU,0XCBA2:SHUA,0XCBA4:SHUAI,0XCBA8:SHUAN,0XCBAA:SHUANG,0XCBAD:SHUI,0XCBB1:SHUN,0XCBB5:SHUO,0XCBB9:SI,0XCBC9:SONG,0XCBD1:SOU,0XCBD4:SU,0XCBE1:SUAN,0XCBE4:SUI,0XCBEF:SUN,0XCBF2:SUO,0XCBFA:TA,0XCCA5:TAI,0XCCAE:TAN,0XCCC0:TANG,0XCCCD:TAO,0XCCD8:TE,0XCCD9:TENG,0XCCDD:TI,0XCCEC:TIAN,0XCCF4:TIAO,0XCCF9:TIE,0XCCFC:TING,0XCDA8:TONG,0XCDB5:TOU,0XCDB9:TU,0XCDC4:TUAN,0XCDC6:TUI,0XCDCC:TUN,0XCDCF:TUO,0XCDDA:WA,0XCDE1:WAI,0XCDE3:WAN,0XCDF4:WANG,0XCDFE:WEI,0XCEC1:WEN,0XCECB:WENG,0XCECE:WO,0XCED7:WU,0XCEF4:XI,0XCFB9:XIA,0XCFC6:XIAN,0XCFE0:XIANG,0XCFF4:XIAO,0XD0A8:XIE,0XD0BD:XIN,0XD0C7:XING,0XD0D6:XIONG,0XD0DD:XIU,0XD0E6:XU,0XD0F9:XUAN,0XD1A5:XUE,0XD1AB:XUN,0XD1B9:YA,0XD1C9:YAN,0XD1EA:YANG,0XD1FB:YAO,0XD2AC:YE,0XD2BB:YI,0XD2F0:YIN,0XD3A2:YING,0XD3B4:YO,0XD3B5:YONG,0XD3C4:YOU,0XD3D9:YU,0XD4A7:YUAN,0XD4BB:YUE,0XD4C5:YUN,0XD4D1:ZA,0XD4D4:ZAI,0XD4DB:ZAN,0XD4DF:ZANG,0XD4E2:ZAO,0XD4F0:ZE,0XD4F4:ZEI,0XD4F5:ZEN,0XD4F6:ZENG,0XD4FA:ZHA,0XD5AA:ZHAI,0XD5B0:ZHAN,0XD5C1:ZHANG,0XD5D0:ZHAO,0XD5DA:ZHE,0XD5E4:ZHEN,0XD5F4:ZHENG,0XD6A5:ZHI,0XD6D0:ZHONG,0XD6DB:ZHOU,0XD6E9:ZHU,0XD7A5:ZHUA,0XD7A7:ZHUAI,0XD7A8:ZHUAN,0XD7AE:ZHUANG,0XD7B5:ZHUI,0XD7BB:ZHUN,0XD7BD:ZHUO,0XD7C8:ZI,0XD7D7:ZONG,0XD7DE:ZOU,0XD7E2:ZU,0XD7EA:ZUAN,0XD7EC:ZUI,0XD7F0:ZUN,0XD7F2:ZUO";
        String[] s1 = pinymap.split(",");
        for (int i = 0; i < s1.length; i++) {
            String[] s2 = s1[i].split(":");
            pinyinMap.put(Integer.parseInt(s2[0].substring(2), 16), s2[1]);
        }
    }
    //todo 其他自定义方法在该类中加

    private static String htmlEncode(int i) {
        if (i == '&') return "&amp;";
        else if (i == '<') return "&lt;";
        else if (i == '>') return "&gt;";
        else if (i == '"') return "&quot;";
        else return "" + (char) i;
    }

    /**
     * 将字符串st进行html编码转换
     *
     * @param st 待处理字符串
     * @return 进行html编码处理后的字符串
     */
    public static String htmlEncode(String st) {
        if (StringUtils.isEmpty(st)) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < st.length(); i++) {
            buf.append(htmlEncode(st.charAt(i)));
        }
        return buf.toString();
    }


    private static String getSubPakage(String beanName) {
        StringBuilder sb = new StringBuilder();
        sb.append(beanName.charAt(0));
        String[] temp = beanName.split("[A-Z]");
        for (String s : temp) {
            System.out.println("s = " + s);
        }
        sb.append(temp[1]);
        return sb.toString().toLowerCase();
    }

    /**
     * str是否与正则表达式regexp匹配
     * @param str 待匹配字符串
     * @param regexp 正则表达式
     * @return true:匹配，false:不匹配
     */
    public static boolean matches(String str,String regexp){
      if(str == null)
          return false;
      else
          return str.matches(regexp);
    }

    public static String dotest() {
        return "tttt2222";
    }

    private static String g(int num) {
        if (num > 0 && num < 160) {
            return "" + (char) num;
        } else if (num < 45216 || num > 55287) {
            return "";
        } else {
            int i = num;
            for (; (!pinyinMap.containsKey(i) && i > 0);) i--;
            return pinyinMap.get(i);
        }
    }

    /**
     * 取得字符串的中午拼音，传入字符串需要转换成iso编码
     * @param str 传入字符串
     * @return String 字符串的中文拼音
     */
    public static String getPinyin(String str) {
        return getPinyin(str, " ");
    }

    /**
     * 取得字符串的中午拼音，传入字符串需要转换成iso编码
     * @param str 传入字符串
     * @param decollator 拼音的分隔符
     * @return String 字符串的中文拼音
     */
    public static String getPinyin(String str, String decollator) {
        String ret = "";
        for (int i = 0; i < str.length(); i++) {
            int p = str.charAt(i);
            if (p > 160) {
                i++;
                if (i >= str.length())
                    break;
                int q = str.charAt(i);
                p = p * 256 + q;
            }
            ret += g(p) + decollator;
        }
        return ret;
    }

    /**
     * 取得字符串的首字母,传入字符串需要转换成iso编码
     * @param str 传入字符串，
     * @return String 字符串的拼音首字母
     */
    public static String getFirstPinyin(String str){

       String[] pinyin = getPinyin(str, " ").split("\\s+");
       StringBuilder stringBuilder = new StringBuilder(pinyin.length);
        for (String s : pinyin) {
            if(s.length()>0)
            stringBuilder.append(s.charAt(0));
        }
       return stringBuilder.toString();
    }




    public static void main(String[] args) throws UnsupportedEncodingException {

        String s = "    \t\n";
        //判断一个字符串是否为null，空及空白字符
        /*
        * StringUtils.isBlank(null)      = true
        * StringUtils.isBlank("")        = true
        * StringUtils.isBlank(" ")       = true
        * StringUtils.isBlank("bob")     = false
        * StringUtils.isBlank("  bob  ") = false
        */

        s = "";
        //判断一个字符串是否为null，空
        /**
         * StringUtils.isEmpty(null)      = true
         * StringUtils.isEmpty("")        = true
         * StringUtils.isEmpty(" ")       = false
         * StringUtils.isEmpty("bob")     = false
         * StringUtils.isEmpty("  bob  ") = false
         */

        s = "123";
        //判断一个字符串是否为数字
        /**
         * StringUtils.isNumeric(null)   = false
         * StringUtils.isNumeric("")     = true
         * StringUtils.isNumeric("  ")   = false
         * StringUtils.isNumeric("123")  = true
         * StringUtils.isNumeric("12 3") = false
         * StringUtils.isNumeric("ab2c") = false
         * StringUtils.isNumeric("12-3") = false
         * StringUtils.isNumeric("12.3") = false
         */

        s = "";
        /*
       * StringUtils.isWhitespace(null)   = false
       * StringUtils.isWhitespace("")     = true
       * StringUtils.isWhitespace("  ")   = true
       * StringUtils.isWhitespace("abc")  = false
       * StringUtils.isWhitespace("ab2c") = false
       * StringUtils.isWhitespace("ab-c") = false
       * */


        System.out.println(StringUtils.left("今天是个好天气", 5));

    }

        public static String encode(String str,String decode,String encode){
        if(str == null) return null;
        try {
            if (!isEmpty(decode) && !isEmpty(encode)){
                 str = new String(str.getBytes(decode),encode);
            }else if(!isEmpty(decode) && isEmpty(encode)){
                 str = new String(str.getBytes(decode));
            }else if(isEmpty(decode) && !isEmpty(encode)){
                 str = new String(str.getBytes(),encode);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean isTrimEmpty(String s) {
        return s == null || s.length() == 0 || s.trim().length() == 0;
    }

    public static String htmlSmallEncode(String str) {
        if (str == null || str.length() == 0) return "";
        StringBuffer buffer = new StringBuffer((int) (str.length() * 1.5));
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '&':
                    buffer.append("&amp;");
                    break;
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '"':
                    buffer.append("&#34;");
                    break;
                case '\'':
                    buffer.append("&#39;");
                    break;
                default:
                    buffer.append(c);
                    break;
            }
        }
        return buffer.toString();
    }
    /**
     * @param gbk 传入字符串，
     * @return String 转换为utf-8后的
     * linux服务器上，这个方法转换有问题，中文正常，字符和数字都乱码
     */
     public static String convertString(String gbk){
          String utf8 = "";
          try {
              utf8 = new String(gbk2utf8(gbk),"UTF-8");
          } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
          }
          return utf8;
      }

      public static byte[] gbk2utf8(String chenese) {
          char c[] = chenese.toCharArray();
          byte[] fullByte = new byte[3 * c.length];
          for (int i = 0; i < c.length; i++) {
              int m = (int) c[i];
              String word = Integer.toBinaryString(m);

              StringBuffer sb = new StringBuffer();
              int len = 16 - word.length();
              for (int j = 0; j < len; j++) {
                  sb.append("0");
              }
              sb.append(word);
              sb.insert(0, "1110");
              sb.insert(8, "10");
              sb.insert(16, "10");

              String s1 = sb.substring(0, 8);
              String s2 = sb.substring(8, 16);
              String s3 = sb.substring(16);

              byte b0 = Integer.valueOf(s1, 2).byteValue();
              byte b1 = Integer.valueOf(s2, 2).byteValue();
              byte b2 = Integer.valueOf(s3, 2).byteValue();
              byte[] bf = new byte[3];
              bf[0] = b0;
              fullByte[i * 3] = bf[0];
              bf[1] = b1;
              fullByte[i * 3 + 1] = bf[1];
              bf[2] = b2;
              fullByte[i * 3 + 2] = bf[2];

          }
          return fullByte;
      }
    /**
     * 转换成jsunicode 类型
     * */
     public static String toJSUnicode(String str) {
		String tmps = getJsonFormat(str);
		String a = "";

		for (int i = 0; i < tmps.length(); i++) {
			Character ch = new Character(tmps.charAt(i));
			if (ch.hashCode() < 127) {
				a += ch;
			} else {
				String tmp = Integer.toHexString(ch.charValue()).toUpperCase();
				for (int x = tmp.length(); x < 4; x++) {
					tmp = "0" + tmp;
				}
				a += "\\u" + tmp;
			}
		}
		return a;
	}
     public static String getJsonFormat(String infomation) {
		String tmpInfo = (infomation == null) ? "" : infomation;
		try {
			tmpInfo = tmpInfo.replaceAll("\r|\n", "");
			tmpInfo = tmpInfo.replaceAll("\'", "`");
			tmpInfo = tmpInfo.replaceAll("\\\\", "\\\\\\\\");
		} catch (Exception e) {
			// System.out.println ("Error 3" + e.getMessage());
		}
		return tmpInfo;
	}
}
