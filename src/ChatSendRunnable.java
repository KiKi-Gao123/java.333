import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatSendRunnable implements Runnable{

    private Socket s = null;

    public ChatSendRunnable(Socket s){
        this.s=s;
    }
    public void run(){

        OutputStream os = null;
        DataOutputStream dos = null;
        try{
            while (true){
                os=s.getOutputStream();
                dos=new DataOutputStream(os);
                Scanner in = new Scanner(System.in);
                String line = in.nextLine();
                dos.writeUTF(line);
            }
        }catch (Exception e){

        }
    }
}
