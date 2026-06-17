package com.example.file_compression_service.controller;

import com.example.file_compression_service.service.FileCompressionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class MainRestController {

    @Autowired
    private FileCompressionService fileCompressionService;

    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);

    @PostMapping("/compress")
    public ResponseEntity<String> compressFile(@RequestParam("uploadFile") MultipartFile file){
        try {
            log.info("file name: {}",file.getOriginalFilename());
            log.info("file size: {} bytes",file.getSize());
            fileCompressionService.compressFile(file);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("successfully compressed", HttpStatus.CREATED);
    }
}
