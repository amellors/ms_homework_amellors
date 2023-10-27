package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.system.SystemData;

public class ReadWriteFileTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @Test
    public void filenotFoundToRead()
    {
        SystemData system_data = new SystemData();

        Exception exception = assertThrows(FileNotFoundException.class, () ->
            new ReadFile("file").exec(system_data));
        assertTrue(exception.getMessage().contains("Could not find a file with name:"));
    }

    @Test
    public void simpleReadWrite()
    {
        SystemData system_data = new SystemData();
        assertDoesNotThrow(() -> {
            new TouchFile("file").exec(system_data);
            new WriteFile("file", "Contents of file").exec(system_data);
            new ReadFile("file").exec(system_data);
        });

        assertEquals("Contents of file", outputStreamCaptor.toString().trim());
    }
    
    @Test
    public void cannotReadFromDirectory()
    {
        SystemData system_data = new SystemData();
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            new MakeDirectory("dir").exec(system_data);
            new ReadFile("dir").exec(system_data);
        });
        assertTrue(exception.getMessage().contains("Could not find a file with name:"));
    }
    
    @Test
    public void cannotWriteToDirectory()
    {
        SystemData system_data = new SystemData();
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            new MakeDirectory("dir").exec(system_data);
            new WriteFile("dir", "Contents don't matter").exec(system_data);
        });
        assertTrue(exception.getMessage().contains("Could not find a file with name:"));
    }
}
