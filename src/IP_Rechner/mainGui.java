package IP_Rechner;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class mainGui {

	protected Shell shlIprechner;

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
		shlIprechner.open();
		shlIprechner.layout();
		while (!shlIprechner.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlIprechner = new Shell();
		shlIprechner.setSize(450, 250);
		shlIprechner.setText("IP Rechner");
		
		Button btnNetInfo = new Button(shlIprechner, SWT.NONE);
		btnNetInfo.setBounds(100, 31, 250, 34);
		btnNetInfo.setText("Netzwerkinformationen berechnen");
		
		Button btnVLSM = new Button(shlIprechner, SWT.NONE);
		btnVLSM.setBounds(100, 71, 250, 34);
		btnVLSM.setText("VLSM");
		
		Button btnNewButton = new Button(shlIprechner, SWT.NONE);
		btnNewButton.setBounds(100, 111, 250, 34);
		btnNewButton.setText("Subnetzmaske -> Prefix");
		
		Button btnNewButton_1 = new Button(shlIprechner, SWT.NONE);
		btnNewButton_1.setBounds(100, 151, 250, 34);
		btnNewButton_1.setText("Anzahl Hosts -> Prefix");
		
		Label lblNewLabel = new Label(shlIprechner, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 382, 15);
		lblNewLabel.setText("Bitte wählen Sie eine Aktion aus:");
		
		Label lblNewLabel_1 = new Label(shlIprechner, SWT.NONE);
		lblNewLabel_1.setBounds(10, 192, 382, 15);
		lblNewLabel_1.setText("(C) 2019 Christoph Pircher");

	}
}
