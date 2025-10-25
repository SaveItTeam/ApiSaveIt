package com.api.model;

import com.api.dto.stock.StockSummary;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@SqlResultSetMapping(
        name = "BatchByEnterpriseMapping",
        classes = @ConstructorResult(
                targetClass = StockSummary.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "batchId", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "expirationDate", type = Date.class),
                        @ColumnResult(name = "quantity", type = String.class),
                        @ColumnResult(name = "image", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "Batch.listOfBatches",
        query = "SELECT * FROM get_batches_by_enterprise(:enterpriseId)",
        resultSetMapping = "BatchByEnterpriseMapping"
)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Campo vazio")
    @Column(name = "id",unique = true)
    private long id;
    @Column(name = "unit_measure")
    private String unitMeasure;
    @Column(name = "entry_date")
    private Date entryDate;
    @Column(name = "batch_code")
    private String batchCode;
    @Column(name = "max_quantity")
    private Integer maxQuantity;
    @Column(name = "expiration_date")
    private Date expirationDate;
    private Integer quantity;
    @Column(name = "product_id")
    private long productId;
}
