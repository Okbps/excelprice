package ut;

import java.io.*;
import java.security.SecureRandom;

public class FileUtil {
    public static long copy(InputStream source, OutputStream sink) throws IOException
    {
        long nread = 0L;
        byte[] buf = new byte[8192];
        int n;
        while ((n = source.read(buf)) > 0) {
            sink.write(buf, 0, n);
            nread += n;
        }
        return nread;
    }

    public static File[] getFiles(String folderPath){
        File folder = new File(folderPath);
        return folder.listFiles();
    }

    public static void saveFile(InputStream source, String fileName){
        File file = new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            copy(source, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomName(String prefix, String ext){
        final SecureRandom random = new SecureRandom();

        long n = random.nextLong();
        if (n == Long.MIN_VALUE) {
            n = 0;
        } else {
            n = Math.abs(n);
        }

        return prefix + Long.toString(n) + (ext.isEmpty() ? "" : ".") + ext;

    }
}
