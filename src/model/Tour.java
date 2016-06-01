package model;

/**
 *
 * @author beetix
 */
public class Tour extends AbstractPiece {
    
    public Tour(Couleur couleur, Coord coord)
    {
        super(couleur, coord);
    }
    
    public boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible)
    {
        
        if (!Coord.coordonnees_valides(xFinal, yFinal))
        {
            System.err.println("Coordonnées hors du plateau de jeu");
            return false;
        }
        
        if (getX() == xFinal && getY() == yFinal)
        {
            System.err.println("Les coordonnées sont les mêmes !");
            return false;
        }
        
        if (getX() == xFinal || getY() == yFinal)
        {
            return true;
        }
        else
        {
            System.err.println("Déplacement impossible, la Tour ne se déplace qu'en lignes");
        }
        
    }
    
}
