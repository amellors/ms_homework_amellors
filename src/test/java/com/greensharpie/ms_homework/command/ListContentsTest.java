package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.system.SystemData;

public class ListContentsTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void emptyDir()
    {
        SystemData system_data = new SystemData();

        new ListContents().exec(system_data);

        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void listMultipleDirs()
    {
        SystemData system_data = new SystemData();
        String[] dirNames = {"newDir1", "newDir2", "newDir3", "newDir4"};
        for (String name: dirNames) {
            new MakeDirectory(name).exec(system_data);
        }

        new ListContents().exec(system_data);

        assertEquals("newDir1, newDir2, newDir3, newDir4", outputStreamCaptor.toString().trim());
    }

    @Test
    public void oneDirList()
    {
        SystemData system_data = new SystemData();
        new MakeDirectory("new_directory").exec(system_data);
        new ListContents().exec(system_data);

        assertEquals("new_directory", outputStreamCaptor.toString().trim());
    }
}
