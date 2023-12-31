package com.greensharpie.ms_homework.filesystem;

import java.util.LinkedHashSet;
import java.util.Set;

public class Directory extends DirectoryEntry {
    private Directory parent;
    private Set<DirectoryEntry> contents;

    protected Directory(String name, Directory newParent) {
        super(name);
        this.parent = newParent;
        this.contents = new LinkedHashSet<DirectoryEntry>();
    }

    public Set<DirectoryEntry> getContents() {
        return contents;
    }

    public DirectoryEntry getEntry(String entryName) {
        for (DirectoryEntry possibleEntry: contents) {
            if (possibleEntry.getName().equals(entryName))
                return possibleEntry;
        }

        return null;
    }

    public Directory getParent() {
        return parent;
    }
    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
