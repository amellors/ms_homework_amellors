package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.FileAlreadyExistsException;

import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.system.SystemData;

public class MakeDirectoryTest {

    @Test
    public void simpleMakeDir()
    {
        SystemData system_data = new SystemData();
        Directory root_dir = system_data.getCwd();

        try {
            new MakeDirectory("new_directory").exec(system_data);
        }
        catch (Exception e) {
            fail("Shouldn't be trowing an exception");
        }

        assertEquals(1, root_dir.getContents().size());
    }

    @Test
    public void makeMultipleDirs()
    {
        SystemData system_data = new SystemData();
        Directory root_dir = system_data.getCwd();

        String[] dirNames = {"newDir1", "newDir2", "newDir3", "newDir4"};

        try {
            for (String name: dirNames) {
                new MakeDirectory(name).exec(system_data);
            }
        }
        catch (Exception e) {
            fail("Shouldn't be trowing an exception");
        }

        assertEquals(dirNames.length, root_dir.getContents().size());
    }

    @Test
    public void disallowSameNamesMakeDir()
    {
        SystemData system_data = new SystemData();
        Directory root_dir = system_data.getCwd();

        try {
            new MakeDirectory("new_directory").exec(system_data);
        }
        catch (Exception e) {
            fail("Shouldn't be trowing an exception");
        }

        assertEquals(1, root_dir.getContents().size());

        Exception exception = assertThrows(FileAlreadyExistsException.class, () ->
            new MakeDirectory("new_directory").exec(system_data));
        assertTrue(exception.getMessage().contains("Directory already contains item named:"));
        assertEquals(1, root_dir.getContents().size());
    }
    
}
