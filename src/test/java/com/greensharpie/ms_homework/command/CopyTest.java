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

import com.greensharpie.ms_homework.system.NotYetImplementedException;
import com.greensharpie.ms_homework.system.SystemData;

public class CopyTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void simpleCopyFile()
    {
        SystemData system_data = new SystemData();
        assertDoesNotThrow(() -> {
            new TouchFile("old_file").exec(system_data);
            new WriteFile("old_file", "Old file contents").exec(system_data);
            new Copy("old_file", "new_file").exec(system_data);
            new ReadFile("new_file").exec(system_data);
        });

        assertEquals(2, system_data.getCwd().getContents().size());
        assertNotNull(system_data.getCwd().getEntry("new_file"));
        assertEquals("Old file contents", outputStreamCaptor.toString().trim());
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
        
            new Copy("newFile1", "newFile2").exec(system_data);
        });
        assertTrue(exception.getMessage().contains("Directory already contains item named:"));
    }

    @Test
    public void fileDoesNotExist()
    {
        SystemData system_data = new SystemData();
        Exception exception = assertThrows(FileNotFoundException.class, () ->
            new Copy("newfile1", "newfile2").exec(system_data));
        assertTrue(exception.getMessage().contains("Could not find an entry with name:"));
    }

    @Test
    public void cannotCopyDirectory()
    {
        SystemData system_data = new SystemData();
        
        Exception exception = assertThrows(NotYetImplementedException.class, () -> {
            new MakeDirectory("old_file").exec(system_data);
            new Copy("old_file", "newFile2").exec(system_data);
        });
        assertTrue(exception.getMessage().contains("Directory copy is not yet implemented"));
    }    
}
