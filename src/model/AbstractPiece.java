/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Gère le comportement commun de toutes les pièces Chaque classe dérivé (Pion, etc.) sera capable de dire si le déplacement est OK.
 * @author loupiotte93 & Beetix
 */
public abstract class AbstractPiece implements Pieces {
    
    private Couleur couleur;
    private Coord coord;
    private String name;
    
    /**
     * 
     * @param couleur
     * @param coord 
     */
    public AbstractPiece(Couleur couleur, Coord coord)
    {
        this.coord=coord;
        this.couleur=couleur;
    }
    
    
    public int getX(){
        return this.coord.x;
    }
    
    public int getY(){
        return this.coord.y;
    }
    
    public Couleur getCouleur(){
        return this.couleur;
    }
    
    /**
     * 
     * @return le nom de la pièce (Tour, Cavalier, etc.) attention le type ne correspond pas forcément au nom de la classe
     */
    public java.lang.String getName(){
        return this.name;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return true si déplacement effectué
     */
    public boolean move(int x,int y){
        return true;
    }
    /**
     * 
     * @return true si piece effectivement capturé Positionne x et y à -1
     */
    public boolean capture(){
        return true;
    }
    
    @Override
    public java.lang.String toString(){
        return (this.getName()+" à la position "+this.coord);
    }
    
    /**
     * 
     * @param xFinal
     * @param yFinal
     * @param isCatchOk
     * @param isCastlingPossible
     * @return true si déplacement légal en fonction des algo de déplacement spécifique de chaque pièce
     */
    public abstract boolean isMoveOk(int xFinal, int yFinal,boolean isCatchOk,boolean isCastlingPossible);
    
    public static void main(String args[]) {
        System.setErr(System.out);
        
        Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
        
        System.out.println("Tests censés être OK :");
        System.out.println("Déplacement de " + maTour + " de 1 vers la droite : " +  (( maTour.isMoveOk(1, 0, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + maTour + " au coin haut gauche : " +  (( maTour.isMoveOk(0, 7, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + maTour + " au coin bas droit : " +  (( maTour.isMoveOk(7, 0, false, false) ) ? "OK" : "KO") );
        
        System.out.println("Tests censés être KO :");
        System.out.println("Déplacement de " + maTour + " en dehors du plateau : " +  (( maTour.isMoveOk(8, 0, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + maTour + " au même endroit : " +  (( maTour.isMoveOk(0, 0, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + maTour + " en diagonale : " +  (( maTour.isMoveOk(1, 1, false, false) ) ? "OK" : "KO") );
        
        Pieces monFou = new Fou(Couleur.NOIR, new Coord(2, 0));
        
        System.out.println("Tests censés être OK :");
        System.out.println("Déplacement de " + monFou + " d'une case vers le haut et la droite : " +  (( monFou.isMoveOk(3, 1, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monFou + " d'une case vers le bas et la droite : " +  (( monFou.isMoveOk(1, 1, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monFou + " vers l'arrête droite : " +  (( monFou.isMoveOk(7, 5, false, false) ) ? "OK" : "KO") );

        System.out.println("Tests censé êtres KO :");
        System.out.println("Déplacement de " + monFou + " d'une case vers la droite et de 2 vers le haut : " +  (( monFou.isMoveOk(4, 5, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monFou + " en dehors du plateau : " +  (( monFou.isMoveOk(3, 1, false, false) ) ? "OK" : "KO") );
        
        Pieces monPionBlanc = new Pion(Couleur.BLANC, new Coord(0, 1));
        
        System.out.println("Tests censés être OK :");
        System.out.println("Déplacement de " + monPionBlanc + " d'une case vers le haut : " +  (( monPionBlanc.isMoveOk(0, 2, true, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionBlanc + " d'une case pour prendre un pion présent en haut à droite : " +  (( monPionBlanc.isMoveOk(1, 2, true, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionBlanc + " de 2 cases vers le haut pour le premier déplacement de ce pion  : " +  (( monPionBlanc.isMoveOk(0, 3, false, false) ) ? "OK" : "KO") );

        
        
    }
    
}
