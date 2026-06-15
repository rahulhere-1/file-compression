package com.example.file_compression_service.base;

public class Leaf extends Node {
    private final char character;

    Leaf(Node left, Node right, char character) {
        super(left, right);
        this.character = character;
    }
}
