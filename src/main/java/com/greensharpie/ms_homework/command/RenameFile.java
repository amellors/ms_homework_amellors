package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.system.SystemData;

public class RenameFile {
    private static final String cli_string = "cat";

    private String newFilename;

    public RenameFile(String newFilename) {
        this.newFilename = newFilename;
    } 

    public void exec(SystemData system) {
        System.out.println("NYI");
    }    
}
