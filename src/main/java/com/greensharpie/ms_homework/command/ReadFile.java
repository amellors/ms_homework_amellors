package com.greensharpie.ms_homework.command;

import java.io.FileNotFoundException;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.filesystem.File;
import com.greensharpie.ms_homework.system.SystemData;

public class ReadFile {
    private static final String cli_string = "cat";

    private String filename;

    public ReadFile(String filename) {
        this.filename = filename;
    } 

    public void exec(SystemData system) throws FileNotFoundException {
        Directory cwd = system.getCwd();

        DirectoryEntry entry = cwd.getEntry(filename);
        if (entry != null && entry instanceof File) {
            System.out.println(((File)entry).getContents());    
        }
        else {
            throw new FileNotFoundException("Could not find a file with name: " + filename);
        }
    }    
}
