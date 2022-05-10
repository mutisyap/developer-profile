package ke.co.pmutisya.profile.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class UploadResource {
    private static final String resourcesFolder = "/apps/media";
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
      log.debug("REST request to upload file");

//        Path directory = Paths.get(resourcesFolder);
//        Files.createDirectories(directory);
        String newFilename = UUID.randomUUID().toString().replace("-", "");

        String fileExtension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        newFilename = newFilename + "." + fileExtension;

        Objects.requireNonNull(file.getOriginalFilename());
        try (BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(
                        new File(resourcesFolder, newFilename)))) {
            outputStream.write(file.getBytes());
            outputStream.flush();
        }

        Map<String, String> responseMap = new HashMap<>();

        responseMap.put("filename", newFilename);
        return ResponseEntity.ok(responseMap);
    }

    private  String getFileExtension(String filename){
        char[] fileChars = filename.toCharArray();

        StringBuilder extension = new StringBuilder();
        for (int i = fileChars.length - 1; i >= 0; i --){
            if (fileChars[i] == '.'){
                break;
            }
            extension.append(fileChars[i]);
        }
        return extension.reverse().toString();
    }
}
