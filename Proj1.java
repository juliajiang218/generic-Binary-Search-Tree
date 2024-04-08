/*
∗ @file: Proj1.java
∗ @description: This file implements the class Proj1 and main method.
∗ @author: Julia Jiang
∗ @date: January 22, 2024
∗ @acknowledgement : worked with Caroline Wales
 */

import java.io.FileNotFoundException;

public class Proj1 {
    //This is the main method.
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 1){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser(args[0]);
    }
}