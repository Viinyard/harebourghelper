package pro.vinyard.dofus;

import java.awt.Point;
import java.io.*;
import java.net.URL;
import java.util.HashMap;


public class Plateau {
	
	private Case[][] tabCase;
	private HashMap<Integer, Case> hmCaseOfColor;
	
	public Plateau(String documentPath) {
		this.tabCase = new Case[27][27];
		this.hmCaseOfColor = new HashMap<Integer, Case>();
		
		this.fillData(documentPath);
	}
	
	private void fillData(String documentPath) {
		try {
			URL url = this.getClass().getClassLoader().getResource(documentPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(documentPath)));
			
			String ligne;
			while((ligne = br.readLine()) != null) {
				String[] seq = ligne.split("%");
				String[] posTab = seq[0].split(":");
				int posX = Integer.valueOf(posTab[0]);
				int posY = Integer.valueOf(posTab[1]);
				int color = Integer.valueOf(seq[1]);
				
				String[] sP = seq[2].split(":");
				Point p = new Point(Integer.valueOf(sP[0]), Integer.valueOf(sP[1]));
				Case c = new Case(p, color, posX, posY);
				this.tabCase[posX][posY] = c;
				
				if(color != 0xffff0000) {
					if(!this.hmCaseOfColor.containsKey(color)) {
						this.hmCaseOfColor.put(color, c);
					}
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Case getCase(int posX, int posY) {
		if(posX < this.tabCase.length && posY < this.tabCase[0].length) {
			return this.tabCase[posX][posY];
		}
		return null;
	}
	
	public Case getCase(int color) {
		return this.hmCaseOfColor.get(color);
	}
}
