package IP_Rechner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class SubnetzPrefixGui {

	protected Shell shlSubnetzmaskeZuPrefix;
	private Text txtAusgPrefix;


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSubnetzmaskeZuPrefix.open();
		shlSubnetzmaskeZuPrefix.layout();
		while (!shlSubnetzmaskeZuPrefix.isDisposed()) {
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
		shlSubnetzmaskeZuPrefix = new Shell();
		shlSubnetzmaskeZuPrefix.setImage(SWTResourceManager.getImage(SubnetzPrefixGui.class, "/Icons/IP_Rechner.ico"));
		shlSubnetzmaskeZuPrefix.setText("Subnetzmaske zu Prefix");
		shlSubnetzmaskeZuPrefix.setSize(403, 174);

		
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
		btnBerechne.setBounds(30, 64, 324, 26);
		btnBerechne.setText("Berechne Prefix");
		
		Label lblSubnetzmaskePrefix = new Label(shlSubnetzmaskeZuPrefix, SWT.NONE);
		lblSubnetzmaskePrefix.setBounds(20, 96, 144, 21);
		lblSubnetzmaskePrefix.setText("Subnetzmaske, Prefix:");
		
		txtAusgPrefix = new Text(shlSubnetzmaskeZuPrefix, SWT.READ_ONLY | SWT.MULTI);
		txtAusgPrefix.setBounds(170, 96, 194, 24);
		
	}
}
