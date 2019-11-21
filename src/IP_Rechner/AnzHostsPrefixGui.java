package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AnzHostsPrefixGui {

	protected Shell shlAnzahlHostsZu;
	private Text AnzahlHosts; // Spinner kann nur bis ca. 2.1 Mrd. Ich brauche aber 4,3 Mrd. Zudem ist die
								// Darstellung bei Zahlen über 999 kaputt
	private Text txtAusgPrefix;
	private boolean offen;

	public AnzHostsPrefixGui() {
		offen = false;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAnzahlHostsZu.open();
		shlAnzahlHostsZu.layout();
		offen = true;
		while (!shlAnzahlHostsZu.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlAnzahlHostsZu = new Shell();
		shlAnzahlHostsZu.setImage(SWTResourceManager.getImage(AnzHostsPrefixGui.class, "/Icons/IP_Rechner.ico"));
		shlAnzahlHostsZu.setSize(399, 169);
		shlAnzahlHostsZu.setText("Anzahl Hosts zu Prefix");
		shlAnzahlHostsZu.addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				offen = false;

			}
		});

		Label lblAufforderung = new Label(shlAnzahlHostsZu, SWT.NONE);
		lblAufforderung.setBounds(10, 10, 307, 21);
		lblAufforderung.setText("Geben Sie die Anzahl an ben\u00F6tigten Hosts ein:");

		Label lblAnzahlHosts = new Label(shlAnzahlHostsZu, SWT.NONE);
		lblAnzahlHosts.setBounds(20, 35, 90, 21);
		lblAnzahlHosts.setText("Anzahl Hosts:");

		AnzahlHosts = new Text(shlAnzahlHostsZu, SWT.BORDER);
		AnzahlHosts.setBounds(116, 34, 90, 24);

		Button btnBerechne = new Button(shlAnzahlHostsZu, SWT.NONE);
		btnBerechne.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!eingabePruefen(AnzahlHosts.getText())) {
					MessageBox warnung = new MessageBox(shlAnzahlHostsZu, SWT.ICON_WARNING | SWT.OK);
					warnung.setText("Fehlerhafte Eingabe");
					warnung.setMessage("Die Eingabe muss eine Zahl zwischen 1 und 4294967294 sein!");
					warnung.open();
				} else {
					// Prefix berechnen
					int Prefix;
					long AnzHosts = Long.parseLong(AnzahlHosts.getText());
					if (AnzHosts <= 2) { // Mindestens /30
						Prefix = 30;
					} else {
						int i = 3;
						while (i < 32 && pot(2, i) - 2 < AnzHosts) {
							++i;
						}
						Prefix = 32 - i;
					}
					String SubnetzmaskeBin = PrefixToBin(Prefix);
					String Subnetzmaske = BinToAddr(SubnetzmaskeBin);

					String Ausgabe = Subnetzmaske;
					Ausgabe = Ausgabe.concat(", /");
					Ausgabe = Ausgabe.concat(Integer.toString(Prefix));
					txtAusgPrefix.setText(Ausgabe);
				}
			}
		});
		btnBerechne.setBounds(30, 64, 324, 26);
		btnBerechne.setText("Berechne Prefix");

		Label lblSubnetzmaskePrefix = new Label(shlAnzahlHostsZu, SWT.NONE);
		lblSubnetzmaskePrefix.setBounds(20, 94, 144, 21);
		lblSubnetzmaskePrefix.setText("Subnetzmaske, Prefix:");

		txtAusgPrefix = new Text(shlAnzahlHostsZu, SWT.READ_ONLY | SWT.MULTI);
		txtAusgPrefix.setBounds(170, 94, 196, 24);

	}

	// Funktion für Potenzrechnung
	private long pot(long Basis, int Exponent) {
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

	// Funktion zum Überprüfen der Eingabe
	private boolean eingabePruefen(String AnzahlHosts) {
		long dummy;
		try {
			dummy = Long.parseLong(AnzahlHosts);
		} catch (Exception e) {
			return false;
		}

		long maxAnzahl = pot(2, 32) - 2;
		if (dummy < 1 || dummy > maxAnzahl) {
			return false;
		}

		return true;
	}

	// Wandelt einen Prefix in eine binäre Subnetzmaske um
	private String PrefixToBin(int Prefix) {
		String PrefixBin = "";
		for (int i = 0; i < Prefix; ++i) {
			PrefixBin = PrefixBin.concat("1");
		}
		for (int i = 0; i < 32 - Prefix; ++i) {
			PrefixBin = PrefixBin.concat("0");
		}
		return PrefixBin;
	}

	// Wandelt eine binäre Zahl in eine dezimale Zahl um
	private long BinToLong(String Binaer) {
		long Zahl = 0;
		for (int i = 0; i < Binaer.length(); ++i) {
			if (Binaer.charAt(Binaer.length() - 1 - i) == '1') {
				Zahl += pot(2, i);
			}
		}
		return Zahl;
	}

	// Wandelt eine Binäre Adresse in dotted-decimal um
	private String BinToAddr(String Binaer) {
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

	public boolean getOffen() {
		return offen;
	}

	public void schliessen() {
		shlAnzahlHostsZu.close();
	}

	public void inVordergrund() {
		shlAnzahlHostsZu.setMinimized(false);
		shlAnzahlHostsZu.setActive();
		shlAnzahlHostsZu.moveAbove(null);
	}

}
