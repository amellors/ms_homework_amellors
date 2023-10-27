package com.greensharpie.ms_homework.command;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.filesystem.EntryFactory;
import com.greensharpie.ms_homework.filesystem.File;
import com.greensharpie.ms_homework.system.NotYetImplementedException;
import com.greensharpie.ms_homework.system.SystemData;

public class Copy {
    private static final String cli_string = "cp";

    private String oldName;
    private String newName;

    public Copy(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    } 

    public void exec(SystemData system) throws FileAlreadyExistsException, FileNotFoundException, NotYetImplementedException {
        Directory cwd = system.getCwd();

        DirectoryEntry entry = cwd.getEntry(oldName);
        if (entry == null) {
            throw new FileNotFoundException("Could not find an entry with name: " + oldName);    
        }

        if (entry instanceof Directory) {
            throw new NotYetImplementedException("Directory copy is not yet implemented"); 
        }

        if (entry instanceof File) {
            DirectoryEntry newEntry = cwd.getEntry(newName);
            if(newEntry == null) {
                // Couldn't find an entry with new name, it's safe to copy
                File newFile = EntryFactory.createFileWithContents(newName, ((File)entry).getContents());
                cwd.getContents().add(newFile);
            }
            else {
                throw new FileAlreadyExistsException("Directory already contains item named: " + newName); 
            }
        }
    }        
}
