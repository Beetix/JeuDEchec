package model;

import model.Coord;
import model.Couleur;
import model.Pieces;
import model.Tour;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author beetix
 */
public class TourTest {
    
    Pieces maTour;
    
    @Before
    public void setUp() {
        maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
    }
    
    @Test
    public void deplacementDUneCaseVersLaDroiteDoitRenvoyerVrai() {
        assertTrue(maTour.isMoveOk(1, 0, false, false));           
    }
    
    
}
