package com.Asws.co.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileServiceImpl implements FileService{

    @Override
    public MultipartFile uploadFiles(String Path, MultipartFile file) throws IOException {

        //file name

        String fileName = file.getOriginalFilename();

        //get path
        String filterPath = Path+File.separator+fileName;


        //make dir if not extists

        File x = new File(Path);
        if(!x.exists()){
            x.mkdir();
        }


        //copy

        Files.copy(file.getInputStream(),Paths.get(filterPath));



        return null;
    }
    
}
