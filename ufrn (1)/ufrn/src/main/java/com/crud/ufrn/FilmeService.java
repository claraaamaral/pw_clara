package com.crud.ufrn;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FilmeService {
    FilmeRepository repository;
    public FilmeService (FilmeRepository repository){
        this.repository= repository;
    }
    public Filme create (Filme f){
        return repository.save(f);
    }
    public void deleteById(long id){
        repository.deleteById(id);
    }
    public Filme update (Filme f){
        return repository.saveAndFlush(f);
    }
    public Optional<Filme> findById (Long id){
        return repository.findById(id);
    }
    public List<Filme> findAll(){
        return repository.findAll();


    }
}
