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
     * @param name 
     */
    public AbstractPiece(Couleur couleur, Coord coord, String name)
    {
        this.coord = coord;
        this.couleur= couleur;
        this.name = name;
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
    @Override
    public boolean move(int x,int y){
        this.coord.x = x;
        this.coord.y = y;
            
        return true;
    }
    /**
     * 
     * @return true si piece effectivement capturé Positionne x et y à -1
     */
    @Override
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
    public boolean isMoveOk(int xFinal, int yFinal,boolean isCatchOk,boolean isCastlingPossible)
    {
        if (!Coord.coordonnees_valides(xFinal, yFinal))
        {
            System.err.println("Coordonnées hors du plateau de jeu");
            return false;
        }
        
        if (getX() == xFinal && getY() == yFinal)
        {
            System.err.println("Les coordonnées sont les mêmes !");
            return false;
        }
        
        return isDeplacementOkPourPiece(xFinal, yFinal, isCatchOk, isCastlingPossible);
    }
    
    /**
     * 
     * @param xFinal
     * @param yFinal
     * @param isCatchOk
     * @param isCastlingPossible
     * @return true si les spécificités de la piece lui autorise le déplacement
     */
    protected abstract boolean isDeplacementOkPourPiece(int xFinal, int yFinal,boolean isCatchOk,boolean isCastlingPossible);
    
    
    /**
     * Méthode main pour faire des tests sur les pièces
     * @deprecated voir les tests JUnit qui remplacent l'utilisation de cette méthode 
     * @param args 
     */
    public static void main(String args[]) {
        
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
        
        Pieces monPionBlanc = new PionBlanc(new Coord(0, 1));
        
        System.out.println("Tests censés être OK :");
        System.out.println("Déplacement de " + monPionBlanc + " d'une case vers le haut : " +  (( monPionBlanc.isMoveOk(0, 2, true, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionBlanc + " d'une case pour prendre un pion présent en haut à droite : " +  (( monPionBlanc.isMoveOk(1, 2, true, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionBlanc + " de 2 cases vers le haut pour le premier déplacement de ce pion  : " +  (( monPionBlanc.isMoveOk(0, 3, false, false) ) ? "OK" : "KO") );
        
        System.out.println("Tests censé êtres KO :");
        System.out.println("Déplacement de " + monPionBlanc + " d'une case vers le bas : " +  (( monPionBlanc.isMoveOk(0, 0, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionBlanc + " d'une case en diagonale alors qu'il n'y a pas de pion : " +  (( monPionBlanc.isMoveOk(1, 2, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionBlanc + " d'une case où elle ne devrait pas pouvoir aller : " +  (( monPionBlanc.isMoveOk(7, 5, false, false) ) ? "OK" : "KO") );
        
        Pieces monPionNoir = new PionNoir(new Coord(4, 6));
        
        System.out.println("Tests censés être OK :");
        System.out.println("Déplacement de " + monPionNoir + " d'une case vers le bas : " +  (( monPionNoir.isMoveOk(4, 5, true, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionNoir + " d'une case pour prendre un pion présent en bas à gauche : " +  (( monPionNoir.isMoveOk(3, 5, true, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionNoir + " de 2 cases vers le bas pour le premier déplacement de ce pion  : " +  (( monPionNoir.isMoveOk(4, 4, false, false) ) ? "OK" : "KO") );
        
        System.out.println("Tests censé êtres KO :");
        System.out.println("Déplacement de " + monPionNoir + " d'une case vers le haut : " +  (( monPionNoir.isMoveOk(4, 7, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionNoir + " d'une case en diagonale alors qu'il n'y a pas de pion : " +  (( monPionNoir.isMoveOk(3, 5, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monPionNoir + " d'une case où elle ne devrait pas pouvoir aller : " +  (( monPionNoir.isMoveOk(0, 4, false, false) ) ? "OK" : "KO") );
        
        Pieces maReine = new Reine(Couleur.BLANC, new Coord(3,0));
        
        System.out.println("Tests censés être OK :");
        System.out.println("Déplacement de " + maReine + " d'une case vers le haut et la droite : " +  (( maReine.isMoveOk(4, 1, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + maReine + " vers l'arrête droite : " +  (( maReine.isMoveOk(7, 4, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + maReine + " au coin bas droit  : " +  (( maReine.isMoveOk(7, 0, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + maReine + " vers l'arrête haute : " +  (( maReine.isMoveOk(3, 7, false, false) ) ? "OK" : "KO") );

        System.out.println("Tests censé êtres KO :");
        System.out.println("Déplacement de " + maReine + " d'une case vers la droite et de 2 vers le haut : " +  (( maReine.isMoveOk(4, 2, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + maReine + " d'une case où elle ne devrait pas pouvoir aller : " +  (( maReine.isMoveOk(0, 4, false, false) ) ? "OK" : "KO") );
        
        Pieces monRoi = new Roi(Couleur.BLANC, new Coord(4,0));
        
        System.out.println("Tests censés être OK :");
        System.out.println("Déplacement de " + monRoi + " d'une case vers le haut et la droite : " +  (( monRoi.isMoveOk(5, 1, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monRoi + " d'une case vers la gauche : " +  (( monRoi.isMoveOk(3, 0, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monRoi + " d'une case vers le haut : " +  (( monRoi.isMoveOk(4, 1, false, false) ) ? "OK" : "KO") );

        System.out.println("Tests censé êtres KO :");
        System.out.println("Déplacement de " + monRoi + " de 2 cases vers la droite et le haut : " +  (( monRoi.isMoveOk(6, 2, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monRoi + " d'une case où il ne devrait pas pouvoir aller : " +  (( monRoi.isMoveOk(0, 4, false, false) ) ? "OK" : "KO") );
        
        Pieces monCavalier = new Cavalier(Couleur.NOIR, new Coord(1,7));
        
        System.out.println("Tests censés être OK :");
        System.out.println("Déplacement de " + monCavalier + " d'un L avec base en bas : " +  (( monCavalier.isMoveOk(2, 5, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monCavalier + " d'un L à l'envers avec base en bas : " +  (( monCavalier.isMoveOk(0, 5, false, false) ) ? "OK" : "KO") );
        System.out.println("Déplacement de " + monCavalier + " d'un L avec base à gauche : " +  (( monCavalier.isMoveOk(3, 6, false, false) ) ? "OK" : "KO") );

        System.out.println("Tests censé êtres KO :");
        System.out.println("Déplacement de " + monCavalier + " d'une case où il ne devrait pas pouvoir aller : " +  (( monCavalier.isMoveOk(0, 4, false, false) ) ? "OK" : "KO") );
    }
    
}
