
package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author beetix
 */
public class Echiquier {
    
    boolean joueur;
    private Jeu jeuBlanc;
    private Jeu jeuNoir;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Echiquier()
    {
        
    }
            
    public void switchJoueur()
    {
        joueur=!joueur;
    }
            
            
    public List<PieceIHMs> getPiecesIHM()
    {
        return Stream.concat(jeuBlanc.getPiecesIHM().stream(), jeuNoir.getPiecesIHM().stream()).collect(Collectors.toList());
    }
    
    /**
     * L'algo est le suivant : 
        s'il n'existe pas de piece du jeu courant aux coordonnÃ©es initiales --> false, 
        si les coordonnÃ©es finales ne sont pas valides ou Ã©gales aux initiales --> false, 
        si position finale ne correspond pas Ã  algo de dÃ©placement piece --> false, 
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
        if(xFinal>7 || yFinal >7 || xFinal<0 || yFinal <0 )
        {
            return false;
        }
        
        if(xFinal==xInit && yFinal==yInit)
        {
            return false;
        }
        
        if (joueur)
        {
            if(jeuBlanc.isPieceHere(xInit, yInit) )
            {
                return false;
            }
            
            if (!jeuBlanc.isMoveOk(xInit, yInit, xFinal, yFinal, false, false) )
            {
                return false;
            }
            
            if (jeuBlanc.isPieceHere(xFinal, yFinal))
            {
                /* Ici, insérer traitement roc du roi */
                return false;
            }
            
            if (jeuNoir.isPieceHere(xFinal, yFinal))
            {
                jeuNoir.move(xFinal, yFinal,-1,-1);
            }
            
            jeuBlanc.move(xInit, yInit, xFinal, yFinal);
        }
        return true;
    }
    
    public boolean move(int xInit, int yInit,int xFinal,int yFinal)
    {
        return true;
    }
    
    public Couleur getColorCurrentPlayer()
    {
        if (joueur) 
            return Couleur.BLANC;
        return Couleur.NOIR;
    }
    
    public Couleur getPieceColor(int x, int y)
    {
        if(jeuBlanc.isPieceHere(x,y))
            return Couleur.BLANC;
        if(jeuBlanc.isPieceHere(x,y))
            return Couleur.NOIR;
        return null;
    }
    
    public String toString()
    {
        return ("Voici l'échiquier !");
    }
}
