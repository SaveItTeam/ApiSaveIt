package com.api.Service;

import com.api.Model.Lote;
import com.api.Repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class LoteService {
    private final BatchRepository batchRepository;

    @Autowired
    public LoteService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }


    //    Métodos de busca
    public List<Lote> listarLotes(){return batchRepository.findAll();}

    // Inserção
    public Lote inserirLote(Lote lote) {
        return batchRepository.save(lote);
    }

    // Deleção
    public void excluirLote(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        batchRepository.deleteById(id);
        //        return;
    }
    // Atualização
    public Lote atualizarLote(Long id, Lote loteAtualizado) {
        Lote lote = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        lote.setUnite_measure(loteAtualizado.getUnite_measure());
        lote.setEntry_date(loteAtualizado.getEntry_date());
        lote.setBatch_code(loteAtualizado.getBatch_code());
        lote.setExpiriation_date(loteAtualizado.getExpiriation_date());
        lote.setQuantity(loteAtualizado.getQuantity());
        lote.setProduct_id(loteAtualizado.getProduct_id());

        return batchRepository.save(lote);
    }

    public Lote atualizarLoteParcial(Long id, Map<String, Object> updates) {
        Lote lote = batchRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lote com ID " + id + " não encontrado"));

        if (updates.containsKey("unit_measure")) {
            lote.setUnite_measure((String) updates.get("unit_measure"));
        }
        if (updates.containsKey("entry_date")) {
            lote.setEntry_date((Date) updates.get("entry_date"));
        }
        if (updates.containsKey("batch_code")) {
            lote.setBatch_code((String) updates.get("batch_code"));
        }
        if (updates.containsKey("expiriation_date")) {
            lote.setExpiriation_date((Date) updates.get("expiriation_date"));
        }
        if (updates.containsKey("quantity")) {
            lote.setQuantity((int) updates.get("quantity"));
        }
        if (updates.containsKey("product_id")) {
            lote.setProduct_id((long) updates.get("product_id"));
        }

        return batchRepository.save(lote);
    }
}
