package com.company.task204;

import java.io.*;
import java.util.PriorityQueue;
import java.util.TreeMap;

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

        public Node(Integer frequency, Character character) {
            this.frequency = frequency;
            this.character = character;
        }

        public Node(HuffmanTree leftChild, HuffmanTree rightChild) {
            this.frequency = leftChild.root.frequency + rightChild.root.frequency;
            this.leftChild = leftChild.root;
            this.rightChild = rightChild.root;
            leftChild.root.setParent(this);
            rightChild.root.setParent(this);
        }

        public Integer getFrequency() {
            return frequency;
        }

        public void setFrequency(Integer frequency) {
            this.frequency = frequency;
        }

        public Character getCharacter() {
            return character;
        }

        public void setCharacter(Character character) {
            this.character = character;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
            leftChild.setParent(this);
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
            rightChild.setParent(this);
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
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

    public void buildTree(int[] frequencyChar) {
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

    public static void encode(String string, TreeMap<Character, StringBuilder> codeTable) {
        try (OutputStream output = new FileOutputStream("src/com/company/task204/res")) {
            char[] charArray = string.toCharArray();
            for (char i : charArray) {
                String s = codeTable.get(i).toString();
                for (char ch : s.toCharArray())
                    output.write(ch);
            }
        } catch (IOException e) {
            System.out.print("Exception");
        }
    }

    public static void decode(TreeMap<Character, StringBuilder> codeTable) {
        StringBuilder string = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();
        try (OutputStream output = new FileOutputStream("src/com/company/task204/result.txt");
             InputStream input = new FileInputStream("src/com/company/task204/res")) {
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
                if (codeTable.containsValue(currentWord)) {
                    for (char ch : currentWord.toString().toCharArray()) {
                        output.write(ch);
                    }
                    currentWord.delete(0, currentWord.length() - 1);
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
