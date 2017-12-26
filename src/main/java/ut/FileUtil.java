package ut;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static long copy(String fileName, OutputStream sink) throws IOException
    {
        return copy(new FileInputStream(fileName), sink);
    }


    public static File[] getFiles(String folderPath){
        File folder = new File(folderPath);
        return folder.listFiles();
    }

    public static List<String> getFileNames(String folderName){
        File[] files = getFiles(folderName);

        return Arrays.stream(files)
                .map(file -> "/upload/" + file.getName())
                .collect(Collectors.toList());
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

    public static boolean deleteFile(String fileName){
        try {
            return Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            return false;
        }
    }
}
