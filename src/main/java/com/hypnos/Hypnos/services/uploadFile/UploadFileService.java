package com.hypnos.Hypnos.services.uploadFile;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface UploadFileService {
    public Resource load(String filename) throws MalformedURLException;
    public String copy(MultipartFile file) throws IOException;
    public boolean delete(String filename);
}
