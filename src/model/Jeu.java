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
    private boolean castling;
    private boolean possibleCapture;
    
    public Jeu(Couleur couleur){
        this.couleur=couleur;
        pieces = ChessPiecesFactory.newPieces(couleur);
    }

    @Override
    public boolean isPieceHere(int x, int y) {
        return findPiece(x,y)!=null;
    }

    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
        Pieces pstart = findPiece(xInit,yInit);
        return pstart.isMoveOk(xFinal,yFinal,isCatchOk, isCastlingPossible);
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
       return findPiece(xInit,yInit).move(xFinal,yFinal);
    }

    @Override
    public boolean capture(int xCatch, int yCatch) {
        return findPiece(xCatch,yCatch).capture();
    }
    
    private Pieces findPiece(int x, int y){
        for(Pieces piece : this.pieces)
        {
            if(piece.getX()==x && piece.getY()==y)
            {
                return piece;
            }
        }
        return null;
    }
    
    public Couleur getPieceCouleur(int x, int y){
        return findPiece(x,y).getCouleur();
    }
    
    public String getPieceName(int x, int y){
        return findPiece(x,y).getName();
    }
    
    public List<Pieces> getPiecesIHM(){
        return pieces;
    }
    
    public void setCastling(){
        this.castling=true;
    }
    
    public void setPossibleCapture(){
        /* Find position of adverse jeu */
        this.possibleCapture=true;
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
