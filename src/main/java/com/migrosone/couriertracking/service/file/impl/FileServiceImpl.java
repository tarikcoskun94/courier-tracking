package com.migrosone.couriertracking.service.file.impl;

import com.migrosone.couriertracking.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Override
    public Path save(String filePath, String content, Object... contentParams) throws IOException {
        Path path = Path.of(filePath);
        Path parentDir = path.getParent();

        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        String finalContent = MessageFormat.format(content, contentParams) + System.lineSeparator();

        return Files.writeString(path,
                finalContent,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }
}
