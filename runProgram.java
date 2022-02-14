package com.company;

public class runProgram {

    static int lineNo;
    public static void Control(String name){

        ReadFile file1 = new ReadFile(name);
        file1.convert();
    }

}
