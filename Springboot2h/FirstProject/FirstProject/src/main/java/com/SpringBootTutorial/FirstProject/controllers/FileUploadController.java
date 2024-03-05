package com.SpringBootTutorial.FirstProject.controllers;

import com.SpringBootTutorial.FirstProject.models.ResponseObject;
import com.SpringBootTutorial.FirstProject.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/v1/FileUpload")
public class FileUploadController {
    @Autowired
    private IStorageService storageService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file)
    {
        try {
           String generatedFileName = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","upload file successfully",generatedFileName));

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed",exception.getMessage(),""));
        }
    }

    @GetMapping("/files/{fileName:.+}")
    public  ResponseEntity<byte []> readDetailFile(@PathVariable String fileName)
    {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception)
        {
            return ResponseEntity.noContent().build();
        }
    }

    //loadall
    @GetMapping("")
    public  ResponseEntity<ResponseObject> getUploadedFiles()
    {
        try {
            List<String>  urls = storageService.loadAll()
                    .map(path -> {
                        //convert fileName to URl
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile",path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("ok","List files successfully",urls));
        }catch (Exception exception)
        {
            return ResponseEntity.ok(new ResponseObject("failed","List files Failed",new String[] {}));
        }
    }
}
