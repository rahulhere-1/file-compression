package com.example.file_compression_service.algorithm;

import com.example.file_compression_service.base.CodeTree;

import java.util.HashMap;
import java.util.Map;

public class HuffmanEncoder {

    private final CodeTree codeTree;

    HuffmanEncoder(CodeTree codeTree){
        this.codeTree = codeTree;
    }

    public void compress(String text){
        codeTree.setCharacterFrequencyMap(calculateCharacterFrequencies(text));

    }

    private Map<Character,Integer> calculateCharacterFrequencies(String text){
        Map<Character,Integer> characterFrequencyMap = new HashMap<>();
        for(char ch: text.toCharArray()){
            characterFrequencyMap.put(ch,characterFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        return characterFrequencyMap;
    }
}
