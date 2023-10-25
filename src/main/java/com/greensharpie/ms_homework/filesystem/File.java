package com.greensharpie.ms_homework.filesystem;

public class File extends DirectoryEntry {
    private String contents;

    protected File(String name) {
        super(name);
        this.contents = "";
    }

    protected File(String name, String contents) {
        super(name);
        this.contents = contents;
    }

    public void setContents(String newContents) {
        this.contents = newContents;
    }

    public String getContents() {
        return contents;
    }  
}
