package com.greensharpie.ms_homework.system;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.filesystem.EntryFactory;

public class SystemData {
    private Directory cwd;

    public SystemData() {
        cwd = EntryFactory.createDirectory("", null);
    }

    public void setCwd(Directory newDir) {
        cwd = newDir;
    }
    public Directory getCwd() {
        return cwd;
    }

}
