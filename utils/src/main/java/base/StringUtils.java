package base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数值类型判断 ，输入字符串判断是否为数值类型
 * Created by
 * jlgaoyuan on 2017/9/1.
 */
public class StringUtils {


    /**
     * 判断是否为数字
     *
     * @param str string
     * @return boolean
     */
    public boolean isNumeric(String str) {
        boolean isInt = isFormat(str, Regex.REGEX_INT);
        boolean isDouble = isFormat(str, Regex.REGEX_DOUBLE);
        return isInt || isDouble;
    }

    /**
     * 判断字符串是不是null或无字符（trim后）
     *
     * @param str str
     * @return boolean
     */
    public boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * 正则表达式规则匹配
     *
     * @param str    字符串
     * @param format 格式
     * @return boolean
     */
    private boolean isFormat(String str, String format) {
        return str.matches(format);
    }


    /**
     * 判断数值是否在范围内
     *
     * @param start  起始数值
     * @param end    结束数值
     * @param number 数值
     * @return boolean
     */
    public boolean numberScope(long start, long end, long number) {
        return number > start && number < end;
    }


    /**
     * 判断数值是否在范围内
     *
     * @param start  起始数值
     * @param end    结束数值
     * @param number 数值
     * @return boolean
     */
    public boolean numberScope(double start, double end, double number) {
        return number >= start && number <= end;
    }

    /**
     * 统计指定字符在字符串中的数量
     *
     * @param str String
     * @return 字符在字符串中的数量
     */
    public int includeCharCount(String str) {
        char ch[] = str.toCharArray();
        int count = 0;
        for (char aCh : ch) {
            if (';' == aCh) {
                count++;
            }
        }
        return count;
    }

    // 判断一个字符串是否都为数字
    public boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }

    // 判断一个字符串是否都为数字
    public boolean isDigitPattern(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(strNum);
        return matcher.matches();
    }

    //截取数字
    public String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 截取非数字
    public String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    // 判断一个字符串是否含有数字
    public boolean hasInDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }


//java中判断字符串是否为数字的三种方法 ：

//1.用JAVA自带的函数

    public static boolean isNumericJava(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


//2.用正则表达式

    public static boolean isNumericPattern(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }


    //3.用ascii码
    public static boolean isNumericAscii(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57)
                return false;
        }
        return true;
    }

    /**
     * 判断字符串中第一个字母或数字的位置
     * @param str 字符串
     * @return index
     */
    public int indexForLetterOrDigit(String str){
        for(int i=0 ; i<str.length() ; i++) { //循环遍历字符串
            if (Character.isDigit(str.charAt(i))) {     //用char包装类中的判断数字的方法判断每一个字符
                return i;
            }
            if (Character.isLetter(str.charAt(i))) {   //用char包装类中的判断字母的方法判断每一个字符
                return i;
            }
        }
        return 0;
    }

}
