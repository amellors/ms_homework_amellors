package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.File;
import com.greensharpie.ms_homework.filesystem.EntryFactory;
import com.greensharpie.ms_homework.system.SystemData;

public class TouchFile {
    private static final String cli_string = "touch";

    private String newFileName;

    public TouchFile(String newFileName) {
        this.newFileName = newFileName;
    } 

    public void exec(SystemData system) {
        File newFile = EntryFactory.createFile(newFileName);
        if (!system.getCwd().getContents().add(newFile)) {
            System.out.println("Directory already contains item named: " + newFileName);
        }
    }
}
