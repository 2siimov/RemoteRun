package bin;

import bin.communicator.ClientReceive;
import lib.config.SocketConfig;
import lib.ui.client.CommandlineUI;

import java.io.IOException;

public class Client {
    private static Client instance;
    private SocketConfig socketConfig;
    private ClientReceive receiver;
    public Client() {

    }
    public static void main(String[] args) throws IOException {
        instance = new Client();
        CommandlineUI.printGreeting();
        instance.socketConfig = CommandlineUI.getSocketConfig();
        instance.receiver = new ClientReceive(instance.socketConfig);
        instance.receiver.getStream();
        Thread listener = new Thread(instance.receiver);
        listener.start();
    }
}
