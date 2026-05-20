package project.ptit_cntt1_it211_session06.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String saveFile(MultipartFile file);

    void deleteFile(String filePath);

    boolean isImageFile(MultipartFile file);
}
