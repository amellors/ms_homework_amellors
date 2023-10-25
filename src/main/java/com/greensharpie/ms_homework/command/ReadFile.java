package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.system.SystemData;

public class ReadFile {
    private static final String cli_string = "cat";

    private String filename;

    public ReadFile(String filename) {
        this.filename = filename;
    } 

    public void exec(SystemData system) {
        System.out.println("NYI");
    }
}
