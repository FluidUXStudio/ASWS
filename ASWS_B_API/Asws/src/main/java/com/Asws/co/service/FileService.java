package com.Asws.co.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    
    public MultipartFile uploadFiles(String Path , MultipartFile file) throws IOException;
}
