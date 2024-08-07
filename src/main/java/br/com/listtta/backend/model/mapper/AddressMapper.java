package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.address.NewUserAddressDTO;
import br.com.listtta.backend.model.dto.address.UpdateUserAddressDTO;
import br.com.listtta.backend.model.entities.address.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "addressId", source = "addressId")
    @Mapping(target = "cityZone", ignore = true)
    @Mapping(target = "district", ignore = true)
    @Mapping(target = "street", ignore = true)
    @Mapping(target = "complement", ignore = true)
    @Mapping(target = "zipCode", ignore = true)
    Address newUserAddressDtoToModel(NewUserAddressDTO newUserAddressDto);

    @Mapping(target = "addressId", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "puid", ignore = true)
    Address updateUserAddressDtoToModel(UpdateUserAddressDTO updateDto);
}
