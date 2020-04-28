/**
 * 
 */
package dev.Entity.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sun.xml.bind.v2.model.core.ID;

import dev.Entity.Collegue;

/**
 * @author boukh
 *
 */
@Repository
public interface CollegueRepository extends JpaRepository<Collegue, ID> {
	
	List<Collegue> findByNom(String nom);
	Optional<Collegue> findByMatricule(String matricule);

}
