package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhenliu on 8/24/15.
 */
public class WriteLog {
    private static boolean isCalledWriteLogToFileMethod = false;
    public static void writeLogToFile(String logMessage) throws IOException {
        File file = new File("CustomerMessageLog");

        if (!file.exists()) {
                file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        fileOutputStream.write(logMessage.getBytes("utf-8"));
        isCalledWriteLogToFileMethod = true;
        fileOutputStream.close();

    }

    public static boolean isCalledWriteLogToFileMethod() {
        return isCalledWriteLogToFileMethod;
    }
}
