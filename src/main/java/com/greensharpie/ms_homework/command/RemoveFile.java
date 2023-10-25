package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.system.SystemData;

public class RemoveFile {
    private static final String cli_string = "rm";

    private String filename;

    public RemoveFile(String filename) {
        this.filename = filename;
    } 

    public void exec(SystemData system) {
        System.out.println("NYI");
    }    
}
