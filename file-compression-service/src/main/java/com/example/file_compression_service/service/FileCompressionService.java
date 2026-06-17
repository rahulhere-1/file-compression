package com.example.file_compression_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileCompressionService {


    public void compressFile(MultipartFile file){
        validateFile(file);
        
    }

    private void validateFile(MultipartFile file) {
        if(file.isEmpty()){
            throw new RuntimeException("file is empty");
        }
        String contentType = file.getContentType();
        if(contentType != null && !contentType.startsWith("text") && !contentType.equals("text/csv") && !contentType.equals("application/json") ){
            throw new RuntimeException("file type is not a text. Only text files can be compressed");
        }
    }
}
