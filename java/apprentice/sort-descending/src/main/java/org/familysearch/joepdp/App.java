package org.familysearch.joepdp;

import java.io.*;
import java.util.TreeSet;

public class App{
    public static void sort(BufferedReader in, PrintStream out) throws IOException {
        TreeSet<String> lines = new TreeSet<String>();
        for(String line = in.readLine(); line!=null; line = in.readLine()){
            lines.add(line);
        }
        for(String line: lines.descendingSet()){
            out.println(line);
        }
    }

    public static void main(String[] args){
        try{
            FileInputStream inFileInputStream = new FileInputStream("in.txt");
            FileOutputStream outFileOutputStream = new FileOutputStream("out.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inFileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintStream printStream = new PrintStream(outFileOutputStream);
            sort(bufferedReader, printStream);
//            sort(new BufferedReader(new InputStreamReader(inFileInputStream)), System.out);
        } catch (Exception e){
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
