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
         * Конструктор по умолчанию
         */
        public Node() {
        }

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

        Node(Node parent, boolean nodeColor) {
            this.parent = parent;
            this.nodeColor = nodeColor;
        }

        Node(int key, String value, boolean nodeColor, Node leftChild, Node rightChild) {
            this.key = key;
            this.value = value;
            this.nodeColor = nodeColor;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        /**
         * Конструктор для узла
         *
         * @param key        ключ
         * @param value      значение
         * @param nodeColor  цвет узла
         * @param parent     родитель
         * @param leftChild  левый потомок
         * @param rightChild правый потомок
         */
        Node(int key, String value, boolean nodeColor, Node parent, Node leftChild, Node rightChild) {
            this.key = key;
            this.value = value;
            this.nodeColor = nodeColor;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isNodeColor() {
            return nodeColor;
        }

        public void setNodeColor(boolean nodeColor) {
            this.nodeColor = nodeColor;
        }

        void makeItBlack() {
            this.nodeColor = false;
        }

        void makeItRed() {
            this.nodeColor = true;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getGrandfather() {
            if (getParent() != null) {
                return getParent().getParent();
            } else {
                return null;
            }
        }

        public Node getUncle() {
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

        public void setUncleColor(boolean color) {
            getUncle().setNodeColor(color);
        }

        public void setGrandfatherColor(boolean color) {
            getGrandfather().setNodeColor(color);
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

    public void addNode(int key, String value) {
        Node newNode = new Node(key, value);
        //Для первого узла
        if (root == null) {
            newNode.makeItBlack();
            newNode.setParent(null);
            newNode.setLeftChild(new Node(newNode, false));
            newNode.setRightChild(new Node(newNode, false));
            root = newNode;
            return;
        } else { // Не для первого узла
            Node currentNode = root;
            Node parent;
            while (true) {
                parent = currentNode;
                //Проверка ключей
                if (key < currentNode.getKey()) {
                    currentNode = currentNode.leftChild;
                    if (currentNode.getValue() == null) {
                        newNode.setNodeColor(true);
                        newNode.setLeftChild(new Node(currentNode, false));
                        newNode.setRightChild(new Node(currentNode, false));
                        parent.setLeftChild(newNode);
                        parent.leftChild.setParent(parent);
                        fixInsertion(parent.getLeftChild());
                        return;
                    }
                } else if (key > currentNode.getKey()) {
                    currentNode = currentNode.rightChild;
                    if (currentNode.getValue() == null) {
                        newNode.setNodeColor(true);
                        newNode.setLeftChild(new Node(currentNode, false));
                        newNode.setRightChild(new Node(currentNode, false));
                        parent.setRightChild(newNode);
                        parent.rightChild.setParent(parent);
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

    private void fixInsertion(Node t) {
        if (t == root) {
            t.makeItBlack();
            return;
        }
        while (t.getParent() != null && t.getParent().isNodeColor()) {
            if (t.getGrandfather().getLeftChild() == t.getParent()) {
                if (t.getUncle() != null) {
                    if (t.getUncle().isNodeColor()) {
                        t.getParent().setNodeColor(false);
                        t.setUncleColor(false);
                        t.setGrandfatherColor(true);
                        t = t.getGrandfather();
                    }
                } else {
                    if (t == t.getParent().getRightChild()) {
                        t = t.getParent();
                        leftRotate(t);
                    }
                    t.getParent().setNodeColor(false);
                    t.setGrandfatherColor(true);
                    rightRotate(t.getGrandfather());
                }
            } else {
                if (t.getUncle() != null) {
                    if (t.getUncle().isNodeColor()) {
                        t.getParent().setNodeColor(false);
                        t.setUncleColor(false);
                        t.setGrandfatherColor(true);
                        t = t.getGrandfather();
                    }
                } else {
                    if (t == t.getParent().getRightChild()) {
                        t = t.getParent();
                        rightRotate(t);
                    }
                    t.getParent().setNodeColor(false);
                    t.setGrandfatherColor(true);
                    leftRotate(t.getGrandfather());
                }
            }
        }
        root.setNodeColor(false);
    }

    private void leftRotate(Node node) {
        Node nodeParent = node.getParent();
        Node nodeRight = node.getRightChild();
        if (nodeParent != null) {
            if (nodeParent.getLeftChild() == node) {
                nodeParent.setLeftChild(nodeRight);
            } else {
                nodeParent.setRightChild(nodeRight);
            }
        } else {
            root = nodeRight;
            root.setParent(null);
        }
        node.setRightChild(nodeRight.getLeftChild());
        nodeRight.setLeftChild(node);
    }

    private void rightRotate(Node node) {
        Node nodeParent = node.getParent();
        Node nodeLeft = node.getLeftChild();
        if (nodeParent != null) {
            if (nodeParent.getLeftChild() == node) {
                nodeParent.setLeftChild(nodeLeft);
            } else {
                nodeParent.setRightChild(nodeLeft);
            }
        } else {
            root = nodeLeft;
            root.setParent(null);
        }
        node.setLeftChild(nodeLeft.getRightChild());
        nodeLeft.setRightChild(node);
    }

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
