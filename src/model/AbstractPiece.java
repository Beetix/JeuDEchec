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
public abstract class AbstractPiece implements Pieces {
    
    private int x;
    private int y;
    private Couleur couleur;
    private Coord coord;
    private String name;
    
    
    public AbstractPiece(Couleur couleur, Coord coord)
    {
        
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
    
    
    public java.lang.String getName(){
        return this.name;
    }
    
    public boolean move(int x,int y){
        return true;
    }
    
    public boolean capture(){
        return true;
    }
    
    @Override
    public java.lang.String toString(){
        return ""+this.getX()+this.getY();
    }
    
    public abstract boolean isMoveOk(int xFinal, int yFinal,boolean isCatchOk,boolean isCastlingPossible);
    
    public static void main(){
        Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
    }
    
}
