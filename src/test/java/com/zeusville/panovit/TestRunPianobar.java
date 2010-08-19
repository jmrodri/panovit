package com.zeusville.panovit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Map;




public class TestRunPianobar {
    
    public static void dumpEnv(Map<String, String> env) {
        for (String key : env.keySet()) {
            System.out.println(key + "=" + env.get(key));
        }
    }
    
    public static String[] envAsArray(Map<String, String> env) {
        String[] envp = new String[env.size()];
        int i = 0;
        for (String key : env.keySet()) {
            envp[i] = key + "=" + env.get(key);
            i++;
        }
        
        return envp;
    }
    
    public static void main(String[] args) {
        try {
            Map<String, String> env = System.getenv();
            dumpEnv(env);
            
            Process p = Runtime.getRuntime().exec("/usr/local/bin/pianobar", envAsArray(env));
            Read read = new Read(p.getInputStream());
            Write write = new Write(p.getOutputStream());
            Thread reader = new Thread(read);
            Thread writer = new Thread(write);
            
            reader.start();
            writer.start();
            System.out.println("threads started");
            int exitval = -99;
            try {
                exitval = p.waitFor();
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("pianobar exited with " + exitval);
            read.stop();
            
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static class Read implements Runnable {
        private InputStream stdout;
        private boolean keepRunning = true;

        public Read(InputStream out) {
            stdout = out;
        }
        
        public void stop() {
            keepRunning = false;
        }
        
        public void run() {
            Reader rdr = null;
            try {
                rdr = new InputStreamReader(new BufferedInputStream(stdout));
                StringBuffer buf = new StringBuffer();
                while (keepRunning) {
                    int i = 0;
                    while((i = rdr.read()) > -1) {
                        buf.append((char)i);
                    }
                    System.out.println(buf.toString());
                    buf = new StringBuffer();
                }
                
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (rdr != null) {
                    try {
                        rdr.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
                rdr = null;
            }
            System.out.println(this.getClass().getName() + " has quit");
            
        }
        
    }
    
    public static class Write implements Runnable {

        private OutputStream stdin;
        public Write(OutputStream in) {
            stdin = in;
        }
        
        public void run() {
            PrintWriter pw;
            try {
                System.out.println("sleep a bit before we send any commands");
                Thread.sleep(3000);
                pw = new PrintWriter(new BufferedOutputStream(stdin));
                System.out.println("Sending \n1\n");
                pw.println("\r" + 1 + "\rn\r");
                Thread.sleep(30000);
                System.out.println("Sending q");
                pw.println("q\r");
                pw.close();
                System.out.println("Quiting");
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(this.getClass().getName() + " has quit");

        }
        
    }
}
