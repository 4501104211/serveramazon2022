package com.student.project.amazone.service.FIle;


import com.student.project.amazone.dto.FileDB;
import com.student.project.amazone.repo.FileDBRepository;
import com.sun.jersey.api.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileDBServiceImpl implements FileDBService {
    private final FileDBRepository fileDBRepository;


    public String getRamdom(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        String fileName = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return fileName;
    }

    @Override
    public FileDB storeImageFile(MultipartFile file) throws IOException {
        FileDB image = new FileDB(getRamdom(), file.getContentType(), file.getBytes());
        return storeImageByte(image);
    }

    @Override
    public FileDB storeImageByte(FileDB file) throws IOException {
        return fileDBRepository.save(file);
    }

    @Override
    public Stream<FileDB> getAllImagesFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public FileDB getImage(String id) {
        Optional<FileDB> optionalFileDB = Optional.ofNullable(fileDBRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy ảnh")));
        FileDB file = optionalFileDB.get();
        return file;
    }
}