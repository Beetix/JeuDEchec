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
public class PionBlancTest {
    
    Pieces monPionBlanc;
    
    @Before
    public void setUp() {
        monPionBlanc = new PionBlanc(new Coord(0, 1));
    }

    @Test
    public void deplacementUneCaseVersLeHautDoitRenvoyerVrai() {
        assertTrue(monPionBlanc.isMoveOk(0, 2, true, false));
    }
    
    @Test
    public void deplacementUneCasePourPrisePieceEnHautADroiteDoitRenvoyerVrai() {
        assertTrue(monPionBlanc.isMoveOk(1, 2, true, false));
    }
    
    @Test
    public void deplacement2CasesVersLeHautPourPremierDeplacementPionDoitRenvoyerVrai()
    {
        assertTrue(monPionBlanc.isMoveOk(0, 3, false, false));
    }
    
    @Test
    public void deplacementUneCaseVersLeBasDoitRenvoyerFaux()
    {
        assertFalse(monPionBlanc.isMoveOk(0, 0, false, false));
    }
    
    @Test
    public void deplacementEnDiagonaleSansPieceAPrendreDoitRenvoyerFaux()
    {
        assertFalse(monPionBlanc.isMoveOk(1, 2, false, false));
    }
    
    @Test
    public void deplacementVersUneCaseNonAtteignableEnUnCoupDoitRenvoyerFaux()
    {
        assertFalse(monPionBlanc.isMoveOk(7, 5, false, false));
    }
    
 
}
