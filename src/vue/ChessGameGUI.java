
package vue;

import model.*;
import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.ChessImageProvider;
import tools.ChessPiecesFactory;
import tools.ChessSinglePieceFactory;

/**
 *
 * @author beetix
 */
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer{

    ChessGameControlers chessGameControler;
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    int xInit;
    int yInit;
    
    
    
    /**
     * Construit le plateau de l'echiquier sous forme de damier 8*8 et le rend ecoutable
     * par les evenements MouseListener et MouseMotionListener. Sont superposes 1 JPanel
     * pour le plateau et autant de JPanel que de carres noirs ou blancs sur lesquels seront
     * positionnees les pieces aux bonnes coordonnees.
     * Les images des pieces et leurs coordonnees seront fournies par des utilitaires.
     * @param name --nom de la fenetre
     * @param chessGameControler
     * @param boardSize --taille fenetre
     */
    public ChessGameGUI(String name, ChessGameControlers chessGameControler, Dimension boardSize)
    {
        super(name);
        this.chessGameControler = chessGameControler;
        
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
 
         chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height-20);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.DARK_GRAY : Color.white );
            else
                square.setBackground( i % 2 == 0 ? Color.white : Color.DARK_GRAY );
        }
     
    }
    
    @Override
    public void update(Observable arg0, Object arg1)
    {
        JPanel c;
        //System.out.println(chessGameControler.getMessage() + "\n");	
        for(Component comp:chessBoard.getComponents())
        {
            if(comp.getClass().equals(JPanel.class) )
            {
                ((JPanel)comp).removeAll();
                ((JPanel)comp).revalidate();
                ((JPanel)comp).repaint();
            }
        }
        List<PieceIHM> piecesIHM = (List<PieceIHM>) arg1;


        for(PieceIHM pieceihm : piecesIHM)
        {
            
            JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(pieceihm.getNamePiece(), pieceihm.getCouleur()) ) );
            JPanel panel = (JPanel)chessBoard.getComponent((8*pieceihm.getY())+(int)pieceihm.getX());
            panel.add(piece);
            
        }

     }
    
    public void mousePressed(MouseEvent e)
    {
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        
         
        if (c instanceof JPanel || !chessGameControler.isPlayerOK(new Coord(  (int)(e.getX()/87.5) , (int)(e.getY()/87.5) ))) 
        {
            System.out.println("mousePressed : isPlayerOK retourne faux");
            //e.consume();
            return;
        }
        
        
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        xInit= (int)((e.getX())/87.5);
        yInit= (int)((e.getY())/87.5);
        chessPiece = (JLabel)c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }
    
    public void mouseDragged(MouseEvent me)
    {
         if (chessPiece == null )
         {
             //me.consume();
             return;
         }
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    
    }
    public void mouseReleased(MouseEvent e)
    {
        if(chessPiece == null)
        {
            return;
        }
        
        

        chessPiece.setVisible(false);
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
 
        if (!chessGameControler.move(new Coord(xInit,yInit),new Coord((int)((e.getX())/87.5),(int)((e.getY())/87.5) ) ))
        {
            System.out.println("Released - Départ : " + xInit + " " + yInit);
            System.out.println("Released - Arrivé : " + (int)((e.getX()+xAdjustment)/87.5) + " " + (int)((e.getY()+yAdjustment)/87.5));
            System.out.println("Pas résussi - MousseReleased");
            //chessPiece.setVisible(false);
            return;
        }
        
        if(chessGameControler.isPionAPromouvoir())
        {
            JDialog.setDefaultLookAndFeelDecorated(true);
            Object[] selectionValues = { "Reine", "Tour", "Fou","Cavalier" };
            String initialSelection = "Reine";
            Object selection = JOptionPane.showInputDialog(null, "Quelle pièce voulez vous créer ?",
                "Piece Select", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
             
            chessGameControler.newPiece(selection.toString(),(int)((e.getX())/87.5), (int)((e.getY())/87.5));
        }
        
        
    }
    
    
    public void mouseClicked(MouseEvent e)
    {
        for(Component comp:chessBoard.getComponents())
        {
            if(comp.getClass().equals(JPanel.class) )
            {
                ((JPanel)comp).setBorder(null);
            }
        }
       //Component c =  chessBoard.findComponentAt(e.getX()/87, e.getY()/75);
        List<Coord> listeDesCasesPossibles =chessGameControler.getPossibleMovements((int)(e.getX()/87.5), (int)(e.getY()/87.5));
            Iterator<Coord> i = listeDesCasesPossibles.iterator(); 
            while (i.hasNext()) { 
                Coord caseitem =i.next();
                JPanel carreau = (JPanel)chessBoard.getComponent(caseitem.x+8*caseitem.y);
                carreau.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.pink));
                carreau.revalidate();
                carreau.repaint();
            }
        if(chessGameControler.isPlayerOK(new Coord(  (int)(e.getX()/87.5) , (int)(e.getY()/87.5)  ) ) )
        {
            System.out.println((int)(e.getX()/87.5) + " " + (int)(e.getY()/87.5));
            chessGameControler.getMessage();
                
        }
    }
    
    public void mouseMoved(MouseEvent e)
    {
        //System.out.println("MouseMoved");
        //chessGameControler.getMessage();
    }
    public void mouseEntered(MouseEvent e)
    {
      //  System.out.println("mouseEntered");
        chessGameControler.getMessage();
    }
    public void mouseExited(MouseEvent e)
    {
        //System.out.println("mouseExited");
        chessGameControler.getMessage();
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new ChessGameDemo();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
    
    
}
