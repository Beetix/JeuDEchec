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
public class PionTest {
    
    class SimplePiece extends AbstractPiece
    {

        public SimplePiece(Couleur couleur, Coord coord, String name) {
            super(couleur, coord, name);
        }

        @Override
        protected boolean isDeplacementOkPourPiece(int xFinal, int yFinal, boolean isCatchOk, boolean isCastlingPossible) {
            return true;
        }
        
    }
    
    Pieces maSimplePiece;
    
    public PionTest() {
    }
    
    @Before
    public void setUp() {
        maSimplePiece = new SimplePiece(Couleur.BLANC, new Coord(0, 0), "SimplePiece");
    }

    @Test
    public void deplacementSurUneCaseDifferenteDuPlateauDoitRenvoyerVrai()
    {
        assertTrue(maSimplePiece.isMoveOk(5, 4, false, false));
    }
    
    @Test
    public void deplacementSurLaMemeCaseDoitRenvoyerFaux() 
    {
        assertFalse(maSimplePiece.isMoveOk(0, 0, false, false));
    }
    
    @Test
    public void deplacementEnDehorsDuPlateauDoitRenvoyerFaux()
    {
        assertFalse(maSimplePiece.isMoveOk(8, 0, true, true));
    }
    
}
