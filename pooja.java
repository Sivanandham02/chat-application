
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
public class pooja extends Frame implements Runnable, ActionListener{
    TextField textField;
    TextArea textArea;
    Button send;
   // ServerSocket serversocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Thread chat;
    pooja(){
        textField=new TextField();
        textArea=new TextArea();
        send=new Button("send");
       send.addActionListener(this);
       try{
       //serversocket=new ServerSocket(999);
       socket=new Socket("localhost",999);

       dataInputStream=new DataInputStream(socket.getInputStream());
       dataOutputStream=new DataOutputStream(socket.getOutputStream());
    }
    catch(Exception e){
        System.out.println(e);
    }

        add(textField);
        add(textArea);
        add(send);

        chat=new Thread(this);
        chat.start();

        setSize(500,500);
        setTitle("pooja");
        setLayout(new FlowLayout());
        setVisible(true);

    } 
    @Override 
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String msg=textField.getText();
        textArea.append("pooja:"+msg+"\n");
        textField.setText("");

        try{dataOutputStream.writeUTF(msg);
        dataOutputStream.flush();
        }catch(Exception f){
            System.out.println(f);
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
   
    public static void main(String[] args) {
        new pooja();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try{
             String msg=dataInputStream.readUTF();
            textArea.append("shivu:"+msg+"\n");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
       // throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
