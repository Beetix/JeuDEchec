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
        return (isCatchOk && Coord.coordonnees_valides(xFinal,yFinal));
    }
}
