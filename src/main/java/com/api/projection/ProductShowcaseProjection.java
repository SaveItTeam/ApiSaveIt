package com.api.projection;

public interface ProductShowcaseProjection {
    Long getProductId();
    String getDescription();
    Double getPrice();
    Long getLoteId();
    String getName();
    String getTipoPeso();
    Double getQuantidadeGeral();
    String getImage();
    String getEmpresa();
    String getLocalizacao();
    String getValidade();
}
