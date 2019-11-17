package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class NetzwerkinfosGui {

	protected Shell shlNetzwerkinformationenBerechnen;
	private Text txtAusgabeMinHost;
	private Text txtAusgabeSubnetzmaske;
	private Text txtAusgabeNetzwerk;
	private Text txtAusgabeIP;
	private Text txtAusgabeMaxHost;


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlNetzwerkinformationenBerechnen.open();
		shlNetzwerkinformationenBerechnen.layout();
		while (!shlNetzwerkinformationenBerechnen.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlNetzwerkinformationenBerechnen = new Shell();
		shlNetzwerkinformationenBerechnen.setImage(SWTResourceManager.getImage(NetzwerkinfosGui.class, "/Icons/IP_Rechner.ico"));
		shlNetzwerkinformationenBerechnen.setSize(485, 282);
		shlNetzwerkinformationenBerechnen.setText("Netzwerkinformationen berechnen");
		
		Label lblAufforderung = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblAufforderung.setBounds(10, 10, 414, 21);
		lblAufforderung.setText("Geben Sie eine IP-Adresse und einen Prefix ein:");
		
		Label lblIpadresse = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblIpadresse.setBounds(20, 37, 75, 21);
		lblIpadresse.setText("IP-Adresse:");
		
		Spinner okt1 = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		okt1.setMaximum(255);
		okt1.setBounds(101, 37, 55, 24);
		
		Label label = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		label.setBounds(158, 39, 4, 21);
		label.setText(".");
		
		Spinner okt2 = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		okt2.setMaximum(255);
		okt2.setBounds(162, 37, 55, 24);
		
		Label label_1 = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		label_1.setBounds(218, 39, 4, 21);
		label_1.setText(".");
		
		Spinner okt3 = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		okt3.setMaximum(255);
		okt3.setBounds(223, 37, 55, 24);
		
		Label label_2 = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		label_2.setBounds(280, 39, 4, 21);
		label_2.setText(".");
		
		Spinner okt4 = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		okt4.setMaximum(255);
		okt4.setBounds(284, 37, 55, 24);
		
		Label lblPrefix = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblPrefix.setBounds(350, 37, 40, 21);
		lblPrefix.setText("Prefix:");
		
		Spinner prefix = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		prefix.setMaximum(31);
		prefix.setBounds(396, 37, 47, 24);
		
		Button btnBerechne = new Button(shlNetzwerkinformationenBerechnen, SWT.NONE);
		btnBerechne.setBounds(30, 67, 403, 26);
		btnBerechne.setText("Berechne Netzwerkinformationen");
		
		Label lblIpadresse_1 = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblIpadresse_1.setBounds(20, 99, 75, 21);
		lblIpadresse_1.setText("IP-Adresse:");
		
		Label lblNetzwerkadresse = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblNetzwerkadresse.setBounds(20, 126, 117, 21);
		lblNetzwerkadresse.setText("Netzwerkadresse:");
		
		Label lblSubnetzmaskePrefix = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblSubnetzmaskePrefix.setBounds(20, 153, 149, 21);
		lblSubnetzmaskePrefix.setText("Subnetzmaske, Prefix:");
		
		Label lblKleinsteHostadresse = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblKleinsteHostadresse.setBounds(20, 180, 150, 21);
		lblKleinsteHostadresse.setText("Kleinste Host-Adresse:");
		
		Label lblGrteHostadresse = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblGrteHostadresse.setBounds(20, 207, 143, 21);
		lblGrteHostadresse.setText("Gr\u00F6\u00DFte Host-Adresse:");
		
		txtAusgabeIP = new Text(shlNetzwerkinformationenBerechnen,SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeIP.setBounds(174, 99, 269, 24);
		
		txtAusgabeNetzwerk = new Text(shlNetzwerkinformationenBerechnen,SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeNetzwerk.setBounds(174, 126, 269, 24);
		
		txtAusgabeSubnetzmaske = new Text(shlNetzwerkinformationenBerechnen,SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeSubnetzmaske.setBounds(174, 153, 269, 24);
		
		txtAusgabeMinHost = new Text(shlNetzwerkinformationenBerechnen,SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeMinHost.setBounds(174, 180, 269, 24);
		
		txtAusgabeMaxHost = new Text(shlNetzwerkinformationenBerechnen, SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeMaxHost.setBounds(174, 207, 269, 24);
		
		

	}
}
