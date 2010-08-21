package com.zeusville.panovit;


import com.tivo.hme.bananas.BApplication;
import com.tivo.hme.bananas.BView;
import com.tivo.hme.interfaces.IContext;
import com.zeusville.panovit.pianobar.Callback;
import com.zeusville.panovit.pianobar.Pianobar;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;


public class Pandora extends BApplication {
    
    private ThreadGroup group;
    private PipeReader eventPipe;
    private Pianobar pbrunner;
    
    public Pandora() {
        super();
        group = new ThreadGroup("panovit");
    }
    
    @Override
    public void init(IContext context) throws Exception {
        super.init(context);
        BView root = getBelow();
        root.setResource("blue.jpg");
        
        MainScreen mainscreen = new MainScreen(this);
        System.out.println(SAFE_TITLE_H + " " + SAFE_TITLE_V + " " + getWidth() + " " + 35);
        BView text = new BView(mainscreen.getNormal(), SAFE_TITLE_H, SAFE_TITLE_V, getWidth(), 35);
        text.setResource(createText("default-24-bold.font", Color.WHITE,
              "Panovit - Pandora for your TiVo"));
        
        System.out.println("Creating eventpipe");
        createEventPipe(mainscreen);
        createPianobar();
        System.out.println("pushing main screen");
        push(mainscreen, TRANSITION_NONE);
    }
    
    private void createEventPipe(Callback handler) throws FileNotFoundException {
        String envhome = System.getenv("HOME");
        File eventfile = new File(envhome + "/.config/panovit/ctl");
        System.out.println("reading from pipe: " + eventfile.getName());
        eventPipe = new PipeReader(eventfile, handler);
        Thread reader = new Thread(group, eventPipe, "eventPipereader");
        System.out.println("Starting thread");
        reader.start();
    }
    
    private void createPianobar() {
        String envhome = System.getenv("HOME");
        String cmd = "/usr/local/bin/pianobar | tee " + envhome +
            "/.config/panovit/ctl";
        System.out.println("Starting pianobar launcher with " + cmd);

        pbrunner = new Pianobar(cmd);
        Thread runner = new Thread(group, pbrunner, "Pianobar Runner");
        System.out.println("Starting pbrunner thread");
        runner.start();
    }
    
}
