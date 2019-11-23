package IP_Rechner;

import java.util.ArrayList;

public class GrundNetzwerk extends Netzwerk {
	private ArrayList<ArrayList<NetzwerkVerfuegbar>> NetzwerkeVerfuegbar = new ArrayList<ArrayList<NetzwerkVerfuegbar>>();
	// Zeilen = Prefixe, Spalten = M�gliche Subnetze pro Prefix

	public GrundNetzwerk(int okt1, int okt2, int okt3, int okt4, int Prefix) {
		super(okt1, okt2, okt3, okt4, Prefix);
		ArrayList<Thread> Threads = new ArrayList<Thread>(); // ArrayList f�r die Threads zur Instanziierung der Spalten
		// Instanziiere Die Zeilen (Prefixe) im 2D Array
		for (int i = 0; i < 30 - Prefix; ++i) {
			NetzwerkeVerfuegbar.add(new ArrayList<NetzwerkVerfuegbar>());
		}

		// Instanziierung Spalten (Subnetze), ein Thread pro Spalte
		for (int i = 0; i < 30 - Prefix; ++i) {
			final int IndexThreadsPrefix = i;// Damit ein Thread wei�, auf welchen Index ("Prefix") er in der ArrayList
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

	}

	// Z�hle die noch verf�gbaren Subnetze
	public long ZaehleVerfuegbareSubnetze(int IndexPrefix) {
		int Zaehler = 0;
		for (int i = 0; i < NetzwerkeVerfuegbar.get(IndexPrefix).size(); ++i) {
			if (NetzwerkeVerfuegbar.get(IndexPrefix).get(i).getVerfuegbar() == true) {
				++Zaehler;
			}
		}
		return Zaehler;
	}

	// Z�hle die durch den Benutzer ausgew�hlten Subnetze
	public long ZaehleAusgewaehlteSubnetze(int IndexPrefix) {
		int Zaehler = 0;
		for (int i = 0; i < NetzwerkeVerfuegbar.get(IndexPrefix).size(); ++i) {
			if (NetzwerkeVerfuegbar.get(IndexPrefix).get(i).getManuell() == true) {
				++Zaehler;
			}
		}
		return Zaehler;
	}

}
