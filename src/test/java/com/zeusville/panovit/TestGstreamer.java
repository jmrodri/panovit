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
        
        Element shout = ElementFactory.make("shout2send", "icecast");
        shout.set("mount", "/pandora.mp3");
        shout.set("port", "8000");
        shout.set("password", "tivo");
        shout.set("ip", "172.31.6.201");

        
        pipe.addMany(src, convert, lame, shout);
        src.link(convert, lame, shout);
        pipe.setState(State.PLAYING);
        Gst.main();
        pipe.setState(State.NULL);
    }
}
