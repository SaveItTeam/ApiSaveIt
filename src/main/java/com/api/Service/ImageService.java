package com.api.Service;

import com.api.Model.Image;
import com.api.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    //    Métodos de busca
    public List<Image> listarImage(){return imageRepository.findAll();}

    public Image inserirImage(Image image) {
        return imageRepository.save(image);
    }

    public void excluirImage(Long id) {
        // Se o produto não for encontrado, pode ser lançado um erro posteriormente.
        // Dependendo da implementação do repository, pode ser necessário buscar primeiro o produto.
        //        Endereco existe = buscarProdutoPorId(id);
        imageRepository.deleteById(id);
        //        return;
    }
    public Image atualizarImage(Long id, Image imageAtualizado) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Imagem com ID " + id + " não encontrado"));

        image.setImage(imageAtualizado.getImage());
        image.setProduct_id(imageAtualizado.getProduct_id());

        return imageRepository.save(image);
    }


    public Image atualizarImageParcial(Long id, Map<String, Object> updates) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Image com ID " + id + " não encontrado"));

        if (updates.containsKey("image")) {
            image.setImage((Byte[]) updates.get("image"));
        }
        if (updates.containsKey("product_id")) {
            image.setProduct_id((long) updates.get("product_id"));
        }

        return imageRepository.save(image);
    }
}
