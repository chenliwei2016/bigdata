/**
 * @author: ChenLiwei
 * 2017年2月18日
 * FindJavaFile.java
 * Comments: This is the new way to iterator a directory from package nio(new IO) from java 1.7
 */
package win.chenliwei.javacore.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FindJavaFile {

    public static void main(String[] args) throws IOException {

    Path startingDir = Paths.get("C:\\Users\\leavy\\Documents\\GitHub\\bigdata\\JavaCore");

    Files.walkFileTree(startingDir, new FindJavaVisitor());
    }

    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toString().endsWith(".java")) {
        System.out.println(file.toAbsolutePath());
        }
        
        return FileVisitResult.CONTINUE;
    }

    }

}