package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

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
		shlNetzwerkinformationenBerechnen.setSize(436, 238);
		shlNetzwerkinformationenBerechnen.setText("Netzwerkinformationen berechnen");
		
		Label lblGebenSieEine = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblGebenSieEine.setBounds(10, 10, 414, 15);
		lblGebenSieEine.setText("Geben Sie eine IP-Adresse und einen Prefix ein:");
		
		Label lblIpadresse = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblIpadresse.setBounds(20, 34, 64, 15);
		lblIpadresse.setText("IP-Adresse:");
		
		Spinner okt1 = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		okt1.setMaximum(255);
		okt1.setBounds(90, 31, 47, 22);
		
		Label label = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		label.setBounds(138, 38, 5, 15);
		label.setText(".");
		
		Spinner okt2 = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		okt2.setMaximum(255);
		okt2.setBounds(143, 31, 47, 22);
		
		Label label_1 = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		label_1.setBounds(191, 38, 5, 15);
		label_1.setText(".");
		
		Spinner okt3 = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		okt3.setMaximum(255);
		okt3.setBounds(196, 31, 47, 22);
		
		Label label_2 = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		label_2.setBounds(244, 38, 5, 15);
		label_2.setText(".");
		
		Spinner okt4 = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		okt4.setMaximum(255);
		okt4.setBounds(249, 31, 47, 22);
		
		Label lblPrefix = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblPrefix.setBounds(307, 34, 38, 15);
		lblPrefix.setText("Prefix:");
		
		Spinner prefix = new Spinner(shlNetzwerkinformationenBerechnen, SWT.BORDER);
		prefix.setMaximum(31);
		prefix.setBounds(351, 31, 47, 22);
		
		Button btnNewButton = new Button(shlNetzwerkinformationenBerechnen, SWT.NONE);
		btnNewButton.setBounds(30, 59, 358, 25);
		btnNewButton.setText("Berechne Netzwerkinformationen");
		
		Label lblIpadresse_1 = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblIpadresse_1.setBounds(20, 90, 59, 15);
		lblIpadresse_1.setText("IP-Adresse:");
		
		Label lblNetzwerkadresse = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblNetzwerkadresse.setBounds(20, 111, 91, 15);
		lblNetzwerkadresse.setText("Netzwerkadresse:");
		
		Label lblSubnetzmaskePrefix = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblSubnetzmaskePrefix.setBounds(20, 132, 115, 15);
		lblSubnetzmaskePrefix.setText("Subnetzmaske, Prefix:");
		
		Label lblKleinsteHostadresse = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblKleinsteHostadresse.setBounds(20, 153, 118, 15);
		lblKleinsteHostadresse.setText("Kleinste Host-Adresse:");
		
		Label lblGrteHostadresse = new Label(shlNetzwerkinformationenBerechnen, SWT.NONE);
		lblGrteHostadresse.setBounds(20, 174, 117, 15);
		lblGrteHostadresse.setText("Gr\u00F6\u00DFte Host-Adresse:");
		
		txtAusgabeIP = new Text(shlNetzwerkinformationenBerechnen, SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeIP.setBounds(148, 90, 250, 21);
		
		txtAusgabeNetzwerk = new Text(shlNetzwerkinformationenBerechnen, SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeNetzwerk.setBounds(148, 111, 250, 21);
		
		txtAusgabeSubnetzmaske = new Text(shlNetzwerkinformationenBerechnen, SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeSubnetzmaske.setBounds(148, 132, 250, 21);
		
		txtAusgabeMinHost = new Text(shlNetzwerkinformationenBerechnen, SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeMinHost.setBounds(148, 153, 250, 21);
		
		txtAusgabeMaxHost = new Text(shlNetzwerkinformationenBerechnen, SWT.READ_ONLY | SWT.MULTI);
		txtAusgabeMaxHost.setBounds(148, 174, 250, 21);
		
		

	}
}
