package com.student.project.amazone.controller;


import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.File.payload.Response;
import com.student.project.amazone.entity.Banner_model;
import com.student.project.amazone.service.Banner_service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v2/banner")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://customerui.herokuapp.com")
@Slf4j
public class Banner_controller {
    @Autowired
    private final FileStorageService storeService;
    @Autowired
    private final Banner_service BannerService;

    @GetMapping
    public ResponseEntity<List<Banner_model>> getListFile() {
        return ResponseEntity.ok().body(BannerService.getList());
    }

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {


        String[] getType = file.getContentType().split("/");


        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        String fileName = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Banner_model banner = new Banner_model();


        banner.setImageName(fileName + "." + getType[1]);
        banner.setLink("thiendia.com");

        BannerService.SaveOrUpdate(banner);

        storeService.storeFileBanner(file, fileName + "." + getType[1]);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new Response(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PutMapping
    public ResponseEntity<Banner_model> update(@RequestParam("file") MultipartFile file,@RequestParam String fileName){


        storeService.storeFileBanner(file,fileName);

        return null;
    }

    @DeleteMapping
    public ResponseEntity<Banner_model> delete(@RequestParam String fileName){
//        storeService.getFilePath(file, fileName + "." + getType[1]);
        BannerService.delete_file(fileName);
        return null;
    }
}
