package softuni.exam.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.exam.models.Picture;
import softuni.exam.models.Player;
import softuni.exam.models.PlayerDto;
import softuni.exam.models.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.PlayerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.google.gson.Gson;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final String Player_PATH = "/Volumes/Toshiba-4TB/My Trainings/Softuni/Football_Information/exam-preparation-skeleton/src/main/resources/files/json/players.json";

    private final String INVALID = "Invalid Player\n";

    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PictureRepository pictureRepository;

    public PlayerServiceImpl(Gson gson, ModelMapper modelMapper) {
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importPlayers() throws IOException{

        StringBuilder sb_Invalid = new StringBuilder();
        StringBuilder sb_Success = new StringBuilder();

        String inputString = this.readPlayersJsonFile();

        PlayerDto[] playerDtos = this.gson.fromJson(inputString, PlayerDto[].class);

        System.out.println("PlayerDtos : " + playerDtos);

        //get all picture records from picture repository
        List<Picture> tempPictures = pictureRepository.findAll();
        
        //get all team records from team repository
        List<Team> tempTeams = teamRepository.findAll();

        for (PlayerDto playerDto : playerDtos) {
            Player player = this.modelMapper.map(playerDto, Player.class);

            System.out.println(player);

            if (player.getPosition().equals("Invalid")) {
                sb_Invalid.append(INVALID);
            }
            else {
                //get picture id (only one) for realPlayer from picture repository

                List<Picture> pictures = tempPictures.stream()
                    .filter(p -> p.getUrl().equals(player.getPicture().getUrl()))
                    .collect(Collectors.toList());

                List<Team> teams = tempTeams.stream()
                    .filter(t -> t.getName().equals(player.getTeam().getName()))
                    .collect(Collectors.toList());

                    System.out.printf("Player : %s, Picture : %s, Team : %s\n", player.getFirstName(), pictures.get(0).getUrl(), teams.get(0).getName());

                // //set picture and team to player
                player.setPicture(pictures.get(0));
                player.setTeam(teams.get(0));

                // System.out.println("realPlayer : " + realPlayer);

                // save to DB
                playerRepository.save(player);

                sb_Success.append("Successfully imported player - " + player.getFirstName() + " " + player.getLastName() + "\n");
            }
        }
        
       return sb_Invalid.toString() + sb_Success.toString();
    }

    @Override
    public boolean areImported() {
        return true;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {

        return Files.readString(Path.of(Player_PATH));
    }

    @Override
    public String exportPlayersWithALargerSalaryThanTheGiven() {

        StringBuilder sb = new StringBuilder();

        List<Player> players = playerRepository.findAllPlayerWithSalaryGreaterThan100K();

        for (Player player : players) {
            sb.append("Player name: " + player.getFirstName() + " " + player.getLastName() + "\n");
            sb.append("Number: " + player.getNumber() + "\n");
            sb.append("Salary: " + player.getSalary() + "\n");
            sb.append("Team: " + player.getTeam().getName() + "\n");
        }

       return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {

        StringBuilder sb = new StringBuilder();

        List<Player> players = playerRepository.findAllPlayerInNorthHub();

        // System.out.println(players.get(0).getFirstName());

        for (Player player : players) {
            sb.append("Player name: " + player.getFirstName() + " " + player.getLastName() + " - " + player.getPosition() + "\n");
            sb.append("Number: " + player.getNumber() + "\n");
        }

        return sb.toString();
    }
}
