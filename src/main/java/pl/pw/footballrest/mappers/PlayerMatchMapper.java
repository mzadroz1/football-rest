package pl.pw.footballrest.mappers;

import org.mapstruct.*;
import pl.pw.footballrest.dto.PlayerMatchDto;
import pl.pw.footballrest.entity.PlayerMatch;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerMatchMapper {
    @Mapping(source = "idPlayerId", target = "id.playerId")
    @Mapping(source = "idMatchId", target = "id.matchId")
    PlayerMatch toEntity(PlayerMatchDto playerMatchDto);

    @InheritInverseConfiguration(name = "toEntity")
    PlayerMatchDto toDto(PlayerMatch playerMatch);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PlayerMatch partialUpdate(PlayerMatchDto playerMatchDto, @MappingTarget PlayerMatch playerMatch);
}