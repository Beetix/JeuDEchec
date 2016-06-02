package model;

/**
 *
 * @author beetix
 */
public class PieceNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "La piece n'a pas été trouvé !";
    }
    
}
