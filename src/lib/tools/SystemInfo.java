package lib.tools;

import lib.debug.Logger;
import lib.units.OS;

public class SystemInfo {
    public static OS getOS() {
        Logger.log("Getting OS Name");
        String osName = System.getProperties().getProperty("os.name");
        if (osName.equals("Mac OS X")) {
            Logger.log("macOS Detected");
            return OS.MACOS;
        } else if (osName.contains("Windows")) {
            Logger.log("Windows Detected");
            return OS.WINDOWS;
        } else {
            Logger.error("Fatal: Your Operating System is not supported, exiting now");
            System.exit(-1);
        }
        return null;
    }
}
