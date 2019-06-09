package com.ltpc.study.core.thread.ch7.ch2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liutong
 * Date: 2019-05-31
 * Time: 17:40
 * Description:
 **/
public class PreventDuplicate {

    public static final String LOCK_PATH = "/Users/jackieliu/";

    public static final String LOCK_FILE = ".lock";

    public static final String PERMISSIONS = "rw-------";

    public static void main(String[] args) {

    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()){
            throw new RuntimeException("The program already running.");
        }

        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path,PosixFilePermissions.asFileAttribute(perms));
    }

    private static Path getLockFile(){
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
}
