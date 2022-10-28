import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStreamsFrame extends JFrame {
    JPanel mainPnl, tAndCPnl, titlePnl, chosenStringPnl, displayPnl, fileDisplayPnl, filterDisplayPnl, buttonPnl;
    JLabel titleLbl, csLbl, fLbl;
    JTextField csTF, fTF;
    JTextArea fileTA, filteredTA;
    JScrollPane scroller;
    JButton quitBtn, loadBtn, searchBtn;
    List<String> fileInfo;


    String chosenString = "";



    JFileChooser chooser = new JFileChooser();
    File chosenFile;
    String chosenFileName;






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


        setVisible(true);
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
        fTF = new JTextField("                             ");

        csLbl = new JLabel("Searching For:");
        csTF = new JTextField("                             ");

        fLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        fTF.setFont(new Font("Monospaced", Font.PLAIN, 20));

        csLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        csTF.setFont(new Font("Monospaced", Font.PLAIN, 20));

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



        fileTA =  new JTextArea(30, 90);
        scroller = new JScrollPane(fileTA);
        fileTA.setFont(new Font("Monospaced", Font.PLAIN, 10));

        fileDisplayPnl.add(scroller);
        fileTA.setEditable(false);


        displayPnl.add(fileDisplayPnl, new GridLayout(1,1));
    }

    private void createFilterDisplayPnl(){
        filterDisplayPnl = new JPanel();

        filteredTA =  new JTextArea(30, 90);
        scroller = new JScrollPane(filteredTA);
        filteredTA.setFont(new Font("Monospaced", Font.PLAIN, 10));

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
                }
            }});



        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();

            }
        });
        
    }

    //read the file and the name
    private void readFile(){
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());
        Stream fileLines = null;

        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();

                Scanner inFile = new Scanner(target);
                while (inFile.hasNextLine()) {

                    chosenFile = chooser.getSelectedFile();
                    Path file = chosenFile.toPath();

                    chosenFileName = chosenFile.getName(); //print name of file
                    fTF.setText(chosenFileName);  //          ^+



                    fileInfo = new ArrayList<>();
                    try (Stream<String> stream = Files.lines(Paths.get(String.valueOf(file)))) {

                        fileInfo = stream
                                .map(String::toUpperCase)
                                .collect(Collectors.toList());
                    }

                    //fileInfo.forEach(fileTA::append);
                    for(Object line : fileInfo){
                        fileTA.append(line + "\n");
                    }
                    break;






                    //Stream lines = Files.lines(file); //break apart
                    //fileLines = Files.lines(file);
                    //printFile();
                }
                inFile.close();
            } else {
                fileTA.setText("File not chosen. Try again!");
            }
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
       //return fileLines;
    }

    //print the file with each new line from the file numbered
   /* private void printFile(){
        String lineNum;
        String rec;
        try{
            for(Object line : readFile().toArray()){

                fileTA.append(line + "\n");

                fileInfo.add(String.valueOf(line));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    */
}


    //filter the file


