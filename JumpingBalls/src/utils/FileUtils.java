package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Nikita on 09.10.2016.
 */
public class FileUtils {
    public static String getWholeText(String filename) {
        File file = new File(filename);
        byte[] data = new byte[(int) file.length()];
        try {
            FileInputStream input = new FileInputStream(file);
            input.read(data);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(data);
    }
}
