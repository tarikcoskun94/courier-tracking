package com.migrosone.couriertracking.service.file;

import java.io.IOException;
import java.nio.file.Path;

public interface FileService {

    Path save(String filePath, String content, Object... contentParams) throws IOException;
}
