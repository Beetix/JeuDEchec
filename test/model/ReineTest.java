/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author beetix
 */
public class ReineTest {
    
    Pieces maReine;
    
    @Before
    public void setUp() {
        maReine = new Reine(Couleur.BLANC, new Coord(3,0));
    }

    @Test
    public void deplacementUneCaseVersLeHautADroiteDoitRenvoyerVrai()
    {
        assertTrue(maReine.isMoveOk(4, 1, false, false));
    }
    
    @Test
    public void deplacementVersArreteDroiteDoitRenvoyerVrai()
    {
        assertTrue(maReine.isMoveOk(7, 4, false, false));
    }
    
    @Test
    public void deplacementAuCoinBasDroitDoitRenvoyerVrai()
    {
        assertTrue(maReine.isMoveOk(7, 0, false, false));
    }
    
    @Test
    public void deplacementVersArreteHauteDoitRenvoyerVrai()
    {
        assertTrue(maReine.isMoveOk(3, 7, false, false));
    }
    
    @Test
    public void deplacementUneCaseVersLaDroiteEt2VersLeHautDoitRenvoyerFaux()
    {
        assertFalse(maReine.isMoveOk(4, 2, false, false));
    }
    
    public void deplacementVersUneCaseNonAtteignableEnUnCoupDoitRenvoyerFaux()
    {
        assertFalse(maReine.isMoveOk(0, 4, false, false));
    }
    
}
