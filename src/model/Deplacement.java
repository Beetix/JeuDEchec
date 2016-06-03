/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author beetix
 */
public class Deplacement {
    
    private Coord depart;
    private int xDelta;
    private int yDelta;
    private int nbSauts;
    
    public Deplacement(int xInit, int yInit, int xFinal, int yFinal)
    {
        depart = new Coord(xInit, yInit);
        xDelta = (xFinal - xInit);
        yDelta = (yFinal - yInit);
        nbSauts = Math.min(Math.abs(xDelta), Math.abs(yDelta));
        if (nbSauts == 0)
        {
            nbSauts = Math.max(Math.abs(xDelta), Math.abs(yDelta));
        }
    }    
    
    public List<Coord> getPointsDePassage()
    {
        List<Coord> pointsDePassage = new LinkedList<>();
        
        int xFacteur = xDelta;
        
        if (xFacteur  != 0)
        {
            xFacteur  /= Math.abs(xFacteur);
        }
        
        int yFacteur = yDelta;
        
        if (yFacteur != 0)
        {
            yFacteur /= Math.abs(yFacteur);
        }
        
        int nbSauts = this.nbSauts - 1; 
        
        while (nbSauts > 0)
        {
            
            pointsDePassage.add(new Coord(depart.x + xFacteur * nbSauts, depart.y + yFacteur * nbSauts));
            nbSauts--;
        }
        
        return pointsDePassage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Deplacement other = (Deplacement) obj;
        if (!Objects.equals(this.depart, other.depart)) {
            return false;
        }
        if (this.xDelta != other.xDelta) {
            return false;
        }
        if (this.yDelta != other.yDelta) {
            return false;
        }
        if (this.nbSauts != other.nbSauts) {
            return false;
        }
        return true;
    }
    
    
}
