package com.greensharpie.ms_homework.command;

import java.io.FileNotFoundException;

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

    public void exec(SystemData system) throws FileNotFoundException {
        Directory cwd = system.getCwd();

        DirectoryEntry entry = cwd.getEntry(filename);
        if (entry != null && entry instanceof File) {
            cwd.getContents().remove(entry);
        }
        else {

            throw new FileNotFoundException("Could not find file with name: " + filename);
        }
    }      
}
