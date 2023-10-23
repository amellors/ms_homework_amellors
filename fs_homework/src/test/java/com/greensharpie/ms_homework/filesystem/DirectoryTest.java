package com.greensharpie.ms_homework.filesystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple DirectoryTest.
 */
public class DirectoryTest {

    @Test
    public void basicNameTests()
    {
        Directory dir1 = new Directory("test_name", null);
        assertTrue(dir1.getName().equals("test_name"));


        Directory dir2 = new Directory("", null);
        assertTrue(dir2.getName().isEmpty());
    }

    @Test
    public void basicParentTests()
    {
        Directory dir1 = new Directory("", null);
        Directory dir2 = new Directory("test_name", dir1);
        assertEquals(dir1, dir2.getParent());
    }

}
