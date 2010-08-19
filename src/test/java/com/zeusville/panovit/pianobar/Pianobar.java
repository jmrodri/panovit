package com.zeusville.panovit.pianobar;

import expectj.ExpectJ;
import expectj.Spawn;
import expectj.TimeoutException;

import java.io.IOException;
import java.io.Writer;


public class Pianobar implements Runnable {
    
    private String pianobarcmd;
    private Writer namedPipe;
    private Spawn pianobar;
    
    public Pianobar(String cmd, Writer ctlfile) {
        pianobarcmd = cmd;
        namedPipe = ctlfile;
        pianobar = null;
    }

    public void start() {
        try {
            // we're not using a timeout
            ExpectJ exp = new ExpectJ();
            pianobar = exp.spawn(pianobarcmd);
            pianobar.expect("Select station:");
            // always choose the first one, we can change it later
            sendToShell("0");
            pianobar.expect("waitforever");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public void stop() {
        quit();
    }
    
    public void loveSong() {
        send("+");
    }
    
    public void banSong() {
        send("-");
    }
    
    public void bookmark() {
        send("b");
    }
    
    public void createStation(String station) {
        send("c");
        // send station to expect framework
        // expect "Search for artist/title:"
    }
    
    public void deleteStation() {
        send("d");
    }
    
    public void songExplain() {
        send("e");
    }
    
    public void history() {
        send("h");
    }
    
    public void songInfo() {
        send("i");
    }
    
    public void move(String station) {
        // expect Move song to station:
        send("m" + station);
    }
    
    public void next() {
        send("n");
    }
    
    public void resume() {
        pause();
    }
    
    public void pause() {
        send("p");
    }
    
    public void quit() {
        send("q");
    }
    
    public void tired() {
        send("t");
    }
    
    public void changeStation(String station) {
        // expect Select station:
    }
    
    private void send(String msg) {
        try {
            this.namedPipe.write(msg);
        }
        catch (IOException e) {
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
