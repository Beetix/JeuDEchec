/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public abstract class Pion extends AbstractPiece {
    public Pion(Couleur couleur, Coord coord)
    {
        super(couleur, coord, "Pion");
    }

    @Override
    protected boolean isDeplacementOkPourPiece(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if(isDeplacementUneCaseSuivantColonne(xFinal, yFinal) && !isCatchOk
                || isCatchOk && isDeplacementUneCaseDiagonale(xFinal, yFinal)
                || isDeplacementDeDeuxAuPremierDeplacement(xFinal, yFinal))
        {
            return true;
        }
        else
        {
           System.err.println("Déplacement impossible, le pion ne peut que"
                   + " avancer d'une case devant lui ou bien en diagonale"
                   + " si il peut prendre une autre pièce. Si le pion est"
                   + " déplacé pour la première fois, il peut avancer de 2 "
                   + "cases");
           return false;
        }
    }
    
    /**
     * @param xFinal
     * @param yFinal
     * @return true si le déplacement est un déplacement d'une case suivant une colonne du plateau
     */
    protected abstract boolean isDeplacementUneCaseSuivantColonne(int xFinal, int yFinal);

    /**
     * @param xFinal
     * @param yFinal
     * @return true si déplacement est un déplacement d'une case en diagonale
     */
    protected abstract boolean isDeplacementUneCaseDiagonale(int xFinal, int yFinal);
    
    /**
     * @param xFinal
     * @param yFinal
     * @return true si déplacement est un déplacement de 2 cases pour le premier déplacement de la pièce
     */
    protected abstract boolean isDeplacementDeDeuxAuPremierDeplacement(int xFinal, int yFinal);

}
