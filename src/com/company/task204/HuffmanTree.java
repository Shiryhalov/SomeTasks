package com.company.task204;

import java.io.*;
import java.util.*;

public class HuffmanTree implements Comparable<HuffmanTree> {
    Node root;

    public HuffmanTree() {
    }

    public HuffmanTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private static class Node {
        private Integer frequency;
        private Character character;
        private Node leftChild;
        private Node rightChild;
        private Node parent;

        Node(Integer frequency, Character character) {
            this.frequency = frequency;
            this.character = character;
        }

        Node(HuffmanTree leftChild, HuffmanTree rightChild) {
            this.frequency = leftChild.root.frequency + rightChild.root.frequency;
            this.leftChild = leftChild.root;
            this.rightChild = rightChild.root;
            leftChild.root.setParent(this);
            rightChild.root.setParent(this);
        }

        Integer getFrequency() {
            return frequency;
        }

        public void setFrequency(Integer frequency) {
            this.frequency = frequency;
        }

        Character getCharacter() {
            return character;
        }

        public void setCharacter(Character character) {
            this.character = character;
        }

        Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
            leftChild.setParent(this);
        }

        Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
            rightChild.setParent(this);
        }

        Node getParent() {
            return parent;
        }

        void setParent(Node parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "frequency=" + frequency +
                    ", character=" + character +
                    '}';
        }
    }

    private static int[] frequencyCounter(String inputName) {
        int[] frequencyChar = new int[256];
        StringBuilder string = new StringBuilder();
        try (InputStream input = new FileInputStream(inputName)) {
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
        return frequencyChar;
    }

    public void buildTree(String inputName) {
        int[] frequencyChar = frequencyCounter(inputName);
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();
        for (int i = 0; i < frequencyChar.length; i++) {
            if (frequencyChar[i] > 0) {
                trees.offer(new HuffmanTree(new Node(frequencyChar[i], (char) i)));
            }
        }
        while (trees.size() > 1) {
            HuffmanTree tree1 = trees.poll();
            HuffmanTree tree2 = trees.poll();
            trees.offer(new HuffmanTree(new Node(tree1, tree2)));
        }
        setRoot(trees.peek().getRoot());
    }

    public TreeMap<Character, StringBuilder> codeTable() {
        TreeMap<Character, StringBuilder> codeTable = new TreeMap<>();
        codeTable.put(' ', new StringBuilder());
        encodeTree(root, codeTable);
        codeTable.remove(' ');
        return codeTable;
    }

    private void encodeTree(Node currentNode, TreeMap<Character, StringBuilder> codeTable) {
        if (currentNode.getCharacter() != null) {
            StringBuilder sb = new StringBuilder(codeTable.get(' ').toString());
            codeTable.put(currentNode.getCharacter(), sb);
        } else {
            codeTable.put(' ', codeTable.get(' ').append("0"));
            encodeTree(currentNode.getLeftChild(), codeTable);
            codeTable.get(' ').deleteCharAt(codeTable.get(' ').length() - 1);
            codeTable.put(' ', codeTable.get(' ').append("1"));
            encodeTree(currentNode.getRightChild(), codeTable);
            codeTable.get(' ').deleteCharAt(codeTable.get(' ').length() - 1);
        }
    }

    public static void encode(String inputName, TreeMap<Character, StringBuilder> codeTable) {
        StringBuilder string = new StringBuilder();
        try (InputStream input = new FileInputStream(inputName);
             OutputStream output = new FileOutputStream("src/com/company/task204/res")) {
            int size = input.available();
            for (int i = 0; i < size; i++) {
                string.append((char) input.read());
            }
            char[] charArray = string.toString().toCharArray();
            for (char i : charArray) {
                String s = codeTable.get(i).toString();
                for (char ch : s.toCharArray())
                    output.write(ch);
            }
        } catch (IOException e) {
            System.out.print("Exception");
        }
    }

    public static void decode(String inputName, TreeMap<Character, StringBuilder> codeTable) {
        StringBuilder string = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();
        boolean flag = false;
        try (OutputStream output = new FileOutputStream("src/com/company/task204/result.txt");
             InputStream input = new FileInputStream(inputName)) {
            int size = input.available();
            for (int i = 0; i < size; i++) {
                string.append((char) input.read());
            }
            for (char c : string.toString().toCharArray()) {
                if (c == 48) {
                    currentWord.append("0");
                } else {
                    currentWord.append("1");
                }
                Set<Map.Entry<Character, StringBuilder>> set = codeTable.entrySet();
                for (Map.Entry<Character, StringBuilder> me : set) {
                    if (me.getValue().toString().equals(currentWord.toString())) {
                        output.write(me.getKey());
                        flag = true;
                    }
                }
                if (flag) {
                    currentWord.delete(0, currentWord.length());
                    flag = false;
                }
            }

        } catch (IOException e) {
            System.out.print("Exception");
        }
    }

    private void traverseTree(Node currentNode) {
        if (currentNode != null) {
            traverseTree(currentNode.getLeftChild());
            if (currentNode.getParent() != null) {
                System.out.println(currentNode.getFrequency() + ", parent: " + currentNode.getParent().getFrequency());
            } else {
                System.out.println(currentNode.getFrequency() + ", no parent ");
            }
            traverseTree(currentNode.getRightChild());
        }
    }

    public void traverseTree() {
        traverseTree(root);
    }

    @Override
    public String toString() {
        return root.frequency.toString();
    }

    @Override
    public int compareTo(HuffmanTree o) {
        return root.frequency - o.root.frequency;
    }
}
