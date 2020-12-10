package cn.com.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
/*
聊天小程序
要求:
使用图形用户界面。
能实现一个聊天室中多人聊天。
可以两人私聊。
提示:使用 socket 通信
 */


public class TestThreadClient {
    public static void main(String[] args) {
        Socket s = null;

        try {
            s = new Socket("127.0.0.1",5652);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Runnable r1 = new ClientSendRunnable(s);
        Thread t1 = new Thread(r1);
        t1.start();

        Runnable r2 = new ClientReceiveRunnable(s);
        Thread t2 = new Thread(r2);
        t2.start();
    }
}


class ClientSendRunnable implements Runnable {
    private Socket s = null;
    public ClientSendRunnable(Socket s){
        this.s=s;
    }

    @Override
    public void run() {
        OutputStream os = null;
        DataOutputStream dos = null;

        try {
            while (true){
                os=s.getOutputStream();
                dos=new DataOutputStream(os);
                Scanner in = new Scanner(System.in);
                String line = in.nextLine();
                dos.writeUTF(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


class ClientReceiveRunnable implements Runnable{
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

class ChatSendRunnable implements Runnable{

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

class ChatServerRunnable implements Runnable{


    private Socket s = null;

    //构造方法
    ChatServerRunnable(Socket s){
        this.s=s;
    }

    public void run(){
        InputStream is = null;
        DataInputStream dis = null;

        try{
            while (true){
                is=s.getInputStream();
                dis=new DataInputStream(is);
                System.out.println("server received:"+dis.readUTF());
            }
        }catch (IOException e){

            e.printStackTrace();

        }
    }






}

class ChatTest {
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

