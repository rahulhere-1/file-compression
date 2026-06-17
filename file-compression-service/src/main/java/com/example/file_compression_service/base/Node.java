package com.example.file_compression_service.base;

public class Node implements Comparable<Node> {

    private Node left;
    private Node right;
    private final int frequency;

    public Node(Node left,Node right){
        this.left = left;
        this.right = right;
        this.frequency = left.getFrequency() + right.getFrequency();
    }

    Node(int frequency){
        this.frequency = frequency;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.frequency,o.getFrequency());
    }
}
