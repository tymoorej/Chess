package Pieces;

import BoardHelpers.BoardPosition;
import BoardHelpers.Copyable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Piece implements Copyable<Piece> {
    public abstract int getValue();
    public abstract boolean isMoveValid(BoardPosition start, BoardPosition end);
    public abstract boolean canJumpOverPieces();
    public abstract Colour getColour();
    public BufferedImage getImage(){
        BufferedImage image = null;
        try {
            String pieceName = getPieceName().toLowerCase();
            image = ImageIO.read(new File("Images/" + pieceName + "-" + getColour().name().toLowerCase() + ".png"));
        } catch (Exception ex) {
            System.out.println("error reading file");
        }

        return image;
    }

    protected abstract String getPieceName();

    public int getOrientedValue(){
        if (getColour().equals(Colour.WHITE)){
            return getValue();
        }
        return -1 * getValue();
    }
}
