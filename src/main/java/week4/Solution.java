/*
    https://programmers.co.kr/learn/courses/30/lessons/17686
    [프로그래머스][2018 KAKAO BLINE RECRUITMENT] 파일명 정렬 문제
 */
package week4;

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<File> fileList = new ArrayList<>();
        for (String file : files){
            fileList.add(new File(file));
        }

        Collections.sort(fileList);

        for (int i = 0; i<fileList.size(); i++){
            answer[i] = fileList.get(i).fileName;
        }
        return answer;
    }
}
class File implements Comparable<File> {
    String fileName;
    String head;
    String number;
    String tail;
    public File(String fileName){
        this.fileName = fileName;
        splitFileName(fileName.toCharArray());
    }
    private void splitFileName(char[] fileName){
        StringBuilder headBuilder = new StringBuilder();
        int num = 0;
        for (int i = num; i<fileName.length; i++, num++){
            if (isNumber(fileName[i])) break;
            headBuilder.append(fileName[i]);
        }
        this.head = headBuilder.toString().toLowerCase();

        StringBuilder numberBuilder = new StringBuilder();
        for (int i = num; i<fileName.length; i++, num++){
            if(!isNumber(fileName[i])) break;
            numberBuilder.append(fileName[i]);
        }
        this.number = numberBuilder.toString();

        StringBuilder tailBuilder = new StringBuilder();
        for (int i = num; i<fileName.length; i++){
            tailBuilder.append(fileName[i]);
        }
        this.tail = tailBuilder.toString();
    }
    private boolean isNumber(char comp){
        int ZERO = (int)'0';
        int NINE = (int)'9';
        if (ZERO <= (int) comp && (int) comp<= NINE) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(File file) {
        if (this.head.equals(file.head)){
            return Integer.parseInt(this.number) - Integer.parseInt(file.number);
        } else {
            return this.head.compareTo(file.head);
        }
    }
}