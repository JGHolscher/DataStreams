import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class DataStreamsFrame extends JFrame {
    JPanel mainPnl, tAndCPnl, titlePnl, chosenStringPnl, displayPnl, fileDisplayPnl, filterDisplayPnl, buttonPnl;
    JLabel titleLbl, csLbl, fLbl;
    JTextField csTF, fTF;
    JTextArea fileTA, filteredTA;
    JScrollPane scroller;
    JButton quitBtn, loadBtn, searchBtn;
    ArrayList<String> fileInfo;


    String chosenString = "";

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

        createTitlePnl();
        createChosenStringPnl();
        createFileDisplayPnl();
        createFilterDisplayPnl();
        createButtonPanel();
    }

    private void createTitlePnl(){
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
        fTF = new JTextField(" ");

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



        fileTA =  new JTextArea(30, 50);
        scroller = new JScrollPane(fileTA);
        fileTA.setFont(new Font("Monospaced", Font.PLAIN, 16));

        fileDisplayPnl.add(scroller);
        fileTA.setEditable(false);


        displayPnl.add(fileDisplayPnl, new GridLayout(1,1));
    }

    private void createFilterDisplayPnl(){
        filterDisplayPnl = new JPanel();

        filteredTA =  new JTextArea(30, 50);
        scroller = new JScrollPane(filteredTA);
        filteredTA.setFont(new Font("Monospaced", Font.PLAIN, 16));

        filterDisplayPnl.add(scroller);
        filteredTA.setEditable(false);

        displayPnl.add(filterDisplayPnl, new GridLayout(1,2));
    }

    private void createButtonPanel() {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 3));

        loadBtn = new JButton("Pick File");
        loadBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
        searchBtn = new JButton("Find String");
        searchBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));

        buttonPnl.add(loadBtn);
        buttonPnl.add(searchBtn);
        buttonPnl.add(quitBtn);

        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        quitBtn.addActionListener(new ActionListener() {//DONE
            JOptionPane pane =new JOptionPane();
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(pane,"Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){System.exit(0);}
                else {setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }}});



        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });
        
    }


    JFileChooser chooser = new JFileChooser();
    String chosenFile;
    //Stream line = null;



    //String line = "";

    


    private void readFile(){
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());


        //Stream lines = Files.lines(Paths.get(FILEPATH))
        
        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();

                Scanner inFile = new Scanner(target);
                while (inFile.hasNextLine()) {

                    chosenFile = chooser.getSelectedFile().getName();
                    fTF.setText(chosenFile);

                }
                inFile.close();
            } else {
                fileTA.setText("File not chosen. Try again!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
