package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private Map<String, String> data;


    public InMemoryKV(Map<String, String> data) {
        this.data = data;
    }

    @Override
    public Map<String, String> toMap() {
        return data;
    }

    @Override
    public void set(String key, String value) {
        this.data.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.data.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.data.getOrDefault(key, defaultValue);
    }
}
// END
