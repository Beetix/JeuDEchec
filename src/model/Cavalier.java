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
public class Cavalier extends AbstractPiece{

    public Cavalier(Couleur couleur, Coord coord){
         super(couleur,coord, "Cavalier");
    }

    @Override
    protected boolean isDeplacementOkPourPiece(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if ( (abs(getX() - xFinal) == 2 && abs(getY() - yFinal)==1 ) || (abs(getX() - xFinal) == 1 && abs(getY() - yFinal)==2 ) )
        {
            return true;
        }
        else
        {
            System.err.println("Déplacement impossible, le cavalier ne se déplace qu'en 'L' ");
            return false;
        }
    }
    
}
