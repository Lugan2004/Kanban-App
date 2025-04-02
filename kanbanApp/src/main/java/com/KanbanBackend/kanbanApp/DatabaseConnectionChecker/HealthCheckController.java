package com.KanbanBackend.kanbanApp.DatabaseConnectionChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {
    private final DatabaseConnectionChecker databaseConnectionChecker;

    @Autowired
    public HealthCheckController(DatabaseConnectionChecker databaseConnectionChecker) {
        this.databaseConnectionChecker = databaseConnectionChecker;
    }

    @GetMapping("/api/health/database")
    public ResponseEntity<Map<String, Object>> checkDatabaseHealth() {
        boolean isConnected = databaseConnectionChecker.isDatabaseConnected();

        Map<String, Object> response = new HashMap<>();
        response.put("status", isConnected ? "UP" : "DOWN");
        response.put("timestamp", new Date());

        HttpStatus status = isConnected ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE;

        return new ResponseEntity<>(response, status);
    }
}