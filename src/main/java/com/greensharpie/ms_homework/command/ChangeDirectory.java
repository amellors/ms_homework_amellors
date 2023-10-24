package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.system.SystemData;

public class ChangeDirectory {
    private static final String cli_string = "cd";

    private String chdirName;

    // No Default const as we need inputs
    private ChangeDirectory() { }

    public ChangeDirectory(String chdirName) {
        this.chdirName = chdirName;
    } 

    public void exec(SystemData system) {
        Directory cwd = system.getCwd();
        if (chdirName.equals("..")) {
            if (cwd.getParent() == null) {
                System.out.println("Already at root");
                return;
            }

            system.setCwd(cwd.getParent());
            return;
        }

        for (DirectoryEntry possibleDirectory: cwd.getContents()) {
            if (possibleDirectory.getName().equals(chdirName) &&
                possibleDirectory instanceof Directory) {
                    system.setCwd((Directory)possibleDirectory);
                    return;
                }
        }

        System.out.println("Could not find directory with name: " + chdirName);
    }    
}
