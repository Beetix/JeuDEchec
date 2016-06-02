/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Fou;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author beetix
 */
public class FouTest {
    
    Pieces monFou;
    
    @Before
    public void setUp() {
        monFou = new Fou(Couleur.NOIR, new Coord(2, 0));        
    }
    
    @Test
    public void deplacementUneCaseVersLeHauteEtLaDroiteDoitRenvoyerVrai() {
        assertTrue( monFou.isMoveOk(3, 1, false, false));           
    }
    
}
