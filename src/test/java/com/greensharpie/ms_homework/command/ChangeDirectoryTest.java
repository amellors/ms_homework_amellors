package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.system.SystemData;

public class ChangeDirectoryTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void alreadyAtRoot()
    {
        SystemData system_data = new SystemData();

        new ChangeDirectory("..").exec(system_data);

        assertEquals("Already at root", outputStreamCaptor.toString().trim());
    }

    @Test
    public void dirNameNotFound()
    {
        SystemData system_data = new SystemData();

        new ChangeDirectory("non_existing_dir").exec(system_data);

        assertEquals("Could not find directory with name: non_existing_dir", outputStreamCaptor.toString().trim());
    }

    @Test
    public void cannotChangeDirectoryToFile()
    {
        SystemData system_data = new SystemData();
        new TouchFile("old_file").exec(system_data);
        new ChangeDirectory("old_file").exec(system_data);

        assertEquals("Could not find directory with name: old_file", outputStreamCaptor.toString().trim());
    }

    @Test
    public void oneDirChange()
    {
        SystemData system_data = new SystemData();
        new MakeDirectory("new_directory").exec(system_data);
        new ChangeDirectory("new_directory").exec(system_data);

        Directory cwd = system_data.getCwd();
        assertEquals("new_directory", cwd.getName());
    }

    @Test
    public void twoDirChange()
    {
        SystemData system_data = new SystemData();
        new MakeDirectory("new_directory").exec(system_data);
        new ChangeDirectory("new_directory").exec(system_data);
        new MakeDirectory("another_new_directory").exec(system_data);
        new ChangeDirectory("another_new_directory").exec(system_data);

        Directory cwd = system_data.getCwd();
        assertEquals("another_new_directory", cwd.getName());
    }

    @Test
    public void multipleDirChanges()
    {
        SystemData system_data = new SystemData();
        String[] dirNames = {"newDir1", "newDir2", "newDir3", "newDir4"};
        for (String name: dirNames) {
            new MakeDirectory(name).exec(system_data);
        }
        new ChangeDirectory("newDir1").exec(system_data);
        Directory cwd = system_data.getCwd();
        assertEquals("newDir1", cwd.getName());


        new ChangeDirectory("..").exec(system_data);
        new ChangeDirectory("newDir3").exec(system_data);
        cwd = system_data.getCwd();
        assertEquals("newDir3", cwd.getName());
    }

    @Test
    public void goUpOneDirectory()
    {
        SystemData system_data = new SystemData();
        new MakeDirectory("new_directory").exec(system_data);
        new ChangeDirectory("new_directory").exec(system_data);
        new MakeDirectory("another_new_directory").exec(system_data);
        new ChangeDirectory("another_new_directory").exec(system_data);
        new ChangeDirectory("..").exec(system_data);

        Directory cwd = system_data.getCwd();
        assertEquals("new_directory", cwd.getName());
    }
}
