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
@SqlResultSetMappings(
        value = {
                @SqlResultSetMapping(
                        name = "StockSummaryMapping",
                        classes = @ConstructorResult(
                                targetClass = StockSummary.class,
                                columns = {
                                        @ColumnResult(name = "totalInput", type = Long.class),
                                        @ColumnResult(name = "totalOutput", type = Long.class),
                                        @ColumnResult(name = "totalDiscard", type = Long.class),
                                        @ColumnResult(name = "monthOutput", type = Integer.class)
                                }
                        )
                ),
                @SqlResultSetMapping(
                        name = "StockByProductSummaryMapping",
                        classes = @ConstructorResult(
                                targetClass = StockSummary.class,
                                columns = {
                                        @ColumnResult(name = "totalOutput", type = Long.class),
                                        @ColumnResult(name = "mouthOutput", type = Integer.class)
                                }
                        )
                )
        }
)
@NamedNativeQuery(
        name = "Stock.getStockSummary",
        query = "SELECT * FROM moviments(:enterpriseId)",
        resultSetMapping = "StockSummaryMapping"
)

@NamedNativeQuery(
        name = "Stock.getStockSummaryByProduct",
        query = "SELECT * FROM moviments_by_product(:enterpriseId, :productId)",
        resultSetMapping = "StockByProductSummaryMapping"
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
