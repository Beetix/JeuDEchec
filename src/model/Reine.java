/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Math.abs;

/**
 *
 * @author user
 */
public class Reine extends AbstractPiece{
    
    public Reine(Couleur couleur, Coord coord){
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
        
        if ( (abs(getX() - xFinal) == abs(getY() - yFinal) ) || ( getX() == xFinal || getY() == yFinal ) )
        {
            return true;
        }
        else
        {
            System.err.println("Déplacement impossible, la Reine ne se déplace que comme le fou ou la tour");
            return false;
        }
    }
}
