package com.api.projection;

public interface ProductShowcaseProjection {
    Long getProductId();
    String getDescription();
    Long getLoteId();
    String getName();
    String getTipoPeso();
    Double getQuantidadeGeral();
    String getImage();
    String getEmpresa();
    String getLocalizacao();
}
