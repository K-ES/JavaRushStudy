import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.LinkedList;
import java.util.List;

public class Cat2 {
    public String name;
//    @JsonDeserialize(as = LinkedList.class)
    public List<Cat> cats;
}
