package model;

/**
 *
 * @author beetix
 */
public interface Pieces extends Cloneable {
    
    /**
     * @return indice de la colonne où est postionnée la pièce
     */
    int getX();
    
    /**
     * @return indice de la ligne où est positionnée la pièce
     */
    int getY();
    
    /**
     * @return couleur de la piece
     */
    Couleur getCouleur();
    
    /**
     * @return le nom de la pièce (Tour, Cavalier, etc.) attention le type ne correspond pas forcément au nom de la classe
     */
    String getName();
    
    /**
     * @param xFinal
     * @param yFinal
     * @param isCatchOk
     * @param isCastlingPossible
     * @return true si déplacement légal en fonction des algo de déplacement spécifique de chaque piéce
     */
    boolean isMoveOk(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible);
    
    /**
     * @param xFinal
     * @param yFinal
     * @return true si déplacement effectué
     */
    boolean move(int xFinal, int yFinal);
    
    /**
     * @return true si piece effectivement capturée Positionne x et y à -1
     */
    boolean capture();
    
}
