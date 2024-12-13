package ma.ac.ensa.DAO;

import ma.ac.ensa.Models.Incident;
import ma.ac.ensa.Models.Membre;

import java.util.List;

public interface MembreDAO {
    void insere(Membre m);
    List<Incident> chargerListIncident();
}
