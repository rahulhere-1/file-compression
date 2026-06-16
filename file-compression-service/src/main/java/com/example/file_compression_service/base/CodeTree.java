package com.example.file_compression_service.base;

import java.util.HashMap;
import java.util.Map;

public class CodeTree {

    private Node head;
    private String text;
    private Map<Character,String> huffmanCodes;
    private Map<Character,Integer> characterFrequencyMap;


    public CodeTree(Node head) {
        this.head = head;
        huffmanCodes = new HashMap<>();
        characterFrequencyMap = new HashMap<>();
    }


    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Map<Character, String> getHuffmanCodes() {
        return huffmanCodes;
    }

    public void setHuffmanCodes(Map<Character, String> huffmanCodes) {
        this.huffmanCodes = huffmanCodes;
    }

    public Map<Character, Integer> getCharacterFrequencyMap() {
        return characterFrequencyMap;
    }

    public void setCharacterFrequencyMap(Map<Character, Integer> characterFrequencyMap) {
        this.characterFrequencyMap = characterFrequencyMap;
    }
}
