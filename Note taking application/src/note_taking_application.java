import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;
import javax.swing.text.*;

public class note_taking_application extends JFrame implements ActionListener {
    JTextArea t;
    JFrame frame;
    //Clipboard clipboard;

    note_taking_application() {
        frame = new JFrame("Notepad");
        //frame.setLayout(null);
        frame.setBackground(Color.WHITE);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");


            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {

        }
        t = new JTextArea();
        JMenuBar m1 = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenuItem item1 = new JMenuItem("New");
        JMenuItem item2 = new JMenuItem("Open");
        JMenuItem item3 = new JMenuItem("Save");
        JMenuItem item4 = new JMenuItem("Print");

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item4);

        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);

        m1.add(menu1);

        JMenu menu2 = new JMenu("Edit");
        JMenuItem item5 = new JMenuItem("cut");
        JMenuItem item6 = new JMenuItem("copy");
        JMenuItem item7 = new JMenuItem("paste");

        //clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        menu2.add(item5);
        menu2.add(item6);
        menu2.add(item7);
        m1.add(menu2);

        item5.addActionListener(this);
        item6.addActionListener(this);
        item7.addActionListener(this);

        JMenuItem menu3 = new JMenuItem("Close");

        m1.add(menu3);
        menu3.addActionListener(this);

        frame.setJMenuBar(m1);
        frame.add(t);
        frame.show();
        //frame.setVisible(true);
    }

    public static void main(String[] args) {
        new note_taking_application();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("cut")) {
            t.cut();
        } else if (s.equals("copy")) {
            t.copy();
        } else if (s.equals("paste")) {
            t.paste();
        } else if (s.equals("Save")) {
            JFileChooser jf = new JFileChooser("frame:");
            int w = jf.showSaveDialog(null);
            if (w == JFileChooser.APPROVE_OPTION) {
                File file = new File(jf.getSelectedFile().getAbsolutePath());

                try {

                    FileWriter writer = new FileWriter(file,false);
                    BufferedWriter w1 = new BufferedWriter(writer);
                    w1.write(t.getText());
                    w1.flush();
                    w1.close();


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Cancelled");
            }
        } else if (s.equals("Open")) {
            JFileChooser jf=new JFileChooser("frame:");
            int w=jf.showOpenDialog(null);
            if(w==JFileChooser.APPROVE_OPTION){
                File f1=new File(jf.getSelectedFile().getAbsolutePath());
                try {
                    String s1="",s2="";
                    FileReader fr=new FileReader(f1);
                    BufferedReader br=new BufferedReader(fr);
                    s2=br.readLine();
                    while ((s1=br.readLine())!=null){
                        s2=s2+"\n"+s1;
                    }
                   t.setText(s2);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(frame, "Cancelled");
            }


        } else if (s.equals("Print")) {
            try {
                t.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        }else if (s.equals("New")){
            t.setText("");
        } else if (s.equals("Close")) {
            frame.setVisible(false);
        }
    }
}
