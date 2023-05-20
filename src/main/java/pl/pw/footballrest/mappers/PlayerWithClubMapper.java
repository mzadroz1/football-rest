package pl.pw.footballrest.mappers;

import org.mapstruct.*;
import pl.pw.footballrest.dto.PlayerWithClubDto;
import pl.pw.footballrest.entity.Player;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClubMapper.class})
public interface PlayerWithClubMapper {
    Player toEntity(PlayerWithClubDto playerWithClubDto);

    PlayerWithClubDto toDto(Player player);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Player partialUpdate(PlayerWithClubDto playerWithClubDto, @MappingTarget Player player);
}