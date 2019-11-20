package IP_Rechner;

public class Netzwerk {

	private long Netzwerkaddr;
	private int Prefix;
	private long Broadcast;
	private long MinHost;
	private long MaxHost;
	
	//Konstruktor für VLSM
	public Netzwerk(long Adresse, int Prefix) {
		Netzwerkaddr = Adresse;
		this.Prefix = Prefix;
		Broadcast = Netzwerkaddr + ((2^(32-this.Prefix))-1);
		MinHost = Netzwerkaddr +1;
		MaxHost = Broadcast -1;
	}
	
	//Konstruktor für Netzwerkinfos
	public Netzwerk(int okt1, int okt2, int okt3, int okt4, int Prefix) {
		this.Prefix = Prefix;
		String IPAddrBin = "";
		//Erstes Oktett zur Binären IP-Adresse hinzufügen
		String Temp = LongToBin(okt1);
		String OktBin = "";
		for(int i = 0; i< 8- Temp.length();++i) {
			OktBin = OktBin.concat("0");
		}
		OktBin = OktBin.concat(Temp);
		IPAddrBin = IPAddrBin.concat(OktBin);
		
		//Zweites Oktett zur Binären IP-Adresse hinzufügen
		Temp = LongToBin(okt2);
		OktBin = "";
		for(int i = 0; i< 8- Temp.length();++i) {
			OktBin = OktBin.concat("0");
		}
		OktBin.concat(Temp);
		IPAddrBin = IPAddrBin.concat(OktBin);
		
		//Drittes Oktett zur Binären IP-Adresse hinzufügen
		Temp = LongToBin(okt3);
		OktBin = "";
		for(int i = 0; i< 8- Temp.length();++i) {
			OktBin = OktBin.concat("0");
		}
		OktBin = OktBin.concat(Temp);
		IPAddrBin = IPAddrBin.concat(OktBin);
		
		//Viertes Oktett zur Binären IP-Adresse hinzufügen
		Temp = LongToBin(okt4);
		OktBin = "";
		for(int i = 0; i< 8- Temp.length();++i) {
			OktBin = OktBin.concat("0");
		}
		OktBin=OktBin.concat(Temp);
		IPAddrBin = IPAddrBin.concat(OktBin);
		
		String NetzwerkAddrBin = "";
		
		for(int i = 0; i< Prefix;++i) {
			NetzwerkAddrBin = NetzwerkAddrBin.concat(IPAddrBin.substring(i, i+1));
		}
		for(int i = 0; i<32-Prefix;++i) {
			NetzwerkAddrBin = NetzwerkAddrBin.concat("0");
		}
		Netzwerkaddr = BinToLong(NetzwerkAddrBin);
		
		Broadcast = Netzwerkaddr + ((2^(32-this.Prefix))-1);
		MinHost = Netzwerkaddr +1;
		MaxHost = Broadcast -1;
		
		
		
	}

	// Wandelt eine dezimale Zahl in eine binäre Zahl um
	public String LongToBin(long Zahl) {
		int MaxExponent = 0;
		while ((2 ^ MaxExponent) < Zahl) { // Finde den Größten zur Umrechnung benötigten Exponenten
			++MaxExponent;
		}
		String ZahlBin = "";

		for (int i = 0; i <= MaxExponent; ++i) {
			if (Zahl - (2 ^ MaxExponent) >= 0) {
				Zahl -= 2 ^ MaxExponent;
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
				Zahl += 2 ^ i;
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
				Adresse =Adresse.concat(".");
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

	//Berechnet die Dotted Decimal Form der Netzwerkadresse und gibt diese Zurück
	public String getNetzwerkAddrDD() {
		String Temp = LongToBin(Netzwerkaddr);
		String NetzwerkAddrBin = "";
		for (int i = 0; i < 32 - Temp.length(); ++i) { // Binäre Subnetzmaske auf 32 Stellen erweitern
			NetzwerkAddrBin = NetzwerkAddrBin.concat("0");
		}
		NetzwerkAddrBin = NetzwerkAddrBin.concat(Temp);
		String NetzwerkaddrDD = BinToAddr(NetzwerkAddrBin);
		return NetzwerkaddrDD;
	}
	
	//Berechnet die Dotted Decimal Form der Subnetzmaske und gibt diese Zurück
	public String getSubnetzmaskeDD() {
		String SubnetzmaskeDD = BinToAddr(PrefixToBin(Prefix));
		return SubnetzmaskeDD;
	}

	//Berechnet die Dotted Decimal Form der Broadcastadresse und gibt diese Zurück
	public String getBroadcastDD() {
		String Temp = LongToBin(Broadcast);
		String BroadcastBin = "";
		for (int i = 0; i < 32 - Temp.length(); ++i) { // Binäre Broadcastadresse auf 32 Stellen erweitern
			BroadcastBin = BroadcastBin.concat("0");
		}
		BroadcastBin = BroadcastBin.concat(Temp);
		
		String BroadcastDD = BinToAddr(BroadcastBin);
		return BroadcastDD;
	}
	
	//Berechnet die Dotted Decimal Form der kleinsten Host-Adresse und gibt diese Zurück
	public String getMinHostDD() {
		String Temp = LongToBin(MinHost);
		String MinHostBin = "";
		for (int i = 0; i < 32 - Temp.length(); ++i) { // Binäre kleinste Host-Adresse auf 32 Stellen erweitern
		MinHostBin = MinHostBin.concat("0");
		}
		MinHostBin = MinHostBin.concat(Temp);
		
		String MinHostDD = BinToAddr(MinHostBin);
		return MinHostDD;
	}
	
	//Berechnet die Dotted Decimal Form der größten Host-Adresse und gibt diese Zurück
	public String getMaxHostDD() {
		String Temp = LongToBin(MaxHost);
		String MaxHostBin = "";
		for (int i = 0; i < 32 - Temp.length(); ++i) { // Binäre größte Host-Adresse auf 32 Stellen erweitern
			MaxHostBin = MaxHostBin.concat("0");
		}
		MaxHostBin = MaxHostBin.concat(Temp);
		
		String MaxHostDD = BinToAddr(MaxHostBin);
		return MaxHostDD;
	}
	
	public long getNetzwerkAddr() {
		return Netzwerkaddr;
	}

	public int getPrefix() {
		return Prefix;
	}

	public long getBroadcast() {
		return Broadcast;
	}

}
