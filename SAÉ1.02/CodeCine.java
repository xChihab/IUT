package SAE;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CodeCine {

	public static void main(String[] args) {
		List<Film> films = null;
		boolean quitter = false;
		String oui;

		System.out.println("                     Y8888b,\n" + "                  ,oA8888888b,\n"
				+ "            ,aaad8888888888888888bo,\n" + "         ,d888888888888888888888888888b,\n"
				+ "       ,888888888888888888888888888888888b,\n" + "      d8888888888888888888888888888888888888,\n"
				+ "     d888888888888888888888888888888888888888b\n"
				+ "    d888888P'                    `Y888888888888,\n"
				+ "    88888P'                    Ybaaaa8888888888l\n"
				+ "   a8888'                      `Y8888P' `V888888\n"
				+ " d8888888a                                `Y8888\n"
				+ "AY/'' `\\Y8b                                 ``Y8b\n"
				+ "Y'      `YP                                    ~~\n" + "");
		System.out.println("      ____ ___ _   _ _____ __  __    _    \n"
				+ "     / ___|_ _| \\ | | ____|  \\/  |  / \\   \n" + "    | |    | ||  \\| |  _| | |\\/| | / _ \\  \n"
				+ "    | |___ | || |\\  | |___| |  | |/ ___ \\ \n" + "     \\____|___|_| \\_|_____|_|  |_/_/   \\_\\");
		while (!quitter) {
			long start, stop;
			start = 0;

			System.out.println("\n=== MENU PRINCIPAL ===");
			System.out.println("1. Charger les données");
			System.out.println("2. Afficher tous les films");
			System.out.println("3. Trier les films");
			System.out.println("4. Rechercher un film");
			System.out.println("5. Filtrer les films");
			System.out.println("6. Quitter");
			System.out.print("Votre choix : ");

			int choix = Clavier.lireInt();
			int choix2;
			String cheminFichier;
			switch (choix) {
			case 1:
				start = 0;
				stop =0;
				System.out.println("Choississez une option oû charger les données");
				System.out.println("1. ArrayList");
				System.out.println("2. LinkedList");
				int choixstock = Clavier.lireInt();
				System.out.println("Choisissez une option :");
				System.out.println("1. IMDbmoviesCUT100.tsv");
				System.out.println("2. IMDbmoviesCUT1000.tsv");
				System.out.println("3. IMDbmoviesCUT10000.tsv");
				System.out.println("4. IMDbmoviesCUT40000.tsv");
				System.out.println("5. IMDbmoviesFULL.tsv");
				System.out.println("6. Chemin personnalisé");
				System.out.print("Votre choix : ");
				choix2 = Clavier.lireInt();

				switch (choix2) {
				case 1:
					cheminFichier = "IMDbmoviesCUT100.tsv";
					break;
				case 2:
					cheminFichier = "IMDbmoviesCUT1000.tsv";
					break;
				case 3:
					cheminFichier = "IMDbmoviesCUT10000.tsv";
					break;
				case 4:
					cheminFichier = "IMDbmoviesCUT40000.tsv";
					break;
				case 5:
					cheminFichier = "IMDbmoviesFULL.tsv";
					break;
				default:
					cheminFichier = Clavier.lireLigne();
				}
				start = System.nanoTime();
				films = ChargeurFilm.chargerFilmsDepuisTSV(cheminFichier, choixstock);
				stop = System.nanoTime();
				if (films != null && !films.isEmpty()) {
					System.out.println("Données chargées avec succès !");
					System.out.println("Nombre de films chargés : " + films.size());
					System.out.println("temps de chargements : " + (float) (stop - start) / 1000000 + " ms\n");
				} else {
					System.out.println("Erreur lors du chargement des données.");
				}
				break;

			case 2:
				start = 0;
				stop = 0;
				if (films == null || films.isEmpty()) {
					System.out.println("Aucun film n'a été chargé.");
				} else {
					System.out.println("=== Liste des films ===");
					start = System.nanoTime();
					films.stream().limit(1000000).forEach(System.out::println);
					stop = System.nanoTime();
					System.out.println("temps de chargements : " + (float) (stop - start) / 1000000 + " ms\n");
				}
				break;
				
			case 3:
				start = 0;
				stop =0;
				if (films == null || films.isEmpty()) {
					System.out.println("Aucun film n'a été chargé.");
				} else {
					System.out.println("=== FILTRAGE DES FILMS ===");
					System.out.println("1. Filtrer par Titre");
					System.out.println("2. Filtrer par Année");
					System.out.println("3. Filtrer par Pays");
					System.out.println("4. Filtrer par Genre");
					System.out.println("5. Filtrer par Réalisateur");
					System.out.println("6. Filtrer par Note (Tri Sélection)");
					System.out.println("7. Filtrer par Note (Tri Fusion)");
					System.out.print("Votre choix : ");

					int filtreChoix = Clavier.lireInt();

					switch (filtreChoix) {
					case 1:
						System.out.print("Entrez un mot-clé pour le titre : ");
						String motCleTitre = Clavier.lireLigne();
						films.stream().filter(f -> f.getTitre().toLowerCase().contains(motCleTitre.toLowerCase()))
								.forEach(System.out::println);
						break;

					case 2:
						System.out.print("Entrez l'année : ");
						int annee = Clavier.lireInt();
						films.stream().filter(f -> f.getAnnee() == annee).forEach(System.out::println);
						break;

					case 3:
						System.out.print("Entrez le pays : ");
						String pays = Clavier.lireLigne();
						films.stream().filter(f -> f.getPays().equalsIgnoreCase(pays)).forEach(System.out::println);
						break;

					case 4:
						System.out.print("Entrez le genre : ");
						String genre = Clavier.lireLigne();
						films.stream().filter(f -> f.getGenres().stream().anyMatch(g -> g.equalsIgnoreCase(genre)))
								.forEach(System.out::println);
						break;

					case 5:
						System.out.print("Entrez le nom du réalisateur : ");
						String realisateur = Clavier.lireLigne();
						films.stream().filter(f -> f.getRealisateur().equalsIgnoreCase(realisateur))
								.forEach(System.out::println);
						break;
					case 6:
						start = System.nanoTime();
						Film.triSelection(films);
						stop = System.nanoTime();
						System.out.println("Films triés par note.");
						System.out.println("temps de chargements : " + (float) (stop - start) / 1000000 + " ms\n");
						System.out.println("Afficher le resultat ? Tappez oui ou non");
						oui = Clavier.lireString();
					    if(oui == "oui") {
					    	System.out.println("=== Liste des films triés par note ===");
						    films.forEach(System.out::println);
					    }
						break;
					case 7:
						start = System.nanoTime();
						Film.triFusion(films, 0, films.size() - 1);
						stop = System.nanoTime();
						System.out.println("Films triés par note ");
						System.out.println("temps de chargements : " + (float) (stop - start) / 1000000 + " ms\n");
					    System.out.println("Afficher le resultat ? Tappez oui ou non");
					    oui = Clavier.lireString();
					    if(oui == "oui") {
					    	System.out.println("=== Liste des films triés par note ===");
						    films.forEach(System.out::println);
					    }
						break;

					default:
						System.out.println("Option invalide.");
					}
				}
				break;

			case 4:
				start = 0;
				stop =0;
				int choix3;
				System.out.println("1. Recherche Linéaire");
				System.out.println("2. Recherche Dichotomique");
				choix3 = Clavier.lireInt();
				switch (choix3) {
				case 1:
					if (films == null || films.isEmpty()) {
						System.out.println("Aucun film n'a été chargé.");
					} else {
						System.out.print("Entrez un mot-clé pour rechercher un film : ");
						String titreRecherche = Clavier.lireLigne();

						// Appeler la methode
						start = System.nanoTime();
						List<Film> filmsTrouves = Film.rechercheLineaire(films, titreRecherche);
						stop = System.nanoTime();
						

						if (!filmsTrouves.isEmpty()) {
							System.out.println("Films trouvés :");
							filmsTrouves.forEach(System.out::println);
							System.out.println("temps de chargements : " + (float) (stop - start) / 1000000 + " ms\n");
						} else {
							System.out.println("Aucun film ne correspond à votre recherche.");
						}
					}
					break;
				case 2:
					start=0;
					stop=0;
					if (films == null || films.isEmpty()) {
						System.out.println("Aucun film n'a été chargé.");
					} else {
						System.out.print("Entrez le titre exact du film à rechercher : ");
						String titreRecherche = Clavier.lireLigne();

						films.sort((f1, f2) -> f1.getTitre().compareToIgnoreCase(f2.getTitre()));
						start=System.nanoTime();
						Film filmTrouve = Film.rechercheDichotomiqueParTitre(films, titreRecherche);
						stop=System.nanoTime();
						System.out.println("temps de chargements : " + (float) (stop - start) / 1000000 + " ms\n");

						if (filmTrouve != null) {
							System.out.println("Film trouvé :");
							System.out.println(filmTrouve);
						} else {
							System.out.println("Film non trouvé.");
						}
					}
					break;
				default:
					System.out.println("Commande non reconnu");
				}
				break;
			case 5:
				start = 0;
				stop =0;
				if (films == null || films.isEmpty()) {
					System.out.println("Aucun film n'a été chargé.");
				} else {
					System.out.print("Entrez l'année pour filtrer les films : ");
					int anneeFiltre = Clavier.lireInt();
					films.stream().filter(f -> f.getAnnee() == anneeFiltre).forEach(System.out::println);
				}
				break;

			case 6:
				start = 0;
				stop =0;
				quitter = true;
				System.out.println("I have to return some videotapes");
				break;

			default:
				System.out.println("Option invalide. Veuillez réessayer.");

			}
		}

	}
}
