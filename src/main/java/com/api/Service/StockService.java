package com.api.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Model.Address;
import com.api.Model.Stock;
import com.api.Repository.StockRepository;
import com.api.dto.address.AddressResponseDTO;
import com.api.dto.estock.StockRequestDTO;
import com.api.dto.estock.StockResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class StockService {

    private final StockRepository stockRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public StockService(StockRepository stockRepository, ObjectMapper objectMapper){
        this.stockRepository = stockRepository;
        this.objectMapper = objectMapper;
    }



    //    Métodos de busca
    public List<StockResponseDTO> listStock(){
        List<Stock> stocks = stockRepository.findAll();
        List<StockResponseDTO> stockResponseDTOS = new ArrayList<>();
        for (Stock stock : stocks) {
            stockResponseDTOS.add(objectMapper.convertValue(stock, StockResponseDTO.class));
        }
        return stockResponseDTOS;
    }

    // Inserção de enderecos
    public void insertStock(StockRequestDTO stock) {
        Stock stockRequest = objectMapper.convertValue(stock, Stock.class);
        stockRepository.save(stockRequest);
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
    public StockResponseDTO updateStock(Long id, StockRequestDTO stockAtualizado) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Estoque com ID " + id + " não encontrado"));

        stock.setQuantity(stockAtualizado.getQuantity());
        stock.setQuantity_output(stockAtualizado.getQuantity_output());
        stock.setBatch_id(stockAtualizado.getBatch_id());

        stockRepository.save(stock);
        return objectMapper.convertValue(stock, StockResponseDTO.class);
    }
    // Atualização de endereço parcial

    public StockResponseDTO updateStockPartial(Long id, Map<String, Object> updates) {
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
        stockRepository.save(stock);
        return objectMapper.convertValue(stock, StockResponseDTO.class);
    }
}
