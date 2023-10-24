package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.EntryFactory;
import com.greensharpie.ms_homework.system.SystemData;

public class MakeDirectory {
    private static final String cli_string = "mkdir";

    private String newDirName;

    public MakeDirectory(String newDirName) {
        this.newDirName = newDirName;
    } 

    public void exec(SystemData system) {
        Directory cwd = system.getCwd();
        Directory newDir = EntryFactory.createDirectory(newDirName, cwd);
        if (!cwd.getContents().add(newDir)) {
            System.out.println("Directory already contains item named: " + newDirName);
        }
    }
}
