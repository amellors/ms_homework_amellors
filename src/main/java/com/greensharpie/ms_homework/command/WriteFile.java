package com.greensharpie.ms_homework.command;

import java.io.FileNotFoundException;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.filesystem.EntryFactory;
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

    public void exec(SystemData system) throws FileNotFoundException {
        Directory cwd = system.getCwd();

        DirectoryEntry entry = cwd.getEntry(filename);
        File targetFile = null;
        if (entry == null) {
            // Just make the file first, then write contents
            targetFile = EntryFactory.createFile(filename);
            cwd.getContents().add(targetFile);
        }
        else if (entry instanceof File) {
            targetFile = (File)entry;
        }
        else {
            throw new FileNotFoundException("Could not find a file with name: " + filename);
        }
        
        targetFile.setContents(newContents);    
    }        
}
