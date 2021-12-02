package FileSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileSystemTest {

    public static Folder readFolder (Scanner sc)  {

        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i=0;i<totalFiles;i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String [] parts = fileInfo.split("\\s+");
                try {
                    folder.addFile(new File(parts[0], Long.parseLong(parts[1])));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                try {
                    folder.addFile(readFolder(sc));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return folder;
    }

    public static void main(String[] args)  {

        //file reading from input

        Scanner sc = new Scanner (System.in);

        System.out.println("===READING FILES FROM INPUT===");
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.addFile(readFolder(sc));
        } catch (FileNameExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
        fileSystem.sortBySize();
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
        System.out.println(fileSystem.findLargestFile());




    }
}

interface IFile extends Comparable<IFile> {
    String getFileName();
    long getFileSize();
    String getFileInfo(int indent);
    void sortBySize();
    IFile findLargestFile();

    @Override
    default int compareTo(IFile o){
        return Long.compare(getFileSize(),o.getFileSize());
    }
}

class File implements IFile{

    private String name;
    private long size;

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
        return String.format("%sFile name: %10s File size: %10d\n",
                IndentPrinter.printIndentation(indent), getFileName(), getFileSize());
    }

    @Override
    public void sortBySize() {
        return;
    }

    @Override
    public IFile findLargestFile() {
        return this;
    }
}

class Folder implements IFile{

    private String name;
    private List<IFile> files;

    public Folder(String name) {
        this.name = name;
        files = new ArrayList<IFile>();
    }

    public void addFile(IFile file) throws FileNameExistsException {
        if(files.stream().filter(x -> x.getFileName().equals(file.getFileName())).collect(Collectors.toList()).size()>0){
            throw new FileNameExistsException(file.getFileName(),this.name);
        }
        files.add(file);
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        if(files.size()==0) return 0;
        return files.stream().mapToLong(x -> x.getFileSize()).sum();
    }

    @Override
    public String getFileInfo(int indent) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%sFolder name: %10s Folder size: %10d\n",
                IndentPrinter.printIndentation(indent), getFileName(), getFileSize()));

        files.stream().forEach(file -> stringBuilder.append(file.getFileInfo(indent + 1)));
        return stringBuilder.toString();
    }

    @Override
    public void sortBySize() {
        files = files.stream().sorted().collect(Collectors.toList());
        files.stream().forEach(x -> x.sortBySize());
    }

    @Override
    public IFile findLargestFile() {
        if(this.files.size()==0) return new File("temp",0);
        return files.stream().map(x -> x.findLargestFile()).max(Comparator.naturalOrder()).get();
    }
}

class FileNameExistsException extends Exception{
    public FileNameExistsException(String name, String folder) {
        super("There is already a file named "+ name +" in the folder "+folder);
    }
}

class FileSystem{
    private Folder root;

    public FileSystem() {
        this.root = new Folder("root");
    }

    public void addFile(IFile file) throws FileNameExistsException {
        root.addFile(file);
    }

    public long findLargestFile (){
        return root.findLargestFile().getFileSize();
    }

    public void sortBySize(){
        root.sortBySize();
    }

    @Override
    public String toString() {
        return root.getFileInfo(0);
    }
}
class IndentPrinter {
    public static String printIndentation(int level) {
        return IntStream.range(0, level)
                .mapToObj(i -> "\t")
                .collect(Collectors.joining());
    }
}