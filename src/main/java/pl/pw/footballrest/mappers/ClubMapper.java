package pl.pw.footballrest.mappers;

import org.mapstruct.*;
import pl.pw.footballrest.dto.ClubDto;
import pl.pw.footballrest.entity.Club;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClubMapper {
    Club toEntity(ClubDto clubDto);

    ClubDto toDto(Club club);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Club partialUpdate(ClubDto clubDto, @MappingTarget Club club);
}