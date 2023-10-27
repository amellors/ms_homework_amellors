package com.greensharpie.ms_homework.command;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.greensharpie.ms_homework.system.SystemData;

public class DirectoryStackTest {

    @Test
    public void emptyStackPop() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        SystemData system_data = new SystemData();

        new Popd().exec(system_data);
        assertEquals("Directory stack is empty", outputStreamCaptor.toString().trim());
    }

    @Test
    public void simplePop() {
        SystemData system_data = new SystemData();

        assertDoesNotThrow(() -> {
            new MakeDirectory("newDir").exec(system_data);
            new Pushd().exec(system_data);
            new ChangeDirectory("newDir").exec(system_data);
            new MakeDirectory("newDir2").exec(system_data);
            new ChangeDirectory("newDir2").exec(system_data);
            new Popd().exec(system_data);
        });

        assertEquals("", system_data.getCwd().getName());
    }

    @Test
    public void complexPop() {
        SystemData system_data = new SystemData();

        assertDoesNotThrow(() -> {
            new MakeDirectory("newDir").exec(system_data);
            new ChangeDirectory("newDir").exec(system_data);
            new Pushd().exec(system_data);
            new MakeDirectory("newDir2").exec(system_data);
            new ChangeDirectory("newDir2").exec(system_data);
            new Pushd().exec(system_data);
            new MakeDirectory("newDir3").exec(system_data);
            new ChangeDirectory("newDir3").exec(system_data);
            new ChangeDirectory("..").exec(system_data);
            new ChangeDirectory("..").exec(system_data);
            new ChangeDirectory("..").exec(system_data);
            new Popd().exec(system_data);
        });

        assertEquals("newDir2", system_data.getCwd().getName());
    }

    @Test
    public void overPopStack() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        assertDoesNotThrow(() -> {
            SystemData system_data = new SystemData();
            new MakeDirectory("newDir").exec(system_data);
            new Pushd().exec(system_data);
            new ChangeDirectory("newDir").exec(system_data);
            new MakeDirectory("newDir2").exec(system_data);
            new ChangeDirectory("newDir2").exec(system_data);
            new Popd().exec(system_data);
                        
            new Popd().exec(system_data);
        });

        assertEquals("Directory stack is empty", outputStreamCaptor.toString().trim());
    }
}
