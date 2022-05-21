import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class MainClass {
    public static void main(String[] args) throws IOException {
        String jsonString = "{\"name\":\"Murka\",\"cats\":[{\"name\":\"Timka\"},{\"name\":\"Killer\"}]}";
        ObjectMapper mapper = new ObjectMapper();
        Cat2 cat = mapper.readValue(jsonString, Cat2.class);

        System.out.println(cat);
        System.out.println(cat.cats.getClass());
    }
}
