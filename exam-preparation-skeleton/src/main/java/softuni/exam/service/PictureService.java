package softuni.exam.service;

import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface PictureService {
    String importPictures() throws JAXBException;
    boolean areImported();
    String readPicturesXmlFile() throws IOException;
}
