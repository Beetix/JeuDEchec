package model;

/**
 *
 * @author beetix
 */
public class Tour extends AbstractPiece {
    
    public Tour(Couleur couleur, Coord coord)
    {
        super(couleur, coord, "Tour");
    }

    @Override
    protected boolean isDeplacementOkPourPiece(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if (getX() == xFinal || getY() == yFinal)
        {
            return true;
        }
        else
        {
            System.err.println("Déplacement impossible, la Tour ne se déplace qu'en lignes");
            return false;
        }
    }
    
}
