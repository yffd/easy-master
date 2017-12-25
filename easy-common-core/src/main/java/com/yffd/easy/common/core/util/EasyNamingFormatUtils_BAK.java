package com.yffd.easy.common.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月14日 上午11:08:02 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class EasyNamingFormatUtils_BAK {

	/**
	 * 下划线转驼峰法
	 * @Date	2017年12月14日 下午1:57:41 <br/>
	 * @author  zhangST
	 * @param lineStr		下划线源字符串
	 * @param smallCamel	大小驼峰，是否为小驼峰
	 * @param prefix		前缀字符串
	 * @param suffix		后缀字符串
	 * @return
	 */
	public static String underline2Camel(String lineStr, boolean smallCamel, String prefix, String suffix){
        if(lineStr==null || "".equals(lineStr)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(lineStr);
        while(matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if(index>0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        if(null!=suffix && !"".equals(suffix)) {
        	sb.append(suffix);
        }
        if(null!=prefix && !"".equals(prefix)) {
        	return prefix + sb.toString();
        }
        return sb.toString();
    }
	
	/**
	 * 驼峰法转下划线
	 * @Date	2017年12月14日 下午1:59:00 <br/>
	 * @author  zhangST
	 * @param camelStr		驼峰源字符串
	 * @param lower			字符串是否小写转化
	 * @param prefix		前缀字符串
	 * @param suffix		后缀字符串
	 * @return
	 */
	public static String camel2Underline(String camelStr, boolean lower, String prefix, String suffix) {
        if(camelStr==null || "".equals(camelStr)) {
            return "";
        }
        camelStr = String.valueOf(camelStr.charAt(0)).toUpperCase().concat(camelStr.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(camelStr);
        while(matcher.find()){
            String word = matcher.group();
            if(lower) {
            	sb.append(word.toLowerCase());
            } else {
            	sb.append(word.toUpperCase());
            }
            sb.append(matcher.end()==camelStr.length()?"":"_");
        }
        if(null!=suffix && !"".equals(suffix)) {
        	sb.append(suffix);
        }
        if(null!=prefix && !"".equals(prefix)) {
        	return prefix + sb.toString();
        }
        return sb.toString();
    }
	
	public static void main(String[] args) {
		String prefix = "";
		String suffix = "";
        String line = "I_HAVE_AN_IPANG3_PIG";
        String smallCamel = underline2Camel(line, true, prefix, suffix);
        String bigCamel = underline2Camel(line, false, prefix, suffix);
        System.out.println(smallCamel);
        System.out.println(bigCamel);
        System.out.println(camel2Underline(smallCamel, false, prefix, null));
        System.out.println(camel2Underline(smallCamel, true, prefix, null));
        
        System.out.println(camel2Underline("userName", false, prefix, null));
        System.out.println(camel2Underline("user_Name", false, prefix, null));
        System.out.println(underline2Camel("USER_NAME", true, prefix, null));
        System.out.println(underline2Camel("userName", true, prefix, null));
        
    }
}

