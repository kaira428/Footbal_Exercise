package softuni.exam.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface TeamService {

    String importTeams() throws JAXBException;

    boolean areImported();

    String readTeamsXmlFile() throws IOException;

}
