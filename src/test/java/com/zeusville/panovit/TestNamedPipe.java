package com.zeusville.panovit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class TestNamedPipe {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread reader = new Thread(new Read());
        //Thread writer = new Thread(new Write());
        
        reader.start();
        //writer.start();
    }
    
    public static class Read implements Runnable {

        public void run() {
            FileReader fr = null;
            try {
                fr = new FileReader(new File("/tmp/testpipe"));
                StringBuffer buf = new StringBuffer();
                int i = 0;
                while((i = fr.read()) > -1) {
                    //System.out.println((char)i);
                    buf.append((char)i);
                    if (':' == (char)i) {
                        break;
                    }
                }
                System.out.println(buf.toString());
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
    

    
    public static class Write implements Runnable {

        public void run() {
            PrintWriter pw;
            try {
                pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("/tmp/testpipe")));
                Random r = new Random();
                for (int i=0; i < 1000; i++) {
                    pw.println(Long.toString(Math.abs(r.nextLong()), 36));
                }
                pw.close();
            }
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

}
