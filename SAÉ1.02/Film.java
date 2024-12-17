package SAE;

import java.util.ArrayList;
import java.util.List;

public class Film {

	private String titre;
	private int annee;
	private List<String> genres; // Genre du film en liste (Possibilités qu'il soit plusieurs)
	private int duree;
	private String pays;
	private String langue;
	private String realisateur;
	private String scenariste;
	private List<String> acteurs; // Noms des acteurs en liste (Possibilités qu'il soit plusieurs)
	private int NbVotes; // Nombre des votes.
	private double MoVotes; // Moyenne des votes.
	private String description;

	public Film(String titre, int annee, List<String> genres, int duree, String pays, String langue, String realisateur,
			String scenariste, List<String> acteurs, String description, int nombreVotes, double moyenneVotes) {
		this.titre = titre;
		this.annee = annee;
		this.genres = genres;
		this.duree = duree;
		this.pays = pays;
		this.langue = langue;
		this.realisateur = realisateur;
		this.scenariste = scenariste;
		this.acteurs = acteurs;
		this.description = description;
		this.NbVotes = nombreVotes;
		this.MoVotes = moyenneVotes;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public String getScenariste() {
		return scenariste;
	}

	public void setScenariste(String scenariste) {
		this.scenariste = scenariste;
	}

	public List<String> getActeurs() {
		return acteurs;
	}

	public void setActeurs(List<String> acteurs) {
		this.acteurs = acteurs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNombreVotes() {
		return NbVotes;
	}

	public void setNombreVotes(int nombreVotes) {
		this.NbVotes = nombreVotes;
	}

	public double getMoyenneVotes() {
		return MoVotes;
	}

	public void setMoyenneVotes(double moyenneVotes) {
		this.MoVotes = moyenneVotes;
	}

	@Override
	public String toString() {
		return String.format(
				"Titre: %-80s, Année: %-4d, Genres: %-35s, Durée: %-5d min, Pays: %-30s, Langue: %-30s, Réalisateur: %-30s, Scénariste: %-40s, Acteurs: %-100s, Description: %-500s, Votes: %-10d, Moyenne des votes: %-5.1f",
				titre, annee, genres, duree, pays, langue, realisateur, scenariste, acteurs, description, NbVotes,
				MoVotes);
	}

	public static List<Film> rechercheLineaire(List<Film> films, String titreRecherche) {
		List<Film> resultats = new ArrayList<>(); // On stock nos resultats de film dans une liste
		for (Film film : films) {
			if (film.getTitre().toLowerCase().contains(titreRecherche.toLowerCase())) {
				resultats.add(film); // Pour mettre tout les resultats correspondant
			}
		}
		return resultats;
	}

	public static Film rechercheDichotomiqueParTitre(List<Film> films, String titreRecherche) {
		int debut = 0;
		int fin = films.size() - 1;

		while (debut <= fin) {
			int milieu = (debut + fin) / 2;
			Film filmMilieu = films.get(milieu);
			int comparaison = filmMilieu.getTitre().compareToIgnoreCase(titreRecherche);

			if (comparaison == 0) {
				return filmMilieu;
			} else if (comparaison < 0) {
				debut = milieu + 1;
			} else { 
				fin = milieu - 1;
			}
		}

		return null;
	}
	public static void triSelection(List<Film> films) {
        int n = films.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (films.get(j).getMoyenneVotes() > films.get(maxIndex).getMoyenneVotes()) {
                    maxIndex = j;
                }
            }
            
            Film temp = films.get(i);
            films.set(i, films.get(maxIndex));
            films.set(maxIndex, temp);
        }
    }

    public static void triFusion(List<Film> films, int g, int d) {
        if (g < d) {
            int m = (g + d) / 2;
            triFusion(films, g, m);
            triFusion(films, m + 1, d);
            fusionner(films, g, m, d);
        }
    }

    private static void fusionner(List<Film> films, int debut, int milieu, int fin) {
        int n1 = milieu - debut + 1;
        int n2 = fin - milieu;

        List<Film> temp1 = new ArrayList<>(films.subList(debut, milieu + 1));
        List<Film> temp2 = new ArrayList<>(films.subList(milieu + 1, fin + 1));

        int i = 0, j = 0, k = debut;

        
        while (i < n1 && j < n2) {
            if (temp1.get(i).getMoyenneVotes() >= temp2.get(j).getMoyenneVotes()) {
                films.set(k, temp1.get(i));
                i++;
            } else {
                films.set(k, temp2.get(j));
                j++;
            }
            k++;
        }


        while (i < n1) {
            films.set(k, temp1.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            films.set(k, temp2.get(j));
            j++;
            k++;
        }
    }
}

	

