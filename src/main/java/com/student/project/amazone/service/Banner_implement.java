package com.student.project.amazone.service;

import com.student.project.amazone.File.UploadService.FileStorageService;
import com.student.project.amazone.entity.Banner_model;
import com.student.project.amazone.repo.Banner_modelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Service
public class Banner_implement implements Banner_service {
    @Autowired
    private final Banner_modelRepository service;
    @Autowired
    private final FileStorageService filestore;

    public Banner_implement(Banner_modelRepository service, FileStorageService filestore) {
        this.service = service;
        this.filestore = filestore;
    }

    @Override
    public List<Banner_model> getList() {
        return new ArrayList<>(service.findAll());
    }

    @Override
    public Banner_model SaveOrUpdate(Banner_model banner) {
        return service.save(banner);
    }

    @Override
    public void delete_file(String fileName) {
        Path filePath = filestore.getFilePath(fileName);
        Banner_model banner = service.findByName(fileName);
        File file = new File(filePath.toString());

        service.delete(banner);
        file.delete();

    }

}
