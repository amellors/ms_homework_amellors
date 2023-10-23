package com.greensharpie.ms_homework.filesystem;

import java.util.LinkedHashSet;
import java.util.Set;

public class Directory extends DirectoryEntry {
    private Directory parent;
    private Set<DirectoryEntry> contents;

    private Directory() {}

    public Directory(String name, Directory newParent) {
        setName(name);
        this.parent = newParent;
        this.contents = new LinkedHashSet<DirectoryEntry>();
    }

    public Set<DirectoryEntry> getContents() {
        return contents;
    }

    public DirectoryEntry getParent() {
        return parent;
    }
    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
