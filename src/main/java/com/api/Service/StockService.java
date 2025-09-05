package com.api.Service;

import com.api.Model.Stock;
import com.api.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }



    //    Métodos de busca
    public List<Stock> listStock(){return stockRepository.findAll();}

    // Inserção de enderecos
    public Stock insertStock(Stock stock) {
        return stockRepository.save(stock);
    }

    // Deleção de endereços
    public void deleteStock(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        stockRepository.deleteById(id);
        //        return;
    }


    // Atualização de endereços
    public Stock updateStock(Long id, Stock stockAtualizado) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estoque com ID " + id + " não encontrado"));

        stock.setQuantity(stockAtualizado.getQuantity());
        stock.setQuantity_output(stockAtualizado.getQuantity_output());
        stock.setBatch_id(stockAtualizado.getBatch_id());

        return stockRepository.save(stock);
    }
    // Atualização de endereço parcial

    public Stock updateStockPartial(Long id, Map<String, Object> updates) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estoque com ID " + id + " não encontrado"));

        if (updates.containsKey("quantity")) {
            stock.setQuantity((int) updates.get("quantity"));
        }
        if (updates.containsKey("quantity_output")) {
            stock.setQuantity_output((int) updates.get("quantity_output"));
        }
        if (updates.containsKey("batch_id")) {
            stock.setBatch_id((int) updates.get("batch_id"));
        }
        return stockRepository.save(stock);
    }
}
