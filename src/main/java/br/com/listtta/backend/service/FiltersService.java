package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.users.CannotUpdateUsersFieldsException;
import br.com.listtta.backend.model.dto.filters.CreateNewFilterDTO;
import br.com.listtta.backend.model.dto.filters.FiltersDTO;
import br.com.listtta.backend.model.dto.filters.UpdateFilterDTO;
import br.com.listtta.backend.model.entities.filters.Filters;
import br.com.listtta.backend.model.mapper.FiltersMapper;
import br.com.listtta.backend.repository.FiltersRepository;
import br.com.listtta.backend.util.validation.Patcher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FiltersService {

    private final FiltersRepository filtersRepository;
    private final FiltersMapper filtersMapper;

    private Filters checkFilterInDatabaseByName(String filterName) {
        Optional<Filters> checkFilterInDatabaseByName = filtersRepository.findByFilterName(filterName);

        if (checkFilterInDatabaseByName.isPresent()) {
            return checkFilterInDatabaseByName.get();
        }  throw new RuntimeException("Filtro não encontrado!");
    }

    @PostConstruct
    public void initializeFilters() {
        if(!filtersRepository.existsById(1L)) {
            List<Filters> initialFilters = Arrays.asList(
                    new Filters(1L, "abstract", "Abstrato"), 
                    new Filters (2L , "watercolor", "Aquarela"),
                    new Filters (3L , "fusion", "Art Fusion"),
                    new Filters (4L , "black-gray", "Black & Gray (preto e cinza)"),
                    new Filters (5L , "blackwork", "Blackwork"),
                    new Filters (6L , "bold-line", "Bold-Line"),
                    new Filters (7L , "scar-coverage", "Cobertura de Cicatriz"),
                    new Filters (8L , "tattoo-coverage", "Cobertura de Tattoo"),
                    new Filters (9L , "colorful", "Colorida"),
                    new Filters (10L ,"stylized", "Estilizada"),
                    new Filters (11L ,"fineline", "Fineline (traços finos)"),
                    new Filters (12L ,"floral", "Floral"),
                    new Filters (13L ,"freehand", "Freehand"),
                    new Filters (14L ,"from-hell", "From Hell"),
                    new Filters (15L ,"gek", "Geek"),
                    new Filters (16L ,"geometric", "Geométrico"),
                    new Filters (17L ,"glitter", "Glitter"),
                    new Filters (18L ,"handpoke", "Handpoke"),
                    new Filters (19L ,"kwaii", "kwaii"),
                    new Filters (20L ,"lettering", "Lettering"),
                    new Filters (21L ,"mandala", "Mandala"),
                    new Filters (22L ,"maori", "Maori"),
                    new Filters (23L ,"neo-tradicional", "Neo Tradicional"),
                    new Filters (24L ,"new-school", "New School"),
                    new Filters (25L ,"old-school", "Old School"),
                    new Filters (26L ,"oriental", "Oriental"),
                    new Filters (27L ,"patch", "Patch (bordado)"),
                    new Filters (28L ,"pet", "Pet"),
                    new Filters (29L ,"pixel", "Pixel"),
                    new Filters (30L ,"pointillism", "Pontilhismo"),
                    new Filters (31L ,"realism", "Realismo"),
                    new Filters (32L ,"sketch", "Sketch"),
                    new Filters (33L ,"sticker", "Sticker (Adesivo)"),
                    new Filters (34L, "tribal", "Tribal")
            );
            filtersRepository.saveAll(initialFilters);
        }
    }

    private Filters checkFilterInDatabaseById(Long filterId) {
        Optional<Filters> checkFilterInDatabaseById = filtersRepository.findById(filterId);

        if (checkFilterInDatabaseById.isPresent()) {
            return checkFilterInDatabaseById.get();
        }
        throw new RuntimeException("Filtro não encontrado!");
    }

    public Filters createNewFilter(CreateNewFilterDTO createNewFilterDto) {
        return filtersRepository.save(filtersMapper.createNewFilterDtoToModel(createNewFilterDto));
    }

    public Filters patchFilter(Long filterId, UpdateFilterDTO updateFilterDto) {
        Filters checkedFilter = checkFilterInDatabaseById(filterId);
        Filters fieldsToUpdate = filtersMapper.updateFilterDtoToModel(updateFilterDto);

        try {
            Patcher.patch(checkedFilter, fieldsToUpdate);
            filtersRepository.save(checkedFilter);
        } catch (IllegalAccessException e) {
            throw new CannotUpdateUsersFieldsException("Não foi possível atualizar o filtro!");
        }
        return checkedFilter;
    }

    public FiltersDTO getOneFilter(String filterName) {
        return filtersMapper.filtersModelToDto(checkFilterInDatabaseByName(filterName));
    }

    public List<FiltersDTO> gerAllFilters() {
        return filtersMapper.listFiltersModelToDto(filtersRepository.findAll());
    }

    public String deleteFilter(Long filterId) {
        checkFilterInDatabaseById(filterId);
        filtersRepository.deleteById(filterId);
        return "Filtro deletado com sucesso!";
    }
}
