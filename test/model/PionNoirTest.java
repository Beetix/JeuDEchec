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
public class PionNoirTest {
    
    Pieces monPionNoir;
    
    @Before
    public void setUp() {
        monPionNoir = new PionNoir(new Coord(4, 6));
    }

    @Test
    public void deplacementUneCaseVersLeBasDoitRenvoyerVrai() {
        assertTrue(monPionNoir.isMoveOk(4, 5, true, false));
    }
    
    @Test
    public void deplacementUneCasePourPrisePieceEnBasAGaucheDoitRenvoyerVrai() {
        assertTrue(monPionNoir.isMoveOk(3, 5, true, false));
    }
    
    @Test
    public void deplacement2CasesVersLeBasPourPremierDeplacementPionDoitRenvoyerVrai()
    {
        assertTrue(monPionNoir.isMoveOk(4, 4, false, false));
    }
    
    @Test
    public void deplacementUneCaseVersLeHautDoitRenvoyerFaux()
    {
        assertFalse(monPionNoir.isMoveOk(4, 7, false, false));
    }
    
    @Test
    public void deplacementEnDiagonaleSansPieceAPrendreDoitRenvoyerFaux()
    {
        assertFalse(monPionNoir.isMoveOk(3, 5, false, false));
    }
    
    @Test
    public void deplacementVersUneCaseNonAtteignableEnUnCoupDoitRenvoyerFaux()
    {
        assertFalse(monPionNoir.isMoveOk(0, 4, false, false));
    }
    
}
