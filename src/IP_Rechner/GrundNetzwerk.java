package IP_Rechner;

import java.util.ArrayList;

public class GrundNetzwerk extends Netzwerk {
	private ArrayList<ArrayList<NetzwerkVerfuegbar>> NetzwerkeVerfuegbar = new ArrayList<ArrayList<NetzwerkVerfuegbar>>();
	// Zeilen = Prefixe, Spalten = Mögliche Subnetze pro Prefix

	public GrundNetzwerk(int okt1, int okt2, int okt3, int okt4, int Prefix) {
		super(okt1, okt2, okt3, okt4, Prefix);
		ArrayList<Thread> Threads = new ArrayList<Thread>(); // ArrayList für die Threads zur Instanziierung der Spalten
		// Instanziiere Die Zeilen (Prefixe) im 2D Array
		for (int i = 0; i < 30 - Prefix; ++i) {
			NetzwerkeVerfuegbar.add(new ArrayList<NetzwerkVerfuegbar>());
		}

		// Instanziierung Spalten (Subnetze), ein Thread pro Spalte
		for (int i = 0; i < 30 - Prefix; ++i) {
			final int IndexThreadsPrefix = i;// Damit ein Thread weiß, auf welchen Index ("Prefix") er in der ArrayList
												// zugreifen muss
			Threads.add(new Thread(new Runnable() { // Erstelle einen neuen Thread
				int index = IndexThreadsPrefix;

				@Override
				public void run() {
					long neuesNetzwerkAddr = getNetzwerkAddr();
					// Solange die Netzwerkadresse eines Subnetzes kleiner als die Broadcastadresse
					// des Grundnetzwerks ist
					while (neuesNetzwerkAddr < getBroadcast()) {
						NetzwerkeVerfuegbar.get(index)
								.add(new NetzwerkVerfuegbar(neuesNetzwerkAddr, Prefix + index + 1));
						neuesNetzwerkAddr += pot(2, 32 - (Prefix + index + 1));
					}
				}
			}));
			Threads.get(i).start(); // Starte den Thread
		}

		// Erst weitermachen, wenn alle Threads fertig sind
		for (int i = 0; i < 30 - Prefix; ++i) {
			try {
				Threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Threads.clear();
		for (int i = 0; i < NetzwerkeVerfuegbar.get(NetzwerkeVerfuegbar.size() - 1).size(); ++i) {
		}
	}

	// Zähle die noch verfügbaren Subnetze
	public int ZaehleVerfuegbareSubnetze(int IndexPrefix) {
		int Zaehler = 0;
		for (int i = 0; i < NetzwerkeVerfuegbar.get(IndexPrefix).size(); ++i) {
			if (NetzwerkeVerfuegbar.get(IndexPrefix).get(i).getVerfuegbar() == true) {
				++Zaehler;
			}
		}
		return Zaehler;
	}

	// Zähle die durch den Benutzer ausgewählten Subnetze
	public int ZaehleAusgewaehlteSubnetze(int IndexPrefix) {
		int Zaehler = 0;
		for (int i = 0; i < NetzwerkeVerfuegbar.get(IndexPrefix).size(); ++i) {
			if (NetzwerkeVerfuegbar.get(IndexPrefix).get(i).getManuell() == true) {
				++Zaehler;
			}
		}
		return Zaehler;
	}

	// Findet das erste freie Subnetz zu einem Prefix
	public int FindeFreiesNetzwerk(int IndexPrefix) {
		int i = 0;
		while (i < NetzwerkeVerfuegbar.get(IndexPrefix).size()
				&& !NetzwerkeVerfuegbar.get(IndexPrefix).get(i).getVerfuegbar()) {
			++i;
		}
		return i;
	}

	// Findet das letzte manuell belegte Subnetz zu einem Prefix
	public int FindeLetzesBelegtesNetzwerk(int IndexPrefix) {
		int i = NetzwerkeVerfuegbar.get(IndexPrefix).size() - 1;
		while (i >= 0 && !NetzwerkeVerfuegbar.get(IndexPrefix).get(i).getManuell()) {
			--i;
		}
		return i;
	}

	// Aktualisiert die Verfügbarkeit der Subnetze, nachdem ein Subnetz zur Auswahl
	// hinzugefügt wurde
	public void AktualisiereVerfuegbarkeitHinz(int IndexPrefixBasis, int IndexPosBasis) {
		/*
		 * ArrayList für die Threads zur Aktualisierung der Verfügbarkeiten Vorwärts:
		 * Ein Thread pro Prefix Rückwärts: Ein Thread für alle Prefixe (es muss ja nur
		 * ein Subnetz pro Prefix aktualisiert werden)
		 */
		ArrayList<Thread> Threads = new ArrayList<Thread>();
		// Im Prinzip von IndexPrefix +1 bis zum letzten Prefix
		// In dieser Form besser für Berechnungen
		for (int i = 0; i < 30 - getPrefix() - IndexPrefixBasis - 1; ++i) {

			// Damit ein Thread weiß, auf welchen Index ("Prefix") er in der ArrayList
			// zugreifen muss
			final int IndexThread = i;
			// neuer Thread zur Aktualisierung eines nachfolgenden Prefixes
			Threads.add(new Thread(new Runnable() {
				int index = IndexThread; // Index zur Berechnung von IndexPrefix und den zu aktualisierenden Prefix
				int IndexPrefix = IndexPrefixBasis; // Kopie zur Sicherheit

				// StartIndex im zu aktualisierenden Prefix (damit ich nicht durchs ganze Array
				// muss)
				int IndexStart = IndexPosBasis * potint(2, index + 1);

				@Override
				public void run() {
					// Aktualisiere die von der Auswahl betroffenen Subnetze im Prefix
					for (int i = 0; i < pot(2, index + 1); ++i) {
						NetzwerkeVerfuegbar.get(IndexPrefix + index + 1).get(IndexStart + i).setVerfuegbar(false);
					}
				}
			}));
			Threads.get(Threads.size() - 1).start(); // starte Thread
		}

		// Thread zum Aktualisieren der vorherigen Prefixe
		Threads.add(new Thread(new Runnable() {
			// Kopien zur Sicherheit
			int IndexPrefix = IndexPrefixBasis;
			int IndexPos = IndexPosBasis;

			@Override
			public void run() {
				// Aktalisiere das betroffene Subnetz im Prefix
				for (int i = 0; i < IndexPrefix; ++i) {
					NetzwerkeVerfuegbar.get(IndexPrefix - i - 1).get(IndexPos / potint(2, i + 1)).setVerfuegbar(false);

				}

			}
		}));
		Threads.get(Threads.size() - 1).start();// starte Thread

		// Erst weitermachen, wenn alle Threads fertig sind
		for (int i = 0; i < Threads.size(); ++i) {
			try {
				Threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Beleget ein Subnetz im übergebenen Prefix
	public void BelegeSubnetz(int IndexPrefix) {
		int pos = FindeFreiesNetzwerk(IndexPrefix);
		NetzwerkeVerfuegbar.get(IndexPrefix).get(pos).setManuell(true);
		NetzwerkeVerfuegbar.get(IndexPrefix).get(pos).setVerfuegbar(false);
		AktualisiereVerfuegbarkeitHinz(IndexPrefix, pos);
	}

	// Funktion für Potenzrechnung mit Integern
	private int potint(int Basis, int Exponent) {
		int Zahl = Basis;
		if (Exponent == 0) {
			Zahl = 1;
		} else {
			for (int i = 1; i < Exponent; ++i) {
				Zahl *= Basis;
			}
		}
		return Zahl;
	}

}
