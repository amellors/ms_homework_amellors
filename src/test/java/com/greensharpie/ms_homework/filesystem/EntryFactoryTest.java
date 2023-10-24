package com.greensharpie.ms_homework.filesystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EntryFactoryTest {
        /**
     * Rigorous Test :-)
     */
    @Test
    public void directoryIsCreatedCorrectly()
    {
        Directory dir1 = EntryFactory.createDirectory("", null);
        assertNotNull(dir1);
        assertTrue(dir1.getName().isEmpty());
        assertNull(dir1.getParent());

        Directory dir2 = EntryFactory.createDirectory("test_dir", dir1);
        assertNotNull(dir2);
        assertEquals("test_dir", dir2.getName());
        assertEquals(dir1, dir2.getParent());
    }
}
