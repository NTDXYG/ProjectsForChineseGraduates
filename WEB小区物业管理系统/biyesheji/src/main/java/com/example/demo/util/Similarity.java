package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

public class Similarity {
    /*
    first与second的相似度+second与first的相似度  /  2
     */
    public static double getSimilarity(String phrase1, String phrase2) {
        return (getSC(phrase1, phrase2) + getSC(phrase2, phrase1)) / 2.0;
    }

    /*
    输入：first字符串；second字符串；下标pos
    处理：first[pos]在second字符串中出现的次数
    输出：返回一个出现在second字符串的哪几个下标的列表
     */
    private static List<Integer> getC(String first, String second, int pos) {
        List<Integer> results = new ArrayList<Integer>();
        char ch = first.charAt(pos);
        for (int i = 0; i < second.length(); i++) {
            if (ch == second.charAt(i)) {
                results.add(i);
            }
        }
        return results;
    }

    /*
    输入：first字符串；second字符串；下标pos
    处理：遍历first[pos]在second中出现的下标，计算其和pos的距离
    输出：返回距离d
     */
    private static int getDistance(String first, String second, int pos) {
        int d = second.length();
        for (int k : getC(first, second, pos)) {
            int value = Math.abs(k - pos);
            if (d > value) {
                d = value;
            }
        }
        return d;
    }

    /*
    输入：first字符串；second字符串；下标pos
    处理：second的长度-距离d，再除以second的长度
    输出：返回first[pos]与second的相似度
     */
    private static double getCC(String first, String second, int pos) {
        return (second.length() - getDistance(first, second, pos)) * 1.0 / second.length();
    }

    /*
    输入：first字符串；second字符串
    处理：遍历first，分别对second进行相似度计算
    输出：返回first与second的相似度
     */
    private static double getSC(String first, String second) {
        double total = 0.0;
        for (int i = 0; i < first.length(); i++) {
            total = total + getCC(first, second, i);
        }
        return total / first.length();
    }
}
