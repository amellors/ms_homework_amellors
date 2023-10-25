package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.system.SystemData;

public class WriteFile {
    private static final String cli_string = "write";

    private String filename;

    public WriteFile(String filename) {
        this.filename = filename;
    } 

    public void exec(SystemData system) {
        System.out.println("NYI");
    }    
}
