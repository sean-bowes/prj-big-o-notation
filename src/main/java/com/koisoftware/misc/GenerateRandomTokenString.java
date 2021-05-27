package com.koisoftware.misc;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateRandomTokenString {
    private ArrayList<String> tokenList = new ArrayList<String>();
    private long timeStamp = new Date().getTime();
    private long timeStampInterval = 0l;
    private int storageLength = 0;

    public void generateRandomTokenString(long timeInterval, int storeLength) {
        timeStampInterval = timeInterval;
        storageLength = storeLength;
        int len = storageLength - tokenList.size();
        for (int i = 0; i < len; i++) {
            tokenList.add(generateRandomTokenString(10));
        }
        timeStamp = new Date().getTime();
    }

    public String generateRandomTokenString(int slen) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = slen;
        //Random random = new Random();
        SecureRandom random = new SecureRandom();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }

    public List<String> getTokens(int cnt) throws Exception {
        if (tokenList.size() < cnt) {
            long tsCurr = new Date().getTime();
            long tsInterval = Long.sum(new Date().getTime(), -timeStamp);
            if (tsInterval < timeStampInterval) {
//                System.out.println("tsCurr=" + tsCurr + ",tsInterval=" + tsInterval);
//                System.out.println("Sleep:" + timeStampInterval);
                Thread.sleep(timeStampInterval);
                generateRandomTokenString(timeStampInterval, storageLength);
            }
        }
        List<String> list = new ArrayList<>();
        int nm = tokenList.size();
        for (int i = 0; i < cnt; i++) {
            if (tokenList.size() == 0) {
                break;
            }
            list.add(tokenList.remove(--nm));
        }
        return list;
    }

    public static void main(String args[]) throws Exception {
        GenerateRandomTokenString obj = new GenerateRandomTokenString();
        System.out.println("TokenString: " + obj.generateRandomTokenString(10));
        Thread.sleep(10);
        System.out.println("generateRandomTokenString");
        obj.generateRandomTokenString(5000, 10);
        //Thread.sleep(10);
        System.out.println("1-TokenString List Size: " + obj.tokenList.size());
        System.out.println("1-TokenString List Taken: " + obj.getTokens(7).toString());
        System.out.println("1-TokenString List Size: " + obj.tokenList.size());
        System.out.println("1-TokenString List Remaining: " + obj.tokenList.toString());
        Thread.sleep(40);
        System.out.println("2-TokenString List Size: " + obj.tokenList.size());
        System.out.println("2-TokenString List Taken: " + obj.getTokens(3).toString());
        System.out.println("2-TokenString List Size: " + obj.tokenList.size());
        System.out.println("2-TokenString List Remaining: " + obj.tokenList.toString());
        Thread.sleep(40);
        System.out.println("3-TokenString List Size: " + obj.tokenList.size());
        System.out.println("3-TokenString List Taken: " + obj.getTokens(2).toString());
        System.out.println("3-TokenString List Size: " + obj.tokenList.size());
        System.out.println("3-TokenString List Remaining: " + obj.tokenList.toString());
    }
}
