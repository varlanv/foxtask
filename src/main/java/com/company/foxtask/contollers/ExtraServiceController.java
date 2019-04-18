package com.company.foxtask.contollers;

import com.company.foxtask.model.entity.ExtraService;
import com.company.foxtask.model.repository.ExtraServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class  ExtraServiceController {

    private final ExtraServiceRepository repository;

    @Autowired
    public ExtraServiceController(ExtraServiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/extra-services")
    public List<ExtraService> getAll() {
        return repository.findAll();
    }
}