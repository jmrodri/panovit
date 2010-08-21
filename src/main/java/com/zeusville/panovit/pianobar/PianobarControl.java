/**
 * Copyright (c) 2010 jesus m. rodriguez
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 */
package com.zeusville.panovit.pianobar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class PianobarControl {
    private PrintWriter pbctl;

    public PianobarControl(File namedpipe) throws FileNotFoundException {
        pbctl = new PrintWriter(new BufferedOutputStream(new FileOutputStream(namedpipe)));
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
        pbctl.close();
    }
    
    public void tired() {
        send("t");
    }
    
    public void changeStation(String station) {
        // expect Select station:
    }
    
    private void send(String msg) {
        this.pbctl.println(msg);
    }
}
