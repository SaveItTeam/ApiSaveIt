package com.api.model;

import com.api.dto.stock.StockSummary;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SqlResultSetMapping(
        name = "StockSummaryMapping",
        classes = @ConstructorResult(
                targetClass = StockSummary.class,
                columns = {
                        @ColumnResult(name = "productId", type = Long.class),
                        @ColumnResult(name = "totalQuantityInput", type = Integer.class),
                        @ColumnResult(name = "totalQuantityOutput", type = Integer.class),
                        @ColumnResult(name = "totalDiscardQuantity", type = Integer.class)
                }
        )
)
@NamedNativeQuery(
        name = "Stock.getStockSummary",
        query = "SELECT * FROM moviments(:enterpriseId)",
        resultSetMapping = "StockSummaryMapping"
)
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private long id;
    @Column(name = "quantity_input")
    private Integer quantityInput;
    @Column(name = "quantity_output")
    private Integer quantityOutput;
    @Column(name = "batch_id")
    private long batchId;
    @Column(name = "product_id")
    private long productId;
    @Column(name = "discard_quantity")
    private Integer discardQuantity;
    @Column(name = "discard_reason")
    private String discardReason;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
