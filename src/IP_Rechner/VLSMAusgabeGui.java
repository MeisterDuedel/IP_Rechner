package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

public class VLSMAusgabeGui {

	protected Shell shlVlsmAusgabe;
	private Text AusgNetzwerk;
	private Text AusgPrefix;
	private Table table;

	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlVlsmAusgabe.open();
		shlVlsmAusgabe.layout();
		while (!shlVlsmAusgabe.isDisposed()) {
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
		shlVlsmAusgabe = new Shell();
		shlVlsmAusgabe.setImage(SWTResourceManager.getImage(VLSMAusgabeGui.class, "/Icons/IP_Rechner.ico"));
		shlVlsmAusgabe.setSize(810, 610);
		shlVlsmAusgabe.setText("VLSM Ausgabe");
		
		Label lblNetzwerk = new Label(shlVlsmAusgabe, SWT.NONE);
		lblNetzwerk.setText("Netzwerkadresse: ");
		lblNetzwerk.setBounds(10, 10, 121, 21);
		
		AusgNetzwerk = new Text(shlVlsmAusgabe, SWT.READ_ONLY | SWT.MULTI);
		AusgNetzwerk.setBounds(134, 10, 120, 24);
		
		Label lblPrefix = new Label(shlVlsmAusgabe, SWT.NONE);
		lblPrefix.setText("Prefix:");
		lblPrefix.setBounds(260, 10, 40, 21);
		
		AusgPrefix = new Text(shlVlsmAusgabe, SWT.READ_ONLY | SWT.MULTI);
		AusgPrefix.setBounds(306, 10, 33, 24);
		
		table = new Table(shlVlsmAusgabe, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(20, 37, 752, 481);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNetzwerkadresse = new TableColumn(table, SWT.NONE);
		tblclmnNetzwerkadresse.setResizable(false);
		tblclmnNetzwerkadresse.setWidth(121);
		tblclmnNetzwerkadresse.setText("Netzwerkadresse");
		
		TableColumn tblclmnSubnetzmaske = new TableColumn(table, SWT.NONE);
		tblclmnSubnetzmaske.setResizable(false);
		tblclmnSubnetzmaske.setText("Subnetzmaske");
		tblclmnSubnetzmaske.setWidth(115);
		
		TableColumn tblclmnPrefix = new TableColumn(table, SWT.NONE);
		tblclmnPrefix.setResizable(false);
		tblclmnPrefix.setWidth(46);
		tblclmnPrefix.setText("Prefix");
		
		TableColumn tblclmnKleinsteHostadresse = new TableColumn(table, SWT.NONE);
		tblclmnKleinsteHostadresse.setResizable(false);
		tblclmnKleinsteHostadresse.setWidth(155);
		tblclmnKleinsteHostadresse.setText("Kleinste Host-Adresse");
		
		TableColumn tblclmnGrteHostadresse = new TableColumn(table, SWT.NONE);
		tblclmnGrteHostadresse.setResizable(false);
		tblclmnGrteHostadresse.setWidth(148);
		tblclmnGrteHostadresse.setText("Gr\u00F6\u00DFte Host-Adresse");
		
		TableColumn tblclmnAnzahlMglicherHosts = new TableColumn(table, SWT.NONE);
		tblclmnAnzahlMglicherHosts.setResizable(false);
		tblclmnAnzahlMglicherHosts.setWidth(165);
		tblclmnAnzahlMglicherHosts.setText("Anzahl m\u00F6glicher Hosts");
		
		Button btnHTML = new Button(shlVlsmAusgabe, SWT.NONE);
		btnHTML.setBounds(29, 524, 732, 26);
		btnHTML.setText("Als HTML Speichern");

	}
}
