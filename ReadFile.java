package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFile {

    static int x;
    public static ArrayList<String>instructions=new ArrayList<>();

    ReadFile(String name) {
        try {
            FileInputStream assemblyText = new FileInputStream(name);
            Scanner fileScanner = new Scanner(assemblyText);
            while (fileScanner.hasNext()) {
                instructions.add(fileScanner.nextLine());
            }
            fileScanner.close();
        }
        catch (IOException e){
            System.out.println("Could not find the file");}
    }
    public static  String readForOffset(int currentAddress, String label){

        for(int i=currentAddress;i<instructions.size();i++){

            StringTokenizer strk = new StringTokenizer(instructions.get(i)," ,():",false);

            ArrayList<String>cc=new ArrayList<>();
            while (strk.hasMoreElements()){
                cc.add(strk.nextToken());}
            if (label.equalsIgnoreCase(cc.get(0)))
                return Integer.toString(i-currentAddress);// revise if the label is in a previous line not a following one what will happen ?
        }

        return " ";
    }


    public  void convert(){


        for(int i=0;i<instructions.size();i++)
        {

            System.out.println((i+1)+": "+MipsProcessor.assemblyToBinaryConverter(instructions.get(i)));

        }

    }
    public static int currentLineNo (String funcName, String term1, String term2, String term3)
    {
        int LineNo=0;
        for(int i=0;i<instructions.size();i++)
        {
            StringTokenizer strk = new StringTokenizer(instructions.get(i)," ,()",false);
            ArrayList<String> cc=new ArrayList<>();
            while (strk.hasMoreElements()){
                cc.add(strk.nextToken());}
//            for(int j=0;j<instructions.size();j++)
//            {
//                LineNo++;
//                if(funcName.equalsIgnoreCase(cc.get(0))&&term1.equalsIgnoreCase(cc.get(1))
//                        && term2.equalsIgnoreCase(cc.get(2))&&term3.equalsIgnoreCase(cc.get(3)))
//                    break;
//            }

            if(funcName.equalsIgnoreCase(cc.get(0))&&term1.equalsIgnoreCase(cc.get(1))
                        && term2.equalsIgnoreCase(cc.get(2))&&term3.equalsIgnoreCase(cc.get(3)))
                    break;

            LineNo++;


        }
        return LineNo;
    }



}





