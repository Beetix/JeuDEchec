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
    
    @Test
    public void deplacementAuCoinHautGaucheDoitRenvoyerVrai()
    {
        assertTrue(maTour.isMoveOk(0, 7, false, false));
    }
    
    @Test
    public void deplacementAuCoinBasDroitDoitRenvoyerVrai()
    {
        assertTrue(maTour.isMoveOk(7, 0, false, false));
    }
    
    @Test
    public void deplacementEnDiagonaleDoitRenvoyerFaux()
    {
        assertTrue(maTour.isMoveOk(1, 1, false, false));
    }
}
