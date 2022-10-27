import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DataStreamsFrame extends JFrame {
    JPanel mainPnl, tAndCPnl, titlePnl, chosenStringPnl, displayPnl, fileDisplayPnl, filterDisplayPnl, buttonPnl;
    JLabel titleLbl, csLbl, fLbl;
    JTextField csTF, fTF;
    JTextArea fileTA, filteredTA;
    JScrollPane scroller;

    String chosenString = "";
    String fileName = "";
    public DataStreamsFrame()
    {
        setTitle("Data Streams");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize((screenWidth /4) * 3 , screenHeight);
        setLocationRelativeTo(null); //centers

        setVisible(true);

        //--------

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        tAndCPnl = new JPanel();
        tAndCPnl.setLayout(new GridLayout(2,1));

        displayPnl = new JPanel();
        displayPnl.setLayout(new GridLayout(1,2));

        mainPnl.add(tAndCPnl, BorderLayout.NORTH);
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        createTitlePanel();
        createChosenStringPnl();
        createFileDisplayPnl();
        createFilterDisplayPnl();
    }

    private void createTitlePanel(){
        titlePnl = new JPanel();

        titleLbl = new JLabel("String Locator", JLabel.CENTER);
        titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        //aligns text and image to be stacked not side by side
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);

        tAndCPnl.add(titlePnl, new GridLayout(1,1));
    }

    private void createChosenStringPnl(){
        chosenStringPnl = new JPanel();

        fLbl = new JLabel("File:");
        fTF = new JTextField(" " + fileName + " ");

        csLbl = new JLabel("Searching For:");
        csTF = new JTextField(" " + chosenString + " ");

        fLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        fTF.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));

        csLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        csTF.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));

        chosenStringPnl.add(fLbl);
        chosenStringPnl.add(fTF);

        chosenStringPnl.add(csLbl);
        chosenStringPnl.add(csTF);

        csTF.setEditable(false);
        fTF.setEditable(false);

        tAndCPnl.add(chosenStringPnl, new GridLayout(2,1));
    }

    private void createFileDisplayPnl(){
        fileDisplayPnl = new JPanel();



        fileTA =  new JTextArea(30, 55);
        scroller = new JScrollPane(fileTA);
        fileTA.setFont(new Font("Monospaced", Font.PLAIN, 16));

        fileDisplayPnl.add(scroller);
        fileTA.setEditable(false);


        displayPnl.add(fileDisplayPnl, new GridLayout(1,1));
    }

    private void createFilterDisplayPnl(){
        filterDisplayPnl = new JPanel();

        filteredTA =  new JTextArea(30, 55);
        scroller = new JScrollPane(filteredTA);
        filteredTA.setFont(new Font("Monospaced", Font.PLAIN, 16));

        filterDisplayPnl.add(scroller);
        filteredTA.setEditable(false);

        displayPnl.add(filterDisplayPnl, new GridLayout(1,2));
    }




}
