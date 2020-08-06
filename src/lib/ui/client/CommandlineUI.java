package lib.ui.client;

import lib.config.SocketConfig;
import lib.debug.Logger;

import java.util.Scanner;

public class CommandlineUI {
    public static void printGreeting() {
        System.out.printf("Welcome to Remote Run\n" +
                "This is still early access, so some might not function normally\n" +
                "Please report any bugs on github\n");
    }
    public static SocketConfig getSocketConfig() {
        Logger.log("Getting Socket Config");
        System.out.println("Building Local Server");
        System.out.print("Port: ");
        int port = new Scanner(System.in).nextInt();
        return new SocketConfig(port);
    }
}
