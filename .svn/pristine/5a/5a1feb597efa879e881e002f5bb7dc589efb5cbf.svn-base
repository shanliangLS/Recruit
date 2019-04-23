package com.recruit.util;

import com.recruit.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtil {
    public static boolean isEmail(String email) {
        if (email != null && email.length() >= 5 && email.length() <= 40) {
            String regEx = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
            // 忽略大小写的写法
            Pattern pat = Pattern.compile(regEx);
            Matcher matcher = pat.matcher(email);
            // 字符串是否与正则表达式相匹配
            return matcher.matches();
        }
        return false;
    }

    public static boolean isPassword(String password) {
        if (password != null) {
            return password.length() >= 6 && password.length() <= 20;
        }
        return false;
    }

    public static boolean isUser(String email, String password) {
        return isEmail(email) && isPassword(password);
    }

    public static boolean isUser(User user) {
        if (user != null) {
            return isUser(user.getEmail(), user.getPassword());
        }
        return false;
    }

    /**
     * @param user1
     * @param user2
     * @return
     */

    public static boolean equals(User user1, User user2) {
        if (user1 == null || user2 == null) {
            return false;
        }
        String email1 = user1.getEmail();
        String password1 = user1.getPassword();
        String email2 = user2.getEmail();
        String password2 = user2.getPassword();
        if (email1 != null && password1 != null && email2 != null && password2 != null) {
            return email1.equals(email2) && password1.equals(password2);
        }
        return false;
    }
}
