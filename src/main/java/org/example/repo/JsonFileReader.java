package org.example.repo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JsonFileReader {
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode readRoot(Path path) throws IOException {
        File f = path.toFile();
        if (!f.exists()) throw new IOException("File not found: " + path.toString());
        return mapper.readTree(f);
    }

    public static JsonNode pick(JsonNode node, String... keys) {
        if (node == null) return null;
        for (String k : keys) {
            JsonNode v = node.get(k);
            if (v != null && !v.isNull()) return v;
        }
        return null;
    }

    public static int asInt(JsonNode node, int def) {
        if (node == null || node.isNull()) return def;
        if (node.isInt()) return node.asInt();
        if (node.isNumber()) return node.intValue();
        if (node.isTextual()) {
            try { return Integer.parseInt(node.asText().trim()); } catch (Exception e) { return def; }
        }
        return def;
    }

    public static String asText(JsonNode node, String def) {
        if (node == null || node.isNull()) return def;
        return node.asText();
    }
}

