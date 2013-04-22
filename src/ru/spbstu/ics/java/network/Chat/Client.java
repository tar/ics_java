package ru.spbstu.ics.java.network.Chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * User: vlastachu
 * Date: 21.04.13
 * Time: 20:28
 */
public class Client implements MessageHandler {
    Chat chat;
    Socket socket;
    Client(Chat chat, String hostName) {
        this.chat = chat;
        try {
            socket = new Socket(hostName, 7777);
            //заявим о себе
            sendCommandToServer(new SocketCommand(SocketCommand.Command.JOIN, new String[]{chat.getName()}));
            //здесь можно подождать ответа в виде списка пользователей на сервере,
            // но для минимализма просто послушаем сервер
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listenServer();
                }
            }).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenServer(){
        try {
            while (true){
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                SocketCommand sc = (SocketCommand)ois.readObject();
                if(sc.getCommand() == SocketCommand.Command.KICK) break;
                switch (sc.getCommand()){
                    case SUCCESS:
                        break;
                    case VOID:
                        break;
                    case ERROR:
                        break;
                    default:
                        chat.executeCommand(sc);
                }
            }
            socket.close();
        } catch (IOException e) {
            chat.log("OH NOOOES!");
        } catch (ClassNotFoundException e) {
            chat.log("ahm...class not found. So sorry, bro.");
        }
    }

    private void sendCommandToServer(SocketCommand sc){
        PrintWriter pw = null;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(sc);
            oos.flush();
        } catch (IOException e) {
            chat.log("Connection problem!");
        }
    }

    @Override
    public void sendMessage(String str) {
        sendCommandToServer(new SocketCommand(SocketCommand.Command.SEND, new String[]{str, chat.getName()}));
    }
}
