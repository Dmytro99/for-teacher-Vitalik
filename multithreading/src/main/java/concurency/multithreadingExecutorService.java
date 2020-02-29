package concurency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class multithreadingExecutorService {

    public static void writeToFileExecutorService()
            throws IOException {
        String path = "D:\\Java\\tree.txt";
        String pathDir = "D:\\Move";

        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(printDirectoryTree(new File(pathDir)));
        fileWriter.close();
    }

    public static String printDirectoryTree(File folder) {
        int indent = 0;
        StringBuilder sb = new StringBuilder();

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        printDirectoryTree(folder, indent, sb);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static void printDirectoryTree(File folder, int indent,
                                           StringBuilder sb) {
        ExecutorService executor = null;

        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        String separator = System.getProperty("line.separator");
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(folder.getName());
        sb.append("/");
        sb.append(separator);
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {

                executor = Executors.newSingleThreadExecutor();
                Runnable r = (() -> printDirectoryTree(file, indent + 1, sb));
                try {
                    executor.submit(r).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }
}
