package org.example.repo;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.model.Fahrer;
import org.example.model.FahrerStatus;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FahrerRepository {

    private final JsonFileReader reader = new JsonFileReader();

    public List<Fahrer> readAll(Path path) throws IOException {
        JsonNode root = reader.readRoot(path);
        List<Fahrer> res = new ArrayList<Fahrer>();

        if (root == null || !root.isArray()) return res;

        for (JsonNode n : root) {
            int id = JsonFileReader.asInt(JsonFileReader.pick(n, "id", "Id"), 0);
            String name = JsonFileReader.asText(JsonFileReader.pick(n, "name", "Name"), "");
            String team = JsonFileReader.asText(JsonFileReader.pick(n, "team", "Team"), "");
            String statusStr = JsonFileReader.asText(JsonFileReader.pick(n, "status", "Status"), "ACTIVE");
            int skill = JsonFileReader.asInt(JsonFileReader.pick(n, "skillLevel", "SkillLevel"), 0);

            FahrerStatus status;
            try {
                status = FahrerStatus.valueOf(statusStr.trim());
            } catch (Exception e) {
                status = FahrerStatus.ACTIVE;
            }

            res.add(new Fahrer(id, name, team, status, skill));
        }

        return res;
    }
}

