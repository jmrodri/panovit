package com.zeusville.panovit.pianobar;

import expectj.ExpectJ;
import expectj.Spawn;

import java.io.IOException;


public class Pianobar implements Runnable {
    
    private String pianobarcmd;
    private Spawn pianobar;
    
    public Pianobar(String cmd) {
        pianobarcmd = cmd;
        pianobar = null;
    }

    public void start() {
        try {
            // we're not using a timeout
            ExpectJ exp = new ExpectJ();
            System.out.println("Starting " + pianobarcmd);
            pianobar = exp.spawn(pianobarcmd);
            pianobar.expect("Select station:");
            // always choose the first one, we can change it later
            sendToShell("0");
            System.out.println("ok gonig to waitforever");
            pianobar.expect("waitforever");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    private void sendToShell(String msg) {
        try {
            pianobar.send(msg);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {    
        start();
    }
}
