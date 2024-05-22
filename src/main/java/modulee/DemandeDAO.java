package modulee;


import java.util.List;

public interface DemandeDAO {
    void ajouterDemande(Demande demande);
    Demande obtenirDemandeParId(int id_demande);
    List<Demande> obtenirToutesDemandes();
    void mettreAJourDemande(Demande demande); // Ajoutez cette méthode
    // Ajoutez d'autres méthodes DAO nécessaires ici...
}

