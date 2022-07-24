package com.crud.ufrn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmeRequestDTO {
    private String titulo;
    private int duracao;
    private String genero;
    private String direcao;
    private int faixaEtaria;
}
