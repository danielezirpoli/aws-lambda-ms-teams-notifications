package com.danzir;

import com.danzir.deserializer.MessageDeserializer;
import org.junit.jupiter.api.Test;

public class MessageDeserializerTest {

    @Test
    public void deserialize() {
        String s = "{\\n    \\\"body\\\": \\\"Some of the endpoints don't meet the expected \\\\\\\"Response time\\\\\\\" KPI (Response time \\\\u003c= 3000 ms)\\\",\\n    \\\"name\\\": \\\"Response time KPI not satisfied\\\",\\n    \\\"state\\\": \\\"no_data\\\",\\n    \\\"path\\\": \\\"/d/-9uhfTQVk/load-test\\\"\\n}";
        MessageDeserializer.deserialize(s);
    }
}
