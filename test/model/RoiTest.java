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
public class RoiTest {
    
    Pieces monRoi;
    
    @Before
    public void setUp() {
        monRoi = new Roi(Couleur.BLANC, new Coord(4,0));
    }

    @Test
    public void deplacementUneCaseVersLeHautADroiteDoitRenvoyerVrai()
    {
        assertTrue(monRoi.isMoveOk(5, 1, false, false));
    }
    
    @Test
    public void deplacementUneCaseVersLaGaucheDoitRenvoyerVrai()
    {
        assertTrue(monRoi.isMoveOk(3, 0, false, false));
    }
    
    @Test
    public void deplacementUneCaseVersLeHautDoitRenvoyerVrai()
    {
        assertTrue(monRoi.isMoveOk(4, 1, false, false));
    }
    
    @Test
    public void deplacementDePlusUneCaseDoitRenvoyerFaux()
    {
        assertFalse(monRoi.isMoveOk(6, 2, false, false));
    }
    
    public void deplacementVersUneCaseNonAtteignableEnUnCoupDoitRenvoyerFaux()
    {
        assertFalse(monRoi.isMoveOk(0, 4, false, false));
    }
    
    
}
