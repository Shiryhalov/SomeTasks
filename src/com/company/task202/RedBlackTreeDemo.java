package com.company.task202;

public class RedBlackTreeDemo {
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.addNode(9, "9");
        redBlackTree.addNode(1, "1");
        redBlackTree.addNode(8, "8");
        redBlackTree.addNode(7, "7");
        redBlackTree.addNode(5, "5");
        redBlackTree.addNode(4, "4");
        redBlackTree.addNode(2, "2");
        redBlackTree.addNode(3, "3");
        redBlackTree.addNode(6, "6");
        redBlackTree.addNode(10, "10");
        redBlackTree.addNode(4, "4");
        redBlackTree.traverseTree();
        System.out.println("Root: " + redBlackTree.getRoot());
    }
}
