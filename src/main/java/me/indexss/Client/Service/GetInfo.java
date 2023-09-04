package me.indexss.Client.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class GetInfo {

    public static void printInfo(String filePath, String directoryPath) {
        File filetoSend = new File(filePath);
        File destinationDirectory = new File(directoryPath);

        if (filetoSend.exists() && filetoSend.isFile() && destinationDirectory.isDirectory()) {
            try {
                Path sourcePath = filetoSend.toPath();
                Path destinationPath = destinationDirectory.toPath().resolve(filetoSend.getName());
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
            }
        } else {
        }
    }
}
