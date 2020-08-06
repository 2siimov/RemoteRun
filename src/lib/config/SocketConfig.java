package lib.config;

import lib.debug.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketConfig {
    private String clientName;
    private int clientPort;
    private InetAddress clientAddress;
    public SocketConfig() {}
    public SocketConfig(int clientPort) {
        this.clientName = "localhost";
        try {
            this.clientAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            Logger.error("Fatal: Unknown Host Exception Caught");
            e.printStackTrace();
            System.exit(-1);
        }
        this.clientPort = clientPort;
    }
    public SocketConfig(String clientName, InetAddress clientAddress, int clientPort) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
    }
    public String getClientName() {
        return this.clientName;
    }
    public int getClientPort() {
        return this.clientPort;
    }
    public InetAddress getClientAddress() {
        return this.clientAddress;
    }
    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }
    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
