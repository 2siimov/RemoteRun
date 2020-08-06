package lib.ui.host;

import bin.communicator.HostSend;
import lib.config.CompileConfig;
import lib.config.SocketConfig;
import lib.debug.Logger;
import lib.units.Language;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class CommandlineUI {
    public static void printGreeting() {
        System.out.printf("Welcome to Remote Run\n" +
                "This is still early access, so some might not function normally\n" +
                "Please report any bugs on github\n");
    }
    public static String changeFilePath(CompileConfig compileConfig) {
        System.out.println("Changing File Path: ");
        System.out.print("Please Input new File Path: ");
        return new Scanner(System.in).nextLine();
    }
    public static Language changeLanguage(CompileConfig compileConfig) {
        System.out.println("Changing Language");
        System.out.println("Current Language: " + compileConfig.language.getName());
        System.out.println("Please Select New Language: ");
        System.out.println("1. Java\n2. C++ (Not Supported)\n3. Python (Not Supported)");
        int choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1:
                System.out.println("Changing Language to Java");
                return Language.JAVA;
            case 2:
                System.out.println("Changing Language to C++");
                return Language.CPP;
            case 3:
                System.out.println("Changing Language to Python");
                return Language.PYTHON;
            default:
                System.out.println("Invalid Choice, Nothing has changed");
                return compileConfig.language;
        }
    }
    public static void mainInterface(SocketConfig socketConfig, CompileConfig compileConfig, HostSend hostSend) throws Exception {
        for (;;) {
            System.out.printf("Currently Connected to " + socketConfig.getClientAddress() + ":" + socketConfig.getClientPort() + "\n");
            System.out.println("File Path: " + compileConfig.filePath);
            System.out.println("Language: " + compileConfig.language.getName());
            System.out.println("\n1. Compile File");
            System.out.println("2. Run File");
            System.out.println("3. Edit File Path");
            System.out.println("4. Edit Language");
            System.out.println("5. Exit");
            int option = new Scanner(System.in).nextInt();
            switch (option) {
                case 1:
                    System.out.println("Compile Result: \n" + hostSend.tellCompile(compileConfig));
                    HostSend.waitForConfirm("Continue?");
                    break;
                case 3:
                    compileConfig.filePath = changeFilePath(compileConfig);
                    break;
                case 4:
                    compileConfig.language = changeLanguage(compileConfig);
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
    public static SocketConfig getSocketConfig() throws UnknownHostException{
        Scanner scanner = new Scanner(System.in);
        Logger.log("Getting Client Socket Config");
        System.out.println("Please Input Host Config");
        System.out.print("Host Address: ");
        InetAddress ipAddress = InetAddress.getByName(scanner.nextLine());
        System.out.printf("Host Port: ");
        int port = scanner.nextInt();
        System.out.printf("Host Title: ");
        scanner.next();
        String name = scanner.nextLine();
        return new SocketConfig(name, ipAddress, port);
    }
}
