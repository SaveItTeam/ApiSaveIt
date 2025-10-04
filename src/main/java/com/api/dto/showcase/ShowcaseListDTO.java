package com.api.dto.showcase;


import lombok.Data;

@Data
public class ShowcaseListDTO {
    private Long id;
    private String name;
    private String image;

    public ShowcaseListDTO(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
