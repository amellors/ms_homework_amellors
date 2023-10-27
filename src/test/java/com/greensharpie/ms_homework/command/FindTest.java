package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.system.SystemData;

public class FindTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void emptyDir()
    {
        SystemData system_data = new SystemData();

        new Find("No_File").exec(system_data);

        assertEquals("No entry with name No_File was found.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void directoryItem()
    {
        SystemData system_data = new SystemData();

        new MakeDirectory("newDir").exec(system_data);
        new Find("newDir").exec(system_data);

        assertEquals("Found a Directory with name newDir", outputStreamCaptor.toString().trim());
    }
    
    @Test
    public void fileItem()
    {
        SystemData system_data = new SystemData();

        new TouchFile("newFile").exec(system_data);
        new Find("newFile").exec(system_data);

        assertEquals("Found a File with name newFile", outputStreamCaptor.toString().trim());
    }
}
