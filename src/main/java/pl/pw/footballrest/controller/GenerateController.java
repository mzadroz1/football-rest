package pl.pw.footballrest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pw.footballrest.entity.Match;
import pl.pw.footballrest.entity.Player;
import pl.pw.footballrest.entity.PlayerMatch;
import pl.pw.footballrest.entity.PlayerMatchId;
import pl.pw.footballrest.repository.MatchRepository;
import pl.pw.footballrest.repository.PlayerMatchRepository;
import pl.pw.footballrest.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/football")
@RequiredArgsConstructor
public class GenerateController {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final PlayerMatchRepository playerMatchRepository;

    @PostMapping("/generate")
    public List<PlayerMatch> generate() {
        List<Match> matches = matchRepository.findAll();
        List<PlayerMatch> results = new ArrayList<>();
        matches.forEach(match -> {
            List<Player> hostPlayers = playerRepository.findByClub(match.getHost());
            Collections.shuffle(hostPlayers);
            hostPlayers = hostPlayers.subList(0,11);

            List<Player> visitorPlayers = playerRepository.findByClub(match.getVisitor());
            Collections.shuffle(visitorPlayers);
            visitorPlayers = visitorPlayers.subList(0,11);

            List<PlayerMatch> hostPlayerMatches = hostPlayers.stream().map(player -> GenerateController.generatePlayerMatch(player, match))
                    .map(playerMatchRepository::save).toList();
            List<PlayerMatch> visitorPlayerMatches = visitorPlayers.stream().map(player -> GenerateController.generatePlayerMatch(player, match))
                    .map(playerMatchRepository::save).toList();

            results.addAll(hostPlayerMatches);
            results.addAll(visitorPlayerMatches);
        });
        return results;
    }

    private static PlayerMatch generatePlayerMatch(Player player, Match match) {
        PlayerMatch playerMatch = new PlayerMatch();
        PlayerMatchId id = new PlayerMatchId();
        id.setPlayerId(player.getId());
        id.setMatchId(match.getId());
        playerMatch.setId(id);
        playerMatch.setMatch(match);
        playerMatch.setPlayer(player);
        Random random = new Random();
        playerMatch.setShirtNumber(random.nextInt(1,32));
        int goalsProb = random.nextInt(0, 10);
        if(goalsProb < 5) {
            playerMatch.setGoals(0);
        } else if (goalsProb < 8) {
            playerMatch.setGoals(1);
        } else if (goalsProb == 8) {
            playerMatch.setGoals(2);
        } else {
            playerMatch.setGoals(3);
        }
        int yellowProb = random.nextInt(0, 10);
        if(yellowProb < 6) {
            playerMatch.setYellowCards(0);
        } else if(yellowProb < 9) {
            playerMatch.setYellowCards(1);
        } else {
            playerMatch.setYellowCards(2);
        }
        int redProb = random.nextInt(0,10);
        if(redProb < 9) {
            playerMatch.setRedCard(0);
        } else {
            playerMatch.setRedCard(1);
        }
        int startTimeRand = random.nextInt(0,90);
        if(startTimeRand < 75) {
            playerMatch.setStartTime(0);
        } else {
            playerMatch.setStartTime(startTimeRand);
        }
        if(startTimeRand > 75) {
            playerMatch.setEndTime(startTimeRand);
        } else {
            playerMatch.setEndTime(90);
        }
        return playerMatch;
    }
}
