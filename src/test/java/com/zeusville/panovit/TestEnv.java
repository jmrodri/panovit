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

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;


public class TestEnv {

    @Test
    public void envTest() {
        System.out.println(System.getenv("HOME"));
    }
    
    @Test
    public void reader() throws FileNotFoundException, InterruptedException {
        String env = System.getenv("HOME");

        File ctlfile = new File(env + File.separator + ".config/panovit/ctl");

        PipeReader pr = new PipeReader(ctlfile, null);
        Thread t = new Thread(pr);
        t.start();
        Thread.sleep(30000);
        pr.stop();
    }
}
