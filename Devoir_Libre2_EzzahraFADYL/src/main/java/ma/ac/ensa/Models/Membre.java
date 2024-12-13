package ma.ac.ensa.Models;

import java.util.Objects;

public class Membre {
    int identifiant;
    String nom;
    String prenom;
    String email;
    int phone;
    public Membre(int identifaint, String nom, String prenom, String email, int phone){
        this.identifiant=identifaint;
        this.nom= nom;
        this.prenom=prenom;
        this.email= email;
        this.phone=phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Membre membre = (Membre) o;
        return identifiant == membre.identifiant;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifiant);
    }

    @Override
    public String toString() {
        return "Membre{" +
                "email='" + email + '\'' +
                ", identifiant=" + identifiant +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", phone=" + phone +
                '}';
    }
}
