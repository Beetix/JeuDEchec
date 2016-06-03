/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.ChessPiecesFactory;
import tools.ChessSinglePieceFactory;

/**
 *
 * @author user
 */
public class Jeu implements Game, Cloneable {
    
    private List<Pieces> pieces;
    
    private Couleur couleur;
    
    private boolean isCastlingPossible;
    
    private boolean isCatchOk;

    public Jeu(Couleur couleur)
    {
        this.couleur=couleur;
        pieces = ChessPiecesFactory.newPieces(couleur);
    }

    public Jeu(List<Pieces> pieces, Couleur couleur, boolean isCastlingPossible, boolean isCatchOk, Pieces roi) {
        this.pieces = pieces;
        this.couleur = couleur;
        this.isCastlingPossible = isCastlingPossible;
        this.isCatchOk = isCatchOk;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Jeu jeuClone = (Jeu) super.clone();
                
        List<Pieces> piecesClonees = new LinkedList<>();
        for (Pieces piece : pieces)
        {
            Pieces pieceClonee = (Pieces) ((AbstractPiece) piece).clone();
            piecesClonees.add(pieceClonee);
        }
        
        jeuClone.pieces = piecesClonees;
        
        return jeuClone;
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
            this.isCatchOk = isCatchOk;
            this.isCastlingPossible = isCastlingPossible;
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
        if (isMoveOk(xInit, yInit, xFinal, yFinal, isCatchOk, isCastlingPossible))
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
        try
        {
            return pieces.remove(findPiece(xCatch,yCatch));
        }
        catch (PieceNotFoundException pNFE)
        {
            return false;                        
        }
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
        this.isCastlingPossible=true;
    }
    
    public void setPossibleCapture(){
        this.isCatchOk=true;
    }
    
    public String toString(){
        String retour = "Jeu "+this.couleur + ". Liste des pièces : \n ";
        for(Pieces piece : pieces)
        {
            retour += ( piece.toString()+"\n");
        }
        return retour;
    }
    
    public boolean newPiece(String type, int x, int y)
    {
        try {
            pieces.remove(findPiece(x, y));
        } catch (PieceNotFoundException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pieces.add(ChessSinglePieceFactory.newPiece(this.couleur, type, x, y));
    }
    
    public static void main(String args[]){
        Jeu leJeuBlanc = new Jeu(Couleur.BLANC);
        System.out.println(leJeuBlanc.isPieceHere(5, 1));
        System.out.println(leJeuBlanc);        
    }

    boolean isPionHere(int x, int y) {
        if(this.isPieceHere(x,y))
        {
            try {
                if((findPiece(x,y).getName())=="Pion")
                {
                    return true;
                }
            } catch (PieceNotFoundException ex) {
                return false;
            }
        }
        return false;
    }
}
