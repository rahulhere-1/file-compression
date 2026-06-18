package com.example.file_compression_service.base;

import java.util.HashMap;
import java.util.Map;

public class CodeTree {

    private String text;
    private Map<Character,String> huffmanCodes;
    private Map<Character,Integer> characterFrequencyMap;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
