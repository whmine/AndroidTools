package com.actionbarsherlock.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 删除str指定的后缀
     * @param str
     * @param suffix
     * @return
     */
    public static String removeSuffix(String str, String suffix)
            throws Exception {
        if (null == str)
            return null;
        if ("".equals(str.trim()))
            return "";

        if (null == suffix || "".equals(suffix))
            return str;

        if (str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }

        throw new Exception(str + " 没有按指定字符串" + suffix + "结尾");
    }

    /**
     * 按长度截取字符串
     * @param str
     * @param length
     * @return
     */
    public static String subString(String str, int length) {
        if (isBlank(str)) {
            return "";
        } else if (str.length() > length) {
            return str.substring(0, length);
        } else {
            return str;
        }
    }

    /**
     * 是否是空的（包括null、""、空格）
     * @param str
     * @return
     * @throws UtilException
     */
    public static boolean isBlank(String str) {
        if (null == str)
            return true;
        if ("".equals(str.trim()))
            return true;

        return false;
    }

    public static boolean isBlank(Long str) {
        if (null == str)
            return true;
        return false;
    }

    /**
     * 将对象转成String
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString().trim();
    }

    public static String add(String str, int num) {
        int i = num;
        if (!isBlank(str)) {
            int intStr = Integer.parseInt(str);
            i = i + intStr;
        }

        return Integer.toString(i);
    }

    /**
     * @param str
     * @return
     */
    public static String getString(String str) {
        if (null == str)
            return "";
        return str;

    }

    /**
     * 计算两个数字字符串的和
     * @param augend
     * @param addend
     * @return
     * @throws UtilException
     */
    public static String getSum(String augend, String second, String addend) {
        if (augend == null)
            augend = "0";
        if (second == null)
            second = "0";
        if (addend == null)
            addend = "0";
        int sum = Integer.parseInt(augend) + Integer.parseInt(second)
                + Integer.parseInt(addend);
        return new Integer(sum).toString();
    }

    public static String change(String str, int n, boolean isLeft) {
        if (str == null || str.length() >= n)
            return str;
        String s = "";
        for (int i = str.length(); i < n; i++)
            s += "0";
        if (isLeft)
            return s + str;
        else
            return str + s;
    }

    public static String getInString(String str) {
        if (str == null)
            return null;
        StringBuffer sb = new StringBuffer("");
        String s[] = str.split(",");
        if (s != null && s.length > 0) {
            for (int i = 0; i < s.length; i++) {
                if (i != 0)
                    sb.append(",");
                sb.append("'").append(s[i]).append("'");
            }
        }
        return sb.toString();
    }

    public static String subInStringByFlag(String str, String flag) {
        if (isBlank(str))
            return null;
        StringBuffer sb = new StringBuffer(str);
        int index = str.lastIndexOf(flag);
        if (index < 0) {
            return str;
        } else {
            str = sb.delete(sb.length() - flag.length(), sb.length())
                    .toString();
            index = str.indexOf(flag);
            if (index < 0) {
                return str;
            } else {
                return sb.deleteCharAt(0).toString();
            }
        }
    }

    /**
     * 根据标识获取str中最后一个flag后的内容
     * @param str
     * @param flag
     * @return
     */
    public static String getLastStr(String str, String flag) {
        if (isBlank(str))
            return null;
        int index = str.lastIndexOf(flag);
        if (index < 0) {
            return str;
        } else {
            return str.substring(index + flag.length());
        }

    }

    /**
     * 获取正则表达式匹配的字符串，将$符处理一下，不然匹配时会认作分组
     * @param str
     * @return
     */
    public static String getRegexStr(String str) {
        String ret = "";
        if (isBlank(str))
            return "";
        if (str.indexOf('$', 0) > -1) {
            while (str.length() > 0) {
                if (str.indexOf('$', 0) > -1) {
                    ret += str.subSequence(0, str.indexOf('$', 0));
                    ret += "\\$";
                    str = str.substring(str.indexOf('$', 0) + 1, str.length());
                } else {
                    ret += str;
                    str = "";
                }
            }

        } else {

            ret = str;
        }

        return ret;

    }

    /**
     * 根据正则表达式截取匹配的字符串列表 从指定字符串中，按正则表达式要求，找出其中能匹配上的字符串列表
     * @param str
     * @param regx
     * @return
     */
    public static List catchStr(String str, String regx) {
        if (isBlank(str))
            return null;

        List ret = new ArrayList();
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        int start = 0;
        int end = 0;
        while (matcher.find()) {
            start = matcher.start();
            end = matcher.end();
            ret.add(str.substring(start, end));
        }
        return ret;

    }

    /**
     * 过滤换行符
     * @param str
     * @return
     */
    public static String filterNextLine(String str) {
        if (isBlank(str))
            return "";
        return str.replaceAll("[\n\r\u0085\u2028\u2029]", "");
    }

    public static String lpad(String str, int length) {
        if (isBlank(str))
            return "";
        int j = str.length();
        for (int i = j; i < length; i++) {
            str = "0" + str;
        }
        return str;
    }

    // 打印替换 </node><node>之间的回车换行符,存储过程返回的\n不会替换
    public static String getStringFilter(String str) {
        if (str != null && !"".equals(str)) {
            Pattern p = Pattern.compile("\r\n");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            return strNoBlank;
        } else {
            return str;
        }
    }

    /**
     * 判断字符串是否为正整数
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        boolean bool = false;
        if (StringUtil.isBlank(str)) {
            return bool;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        bool = isNum.matches();
        return bool;
    }

    /**
     * 程序中有些方法的参数定义为String[]，调用时组装时很费劲 可使用java5特性 不定长度参数 来实现
     * 但目前湖北项目需考虑在java1.4下的运行，所以还不能实现。
     * @param str
     * @return
     */
    public static String[] getStringArray(String str) {
        String[] strarr = new String[1];
        strarr[0] = str;
        return strarr;
    }

    public static String[] getStringArray(List list) {
        String[] strarr = new String[list.size()];
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                strarr[i] = (String) list.get(i);
            }
        }
        return strarr;
    }

}
