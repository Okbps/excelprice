import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JsonUtil {
    public static void writeJson(OutputStream out, Object o){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(out, o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T readJson(InputStream in, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(in, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
