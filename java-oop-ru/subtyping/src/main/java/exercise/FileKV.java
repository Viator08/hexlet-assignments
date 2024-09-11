package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String path;
    private Map<String, String> data;
    private int hash;

    public FileKV(String path, Map<String, String> data) {
        this.path = path;
        this.data = data;
    }

    private void writeFile() {
        var data = Utils.serialize(this.data);
        Utils.writeFile(path, data);
        this.hash = generateHash(this.data);
    }

    private Map<String, String> readFile() {
        var file = Utils.readFile(path);
        var data = Utils.deserialize(file);
        if (this.hash != generateHash(data)) {
            this.data = data;
            this.hash = generateHash(this.data);
        }
        return data;
    }

    @Override
    public void set(String key, String value) {
        this.data.put(key, value);
        this.writeFile();
    }

    @Override
    public void unset(String key) {
        this.data.remove(key);
        this.writeFile();
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return this.data;
    }

    private int generateHash(Map<String, String> data) {
        return data.hashCode();
    }
}
// END
