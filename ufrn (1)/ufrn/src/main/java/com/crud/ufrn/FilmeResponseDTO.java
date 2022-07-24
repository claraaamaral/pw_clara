package com.crud.ufrn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmeResponseDTO extends RepresentationModel<FilmeResponseDTO> {

    private String titulo;
    private int duracao;
    private String genero;
    private String direcao;
    private int faixaEtaria;
    public void addHateoasLinks(Long id) {

        this.add(linkTo(FilmeController.class).slash(id).withSelfRel());
        this.add(linkTo(FilmeController.class).withRel("GET"));
        this.add(linkTo(FilmeController.class).slash(id).withRel("DELETE"));
        this.add(linkTo(FilmeController.class).slash(id).withRel("PUT"));
        this.add(linkTo(FilmeController.class).withRel("POST"));

    }
}
