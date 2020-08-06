package lib.compiler;

import lib.debug.Logger;
import lib.tools.SystemInfo;

import java.io.InputStream;

public class JavaCompile {
    private static String windowsCompile(String filePath) throws Exception {
        Logger.log("Starting Compile");
        Process p = Runtime.getRuntime().exec(new String[] {"javac", filePath});
        InputStream inputStream = p.getErrorStream();
        Logger.log("Error Stream Established");
        StringBuffer result = new StringBuffer();
        byte[] line = new byte[512];
        int length = 0;
        while ((length = inputStream.read(line)) != -1) {
            result.append(new String(line, 0, length, "GBK"));
        }
        Logger.log("Got Result: ");
        System.out.println(result.toString());
        return result.toString();
    }
    public static String compile(String filePath) throws Exception {
        switch (SystemInfo.getOS()) {
            case WINDOWS:
                return windowsCompile(filePath);
            default:
                Logger.error("Operating System not supported");
                System.exit(-1);
        }
        return null;
    }
}
