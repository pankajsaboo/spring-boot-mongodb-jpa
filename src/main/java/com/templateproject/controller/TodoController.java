package com.templateproject.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

import com.templateproject.domain.Todo;
import com.templateproject.repository.TodoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return todoRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    @GetMapping(value="/todos/{id}")
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable("id") String id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        }
    }

//    @PutMapping(value="/todos/{id}")
//    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id,
//                                           @Valid @RequestBody Todo todo) {
//        Optional<Todo> todoData = todoRepository.findById(id);
//        if(todoData == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        todoData.setTitle(todo.getTitle());
//        todoData.setCompleted(todo.getCompleted());
//        Todo updatedTodo = todoRepository.save(todoData);
//        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
//    }

    @DeleteMapping(value="/todos/{id}")
    public void deleteTodo(@PathVariable("id") String id) {
        todoRepository.deleteById(id);
    }
}