package bin.communicator;

import lib.config.CompileConfig;
import lib.config.SocketConfig;
import lib.debug.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class HostSend {
    private SocketConfig hostConfig;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private PrintStream printStream;

    public HostSend(SocketConfig clientConfig) throws IOException {
        Logger.log("Initializing Host Sender");
        this.hostConfig = clientConfig;
        Logger.log("Config Loaded");
    }
    public static void waitForConfirm(String msg) {
        try {
            System.out.println(msg + " (Press Enter to Proceed)");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getStream() throws IOException {
        waitForConfirm("Connect to Host?");
        Logger.log("Trying to reach host");
        socket = new Socket(hostConfig.getClientAddress(), hostConfig.getClientPort());
        Logger.log("Host Connected, Starting Stream");
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        printStream = new PrintStream(outputStream);
        Logger.log("Stream started");
    }
    public String tellCompile(CompileConfig compileConfig) throws IOException {
        Logger.log("Telling Compile");
        byte[] remsg = new byte[512];
        printStream.println("compile");
        int length = inputStream.read(remsg);
        if (new String(remsg, 0, length, "UTF-8").contains("Roger")) {
            Logger.log("Client Received, Sending File Path");
            printStream.println(compileConfig.filePath);
            Logger.log("Sending Language");
            printStream.println(compileConfig.language.getName());
            Logger.log("Receiving Result");
            length = inputStream.read(remsg);
            return new String(remsg, 0, length, "UTF-8");
        }
        return null;
    }
    public void sendCmd(String cmd) throws IOException {
        Logger.log("Sending Command " + cmd);
        printStream.println(cmd);
        Logger.log("Command Successfully Sent");
    }
}
