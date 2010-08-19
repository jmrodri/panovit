package com.zeusville.panovit;

import com.zeusville.panovit.pianobar.Pianobar;

import expectj.ExpectJ;
import expectj.Spawn;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class TestExpect {


    public void testfoo() throws Exception {
        ExpectJ expectinator = new ExpectJ();
        Spawn shell = expectinator.spawn("/usr/local/bin/pianobar");
        
        shell.expect("Select station:");
        //String stations = shell.getCurrentStandardOutContents();
        //System.out.println("BEGIN\n" + stations + "\nEND");
        shell.send("1\n");
        shell.expect("waitforever");
        //shell.send("q\n");
    }
    
    @Test
    public void usePianobar() throws FileNotFoundException, InterruptedException {
        PrintWriter ctlfile = new PrintWriter(new File("/home/jesusr/.config/pianobar/ctl"));
        Pianobar pb = new Pianobar("/usr/local/bin/pianobar", ctlfile);
        System.out.println("pianobar created");
        Thread t = new Thread(pb);
        System.out.println("thread created");
        t.start();
        System.out.println("thread started");
        Thread.sleep(10);
        System.out.println("choosing next song");
        pb.next();
        Thread.sleep(10);
        pb.next();
        Thread.sleep(10);
        pb.changeStation("17");
        pb.quit();
    }
}
