/**
 * 
 */
package dev.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author boukh
 *
 */
@Entity
@Table(name="collegue")
public class Collegue {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	long id;

	@Column(name="matricule")
    private String matricule;
	@Column(name="nom")
    private String nom;
	@Column(name="prenoms")
    private String prenoms;
	@Column(name="email")
    private String email;
	@Column(name="date_de_naissance")
    private LocalDate DateDeNaissance;
	@Column(name="photo_url")
    private String photoUrl;


	public Collegue() {

	}


	public Collegue(String matricule, String nom, String prenoms, String email, LocalDate dateDeNaissance, String photoUrl) {

		this.matricule = matricule;
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		DateDeNaissance = dateDeNaissance;
		this.photoUrl = photoUrl;
	}
	
	public Collegue(String nom, String prenoms, String email, LocalDate dateDeNaissance, String photoUrl) {


		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		DateDeNaissance = dateDeNaissance;
		this.photoUrl = photoUrl;
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenoms() {
		return prenoms;
	}


	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getDateDeNaissance() {
		return DateDeNaissance;
	}


	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		DateDeNaissance = dateDeNaissance;
	}


	public String getPhotoUrl() {
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}


	@Override
	public String toString() {
		return "Collegue [matricule=" + matricule + ", nom=" + nom + ", prenoms=" + prenoms + ", email=" + email
				+ ", DateDeNaissance=" + DateDeNaissance + ", photoUrl=" + photoUrl + "]";
	}

}
