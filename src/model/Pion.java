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
public class Pion extends AbstractPiece {
    public Pion(Couleur couleur, Coord coord)
    {
        super(couleur,coord);
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
        
        if(isDeplacementUneCaseNordSud(xFinal, yFinal) || isCatchOk && isDeplacementUneCaseDiagonale(xFinal, yFinal) || isDeplacementDeDeuxAuPremierTour(xFinal, yFinal))
        {
            return true;
        }
        else
        {
           System.err.println("Déplacement impossible, le pion ne peut que"
                   + " avancer d'une case devant lui ou bien en diagonale"
                   + " si il peut prendre une autre pièce");
           return false;
        }
    }

    private boolean isDeplacementUneCaseNordSud(int xFinal, int yFinal)
    {
        return ( (getY() - 1 == yFinal && getCouleur() == Couleur.NOIR) 
                || ( getY() + 1 == yFinal && getCouleur() == Couleur.BLANC) ) 
                &&
                getX() == xFinal;
    }

    private boolean isDeplacementUneCaseDiagonale(int xFinal, int yFinal)
    {
        return ( (getY() - 1 == yFinal && getCouleur() == Couleur.NOIR) 
                || (getY() + 1 == yFinal && getCouleur() == Couleur.BLANC) ) 
                &&
                (getX() + 1 == xFinal || getX() - 1 == xFinal);
    }
    
    private boolean isDeplacementDeDeuxAuPremierTour(int xFinal, int yFinal)
    {
        return (  getY() == 1 && yFinal == 3 && getCouleur() == Couleur.BLANC 
                || getY() == 6 && yFinal == 4 && getCouleur() == Couleur.NOIR );
    }

}
