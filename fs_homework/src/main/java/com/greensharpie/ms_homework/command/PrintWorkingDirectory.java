package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.system.SystemData;

public class PrintWorkingDirectory {
    private static final String cli_string = "pwd";

    // No inputs needed
    public PrintWorkingDirectory() { }

    public void exec(SystemData system) {
        System.out.println(generateDirectoryPath(system.getCwd()));
    }

    private String generateDirectoryPath(Directory currentDirectory) {
        if (currentDirectory.getParent() == null) {
            return "/";
        }
        else if (currentDirectory.getParent().getParent() == null) {
            return "/" + currentDirectory.getName();
        }
        else {
            return generateDirectoryPath(currentDirectory.getParent()) + "/" + currentDirectory.getName();
        }
    }
}
