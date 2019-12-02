package IP_Rechner;
/*Berechnung des Prefixes zu einer vom Benutzer eingegebenen Subnetzmaske*/
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SubnetzPrefixGui {

	protected Shell shlSubnetzmaskeZuPrefix;
	private Text txtAusgPrefix;
	private boolean offen;

	public SubnetzPrefixGui() {
		offen = false;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSubnetzmaskeZuPrefix.open();
		shlSubnetzmaskeZuPrefix.layout();
		offen = true;
		while (!shlSubnetzmaskeZuPrefix.isDisposed()) {
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
		shlSubnetzmaskeZuPrefix = new Shell(SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shlSubnetzmaskeZuPrefix.setImage(SWTResourceManager.getImage(SubnetzPrefixGui.class, "/Icons/IP_Rechner.ico"));
		shlSubnetzmaskeZuPrefix.setText("Subnetzmaske zu Prefix");
		shlSubnetzmaskeZuPrefix.setSize(403, 174);
		shlSubnetzmaskeZuPrefix.addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				offen = false;

			}
		});

		Label lblAufforderung = new Label(shlSubnetzmaskeZuPrefix, SWT.NONE);
		lblAufforderung.setBounds(10, 10, 367, 21);
		lblAufforderung.setText("Geben Sie eine Subnetzmaske (dotted decimal) ein:");

		Label lblSubnetzmaske = new Label(shlSubnetzmaskeZuPrefix, SWT.NONE);
		lblSubnetzmaske.setBounds(20, 35, 100, 21);
		lblSubnetzmaske.setText("Subnetzmaske:");

		Spinner okt1 = new Spinner(shlSubnetzmaskeZuPrefix, SWT.BORDER);
		okt1.setMaximum(255);
		okt1.setBounds(126, 34, 55, 24);

		Label label = new Label(shlSubnetzmaskeZuPrefix, SWT.NONE);
		label.setBounds(183, 36, 4, 21);
		label.setText(".");

		Spinner okt2 = new Spinner(shlSubnetzmaskeZuPrefix, SWT.BORDER);
		okt2.setMaximum(255);
		okt2.setBounds(187, 34, 55, 24);

		Label label_1 = new Label(shlSubnetzmaskeZuPrefix, SWT.NONE);
		label_1.setBounds(243, 36, 4, 21);
		label_1.setText(".");

		Spinner okt3 = new Spinner(shlSubnetzmaskeZuPrefix, SWT.BORDER);
		okt3.setMaximum(255);
		okt3.setBounds(248, 34, 55, 24);

		Label label_2 = new Label(shlSubnetzmaskeZuPrefix, SWT.NONE);
		label_2.setBounds(305, 36, 4, 21);
		label_2.setText(".");

		Spinner okt4 = new Spinner(shlSubnetzmaskeZuPrefix, SWT.BORDER);
		okt4.setMaximum(255);
		okt4.setBounds(309, 34, 55, 24);

		Button btnBerechne = new Button(shlSubnetzmaskeZuPrefix, SWT.NONE);
		btnBerechne.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String SubnetzmaskeBin = "";
				// Erstes Oktett zur binären Subnetzmaske hinzufügen
				String OktBin = LongToBin(okt1.getSelection());
				SubnetzmaskeBin = SubnetzmaskeBin.concat(OktBin);

				// Zweites Oktett zur binären Subnetzmaske hinzufügen
				OktBin = LongToBin(okt2.getSelection());
				SubnetzmaskeBin = SubnetzmaskeBin.concat(OktBin);

				// Drittes Oktett zur binären Subnetzmaske hinzufügen
				OktBin = LongToBin(okt3.getSelection());
				SubnetzmaskeBin = SubnetzmaskeBin.concat(OktBin);

				// Viertes Oktett zur binären Subnetzmaske hinzufügen
				OktBin = LongToBin(okt4.getSelection());
				SubnetzmaskeBin = SubnetzmaskeBin.concat(OktBin);

				if (!eingabePruefen(SubnetzmaskeBin)) {
					// Wenn Eingabe keine gültige Subnetzmaske, Warnung ausgeben
					MessageBox warnung = new MessageBox(shlSubnetzmaskeZuPrefix, SWT.ICON_WARNING | SWT.OK);
					warnung.setText("Fehlerhafte Eingabe");
					warnung.setMessage("Die Eingabe ist keine gültige Subnetzmaske!");
					warnung.open();
				} else {
					// Prefix berechnen
					int prefix = berechnePrefix(SubnetzmaskeBin);
					// Füge Oktette für Ausgabe zusammen
					String Ausgabe = "";
					Ausgabe = Ausgabe.concat(Integer.toString(okt1.getSelection()));
					Ausgabe = Ausgabe.concat(".");
					Ausgabe = Ausgabe.concat(Integer.toString(okt2.getSelection()));
					Ausgabe = Ausgabe.concat(".");
					Ausgabe = Ausgabe.concat(Integer.toString(okt3.getSelection()));
					Ausgabe = Ausgabe.concat(".");
					Ausgabe = Ausgabe.concat(Integer.toString(okt4.getSelection()));
					Ausgabe = Ausgabe.concat(", /");
					Ausgabe = Ausgabe.concat(Integer.toString(prefix));
					txtAusgPrefix.setText(Ausgabe);
				}
			}
		});
		btnBerechne.setBounds(30, 64, 324, 26);
		btnBerechne.setText("Berechne Prefix");

		Label lblSubnetzmaskePrefix = new Label(shlSubnetzmaskeZuPrefix, SWT.NONE);
		lblSubnetzmaskePrefix.setBounds(20, 96, 144, 21);
		lblSubnetzmaskePrefix.setText("Subnetzmaske, Prefix:");

		txtAusgPrefix = new Text(shlSubnetzmaskeZuPrefix, SWT.READ_ONLY | SWT.MULTI);
		txtAusgPrefix.setBounds(170, 96, 194, 24);

	}

	// Wandelt eine dezimale Zahl in eine binäre Zahl um
	public String LongToBin(long Zahl) {
		String ZahlBin = "";
		for (int i = 0; i <= 7; ++i) {
			if (Zahl - pot(2, 7 - i) >= 0) {
				Zahl -= pot(2, 7 - i);
				ZahlBin = ZahlBin.concat("1");
			} else {
				ZahlBin = ZahlBin.concat("0");
			}
		}
		return ZahlBin;
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

	// Prüft, ob die Eingabe eine Gültige Subnetzmaske ist
	private boolean eingabePruefen(String SubnetzmaskeBin) {
		int i = 0;
		while (i < 32 && SubnetzmaskeBin.charAt(i) == '1') {
			++i;
		}

		while (i < 32 && SubnetzmaskeBin.charAt(i) == '0') {
			++i;
		}

		if (i == 32) {
			return true;
		} else {
			return false;
		}
	}

	// Berechnet aus der binären Subnetzmaske den Prefix
	private int berechnePrefix(String SubnetzmaskeBin) {
		int i = 0;
		while (i < 32 && SubnetzmaskeBin.charAt(i) == '1') {
			++i;
		}
		return i;
	}

	public boolean getOffen() {
		return offen;
	}

	public void schliessen() {
		shlSubnetzmaskeZuPrefix.close();
	}

	public void inVordergrund() {
		shlSubnetzmaskeZuPrefix.setMinimized(false);
		shlSubnetzmaskeZuPrefix.setActive();
		shlSubnetzmaskeZuPrefix.moveAbove(null);
	}
}
