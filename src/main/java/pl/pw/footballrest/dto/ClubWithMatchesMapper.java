package pl.pw.footballrest.dto;

import org.mapstruct.*;
import pl.pw.footballrest.entity.Club;
import pl.pw.footballrest.mappers.MatchMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {MatchMapper.class, MatchMapper.class})
public interface ClubWithMatchesMapper {
    Club toEntity(ClubWithMatchesDto clubWithMatchesDto);

    ClubWithMatchesDto toDto(Club club);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Club partialUpdate(ClubWithMatchesDto clubWithMatchesDto, @MappingTarget Club club);

    @AfterMapping
    default void linkHomeMatches(@MappingTarget Club club) {
        club.getHomeMatches().forEach(homeMatch -> homeMatch.setHost(club));
    }

    @AfterMapping
    default void linkVisitorMatches(@MappingTarget Club club) {
        club.getVisitorMatches().forEach(visitorMatch -> visitorMatch.setVisitor(club));
    }
}