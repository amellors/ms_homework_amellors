package com.greensharpie.ms_homework.filesystem;

public abstract class DirectoryEntry {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DirectoryEntry [name=" + name + "]";
    }
}
