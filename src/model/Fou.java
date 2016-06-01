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
public class Fou extends AbstractPiece{
    public Fou(Couleur couleur, Coord coord)
    {
        super(couleur,coord, "Fou");
    }

    @Override
    protected boolean isDeplacementOkPourPiece(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        if (abs(getX() - xFinal) ==  abs(getY() - yFinal))
        {
            return true;
        }
        else
        {
            System.err.println("Déplacement impossible, le Fou ne se déplace qu'en diagonales");
            return false;
        }
    }
}
