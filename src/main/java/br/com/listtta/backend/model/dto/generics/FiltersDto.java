package br.com.listtta.backend.model.dto.generics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltersDto {

    private Long filterId;
    private String filterName;
    private String displayName;

}