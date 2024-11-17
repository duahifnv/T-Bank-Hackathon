package dstu.hacktesting.service;

import dstu.hacktesting.repository.CrudRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public abstract class CrudService<T, D> {
    private final CrudRepository<T> crudRepository;
    public abstract T create(D dto);
    public List<T> readAll() {
        return crudRepository.findAll();
    }
    public T update(T model) {
        return crudRepository.save(model);
    }
    public void delete(Long id) {
        crudRepository.deleteById(id);
    }
}
