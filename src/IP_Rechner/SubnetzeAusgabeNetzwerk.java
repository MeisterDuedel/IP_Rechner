package IP_Rechner;
/* Klasse zur Berechnung der (bestm�glichen) Belegung der Subnetze und zur Vorbereitung der Ausgabe (VLSM)*/
import java.util.ArrayList;

public class SubnetzeAusgabeNetzwerk extends Netzwerk {
	// Zeilen = Prefixe, Spalten = M�gliche Subnetze pro Prefix
	private ArrayList<ArrayList<NetzwerkVerfuegbar>> NetzwerkeVerfuegbar = new ArrayList<ArrayList<NetzwerkVerfuegbar>>();

	// 2 Dimensional, damit ich mit Threads arbeiten kann
	// Zeilen = Prefixe, Spalten = M�gliche Subnetze pro Prefix
	private ArrayList<ArrayList<Netzwerk>> AusgewaehlteSubnetze = new ArrayList<ArrayList<Netzwerk>>();

	private ArrayList<Netzwerk> FreieSubnetze = new ArrayList<Netzwerk>();

	public SubnetzeAusgabeNetzwerk(int okt1, int okt2, int okt3, int okt4, int Prefix) {
		super(okt1, okt2, okt3, okt4, Prefix);
		// Instanziiere die Zeilen (Prefixe) im 2D Array
		for (int i = 0; i < 30 - Prefix; ++i) {
			NetzwerkeVerfuegbar.add(new ArrayList<NetzwerkVerfuegbar>());
			AusgewaehlteSubnetze.add(new ArrayList<Netzwerk>());
		}
		// ArrayList f�r die Threads zur Instanziierung der Spalten
		ArrayList<Thread> Threads = new ArrayList<Thread>();
		// Instanziierung Spalten (Subnetze), ein Thread pro Prefix
		for (int i = 0; i < 30 - Prefix; ++i) {
			// Damit ein Thread wei�, auf welchen Index ("Prefix") er in der ArrayList
			// zugreifen muss
			final int IndexThreadsPrefix = i;
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

	// Findet das erste freie Subnetz zu einem Prefix
	private int FindeFreiesNetzwerk(int IndexPrefix) {
		int i = 0;
		while (i < NetzwerkeVerfuegbar.get(IndexPrefix).size()
				&& !NetzwerkeVerfuegbar.get(IndexPrefix).get(i).getVerfuegbar()) {
			++i;
		}
		return i;
	}

	// Aktualisiert die Verf�gbarkeit der Subnetze, nachdem ein Subnetz belegt wurde
	private void AktualisiereVerfuegbarkeitHinz(int IndexPrefixBasis, int IndexPosBasis) {
		/*
		 * ArrayList f�r die Threads zur Aktualisierung der Verf�gbarkeiten. Vorw�rts:
		 * Ein Thread pro Prefix; R�ckw�rts: Ein Thread f�r alle Prefixe (R�ckw�rts muss
		 * nur ein Subnetz pro Prefix aktualisiert werden)
		 */
		ArrayList<Thread> Threads = new ArrayList<Thread>();
		// Im Prinzip von IndexPrefix +1 bis zum letzten Prefix
		// In dieser Form besser f�r Berechnungen
		for (int i = 0; i < 30 - getPrefix() - IndexPrefixBasis - 1; ++i) {

			// Damit ein Thread wei�, auf welchen Index ("Prefix") er in der ArrayList
			// zugreifen muss
			final int IndexThread = i;
			// neuer Thread zur Aktualisierung eines nachfolgenden Prefixes
			Threads.add(new Thread(new Runnable() {
				int index = IndexThread; // Index zur Berechnung von IndexStart und den zu aktualisierenden Prefix
				int IndexPrefix = IndexPrefixBasis; // Kopie zur Sicherheit

				// StartIndex im zu aktualisierenden Prefix (damit ich nicht durchs ganze Array
				// muss)
				int IndexStart = IndexPosBasis * potint(2, index + 1);

				@Override
				public void run() {
					// Aktualisiere die von der Belegung betroffenen Subnetze im Prefix
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
		Threads.clear();
	}

	// Beleget ein Subnetz im �bergebenen Prefix
	public void BelegeSubnetz(int IndexPrefix) {
		int pos = FindeFreiesNetzwerk(IndexPrefix);
		NetzwerkeVerfuegbar.get(IndexPrefix).get(pos).setManuell(true);
		NetzwerkeVerfuegbar.get(IndexPrefix).get(pos).setVerfuegbar(false);
		AktualisiereVerfuegbarkeitHinz(IndexPrefix, pos);
	}

	// Belegt das Uplink-Netzwerk
	public void BelegeUplinkSubnetz(long NetzwerkADDR, int IndexPrefix) {
		int pos = 0;
		// Finde das Netzwerk im Prefix
		while (pos < NetzwerkeVerfuegbar.get(IndexPrefix).size()
				&& NetzwerkeVerfuegbar.get(IndexPrefix).get(pos).getNetzwerkAddr() != NetzwerkADDR) {
			++pos;
		}
		NetzwerkeVerfuegbar.get(IndexPrefix).get(pos).setUplink(true);
		NetzwerkeVerfuegbar.get(IndexPrefix).get(pos).setVerfuegbar(false);
		AktualisiereVerfuegbarkeitHinz(IndexPrefix, pos);
	}

	// Berechnet die Belegung der ausgew�hlten und noch freien Subnetze
	public void AuswahlFertig(int[] parNetzwerkeAusgewaehlt, int[] parNetzwerkeVerfuegbar) {
		// L�sche alle Subnetze aus den Ausgabearrays (wenn welche von einer vorherigen
		// Ausgabe noch drin sind)
		for (int i = 0; i < AusgewaehlteSubnetze.size(); ++i) {
			AusgewaehlteSubnetze.get(i).clear();
		}
		FreieSubnetze.clear();

		/*
		 * Klone die Arrays, in denen die Anzahl der Vef�gbaren / Ausgew�hlten Subnetze
		 * pro Prefix gespeichert werden, um diese Ver�ndern zu k�nnen, ohne dass das
		 * Original beeinflusst wird
		 */
		int[] NetzwerkeAusgewaehlt = parNetzwerkeAusgewaehlt.clone();
		int[] NetzwerkeUebrig = parNetzwerkeVerfuegbar.clone();

		// Belegung �bertragen
		for (int i = 0; i < NetzwerkeAusgewaehlt.length; ++i) {
			for (int k = 0; k < NetzwerkeAusgewaehlt[i]; ++k) {
				BelegeSubnetz(i);
			}
		}

		// ArrayList f�r die Threads zum �bertragen der ausgew�hlten Subnetze
		ArrayList<Thread> Threads = new ArrayList<Thread>();

		// Ausgew�hlte Subnetze (und ggf. Uplink-Netzwerk) von NetzwerkeVerfuegbar in
		// AusgewaehlteSubnetze �bertragen (ein Thread pro Prefix)
		for (int i = 0; i < NetzwerkeAusgewaehlt.length; ++i) {
			final int IndexPrefixThread = i; // ArrayList Index f�r Thread
			Threads.add(new Thread(new Runnable() { // neuer Thread
				int index = IndexPrefixThread;

				@Override
				public void run() {
					for (int i = 0; i < NetzwerkeVerfuegbar.get(index).size(); ++i) {
						if (NetzwerkeVerfuegbar.get(index).get(i).getManuell()
								|| NetzwerkeVerfuegbar.get(index).get(i).getUplink()) {
							AusgewaehlteSubnetze.get(index).add(NetzwerkeVerfuegbar.get(index).get(i));
						}
					}
				}
			}));
			Threads.get(Threads.size() - 1).start(); // thread starten
		}
		// Erst weiter machen, wenn alle Threads fertig sind
		for (int i = 0; i < Threads.size(); ++i) {
			try {
				Threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Freie Subnetze zusammenfassen und in FreieSubnetze �bertragen
		int SummeUebrig = 0;
		do {
			SummeUebrig = 0;
			// Z�hlen, wie viele Subnetze noch verf�gbar sind
			for (int i = 0; i < NetzwerkeUebrig.length; ++i) {
				SummeUebrig += NetzwerkeUebrig[i];
			}
			if (SummeUebrig > 0) {
				// Das gr��te freie Netzwerk finden
				int i = 0;
				while (i < NetzwerkeUebrig.length && NetzwerkeUebrig[i] == 0) {
					++i;
				}
				int pos = FindeFreiesNetzwerk(i);
				// F�ge gr��tes freies Netzwerk zu FreieSubnetze hinzu
				FreieSubnetze.add(NetzwerkeVerfuegbar.get(i).get(pos));
				// Belege gr��tes freies Netzwerk in NetzwerkeVerfuegbar
				NetzwerkeVerfuegbar.get(i).get(pos).setVerfuegbar(false);
				AktualisiereVerfuegbarkeitHinz(i, pos);

				// Aktualisiere Z�hlerarray
				NetzwerkeUebrig[i] -= 1; // Belege Subnetz im Z�hler-Array

				// Aktualisiere die Anzahl der verf�gbaren Subnetze in den nachfolgenden
				// Prefixen
				for (int k = 0; k < 30 - getPrefix() - i - 1; ++k) {
					NetzwerkeUebrig[i + k + 1] -= potint(2, k + 1);
				}

				// Aktualisiere die Anzahl der verf�gbaren Subnetze in den vorhergehenden
				// Prefixen
				for (int k = i - 1; k >= 0; --k) {
					/*
					 * Wenn die Anzahl an verf�gbaren Subnetzen im nachfolgenden Prefix kleiner als
					 * 2* die (noch) aktuelle Anzahl an m�glichen Subnetzen im Prefix ist
					 */
					if (NetzwerkeUebrig[k + 1] < 2 * NetzwerkeUebrig[k]) {
						NetzwerkeUebrig[k] -= 1;
					}
				}
			}

		} while (SummeUebrig > 0); // Solange noch Subnetze Verf�gbar sind

		Threads.clear();

		// Thread zum Zur�cksetzen der Belegung in NetzwerkeVerfuegbar
		Thread SubnetzeZuruecksetzen = new Thread(new Runnable() {
			boolean uplink = false; // Ob ein Uplink Netzwerk gefunden wurde
			int uplinkPrefix = 0; // Prefix(Index) des Uplink-Netzwerks
			int uplinkPos = 0; // Position des Uplink-Netzwerks im Prefix

			@Override
			public void run() {
				ArrayList<Thread> ThreadsZuruecksetzen = new ArrayList<Thread>();
				// Setze Belegung in NetzwerkeVerfuegbar zur�ck (Damit es keine Probleme gibt,
				// wenn der Benutzer in der Auswahl etwas �ndert)
				// Ein Thread pro Prefix
				for (int i = 0; i < NetzwerkeVerfuegbar.size(); ++i) {
					final int IndexPrefixThread = i;
					ThreadsZuruecksetzen.add(new Thread(new Runnable() {
						int index = IndexPrefixThread;

						@Override
						public void run() {
							// Setze alle Subnetze zur�ck bis auf Uplink
							for (int i = 0; i < NetzwerkeVerfuegbar.get(index).size(); ++i) {
								if (!NetzwerkeVerfuegbar.get(index).get(i).getUplink()) {
									NetzwerkeVerfuegbar.get(index).get(i).setManuell(false);
									NetzwerkeVerfuegbar.get(index).get(i).setVerfuegbar(true);
								} else {
									// Wenn Thread das Uplink Netzwerk findet
									uplink = true;
									uplinkPrefix = index;
									uplinkPos = i;
								}
							}

						}
					}));
					ThreadsZuruecksetzen.get(ThreadsZuruecksetzen.size() - 1).start();

				}
				// Dieser Thread soll erst weitermachen, wenn alle Threads fertig sind
				for (int i = 0; i < ThreadsZuruecksetzen.size(); ++i) {
					try {
						ThreadsZuruecksetzen.get(i).join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// Wenn ein Uplink Netzwerk gefunden wurde
				if (uplink) {
					// Da alle Subnetze zur�ckgesetzt wurden, muss die Verf�gbarkeit der Subnetze,
					// die durch das Uplink-Netzwerk blockert werden, aktualisiert werden
					AktualisiereVerfuegbarkeitHinz(uplinkPrefix, uplinkPos);
				}
				ThreadsZuruecksetzen.clear();
			}
		});
		SubnetzeZuruecksetzen.start();
		/*
		 * Auf den Thread zum Zur�cksetzen der Belegung muss nicht gewartet werden, er
		 * soll im Hintergrund laufen
		 */
	}

	// Funktion f�r Potenzrechnung mit Integern
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

	public ArrayList<ArrayList<Netzwerk>> getAusgewaehlteSubnetze() {
		return AusgewaehlteSubnetze;
	}

	public ArrayList<Netzwerk> getFreieSubnetze() {
		return FreieSubnetze;
	}

}
