package ru.spbstu.ics.java.network.Chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * User: vlastachu
 * Date: 21.04.13
 * Time: 20:28
 */
public class Server implements MessageHandler {
    Chat chat;
    ServerSocket serverSocket;
    List<Socket> clientSockets;

    Server(Chat chat) {
        this.chat = chat;
        clientSockets = new ArrayList<Socket>();
        try {
            serverSocket = new ServerSocket(7777);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    waitForClients();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForClients() {
        try {
            while (true) {
                Socket socket = null;
                socket = serverSocket.accept();
                clientSockets.add(socket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        listenClient(clientSockets.size() - 1);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listenClient(int index) {
        try {
            while (true) {
                ObjectInputStream ois = new ObjectInputStream(clientSockets.get(index).getInputStream());
                SocketCommand sc = (SocketCommand) ois.readObject();
                switch (sc.getCommand()) {
                    case SUCCESS:
                        break;
                    case VOID:
                        break;
                    case ERROR:
                        break;
                    default:
                        chat.executeCommand(sc);
                        sendToClients(sc);
                }
            }
        } catch (IOException e) {
            chat.log("client don't answer");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void sendToClients(SocketCommand sc) {
        ObjectOutputStream oos;
        try {
            for (Socket socket : clientSockets) {
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(sc);
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String str) {
        SocketCommand sc = new SocketCommand(SocketCommand.Command.SEND, new String[]{str, chat.getName()});
        chat.executeCommand(sc);
        sendToClients(sc);
    }
}
