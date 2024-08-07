package br.com.listtta.backend.model.mapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.apache.tika.Tika;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsViewDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalsView;
import br.com.listtta.backend.model.enums.CitiesZone;

@Mapper(componentModel = "spring")
public interface ProfessionalsViewMapper {

    ProfessionalsViewMapper INSTANCE = Mappers.getMapper(ProfessionalsViewMapper.class);

    @Named("mapCityZone")
    default String mapCityZone(CitiesZone cityZone) {
        return cityZone != null ? cityZone.getCityZone() : null;
    }

    @Named("mapByteaToBase64")
    default String mapByteaToBase64(byte[] bytea) {
        return bytea != null ? Base64.getEncoder().encodeToString(bytea) : null;
    }

    @Named("detectMimetype")
    default String detectMimetype(byte[] bytea) {
        if (bytea == null) {
            return null;
        }

        String mimeType;

        try {
            Tika tika = new Tika();
            mimeType = tika.detect(bytea);
        } catch (Exception e) {
            e.printStackTrace();
            mimeType = null;
        }

        return mimeType;
    }

    @Mappings({
            @Mapping(source = "state", target = "address.state"),
            @Mapping(source = "city", target = "address.city"),
            @Mapping(source = "cityZone", target = "address.cityZone", qualifiedByName = "mapCityZone"),
            @Mapping(source = "district", target = "address.district"),
            @Mapping(source = "street", target = "address.street"),
            @Mapping(source = "complement", target = "address.complement"),
            @Mapping(source = "zipCode", target = "address.zipCode"),
            @Mapping(source = "type", target = "details.type"),
            @Mapping(source = "instagramUrl", target = "details.instagramUrl"),
            @Mapping(source = "skills", target = "details.skills"),
            @Mapping(source = "profilePicture", target = "profilePicture", qualifiedByName = "mapByteaToBase64"),
            @Mapping(source = "profilePicture", target = "mimetype", qualifiedByName = "detectMimetype")

    })
    ProfessionalsViewDTO getProfessionalView(ProfessionalsView professionalsView);

    @InheritConfiguration
    List<ProfessionalsViewDTO> listAllProfessionalsView(List<ProfessionalsView> professionalsViews);
}
