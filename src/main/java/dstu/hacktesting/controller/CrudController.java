package dstu.hacktesting.controller;

import dstu.hacktesting.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public abstract class CrudController<T, D> {
    protected final CrudService<T, D> crudService;
    @GetMapping
    public ResponseEntity<List<T>> readAll() {
        return new ResponseEntity<>(crudService.readAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<T> create(@RequestBody D dto) {
        return new ResponseEntity<>(crudService.create(dto), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<T> update(@RequestBody T model) {
        return new ResponseEntity<>(crudService.update(model), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        crudService.delete(id);
        return HttpStatus.OK;
    }
}
