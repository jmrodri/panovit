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
package com.zeusville.panovit;

import com.zeusville.panovit.pianobar.Callback;
import com.zeusville.panovit.pianobar.EventType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PipeReader listens on a named pipe for events from pianobar.
 * @version $Rev$
 */
public class PipeReader implements Runnable {

    private static final String MAGICSTRING = "Select station:";
    
    private boolean keeprunning = true;
    private File ctlfile;
    private FileReader fr;
    private Callback evthandler;
    
    public PipeReader(File pipe, Callback handler) throws FileNotFoundException {
        ctlfile = pipe;
        fr = new FileReader(ctlfile);
        evthandler = handler;
    }
    
    public void read() {
        try {
            StringBuffer buf = new StringBuffer();
            int i = 0;
            while (keeprunning) {
                while ((i = fr.read()) > -1) {
                    buf.append((char)i);
                    if (':' == (char)i && (buf.indexOf(MAGICSTRING) > -1)) {
                        System.out.println("We found " + MAGICSTRING);
                        evthandler.callback(EventType.STATIONADD, getStations(buf.toString()));
                        break;
                    }
                }
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List<String> getStations(String output) {
        List<String> stations = new ArrayList<String>();
        String[] prestations = output.split("\\s+\\d*\\)\\s{1,2}(q|Q)\\s+");
        for (String s : prestations) {
            stations.add(s.trim());
        }
        return stations;
    }

    public void run() {
        System.out.println("Starting...");
        read();
        System.out.println("Quitting..");
    }
    
    public void stop() {
        System.out.println("Stopping ...");
        keeprunning = false;
    }
    
    
}
