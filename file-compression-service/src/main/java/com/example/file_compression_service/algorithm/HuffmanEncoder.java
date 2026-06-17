package com.example.file_compression_service.algorithm;

import com.example.file_compression_service.base.CodeTree;
import com.example.file_compression_service.base.Leaf;
import com.example.file_compression_service.base.Node;

import java.util.*;

public class HuffmanEncoder {

    private final CodeTree codeTree;
    private final Queue<Node> queue;
    private final Map<Character,String> huffmanCodes;
    HuffmanEncoder(CodeTree codeTree){
        this.codeTree = codeTree;
        queue = new PriorityQueue<>();
        huffmanCodes = new HashMap<>();
    }

    public String compress(){
        String text = codeTree.getText();
        codeTree.setCharacterFrequencyMap(calculateCharacterFrequencies(text));
        Node root = generateCodeTree();
        generateHuffmanCodes(root,"");
        return getEncodedText();
    }

    private void generateHuffmanCodes(Node root, String code) {
        if(root instanceof Leaf leaf) {
            huffmanCodes.put(leaf.getCharacter(),code);
            return;
        }
        generateHuffmanCodes(root.getLeft(),code.concat("0"));
        generateHuffmanCodes(root.getRight(),code.concat("1"));
    }

    private Map<Character,Integer> calculateCharacterFrequencies(String text){
        Map<Character,Integer> characterFrequencyMap = new HashMap<>();
        for(char ch: text.toCharArray()){
            characterFrequencyMap.put(ch,characterFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        return characterFrequencyMap;
    }

    private Node generateCodeTree(){
        Map<Character,Integer> characterFrequencyMap = codeTree.getCharacterFrequencyMap();
        for (Map.Entry<Character,Integer> entry: characterFrequencyMap.entrySet()){
            char character = entry.getKey();
            Integer count = entry.getValue();
            queue.add(new Leaf(count,character));
        }
        while (queue.size()>1){
            queue.add(new Node(queue.poll(), Objects.requireNonNull(queue.poll())));
        }

        return queue.poll();
    }

    private String getEncodedText() {
        StringBuilder sb = new StringBuilder();
        String text = codeTree.getText();
        for (char character : text.toCharArray()) {
            sb.append(huffmanCodes.get(character));
        }
        return sb.toString();
    }
}
