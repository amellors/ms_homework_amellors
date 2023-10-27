package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.FileAlreadyExistsException;

import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.system.SystemData;

public class TouchFileTest {

    @Test
    public void simpleTouchFile()
    {
        SystemData system_data = new SystemData();

        assertDoesNotThrow(() -> {
            new TouchFile("new_file").exec(system_data);
        });

        assertEquals(1, system_data.getCwd().getContents().size());
    }

    @Test
    public void touchMultipleFiles()
    {
        SystemData system_data = new SystemData();

        String[] dirNames = {"newFile1", "newFile2", "newFile3", "newFile4"};

        assertDoesNotThrow(() -> {
            for (String name: dirNames) {
                new TouchFile(name).exec(system_data);
            }
        });

        assertEquals(dirNames.length, system_data.getCwd().getContents().size());
    }

    @Test
    public void disallowSameNamesFiles()
    {
        SystemData system_data = new SystemData();

        assertDoesNotThrow(() -> {
            new TouchFile("new_file").exec(system_data);
        });
        assertEquals(1, system_data.getCwd().getContents().size());

        Exception exception = assertThrows(FileAlreadyExistsException.class, () ->
            new TouchFile("new_file").exec(system_data));
        assertTrue(exception.getMessage().contains("Directory already contains item named:"));

        assertEquals(1, system_data.getCwd().getContents().size());
    }

    @Test
    public void allowFilesInDifferentDirs()
    {
        SystemData system_data = new SystemData();

        assertDoesNotThrow(() -> {
            new TouchFile("new_file").exec(system_data);
            new MakeDirectory("dir1").exec(system_data);

            assertEquals(2, system_data.getCwd().getContents().size());

            new ChangeDirectory("dir1").exec(system_data);
            new TouchFile("new_file").exec(system_data);

            assertEquals(1, system_data.getCwd().getContents().size());
        });
    }    
}
