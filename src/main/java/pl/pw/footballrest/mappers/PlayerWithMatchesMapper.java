package pl.pw.footballrest.mappers;

import org.mapstruct.*;
import pl.pw.footballrest.dto.PlayerWithMatchesDto;
import pl.pw.footballrest.entity.Player;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerWithMatchesMapper {
    Player toEntity(PlayerWithMatchesDto playerWithMatchesDto);

    PlayerWithMatchesDto toDto(Player player);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Player partialUpdate(PlayerWithMatchesDto playerWithMatchesDto, @MappingTarget Player player);

    @AfterMapping
    default void linkPlayerMatches(@MappingTarget Player player) {
        player.getPlayerMatches().forEach(playerMatch -> playerMatch.setPlayer(player));
    }
}