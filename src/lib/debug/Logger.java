package lib.debug;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void log(String msg) {
        System.out.printf("[" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "] "
            + msg + "\n");
    }
    public static void warning(String msg) {
        System.out.printf("[WARNING] " + msg + "\n");
    }
    public static void error(String msg) {
        System.out.printf("[ERROR] " + msg + "\n");
    }
}
