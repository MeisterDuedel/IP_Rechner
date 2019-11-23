package IP_Rechner;

public class Netzwerk {

	private long Netzwerkaddr;
	private int Prefix;
	// private long Broadcast;
	// private long MinHost;
	// private long MaxHost;

	// Konstruktor für VLSM
	public Netzwerk(long Adresse, int Prefix) {
		Netzwerkaddr = Adresse;
		this.Prefix = Prefix;
	}

	// Konstruktor für Netzwerkinfos
	public Netzwerk(int okt1, int okt2, int okt3, int okt4, int Prefix) {
		this.Prefix = Prefix;
		String IPAddrBin = "";
		// Erstes Oktett zur Binären IP-Adresse hinzufügen
		String OktBin = LongToBin(okt1, false);
		IPAddrBin = IPAddrBin.concat(OktBin);

		// Zweites Oktett zur Binären IP-Adresse hinzufügen
		OktBin = LongToBin(okt2, false);
		IPAddrBin = IPAddrBin.concat(OktBin);

		// Drittes Oktett zur Binären IP-Adresse hinzufügen
		OktBin = LongToBin(okt3, false);
		IPAddrBin = IPAddrBin.concat(OktBin);

		// Viertes Oktett zur Binären IP-Adresse hinzufügen
		OktBin = LongToBin(okt4, false);
		IPAddrBin = IPAddrBin.concat(OktBin);

		String NetzwerkAddrBin = "";

		for (int i = 0; i < Prefix; ++i) {
			NetzwerkAddrBin = NetzwerkAddrBin.concat(IPAddrBin.substring(i, i + 1));
		}
		for (int i = 0; i < 32 - Prefix; ++i) {
			NetzwerkAddrBin = NetzwerkAddrBin.concat("0");
		}

		Netzwerkaddr = BinToLong(NetzwerkAddrBin);

	}

	// Wandelt eine dezimale Zahl in eine binäre Zahl um
	public String LongToBin(long Zahl, boolean Adresse) {
		int MaxExponent = 0;
		if (Adresse) {
			MaxExponent = 31; // Größter Exponent bei Adressen
		} else {
			MaxExponent = 7; // Größter Exponent bei Oktetten
		}
		String ZahlBin = "";
		for (int i = 0; i <= MaxExponent; ++i) {
			if (Zahl - pot(2, MaxExponent - i) >= 0) {
				Zahl -= pot(2, MaxExponent - i);
				ZahlBin = ZahlBin.concat("1");
			} else {
				ZahlBin = ZahlBin.concat("0");
			}
		}
		return ZahlBin;
	}

	// Wandelt eine binäre Zahl in eine dezimale Zahl um
	public long BinToLong(String Binaer) {
		long Zahl = 0;
		for (int i = 0; i < Binaer.length(); ++i) {
			if (Binaer.charAt(Binaer.length() - 1 - i) == '1') {
				Zahl += pot(2, i);
			}
		}
		return Zahl;
	}

	// Wandelt eine Binäre Adresse in dotted-decimal um
	public String BinToAddr(String Binaer) {
		String Adresse = "";
		for (int i = 1; i <= 4; ++i) {
			long okt = BinToLong(Binaer.substring((i * 8) - 8, i * 8));
			Adresse = Adresse.concat(Long.toString(okt));
			if (i < 4) {
				Adresse = Adresse.concat(".");
			}
		}
		return Adresse;
	}

	// Wandelt einen Prefix in eine binäre Subnetzmaske um
	public String PrefixToBin(int Prefix) {
		String PrefixBin = "";
		for (int i = 0; i < Prefix; ++i) {
			PrefixBin = PrefixBin.concat("1");
		}
		for (int i = 0; i < 32 - Prefix; ++i) {
			PrefixBin = PrefixBin.concat("0");
		}
		return PrefixBin;
	}

	// Berechnet die Dotted Decimal Form der Netzwerkadresse und gibt diese Zurück
	public String getNetzwerkAddrDD() {
		String NetzwerkAddrBin = LongToBin(Netzwerkaddr, true);

		String NetzwerkaddrDD = BinToAddr(NetzwerkAddrBin);
		return NetzwerkaddrDD;
	}

	// Berechnet die Dotted Decimal Form der Subnetzmaske und gibt diese Zurück
	public String getSubnetzmaskeDD() {
		String SubnetzmaskeDD = BinToAddr(PrefixToBin(Prefix));
		return SubnetzmaskeDD;
	}

	// Berechnet die Dotted Decimal Form der Broadcastadresse und gibt diese Zurück
	public String getBroadcastDD() {
		String BroadcastBin = LongToBin(Netzwerkaddr + ((pot(2, 32 - Prefix)) - 1), true);

		String BroadcastDD = BinToAddr(BroadcastBin);
		return BroadcastDD;
	}

	// Berechnet die Dotted Decimal Form der kleinsten Host-Adresse und gibt diese
	// Zurück
	public String getMinHostDD() {
		long MinHost = 0;
		if (Prefix < 31) {
			MinHost = Netzwerkaddr + +1;
		} else { // Ein /31 Netzwerk hat nur Netzwerkadresse und Broadcastadresse
			MinHost = 0;
		}
		String MinHostBin = LongToBin(MinHost, true);

		String MinHostDD = BinToAddr(MinHostBin);
		return MinHostDD;
	}

	// Berechnet die Dotted Decimal Form der größten Host-Adresse und gibt diese
	// Zurück
	public String getMaxHostDD() {
		long MaxHost = 0;

		if (Prefix < 31) {
			MaxHost = (Netzwerkaddr + ((pot(2, 32 - Prefix)) - 1)) - 1;
		} else { // Ein /31 Netzwerk hat nur Netzwerkadresse und Broadcastadresse
			MaxHost = 0;
		}

		String MaxHostBin = LongToBin(MaxHost, true);

		String MaxHostDD = BinToAddr(MaxHostBin);
		return MaxHostDD;
	}

	// Funktion für Potenzrechnung
	protected long pot(long Basis, int Exponent) {
		long Zahl = Basis;
		if (Exponent == 0) {
			Zahl = 1;
		} else {
			for (int i = 1; i < Exponent; ++i) {
				Zahl *= Basis;
			}
		}
		return Zahl;
	}

	public long getNetzwerkAddr() {
		return Netzwerkaddr;
	}

	public int getPrefix() {
		return Prefix;
	}

	public long getBroadcast() {
		return Netzwerkaddr + ((pot(2, 32 - Prefix)) - 1);
	}

	public long getMinHost() {
		long MinHost = 0;
		if (Prefix < 31) {
			MinHost = Netzwerkaddr + +1;
		} else { // Ein /31 Netzwerk hat nur Netzwerkadresse und Broadcastadresse
			MinHost = 0;
		}

		return MinHost;
	}

	public long getMaxHost() {
		long MaxHost = 0;
		if (Prefix < 31) {
			MaxHost = (Netzwerkaddr + ((pot(2, 32 - Prefix)) - 1)) - 1;
		} else { // Ein /31 Netzwerk hat nur Netzwerkadresse und Broadcastadresse
			MaxHost = 0;
		}
		return MaxHost;
	}

}
