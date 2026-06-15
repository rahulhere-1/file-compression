package com.example.file_compression_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class MainRestController {


    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);

    @PostMapping("/compress")
    public ResponseEntity<String> compressFile(@RequestParam("uploadFile") MultipartFile file){
        try {

        } catch (Exception e){
            e.printStackTrace();
        }
        log.info("file name: {}",file.getOriginalFilename());
        log.info("file size: {} bytes",file.getSize());
        return new ResponseEntity<>("successfully compressed", HttpStatus.CREATED);
    }
}
