package com.example.file_compression_service.service;

import com.example.file_compression_service.algorithm.HuffmanEncoder;
import com.example.file_compression_service.base.CodeTree;
import com.example.file_compression_service.controller.MainRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileCompressionService {

    private static final Logger log = LoggerFactory.getLogger(FileCompressionService.class);



    public void compressFile(List<MultipartFile> files) throws IOException {
        MultipartFile file = validateFile(files);
        String content = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        CodeTree codeTree = new CodeTree();
        codeTree.setText(content);
        HuffmanEncoder huffmanEncoder = new HuffmanEncoder(codeTree);
        huffmanEncoder.setFileName(file.getOriginalFilename());
        huffmanEncoder.compress();
    }

    private MultipartFile validateFile(List<MultipartFile> files) {

        if(files.size()>1){
            throw new RuntimeException("Only 1 file can be compressed");
        }
        MultipartFile file = files.get(0);
        if(file.isEmpty()){
            throw new RuntimeException("file is empty");
        }
        String contentType = file.getContentType();
        if(contentType != null && !contentType.startsWith("text") && !contentType.equals("text/csv") && !contentType.equals("application/json") ){
            throw new RuntimeException("file type is not a text. Only text files can be compressed");
        }
        log.info("file name: {}",file.getOriginalFilename());
        log.info("file size: {} bytes",file.getSize());
        return file;
    }
}
