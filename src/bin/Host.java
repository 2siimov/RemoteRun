package bin;

import bin.communicator.HostSend;
import lib.config.CompileConfig;
import lib.config.SocketConfig;
import lib.ui.host.CommandlineUI;

import java.io.IOException;

public class Host {
    private static Host instance;
    private SocketConfig socketConfig;
    private HostSend hostSend;
    public Host() throws IOException {
        CommandlineUI.printGreeting();
        socketConfig = CommandlineUI.getSocketConfig();
        hostSend = new HostSend(socketConfig);
    }
    public static void main(String[] args) throws Exception {
        instance = new Host();
        instance.hostSend.getStream();
        CommandlineUI.mainInterface(instance.socketConfig, new CompileConfig(), instance.hostSend);

    }
}
