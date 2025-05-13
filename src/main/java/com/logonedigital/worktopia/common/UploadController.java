package com.logonedigital.worktopia.common;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequestMapping({"/uploads"})
public class UploadController {
    private final String UPLOAD_DIR = "uploads/";

    @PostMapping({"/file"})
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String var10000 = String.valueOf(UUID.randomUUID());
            String fileName = var10000 + "_" + file.getOriginalFilename();
            Path filePath = Paths.get("uploads/" + fileName);
            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            return ResponseEntity.ok(fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erreur lors de upload : " + e.getMessage());
        }
    }

    @GetMapping({"/file/{filename}"})
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get("uploads/" + filename);
        Resource resource = new UrlResource(filePath.toUri());
        return ((ResponseEntity.BodyBuilder)ResponseEntity.ok().header("Content-Disposition", new String[]{"attachment; filename=\"" + filename + "\""})).body(resource);
    }
}
