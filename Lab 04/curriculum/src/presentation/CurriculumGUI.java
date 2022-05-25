package presentation; 
  

 
import domain.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * @version ECI 2019-02
 */
public class CurriculumGUI extends JFrame{
    private static final Dimension PREFERRED_DIMENSION =
                         new Dimension(450,450);

    private Curriculum collection;

    /*Panel buttonList*/
    private JButton buttonList;
    private JButton buttonRestartList;
    private JTextArea textInformation;
    
    /*Panel buttonAdd*/
    private JTextField textCode;   
    private JTextField textName;
    private JTextField textCredits;
    private JTextArea textResults;

    
    private JButton buttonAdd;
    private JButton buttonRestartAdd;
    
    /*Panel search*/
    private JTextField queryText;
    private JTextArea answersText;
    

    
    
    private CurriculumGUI() {
        collection=new Curriculum();
        try{
            collection.addSimpleActivities();
        } catch (ActivityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        prepareElements();
        prepareActions();
    }


    private void prepareElements(){
        setTitle("Plan 14. Ingenieria de Sistemas.");
        textCode = new JTextField(50);
        textName = new JTextField(50);
        textCredits = new JTextField(50);
        textResults = new JTextArea(10, 50);
        textResults.setLineWrap(true);
        textResults.setWrapStyleWord(true);
        JTabbedPane labels = new JTabbedPane();
        labels.add("Listar",   prepareList());
        labels.add("Adicionar",  prepareAdd());
        labels.add("Buscar", prepareSearch());
        getContentPane().add(labels);
        setSize(PREFERRED_DIMENSION);
        
    }


    /**
     * Return the panel to list
     * @return
     */
    private JPanel prepareList(){

        textInformation = new JTextArea(10, 50);
        textInformation.setEditable(false);
        textInformation.setLineWrap(true);
        textInformation.setWrapStyleWord(true);
        JScrollPane scrollArea =
                new JScrollPane(textInformation,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                
        JPanel  buttons = new JPanel();
        buttonList = new JButton("Listar");
        buttonRestartList = new JButton("Limpiar");
        buttons.add(buttonList);
        buttons.add(buttonRestartList);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollArea, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
     }
     
    /**
     * Return the panel to add
     * @return 
     */
    private JPanel prepareAdd(){
        JPanel singleLineFields=new JPanel();
        singleLineFields.setLayout(new GridLayout(7,1));
        singleLineFields.add(new JLabel("Sigla"));
        singleLineFields.add(textCode);
        singleLineFields.add(new JLabel("Nombre"));
        singleLineFields.add(textName);
        singleLineFields.add(new JLabel("Creditos"));
        singleLineFields.add(textCredits);        
        singleLineFields.add(new JLabel("Resultados de Aprendizaje"));
        JPanel textInformationPanel = new JPanel();
        textInformationPanel.setLayout(new BorderLayout());
        textInformationPanel.add(singleLineFields, BorderLayout.NORTH);
        textInformationPanel.add(textResults, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttonAdd = new JButton("Adicionar");
        buttonRestartAdd = new JButton("Limpiar");
        buttons.add(buttonAdd);
        buttons.add(buttonRestartAdd);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textInformationPanel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        return panel;
    }

    
   /**
     * Return the panel to search
     * @return 
     */
    private JPanel prepareSearch(){
        JPanel searchArea=new JPanel();
        searchArea.setLayout(new GridLayout(2,1));
        searchArea.add(new JLabel("Patron"));
        queryText = new JTextField(50);
        searchArea.add(queryText);
        
        answersText = new JTextArea(10,50);
        answersText.setEditable(false);
        answersText.setLineWrap(true);
        answersText.setWrapStyleWord(true);
        JScrollPane scrollArea = new JScrollPane(answersText,
                                     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                     JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel buttonListArea = new JPanel();
        buttonListArea.setLayout(new BorderLayout());
        buttonListArea.add(searchArea, BorderLayout.NORTH);
        buttonListArea.add(scrollArea, BorderLayout.CENTER);

        return buttonListArea;
    }


    private void prepareActions(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buttonList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                actionList();
            }
        });

        buttonRestartList.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                actionRestartList();
            }
        });
        
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                actionAdd();                    
            }
        });
        
        buttonRestartAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev){
                actionRestartAdd();
            }
        });
        

        queryText.getDocument().addDocumentListener(new DocumentListener(){
            public void changedUpdate(DocumentEvent ev){
                actionSearch();
            }
           
            public void insertUpdate(DocumentEvent ev){
                actionSearch();
            }
            
            public void removeUpdate(DocumentEvent ev){
                actionSearch();
            }
            
        });
    }    

 
    private void actionList(){
        textInformation.setText(collection.toString());
    }
    
    private void actionRestartList(){
       textInformation.setText("");
    }
    
    private void actionAdd(){
        try{
            collection.addSimple(textCode.getText(),textName.getText(),textCredits.getText(), textResults.getText());
        } catch (ActivityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }

    private void actionRestartAdd(){
         textCode.setText("");
         textName.setText("");
         textCredits.setText("");
         textResults.setText("");
    }
    
    private void actionSearch(){
        try{
            String queryPattern=queryText.getText();
            StringBuffer buffer = new StringBuffer();
            if(queryPattern.length() > 0) {
                ArrayList <Activity> results = collection.search(queryPattern);
                for(int i = 0; i < results.size(); i++) {
                    buffer.append(results.get(i).toString());
                    buffer.append('\n');
                    buffer.append('\n');
                 }
            }
            answersText.setText(buffer.toString());
        }catch (NullPointerException e){
            Log.record(e);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en la ejecución");
        } catch (Exception e){
            Log.record(e);
            JOptionPane.showMessageDialog(null, "Error, el programa se cerrará");
            System.exit(-1);
        }
    } 
    
   public static void main(String args[]){
       CurriculumGUI gui=new CurriculumGUI();
       gui.setVisible(true);
   }    
}
