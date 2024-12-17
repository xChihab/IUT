package SAE;

import java.io.*;
import java.util.*;

public class ChargeurFilm {
	public static List<Film> chargerFilmsDepuisTSV(String fichier,int choixstock) {
		List<Film> films;
		// ca marche comme ca sinon avec un if (...){List<Film> films = new LinkedList<>(); } marche pas. Pareil pour arraylist
		if ( choixstock == 2) {
			films = new LinkedList<>();
		}
		
		else {
			films = new ArrayList<>();
		}
		try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
			String ligne;
			br.readLine();
			while ((ligne = br.readLine()) != null) {
				String[] data = ligne.split("\t");
				String titre = data[1];
				int annee = Integer.parseInt(data[3]);
				List<String> genres = Arrays.asList(data[5].split(","));
				int duree = Integer.parseInt(data[6]);
				String pays = data[7];
				String langue = data[8];
				String realisateur = data[9];
				String scenariste = data[10];
				List<String> acteurs = Arrays.asList(data[12].split(","));
				String description = data[13];
				int nombreVotes = Integer.parseInt(data[15]);
				double moyenneVotes = Double.parseDouble(data[14]);
				Film film = new Film(titre, annee, genres, duree, pays, langue, realisateur, scenariste, acteurs,
						description, nombreVotes, moyenneVotes);
				films.add(film);
				// Chihab
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
		}
		return films;
	}
}
