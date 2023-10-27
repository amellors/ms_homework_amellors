package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.system.DirectoryStack;
import com.greensharpie.ms_homework.system.SystemData;

public class Pushd {
    private static final String cli_string = "pushd";

    public Pushd() { } 

    public void exec(SystemData system) {
        DirectoryStack stack = DirectoryStack.getInstance();
        stack.pushd(system.getCwd());
    }        
}
