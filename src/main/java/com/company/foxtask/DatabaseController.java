package com.company.foxtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseController {

    final private DatabaseReinitialize db;

    @Autowired
    public DatabaseController(DatabaseReinitialize db) {
        this.db = db;
    }

    @DeleteMapping("delete-all")
    public void deleteAll() {
        db.deleteAll();
    }
}
