package IP_Rechner;
/*Status eines möglichen Subnetzes (VLSM)*/
public class NetzwerkVerfuegbar extends Netzwerk {
	private boolean Verfuegbar; // Ist das Netzwerk noch verfügbar?
	private boolean Manuell; // Ist das Netzwerk durch den Benutzer "ausgewählt" worden?
	private boolean Uplink; // Ist das Netzerk das Uplink-Netzwerk?

	public NetzwerkVerfuegbar(Long Adresse, int Prefix) {
		super(Adresse, Prefix);
		Verfuegbar = true;
		Manuell = false;
		Uplink = false;
	}

	public boolean getVerfuegbar() {
		return Verfuegbar;
	}

	public boolean getManuell() {
		return Manuell;
	}

	public boolean getUplink() {
		return Uplink;
	}

	public void setVerfuegbar(boolean wert) {
		Verfuegbar = wert;
	}

	public void setManuell(boolean wert) {
		Manuell = wert;
	}

	public void setUplink(boolean wert) {
		Uplink = wert;
	}
}
