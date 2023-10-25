package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
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
        new ReadFile("file").exec(system_data);

        assertEquals("Could not find a file with name: file", outputStreamCaptor.toString().trim());

        outputStreamCaptor.reset();
        new WriteFile("file", "Contents of file").exec(system_data);

        assertEquals("Could not find a file with name: file", outputStreamCaptor.toString().trim());
    }

    @Test
    public void simpleReadWrite()
    {
        SystemData system_data = new SystemData();
        new TouchFile("file").exec(system_data);
        new WriteFile("file", "Contents of file").exec(system_data);
        new ReadFile("file").exec(system_data);

        assertEquals("Contents of file", outputStreamCaptor.toString().trim());
    }
    
    @Test
    public void cannotReadFromDirectory()
    {
        SystemData system_data = new SystemData();
        new MakeDirectory("dir").exec(system_data);
        new ReadFile("dir").exec(system_data);

        assertEquals("Could not find a file with name: dir", outputStreamCaptor.toString().trim());
    }
    
    @Test
    public void cannotWriteToDirectory()
    {
        SystemData system_data = new SystemData();
        new MakeDirectory("dir").exec(system_data);
        new WriteFile("dir", "Contents don't matter").exec(system_data);

        assertEquals("Could not find a file with name: dir", outputStreamCaptor.toString().trim());
    }
}
