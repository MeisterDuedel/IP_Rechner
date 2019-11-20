package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class AnzHostsPrefixGui {

	protected Shell shlAnzahlHostsZu;
	private Text AnzahlHosts;
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlAnzahlHostsZu = new Shell();
		shlAnzahlHostsZu.setImage(SWTResourceManager.getImage(AnzHostsPrefixGui.class, "/Icons/IP_Rechner.ico"));
		shlAnzahlHostsZu.setSize(399, 169);
		shlAnzahlHostsZu.setText("Anzahl Hosts zu Prefix");
		shlAnzahlHostsZu.addListener(SWT.Close,new Listener() {
			
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
		btnBerechne.setBounds(30, 64, 324, 26);
		btnBerechne.setText("Berechne Prefix");
		
		Label lblSubnetzmaskePrefix = new Label(shlAnzahlHostsZu, SWT.NONE);
		lblSubnetzmaskePrefix.setBounds(20, 94, 144, 21);
		lblSubnetzmaskePrefix.setText("Subnetzmaske, Prefix:");
		
		txtAusgPrefix = new Text(shlAnzahlHostsZu, SWT.READ_ONLY | SWT.MULTI);
		txtAusgPrefix.setBounds(170, 94, 196, 24);

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

