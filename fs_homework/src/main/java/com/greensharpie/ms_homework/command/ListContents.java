package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.system.SystemData;

public class ListContents {
    private static final String cli_string = "ls";

    // No inputs needed
    public ListContents() { }

    public void exec(SystemData system) {
        String contents = "";
        for (DirectoryEntry de: system.getCwd().getContents()) {
            if(!contents.isEmpty()) {
                contents += ", ";
            }
            contents += de.getName();
        }
        System.out.println(contents);
    }
}
