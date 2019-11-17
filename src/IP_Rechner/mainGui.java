package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
		shlIpRechner.setSize(450, 241);
		shlIpRechner.setText("IP Rechner");
		
		Label lblBiteWhlenSie = new Label(shlIpRechner, SWT.NONE);
		lblBiteWhlenSie.setBounds(10, 10, 414, 21);
		lblBiteWhlenSie.setText("W\u00E4hlen Sie eine Aktion aus:");
		
		Button btnNetzwerkinformationenBerechnen = new Button(shlIpRechner, SWT.NONE);
		btnNetzwerkinformationenBerechnen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NetzwerkinfosGui Infos = new NetzwerkinfosGui();
				Infos.open();
			}
		});
		btnNetzwerkinformationenBerechnen.setBounds(90, 37, 250, 25);
		btnNetzwerkinformationenBerechnen.setText("Netzwerkinformationen berechnen");
		
		Button btnVlsm = new Button(shlIpRechner, SWT.NONE);
		btnVlsm.setBounds(90, 68, 250, 25);
		btnVlsm.setText("VLSM");
		
		Button btnSubnetzmaskePrefix = new Button(shlIpRechner, SWT.NONE);
		btnSubnetzmaskePrefix.setBounds(90, 99, 250, 25);
		btnSubnetzmaskePrefix.setText("Subnetzmaske -> Prefix");
		
		Button btnAnzahlHosts = new Button(shlIpRechner, SWT.NONE);
		btnAnzahlHosts.setBounds(90, 130, 250, 25);
		btnAnzahlHosts.setText("Anzahl Hosts -> Prefix");
		
		Label lblChristoph = new Label(shlIpRechner, SWT.NONE);
		lblChristoph.setBounds(10, 161, 414, 21);
		lblChristoph.setText("\u00A9 2019 Christoph Pircher");

	}
}
