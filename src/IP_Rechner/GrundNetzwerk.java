package IP_Rechner;
/* Klasse zur Auswahl der Anzahl an Subnetzen pro Prefix (VLSM)*/
public class GrundNetzwerk {
	int Prefix;
	// Array, in dem die Anzahl der verfügbaren Subnetze gespeichert wird.
	int[] NetzwerkeVerfuegbar;
	// Array, in dem die Anzahl der ausgewählten Subnetze gespeichert wird.
	int[] NetzwerkeAusgewaehlt;

	public GrundNetzwerk(int Prefix) {
		this.Prefix = Prefix;
		NetzwerkeVerfuegbar = new int[30 - Prefix];
		NetzwerkeAusgewaehlt = new int[30 - Prefix];

		// Felder der Arrays mit den entsprechenden Werten vorbelegen
		for (int i = 0; i < 30 - Prefix; ++i) {
			NetzwerkeVerfuegbar[i] = potint(2, i + 1);
			NetzwerkeAusgewaehlt[i] = 0;
		}

	}

	// Belegt ein Subnetz und aktualisiert die Verfügbarkeit der übrigen Subnetze
	public void BelegeSubnetz(int IndexPrefix) {
		NetzwerkeVerfuegbar[IndexPrefix] -= 1; // Belege Subnetz
		NetzwerkeAusgewaehlt[IndexPrefix] += 1; // Füge Subnetz zur Auswahl hinzu

		// Aktualisiere die Anzahl der verfügbaren Subnetze in den nachfolgenden
		// Prefixen
		for (int i = 0; i < 30 - Prefix - IndexPrefix - 1; ++i) {
			NetzwerkeVerfuegbar[IndexPrefix + i + 1] -= potint(2, i + 1);
		}

		// Aktualisiere die Anzahl der verfügbaren Subnetze in den vorhergehenden
		// Prefixen
		for (int i = IndexPrefix - 1; i >= 0; --i) {
			/*
			 * Wenn die Anzahl an verfügbaren Subnetzen im nachfolgenden Prefix kleiner als
			 * 2* die (noch) aktuelle Anzahl an möglichen Subnetzen im Prefix ist
			 */
			if (NetzwerkeVerfuegbar[i + 1] < 2 * NetzwerkeVerfuegbar[i]) {
				NetzwerkeVerfuegbar[i] -= 1;
			}
		}
	}

	// Gibt ein Subnetz frei und aktualisiert die Verfügbarkeit der übrigen Subnetze
	public void GebeSubnetzFrei(int IndexPrefix) {
		NetzwerkeVerfuegbar[IndexPrefix] += 1; // Mache Subnetz wieder verfügbar
		NetzwerkeAusgewaehlt[IndexPrefix] -= 1; // Entferne Subnetz aus Auswahl
		// Aktualisiere die Anzahl der verfügbaren Subnetze in den nachfolgenden
		// Prefixen
		for (int i = 0; i < 30 - Prefix - IndexPrefix - 1; ++i) {
			NetzwerkeVerfuegbar[IndexPrefix + i + 1] += potint(2, i + 1);
		}

		// Aktualisiere die Anzahl der verfügbaren Subnetze in den vorhergehenden
		// Prefixen
		for (int i = IndexPrefix - 1; i >= 0; --i) {
			/*
			 * Wenn die Anzahl an verfügbaren Subnetzen im nachfolgenden Prefix gleich 2*
			 * die (noch) aktuelle Anzahl an möglichen Subnetzen im Prefix +1 ist
			 */
			if (NetzwerkeVerfuegbar[i + 1] == 2 * (NetzwerkeVerfuegbar[i] + 1)) {
				NetzwerkeVerfuegbar[i] += 1;
			}
		}
	}

	// Belegt das Uplink-Netzwerk und aktualisiert die Verfügbarkeit der übrigen
	// Subnetze
	public void BelegeUplinkSubnetz(int IndexPrefix) {
		NetzwerkeVerfuegbar[IndexPrefix] -= 1; // Belege Subnetz

		// Aktualisiere die Anzahl der verfügbaren Subnetze in den nachfolgenden
		// Prefixen
		for (int i = 0; i < 30 - Prefix - IndexPrefix - 1; ++i) {
			NetzwerkeVerfuegbar[IndexPrefix + i + 1] -= potint(2, i + 1);
		}

		// Aktualisiere die Anzahl der verfügbaren Subnetze in den vorhergehenden
		// Prefixen
		for (int i = IndexPrefix - 1; i >= 0; --i) {
			/*
			 * Wenn die Anzahl an verfügbaren Subnetzen im nachfolgenden Prefix kleiner als
			 * 2* die (noch) aktuelle Anzahl an möglichen Subnetzen im Prefix ist
			 */
			if (NetzwerkeVerfuegbar[i + 1] < 2 * NetzwerkeVerfuegbar[i]) {
				NetzwerkeVerfuegbar[i] -= 1;
			}
		}
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

	public int[] getNetzwerkeVerfuegbar() {
		return NetzwerkeVerfuegbar;
	}

	public int[] getNetzwerkeAusgewaehlt() {
		return NetzwerkeAusgewaehlt;
	}

	public int getPrefix() {
		return Prefix;
	}

}
