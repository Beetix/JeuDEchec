
package model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author beetix
 */
public class Echiquier {
    
    private Jeu jeuCourant;
    private Jeu jeuEnAttente;
    private Jeu jeuBlanc;
    private Jeu jeuNoir;
    private String message;
    private boolean endOfGame;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Echiquier()
    {
        jeuBlanc = new Jeu(Couleur.BLANC);
        jeuNoir = new Jeu(Couleur.NOIR);
        jeuCourant = jeuBlanc;
        jeuEnAttente=jeuNoir;
        message = "Début de partie";
        endOfGame=false;
    }
            
    public void switchJoueur()
    {
        Jeu tmp=jeuCourant;
        jeuCourant=jeuEnAttente;
        jeuEnAttente=tmp;
    }
           
            
    public List<PieceIHMs> getPiecesIHM()
    {
        return Stream.concat(jeuBlanc.getPiecesIHM().stream(), jeuNoir.getPiecesIHM().stream()).collect(Collectors.toList());
    }
    
    /**
     * L'algo est le suivant : 
        s'il n'existe pas de piece du jeu courant aux coordonnées initiales --> false, 
        si les coordonnÃ©es finales ne sont pas valides ou égales aux initiales --> false, 
        si position finale ne correspond pas Ã  algo de déplacement piece --> false, 
        s'il existe une piÃ©ce intermÃ©diaire sur la trajectoire --> false (sauf cavalier), 
        s'il existe une piÃ©ce positionnÃ©es aux coordonnÃ©es finales :
        si elle est de la mÃ©me couleur --> false ou tentative roque du roi, 
        sinon : prendre la piÃ©ce intermÃ©diaire (vigilance pour le cas du pion) et dÃ©placer la piÃ©ce -->true,
        sinon dÃ©placer la piÃ©ce -->true
     * @param xInit
     * @param yInit
     * @param xFinal
     * @param yFinal
     * @return 
     */
    public boolean isMoveOk(int xInit, int yInit,int xFinal,int yFinal)
    {
        
        if(!jeuCourant.isPieceHere(xInit, yInit) )
        {
            return false;
        }

        if (!jeuCourant.isMoveOk(xInit, yInit, xFinal, yFinal, jeuEnAttente.isPieceHere(xFinal, yFinal), false) )
        {
            return false;
        }
        
        if (jeuCourant.isPieceHere(xFinal, yFinal))
        {
            return false;
        }
        
        Deplacement monDeplacement = new Deplacement(xInit, yInit, xFinal, yFinal);
        
        for (Coord pointDePassage : monDeplacement.getPointsDePassage())
        {
            if (jeuCourant.isPieceHere(pointDePassage.x, pointDePassage.y)
                    || jeuEnAttente.isPieceHere(pointDePassage.x, pointDePassage.y))
            {
                return false;
            }
        }
        
        
        
        /* Solution pour détecter une situation d'échec non fonctionnelle
        
        try
        {
            
            Jeu simulationJeuCourant = (Jeu) jeuCourant.clone();
            Jeu simulationJeuEnAttente = (Jeu) jeuEnAttente.clone();
            
            simulationJeuCourant.move(xInit, yInit, xFinal, yFinal);
            
            for (int xRoi = 0; xRoi < 8; xRoi++)
            {
                for (int yRoi = 0; yRoi < 8; yRoi++)
                {
                    if (simulationJeuCourant.isPieceHere(xRoi, yRoi))
                    {
                        try
                        {
                            String nomPiece = simulationJeuCourant.getPieceName(xRoi, yRoi);
                            if (nomPiece.equals("Roi"))
                            {
                                for (int xPieceAdverse = 0; xPieceAdverse < 8; xPieceAdverse++)
                                {
                                    for (int yPieceAdverse = 0; yPieceAdverse < 8; yPieceAdverse++) 
                                    {
                                        if (simulationJeuEnAttente.isPieceHere(xPieceAdverse, yPieceAdverse)
                                                && simulationJeuEnAttente.isMoveOk(xPieceAdverse, yPieceAdverse, xRoi, yRoi, true, false))
                                        {
                                            return false;
                                        }
                                    }
                                }
                            }
                 
                        }
                        catch (PieceNotFoundException pNFE)
                        {
                            System.err.println("Cas pièce absente non géré !");
                            return false;
                        }
                    }
                }
            }
                    
        }
        catch (CloneNotSupportedException cNSE)
        {
            return false;
        }
        */
        
        
       
        /* insérer traitement roc du roi
        if (jeuCourant.isPieceHere(xFinal, yFinal))
        {
            return false;
        }*/
        
        return true;
    }
    
    public boolean move(int xInit, int yInit,int xFinal,int yFinal)
    {
        if(isMoveOk(xInit, yInit, xFinal, yFinal))
        {
            
            if (jeuEnAttente.isPieceHere(xFinal, yFinal))
            {
                jeuEnAttente.capture(xFinal, yFinal);
            }
            

            jeuCourant.move(xInit, yInit, xFinal, yFinal);
            
            message = "OK";
            return true;
        }
        message = "KO";
        return false;
        
    }
    
    public Couleur getColorCurrentPlayer()
    {
        if (jeuCourant==jeuBlanc) 
        {
            return Couleur.BLANC;
        }
        return Couleur.NOIR;
        
    }
    
    public Couleur getPieceColor(int x, int y) throws PieceNotFoundException
    {
        return jeuCourant.getPieceCouleur(x, y);
    }
    
    @Override
    public String toString()
    {
        return jeuBlanc.toString() + "\n" + jeuNoir.toString();
    }
    
    public boolean isEnd()
    {
        return endOfGame;
    }

    public List<Coord> getPossibleMovements(int xInit, int yInit) {
        List<Coord> liste=new LinkedList<>();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(isMoveOk(xInit, yInit, i, j))
                {
                    boolean add = liste.add(new Coord(i,j));
                }
            }
        }
        return liste;
    }
    
    public boolean isPionAPromouvoir() {
        List<Coord> liste=new LinkedList<>();
        for(int i=0;i<8;i++)
        {
            if(jeuNoir.isPionHere( i,0) || jeuBlanc.isPionHere( i,7))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean newPiece(String type, int x, int y)
    {
        return jeuEnAttente.newPiece(type, x, y);
    }
}
