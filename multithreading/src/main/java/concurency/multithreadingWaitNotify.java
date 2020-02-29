package concurency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class multithreadingWaitNotify {

    public static void writeToFileWaitNotify()
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
        printDirectoryTree(new WaitNotify(), folder, indent, sb);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static void printDirectoryTree(WaitNotify waitNotify, File folder, int indent,
                                           StringBuilder sb) {
        AtomicInteger count = new AtomicInteger();
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
                new Thread(() -> {
                    printDirectoryTree(new WaitNotify(), file, indent + 1, sb);
                    waitNotify.doNotify();
                }).start();
                waitNotify.doWait();
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

    private static class WaitNotify {

        private final MonitorObject myMonitorObject = new MonitorObject();
        private boolean wasSignalled = false;

        void doWait() {
            synchronized (myMonitorObject) {
                while (!wasSignalled) {
                    try {
                        myMonitorObject.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                wasSignalled = false;
            }
        }

        void doNotify() {
            synchronized (myMonitorObject) {
                wasSignalled = true;
                myMonitorObject.notifyAll();
            }
        }

        private static class MonitorObject {
        }
    }
}
