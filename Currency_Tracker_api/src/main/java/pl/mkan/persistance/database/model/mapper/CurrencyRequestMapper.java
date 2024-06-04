package pl.mkan.persistance.database.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.mkan.controller.dto.RequestDTO;
import pl.mkan.persistance.database.model.CurrencyRequestEntity;

@Mapper(componentModel = "spring")
public interface CurrencyRequestMapper {
    @Mapping(source = "mid", target = "value")
    RequestDTO toDTO(CurrencyRequestEntity entity);
}
