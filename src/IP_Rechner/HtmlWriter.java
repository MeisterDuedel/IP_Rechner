package IP_Rechner;

import java.io.PrintWriter;
import java.util.ArrayList;

public class HtmlWriter {
	String Pfad; // Pfad, an dem die HTML Datei gespeichert werden soll
	private ArrayList<ArrayList<Netzwerk>> Subnetze;
	private ArrayList<Netzwerk> FreieSubnetze;
	private String NetzwerkadresseGrundnetzwerk;
	private int PrefixGrundnetzwerk;

	public HtmlWriter(String pfad, ArrayList<ArrayList<Netzwerk>> SubAusgewaehlt, ArrayList<Netzwerk> SubFrei,
			String NetzwerkAddrGrund, int PrefixGrund) {
		Pfad = pfad;
		Subnetze = SubAusgewaehlt;
		FreieSubnetze = SubFrei;
		NetzwerkadresseGrundnetzwerk = NetzwerkAddrGrund;
		PrefixGrundnetzwerk = PrefixGrund;
	}

	// Schreibt die HTML Datei
	public boolean schreibe() {
		try {
			PrintWriter writer = new PrintWriter(Pfad, "UTF-8");

			// Schreibe Kopf
			writer.print(
					"<!DOCTYPE HTML>\n<!--HTML-Ausgabe VLSM Subnetting (generiert durch IP_Rechner)\nGeneriert am: ");
			writer.println(new java.util.Date());
			writer.println(
					"Template zuletzt ge�ndert: 29.11.2019\nTemplate erstellt: 29.11.2019\nTemplate Autor: Christoph Pircher-->");

			// Schreibe HTML File bis zur Ersten Tabelle (Netzwerkadresse und Prefix vom
			// Grundnetzwerk werden eingef�gt!)
			writer.print("<html lang=\"de\">\r\n" + "<head>\r\n" + "<title>" + NetzwerkadresseGrundnetzwerk + ", /"
					+ PrefixGrundnetzwerk + "</title>\r\n"
					+ "<meta charset=\"utf-8\" name=\"vierport\" content=\"width=device-width,initial-scale=1.0\">\r\n"
					+ "<style type=\"text/css\">\r\n" + "html{\r\n" + "font-family: Aral, Verdana, Helvetica;\r\n"
					+ "font-size: 12pt;\r\n" + "width: 100%;\r\n" + "}\r\n" + "\r\n" + "body{\r\n"
					+ "padding-left: 1em;\r\n" + "padding-right: 1em;\r\n" + "}\r\n" + "table{\r\n"
					+ "border: 1px solid black;\r\n" + "border-collapse: separate;\r\n" + "border-spacing: 0em;\r\n"
					+ "}\r\n" + "th,td{\r\n" + "border:1px solid black;\r\n" + "padding-left: 0.5em;\r\n"
					+ "padding-right: 0.5em;\r\n" + "}\r\n" + "\r\n" + "@media print{   \r\n" + "html{\r\n"
					+ "font-size: 11pt;\r\n" + "margin: 0;\r\n" + "}\r\n" + "body{\r\n" + "padding: 0;\r\n" + "}\r\n"
					+ "h2{\r\n" + "text-align: center;\r\n" + "}\r\n" + "}\r\n" + "\r\n" + "</style>\r\n"
					+ "</head>\r\n" + "<body>\r\n" + "<h2>VLSM Subnetting</h2>\r\n" + "<b>Netzwerkadresse: </b>");
			writer.print(NetzwerkadresseGrundnetzwerk); // Netzwerkadresse des Grundnetzwerks einf�gen
			writer.print("<b> Prefix: </b>");
			writer.print(PrefixGrundnetzwerk); // Prefix des Grundnetzwerks einf�gen
			writer.println("<br><br>Belegte Subnetze:\r\n" + "<table>\r\n"
					+ "<tr><th>Netzwerk-Adresse</th><th>Subnetzmaske</th><th>Prefix</th><th>Broadcast-Adresse</th>"
					+ "<th>Kleinste Host-Adresse</th><th>Gr��te Host-Adresse</th><th>Anzahl m�glicher Hosts</th></tr>");

			// Schreibe Belegte Subnetze
			for (int i = 0; i < Subnetze.size(); ++i) {
				writer.print("<tr>");
				for (int k = 0; k < Subnetze.get(i).size(); ++k) {
					writer.print("<td>" + Subnetze.get(i).get(k).getNetzwerkAddrDD() + "</td>");
					writer.print("<td>" + Subnetze.get(i).get(k).getSubnetzmaskeDD() + "</td>");
					writer.print("<td>" + "/" + Subnetze.get(i).get(k).getPrefix() + "</td>");
					writer.print("<td>" + Subnetze.get(i).get(k).getBroadcastDD() + "</td>");
					writer.print("<td>" + Subnetze.get(i).get(k).getMinHostDD() + "</td>");
					writer.print("<td>" + Subnetze.get(i).get(k).getMaxHostDD() + "</td>");
					writer.print("<td>" + Long.toString(pot(2, 32 - Subnetze.get(i).get(k).getPrefix())) + "</td>");
				}
				writer.println("</tr>");
			}

			writer.print("</table><br>\r\n" + "Freie Subnetze (Zusammengefasst):\r\n" + "<table>");

			// Schreibe Freie Subnetze
			for (int i = 0; i < FreieSubnetze.size(); ++i) {
				writer.print("<td>" + FreieSubnetze.get(i).getNetzwerkAddrDD() + "</td>");
				writer.print("<td>" + FreieSubnetze.get(i).getSubnetzmaskeDD() + "</td>");
				writer.print("<td>/" + FreieSubnetze.get(i).getPrefix() + "</td>");
				writer.print("<td>" + FreieSubnetze.get(i).getMinHostDD() + "</td>");
				writer.print("<td>" + FreieSubnetze.get(i).getMaxHostDD() + "</td>");
				writer.print("<td>" + Long.toString(pot(2, 32 - FreieSubnetze.get(i).getPrefix())) + "</td>");
			}

			writer.print("</table><br>\r\n" + "</body>\r\n" + "</html>");

			writer.close();
		} catch (Exception e) {
			// Wenn schreiben fehlschl�gt
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Funktion f�r Potenzrechnung
	private long pot(long Basis, int Exponent) {
		long Zahl = Basis;
		if (Exponent == 0) {
			Zahl = 1;
		} else {
			for (int i = 1; i < Exponent; ++i) {
				Zahl *= Basis;
			}
		}
		return Zahl;
	}

}
