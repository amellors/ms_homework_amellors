package com.greensharpie.ms_homework;

import com.greensharpie.ms_homework.command.ListContents;
import com.greensharpie.ms_homework.command.MakeDirectory;
import com.greensharpie.ms_homework.system.SystemData;

/**
 * Hello world!
 *
 */
public class App 
{
    private final static SystemData system_data = new SystemData();

    public static void main( String[] args )
    {
        new MakeDirectory("school").exec(system_data);
        new ListContents().exec(system_data);
    }

}
