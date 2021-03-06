package tools;

import model.Coord;
import model.Couleur;

/**
 * @author francoise.perrin
 *
 */
public enum ChessPiecePos {

	TOURNOIR("Tour", Couleur.NOIR, new Coord[] {new Coord(0,7), new Coord(7,7)}),
	CAVALIERNOIR("Cavalier", Couleur.NOIR, new Coord[] {new Coord(1,7), new Coord(6,7)}), 
	FOUNOIR("Fou", Couleur.NOIR, new Coord[] {new Coord(2,7), new Coord(5,7)}), 
	REINENOIR("Reine", Couleur.NOIR, new Coord[] {new Coord(3,7)}), 
	ROINOIR("Roi", Couleur.NOIR, new Coord[] {new Coord(4,7)}),
	PIONNOIR("PionNoir", Couleur.NOIR,  new Coord[] {new Coord(0,6), new Coord(1,6), new Coord(2,6), new Coord(3,6),
			new Coord(4,6), new Coord(5,6), new Coord(6,6), new Coord(7,6)}),
			
	TOURBLANC("Tour", Couleur.BLANC, new Coord[] {new Coord(0,0), new Coord(7,0)}),
	CAVALIERBLANC("Cavalier", Couleur.BLANC, new Coord[] {new Coord(1,0), new Coord(6,0)}), 
	FOUBLANC("Fou", Couleur.BLANC, new Coord[] {new Coord(2,0), new Coord(5,0)}), 
	REINEBLANC("Reine", Couleur.BLANC, new Coord[] {new Coord(3,0)}), 
	ROIBLANC("Roi", Couleur.BLANC, new Coord[] {new Coord(4,0)}),
	PIONBLANC("PionBlanc",Couleur.BLANC, new Coord[] {new Coord(0,1), new Coord(1,1), new Coord(2,1), new Coord(3,1),
			new Coord(4,1), new Coord(5,1), new Coord(6,1), new Coord(7,1)})   
	; 
	public String nom;
	public Couleur couleur;
	public  Coord[] coords = new Coord[8] ;   

	ChessPiecePos( String nom, Couleur couleur, Coord[] coords) { 
		this.nom = nom;
                this.couleur = couleur;
		this.coords = coords;
	} 
        
        ChessPiecePos( String nom, Coord[] coords) { 
		this.nom = nom;
                this.couleur=Couleur.BLANC;
                this.coords = coords;
	} 


	public static void main(String[] args) {
		for (int i = 0; i < ChessPiecePos.values().length; i++) {
			System.out.print(ChessPiecePos.values()[i].name() + " \t"); 
			System.out.print(ChessPiecePos.values()[i].nom + " \t"); 
			for (int j = 0; j < (ChessPiecePos.values()[i].coords).length; j++) {
				System.out.print(ChessPiecePos.values()[i].coords[j] + " "); 				
			}
			System.out.println();
		}
	}
}

