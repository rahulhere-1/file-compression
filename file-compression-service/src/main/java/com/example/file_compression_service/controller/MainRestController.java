package com.example.file_compression_service.controller;

import com.example.file_compression_service.service.FileCompressionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class MainRestController {

    @Autowired
    private FileCompressionService fileCompressionService;


    @PostMapping("/compress")
    public ResponseEntity<String> compressFile(@RequestParam("uploadFile") List<MultipartFile> files){

        try {
            fileCompressionService.compressFile(files);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("successfully compressed", HttpStatus.CREATED);
    }
}
