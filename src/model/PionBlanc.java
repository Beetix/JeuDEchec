package model;

/**
 *
 * @author beetix
 */
public class PionBlanc extends Pion {

    public PionBlanc(Coord coord) {
        super(Couleur.BLANC, coord);
    }

    @Override
    protected boolean isDeplacementUneCaseSuivantColonne(int xFinal, int yFinal) {
        return getY() + 1 == yFinal && getX() == xFinal;
    }

    @Override
    protected boolean isDeplacementUneCaseDiagonale(int xFinal, int yFinal) {
        return getY() + 1 == yFinal && (getX() + 1 == xFinal || getX() - 1 == xFinal);
    }

    @Override
    protected boolean isDeplacementDeDeuxAuPremierDeplacement(int xFinal, int yFinal) {
        return getY() == 1 && yFinal == 3;
    }
    
}
