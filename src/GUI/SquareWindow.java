package GUI;

import pieces.AbstractPiece;
import player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class SquareWindow extends JLayeredPane {
    public final static int SQUARE_SIZE = 90;

    private final static String fillCircle = "img/fillCircle.png";
    private final static String circle = "img/circle.png";

    private final int id;
    private final ChessGameWindow chessBoard;
    private JLabel highlightIcon;
    private JLabel pieceImage;

    public SquareWindow(int squareId, ChessGameWindow chessBoard, Color squareColor){
        this.id = squareId;
        this.chessBoard = chessBoard;
        this.setBounds(0,0,SQUARE_SIZE,SQUARE_SIZE);
        this.setBackground(squareColor);
        this.setOpaque(true);
        this.assignPieceImage();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    if(chessBoard.getSelectedSquare() == null){
                        chessBoard.firstClickAction(chessBoard.getBoard().getSquare(id));
                    }
                    else{
                        chessBoard.secondClickAction(chessBoard.getBoard().getSquare(id));
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    if(chessBoard.getSelectedSquare() == null){
                        chessBoard.firstClickAction(chessBoard.getBoard().getSquare(id));
                    }
                    else{
                        chessBoard.secondClickAction(chessBoard.getBoard().getSquare(id));
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public int getId() {
        return id;
    }


    public void highlightSquare(boolean on){
        if(on) {
            String imageSrc = this.chessBoard.getBoard().getSquare(id).isOccupied() ? circle : fillCircle;
                try {
                    this.highlightIcon = new JLabel(new ImageIcon(ImageIO.read(new File(imageSrc))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            this.highlightIcon.setBackground(null);
            this.highlightIcon.setBounds(5,4,90,90);
            this.highlightIcon.setOpaque(true);
            this.add(highlightIcon, DEFAULT_LAYER);
            } else{
            this.remove(this.highlightIcon);
            this.repaint();
            this.revalidate();
        }
    }

    private void assignPieceImage(){
        AbstractPiece piece = chessBoard.getBoard().getSquare(id).getPiece();
        if(piece != null){
            String pieceColor = piece.getPieceColor() == Player.BLACK ? "b" : "w";
            try {
                pieceImage = new JLabel(new ImageIcon(ImageIO.read(new File("img/" +
                        pieceColor+ piece.getPieceTypes().toString() +".png"))));

            }
            catch (final IOException e) {
                e.printStackTrace();
            }
            pieceImage.setBounds(6,4,SQUARE_SIZE,SQUARE_SIZE);
            add(pieceImage, JLayeredPane.DRAG_LAYER);
        }
    }

}
