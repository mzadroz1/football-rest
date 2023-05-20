package pl.pw.footballrest.mappers;

import org.mapstruct.*;
import pl.pw.footballrest.dto.PlayerDto;
import pl.pw.footballrest.entity.Player;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerMapper {
    Player toEntity(PlayerDto playerDto);

    PlayerDto toDto(Player player);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Player partialUpdate(PlayerDto playerDto, @MappingTarget Player player);
}