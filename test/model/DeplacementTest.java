package model;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author beetix
 */
public class DeplacementTest {

    @Test
    public void pointsDePassagePourDeplacementVersLeHaut() 
    {                
        Deplacement versLeHaut = new Deplacement(0, 0, 0, 3);
        List<Coord> pointsDePassage = versLeHaut.getPointsDePassage();
        
        assertTrue(pointsDePassage.size() == 2);
        assertTrue(pointsDePassage.contains(new Coord(0, 1)));
        assertTrue(pointsDePassage.contains(new Coord(0, 2)));
    }
    
    @Test
    public void pointsDePassagePourDeplacementVersLeBas()
    {
        Deplacement versLeBas = new Deplacement(0, 5, 0, 0);
        
        List<Coord> pointsDePassage = versLeBas.getPointsDePassage();
        
        assertTrue(pointsDePassage.size() == 4);
        assertTrue(pointsDePassage.contains(new Coord(0, 4)));
        assertTrue(pointsDePassage.contains(new Coord(0, 3)));
        assertTrue(pointsDePassage.contains(new Coord(0, 2)));
        assertTrue(pointsDePassage.contains(new Coord(0, 1)));
    }
    
    @Test
    public void pointsDePassagePourDeplacementVersLaDroite()
    {
        Deplacement versLaDroite = new Deplacement(0, 0, 2, 0);
        
        List<Coord> pointsDePassage = versLaDroite.getPointsDePassage();
        
        assertTrue(pointsDePassage.size() == 1);
        assertTrue(pointsDePassage.contains(new Coord(1, 0)));
    }
    
    @Test 
    public void pointsDePassagePourDeplacementVersLaGauche()
    {
        Deplacement versLaGauche = new Deplacement(4, 4, 1, 4);
        
        List<Coord> pointsDePassage = versLaGauche.getPointsDePassage();
        
        assertTrue(pointsDePassage.size() == 2);
        assertTrue(pointsDePassage.contains(new Coord(3, 4)));
        assertTrue(pointsDePassage.contains(new Coord(2, 4)));
    }
    
    @Test
    public void pointsDePassagePourDeplacementDiagonalHautGauche()
    {
        Deplacement versHautGauche = new Deplacement(7, 3, 5, 5);
        
        List<Coord> pointsDePassage = versHautGauche.getPointsDePassage();

        assertTrue(pointsDePassage.size() == 1);
        assertTrue(pointsDePassage.contains(new Coord(6, 4)));
    }
    
    @Test
    public void pointsDePassagePourDeplacementDiagonalHautDroit()
    {
        Deplacement versHautDroit = new Deplacement(0, 0, 4, 4);
        
        List<Coord> pointsDePassage = versHautDroit.getPointsDePassage();
        
        assertTrue(pointsDePassage.size() == 3);
        assertTrue(pointsDePassage.contains(new Coord(1, 1)));
        assertTrue(pointsDePassage.contains(new Coord(2, 2)));
        assertTrue(pointsDePassage.contains(new Coord(3, 3)));
        
    }
    
    @Test
    public void pointsDePassagePourDeplacementDiagonalBasGauche()
    {
        Deplacement versBasGauche = new Deplacement(6, 10, 2, 6);
        
        List<Coord> pointsDePassage = versBasGauche.getPointsDePassage();
         
        assertTrue(pointsDePassage.size() == 3);
        assertTrue(pointsDePassage.contains(new Coord(5, 9)));
        assertTrue(pointsDePassage.contains(new Coord(4, 8)));
        assertTrue(pointsDePassage.contains(new Coord(3, 7)));
        
    }
    
    @Test
    public void pointsDePassagePourDeplacementDiagonalBasDroit()
    {
        Deplacement versBasDroit = new Deplacement(42, 24, 45, 21);
        
        List<Coord> pointsDePassage = versBasDroit.getPointsDePassage();
        
        assertTrue(pointsDePassage.size() == 2);
        assertTrue(pointsDePassage.contains(new Coord(43, 23)));
        assertTrue(pointsDePassage.contains(new Coord(44, 22)));
    }
    
    @Test
    public void unDeplacementUneCaseNeDoitPasAvoirDePointDePassage()
    {
        Deplacement uneCase = new Deplacement(0, 0, 1, 1);
        
        assertTrue(uneCase.getPointsDePassage().isEmpty());
    }
    
}
