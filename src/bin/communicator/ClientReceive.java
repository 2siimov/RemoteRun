package bin.communicator;

import lib.compiler.JavaCompile;
import lib.config.SocketConfig;
import lib.debug.Logger;
import lib.units.Language;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientReceive implements Runnable {
    private SocketConfig localConfig;
    private ServerSocket server;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private PrintStream printStream;
    private BufferedReader bufferedReader;

    public ClientReceive(SocketConfig localConfig) throws IOException {
        this.localConfig = localConfig;
        server = new ServerSocket(this.localConfig.getClientPort());
        Logger.log("Local Config Loaded");
    }
    public void waitForConfirm(String msg) {
        try {
            System.out.println(msg + " (Press Enter to Proceed)");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getStream() throws IOException {
        waitForConfirm("Start Server?");
        Logger.log("Waiting for Connection...");
        socket = server.accept();
        Logger.log("Building Stream");
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        printStream = new PrintStream(outputStream, true, "UTF-8");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }
    public void receiveCompile() throws IOException {
        Logger.log("Compile Order Received");
        printStream.println("Roger");
        Logger.log("Getting Compile Info");
        String filePath = bufferedReader.readLine();
        Logger.log("File Path Received: " + filePath);
        Language language = Language.getLanguage(bufferedReader.readLine());
        Logger.log("Language Received: " + language.getName());
        Logger.log("Compiling");
        switch (language) {
            case JAVA:
                try {
                    printStream.println(JavaCompile.compile(filePath));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            default:
                Logger.log("Language Not Supported");
                System.exit(-1);
        }
    }
    @Override
    public void run() {
        for (;;) {
            try {
                Logger.log("Waiting for commands");
                String cmd = bufferedReader.readLine();
                Logger.log("Command Read: " + cmd);
                if (cmd.contains("compile")) {
                    receiveCompile();
                }
            } catch (IOException e) {
                Logger.error("IO Exception Caught");
                e.printStackTrace();
                break;
            }
        }
    }
}
