/**
 * 
 */
package dev.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.Entity.Collegue;
import dev.Entity.MessageErreur;
import dev.Entity.Repository.CollegueRepository;
import net.minidev.json.JSONObject;

/**
 * @author boukh
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/collegues")
public class CollegueController {
	
	private CollegueRepository collegueRepository;
	
	public CollegueController(CollegueRepository collegueRepository) {
		super();
		this.collegueRepository = collegueRepository;
	}
	
	@GetMapping(params = "nom")
	public List<String> getColleguetNom(@RequestParam String nom) {
		
		List<Collegue> collegues = collegueRepository.findByNom(nom);
		
	    List<String> listMat = new ArrayList<String>();
		
		for (Collegue c:collegues){
			listMat.add(c.getMatricule()) ;
		}
		return listMat;
	}
	
	@GetMapping("{matricule}")
	public Collegue getCollegueMat(@PathVariable String matricule) throws CollegueNotFoundException {
		
		Optional<Collegue> optionalCol = collegueRepository.findByMatricule(matricule);
	return optionalCol.orElseThrow(() -> new CollegueNotFoundException("Collegue non trouvé"));
		
	}
	
	@PostMapping
	public ResponseEntity<?> postCollegue(@Valid @RequestBody Collegue collegue) {
		
        collegue.setMatricule(UUID.randomUUID().toString());
		
			collegueRepository.save(collegue);
			return ResponseEntity.status(200).body(collegue);

	}
	
	@GetMapping
	public List<Collegue> getCollegue() {
		
		List<Collegue> collegues = collegueRepository.findAll();
		
		return collegues;
	}
	
    @PatchMapping("{matricule}")
    public ResponseEntity<Object> updateCollegue(@RequestBody @Valid Collegue collegue, @PathVariable String matricule) {
		Optional<Collegue> optionalCol = collegueRepository.findByMatricule(matricule);
        if (optionalCol.isPresent()) {
            Collegue col = optionalCol.get();
                col.setPhotoUrl(collegue.getPhotoUrl());
                col.setEmail(collegue.getEmail());
            collegueRepository.save(col);
            return ResponseEntity.status(200).body(collegue);
        } else {
            return ResponseEntity.status(404).body(new MessageErreur("Erreur : le collegueOpt a mettre a jour n'a pas ete trouve"));
        }
    }
	
    @RequestMapping("/photos")
	public ResponseEntity<Object> getPhotoCol() {
        List<JSONObject> photos = new ArrayList<JSONObject>();
		
		List<Collegue> collegues = collegueRepository.findAll();
		
		for(Collegue c:collegues) {
			
	          JSONObject photo = new JSONObject();

	          photo.put("matricule", c.getMatricule());
	          photo.put("photoUrl", c.getPhotoUrl());
	          
	          photos.add(photo);
		}
		
		return new ResponseEntity<Object>(photos, HttpStatus.OK);
	}
    
    @DeleteMapping("{matricule}")  
    public void deleteCollegue(@PathVariable String matricule,Collegue collegue) {  
		Optional<Collegue> optionalCol = collegueRepository.findByMatricule(matricule);
		  Collegue col = optionalCol.get();
        collegueRepository.delete(col);
    }  
	
}
