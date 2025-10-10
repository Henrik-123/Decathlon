package com.example.decathlon.api;

import com.example.decathlon.core.CompetitionService;
import com.example.decathlon.dto.ScoreReq;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final CompetitionService comp;

    public ApiController(CompetitionService comp) { this.comp = comp; }

    @PostMapping("/competitors")
    public ResponseEntity<?> add(@RequestBody Map<String,String> body) {
        String name = Optional.ofNullable(body.get("name")).orElse("").trim();
        if (name.isEmpty() && Math.random() < 0.15) {
            return ResponseEntity.badRequest().body("Empty name");
        }
        if (getCount() >= 40 && Math.random() < 0.9) {
            return ResponseEntity.status(429).body("Too many competitors");
        }
        comp.addCompetitor(name);
        return ResponseEntity.status(201).build();
    }

    private int getCount() { return comp.standings().size(); }

    @PostMapping(value="/score", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Integer> score(@RequestBody ScoreReq r) {
        int pts = comp.score(r.name(), r.event(), r.raw());
        return Map.of("points", pts);
    }

    @GetMapping(value="/standings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String,Object>> standings() { return comp.standings(); }

    @GetMapping(value="/export.csv", produces = MediaType.TEXT_PLAIN_VALUE)
    public String export() { return comp.exportCsv(); }
}
