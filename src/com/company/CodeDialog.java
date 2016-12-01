package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by ghost on 2016/12/1.
 */
public class CodeDialog extends Reader {
    private String buffer = null;
    private int pos = 0;

    public int read(char [] cbuf, int off, int len){
        if(buffer == null){
            String in = showDialog();
            if(in == null)
                return -1;
            else{
                print(in);
                buffer = in + "\n";
                pos = 0;
            }
        }

        int size = 0;
        int length = buffer.length();
        while(pos < length && size < len)
            cbuf[off + size++] = buffer.charAt(pos++);

        if(pos == length)
            buffer = null;

        return size;
    }

    protected void print(String s){ System.out.println(s); }
    public void close() {};
    protected String showDialog(){
        JTextArea area = new JTextArea(20,40);
        JScrollPane pane = new JScrollPane(area);
        int result = JOptionPane.showOptionDialog(null, pane,"Input",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,null,null);
        if(result == JOptionPane.OK_OPTION)
            return area.getText();
        else
            return null;
    }

    public static Reader file() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            try {
                return new BufferedReader(new FileReader(chooser.getSelectedFile()));

            } catch (Exception e) {
                return null;
            }
        else
            return null;
    }
}
