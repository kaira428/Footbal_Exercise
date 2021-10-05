package softuni.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import softuni.exam.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query(value="Select * From players p Join teams t on t.id = p.team_id where p.salary > 100000 order by p.salary desc", nativeQuery = true)
    public List<Player> findAllPlayerWithSalaryGreaterThan100K();

    // @Query(value="Select * From players p Join teams t on t.id = p.team_id where t.name = 'North Hub' order by p.id", nativeQuery = true)
    // public List<Player> findAllPlayerInNorthHub();

    @Query(value= "select b, a from Team a Join a.players b where a.name = 'North Hub' order by b.id")
    public List<Player> findAllPlayerInNorthHub();
}
