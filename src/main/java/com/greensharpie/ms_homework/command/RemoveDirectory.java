package com.greensharpie.ms_homework.command;

import java.io.FileNotFoundException;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.system.SystemData;

public class RemoveDirectory {
    private static final String cli_string = "rmdir";

    private String rmdirName;

    public RemoveDirectory(String rmdirName) {
        this.rmdirName = rmdirName;
    } 

    public void exec(SystemData system) throws FileNotFoundException {
        Directory cwd = system.getCwd();
        
        DirectoryEntry entry = cwd.getEntry(rmdirName);
        if (entry != null && entry instanceof Directory) {
            cwd.getContents().remove(entry);
        }
        else {
            throw new FileNotFoundException("Could not find directory with name: " + rmdirName);
        }
    }    
}
