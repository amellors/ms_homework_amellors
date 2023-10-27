package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.system.SystemData;

public class RemoveDirectoryTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void rmDirTest()
    {
        SystemData system_data = new SystemData();
        try {
            new MakeDirectory("new_directory").exec(system_data);
            new MakeDirectory("new_directory2").exec(system_data);
            new RemoveDirectory("new_directory").exec(system_data);
        }
        catch (Exception e) {
            fail("Shouldn't be trowing an exception");
        }
        Directory cwd = system_data.getCwd();
        
        assertEquals(1, cwd.getContents().size());
    }

    @Test
    public void rmDirNameNotFound()
    {
        SystemData system_data = new SystemData();

        new RemoveDirectory("non_existing_dir").exec(system_data);

        assertEquals("Could not find directory with name: non_existing_dir", outputStreamCaptor.toString().trim());
    }
}
