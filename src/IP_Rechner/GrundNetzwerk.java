package IP_Rechner;

import java.util.ArrayList;
import org.eclipse.swt.widgets.ProgressBar;

public class GrundNetzwerk extends Netzwerk {
	private ArrayList<ArrayList<NetzwerkVerfuegbar>> NetzwerkeVerfuegbar = new ArrayList<ArrayList<NetzwerkVerfuegbar>>();
	private ProgressBar StatusAnzeige; // Statusanzeige aus GUI
	private int IndexThreads; // Damit ein Thread weiﬂ, auf welchen Index er in der ArrayList zugreifen muss

	public GrundNetzwerk(int okt1, int okt2, int okt3, int okt4, int Prefix, ProgressBar StatAnz) {
		super(okt1, okt2, okt3, okt4, Prefix);
		StatusAnzeige = StatAnz;
		ArrayList<Thread> Threads = new ArrayList<Thread>();
		// Erstelle Die ArrayListen (Prefixe) im 2D Array
		for (int i = 0; i < 30 - Prefix; ++i) {
			NetzwerkeVerfuegbar.add(new ArrayList<NetzwerkVerfuegbar>());
		}
		
		
		StatAnz.setMaximum(30 - Prefix);
		for (int i = 0; i < 30 - Prefix; ++i) {
			IndexThreads = i;
			Threads.add(new Thread(new Runnable() {
				int index = IndexThreads;

				@Override
				public void run() {
					long neuesNetzwerkAddr = getNetzwerkAddr();
					while (neuesNetzwerkAddr < getBroadcast()) {
						NetzwerkeVerfuegbar.get(index)
								.add(new NetzwerkVerfuegbar(neuesNetzwerkAddr, Prefix + index + 1));
						neuesNetzwerkAddr += pot(2, 32 - (Prefix + index + 1));
						
					}
					System.out.println("Thread ".concat(Integer.toString(index)));
					//updateStatus();
				}
			}));
			Threads.get(i).start();
		}
		}
		
		/*
		for (int i = 0; i < 30 - Prefix; ++i) {
			try {
				Threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		/*
		for (int i = 0; i< 30-Prefix; ++i) {
			long neuesNetzwerkAddr = getNetzwerkAddr();
			while (neuesNetzwerkAddr < getBroadcast()) {
				NetzwerkeVerfuegbar.get(i)
						.add(new NetzwerkVerfuegbar(neuesNetzwerkAddr, Prefix + i + 1));
				neuesNetzwerkAddr += pot(2, 32 - (Prefix + i + 1));
				
			}
			System.out.println("Thread ".concat(Integer.toString(i)));
		}
		
		System.out.println("fertig");
		
	}
	*/
	synchronized private void updateStatus() {
		StatusAnzeige.setSelection(StatusAnzeige.getSelection() + 1);
	}

}
