package ca.bcit.comp2526.a2b;

/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public class Color {

    private final Color color;
    
    Color(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }
    
    public boolean isRed() {
        return true;
    }
        
    
}
