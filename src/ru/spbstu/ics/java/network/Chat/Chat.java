package ru.spbstu.ics.java.network.Chat;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * User: vlastachu
 * Date: 21.04.13
 * Time: 13:37
 */
public class Chat {
    private static String name;
    private JTextArea userInput;
    private JButton sendButton;
    private JPanel panel;
    private JEditorPane messageView;
    private JButton findServerButton;
    private JButton createServerButton;
    private JList userList;
    private List<String> users;
    private MessageHandler messageHandler;
    private StringBuilder chatLog;

    Chat(){
//        userInput = new JTextArea();
//        sendButton = new JButton();
//        panel = new JPanel();
//        messageView = new JEditorPane();
//        findServerButton = new JButton();
//        createServerButton = new JButton();
//        userList = new JList();
        users = new ArrayList<String>();
        chatLog = new StringBuilder();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat");
        Chat chat = new Chat();
        frame.setContentPane(chat.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        chat.setUp();
    }

    private void setUp() {
        messageView.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (e.getURL().toString().substring(0, 5).equals("user:")) {
                        //TODO
                    } else {
                        if (Desktop.isDesktopSupported()) {
                            try {
                                Desktop.getDesktop().browse(e.getURL().toURI());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        findServerButton.setEnabled(false);
        createServerButton.setEnabled(false);
        log("Welcome! Please type your chat name and send.");
        sendButton.addActionListener(new

                                             ActionListener() {
                                                 @Override
                                                 public void actionPerformed(ActionEvent e) {
                                                     name = userInput.getText();
                                                     userInput.setText("");
                                                     writeMessage(name);
                                                     log("Great! Now choose one of two: create server or type your friend's server name.");
                                                     findServerButton.setEnabled(true);
                                                     createServerButton.setEnabled(true);
                                                     sendButton.setEnabled(false);
                                                     sendButton.removeActionListener(this);
                                                     findServerButton.addActionListener(new ActionListener() {
                                                         @Override
                                                         public void actionPerformed(ActionEvent e) {
                                                             findServer();
                                                         }
                                                     }
                                                     );
                                                     createServerButton.addActionListener(new

                                                                                                  ActionListener() {
                                                                                                      @Override
                                                                                                      public void actionPerformed(ActionEvent e) {
                                                                                                          createServer();
                                                                                                      }
                                                                                                  }
                                                     );
                                                 }
                                             });
    }

    private void writeMessage(String str) {
        log(name + ": " + str);
    }

    public void writeMessage(String str, String name) {
        log(name + ": " + str);
    }

    public void log(String str) {
        chatLog.append("<p>").append(str).append("</p>");
        messageView.setText( "<html><body style=\"font-size:13pt\">" + chatLog.toString() + "</body></html>");
    }

    public String getName() {
        return name;
    }

    public void executeCommand(SocketCommand sc) {
        try {
            String[] params = sc.getParams();
            switch (sc.getCommand()) {
                case SEND:
                    writeMessage(params[0], params[1]);
                    break;
                case JOIN:
                    log(params[0] + " has joined");
                    break;
                case EXIT:
                    log(params[0] + " has left");
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            log("wrong command");
        }
    }

    private void findServer() {
        String s = (String)JOptionPane.showInputDialog("write server host name");
        messageHandler = new Client(this, s);
        chatReady();
    }

    private void createServer() {
        messageHandler = new Server(this);
        chatReady();
    }

    private void chatReady(){
        findServerButton.setEnabled(false);
        createServerButton.setEnabled(false);
        sendButton.setEnabled(true);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        userInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER)
                    sendMessage();
            }
        });
    }

    private void sendMessage() {
        String msg = userInput.getText();
        userInput.setText("");
        messageHandler.sendMessage(msg);
    }
}
