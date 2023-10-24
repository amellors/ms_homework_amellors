package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.system.SystemData;

public class PrintWorkingDirectoryTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void rootDir()
    {
        SystemData system_data = new SystemData();

        new PrintWorkingDirectory().exec(system_data);

        assertEquals("/", outputStreamCaptor.toString().trim());
    }

    @Test
    public void rootWithMultipleDirs()
    {
        SystemData system_data = new SystemData();
        String[] dirNames = {"newDir1", "newDir2", "newDir3", "newDir4"};
        for (String name: dirNames) {
            new MakeDirectory(name).exec(system_data);
        }
        new PrintWorkingDirectory().exec(system_data);

        assertEquals("/", outputStreamCaptor.toString().trim());
    }

    @Test
    public void oneDirPath()
    {
        SystemData system_data = new SystemData();
        String[] dirNames = {"newDir1", "newDir2", "newDir3", "newDir4"};
        for (String name: dirNames) {
            new MakeDirectory(name).exec(system_data);
        }
        new ChangeDirectory("newDir3").exec(system_data);
        new PrintWorkingDirectory().exec(system_data);

        assertEquals("/newDir3", outputStreamCaptor.toString().trim());
    }

    @Test
    public void multipleDirPath()
    {
        SystemData system_data = new SystemData();
        String[] dirNames = {"newDir1", "newDir2", "newDir3", "newDir4"};
        for (String name: dirNames) {
            new MakeDirectory(name).exec(system_data);
        }
        new ChangeDirectory("newDir3").exec(system_data);
        for (String name: dirNames) {
            new MakeDirectory(name).exec(system_data);
        }
        new ChangeDirectory("newDir2").exec(system_data);
        new PrintWorkingDirectory().exec(system_data);

        assertEquals("/newDir3/newDir2", outputStreamCaptor.toString().trim());
    }  
}
