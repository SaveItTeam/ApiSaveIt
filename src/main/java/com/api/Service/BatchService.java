package com.api.Service;

import com.api.Model.Batch;
import com.api.Repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class BatchService {
    private final BatchRepository batchRepository;

    @Autowired
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }


    public List<Batch> listBatch(){return batchRepository.findAll();}

    public Batch insertBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    public void deleteBatch(Long id) {
        batchRepository.deleteById(id);
    }
    // Atualização
    public Batch updateBatch(Long id, Batch batchAtualizado) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        batch.setUnite_measure(batchAtualizado.getUnite_measure());
        batch.setEntry_date(batchAtualizado.getEntry_date());
        batch.setBatch_code(batchAtualizado.getBatch_code());
        batch.setExpiriation_date(batchAtualizado.getExpiriation_date());
        batch.setQuantity(batchAtualizado.getQuantity());
        batch.setProduct_id(batchAtualizado.getProduct_id());

        return batchRepository.save(batch);
    }

    public Batch updateBatchPartial(Long id, Map<String, Object> updates) {
        Batch batch = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        if (updates.containsKey("unit_measure")) {
            batch.setUnite_measure((String) updates.get("unit_measure"));
        }
        if (updates.containsKey("entry_date")) {
            batch.setEntry_date((Date) updates.get("entry_date"));
        }
        if (updates.containsKey("batch_code")) {
            batch.setBatch_code((String) updates.get("batch_code"));
        }
        if (updates.containsKey("expiriation_date")) {
            batch.setExpiriation_date((Date) updates.get("expiriation_date"));
        }
        if (updates.containsKey("quantity")) {
            batch.setQuantity((int) updates.get("quantity"));
        }
        if (updates.containsKey("product_id")) {
            batch.setProduct_id((long) updates.get("product_id"));
        }

        return batchRepository.save(batch);
    }
}
