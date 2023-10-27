package com.greensharpie.ms_homework.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.filesystem.Directory;
import com.greensharpie.ms_homework.system.SystemData;

public class RemoveDirectoryTest { 
    @Test
    public void rmDirTest()
    {
        SystemData system_data = new SystemData();
        assertDoesNotThrow(() -> {
            new MakeDirectory("new_directory").exec(system_data);
            new MakeDirectory("new_directory2").exec(system_data);
            new RemoveDirectory("new_directory").exec(system_data);
        });

        Directory cwd = system_data.getCwd();
        
        assertEquals(1, cwd.getContents().size());
    }

    @Test
    public void rmDirNameNotFound()
    {
        SystemData system_data = new SystemData();

        Exception exception = assertThrows(FileNotFoundException.class, () ->
            new RemoveDirectory("non_existing_dir").exec(system_data));
        assertTrue(exception.getMessage().contains("Could not find directory with name:"));
    }
}
