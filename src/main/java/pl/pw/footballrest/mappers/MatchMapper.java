package pl.pw.footballrest.mappers;

import org.mapstruct.*;
import pl.pw.footballrest.dto.MatchDto;
import pl.pw.footballrest.entity.Match;
import pl.pw.footballrest.entity.PlayerMatch;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClubMapper.class})
public interface MatchMapper {
    Match toEntity(MatchDto matchDto);

//    @Mapping(source = "match", target = "hostGoals", qualifiedByName = "hostGoals")
//    @Mapping(source = "match", target = "visitorGoals", qualifiedByName = "visitorGoals")
    MatchDto toDto(Match match);

//    @Named("hostGoals")
//    default Integer hostGoals(Match match) {
//        return match.getPlayerMatches().stream()
//                .filter(playerMatch -> playerMatch.getPlayer().getClub().equals(match.getHost()))
//                .mapToInt(PlayerMatch::getGoals).sum();
//    }
//
//    @Named("visitorGoals")
//    default Integer visitorGoals(Match match) {
//        return match.getPlayerMatches().stream()
//                .filter(playerMatch -> playerMatch.getPlayer().getClub().equals(match.getVisitor()))
//                .mapToInt(PlayerMatch::getGoals).sum();
//    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Match partialUpdate(MatchDto matchDto, @MappingTarget Match match);
}