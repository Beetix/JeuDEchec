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
        return ""+this.getX()+this.getY();
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
    
    public static void main(){
        Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
    }
    
}
