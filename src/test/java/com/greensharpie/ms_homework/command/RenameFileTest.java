package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.FileAlreadyExistsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.system.SystemData;

public class RenameFileTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void simpleRenameFile()
    {
        SystemData system_data = new SystemData();
        assertDoesNotThrow(() -> {
            new TouchFile("old_file").exec(system_data);
            new RenameFile("old_file", "new_file").exec(system_data);
        });

        assertEquals(1, system_data.getCwd().getContents().size());
        assertNotNull(system_data.getCwd().getEntry("new_file"));
    }

    @Test
    public void fileAlreadyExists()
    {
        SystemData system_data = new SystemData();

        String[] dirNames = {"newFile1", "newFile2", "newFile3", "newFile4"};

        Exception exception = assertThrows(FileAlreadyExistsException.class, () -> {
            for (String name: dirNames) {
                new TouchFile(name).exec(system_data);
            }
        
            new RenameFile("newFile1", "newFile2").exec(system_data);
        });
        assertTrue(exception.getMessage().contains("Directory already contains item named:"));
    }

    @Test
    public void fileDoesNotExist()
    {
        SystemData system_data = new SystemData();
        Exception exception = assertThrows(FileNotFoundException.class, () ->
            new RenameFile("newfile1", "newfile2").exec(system_data));
        assertTrue(exception.getMessage().contains("Could not find a file with name:"));
    }

    @Test
    public void cannotRenameDirectory()
    {
        // Although lets be honest this should actually be fine.
        SystemData system_data = new SystemData();
        
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            new MakeDirectory("old_file").exec(system_data);
            new RenameFile("old_file", "newFile2").exec(system_data);
        });
        assertTrue(exception.getMessage().contains("Could not find a file with name:"));
    }
}
