package si.matjazcerkvenik.openmp3player.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistsRepository extends JpaRepository<Playlist, Integer> {

}
