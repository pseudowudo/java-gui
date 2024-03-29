import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void createGUI(){
        JFrame frame = new JFrame("Text Editor");
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setVisible(true);

        JLabel label = new JLabel("txt editor v1");
        label.setForeground(Color.white);
        frame.getContentPane().add(label);
        Container contentPane = frame.getContentPane();
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        layout.putConstraint(SpringLayout.NORTH, label,5,SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label,0,SpringLayout.HORIZONTAL_CENTER, contentPane);
        JTextArea area= new JTextArea("",39,123);
        contentPane.add(area);

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, area,0,SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, area,0,SpringLayout.HORIZONTAL_CENTER, contentPane);

        JButton jb1=new JButton("Open");
        layout.putConstraint(SpringLayout.WEST, jb1,0,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, jb1,0,SpringLayout.NORTH, contentPane);

        JButton jb3=new JButton("Save As");
        layout.putConstraint(SpringLayout.WEST, jb3,70,SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, jb3,0,SpringLayout.NORTH, contentPane);
        contentPane.add(jb1);

        JButton jb4=new JButton("Clear");
        layout.putConstraint(SpringLayout.EAST, jb4,0,SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, jb4,0,SpringLayout.NORTH, contentPane);
        contentPane.add(jb4);
        jb4.addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent e) {
                                      area.setText("");
                                  }
                              });

                contentPane.add(jb3);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chsr= new JFileChooser();
                chsr.showOpenDialog(null);
                String filename=chsr.getSelectedFile().getAbsolutePath();


                String fn = filename;
                Path path = Paths.get(fn);
                try {
                    byte[] bytes = Files.readAllBytes(path);
                    java.util.List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
                    String result = String.join("\n ", allLines);
                    System.out.println(result);
                    area.setText(result);

                    JButton jb2=new JButton("Save");
                    layout.putConstraint(SpringLayout.WEST, jb2,70,SpringLayout.WEST, contentPane);
                    layout.putConstraint(SpringLayout.NORTH, jb2,0,SpringLayout.NORTH, contentPane);
                    layout.putConstraint(SpringLayout.WEST, jb3,140,SpringLayout.WEST, contentPane);
                    layout.putConstraint(SpringLayout.NORTH, jb3,0,SpringLayout.NORTH, contentPane);
                    contentPane.add(jb2);

                    jb2.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            try {
                                PrintWriter pw=new PrintWriter(filename);
                                area.write(pw);
                                pw.close();
                            } catch (IOException f) {
                                f.printStackTrace();
                            }
                        }
                    });
                } catch (IOException f) {
                    f.printStackTrace();
                }}


        });

        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser c = new JFileChooser();
                int rVal = c.showSaveDialog(null);
                try {
                    PrintWriter pw=new PrintWriter(c.getSelectedFile().getAbsolutePath());
                    area.write(pw);
                    pw.close();
                } catch (IOException f) {
                    f.printStackTrace();
                }


            }
        });


    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}