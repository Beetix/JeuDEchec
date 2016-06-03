package model;

import java.util.List;


/**
 * @author francoise.perrin
 * 
 * Cette interface défini le comportement attendu 
 * des jeux de plateaux
 *
 */
public interface BoardGames {	

	/**
	 * Permet de deplacer une piece connaissant ses coordonnees initiales 
	 * vers ses coordonnees finales 	 *  
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return OK si deplacement OK	 
	 */
	public boolean move (int xInit, int yInit, int xFinal, int yFinal); 

	/**
	 * @return true si c'est la fin du jeu
	 */
	public boolean isEnd();

	/**
	 * @return un message sur l'état du jeu
	 */
	public String getMessage();

	/**
	 * @return la couleur du jouer courant
	 */
	public Couleur getColorCurrentPlayer();
	
	/**
	 * @param x
	 * @param y
	 * @return la couleur de la pièce sélectionnée
         * @throws model.PieceNotFoundException
	 */
	public Couleur getPieceColor(int x, int y) throws PieceNotFoundException;
 
        
        /**
         * 
         * @param xInit
         * @param yInit
         * @return une liste contenant les cases de déplacement possibles
         */
        public List<Coord> getPossibleMovements(int xInit, int yInit);
        
        /**
         * 
         * @return true if there is a Pion to Upgrade
         */
        public boolean isPionAPromouvoir();
        
        
        /**
         * 
         * @return true if there is a Pion to Upgrade
         */
        public boolean newPiece(String type, int x, int y);
}
