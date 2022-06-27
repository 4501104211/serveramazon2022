package com.student.project.amazone.service.FIle;

import com.student.project.amazone.dto.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileDBService {
    FileDB storeImageFile(MultipartFile file) throws IOException;

    FileDB storeImageByte(FileDB file) throws IOException;

    FileDB updateImageFile(String id, MultipartFile file) throws IOException;

    Stream<FileDB> getAllImagesFiles();

    FileDB getImage(String id);
}