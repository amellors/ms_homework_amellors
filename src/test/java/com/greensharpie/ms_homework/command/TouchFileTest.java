package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.system.SystemData;

public class TouchFileTest {

    @Test
    public void simpleTouchFile()
    {
        SystemData system_data = new SystemData();

        new TouchFile("new_file").exec(system_data);

        assertEquals(1, system_data.getCwd().getContents().size());
    }

    @Test
    public void touchMultipleFiles()
    {
        SystemData system_data = new SystemData();

        String[] dirNames = {"newFile1", "newFile2", "newFile3", "newDir4newFile4"};

        for (String name: dirNames) {
            new TouchFile(name).exec(system_data);
        }

        assertEquals(dirNames.length, system_data.getCwd().getContents().size());
    }

    @Test
    public void disallowSameNamesFiles()
    {
        SystemData system_data = new SystemData();

        new TouchFile("new_file").exec(system_data);

        assertEquals(1, system_data.getCwd().getContents().size());

        new TouchFile("new_file").exec(system_data);

        assertEquals(1, system_data.getCwd().getContents().size());
    }

    @Test
    public void allowFilesInDifferentDirs()
    {
        SystemData system_data = new SystemData();

        new TouchFile("new_file").exec(system_data);
        new MakeDirectory("dir1").exec(system_data);

        assertEquals(2, system_data.getCwd().getContents().size());

        new ChangeDirectory("dir1").exec(system_data);
        new TouchFile("new_file").exec(system_data);

        assertEquals(1, system_data.getCwd().getContents().size());
    }    
}
