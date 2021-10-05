package softuni.exam.service.impl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import softuni.exam.models.Picture;
import softuni.exam.models.PictureDto;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

    private final String PICTURE_PATH = "/Volumes/Toshiba-4TB/My Trainings/Softuni/Football_Information/exam-preparation-skeleton/src/main/resources/files/xml/pictures.xml";

    private final String INVALID = "Invalid Picture\n";

    File file = new File(PICTURE_PATH);

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public String importPictures() throws JAXBException {

        StringBuilder sb_Invalid = new StringBuilder();
        StringBuilder sb_Success = new StringBuilder();

        JAXBContext context = JAXBContext.newInstance(PictureDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        PictureDto pictureDtos = (PictureDto) unmarshaller.unmarshal(file);

        // System.out.println(pictureDtos.getPictures().get(0));

        for(Picture picture : pictureDtos.getPictures()) {
            // System.out.println((picture.getUrl()));

            if (picture.getUrl() == null) {
                sb_Invalid.append(INVALID);
            }
            else {
                pictureRepository.save(picture);
                sb_Success.append("Successfully imported picture - " + picture.getUrl() + "\n");
            }
            
        }

       return sb_Invalid.toString() + sb_Success.toString();
    }

    @Override
    public boolean areImported() {
        return true;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return Files.readString(Path.of(PICTURE_PATH));
    }
}
