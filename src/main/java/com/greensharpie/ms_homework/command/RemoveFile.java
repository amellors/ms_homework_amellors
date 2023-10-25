package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.filesystem.File;
import com.greensharpie.ms_homework.system.SystemData;

public class RemoveFile {
    private static final String cli_string = "rm";

    private String filename;

    public RemoveFile(String filename) {
        this.filename = filename;
    } 

    public void exec(SystemData system) {
        Directory cwd = system.getCwd();
        for (DirectoryEntry possibleDirectory: cwd.getContents()) {
            if (possibleDirectory.getName().equals(filename))
                if (possibleDirectory instanceof File) {
                    cwd.getContents().remove(possibleDirectory);
                    return;
                }
        }

        System.out.println("Could not find file with name: " + filename);
    }      
}
