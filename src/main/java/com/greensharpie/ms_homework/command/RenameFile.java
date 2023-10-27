package com.greensharpie.ms_homework.command;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.filesystem.File;
import com.greensharpie.ms_homework.system.SystemData;

public class RenameFile {
    private static final String cli_string = "mv";

    private String oldName;
    private String newName;

    public RenameFile(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    } 

    public void exec(SystemData system) throws FileAlreadyExistsException, FileNotFoundException {
        Directory cwd = system.getCwd();

        DirectoryEntry entry = cwd.getEntry(oldName);
        if (entry != null && entry instanceof File) {
            DirectoryEntry newEntry = cwd.getEntry(newName);
            if(newEntry == null) {
                // Couldn't find an entry with new name, it's safe to rename
                entry.setName(newName);
            }
            else {
                throw new FileAlreadyExistsException("Directory already contains item named: " + newName); 
            }
        }
        else {
            throw new FileNotFoundException("Could not find a file with name: " + oldName);
        }
    }    
}
