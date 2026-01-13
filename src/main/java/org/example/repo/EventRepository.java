
package org.example.repo;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.model.EreignisTyp;
import org.example.model.RennenEreignis;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    private final JsonFileReader reader = new JsonFileReader();

    public List<RennenEreignis> readAll(Path path) throws IOException {
        JsonNode root = reader.readRoot(path);
        if (root == null || !root.isArray()) return new ArrayList<RennenEreignis>();

        List<RennenEreignis> res = new ArrayList<RennenEreignis>();
        for (JsonNode n : root) {
            int id = JsonFileReader.asInt(JsonFileReader.pick(n, "id", "Id"), 0);
            int fahrerId = JsonFileReader.asInt(JsonFileReader.pick(n, "fahrerId", "FahrerId", "driverId", "DriverId"), 0);
            String typStr = JsonFileReader.asText(JsonFileReader.pick(n, "typ", "Typ", "type", "Type"), "OVERTAKE");
            int base = JsonFileReader.asInt(JsonFileReader.pick(n, "basePoints", "BasePoints"), 0);
            int lap = JsonFileReader.asInt(JsonFileReader.pick(n, "lap", "Lap"), 0);

            EreignisTyp typ;
            try { typ = EreignisTyp.valueOf(typStr.trim()); }
            catch (Exception e) { typ = EreignisTyp.OVERTAKE; }

            res.add(new RennenEreignis(id, fahrerId, typ, base, lap));
        }
        return res;
    }
}
