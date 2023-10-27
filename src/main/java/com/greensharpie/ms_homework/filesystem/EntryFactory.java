package com.greensharpie.ms_homework.filesystem;

public class EntryFactory {
    private EntryFactory() { }

    static public Directory createDirectory(String dirName, Directory parentDir) {
        Directory newDir = new Directory(dirName, parentDir);
        return newDir;
    }

    static public File createFile(String filename) {
        File newFile = new File(filename);
        return newFile;
    }
    
    static public File createFileWithContents(String filename, String contents) {
        File newFile = new File(filename, contents);
        return newFile;
    }
}
