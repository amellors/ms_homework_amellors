package com.greensharpie.ms_homework.system;

import java.util.Stack;

import com.greensharpie.ms_homework.filesystem.Directory;

public class DirectoryStack {

    private static DirectoryStack instance;

    private DirectoryStack() {
        this.directoryStack = new Stack<Directory>();
    }

    // static block initialization for exception handling
    static {
        try {
            instance = new DirectoryStack();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static DirectoryStack getInstance() {
        return instance;
    }

    private Stack<Directory> directoryStack;
    public void pushd(Directory cwd)  {
        directoryStack.add(cwd);
    }
    
    public Directory popd() {
        return directoryStack.pop();
    }
    
    public boolean isEmpty() {
        return directoryStack.empty();
    }
}

