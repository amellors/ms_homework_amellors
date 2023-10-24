package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.system.SystemData;

public class MakeDirectoryTest {

    @Test
    public void simpleMakeDir()
    {
        SystemData system_data = new SystemData();
        Directory root_dir = system_data.getCwd();

        new MakeDirectory("new_directory").exec(system_data);

        assertEquals(1, root_dir.getContents().size());
    }

    @Test
    public void makeMultipleDirs()
    {
        SystemData system_data = new SystemData();
        Directory root_dir = system_data.getCwd();

        String[] dirNames = {"newDir1", "newDir2", "newDir3", "newDir4"};

        for (String name: dirNames) {
            new MakeDirectory(name).exec(system_data);
        }

        assertEquals(dirNames.length, root_dir.getContents().size());
    }

    @Test
    public void disallowSameNamesMakeDir()
    {
        SystemData system_data = new SystemData();
        Directory root_dir = system_data.getCwd();

        new MakeDirectory("new_directory").exec(system_data);

        assertEquals(1, root_dir.getContents().size());

        new MakeDirectory("new_directory").exec(system_data);

        assertEquals(1, root_dir.getContents().size());
    }
    
}
