package com.company.task202;

/**
 * @author Dmitry Shiryhalov
 * @version 1.0
 * on 03.02.2019.
 */

public class RedBlackTree {
    /**
     * Класс для представления узла
     */
    public static class Node {
        /**
         * Ключ
         */
        private int key;
        /**
         * Значение
         */
        private String value;
        /**
         * Бит цвета узла, false - черный, true - красный
         */
        private boolean nodeColor;
        /**
         * Родитель
         */
        private Node parent;
        /**
         * Левый потомок
         */
        private Node leftChild;
        /**
         * Правый потомок
         */
        private Node rightChild;

        /**
         * Конструктор для узла
         *
         * @param key   ключ
         * @param value значение
         */
        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Конструктор для узла
         *
         * @param parent    родитель
         * @param nodeColor цвет узла
         */
        Node(Node parent, boolean nodeColor) {
            this.parent = parent;
            this.nodeColor = nodeColor;
        }

        /**
         * Get-еры, Set-еры:
         */
        int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        boolean isNodeColor() {
            return nodeColor;
        }

        void setNodeColor(boolean nodeColor) {
            this.nodeColor = nodeColor;
        }

        void makeItBlack() {
            this.nodeColor = false;
        }

        void makeItRed() {
            this.nodeColor = true;
        }

        Node getParent() {
            return parent;
        }

        void setParent(Node parent) {
            this.parent = parent;
        }

        Node getLeftChild() {
            return leftChild;
        }

        void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        Node getRightChild() {
            return rightChild;
        }

        void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        Node getGrandfather() {
            if (getParent() != null) {
                return getParent().getParent();
            } else {
                return null;
            }
        }

        Node getUncle() {
            Node grandfather = getGrandfather();
            if (grandfather != null) {
                if (grandfather.getLeftChild() == getParent() && grandfather.getRightChild().value != null) {
                    return grandfather.getRightChild();
                } else if (grandfather.getRightChild() == getParent() && grandfather.getLeftChild().value != null) {
                    return grandfather.getLeftChild();
                } else return null;
            }
            return null;
        }

        /**
         * toString для узла
         */
        @Override
        public String toString() {
            return "Key: " + key + ", Value: " + value;
        }
    }

    private Node root;

    /**
     * Добавление узла в дерево
     *
     * @param key   ключ
     * @param value значение
     */
    public void addNode(int key, String value) {
        Node newNode = new Node(key, value);
        //Для первого узла
        if (root == null) {
            newNode.makeItBlack();
            newNode.setParent(null);
            newNode.setLeftChild(new Node(newNode, false));
            newNode.setRightChild(new Node(newNode, false));
            root = newNode;
        } else { // Не для первого узла
            Node currentNode = root;
            Node parent;
            while (true) {
                parent = currentNode;
                //Проверка ключей
                if (key < currentNode.getKey()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode.getValue() == null) {
                        newNode.makeItRed();
                        newNode.setLeftChild(new Node(currentNode, false));
                        newNode.setRightChild(new Node(currentNode, false));
                        newNode.setParent(parent);
                        parent.setLeftChild(newNode);
                        fixInsertion(parent.getLeftChild());
                        return;
                    }
                } else if (key > currentNode.getKey()) {
                    currentNode = currentNode.getRightChild();
                    if (currentNode.getValue() == null) {
                        newNode.makeItRed();
                        newNode.setLeftChild(new Node(currentNode, false));
                        newNode.setRightChild(new Node(currentNode, false));
                        newNode.setParent(parent);
                        parent.setRightChild(newNode);
                        fixInsertion(parent.getRightChild());
                        return;
                    }
                } else {
                    System.out.println("Невозможна вставка элемента с таким key, ибо он уже существует");
                    return;
                }
            }
        }
    }

    /**
     * Исправление дерева
     *
     * @param node узел
     */
    private void fixInsertion(Node node) {
        if (node == root) {
            node.makeItBlack();
            return;
        }
        Node temp;
        while (node.getParent() != null && node.getParent().isNodeColor()) {
            if (node.getGrandfather().getLeftChild() == node.getParent()) {
                temp = node.getGrandfather().getRightChild();
                if (temp.isNodeColor()) {
                    temp.makeItBlack();
                    node.getParent().makeItBlack();
                    node.getGrandfather().makeItRed();
                    node = node.getGrandfather();
                } else {
                    if (node == node.getParent().getRightChild()) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().makeItBlack();
                    node.getGrandfather().makeItRed();
                    rightRotate(node.getGrandfather());
                }
            } else {
                temp = node.getGrandfather().getLeftChild();
                if (temp.isNodeColor()) {
                    temp.makeItBlack();
                    node.getParent().makeItBlack();
                    node.getGrandfather().makeItRed();
                    node = node.getGrandfather();
                } else {
                    if (node == node.getParent().getLeftChild()) {
                        node = node.getParent();
                        rightRotate(node);
                    }
                    node.getParent().makeItBlack();
                    node.getGrandfather().makeItRed();
                    leftRotate(node.getGrandfather());
                }
            }
        }
        root.makeItBlack();
    }

    /**
     * Левый поворот дерева
     *
     * @param node добавленный узел
     */
    private void leftRotate(Node node) {
        Node nodeParent = node.getParent();
        Node nodeRight = node.getRightChild();
        if (nodeParent != null && nodeParent.getValue() != null) {
            if (nodeParent.getLeftChild() == node) {
                nodeParent.setLeftChild(nodeRight);
                nodeRight.setParent(nodeParent);
            } else {
                nodeParent.setRightChild(nodeRight);
                nodeRight.setParent(nodeParent);
            }
        } else {
            root = nodeRight;
            root.setParent(null);
        }
        node.setRightChild(nodeRight.getLeftChild());
        nodeRight.getLeftChild().setParent(node);
        nodeRight.setLeftChild(node);
        node.setParent(nodeRight);
    }

    /**
     * Правый поворот дерева
     *
     * @param node добавленный узел
     */
    private void rightRotate(Node node) {
        Node nodeParent = node.getParent();
        Node nodeLeft = node.getLeftChild();
        if (nodeParent != null && nodeParent.getValue() != null) {
            if (nodeParent.getLeftChild() == node) {
                nodeParent.setLeftChild(nodeLeft);
                nodeLeft.setParent(nodeParent);
            } else {
                nodeParent.setRightChild(nodeLeft);
                nodeLeft.setParent(nodeParent);
            }
        } else {
            root = nodeLeft;
            root.setParent(null);
        }
        node.setLeftChild(nodeLeft.getRightChild());
        nodeLeft.getRightChild().setParent(node);
        nodeLeft.setRightChild(node);
        node.setParent(nodeLeft);
    }

    /**
     * Вывод дерева
     *
     * @param currentNode current node
     */
    private void traverseTree(Node currentNode) {
        if (currentNode.getLeftChild() != null && currentNode.getRightChild() != null) {
            traverseTree(currentNode.getLeftChild());
            System.out.println(currentNode + " " + currentNode.isNodeColor() + ", родитель:" +
                    currentNode.getParent());
            traverseTree(currentNode.getRightChild());
        }
    }

    public void traverseTree() {
        traverseTree(root);
    }

    /**
     * Get-ер для корня дерева
     */
    public Node getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "RedBlackTree{" +
                "root=" + root +
                '}';
    }
}
