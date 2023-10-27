package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.system.SystemData;

public class Find {
    private static final String cli_string = "find";

    private String findName;

    public Find(String findName) {
        this.findName = findName;
    } 

    public void exec(SystemData system) {
        DirectoryEntry entry = system.getCwd().getEntry(findName);
        if (entry != null) {
            System.out.println("Found a " + entry.getClass().getSimpleName() + " with name " + findName );
        }
        else {
            System.out.println("No entry with name " + findName + " was found.");
        }
    }
}
