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

import org.gstreamer.Element;
import org.gstreamer.ElementFactory;
import org.gstreamer.Gst;
import org.gstreamer.Pipeline;
import org.gstreamer.State;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class TestGstreamer {

    /*
     * Element src = ElementFactory.make("fakesrc", "Source"); gst-launch
     * pulsesrc device=alsa_output.pci-0000_00_14.2.analog-stereo.monitor !
     * audioconvert ! lame name=enc mode=0 vbr-quality=6 ! shout2send
     * mount=/pandora.mp3 port=8000 password=tivo ip=172.31.6.3
     */
    @Test
    public void connect() {
        Gst.init();
        Pipeline pipe = new Pipeline("SimplePipeline");
        //Element src = ElementFactory.make("fakesrc", "Source");
        Element src = ElementFactory.make("pulsesrc", "Source");
        src.set("device", "alsa_output.pci-0000_00_1b.0.analog-stereo.monitor");
        Element convert = ElementFactory.make("audioconvert", "convert");
        Element lame = ElementFactory.make("lame", "lame");
        lame.set("name", "enc");
        lame.set("mode", "0");
        lame.set("vbr-quality", "6");
        
//        AppSink appsink = (AppSink) ElementFactory.make("appsink", "stream");
//        Buffer buf = appsink.pullBuffer();
        
        Element file = ElementFactory.make("filesink", "buffer");
        file.set("location", "/tmp/buffer.mp3");
        
//        Element shout = ElementFactory.make("shout2send", "icecast");
//        shout.set("mount", "/pandora.mp3");
//        shout.set("port", "8000");
//        shout.set("password", "something");
//        shout.set("ip", "192.168.1.201");

        
        pipe.addMany(src, convert, lame, file);
        src.link(convert, lame, file);
        pipe.setState(State.PLAYING);
        Gst.main();
        pipe.setState(State.NULL);
    }
    
    public static class Read implements Runnable {

        public void run() {
            FileInputStream fr = null;
            try {
                fr = new FileInputStream(new File("/tmp/buffer.mp3"));
                byte[] music = new byte[1024];
                int i = 0;
                while((i = fr.read(music)) > -1) {
                    
                    //System.out.println((char)i);
                   // buf.append((char)i);
                    if (':' == (char)i) {
                        break;
                    }
                }
                //System.out.println(buf.toString());
                quit();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            finally {
                try {
                    if (fr != null) {
                        fr.close();
                    }
                }
                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        public void quit() {
            PrintWriter pw;
            try {
                pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("/tmp/pianobarinput")));
                pw.println("q\n");
                pw.close();
            }
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}
