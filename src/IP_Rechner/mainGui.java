package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class mainGui {

	protected Shell shlIpRechner;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mainGui window = new mainGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlIpRechner.open();
		shlIpRechner.layout();
		while (!shlIpRechner.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlIpRechner = new Shell();
		shlIpRechner.setImage(SWTResourceManager.getImage(mainGui.class, "/Icons/IP_Rechner.ico"));
		shlIpRechner.setSize(450, 220);
		shlIpRechner.setText("IP Rechner");
		
		Label lblAufforderung = new Label(shlIpRechner, SWT.NONE);
		lblAufforderung.setBounds(10, 10, 414, 21);
		lblAufforderung.setText("W\u00E4hlen Sie eine Aktion aus:");
		
		Button btnNetzwerkinformationenBerechnen = new Button(shlIpRechner, SWT.NONE);
		btnNetzwerkinformationenBerechnen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NetzwerkinfosGui Infos = new NetzwerkinfosGui();
				Infos.open();
			}
		});
		btnNetzwerkinformationenBerechnen.setBounds(90, 37, 250, 26);
		btnNetzwerkinformationenBerechnen.setText("Netzwerkinformationen berechnen");
		
		Button btnVlsm = new Button(shlIpRechner, SWT.NONE);
		btnVlsm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				VLSMGui VLSM = new VLSMGui();
				VLSM.open();
			}
		});
		btnVlsm.setBounds(90, 69, 250, 26);
		btnVlsm.setText("VLSM");
		
		Button btnSubnetzmaskePrefix = new Button(shlIpRechner, SWT.NONE);
		btnSubnetzmaskePrefix.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SubnetzPrefixGui SubPre = new SubnetzPrefixGui();
				SubPre.open();
			}
		});
		btnSubnetzmaskePrefix.setBounds(90, 101, 250, 26);
		btnSubnetzmaskePrefix.setText("Subnetzmaske -> Prefix");
		
		Button btnAnzahlHosts = new Button(shlIpRechner, SWT.NONE);
		btnAnzahlHosts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AnzHostsPrefixGui HostsPre = new AnzHostsPrefixGui();
				HostsPre.open();
			}
		});
		btnAnzahlHosts.setBounds(90, 133, 250, 26);
		btnAnzahlHosts.setText("Anzahl Hosts -> Prefix");

	}
}
