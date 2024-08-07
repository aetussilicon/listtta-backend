package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsSkillsDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalsSkills;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfessionalsSkillsMapper {

    ProfessionalsSkillsMapper INSTANCE = Mappers.getMapper(ProfessionalsSkillsMapper.class);

    @Mapping(target = "skillId", ignore = true)
    ProfessionalsSkills skillsDtoToModel (ProfessionalsSkillsDTO skillsDto);
}
