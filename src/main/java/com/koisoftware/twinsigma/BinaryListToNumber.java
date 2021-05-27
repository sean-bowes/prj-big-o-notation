package com.koisoftware.twinsigma;

import java.io.BufferedWriter;
import java.io.IOException;


public class BinaryListToNumber {
    private class SinglyLinkedListNode {
        public long data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(long nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    private class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(long nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);
            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }
            this.tail = node;
        }
    }

    class SinglyLinkedListPrintHelper {
        public void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
            while (node != null) {
                bufferedWriter.write(String.valueOf(node.data));
                node = node.next;
                if (node != null) {
                    bufferedWriter.write(sep);
                }
            }
        }
    }

    class Result {
        public long getNumber(SinglyLinkedListNode binary) {
            // A binary number is represented as a series of 0's and 1's.
            // In this challenge, the series will be in the form of a singly-linked list.
            // Each node instance, a LinkedListNode, has a value, data,
            // and a pointer to the next node, next. Given a reference to the head of a
            // singly-linked list, convert the binary number represented to a decimal number.
            long nm = binary.data;
            while (binary.next != null) {
                nm = (nm << 1) | binary.next.data;
                binary = binary.next;
            }
            return nm;
        }
    }

    public Integer convertBinaryToDecimal(Integer binaryNumber) {

        Integer decimalNumber = 0;
        Integer base = 1;

        while (binaryNumber > 0) {
            int lastDigit = binaryNumber % 10;
            binaryNumber = binaryNumber / 10;
            decimalNumber += lastDigit * base;
            base = base * 2;
        }
        return decimalNumber;
    }

    public static void main(String[] args) throws IOException {
        BinaryListToNumber obj = new BinaryListToNumber();
        long[] arr1 = {0, 0, 1, 1, 0, 1, 0};
        //ans 26
        int[] arr2 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        //ans 31
        int[] arr3 = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        //ans 9223372036854775807
        SinglyLinkedList binary1 = obj.new SinglyLinkedList();
        for (long i : arr1) {
            binary1.insertNode(i);
        }
        long result = obj.new Result().getNumber(binary1.head);
        System.out.println("result=" + String.valueOf(result));
        // ###################################
        SinglyLinkedList binary2 = obj.new SinglyLinkedList();
        for (long i : arr2) {
            binary2.insertNode(i);
        }
        result = obj.new Result().getNumber(binary2.head);
        System.out.println("result=" + String.valueOf(result));
        // ###################################
        SinglyLinkedList binary3 = obj.new SinglyLinkedList();
        for (long i : arr3) {
            binary3.insertNode(i);
        }
        result = obj.new Result().getNumber(binary3.head);
        System.out.println("result=" + String.valueOf(result));
        // ###################################
    }

}
