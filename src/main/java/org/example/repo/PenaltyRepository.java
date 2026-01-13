package org.example.repo;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.model.Strafe;
import org.example.model.StrafeGrund;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PenaltyRepository {
    private final JsonFileReader reader = new JsonFileReader();

    public List<Strafe> readAll(Path path) throws IOException {
        JsonNode root = reader.readRoot(path);
        if (root == null || !root.isArray()) return new ArrayList<Strafe>();

        List<Strafe> res = new ArrayList<Strafe>();
        for (JsonNode n : root) {
            int id = JsonFileReader.asInt(JsonFileReader.pick(n, "id", "Id"), 0);
            int fahrerId = JsonFileReader.asInt(JsonFileReader.pick(n, "fahrerId", "FahrerId", "driverId", "DriverId"), 0);
            String grundStr = JsonFileReader.asText(JsonFileReader.pick(n, "grund", "Grund", "reason", "Reason"), "TRACK_LIMITS");
            int seconds = JsonFileReader.asInt(JsonFileReader.pick(n, "seconds", "Seconds"), 0);
            int lap = JsonFileReader.asInt(JsonFileReader.pick(n, "lap", "Lap"), 0);

            StrafeGrund grund;
            try { grund = StrafeGrund.valueOf(grundStr.trim()); }
            catch (Exception e) { grund = StrafeGrund.TRACK_LIMITS; }

            res.add(new Strafe(id, fahrerId, grund, seconds, lap));
        }
        return res;
    }
}
