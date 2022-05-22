package com.example.demo;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class CHATCLIENT extends Frame implements ActionListener,Runnable{
    private Button button1, button2;
    private TextArea textarea1, textarea2;
    private TextField textfield1;
    private Label label1, label2, label3;
    Socket socket;
    Thread thread;
    InputStream in;
    OutputStream out;
// PrintWriter out;
public static void main(String[] args)
{
    new CHATCLIENT();
}
    public void ChatClient()
    {
        setLayout(null);
        label1 = new Label("Server IP address:");
        label1.setBounds(35, 80, 105, 20);
        add(label1);
        textfield1 = new TextField("127.0.0.1");
        textfield1.setBounds(145, 80, 100, 20);
        add(textfield1);
        button1 = new Button("Connect");
        button1.setBounds(255, 80, 80, 20);
        add(button1);
        button1.addActionListener(this);
        button2 = new Button("Send");
        button2.setBounds(160, 390, 60, 20);
        add(button2);
        button2.addActionListener(this);
        textarea1 = new TextArea("", 7, 45, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textarea1.setBounds(20, 110, 340, 120);
        add(textarea1);
        label2 = new Label();
        label2.setBounds(20, 240, 100, 20);
        label2.setText("Type here:");
        add(label2);
        textarea2 = new TextArea("", 7, 45,
                TextArea.SCROLLBARS_VERTICAL_ONLY);
        textarea2.setBounds(20, 260, 340, 120);
        textarea2.setForeground(Color.RED);
        add(textarea2);
        label3 = new Label("Chat Client");
        label3.setFont(new Font("Times New Roman", Font.BOLD, 36));
        label3.setBounds(100, 35, 200, 30);
        add(label3);
        setSize(400, 430);
        setTitle("Chat Client");
        setVisible(true);
//textarea2.requestFocus();
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(
                    WindowEvent e){
                System.exit(0);
            } }
        );
    }
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource()==button1)
        {
            String hostname=textfield1.getText();
            try
            {
                socket = new Socket(hostname, 13);
                in=socket.getInputStream();
                out=socket.getOutputStream();
                textarea1.setText("you are now connected");
                thread=new Thread(this);
                thread.start();
            }catch (UnknownHostException e)
            {
                textarea1.setText(e.getMessage());
            }catch (IOException e)
            {
                textarea1.setText(e.getMessage());
            }
        }
        if(event.getSource()==button2)
        {
            try
            {
                String str = textarea2.getText() + "\n";
                byte buffer[] = str.getBytes();
                out.write(buffer);
                textarea1.setForeground(Color.RED);
                textarea1.append(str+"\n");
                textarea2.setText("");
                textarea2.requestFocus();
            }catch(Exception e){}
        } }
    public void run()
    {
        String instring;
        try {
            BufferedReader in = new BufferedReader (new
                    InputStreamReader(socket.getInputStream()));
            textarea1.setForeground(Color.BLUE);
            while((instring = in.readLine()) != null){
                textarea1.append(instring + "\n");
            }
        }catch (Exception e)
        {
            textarea1.setText(e.getMessage());
        }
    }
}