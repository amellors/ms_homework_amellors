package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.filesystem.Directory;
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

        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void directoryItem()
    {
        SystemData system_data = new SystemData();
        assertDoesNotThrow(() -> {
            new MakeDirectory("newDir").exec(system_data);
            new Find("newDir").exec(system_data);
        });

        assertEquals("Found a Directory with name newDir", outputStreamCaptor.toString().trim());
    }
    
    @Test
    public void fileItem()
    {
        SystemData system_data = new SystemData();
        assertDoesNotThrow(() -> {
            new TouchFile("newFile").exec(system_data);
            new Find("newFile").exec(system_data);
        });

        assertEquals("Found a File with name newFile", outputStreamCaptor.toString().trim());
    }

    /// =============== Recursive finds:

    @Test
    public void emptyDir_recurse()
    {
        SystemData system_data = new SystemData();

        new Find("No_File", true).exec(system_data);

        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void directoryItem_recurse()
    {
        SystemData system_data = new SystemData();

        assertDoesNotThrow(() -> {
            new MakeDirectory("newDir").exec(system_data);
            new Find("newDir", true).exec(system_data);
        });

        assertEquals("Found a Directory with name newDir", outputStreamCaptor.toString().trim());
    }
    
    @Test
    public void fileItem_recurse()
    {
        SystemData system_data = new SystemData();

        assertDoesNotThrow(() -> {
            new TouchFile("newFile").exec(system_data);
            new Find("newFile", true).exec(system_data);
        });

        assertEquals("Found a File with name newFile", outputStreamCaptor.toString().trim());
    }

    @Test
    public void deepFind_recurse()
    {
        SystemData system_data = new SystemData();
        Directory root = system_data.getCwd();

        assertDoesNotThrow(() -> {
            new MakeDirectory("newDir").exec(system_data);
            new ChangeDirectory("newDir").exec(system_data);
            new MakeDirectory("newDir2").exec(system_data);
            new ChangeDirectory("newDir2").exec(system_data);
            new TouchFile("newFile").exec(system_data);
            new MakeDirectory("newDir3").exec(system_data);
            new ChangeDirectory("newDir3").exec(system_data);
            new TouchFile("newFile").exec(system_data);

            system_data.setCwd(root);
            new Find("newFile", true).exec(system_data);
        });

        assertEquals("Found a File with name newDir/newDir2/newFile\nFound a File with name newDir/newDir2/newDir3/newFile", outputStreamCaptor.toString().trim());
    }


}
