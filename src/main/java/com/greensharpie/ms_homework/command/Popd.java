package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.system.DirectoryStack;
import com.greensharpie.ms_homework.system.SystemData;

public class Popd {
    private static final String cli_string = "pushd";

    public Popd() { } 

    public void exec(SystemData system) {
        DirectoryStack stack = DirectoryStack.getInstance();
        if (!stack.isEmpty()) {
            system.setCwd(stack.popd());
        }
        else {
            System.out.println("Directory stack is empty");
        }
    }     
}
