package chatting.application;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Client extends JFrame implements ActionListener {

    JPanel p1;
    JTextField tf1;
    JButton b1;
    JTextArea ta1;
    static JTextPane tp1;
    static DataInputStream dis;
    static DataOutputStream dos;

    static Socket s;

    Client() {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        ImageIcon backButtonii1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
        // The class ImageIcon is an implementation of the Icon interface that paints
        // Icons from Images
        // The Java ClassLoader is a part of the Java Runtime Environment that
        // dynamically loads Java classes into the Java Virtual Machine.The Java run
        // time system does not need to know about files and file systems because of
        // classloaders.
        Image backButtoni1 = backButtonii1.getImage().getScaledInstance(25, 20, Image.SCALE_DEFAULT);
        ImageIcon backButtonii2 = new ImageIcon(backButtoni1);
        JLabel l1 = new JLabel(backButtonii2);
        // Images cant be directly put upon frame, for that it needs JLabel
        l1.setBounds(13, 22, 30, 30);
        p1.add(l1);
        l1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon displayPicSenderii1 = new ImageIcon(
                ClassLoader.getSystemResource("chatting/application/icons/2.png"));
        Image displayPicSenderi1 = displayPicSenderii1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon displayPicSenderii2 = new ImageIcon(displayPicSenderi1);
        JLabel l2 = new JLabel(displayPicSenderii2);
        l2.setBounds(45, 10, 60, 60);
        p1.add(l2);

        JLabel l3 = new JLabel("Bunty");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 22, 100, 25);
        p1.add(l3);

        tf1 = new JTextField();
        tf1.setBounds(5, 660, 400, 35);
        tf1.setFont(new Font("ARIAL", Font.PLAIN, 14));
        add(tf1);

        /*
         * ta1=new JTextArea(); ta1.setBackground(Color.lightGray); SimpleAttributeSet
         * right = new SimpleAttributeSet(); StyleConstants.setAlignment(right,
         * StyleConstants.ALIGN_RIGHT); // ta1.setParagraphAttributes(0,ta1, right,
         * false); ta1.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
         * ta1.setBounds(5,73,440,580); ta1.setLineWrap(true); ta1.setEditable(false);
         * ta1. add(ta1);
         */

        tp1 = new JTextPane();
        tp1.setBackground(Color.lightGray);
        SimpleAttributeSet rightPane = new SimpleAttributeSet();
        StyleConstants.setAlignment(rightPane, StyleConstants.ALIGN_RIGHT);
        tp1.setParagraphAttributes(rightPane, true);
        tp1.setFont(new Font("SAN_SERIF", Font.PLAIN, 25));
        JScrollPane scrollPane = new JScrollPane(tp1);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tp1.setBounds(5, 73, 440, 580);
        tp1.setEditable(false);
        add(tp1);

        ImageIcon sendii1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/send.png"));
        Image sendi1 = sendii1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon sendii2 = new ImageIcon(sendi1);
        b1 = new JButton(sendii2);
        b1.setBounds(410, 660, 30, 30);
        b1.setBackground(new Color(83, 209, 166));
        b1.addActionListener(this);
        add(b1);

        setLayout(null); // The setLayout(...) method allows you to set the layout of the container,
                         // often a JPanel, to say FlowLayout, BorderLayout, GridLayout, null layout, or
                         // whatever layout desired. The layout manager helps lay out the components held
                         // by this container

        setSize(450, 732);
        setLocation(800, 50);
        setUndecorated(false);
        // setShape(new RoundRectangle2D.Double(2,2,5,5,50,50));
        setVisible(true); // alwaysa has to be in the end

    }

    public void actionPerformed(ActionEvent e) {
        try {
            String out = tf1.getText();
            tp1.setText(tp1.getText() + "\n\n" + out);
            dos.writeUTF(out);

            } catch (IOException e1) {
                e1.printStackTrace();
        }
        tf1.setText(null); 
    };

    public static void main(String[] args) {
        new Client().setVisible(true);

        String msgInput="";
        try {
            s=new Socket("127.0.0.1", 6001);
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            
            msgInput=dis.readUTF();
            tp1.setText(tp1.getText()+"\n"+msgInput);
            s.close();
        }catch(Exception e){}

    }



}
 