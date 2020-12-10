import java.net.ServerSocket;
import java.net.Socket;

public class ChatTest {
    @SuppressWarnings("resource")
    public static void main(String[] args)throws Exception {
        ServerSocket ss = null;
        ss = new ServerSocket(5652);

        while (true) {
            Socket s = ss.accept();
            Runnable r1 = new ChatServerRunnable(s);
            Thread t1 = new Thread(r1);
            t1.start();

            Runnable r2 = new ChatSendRunnable(s);
            Thread t2 = new Thread(r2);
            t2.start();
        }
    }
}
