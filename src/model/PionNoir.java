package model;

/**
 *
 * @author beetix
 */
public class PionNoir extends Pion {

    public PionNoir(Coord coord) {
        super(Couleur.NOIR, coord);
    }
    public PionNoir(Couleur cool, Coord coord) {
        super(Couleur.NOIR, coord);
    }
    
    @Override
    protected boolean isDeplacementUneCaseSuivantColonne(int xFinal, int yFinal) {
        return getY() - 1 == yFinal && getX() == xFinal;
    }

    @Override
    protected boolean isDeplacementUneCaseDiagonale(int xFinal, int yFinal) {
        return getY() - 1 == yFinal && (getX() + 1 == xFinal || getX() - 1 == xFinal);
    }

    @Override
    protected boolean isDeplacementDeDeuxAuPremierDeplacement(int xFinal, int yFinal) {
        return getY() == 6 && yFinal == 4;
    }
    
    
}
