package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.system.SystemData;

public class ChangeDirectory {
    private static final String cli_string = "cd";

    private String chdirName;

    public ChangeDirectory(String chdirName) {
        this.chdirName = chdirName;
    } 

    public void exec(SystemData system) {
        Directory cwd = system.getCwd();
        if (chdirName.equals("..")) {
            if (cwd.getParent() == null) {
                return;
            }

            system.setCwd(cwd.getParent());
            return;
        }

        DirectoryEntry entry = cwd.getEntry(chdirName);
        if (entry != null && entry instanceof Directory) {
            system.setCwd((Directory)entry);
        }
        else {
            System.out.println("Could not find directory with name: " + chdirName);
        }
    }    
}
