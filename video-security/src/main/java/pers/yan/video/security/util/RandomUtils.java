package pers.yan.video.security.util;

import java.util.Random;

/**
 * @author likaiyan
 * @date 2020/9/29 2:56 下午
 */
public class RandomUtils {

    public static final String CHARS = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

    /**
     * 获取随机数字字符串
     *
     * @param length 长度
     * @return 数字字符串
     */
    public static String getRandomDigits(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 获取随机字母字符串
     * @param length 长度
     * @return 字母字符串
     */
    public static String getRandomChars(int length){
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char)('a' + random.nextInt(26)));
        }
        return sb.toString();
    }

    /**
     * 获取随机字符串
     * @param length 长度
     * @return 随机字符串
     */
    public static String getRandomCode(int length){
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

}
