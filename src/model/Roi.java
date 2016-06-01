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
         super(couleur, coord, "Roi");
    }

    @Override
    protected boolean isDeplacementOkPourPiece(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
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
