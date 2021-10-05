package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.exam.models.Picture;
import softuni.exam.models.Team;
import softuni.exam.models.TeamDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.service.TeamService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final String TEAM_PATH = "/Volumes/Toshiba-4TB/My Trainings/Softuni/Football_Information/exam-preparation-skeleton/src/main/resources/files/xml/teams.xml";

    private final String INVALID = "Invalid Picture\n";

    File file = new File(TEAM_PATH);

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PictureRepository pictureRepository;
 
    @Override
    public String importTeams() throws JAXBException {

        StringBuilder sb_Invalid = new StringBuilder();
        StringBuilder sb_Success = new StringBuilder();

        JAXBContext context = JAXBContext.newInstance(TeamDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TeamDto teamDtos = (TeamDto) unmarshaller.unmarshal(file);

        // System.out.println(teamDtos.getTeams());

        //get all picture records from picture repository
        List<Picture> tempPictures = pictureRepository.findAll();

        // System.out.println(tempPictures);

        for (Team team : teamDtos.getTeams()) {
            // System.out.println(team);
            int requiredPictureIndex = 0;

            if (team.getPicture().getUrl().equals("Invalid") ||
            team.getPicture().getUrl().equals("noPicture") ||
            team.getPicture().getUrl().equals("invalidURL")) {
                sb_Invalid.append(INVALID);
            }
            else {
                //get picture id for realTeam from picture repository
                for (int i=0; i < tempPictures.size(); i++) {
                    if (tempPictures.get(i).getUrl().equals(team.getPicture().getUrl())) {
                        requiredPictureIndex = i;
                    }
                }
                
                // System.out.println(requiredPictureId);

                Team realTeam = new Team();
                realTeam.setName(team.getName());
                realTeam.setPicture(tempPictures.get(requiredPictureIndex));

                System.out.println(realTeam);
                teamRepository.save(realTeam);

                sb_Success.append("Successfully imported team - " + team.getName() + "\n");
            }
            
        }

       return sb_Invalid.toString() + sb_Success.toString();
    }
    
    @Override
    public boolean areImported() {
        return true;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return Files.readString(Path.of(TEAM_PATH));
    }

}
