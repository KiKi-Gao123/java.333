import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientReceiveRunnable implements Runnable{
    private Socket s = null;

    public ClientReceiveRunnable(Socket s){
        this.s=s;
    }
    public void run(){
        InputStream is = null;
        DataInputStream dis = null;

        try{
            while (true){
                is=s.getInputStream();
                dis=new DataInputStream(is);
                System.out.println("client received:"+dis.readUTF());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
