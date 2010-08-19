package com.zeusville.panovit;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class TestFifo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("/home/jesusr/.config/pianobar/ctl")));
            System.out.println("sending: " + args[0]);
            pw.println(args[0] + "\n");
            pw.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
