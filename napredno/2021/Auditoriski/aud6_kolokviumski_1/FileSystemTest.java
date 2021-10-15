package mk.ukim.finki.aud6_kolokviumski_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

interface IFile extends Comparable<IFile> {
    String getFileName();

    long getFileSize();

    String getFileInfo(int indent);

    void sortBySize();

    long findLargestFile();

    @Override
    default int compareTo(IFile o) {
        return Long.compare(this.getFileSize(), o.getFileSize());
    }
}


class File implements IFile {

    String name;
    long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        return size;
    }

    @Override
    public String getFileInfo(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++)
            sb.append(" ");
        sb.append(name).append(" ").append(getFileSize());
        return sb.toString();
    }

    @Override
    public void sortBySize() {

    }

    @Override
    public long findLargestFile() {
        return size;
    }
}

class Folder extends File {

    List<IFile> files;

    public Folder(String name) {
        super(name, 0);
        files = new ArrayList<>();
    }

    @Override
    public long getFileSize() {
//        long sum = 0;
//        for (IFile file : files) {
//            sum+=file.getFileSize();
//        }
//        return sum;
        return files.stream().mapToLong(f -> f.getFileSize()).sum();
    }

    @Override
    public String getFileInfo(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getFileInfo(indent));

        for (IFile file : files)
            sb.append(file.getFileInfo(indent + 1));

        return sb.toString();
    }

    @Override
    public void sortBySize() {
        Collections.sort(files);
        for (IFile file : files) {
            file.sortBySize();
        }
    }

    @Override
    public long findLargestFile() {
        return files.stream().mapToLong(i -> i.findLargestFile()).max().orElse(0);
    }

    public void addFile(IFile file) {
        //TODO handle exception
        files.add(file);
    }
}

class FileSystem {

    Folder root;

    FileSystem() {
        root = new Folder("root");
    }

    public void addFile(Folder readFolder) {
        root.addFile(readFolder);
    }

    public void sortBySize() {
        root.sortBySize();
    }

    public long findLargestFile() {
        return root.findLargestFile();
    }

    @Override
    public String toString() {
        return root.getFileInfo(0);
    }
}

public class FileSystemTest {

    public static Folder readFolder(Scanner sc) {

        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < totalFiles; i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String[] parts = fileInfo.split("\\s+");

                folder.addFile(new File(parts[0], Long.parseLong(parts[1])));

            } else {

                folder.addFile(readFolder(sc));

            }
        }

        return folder;
    }

    public static void main(String[] args) {

        //file reading from input

        Scanner sc = new Scanner(System.in);

        System.out.println("===READING FILES FROM INPUT===");
        FileSystem fileSystem = new FileSystem();

        fileSystem.addFile(readFolder(sc));


        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
        fileSystem.sortBySize();
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
        System.out.println(fileSystem.findLargestFile());


    }
}