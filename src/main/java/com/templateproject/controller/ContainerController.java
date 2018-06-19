package com.templateproject.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.templateproject.domain.Container;
import com.templateproject.service.ContainerService;

@RestController
@RequestMapping("/container")
@CrossOrigin("*")
public class ContainerController {
	
	@Autowired
    private ContainerService containerService;
	
	@GetMapping
    public List<Container> getAll() {
        return containerService.findAll();
    }

    @PostMapping
    public Container createTodo(@Valid @RequestBody Container container) throws Exception {
        return containerService.save(container);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<Optional<Container>> getTodoById(@PathVariable("id") long id) {
    	return containerService.findById(id);
    }

    @PutMapping(value="{id}")
    public ResponseEntity<Container> updateTodo(@PathVariable("id") long id,
                                           @Valid @RequestBody Container container) {
        return containerService.update(id,container);
    }

    @DeleteMapping(value="{id}")
    public void deleteTodo(@PathVariable("id") long id) {
        containerService.deleteById(id);
    }
	
}
