package IP_Rechner;

public class NetzwerkVerfuegbar extends Netzwerk {
	private boolean Verfuegbar;
	private boolean Manuell;
	private boolean Uplink;

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
