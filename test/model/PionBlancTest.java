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
}
