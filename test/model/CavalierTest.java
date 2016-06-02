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
public class CavalierTest {
    
    Pieces monCavalier;
    
    @Before
    public void setUp() {
        monCavalier = new Cavalier(Couleur.NOIR, new Coord(1,7));
    }

    @Test
    public void deplacementEnLAvecBaseEnBasDoitRenvoyerVrai()
    {
        assertTrue(monCavalier.isMoveOk(2, 5, false, false));
    }
    
    @Test
    public void deplacementEnLAEnversAvecBaseEnBasDoitRenvoyerVrai()
    {
        assertTrue(monCavalier.isMoveOk(0, 5, false, false));
    }
    
    @Test
    public void deplacementEnLAvecBaseAGaucheDoitRenvoyerVrai()
    {
        assertTrue(monCavalier.isMoveOk(3, 6, false, false));
    }
    
    public void deplacementVersUneCaseNonAtteignableEnUnCoupDoitRenvoyerFaux()
    {
        assertFalse(monCavalier.isMoveOk(0, 4, false, false));
    }
    
}
