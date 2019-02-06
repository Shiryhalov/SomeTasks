package com.company.task202;

public class RedBlackTreeDemo {
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.addNode(6, "6");
        redBlackTree.traverseTree();
        System.out.println();
        redBlackTree.addNode(1, "1");
        redBlackTree.traverseTree();
        System.out.println();
        redBlackTree.addNode(8, "8");
        redBlackTree.traverseTree();
        System.out.println();
        redBlackTree.addNode(7, "7");
        redBlackTree.traverseTree();
        System.out.println();
        redBlackTree.addNode(5, "5");
        redBlackTree.traverseTree();
        System.out.println();
        redBlackTree.addNode(4, "4");
        redBlackTree.traverseTree();
        System.out.println();
        redBlackTree.addNode(2, "2");
        redBlackTree.traverseTree();
        System.out.println();
        redBlackTree.addNode(3, "3");
        redBlackTree.traverseTree();
        System.out.println();
        redBlackTree.traverseTree();
        System.out.println("Root: " + redBlackTree.getRoot());
    }
}
