
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
public class shivu extends Frame implements Runnable, ActionListener{
    TextField textField;
    TextArea textArea;
    Button send;
    ServerSocket serversocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Thread chat;
    shivu(){
        textField=new TextField();
        textArea=new TextArea();
        send=new Button("send");
       send.addActionListener(this);
       try{
       serversocket=new ServerSocket(999);
       socket=serversocket.accept();

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
        chat.setDaemon(true);
        chat.start();

        setSize(500,500);
        setTitle("shivu");
        setLayout(new FlowLayout());
        setVisible(true);

    } 
    @Override 
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String msg=textField.getText();
        textArea.append("shivu:"+msg+"\n");
        textField.setText("");

        try{dataOutputStream.writeUTF(msg);
        dataOutputStream.flush();
        }catch(Exception f){
            System.out.println(f);
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
   
    public static void main(String[] args) {
        new shivu();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try{
             String msg=dataInputStream.readUTF();
            textArea.append("pooja:"+msg+"\n");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
       // throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
