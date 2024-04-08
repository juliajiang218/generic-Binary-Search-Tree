/*
∗ @file: Parser.java
∗ @description: This program implements the Parser class and reads a series of commands from the input file, with one command per line.
∗ @author: Julia Jiang
∗ @date: January 21 , 2024
∗ @acknowledgement : worked with Caroline Wales
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;

public class Parser<BSTIterator> {

    //Create a BST tree of Integer type
    private BST<Integer> mybst = new BST<>();
    //Create a BST tree of MySquare type
    private BST<MySquare> BST_mySquare = new BST<>();

    //This is the Parser constructor. It takes in a String data type filename.
    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    /*
    This method removes redundant spaces for each file input and ignore blank lines in the file.
    It calls operate_BST method and operate_BST_mySquare method.
    */
    public void process(File input) throws FileNotFoundException {
            Scanner reader = new Scanner(input);
            String line = null;
            ArrayList<String> list = new ArrayList<>();

            while(reader.hasNextLine()) {
                line = reader.nextLine();
                if (!line.isEmpty()) {
                    line = line.trim().replaceAll("\\s+", " ");
                    list.add(line);
                }
            }

            for(int i =0; i < list.size(); i++) {
                line = list.get(i);
                String[] command = line.split(" ");
                operate_BST(command);
                operate_BST_mySquare(command);
            }
    }

    // This method takes a string array named command, performs the given command on MySquare datatype BST, and then writes the results to a file.
    public void operate_BST_mySquare(String[] command){
        switch(command[0]){
            case "insert":
                String val = command[1];
                Integer n = Integer.valueOf(val);
                MySquare s1 = new MySquare(n);
                BST_mySquare.insert(s1);
                writeToFile("insert: " + val, "./result.txt");
                break;

            case "search":
                String s = command[1];
                Integer ss = Integer.valueOf(s);
                MySquare s2 = new MySquare(ss);
                if ( BST_mySquare.find(s2) == null) {
                    writeToFile("search failed", "./result.txt");
                    break;
                }
                writeToFile("search: "+ s, "./result.txt");
                break;

            case "remove":
                String r = command[1];
                Integer rr = Integer.valueOf(r);
                MySquare s3 = new MySquare(rr);
                if (BST_mySquare.remove(s3) == null) {
                    writeToFile("remove failed", "./result.txt");
                    break;
                }
                writeToFile("remove: " + r, "./result.txt");
                break;

            case "print":
                Iterator<Node<MySquare>> iter = BST_mySquare.iterator();
                String list = "";

                while(iter.hasNext()){
                    Node<MySquare> a = iter.next();
                    String b = String.valueOf(a.getElement().getArea());
                    list += b;
                    list += " ";
                }

                writeToFile(list, "./result.txt");
                break;

            default:
                writeToFile("Invalid Command", "./result.txt");
        }
    }
    // This method takes a string array named command, performs the given command on integer datatype BST, and then writes the results to a file.
    public void operate_BST(String[] command) {
        switch (command[0]) {

            case "insert":
                String val = command[1];
                mybst.insert(Integer.valueOf(val));
                writeToFile("insert " + val, "./resultT.txt");
                break;

            case "search":
                String n = command[1];
                Integer v = mybst.find(Integer.valueOf(n));
                if ( v == null) {
                    writeToFile("search failed", "./resultT.txt");
                    break;
                }

                writeToFile("search "+ v, "./resultT.txt");
                break;

            case "remove":
                String r = command[1];
                Integer removed = mybst.remove(Integer.valueOf(r));

                if (removed == null) {
                    writeToFile("remove failed", "./resultT.txt");
                    break;
                }
                writeToFile("remove " + removed, "./resultT.txt");
                break;

            case "print":
                Iterator<Node<Integer>> iter = mybst.iterator();
                String list = "";
                while(iter.hasNext()){
                    Node<Integer> a = iter.next();
                    String b = String.valueOf(a.getElement());
                    list += b;
                    list += " ";
                }
                writeToFile(list, "./resultT.txt");
                break;

            default:
                writeToFile("Invalid Command", "./resultT.txt");
        }
    }

    /*
    This method writes the BST results into "result.txt".
    It opens the specified file, appends a new line at the end, and writes the output content.
    */

    public void writeToFile(String content, String filePath) {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(content);
                writer.newLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }
}

