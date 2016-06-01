/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import tools.ChessPiecesFactory;

/**
 *
 * @author user
 */
public class Jeu implements Game{
    private List<Pieces> pieces;
    private Couleur couleur;
    
    public Jeu(Couleur couleur){
        this.couleur=couleur;
        pieces = ChessPiecesFactory.newPieces(couleur);
    }

    @Override
    public boolean isPieceHere(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean capture(int xCatch, int yCatch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public String toString(){
        String retour = "Jeu "+this.couleur + ". Liste des pièces : \n ";
        for(Pieces piece : pieces)
        {
            retour += ( piece.toString()+"\n");
        }
        return retour;
    }
    
    public static void main(String args[]){
        Jeu leJeuBlanc = new Jeu(Couleur.BLANC);
        
        System.out.println(leJeuBlanc);
    }
    
}
