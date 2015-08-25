package utils;


import java.io.*;

public class FileUtils {
    private static boolean isCalledWriteMessageLog;

    public static void writeMessageLog(String message) {
        PrintStream printer = null;
        try {
            File file = new File("/Users/ppyao/yaopingping/BeachProject/YaopingpingJob1 /src/main/resources/sendMessageLog");
            if (!file.exists()) {
                file.createNewFile();
            }
            printer = new PrintStream(new FileOutputStream(file, true));
            printer.append(message);
            isCalledWriteMessageLog = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printer.close();
        }
    }

    public static boolean isCalledWriteMessageLog() {
        return isCalledWriteMessageLog;
    }
}
