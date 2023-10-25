package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.system.SystemData;

public class RemoveFileTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void rmDirTest()
    {
        SystemData system_data = new SystemData();
        new MakeDirectory("new_directory").exec(system_data);
        new TouchFile("new_file").exec(system_data);
        new TouchFile("new_file2").exec(system_data);
        new RemoveFile("new_file").exec(system_data);
        Directory cwd = system_data.getCwd();
        
        assertEquals(2, cwd.getContents().size());
    }

    @Test
    public void rmCannotRemoveDirectory()
    {
        SystemData system_data = new SystemData();

        new MakeDirectory("new_directory").exec(system_data);
        new RemoveFile("new_directory").exec(system_data);

        assertEquals("Could not find file with name: new_directory", outputStreamCaptor.toString().trim());
    }

    @Test
    public void rmDirNameNotFound()
    {
        SystemData system_data = new SystemData();

        new RemoveFile("non_existing_file").exec(system_data);

        assertEquals("Could not find file with name: non_existing_file", outputStreamCaptor.toString().trim());
    }
}