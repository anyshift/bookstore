package com.bookstore.controller.utils;

import com.bookstore.controller.dao.Impl.UserDAOImpl;
import com.bookstore.controller.servlet.service.UserService;
import com.bookstore.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtils {
    private static final UserService userService = new UserService();
    private static final UserDAOImpl userDAO = new UserDAOImpl();

    public static User getUserByUserName (String userName) {
        return userDAO.getUserByUserName(userName);
    }

    public static User getUserByUserId (int userId) {
        return userDAO.getUserByUserId(userId);
    }

    public static long addUser(User user) {
        return userDAO.addUser(user);
    }

    public static StringBuffer validateInputForm(String userName, String password) {
        StringBuffer errorInfo = new StringBuffer("");
        boolean userNameIsNullOrEmpty = userName == null || "".equals(userName.trim());
        boolean passwordIsNullOrEmpty = password == null || "".equals(password.trim());
        if (userNameIsNullOrEmpty && passwordIsNullOrEmpty) {
            errorInfo.append("请输入用户名和密码<br>");
            return errorInfo;
        }
        if (userNameIsNullOrEmpty) {
            errorInfo.append("请输入用户名<br>");
        }
        if (passwordIsNullOrEmpty) {
            errorInfo.append("请输入密码<br>");
        }
        return errorInfo;
    }

    public static StringBuffer validateInputForm(String userName, String password, String password_again) {
        StringBuffer errorInfo = new StringBuffer("");
        boolean userNameIsNullOrEmpty = userName == null || "".equals(userName.trim());
        boolean passwordIsNullOrEmpty = password == null || "".equals(password.trim());
        boolean password_again_IsNullOrEmpty = password_again == null || "".equals(password_again.trim());
        if (userNameIsNullOrEmpty && passwordIsNullOrEmpty && password_again_IsNullOrEmpty) {
            errorInfo.append("请输入用户名和密码<br>");
            return errorInfo;
        }
        if (userNameIsNullOrEmpty) {
            errorInfo.append("请输入用户名<br>");
        } else { //如果用户名不空
            if (passwordIsNullOrEmpty && password_again_IsNullOrEmpty) { //两个密码都为空
                errorInfo.append("请输入密码<br>");
            } else {
                assert password != null;
                assert password_again != null;
                if (!password.trim().equals(password_again.trim())) {
                    errorInfo.append("两次密码输入不一致，请重新输入<br>");
                }
            }
        }
        return errorInfo;
    }

    public static StringBuffer validateUsernameUnique(String username) {
        StringBuffer errorInfo = new StringBuffer("");
        User user = userDAO.getUserByUserName(username);
        if (user != null) {
            errorInfo.append("用户名已存在，请重新输入用户名<br>");
        }
        return errorInfo;
    }

    public static StringBuffer validateInputPasswordSecure(String password) {
        StringBuffer errorInfo = new StringBuffer("");
        if (!checkPasswordSecure(password)) {
            errorInfo.append("密码必须包含「数字、大小写字母」且密码位数需 8～16位<br>");
        }
        return errorInfo;
    }

    public static boolean checkPasswordSecure(String password) {
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static StringBuffer validateInputPassword(User user, String password) {
        StringBuffer errorInfo = new StringBuffer("");
        String needValidatePassword = encodePassword(password);
        String realPassword = user.getPassword();
        if (!needValidatePassword.trim().equals(realPassword)) {
            errorInfo.append("账号与密码不匹配，请重新输入<br>");
        }
        return errorInfo;
    }

    /**
     * 对于任意长度的消息，SHA256都会产生一个256位的哈希值，称作消息摘要。
     * 这个摘要相当于是个长度为32个字节的数组，通常由一个长度为64的十六进制字符串来表示，其中1个字节=8位，一个十六进制的字符的长度为4位。
     * @param password
     * @return SHA-256加密算法生成的结果
     */
    public static String encodePassword(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = messageDigest.digest(password.getBytes()); //生成32位
        return byte2Hex(digest);
    }

    /**
     * byte 转 16进制
     * @param bytes
     * @return
     */
    public static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) { //最后一位补0
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

}
