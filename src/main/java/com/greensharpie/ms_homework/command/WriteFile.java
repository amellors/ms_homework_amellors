package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.filesystem.File;
import com.greensharpie.ms_homework.system.SystemData;

public class WriteFile {
    private static final String cli_string = "write";

    private String filename;
    private String newContents;

    public WriteFile(String filename, String newContents) {
        this.filename = filename;
        this.newContents = newContents;
    } 

    public void exec(SystemData system) {
        Directory cwd = system.getCwd();

        DirectoryEntry entry = cwd.getEntry(filename);
        if (entry != null && entry instanceof File) {
            ((File)entry).setContents(newContents);    
        }
        else {
            System.out.println("Could not find a file with name: " + filename);
        }
    }        
}
