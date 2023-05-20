package pl.pw.footballrest.service;

import com.cosium.spring.data.jpa.entity.graph.domain2.DynamicEntityGraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pw.footballrest.dto.ClubDto;
import pl.pw.footballrest.dto.ClubWithMatchesDto;
import pl.pw.footballrest.dto.ClubWithMatchesMapper;
import pl.pw.footballrest.mappers.ClubMapper;
import pl.pw.footballrest.repository.ClubRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubMapper clubMapper;
    private final ClubRepository clubRepository;
    private final ClubWithMatchesMapper clubWithMatchesMapper;

    public List<ClubDto> getAllClubs() {
        return StreamSupport.stream(clubRepository.findAll().spliterator(), false)
                .map(clubMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ClubWithMatchesDto> getAllClubsWithMatches() {
        return StreamSupport.stream(clubRepository.findAll(DynamicEntityGraph.loading()
                        .addPath("visitorMatches")
                        .addPath("homeMatches")
                        .build()).spliterator(), false)
                .map(clubWithMatchesMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClubDto getClub(Long clubId) {
        return clubRepository.findById(clubId)
                .map(clubMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("No Club with specified id=" + clubId + " found"));
    }
}
