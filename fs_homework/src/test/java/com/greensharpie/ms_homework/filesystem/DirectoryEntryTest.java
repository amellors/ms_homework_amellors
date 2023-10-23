package com.greensharpie.ms_homework.filesystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DirectoryEntryTest {
    @Test
    public void renameDirectory()
    {
        DirectoryEntry dirEnt = new DirectoryEntry("");
        dirEnt.setName("new_root");
        assertEquals("new_root", dirEnt.getName());
    }

    @Test
    public void toStringTest()
    {
        DirectoryEntry dirEnt = new DirectoryEntry("dirName");
        assertEquals("DirectoryEntry [name=dirName]", dirEnt.toString());
    }

    @Test
    public void equalsTests()
    {
        DirectoryEntry dirEnt = new DirectoryEntry("dirName");
        DirectoryEntry dirEnt2 = new DirectoryEntry("dirName");
        DirectoryEntry dirEnt3 = new DirectoryEntry("otherName");


        assertTrue(dirEnt.equals(dirEnt));
        assertTrue(dirEnt.equals(dirEnt2));
        assertFalse(dirEnt.equals(dirEnt3));
        assertFalse(dirEnt.equals("BooFar"));
    }
}
