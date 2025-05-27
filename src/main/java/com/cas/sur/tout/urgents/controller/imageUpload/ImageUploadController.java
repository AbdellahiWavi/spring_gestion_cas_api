package com.cas.sur.tout.urgents.controller.imageUpload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import static com.cas.sur.tout.urgents.utils.Constants.IMAGE_ENDPOINT;

@RestController
@RequestMapping(IMAGE_ENDPOINT)
public class ImageUploadController {

    @Value("http://${server.hostname}:${server.port}")
    private String baseUrl;

    List<String> allowedExtensions = List.of("png", "jpg", "jpeg", "gif", "mp4", "mov", "avi", "wmv", "mkv", "webm", "mp3", "wav", "ogg", "aac", "flac");

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        final String uploadDir = "uploads/";
        final String originalFilename = file.getOriginalFilename();
        final String extension = originalFilename != null ?
                originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase() :
                "";

        // Vérification de l'extension
        if (!allowedExtensions.contains(extension)) {
            return ResponseEntity.badRequest().body("Invalid file type");
        }

        // Génération du nom unique
        final String fileName = UUID.randomUUID() + "_" + originalFilename;
        final Path uploadPath = Paths.get(uploadDir);

        // Création du dossier si nécessaire
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Sauvegarde du fichier
        final Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Construction de l'URL finale
        final String imageUrl = this.baseUrl + IMAGE_ENDPOINT + "/" + fileName;
        return ResponseEntity.ok(imageUrl);
    }

}
