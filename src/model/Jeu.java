/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
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
    
    public Jeu(Couleur couleur)
    {
        this.couleur=couleur;
        pieces = ChessPiecesFactory.newPieces(couleur);
    }

    @Override
    public boolean isPieceHere(int x, int y) {
        try
        {
            findPiece(x,y);
            return true;
            
        }
        catch (PieceNotFoundException pNFE)
        {
            return false;
        }
    }

    @Override
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible)
    {
        try
        {
            Pieces pstart = findPiece(xInit,yInit);
            return pstart.isMoveOk(xFinal, yFinal, isCatchOk, isCastlingPossible);
        }
        catch (PieceNotFoundException pNFE)
        {
            return false;
        }
            
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal)
    {
        if (isMoveOk(xInit, yInit, xFinal, yFinal, possibleCapture, castling))
        {
            try
            {
                return findPiece(xInit,yInit).move(xFinal,yFinal);
            }
            catch (PieceNotFoundException pNFE)
            {
                return false;                        
            }
            
        }
       return false;
    }

    @Override
    public boolean capture(int xCatch, int yCatch) {
        
        return true;
    }
    
    private Pieces findPiece(int x, int y) throws PieceNotFoundException {
        for(Pieces piece : this.pieces)
        {
            if(piece.getX()==x && piece.getY()==y)
            {
                return piece;
            }
        }
        throw new PieceNotFoundException();
    }
    
    public Couleur getPieceCouleur(int x, int y) throws PieceNotFoundException {
        return findPiece(x,y).getCouleur();
    }
    
    public String getPieceName(int x, int y) throws PieceNotFoundException {
        return findPiece(x,y).getName();   
    }
    
    public List<PieceIHMs> getPiecesIHM(){
        PieceIHMs newPieceIHM = null;
        List<PieceIHMs> list = new LinkedList<PieceIHMs>();
        for (Pieces piece : pieces){
            // si la pièce est toujours en jeu
            if (piece.getX()!=-1) {
                newPieceIHM = new PieceIHM(piece);
                list.add(newPieceIHM);
            }
        }
        return list;
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
    
    public boolean supprimerPiece(int xInit, int yInit)
    {
        try
        {
            return pieces.remove(findPiece(xInit,yInit));
        }
        catch (PieceNotFoundException pNFE)
        {
            return false;                        
        }
    }
    
    
    public static void main(String args[]){
        Jeu leJeuBlanc = new Jeu(Couleur.BLANC);
        System.out.println(leJeuBlanc.isPieceHere(5, 1));
        System.out.println(leJeuBlanc);        
    }
}
