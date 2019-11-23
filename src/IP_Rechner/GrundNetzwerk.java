package IP_Rechner;

import java.util.ArrayList;

public class GrundNetzwerk extends Netzwerk {
	private ArrayList<ArrayList<NetzwerkVerfuegbar>> NetzwerkeVerfuegbar = new ArrayList<ArrayList<NetzwerkVerfuegbar>>();
	private int IndexThreads; // Damit ein Thread weiﬂ, auf welchen Index er in der ArrayList zugreifen muss

	public GrundNetzwerk(int okt1, int okt2, int okt3, int okt4, int Prefix) {
		super(okt1, okt2, okt3, okt4, Prefix);
		ArrayList<Thread> Threads = new ArrayList<Thread>();
		// Erstelle Die ArrayListen (Prefixe) im 2D Array
		for (int i = 0; i < 30 - Prefix; ++i) {
			NetzwerkeVerfuegbar.add(new ArrayList<NetzwerkVerfuegbar>());

		}
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

				}
			}));
			Threads.get(i).start();
		}

		// System.out.println("fertig");

	}

}
