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
public class Roi extends AbstractPiece{

    public Roi(Couleur couleur, Coord coord){
         super(couleur,coord);
    }
    
    
    @Override
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
        
        if (abs(getX() - xFinal) <=1 &&  abs(getY() - yFinal) <= 1 )
        {
            return true;
        }
        else
        {
            System.err.println("Déplacement impossible, le Roi ne se déplace que d'une case");
            return false;
        }
    }
    
    
}
