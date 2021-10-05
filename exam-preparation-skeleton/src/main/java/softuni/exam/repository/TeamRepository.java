package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}
