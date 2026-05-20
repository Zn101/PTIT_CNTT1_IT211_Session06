package project.ptit_cntt1_it211_session06.service.impl;

import project.ptit_cntt1_it211_session06.exception.InvalidFileException;
import project.ptit_cntt1_it211_session06.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String saveFile(MultipartFile file) {

        return null;
    }

    @Override
    public void deleteFile(String filePath) {

    }

    @Override
    public boolean isImageFile(MultipartFile file) {

        String contentType = file.getContentType();

        return contentType != null &&
                (
                        contentType.equals("image/jpeg") ||
                                contentType.equals("image/png")
                );
    }
}