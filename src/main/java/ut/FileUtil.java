package ut;

import java.io.*;

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

    public static String getResourcePath(String path){
        String absoluteName = FileUtil.class.getClassLoader()
                .getResource("")
                .getPath()+path;

        return absoluteName.replaceFirst("/", "");
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

}
