package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class MipsProcessor {
//    public static ArrayList<String> toBinaryInstructions(ArrayList<String> instructionList) {
//        ArrayList<String> binaryInstructionList = new ArrayList<String>();
//        for (int i = 0; i < instructionList.size(); i++) {
//
//            binaryInstructionList.add(assemblyToBinaryConverter(instructionList.get(i)));
//        }
//        return binaryInstructionList;
//    }


    static int beqOrBneLineNo=0;
    static int branchLineNo=0;
    static String [] returnOpCode(String instruction) {
        String[] opCode = new String[2];

        if (instruction.equalsIgnoreCase("add")) {
            opCode[0] = "000000";
            opCode[1] = "100000";
        } else if (instruction.equalsIgnoreCase("addi")) {
            opCode[0] = "001000";
            opCode[1] = "";
        } else if (instruction.equalsIgnoreCase("sub")) {
            opCode[0] = "000000";
            opCode[1] = "100010";
        } else if (instruction.equalsIgnoreCase("sub")) {
            opCode[0] = "000000";
            opCode[1] = "100010";
        } else if (instruction.equalsIgnoreCase("and")) {
            opCode[0] = "000000";
            opCode[1] = "100100";
        } else if (instruction.equalsIgnoreCase("andi")) {
            opCode[0] = "001100";
            opCode[1] = "";
        } else if (instruction.equalsIgnoreCase("or")) {
            opCode[0] = "000000";
            opCode[1] = "100101";
        } else if (instruction.equalsIgnoreCase("ori")) {
            opCode[0] = "001101";
            opCode[1] = "";
        } else if (instruction.equalsIgnoreCase("nor")) {
            opCode[0] = "000000";
            opCode[1] = "100111";
        } else if (instruction.equalsIgnoreCase("sll")) {
            opCode[0] = "000000";
            opCode[1] = "000000";
        } else if (instruction.equalsIgnoreCase("srl")) {
            opCode[0] = "000000";
            opCode[1] = "000010";
        } else if (instruction.equalsIgnoreCase("sra")) {
            opCode[0] = "000000";
            opCode[1] = "000011";
        } else if (instruction.equalsIgnoreCase("slt")) {
            opCode[0] = "000000";
            opCode[1] = "101010";
        } else if (instruction.equalsIgnoreCase("slti")) {
            opCode[0] = "001010";
            opCode[1] = "";
        } else if (instruction.equalsIgnoreCase("lui")) {
            opCode[0] = "001111";
            opCode[1] = "";
        }
        else if (instruction.equalsIgnoreCase("lw")) {
            opCode[0] = "100011";
            opCode[1] = "";
        }
        else if (instruction.equalsIgnoreCase("sw")) {
            opCode[0] = "101011";
            opCode[1] = "";
        }
        else if (instruction.equalsIgnoreCase("beq")) {
            opCode[0] = "000100";
            opCode[1] = "";
        }
        else if (instruction.equalsIgnoreCase("bne")) {
            opCode[0] = "000101";
            opCode[1] = "";
        }
        else if (instruction.equalsIgnoreCase("j")) {
            opCode[0] = "000010";
            opCode[1] = "";
        }

        return opCode;
    }
    public static String shiftCalculator(String str,String shiftAmountInDecimal){
        String string1 ="";
        Integer int1= Integer.parseInt(shiftAmountInDecimal);

        if(str.equalsIgnoreCase("sll")||str.equalsIgnoreCase("srl"))
        {
            string1=Integer.toBinaryString(int1);
            while (string1.length() != 5)
                string1 = "0" + string1;
        }

        if(str.equalsIgnoreCase("addi")||str.equalsIgnoreCase("andi")
                ||str.equalsIgnoreCase("ori")||str.equalsIgnoreCase("slti")){
            string1=Integer.toBinaryString(int1);
            while (string1.length()!=16)
                string1 = "0" + string1;
        }

        return string1;

    }
    public static String immediateCalculator( String immediateInDecimal){
        Integer int1= Integer.parseInt(immediateInDecimal);
        String string1=Integer.toBinaryString(int1);
        while (string1.length() != 16)
        { string1 = "0" + string1;}
        return string1;

    }
    public static String offsetCalculator(String str, String immediateInDecimal){
        Integer int1= Integer.parseInt(immediateInDecimal);
        String string1=Integer.toBinaryString(int1);
        while (string1.length() != 16)
        { string1 = "0" + string1;}
        return string1;

    }
    public static List<String> toBinaryInstructions(List<String> instructionList) {
        ArrayList<String> binaryInstructionList = new ArrayList<String>();
        for (int i = 0; i < instructionList.size(); i++) {

            binaryInstructionList.add(assemblyToBinaryConverter(instructionList.get(i)));
        }
        return binaryInstructionList;
    }

    public static ArrayList<String> lineIntoTokens(String string)
    {
        ArrayList<String> arrayList=new ArrayList<String>();
        StringTokenizer stringTokenizer =new StringTokenizer(string," ,():",false);
        while (stringTokenizer.hasMoreElements())
            arrayList.add(stringTokenizer.nextToken());
        return arrayList;
    }


    public static String assemblyToBinaryConverter(String assemblyString){
        HashMap<String,String> registerNumbering=new HashMap<String, String>();
        registerNumbering.put("$zero", "00000");//registerZero
        registerNumbering.put("$at", "00001");// 1
        registerNumbering.put("$v0", "00010");// 2
        registerNumbering.put("$v1", "00011");// 3
        registerNumbering.put("$a0", "00100");// 4
        registerNumbering.put("$a1", "00101");// 5
        registerNumbering.put("$a2", "00110");// 6
        registerNumbering.put("$a3", "00111");// 7
        registerNumbering.put("$t0", "01000");// 8
        registerNumbering.put("$t1", "01001");// 9
        registerNumbering.put("$t2", "01010");// 10
        registerNumbering.put("$t3", "01011");// 11
        registerNumbering.put("$t4", "01100");// 12
        registerNumbering.put("$t5", "01101");// 13
        registerNumbering.put("$t6", "01110");// 14
        registerNumbering.put("$t7", "01111");// 15
        registerNumbering.put("$s0", "10000");// 16
        registerNumbering.put("$s1", "10001");// 17
        registerNumbering.put("$s2", "10010");// 18
        registerNumbering.put("$s3", "10011");// 19
        registerNumbering.put("$s4", "10100");// 20
        registerNumbering.put("$s5", "10101");// 21
        registerNumbering.put("$s6", "10110");// 22
        registerNumbering.put("$s7", "10111");// 23
        registerNumbering.put("$t8", "11000");// 24
        registerNumbering.put("$t9", "11001");// 25
        registerNumbering.put("$k0", "11010");// 26
        registerNumbering.put("$k1", "11011");// 27
        registerNumbering.put("$gp", "11100");// 28
        registerNumbering.put("$sp", "11101");// 29
        registerNumbering.put("$fp", "11110");// 30
        registerNumbering.put("$ra", "11111");

        String string2;
        StringTokenizer strk = new StringTokenizer(assemblyString," ,():",false);

        ArrayList<String> oneInstructionIntoTokens=new ArrayList<String>();
        while (strk.hasMoreElements())
            oneInstructionIntoTokens.add(strk.nextToken());

        String [] opCode = returnOpCode(oneInstructionIntoTokens.get(0));


        if (oneInstructionIntoTokens.get(0).equalsIgnoreCase("sll")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("srl")){
            String shAmount= shiftCalculator(oneInstructionIntoTokens.get(0),oneInstructionIntoTokens.get(3));
            string2=opCode[0]+" 00000"+" "+registerNumbering.get(oneInstructionIntoTokens.get(2))+" "+registerNumbering.get(oneInstructionIntoTokens.get(1))+
                    "  "+shAmount+" "+opCode[1];
            return string2;

        }
        if (oneInstructionIntoTokens.get(0).equalsIgnoreCase("addi")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("andi")
                ||oneInstructionIntoTokens.get(0).equalsIgnoreCase("ori")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("slti")){

            String shAmount=shiftCalculator(oneInstructionIntoTokens.get(0),oneInstructionIntoTokens.get(3));
            string2= opCode[0]+registerNumbering.get(oneInstructionIntoTokens.get(2))+
                    registerNumbering.get(oneInstructionIntoTokens.get(1))+shAmount;
            return string2;
        }


        if (oneInstructionIntoTokens.get(0).equalsIgnoreCase("add")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("sub")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("and")
                ||oneInstructionIntoTokens.get(0).equalsIgnoreCase("or")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("nor")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("slt")) {
            string2=opCode[0]+registerNumbering.get(oneInstructionIntoTokens.get(2))+registerNumbering.get(oneInstructionIntoTokens.get(3))+
                      registerNumbering.get(oneInstructionIntoTokens.get(1))+"00000"+opCode[1];


            return string2;
        }


        if (oneInstructionIntoTokens.get(0).equalsIgnoreCase("addi")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("andi")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("ori")
                ||oneInstructionIntoTokens.get(0).equalsIgnoreCase("slti")){
            String immediate= immediateCalculator(oneInstructionIntoTokens.get(3));
            string2=opCode[0]+registerNumbering.get(oneInstructionIntoTokens.get(2))+registerNumbering.get(oneInstructionIntoTokens.get(1))+
                    immediate+opCode[1];
            return string2;

        }

        if (oneInstructionIntoTokens.get(0).equalsIgnoreCase("lw")||oneInstructionIntoTokens.get(0).equalsIgnoreCase("sw")){
            String immediate= immediateCalculator(oneInstructionIntoTokens.get(2));
            string2=opCode[0]+registerNumbering.get(oneInstructionIntoTokens.get(3))+registerNumbering.get(oneInstructionIntoTokens.get(1))+
                    immediate+opCode[1];
            return string2;

        }


        if (oneInstructionIntoTokens.get(0).equalsIgnoreCase("beq")||
                oneInstructionIntoTokens.get(0).equalsIgnoreCase("bne"))
        {


            //this loop is to calculate the beq or bne line number
            //and then we will get the difference from the line that
            //we will branch to


            for (int i=0;i<ReadFile.instructions.size();i++)
            {

                ArrayList<String>arrayList=new ArrayList<String>();


                arrayList=lineIntoTokens(ReadFile.instructions.get(i));


                if (arrayList.get(0).equalsIgnoreCase(oneInstructionIntoTokens.get(0))&&
                        arrayList.get(3).equalsIgnoreCase(oneInstructionIntoTokens.get(3))) {
                    beqOrBneLineNo=i;
                    break;
                }



                for (int p=0;p<arrayList.size();p++)
                arrayList.remove(p);


            }
            // this for loop is to calculate the number of the the line that we will branch to

            for (int t=0;t<ReadFile.instructions.size();t++)
            {
                ArrayList<String>arrayList2= new ArrayList<String>();

                arrayList2=lineIntoTokens(ReadFile.instructions.get(t));

                if (arrayList2.get(0).equalsIgnoreCase(oneInstructionIntoTokens.get(3))) {
                    branchLineNo=t;
                    break;
                }


                for (int p2=0;p2<arrayList2.size();p2++)
                    arrayList2.remove(p2);

            }

            Integer integer= new Integer((branchLineNo-beqOrBneLineNo)-1);

            if (integer<0)
                integer+=1;

            String addressInBinary=Integer.toBinaryString(integer);

            while (addressInBinary.length()<16)
                addressInBinary="0"+addressInBinary;



           string2=opCode[0]+" "+ registerNumbering.get(oneInstructionIntoTokens.get(1))+" "+
                    registerNumbering.get(oneInstructionIntoTokens.get(2))+" "+addressInBinary;

           return string2;




        }



//        if (oneInstructionIntoTokens.get(0).equalsIgnoreCase("j")){
//            String address= immediateCalculator(oneInstructionIntoTokens.get(1));
//            string2=opCode[0]+" "+address+" "+opCode[1];// needs revision and work on address size (should be 26 bits)
//            return string2;
//
//        }

//        string2=opCode[0]+"  00000 "+registerNumbering.get(oneInstructionIntoTokens.get(1))+" "+immediateCalculator(oneInstructionIntoTokens.get(2))+
//                " "+opCode[1];//in case of lui
        return "dd  ";


    }










    public static void main(String[] args) {
        ReadFile file1 = new ReadFile("java.txt");
        file1.convert();

        ArrayList<String> arrayList=new ArrayList<String>();
        for (int i=0;i<ReadFile.instructions.size();i++)
            arrayList.add(ReadFile.instructions.get(i));


        List<String> instructionsInBinary=new ArrayList<String>();

        for (int i =0;i<ReadFile.instructions.size();i++)
            instructionsInBinary.add(assemblyToBinaryConverter(ReadFile.instructions.get(i)));

        System.out.println(instructionsInBinary);

        List<String> binaryInstructionList=toBinaryInstructions(ReadFile.instructions);

        AssemblyProcessor assemblyProcessor=new AssemblyProcessor();
        assemblyProcessor.process(binaryInstructionList);







    }
}
