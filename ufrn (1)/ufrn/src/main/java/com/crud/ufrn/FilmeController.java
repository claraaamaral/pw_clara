package com.crud.ufrn;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
@CrossOrigin(origins = "*")
public class FilmeController {

  FilmeService service;
    ModelMapper modelMapper = new ModelMapper();


    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Filme> findAll(){
        return service.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<FilmeResponseDTO> findById(@PathVariable Long id){
        Optional<Filme> f  = service.findById(id);
        if (f.isPresent()){
            Filme filme = f.get();
           FilmeResponseDTO filmeResponseDto = modelMapper.map(filme, FilmeResponseDTO.class);
            filmeResponseDto.addHateoasLinks(filme.getId());

            return ResponseEntity.ok().body(filmeResponseDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Filme> insert(@RequestBody FilmeRequestDTO f) throws URISyntaxException {


        Filme novo = modelMapper.map(f, Filme.class);


        service.create(novo);

        URI uri = new URI("/filmes/" + novo.getId());
        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Filme> update (@PathVariable Long id, @RequestBody Filme f){
        if (service.findById(id).isPresent()){
            Filme atualizado= modelMapper.map(f, Filme.class);
            atualizado.setId(id);
            service.update(atualizado);
            return ResponseEntity.ok().body(atualizado);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id ){
        if (service.findById(id).isPresent()){
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}