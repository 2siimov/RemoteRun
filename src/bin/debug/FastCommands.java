package bin.debug;

import bin.communicator.ClientReceive;
import lib.ui.client.CommandlineUI;

import java.io.IOException;

public class FastCommands {
    public static void main(String[] args) throws IOException {
        ClientReceive cr = new ClientReceive(CommandlineUI.getSocketConfig());
        cr.getStream();
        Thread getter = new Thread(cr);
        getter.start();
    }
}
