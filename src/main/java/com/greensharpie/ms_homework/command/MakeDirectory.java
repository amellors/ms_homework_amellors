package com.greensharpie.ms_homework.command;

import java.nio.file.FileAlreadyExistsException;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.EntryFactory;
import com.greensharpie.ms_homework.system.SystemData;

public class MakeDirectory {
    private static final String cli_string = "mkdir";

    private String newDirName;

    public MakeDirectory(String newDirName) {
        this.newDirName = newDirName;
    } 

    public void exec(SystemData system) throws FileAlreadyExistsException {
        Directory cwd = system.getCwd();
        Directory newDir = EntryFactory.createDirectory(newDirName, cwd);
        if (!cwd.getContents().add(newDir)) {
            throw new FileAlreadyExistsException("Directory already contains item named: " + newDirName);
        }
    }
}
