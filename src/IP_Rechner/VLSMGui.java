package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ProgressBar;

public class VLSMGui {

	protected Shell shlVlsmAuswahl;
	private Label lblAdresse;
	private Spinner UplinkOkt1;
	private Spinner UplinkOkt2;
	private Spinner UplinkOkt3;
	private Spinner UplinkOkt4;
	private Label lblPrefixUplink;
	private Spinner UplinkPrefix;
	private Button btnUplinkDefinitivHost;
	private Text AusgGrundNetzwerkAddr;
	private Text AugPefixGrund;
	private Table table;
	private Text AusgNetzUplink;
	private Text AusgabePrefixUplink;
	private Label lblUplinknetzwerkadresse;
	private Label lblPrefixUplink_1;
	private ProgressBar Statusanzeige;
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlVlsmAuswahl.open();
		shlVlsmAuswahl.layout();
		while (!shlVlsmAuswahl.isDisposed()) {
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
		shlVlsmAuswahl = new Shell();
		shlVlsmAuswahl.setImage(SWTResourceManager.getImage(VLSMGui.class, "/Icons/IP_Rechner.ico"));
		shlVlsmAuswahl.setSize(662, 604);
		shlVlsmAuswahl.setText("VLSM Auswahl");
		
		Label lblAufforderung = new Label(shlVlsmAuswahl, SWT.NONE);
		lblAufforderung.setBounds(10, 10, 626, 21);
		lblAufforderung.setText("Geben Sie die Netzwerkadresse oder eine IP Adresse und den Prefix des Grundnetzwerks an:");
		
		Label lblNetzwerkipadresse = new Label(shlVlsmAuswahl, SWT.NONE);
		lblNetzwerkipadresse.setBounds(20, 37, 149, 21);
		lblNetzwerkipadresse.setText("Netzwerk-/IP-Adresse:");
		
		
		Spinner NetzwerkOkt1 = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		NetzwerkOkt1.setMaximum(255);
		NetzwerkOkt1.setBounds(175, 37, 55, 24);
		
		Label label = new Label(shlVlsmAuswahl, SWT.NONE);
		label.setBounds(232, 39, 4, 21);
		label.setText(".");
		
		Spinner NetzwerkOkt2 = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		NetzwerkOkt2.setMaximum(255);
		NetzwerkOkt2.setBounds(236, 37, 55, 24);
		
		Label label_1 = new Label(shlVlsmAuswahl, SWT.NONE);
		label_1.setBounds(292, 39, 4, 21);
		label_1.setText(".");
		
		Spinner NetzwerkOkt3 = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		NetzwerkOkt3.setMaximum(255);
		NetzwerkOkt3.setBounds(297, 37, 55, 24);
		
		Label label_2 = new Label(shlVlsmAuswahl, SWT.NONE);
		label_2.setBounds(354, 39, 4, 21);
		label_2.setText(".");
		
		Spinner NetzwerkOkt4 = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		NetzwerkOkt4.setMaximum(255);
		NetzwerkOkt4.setBounds(358, 37, 55, 24);
		
		Label lblPrefix = new Label(shlVlsmAuswahl, SWT.NONE);
		lblPrefix.setBounds(419, 37, 40, 21);
		lblPrefix.setText("Prefix:");
		
		Spinner NetzwerkPrefix = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		NetzwerkPrefix.setMaximum(29);
		NetzwerkPrefix.setBounds(465, 37, 47, 24);
		
		Button btnUplink = new Button(shlVlsmAuswahl, SWT.CHECK);
		btnUplink.setBounds(10, 64, 209, 21);
		btnUplink.setText("Uplink Netzwerk reservieren");
		btnUplink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//System.out.println("test");
				if(btnUplink.getSelection()) {
					lblAdresse.setEnabled(true);
					UplinkOkt1.setEnabled(true);
					UplinkOkt2.setEnabled(true);
					UplinkOkt3.setEnabled(true);
					UplinkOkt4.setEnabled(true);
					lblPrefixUplink.setEnabled(true);
					UplinkPrefix.setEnabled(true);
					btnUplinkDefinitivHost.setEnabled(true);
					lblUplinknetzwerkadresse.setEnabled(true);
					lblPrefixUplink_1.setEnabled(true);
				}else {
					lblAdresse.setEnabled(false);
					UplinkOkt1.setEnabled(false);
					UplinkOkt2.setEnabled(false);
					UplinkOkt3.setEnabled(false);
					UplinkOkt4.setEnabled(false);
					lblPrefixUplink.setEnabled(false);
					UplinkPrefix.setEnabled(false);
					btnUplinkDefinitivHost.setEnabled(false);
					lblUplinknetzwerkadresse.setEnabled(false);
					lblPrefixUplink_1.setEnabled(false);
				}
			}
		});
		
		lblAdresse = new Label(shlVlsmAuswahl, SWT.NONE);
		lblAdresse.setEnabled(false);
		lblAdresse.setBounds(20, 91, 55, 21);
		lblAdresse.setText("Adresse:");
		
		UplinkOkt1 = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		UplinkOkt1.setEnabled(false);
		UplinkOkt1.setMaximum(255);
		UplinkOkt1.setBounds(81, 91, 55, 24);
		
		Label labelp = new Label(shlVlsmAuswahl, SWT.NONE);
		labelp.setBounds(138, 93, 4, 21);
		labelp.setText(".");
		
		UplinkOkt2 = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		UplinkOkt2.setEnabled(false);
		UplinkOkt2.setMaximum(255);
		UplinkOkt2.setBounds(142, 91, 55, 24);
		
		Label labelp_1 = new Label(shlVlsmAuswahl, SWT.NONE);
		labelp_1.setBounds(198, 93, 4, 21);
		labelp_1.setText(".");
		
		UplinkOkt3 = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		UplinkOkt3.setEnabled(false);
		UplinkOkt3.setMaximum(255);
		UplinkOkt3.setBounds(203, 91, 55, 24);
		
		Label labelp_2 = new Label(shlVlsmAuswahl, SWT.NONE);
		labelp_2.setBounds(260, 93, 4, 21);
		labelp_2.setText(".");
		
		UplinkOkt4 = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		UplinkOkt4.setEnabled(false);
		UplinkOkt4.setMaximum(255);
		UplinkOkt4.setBounds(264, 91, 55, 24);
		
		lblPrefixUplink = new Label(shlVlsmAuswahl, SWT.NONE);
		lblPrefixUplink.setEnabled(false);
		lblPrefixUplink.setBounds(330, 91, 168, 21);
		lblPrefixUplink.setText("Prefix (0=kleinstm\u00F6glich):");
		
		UplinkPrefix = new Spinner(shlVlsmAuswahl, SWT.BORDER);
		UplinkPrefix.setEnabled(false);
		UplinkPrefix.setMaximum(30);
		UplinkPrefix.setBounds(504, 91, 47, 24);
		
		btnUplinkDefinitivHost = new Button(shlVlsmAuswahl, SWT.CHECK);
		btnUplinkDefinitivHost.setEnabled(false);
		btnUplinkDefinitivHost.setBounds(81, 121, 309, 21);
		btnUplinkDefinitivHost.setText("Adresse ist DEFINITIV eine Host-IP-Adresse");
		
		Button btnBerechneSubnetze = new Button(shlVlsmAuswahl, SWT.NONE);
		btnBerechneSubnetze.setBounds(30, 148, 586, 26);
		btnBerechneSubnetze.setText("Berechne verf\u00FCgbare Subnetze");
		
		Label lblNetzwerkadresse = new Label(shlVlsmAuswahl, SWT.NONE);
		lblNetzwerkadresse.setBounds(20, 180, 121, 21);
		lblNetzwerkadresse.setText("Netzwerkadresse: ");
		
		AusgGrundNetzwerkAddr = new Text(shlVlsmAuswahl, SWT.READ_ONLY | SWT.MULTI);
		AusgGrundNetzwerkAddr.setBounds(144, 180, 120, 24);
		
		Label lblPrefix_1 = new Label(shlVlsmAuswahl, SWT.NONE);
		lblPrefix_1.setBounds(270, 180, 40, 21);
		lblPrefix_1.setText("Prefix:");
		
		AugPefixGrund = new Text(shlVlsmAuswahl, SWT.READ_ONLY | SWT.MULTI);
		AugPefixGrund.setBounds(316, 180, 33, 24);
		
		table = new Table(shlVlsmAuswahl, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 234, 606, 254);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnPrefix = new TableColumn(table, SWT.NONE);
		tblclmnPrefix.setResizable(false);
		tblclmnPrefix.setWidth(44);
		tblclmnPrefix.setText("Prefix");
		
		TableColumn tblclmnAnzahlMglicherHosts = new TableColumn(table, SWT.NONE);
		tblclmnAnzahlMglicherHosts.setResizable(false);
		tblclmnAnzahlMglicherHosts.setWidth(163);
		tblclmnAnzahlMglicherHosts.setText("Anzahl m\u00F6glicher Hosts");
		
		TableColumn tblclmnAnzahlVerfgbar = new TableColumn(table, SWT.NONE);
		tblclmnAnzahlVerfgbar.setResizable(false);
		tblclmnAnzahlVerfgbar.setWidth(122);
		tblclmnAnzahlVerfgbar.setText("Anzahl Verf\u00FCgbar");
		
		TableColumn tblclmnAnzahlAusgewhlt = new TableColumn(table, SWT.NONE);
		tblclmnAnzahlAusgewhlt.setResizable(false);
		tblclmnAnzahlAusgewhlt.setWidth(136);
		tblclmnAnzahlAusgewhlt.setText("Anzahl Ausgew\u00E4hlt");
		
		lblUplinknetzwerkadresse = new Label(shlVlsmAuswahl, SWT.NONE);
		lblUplinknetzwerkadresse.setEnabled(false);
		lblUplinknetzwerkadresse.setText("Uplink-Netzwerkadresse: ");
		lblUplinknetzwerkadresse.setBounds(20, 207, 168, 21);
		
		AusgNetzUplink = new Text(shlVlsmAuswahl, SWT.READ_ONLY | SWT.MULTI);
		AusgNetzUplink.setBounds(192, 208, 120, 24);
		
		lblPrefixUplink_1 = new Label(shlVlsmAuswahl, SWT.NONE);
		lblPrefixUplink_1.setEnabled(false);
		lblPrefixUplink_1.setText("Prefix:");
		lblPrefixUplink_1.setBounds(312, 207, 40, 21);
		
		AusgabePrefixUplink = new Text(shlVlsmAuswahl, SWT.READ_ONLY | SWT.MULTI);
		AusgabePrefixUplink.setBounds(357, 208, 33, 24);
		
		Button btnHinzfg = new Button(shlVlsmAuswahl, SWT.NONE);
		btnHinzfg.setBounds(30, 494, 191, 26);
		btnHinzfg.setText("1x Zur Auswahl hinzuf\u00FCgen");
		
		Button btnEntfernen = new Button(shlVlsmAuswahl, SWT.NONE);
		btnEntfernen.setBounds(227, 494, 191, 26);
		btnEntfernen.setText("1x Aus Auswahl entfernen");
		
		Button btnAusgabe = new Button(shlVlsmAuswahl, SWT.NONE);
		btnAusgabe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				VLSMAusgabeGui Ausgabe = new VLSMAusgabeGui();
				Ausgabe.open();
			}
		});
		btnAusgabe.setBounds(425, 494, 191, 26);
		btnAusgabe.setText("Subnetze Ausgeben");
		
		Statusanzeige = new ProgressBar(shlVlsmAuswahl, SWT.NONE);
		Statusanzeige.setBounds(20, 526, 606, 22);

	}
}
