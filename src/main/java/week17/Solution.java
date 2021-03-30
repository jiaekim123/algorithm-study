package week17;
/*
https://programmers.co.kr/learn/courses/30/lessons/72411
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution {
    String[] array;

    public String[] solution(String[] orders, int[] course) {
        HashSet<String> set = new HashSet<>();
        for (String o : orders) {
            String[] arr = o.split("");
            for (String a : arr) {
                set.add(a);
            }
        }
        array = set.stream().toArray(String[]::new); // stream 문법
        String ans = "";
        for (int c : course) {
            ArrayList<String> list = new ArrayList<>();
            combi(c, "", 0, list);
            if (!ans.equals("")) ans += " ";
            ans += solve(orders, list);

        }
        String[] answer = ans.split(" ");
        Arrays.sort(answer);
        return answer;
    }

    // 글자수 n 개인 조합
    public void combi(int n, String ret, int start, List<String> list) {
        if (ret.length() == n) {
            list.add(ret);
            return;
        }

        for (int i = start; i < array.length; i++) {
            combi(n, ret + array[i], i + 1, list);
        }
    }

    // orders 를 순회하면서 어떤 문자 조합이 몇번 반복되는지 확인 후 최대
    public String solve(String[] orders, List<String> menus) {
        int[] menu_count = new int[menus.size()];
        int max = 1;
        String menu = "";
        for (int i = 0; i < menus.size(); i++) {
            for (String order : orders) {
                if (check(order, menus.get(i))) menu_count[i]++;
            }
            if (max < menu_count[i]) {
                max = menu_count[i];
                menu = menus.get(i);
            } else if (max != 1 && max == menu_count[i]) {
                menu += " " + menus.get(i);
            }
        }
        return menu;
    }

    public boolean check(String order, String menu) {
        String[] menu_arr = menu.split("");
        if (menu.length() > order.length()) return false;
        for (String m : menu_arr)
            if (!order.contains(m)) return false;
        return true;
    }
}
