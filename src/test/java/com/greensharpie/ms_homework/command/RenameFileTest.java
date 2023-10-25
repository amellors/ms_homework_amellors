package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        new TouchFile("old_file").exec(system_data);
        new RenameFile("old_file", "new_file").exec(system_data);

        assertEquals(1, system_data.getCwd().getContents().size());
        assertNotNull(system_data.getCwd().getEntry("new_file"));
    }

    @Test
    public void fileAlreadyExists()
    {
        SystemData system_data = new SystemData();

        String[] dirNames = {"newFile1", "newFile2", "newFile3", "newDir4newFile4"};

        for (String name: dirNames) {
            new TouchFile(name).exec(system_data);
        }

        new RenameFile("newFile1", "newFile2").exec(system_data);

        assertEquals("Directory already contains entry with name: newFile2", outputStreamCaptor.toString().trim());
    }

    @Test
    public void fileDoesNotExist()
    {
        SystemData system_data = new SystemData();
        new RenameFile("newFile1", "newFile2").exec(system_data);

        assertEquals("Could not find a file with name: newFile1", outputStreamCaptor.toString().trim());
    }

    @Test
    public void cannotRenameDirectory()
    {
        // Although lets be honest this should actually be fine.
        SystemData system_data = new SystemData();
        new MakeDirectory("old_file").exec(system_data);
        new RenameFile("old_file", "newFile2").exec(system_data);

        assertEquals("Could not find a file with name: old_file", outputStreamCaptor.toString().trim());
    }
}
