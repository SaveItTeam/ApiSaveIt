package com.api.repository;

import com.api.model.Image;
import com.api.projection.ProductShowcaseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "select \n" +
            "    p.id as productId,\n" +
            "    p.description as description,\n" +
            "    b.id as loteId,\n" +
            "    p.name as name,\n" +
            "    b.unit_measure as tipoPeso,\n" +
            "    s.quantity_showcase as quantidadeGeral,\n" +
            "    i.image as image,\n" +
            "    e.name as empresa,\n" +
            "    e.id as enterpriseId, \n" +
            "    concat(a.city, ' - ', a.state) as localizacao,\n" +
            "    b.expiration_date as validade \n" +
            "from Showcase s\n" +
            "join Batch b on s.batch_id = b.id\n" +
            "join Product p on b.product_id = p.id\n" +
            "left join Image i on i.product_id = p.id\n" +
            "join Enterprise e on p.enterprise_id = e.id\n" +
            "join Address a on e.address_id = a.id\n" +
            "where s.id = :showcaseId",
            nativeQuery = true)
    ProductShowcaseProjection findShowcaseWithImage(@Param("showcaseId") Long showcaseId);


    @Query("select i from Image i where i.productId = :productId")
    List<Image> findByProductId(@Param("productId") Long productId);

}
