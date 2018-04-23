package si.matjazcerkvenik.openmp3player.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Mp3FilesRepository extends JpaRepository<Mp3File, Integer> {

}
