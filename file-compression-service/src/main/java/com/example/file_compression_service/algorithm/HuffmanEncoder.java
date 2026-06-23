package com.example.file_compression_service.algorithm;

import com.example.file_compression_service.base.CodeTree;
import com.example.file_compression_service.base.Leaf;
import com.example.file_compression_service.base.Node;
import jakarta.websocket.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class HuffmanEncoder {

    private static final Logger log = LoggerFactory.getLogger(HuffmanEncoder.class);
    private final CodeTree codeTree;
    private final Queue<Node> queue;
    private final Map<Character,String> huffmanCodes;
    private String fileName;
    private String compressedFilePath;

    public HuffmanEncoder(CodeTree codeTree){
        this.codeTree = codeTree;
        queue = new PriorityQueue<>();
        huffmanCodes = new HashMap<>();
    }

    public void compress() throws IOException  {
        String text = codeTree.getText();
        codeTree.setCharacterFrequencyMap(calculateCharacterFrequencies(text));
        Node root = generateCodeTree();
        generateHuffmanCodes(root, "");
        String encodedText = getEncodedText();
        String compresedFilePath = "C:\\files\\".concat(fileName.concat("_compressed")).concat(".txt\\");
        File file = new File(compresedFilePath);
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        StringBuilder sb = new StringBuilder();
        for(char ch: text.toCharArray()){
            sb.append(huffmanCodes.get(ch));
        }
        int len = (sb.length()+7)/8;
        byte res[] = new byte[len];
        int j = 0;
        for(int i=0;i<len;i++){
            if(i+8<sb.length()){
                res[j++]=(byte)Integer.parseInt(sb.substring(i,i+8),2);
            }else {
                res[j++]=(byte)Integer.parseInt(sb.substring(i),2);
            }
        }
        out.write(res);
        log.info("Compressed file: {}",file);
        out.close();
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void decompress() throws FileNotFoundException {
        File file = new File("C:\\files\\".concat(fileName.concat("_decompressed")).concat(".txt\\"));
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));

    }

    public String getCompressedFilePath() {
        return compressedFilePath;
    }

    public void setCompressedFilePath(String compressedFilePath) {
        this.compressedFilePath = compressedFilePath;
    }
}
