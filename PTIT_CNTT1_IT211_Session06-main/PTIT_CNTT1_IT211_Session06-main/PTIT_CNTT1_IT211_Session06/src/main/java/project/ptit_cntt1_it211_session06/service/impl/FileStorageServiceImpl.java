package project.ptit_cntt1_it211_session06.service.impl;

import project.ptit_cntt1_it211_session06.exception.InvalidFileException;
import project.ptit_cntt1_it211_session06.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("File is empty");
        }

        if (!isImageFile(file)) {
            throw new InvalidFileException("Only JPG and PNG image files are allowed");
        }

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            String originalFileName = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFileName != null && originalFileName.contains(".")) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            String newFileName = UUID.randomUUID().toString() + fileExtension;

            Path targetPath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + newFileName;
        } catch (IOException e) {
            throw new InvalidFileException("Could not store file. Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return;
        }
        try {
            String filename = filePath;
            if (filePath.contains("/uploads/")) {
                filename = filePath.substring(filePath.lastIndexOf("/uploads/") + "/uploads/".length());
            } else if (filePath.contains("/")) {
                filename = filePath.substring(filePath.lastIndexOf("/") + 1);
            } else if (filePath.contains("\\")) {
                filename = filePath.substring(filePath.lastIndexOf("\\") + 1);
            }
            Path targetPath = Paths.get(uploadDir).resolve(filename).toAbsolutePath().normalize();
            Files.deleteIfExists(targetPath);
        } catch (IOException e) {
            System.err.println("Could not delete file: " + filePath + ". Error: " + e.getMessage());
        }
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