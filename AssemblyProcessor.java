package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssemblyProcessor {

    HashMap<String, Integer> registers = new HashMap<>();

    public AssemblyProcessor() {
        this.registers.put("00000", 0);
        this.registers.put("00001", 0);
        this.registers.put("00010", 0);
        this.registers.put("00011", 0);
        this.registers.put("00100", 0);
        this.registers.put("00101", 0);
        this.registers.put("00110", 0);
        this.registers.put("00111", 0);
        this.registers.put("01000", 0);
        this.registers.put("01001", 0);
        this.registers.put("01010", 0);
        this.registers.put("01011", 0);
        this.registers.put("01100", 0);
        this.registers.put("01101", 0);
        this.registers.put("01110", 0);
        this.registers.put("01111", 0);
        this.registers.put("10000", 0);
        this.registers.put("10001", 0);
        this.registers.put("10010", 0);
        this.registers.put("10011", 4);
        this.registers.put("10100", 0);
        this.registers.put("10101", 5);
        this.registers.put("10110", 0);
        this.registers.put("10111", 0);
        this.registers.put("11000", 0);
        this.registers.put("11001", 0);
        this.registers.put("11010", 0);
        this.registers.put("11011", 0);
        this.registers.put("11100", 0);
        this.registers.put("11101", 0);
        this.registers.put("11110", 0);
        this.registers.put("11111", 0);
    }

    public void process(List<String> binaryInstructionList) {

        for (int i = 0; i < binaryInstructionList.size(); i++) {

            processInstruction(binaryInstructionList.get(i));
        }

    }

    public void processInstruction(String binaryInstruction) {
        // we identify the first 6 digitd of the binary number to know the operation and the type
        // we divide the binary number depending on the type of the operation
        //do the operation
        //we transform these part of the binary number to values
        //put them in the hashmap
        String operationCode = operationCodeDigits(binaryInstruction);
        String functionDigits = functionDigits(binaryInstruction);
        if (operationCode.equalsIgnoreCase("000000") && functionDigits.equalsIgnoreCase("100000")) {
            this.addOperation(binaryInstruction);
        }
        if (operationCode.equalsIgnoreCase("001000")) {
            addImmediateOperation(binaryInstruction);
        }
        if (operationCode.equalsIgnoreCase("000000") && functionDigits.equalsIgnoreCase("100100")) {
            this.andOperation(binaryInstruction);
        }
        if (operationCode.equalsIgnoreCase("001100")) {
            this.andImediateOperation(binaryInstruction);
        }
        if(operationCode.equalsIgnoreCase("000000")&& functionDigits.equalsIgnoreCase("100101")){
            this.orOperation(binaryInstruction);
        }
        if(operationCode.equalsIgnoreCase("001101")){
            this.orImediateOperation(binaryInstruction);
        }

    }




    public void addImmediateOperation(String binaryInstruction) {
        Integer rtValue = this.registers.get(rtDigits(binaryInstruction));
        Integer immediateInDecimal = Integer.parseInt(immediateDigits(binaryInstruction), 2);
        System.out.println(rtValue);
        System.out.println(immediateInDecimal);
        int sum = rtValue + immediateInDecimal;
        setRegister(rsDigits(binaryInstruction), sum);
        System.out.println("Addi: result = " + sum);
    }
    public void andOperation(String binaryInstruction) {
        String rtValueInBinary = toBinary32Bits(this.registers.get(rtDigits(binaryInstruction)));
        String rdValueInBinary = toBinary32Bits(this.registers.get(rdDigits(binaryInstruction)));
        StringBuilder sb = new StringBuilder();

        for (int i=0;i<32;i++) {
            if ((rtValueInBinary.charAt(i) == '1') && (rdValueInBinary.charAt(i) == '1')) {
                sb.append('1');
            } else {
                sb.append('0');
            }

        }

        System.out.println("Andi result is " + sb.toString());
        setRegister(rsDigits(binaryInstruction), Integer.parseInt(sb.toString()));
    }
    public void andImediateOperation(String binaryInstruction) {
        String rtValueInBinary = toBinary32Bits(this.registers.get(rtDigits(binaryInstruction)));
        String immediateInBinary = toBinary32Bits(immediateDigits(binaryInstruction));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (rtValueInBinary.charAt(i) == '1' && immediateInBinary.charAt(i) == '1') {
                sb.append('1');
            } else {
                sb.append('0');
            }

        }

        System.out.println("Andi result is " + sb.toString());
        setRegister(rsDigits(binaryInstruction), Integer.parseInt(sb.toString()));
    }
    public void addOperation(String binaryInstruction) {
        Integer rdValue = this.registers.get(rsDigits(binaryInstruction));
        Integer rtValue = this.registers.get(rtDigits(binaryInstruction));
        System.out.println(rdValue+" "+rtValue);

        int sum = rdValue + rtValue;
        setRegister(rsDigits(binaryInstruction), sum);
        System.out.println("Add: result = " + sum);
    }
    public void orOperation(String binaryInstruction) {
        String rtValueInBinary = toBinary32Bits(this.registers.get(rtDigits(binaryInstruction)));
        String rdValueInBinary = toBinary32Bits(this.registers.get(rdDigits(binaryInstruction)));



        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (rtValueInBinary.charAt(i) == '0' && rdValueInBinary.charAt(i) == '0') {
                sb.append('0');
            } else {
                sb.append('1');
            }

        }

        System.out.println("Or result is " + sb.toString());
        setRegister(rsDigits(binaryInstruction), Integer.parseInt(sb.toString()));
    }
    public void orImediateOperation(String binaryInstruction) {
        String rtValueInBinary = toBinary32Bits(registers.get(rtDigits(binaryInstruction)));
        String immediateInBinary = toBinary32Bits(immediateDigits(binaryInstruction));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            if (rtValueInBinary.charAt(i) == '0' && immediateInBinary.charAt(i) == '0') {
                sb.append('0');
            } else {
                sb.append('1');
            }

        }

        System.out.println("Ori result is " + sb.toString());
        setRegister(rsDigits(binaryInstruction), Integer.parseInt(sb.toString()));
    }


    public void setRegister(String key, int value) {
        if (key.equals("00000")) {
            throw new RuntimeException("cannot change register $zero");
        }
        this.registers.put(key, value);
    }



    public String operationCodeDigits(String binaryInstruction) {
        return binaryInstruction.substring(0, 6);
    }
    public String rsDigits(String binaryInstruction) {
        return binaryInstruction.substring(6, 11);
    }
    public String rtDigits(String binaryInstruction) {
        return binaryInstruction.substring(11, 16);
    }
    public String rdDigits(String binaryInstruction) {
        return binaryInstruction.substring(16, 21);
    }
    public String functionDigits(String binaryInstruction) {
        return binaryInstruction.substring(26, 32);
    }
    public String immediateDigits(String binaryInstruction) {
        return binaryInstruction.substring(17, 32);
    }



    public String toBinary32Bits(int value) {
        String binaryValue = Integer.toBinaryString(value);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32 - binaryValue.length(); i++) {
            sb.append('0');
        }
        sb.append(binaryValue);
        return sb.toString();
    }
    public String toBinary32Bits(String binaryValue) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32 - binaryValue.length(); i++) {
            sb.append('0');
        }
        sb.append(binaryValue);
        return sb.toString();
    }


}
