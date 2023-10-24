package com.greensharpie.ms_homework.filesystem;

public class EntryFactory {
    private EntryFactory() { }

    static public Directory createDirectory(String dirName, Directory parentDir) {
        Directory newDir = new Directory(dirName, parentDir);
        return newDir;
    }
}
