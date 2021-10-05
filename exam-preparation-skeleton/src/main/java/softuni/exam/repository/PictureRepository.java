package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import softuni.exam.models.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long>{

}
