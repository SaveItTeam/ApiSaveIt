package com.api.projection;

import java.util.Date;

public interface ProductShowcaseProjection {
    Long getProductId();
    String getDescription();
    Long getLoteId();
    String getName();
    String getTipoPeso();
    Double getQuantidadeGeral();
    String getImage();
    String getEmpresa();
    Long getEnterpriseId();
    String getLocalizacao();
    Date getValidade();
}
