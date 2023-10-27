package com.greensharpie.ms_homework.command;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.DirectoryEntry;
import com.greensharpie.ms_homework.system.SystemData;

public class Find {
    private static final String cli_string = "find";

    private String findName;
    private boolean recursive;

    public Find(String findName) {
        this.findName = findName;
        this.recursive = false; 
    }

    public Find(String findName, boolean recursive) {
        this(findName);
        this.recursive = recursive;
    } 

    public void exec(SystemData system) {   
        if(recursive) {
            recurse("", system.getCwd());
        }
        else {
            find("", system.getCwd());
        }

    }

    private void find(String baseDirName, Directory dir) {
        DirectoryEntry entry = dir.getEntry(findName);
        if (entry != null) {
            String entryname = baseDirName.isEmpty() ? findName : baseDirName + "/" + findName;
            System.out.println("Found a " + entry.getClass().getSimpleName() + " with name " + entryname );
        }
    }

    private void recurse(String baseDirName, Directory dir) {
        find(baseDirName, dir);
        for (DirectoryEntry ent: dir.getContents()) {
            if (ent instanceof Directory) {
                recurse(baseDirName.isEmpty() ? ent.getName() : baseDirName + "/" + ent.getName(), (Directory)ent);
            }
        }
    }
}
