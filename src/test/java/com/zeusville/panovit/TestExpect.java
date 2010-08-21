package com.zeusville.panovit;

import com.zeusville.panovit.pianobar.Pianobar;
import com.zeusville.panovit.pianobar.PianobarControl;

import expectj.ExpectJ;
import expectj.Spawn;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;


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
        File ctlfile = new File("/home/jesusr/.config/pianobar/ctl");
        PianobarControl pb = new PianobarControl(ctlfile);
        System.out.println("Playing next song");
        pb.next();
        Thread.sleep(10);
        System.out.println("Next song");
        pb.next();
        Thread.sleep(10);
        System.out.println("Change to station 17");
        pb.changeStation("17");
        System.out.println("quitting");
        pb.quit();
    }
}
