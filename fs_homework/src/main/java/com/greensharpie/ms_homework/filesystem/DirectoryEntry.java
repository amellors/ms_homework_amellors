package com.greensharpie.ms_homework.filesystem;

import java.util.Objects;

public class DirectoryEntry {

    private String name;

    protected DirectoryEntry() {}

    public DirectoryEntry(String name) {
        this.name = name;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof DirectoryEntry)) {
            return false;
        }

        return this.name == ((DirectoryEntry)o).name;
    }
}
