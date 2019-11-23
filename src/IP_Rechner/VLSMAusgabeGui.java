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
	private Table tableSubnetze;
	private Table tableUnbelegt;

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
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlVlsmAusgabe = new Shell();
		shlVlsmAusgabe.setImage(SWTResourceManager.getImage(VLSMAusgabeGui.class, "/Icons/IP_Rechner.ico"));
		shlVlsmAusgabe.setSize(934, 669);
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

		tableSubnetze = new Table(shlVlsmAusgabe, SWT.BORDER | SWT.FULL_SELECTION);
		tableSubnetze.setBounds(20, 37, 878, 366);
		tableSubnetze.setHeaderVisible(true);
		tableSubnetze.setLinesVisible(true);

		TableColumn tblclmnNetzwerkadresseSub = new TableColumn(tableSubnetze, SWT.NONE);
		tblclmnNetzwerkadresseSub.setResizable(false);
		tblclmnNetzwerkadresseSub.setWidth(121);
		tblclmnNetzwerkadresseSub.setText("Netzwerkadresse");

		TableColumn tblclmnSubnetzmaskeSub = new TableColumn(tableSubnetze, SWT.NONE);
		tblclmnSubnetzmaskeSub.setResizable(false);
		tblclmnSubnetzmaskeSub.setText("Subnetzmaske");
		tblclmnSubnetzmaskeSub.setWidth(115);

		TableColumn tblclmnPrefixSub = new TableColumn(tableSubnetze, SWT.NONE);
		tblclmnPrefixSub.setResizable(false);
		tblclmnPrefixSub.setWidth(46);
		tblclmnPrefixSub.setText("Prefix");

		TableColumn tblclmnBroadcastadresseSub = new TableColumn(tableSubnetze, SWT.NONE);
		tblclmnBroadcastadresseSub.setResizable(false);
		tblclmnBroadcastadresseSub.setWidth(126);
		tblclmnBroadcastadresseSub.setText("Broadcastadresse");

		TableColumn tblclmnKleinsteHostadresseSub = new TableColumn(tableSubnetze, SWT.NONE);
		tblclmnKleinsteHostadresseSub.setResizable(false);
		tblclmnKleinsteHostadresseSub.setWidth(155);
		tblclmnKleinsteHostadresseSub.setText("Kleinste Host-Adresse");

		TableColumn tblclmnGroessteHostadresseSub = new TableColumn(tableSubnetze, SWT.NONE);
		tblclmnGroessteHostadresseSub.setResizable(false);
		tblclmnGroessteHostadresseSub.setWidth(148);
		tblclmnGroessteHostadresseSub.setText("Gr\u00F6\u00DFte Host-Adresse");

		TableColumn tblclmnAnzahlMoeglicherHostsSub = new TableColumn(tableSubnetze, SWT.NONE);
		tblclmnAnzahlMoeglicherHostsSub.setResizable(false);
		tblclmnAnzahlMoeglicherHostsSub.setWidth(165);
		tblclmnAnzahlMoeglicherHostsSub.setText("Anzahl m\u00F6glicher Hosts");

		Label lblUnbelegteNetzwerke = new Label(shlVlsmAusgabe, SWT.NONE);
		lblUnbelegteNetzwerke.setBounds(10, 409, 148, 21);
		lblUnbelegteNetzwerke.setText("Unbelegte Netzwerke:");

		tableUnbelegt = new Table(shlVlsmAusgabe, SWT.BORDER | SWT.FULL_SELECTION);
		tableUnbelegt.setLinesVisible(true);
		tableUnbelegt.setHeaderVisible(true);
		tableUnbelegt.setBounds(20, 436, 878, 142);

		TableColumn tableclmnNetzwerkadresseUnb = new TableColumn(tableUnbelegt, SWT.NONE);
		tableclmnNetzwerkadresseUnb.setWidth(121);
		tableclmnNetzwerkadresseUnb.setText("Netzwerkadresse");
		tableclmnNetzwerkadresseUnb.setResizable(false);

		TableColumn tableclmnSubnetzmaskeUnb = new TableColumn(tableUnbelegt, SWT.NONE);
		tableclmnSubnetzmaskeUnb.setWidth(115);
		tableclmnSubnetzmaskeUnb.setText("Subnetzmaske");
		tableclmnSubnetzmaskeUnb.setResizable(false);

		TableColumn tableclmnPrefixUnb = new TableColumn(tableUnbelegt, SWT.NONE);
		tableclmnPrefixUnb.setWidth(46);
		tableclmnPrefixUnb.setText("Prefix");
		tableclmnPrefixUnb.setResizable(false);

		TableColumn tableclmnBroadcastadresseUnb = new TableColumn(tableUnbelegt, SWT.NONE);
		tableclmnBroadcastadresseUnb.setWidth(126);
		tableclmnBroadcastadresseUnb.setText("Broadcastadresse");
		tableclmnBroadcastadresseUnb.setResizable(false);

		TableColumn tableclmnKleinsteHostAdresseUnb = new TableColumn(tableUnbelegt, SWT.NONE);
		tableclmnKleinsteHostAdresseUnb.setWidth(155);
		tableclmnKleinsteHostAdresseUnb.setText("Kleinste Host-Adresse");
		tableclmnKleinsteHostAdresseUnb.setResizable(false);

		TableColumn tableclmnGroessteHostAdresseUnb = new TableColumn(tableUnbelegt, SWT.NONE);
		tableclmnGroessteHostAdresseUnb.setWidth(148);
		tableclmnGroessteHostAdresseUnb.setText("Gr\u00F6\u00DFte Host-Adresse");
		tableclmnGroessteHostAdresseUnb.setResizable(false);

		TableColumn tableclmnManzahlMoeglicherHostsUnb = new TableColumn(tableUnbelegt, SWT.NONE);
		tableclmnManzahlMoeglicherHostsUnb.setWidth(165);
		tableclmnManzahlMoeglicherHostsUnb.setText("Anzahl m\u00F6glicher Hosts");
		tableclmnManzahlMoeglicherHostsUnb.setResizable(false);

		Button btnHTML = new Button(shlVlsmAusgabe, SWT.NONE);
		btnHTML.setBounds(30, 584, 859, 26);
		btnHTML.setText("Als HTML Speichern");

	}
}
