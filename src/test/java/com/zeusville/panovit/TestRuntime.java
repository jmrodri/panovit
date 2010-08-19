package com.zeusville.panovit;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;



public class TestRuntime {

    private void quit() {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("/home/jesusr/.config/pianobar/ctl")));
            

            pw.println("q");

            pw.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void runtime() throws IOException {
        Process pianobar = Runtime.getRuntime().exec("/usr/local/bin/pianobar");
        BufferedReader br = new BufferedReader(new InputStreamReader(pianobar.getInputStream()));
        char[] line = new char[1024];
        while( br.read(line) != -1) {
            System.out.println(line);
        }
        System.out.println("quitting");
        quit();
        
    }
}
