package model.observable;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.BoardGames;
import model.Coord;
import model.Couleur;
import model.Echiquier;
import model.PieceNotFoundException;


/**
 * @author francoise.perrin
 * 
 * Cette classe est fortement couplée à un Echiquier qu'elle crée
 * Elle le rend  Observable et en simplifie l'interface
 * (DP Proxy, Facade, Observer)
 *
 */
public class ChessGame extends Observable implements BoardGames{

	private Echiquier echiquier;

	/**
	 * Cree une instance de la classe Echiquier
	 * et notifie ses observateurs
	 */
	public ChessGame() {
		super();
		this.echiquier = new Echiquier();
		this.notifyObservers(echiquier.getPiecesIHM()); 
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String st = "";
		st += "\n" + echiquier.getMessage() + "\n";
		st = echiquier.toString();	
		return  st;
	}


	/**
	 * Permet de deplacer une piece connaissant ses coordonnees initiales vers ses
	 * coordonnees finales si le deplacement est "legal". 
	 * Si deplacement OK, permet l'alternance des joueurs.
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return OK si deplacement OK
	 * si OK, permet l'alternance des joueurs
	 */
        @Override
	public boolean move (int xInit, int yInit, int xFinal, int yFinal){
		boolean ret = false; 

		ret = echiquier.isMoveOk(xInit, yInit, xFinal, yFinal);
		if (ret){
			ret = echiquier.move(xInit, yInit, xFinal, yFinal);
		}
		if (ret){
			echiquier.switchJoueur();
		}		
		
		this.notifyObservers(echiquier.getPiecesIHM()); 
		return ret;	
	}
        
        @Override
	public List<Coord> getPossibleMovements(int xInit, int yInit){
		return echiquier.getPossibleMovements(xInit,yInit);		
	}

        @Override
	public boolean isEnd(){
		return echiquier.isEnd();		
	}
        
        @Override
	public boolean isPionAPromouvoir(){
		return echiquier.isPionAPromouvoir();		
	}
        
        @Override
        public boolean newPiece(String type, int x, int y)
        {
            boolean valid = echiquier.newPiece(type, x, y);
            this.notifyObservers(echiquier.getPiecesIHM());
            return valid;
        }

        @Override
	public String getMessage() {
		return echiquier.getMessage();
	}


        @Override
	public Couleur getColorCurrentPlayer(){		
		return echiquier.getColorCurrentPlayer();		
	}	

        @Override
	public Couleur getPieceColor(int x, int y) throws PieceNotFoundException 
        {
		return echiquier.getPieceColor(x, y);
	}

	

	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void	notifyObservers(Object arg) {
		super.setChanged();
		super.notifyObservers(arg); 
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	@Override
	public void addObserver(Observer o){
		super.addObserver(o);
		this.notifyObservers(echiquier.getPiecesIHM()); 
	}

}
