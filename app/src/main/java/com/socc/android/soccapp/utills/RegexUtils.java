package com.socc.android.soccapp.utills;

import java.util.regex.Pattern;

/**
 * Created by ksang on 2017-05-05.
 */
public class RegexUtils {
    final static String pattenNumber = "(.*)[0-9](.*)";
    final static String patternAlpha = "(.*)[a-zA-Z](.*)";
    final static String patternSpecial = "(.*)[/`~!_@#$%()<>,.;:-=&\\^\\[\\]\\{\\}[*][+][|][?]](.*)";
    final static String patternBlank = "(.*)[\\s](.*)";
    final static String patternHangul = "(.*)[ㄱ-ㅎㅏ-ㅣ가-힣](.*)";
    final static String patternEmail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
    final static String patternPwd = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,16}$";

    public static boolean CheckNumber(String str){
        return Pattern.matches(pattenNumber,str);
    }
    public static boolean CheckAlpha(String str){
        return Pattern.matches(patternAlpha,str);
    }
    public static boolean CheckSpecial(String str){
        return Pattern.matches(patternSpecial,str);
    }
    public static boolean CheckBlank(String str){
        return Pattern.matches(patternBlank,str);
    }
    public static boolean CheckHangul(String str){
        return Pattern.matches(patternHangul,str);
    }
    public static boolean CheckEmail(String str){
        return Pattern.matches(patternEmail,str);
    }
    public static boolean CheckPwd(String str){
        return Pattern.matches(patternPwd,str);
    }
}
