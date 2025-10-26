package com.api.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductShowcaseStatusDTO {
    private Long idEmpresa;
    private String nomeEmpresa;
    private Long idProduto;
    private String nomeProduto;
    private String dataValidade;
    private String estaNaVitrine;
    private int quantidadeVitrine;
}
