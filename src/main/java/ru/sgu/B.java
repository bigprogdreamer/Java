package main.java.ru.sgu;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.zip.*;

public class B {

    private final Path workDir;
    private final String targetStr;

    public B(String dirName, String target) {
        this.workDir = Paths.get(dirName);
        this.targetStr = target.toLowerCase();
    }

    private boolean isCorrectName(Path file) {
        return file.getFileName().toString().toLowerCase().contains(targetStr);
    }

    private int fileCount = 0;

    private void zipFile(ZipOutputStream zout, Path file) throws IOException {
        fileCount++;
        System.out.printf("Выбран файл %d: '%s'%n", fileCount, file.getFileName());
        zout.putNextEntry(new ZipEntry(workDir.relativize(file).toString()));
        Files.copy(file, zout);
        zout.closeEntry();
    }

    public void start() {
        try (FileOutputStream fout = new FileOutputStream(workDir.getFileName() + ".zip");
             ZipOutputStream zout = new ZipOutputStream(fout)) {
            Files.walkFileTree(workDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (isCorrectName(file)) {
                        zipFile(zout, file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.printf("Не удалось создать архив %s.zip%n", workDir.getFileName());
            return;
        }
        System.out.println("Архивация выбранных файлов прошла успешно");
    }

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите дирректорию для поиска: ");
            String dirname = scanner.nextLine();
            System.out.print("\nВведите тергетлайн для поиска: ");
            String targetStr = scanner.nextLine();
            new B(dirname,targetStr).start();
        }
    }
}
