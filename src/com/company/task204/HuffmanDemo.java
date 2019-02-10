package com.company.task204;

import java.io.*;
import java.util.Set;
import java.util.TreeMap;

public class HuffmanDemo {
    public static void main(String[] args) {
        int[] frequencyChar = new int[256];
        StringBuilder string = new StringBuilder();
        try (InputStream input = new FileInputStream("src/com/company/task204/original.txt")) {
            int size = input.available();
            for (int i = 0; i < size; i++) {
                string.append((char) input.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Exception");
        }
        for (char c : string.toString().toCharArray()) {
            frequencyChar[c]++;
        }

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.buildTree(frequencyChar);
        huffmanTree.traverseTree();
        TreeMap<Character, StringBuilder> codeTable = huffmanTree.codeTable();
        Set<Character> set = codeTable.keySet();

        // Display the elements.
        for (Character me : set) {
            System.out.print(me + ": ");
            System.out.println(codeTable.get(me));
        }
        System.out.println();

        HuffmanTree.encode(string.toString(), codeTable);
        HuffmanTree.decode(codeTable);
    }
}
