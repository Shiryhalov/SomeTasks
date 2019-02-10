package com.company.task204;

import java.util.Set;
import java.util.TreeMap;

public class HuffmanDemo {
    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.buildTree("src/com/company/task204/original.txt");
        huffmanTree.traverseTree();
        System.out.println();
        TreeMap<Character, StringBuilder> codeTable = huffmanTree.codeTable();
        Set<Character> set = codeTable.keySet();

        for (Character me : set) {
            System.out.print(me + ": ");
            System.out.println(codeTable.get(me));
        }

        HuffmanTree.encode("src/com/company/task204/original.txt", codeTable);
        HuffmanTree.decode("src/com/company/task204/res", codeTable);
    }
}
