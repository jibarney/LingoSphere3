
import dbObjects.DbResources;
import dbObjects.UserData;
import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;


public class LingoSphere3 extends javax.swing.JFrame {
    
    private DbResources db = new DbResources();
    static String logInMsg = "\nWelcome to Lingo Sphere, the language learning "+
        "tool developed by team JTA to facilitate foreign language learning and lesson "+
 "planning.\n\n"+
        "The program is intended for use by language students who need to review predetermined"+
        " vocabulary lists, instructors who need to set up language drills for class,  "
        + "and the casual user interested in looking for quick translation and proper"
        + " pronunciation of a particular word.  ";
    static String sampleGradesText = "Test #016  12/05/2001   97%\n"+
                                     "Test #141  02/15/2016   pending\n"+
                                     "Test #003               not taken\n";
    
    String userDataFilename = "userdata.txt" ;
   // private ArrayList<User> userList = new ArrayList<>();
    ArrayList<StudyList> myWordLists ;
    private StudyList myLesson ;
    Map.Entry<String,String> currentPair ;
    int hitCount, missCount ;
    private File localfile ;  // Store word lists
    public UserData dbUser = null;
    private TeacherTab tab;
    int idxWelcomeTab, idxStudentTab, idxTeacherTab, idxQuickXlateTab ;
    
    public LingoSphere3() {
        initComponents();
        setLocationRelativeTo(null);
        
        welcomeMsg.setText(logInMsg);
        welcomeMsg.setLineWrap(true);
        welcomeMsg.setWrapStyleWord(true);
        welcomeMsg.setEditable(false); 
        systemMessages.setLineWrap(true);
        systemMessages.setWrapStyleWord(true);
        systemMessages.setEditable(false);
        GradeList.setText(sampleGradesText);
        GradeList.setEditable(false);
        setTitle(" Lingo Sphere German Translator");
        currentPsswdField.setColumns(14);
        currentUserField.setColumns(14);
        newUserField.setColumns(14);
        newPsswdField.setColumns(14);
        existingUserButton.setSelected(true);
        //TeacherPage3 TeacherPages = new TeacherPage3() ;
        QuickTranslation translation = new QuickTranslation();
        
        // Need to debug executable.  Display error in message window.
        systemMessages.setText(db.getSQLSetupStatus());
       try{
           List<UserData> teacherList = db.getTeachers();
            teacherSelect.addItem("Select Instructor");
            
            for(int c = 0; c < teacherList.size(); c++){
                //String name = teacherList.get(c).getUserName();
                teacherSelect.addItem(teacherList.get(c));
            }
            
       }catch(SQLException e){
           System.out.println(e);
       }
       teacherSelect.setEnabled(false);
        
       // Initially, current user area enabled, new user disabled.
        
        Component[] comNU = newUserPanel.getComponents();
        Component[] comCU = currentUserPanel.getComponents();  
        for (int a = 0; a < comCU.length; a++) comCU[a].setEnabled(true);
        for (int a = 0; a < comNU.length; a++) comNU[a].setEnabled(false);
              
    
       /* Since the translation tab is the only one active upon startup,
        make sure it is adjacent to the Welcome tab (index=1).  It just 
        looks better. */
        
        jTabbedPane2.insertTab("Translation", null, translation, null, 1);
        //jTabbedPane2.remove(jPanel3);
        
    /* Create tab functions for both teachers,students and guests. The quick translation
        tab is the only one that will be active until a valid student or 
        teacher id is entered.
     */
        
         tab = new TeacherTab();
         jTabbedPane2.addTab("Teacher Page", tab);
         
         idxStudentTab = jTabbedPane2.indexOfComponent(jPanel3);
         idxTeacherTab = jTabbedPane2.indexOfComponent(tab);
         
         jTabbedPane2.setEnabledAt(idxStudentTab,false) ;
         jTabbedPane2.setEnabledAt(idxTeacherTab, false);
                    
        enablePanel(jPanel9,false);
        logoutButton.setEnabled(false);
        
   /* Read in all lists from the local file "LocalListFile.txt".  If this file
      does not exist, read in "GermanWordList.txt" which contains prebuilt lists
      embedded in the jar file executable.  (Note that embedded resource files must
      be accessed as a resource stream.)    Once the LocalListFile.txt is created, it can
      be edited and overwritten with any local user updates.  The next time the program
      is run, it will read from that file if it is available in the run directory. 
   */
        
        InputStream default_is = getClass().getResourceAsStream("resources/GermanWordList.txt");
        localfile =new File("LocalListFile.txt");
       
        //if file doesnt exists, then create it
        try{ 
              if(!localfile.exists()) 
              {
                  System.out.println("Reading embedded file");
                  localfile.createNewFile();
                  myWordLists = LS_FileIOUtility.readVocabFile(default_is);
                  LS_FileIOUtility.writeUpdatedListFile(localfile,myWordLists) ;
              }
              else
              {
                   System.out.println("Reading local file");
                   InputStream lfile_is = new FileInputStream(localfile);
                   myWordLists = LS_FileIOUtility.readVocabFile(lfile_is);
              }
                  
              
        }
        catch (IOException e){}
        
    // Loop through all the lists and get their names to add to the combo box 
    // selector.
        
        for (StudyList thisList: myWordLists) 
        {
            reviewListComboBox.addItem( thisList.getListName());
        }    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        currentUserPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        currentUserField = new javax.swing.JTextField();
        currentPsswdField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        newUserPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        newUserField = new javax.swing.JTextField();
        newPsswdField = new javax.swing.JTextField();
        classNum = new javax.swing.JComboBox();
        accountType = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        teacherSelect = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        existingUserButton = new javax.swing.JRadioButton();
        newUserButton = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        systemMessages = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        welcomeMsg = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        reviewListComboBox = new javax.swing.JComboBox();
        startReviewButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        correctAnswer = new javax.swing.JLabel();
        germanWordField = new javax.swing.JLabel();
        studentTranslation = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        overrideMiss = new javax.swing.JButton();
        inputTranslation = new javax.swing.JButton();
        shuffleReset = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        hitCountLabel = new javax.swing.JLabel();
        missCountLabel = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        finalScore = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        GradeList = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Lingo Sphere"); // NOI18N

        jTabbedPane2.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jTabbedPane2.setName("LingoSphere German Translator"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        currentUserPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel3.setText("Username:");

        jLabel4.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel4.setText("Password:");

        jLabel5.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel5.setText("Please log in:");

        currentUserField.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        currentUserField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentUserFieldActionPerformed(evt);
            }
        });

        currentPsswdField.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel6.setText("Current User");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout currentUserPanelLayout = new javax.swing.GroupLayout(currentUserPanel);
        currentUserPanel.setLayout(currentUserPanelLayout);
        currentUserPanelLayout.setHorizontalGroup(
            currentUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentUserPanelLayout.createSequentialGroup()
                .addGroup(currentUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(currentUserPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(currentUserPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(currentUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(currentUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currentPsswdField, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(currentUserField)))
                    .addGroup(currentUserPanelLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel6)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        currentUserPanelLayout.setVerticalGroup(
            currentUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(currentUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentUserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(currentUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(currentPsswdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.getAccessibleContext().setAccessibleName(" Current User ");

        newUserPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel7.setText("New User");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel8.setText("Please create an account:");

        jLabel9.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel9.setText("New Username");

        jLabel10.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel10.setText("New Password");

        jLabel11.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel11.setText("Class Enrolled");

        jLabel12.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel12.setText("Account Type");

        newUserField.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        newUserField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserFieldActionPerformed(evt);
            }
        });

        newPsswdField.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        newPsswdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPsswdFieldActionPerformed(evt);
            }
        });

        classNum.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        classNum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GRM101", "SPN300", "JPN220", "FRN102" }));

        accountType.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        accountType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student", "Instructor" }));
        accountType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountTypeActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Instructor");
        jLabel17.setToolTipText("");

        javax.swing.GroupLayout newUserPanelLayout = new javax.swing.GroupLayout(newUserPanel);
        newUserPanel.setLayout(newUserPanelLayout);
        newUserPanelLayout.setHorizontalGroup(
            newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUserPanelLayout.createSequentialGroup()
                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newUserPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(newUserPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(newUserField, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(newUserPanelLayout.createSequentialGroup()
                                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(newUserPanelLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel10))
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(classNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(accountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(newPsswdField)))
                            .addComponent(jLabel8)))
                    .addGroup(newUserPanelLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel7))
                    .addGroup(newUserPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(teacherSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        newUserPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {newPsswdField, newUserField});

        newUserPanelLayout.setVerticalGroup(
            newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(newUserField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(newPsswdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(classNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(accountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(teacherSelect))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup5.add(existingUserButton);
        existingUserButton.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        existingUserButton.setSelected(true);
        existingUserButton.setText("Existing Account");
        existingUserButton.setActionCommand("existingUser");
        existingUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existingUserButtonActionPerformed(evt);
            }
        });

        buttonGroup5.add(newUserButton);
        newUserButton.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        newUserButton.setText("New Account");
        newUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        systemMessages.setColumns(20);
        systemMessages.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        systemMessages.setForeground(new java.awt.Color(255, 0, 0));
        systemMessages.setRows(5);
        systemMessages.setText("System messages here");
        jScrollPane1.setViewportView(systemMessages);

        jLabel13.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel13.setText("Select New or Existing Account");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(existingUserButton)
                        .addGap(18, 18, 18)
                        .addComponent(newUserButton)
                        .addGap(0, 72, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(87, 87, 87))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existingUserButton)
                    .addComponent(newUserButton))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/LSlogo2.jpg"))); // NOI18N

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBar(null);
        jScrollPane2.setWheelScrollingEnabled(false);

        welcomeMsg.setColumns(20);
        welcomeMsg.setFont(new java.awt.Font("Serif", 2, 24)); // NOI18N
        welcomeMsg.setRows(8);
        jScrollPane2.setViewportView(welcomeMsg);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        loginButton.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        logoutButton.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(430, 430, 430)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(425, 425, 425))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {loginButton, logoutButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {loginButton, logoutButton});

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(currentUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 28, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentUserPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newUserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Welcome", jPanel5);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("Welcome to German 103\nSection : 11123\nInstructor: Oscar Meyer");
        jScrollPane3.setViewportView(jTextArea1);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/germanFlag.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Vocabulary Review");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Select list or category");

        jLabel16.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("and hit 'Start Review'");

        reviewListComboBox.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        reviewListComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Numbers", "Verbs", "Animals" }));
        reviewListComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewListComboBoxActionPerformed(evt);
            }
        });

        startReviewButton.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        startReviewButton.setText("Start Review");
        startReviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startReviewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reviewListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startReviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel15, jLabel16, jLabel2, reviewListComboBox, startReviewButton});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(reviewListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startReviewButton, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        correctAnswer.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        correctAnswer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        correctAnswer.setText("Correct Word");
        correctAnswer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        germanWordField.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        germanWordField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        germanWordField.setText("German Word");
        germanWordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        studentTranslation.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        studentTranslation.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        studentTranslation.setText("English Word");
        studentTranslation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(germanWordField, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(correctAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(studentTranslation, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {correctAnswer, germanWordField, studentTranslation});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(germanWordField, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(studentTranslation, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                .addComponent(correctAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {correctAnswer, germanWordField, studentTranslation});

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        overrideMiss.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        overrideMiss.setText("Override Miss");
        overrideMiss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overrideMissActionPerformed(evt);
            }
        });

        inputTranslation.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        inputTranslation.setText("Submit Response");
        inputTranslation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTranslationActionPerformed(evt);
            }
        });

        shuffleReset.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        shuffleReset.setText("Shuffle and Reset List");
        shuffleReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shuffleResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(overrideMiss, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shuffleReset, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(inputTranslation, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {overrideMiss, shuffleReset});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputTranslation, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(overrideMiss, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shuffleReset, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel19.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel19.setText("Hit Count:");

        jLabel20.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel20.setText("Miss Count: ");

        hitCountLabel.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        hitCountLabel.setForeground(new java.awt.Color(0, 153, 51));
        hitCountLabel.setText("000");

        missCountLabel.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        missCountLabel.setForeground(new java.awt.Color(255, 0, 51));
        missCountLabel.setText("000");

        jLabel24.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel24.setText("Final Score: ");

        finalScore.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        finalScore.setText("- - - % ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(missCountLabel)
                            .addComponent(hitCountLabel)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel24)
                        .addGap(28, 28, 28)
                        .addComponent(finalScore)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(hitCountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(missCountLabel))
                .addGap(17, 17, 17)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finalScore, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), new java.awt.Color(153, 153, 153))));

        jLabel25.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Graded Test Scores");
        jLabel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        GradeList.setColumns(20);
        GradeList.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        GradeList.setRows(5);
        jScrollPane4.setViewportView(GradeList);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel8, jPanel9});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel11, jPanel6});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel4, jPanel6, jPanel8});

        jTabbedPane2.addTab("Student Self Test", jPanel3);

        getContentPane().add(jTabbedPane2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserButtonActionPerformed
        enablePanel(currentUserPanel,false);
        enablePanel(newUserPanel,true);
        //   newUserFlag = false;
    }//GEN-LAST:event_newUserButtonActionPerformed

    private void existingUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existingUserButtonActionPerformed
        enablePanel(currentUserPanel,true);
        enablePanel(newUserPanel,false);
        //   newUserFlag = false;
    }//GEN-LAST:event_existingUserButtonActionPerformed

    private void accountTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountTypeActionPerformed
        if(String.valueOf(accountType.getSelectedItem()).equalsIgnoreCase("Student")){
            teacherSelect.setEnabled(true);
        }else{
            teacherSelect.setEnabled(false);
        }
    }//GEN-LAST:event_accountTypeActionPerformed

    private void newPsswdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPsswdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPsswdFieldActionPerformed

    private void newUserFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newUserFieldActionPerformed

    private void currentUserFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentUserFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentUserFieldActionPerformed

    private void startReviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startReviewButtonActionPerformed
        String chosenList = reviewListComboBox.getSelectedItem().toString() ;
        
        for (StudyList thisList: myWordLists) 
        {
           if (chosenList.equals(thisList.getListName())) 
           {
               myLesson = thisList; 
               myLesson.initiateList();
               initializeStudentReview() ;
               startReviewButton.setEnabled(false) ;
               reviewListComboBox.setEnabled(false);
             
           }
        } 
    }//GEN-LAST:event_startReviewButtonActionPerformed

    private void reviewListComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewListComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewListComboBoxActionPerformed

    private void overrideMissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overrideMissActionPerformed
         if (missCount>0)
        {
            missCount--;
            hitCount++;
            hitCountLabel.setText(Integer.toString(hitCount)); 
            missCountLabel.setText(Integer.toString(missCount)); 
        }
    }//GEN-LAST:event_overrideMissActionPerformed

    private void shuffleResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shuffleResetActionPerformed
         myLesson.shuffleMap() ;
        initializeStudentReview() ;
    }//GEN-LAST:event_shuffleResetActionPerformed

    private void inputTranslationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTranslationActionPerformed
         if (inputTranslation.getText().equals("Submit Response"))
        {
             correctAnswer.setText(currentPair.getKey());
             if (currentPair.getKey().equals( studentTranslation.getText().trim()))
             {
                correctAnswer.setForeground(new Color(0x009933));  // Nicer shade of green
                hitCount++ ;
                 hitCountLabel.setText(Integer.toString(hitCount));
             }
             else
             {
                 correctAnswer.setForeground(Color.RED) ;
                 missCount++ ;
                 missCountLabel.setText(Integer.toString(missCount));   
             }
             inputTranslation.setText("Next Item");
        }
        else
        {
            inputTranslation.setText("Submit Response");
            currentPair = myLesson.getNextEntry();
            if (currentPair == null)
            {
                int pct = hitCount*100/(hitCount+missCount) ;
                finalScore.setText(pct+"%");  
                inputTranslation.setEnabled(false);
                startReviewButton.setEnabled(true) ;
                reviewListComboBox.setEnabled(true);
            }
            else
            {
               germanWordField.setText(currentPair.getValue());
               studentTranslation.setText("");
               correctAnswer.setText("");
            } 
        }
    }//GEN-LAST:event_inputTranslationActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        boolean validLogin = false;
        // User userFound = new User();
      //  UserData dbUser = null;

        if (newUserButton.isSelected())
        {
            String newUsername = newUserField.getText() ;
            String newPsswd = newPsswdField.getText() ;
            Boolean isTeacher = false;
            int teacherId = -1;
            if(String.valueOf(accountType.getSelectedItem()).equalsIgnoreCase("Instructor")){
                isTeacher = true;
                jTabbedPane2.setEnabledAt(idxStudentTab,false) ;
                jTabbedPane2.setEnabledAt(idxTeacherTab, true);
            }else{
                //UserData teacher = (UserData)teacherSelect.getSelectedItem();
                //teacherId = teacher.getUserId();
                jTabbedPane2.setEnabledAt(idxStudentTab,true) ;
                jTabbedPane2.setEnabledAt(idxTeacherTab, false);
            }
            

            if ((newUsername.length() < 5) || (newPsswd.length() < 5))
            {
                systemMessages.setText("Password and username must be at least 5 characters");
            }
            else
            {
                // userFound = addUser(userDataFilename) ;
                //        systemMessages.setText(userFound.welcomeUser());

                enablePanel(newUserPanel,false);

                try{
                    dbUser = db.createUser(newUsername , newPsswd, isTeacher, teacherId);
                    systemMessages.setText(dbUser.welcomeUser());
                    loginButton.setEnabled(false);
                    logoutButton.setEnabled(true);
                }
                catch(SQLException ex){
                    System.out.println("e: " + ex);
                    systemMessages.setText("SQL create user error:"+ex);
                }
            }
        }
        else  // Look for existing user
        {
            //userList = getUsers(userDataFilename);
            //for (User user: userList){
                try{
                    dbUser = db.getUser(currentUserField.getText() , currentPsswdField.getText());
                }
                catch(SQLException ex){
                    System.out.println("e: " + ex);
                }
                if (dbUser != null)
                {
                    if(dbUser.isIsTeacher()){
                        jTabbedPane2.setEnabledAt(idxStudentTab,false) ;
                        jTabbedPane2.setEnabledAt(idxTeacherTab, true);
                       //JIB tab = new TeacherTab(dbUser);
                       //JIB jTabbedPane2.addTab("Teacher Page", tab);
                    }
                    else{
                      jTabbedPane2.setEnabledAt(idxStudentTab,true) ;
                      jTabbedPane2.setEnabledAt(idxTeacherTab, false);  
                      //JIB  jTabbedPane2.add("Student Self Test", jPanel3);
                    }
                    validLogin = true;
                    loginButton.setEnabled(false);
                    logoutButton.setEnabled(true);
                    //userFound = new User(user);
                }
                //}
            if (!validLogin)
            systemMessages.setText(" Invalid login. Please try again.");
            else
            {
                //  systemMessages.setText(userFound.welcomeUser());
                systemMessages.setText(dbUser.welcomeUser() );
                enablePanel(currentUserPanel,false);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        systemMessages.setText("Logging out user "+dbUser.getUserName()) ;
        logoutButton.setEnabled(false);
        loginButton.setEnabled(true);
        jTabbedPane2.setEnabledAt(idxStudentTab,false) ;
         jTabbedPane2.setEnabledAt(idxTeacherTab, false);
        
       /* if(dbUser.isIsTeacher()){
            jTabbedPane2.remove(tab);         
        }
        else{
            jTabbedPane2.remove(jPanel3);
        }*/
        
        enablePanel(currentUserPanel,true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LingoSphere3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LingoSphere3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LingoSphere3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LingoSphere3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LingoSphere3().setVisible(true);
            }
        });
    }  
    
 // Utility to disable/enable all components in a panel
    void enablePanel(JPanel panel, boolean enabled)
    {
          Component[] panelIO = panel.getComponents();
          for (int a = 0; a < panelIO.length; a++) {
                     panelIO[a].setEnabled(enabled);
          }
    }
    
     void initializeStudentReview()
    {
               currentPair = myLesson.getNextEntry();
               germanWordField.setText(currentPair.getValue());
               studentTranslation.setText("");
               correctAnswer.setText("");
               hitCount = 0;
               missCount = 0;
               //inputTranslation.setEnabled(true);
               enablePanel(jPanel9,true);
               hitCountLabel.setText("0");
               missCountLabel.setText("0");
               finalScore.setText("---%");   
        
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea GradeList;
    private javax.swing.JComboBox accountType;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JComboBox classNum;
    private javax.swing.JLabel correctAnswer;
    private javax.swing.JTextField currentPsswdField;
    private javax.swing.JTextField currentUserField;
    private javax.swing.JPanel currentUserPanel;
    private javax.swing.JRadioButton existingUserButton;
    private javax.swing.JLabel finalScore;
    private javax.swing.JLabel germanWordField;
    private javax.swing.JLabel hitCountLabel;
    private javax.swing.JButton inputTranslation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel missCountLabel;
    private javax.swing.JTextField newPsswdField;
    private javax.swing.JRadioButton newUserButton;
    private javax.swing.JTextField newUserField;
    private javax.swing.JPanel newUserPanel;
    private javax.swing.JButton overrideMiss;
    private javax.swing.JComboBox reviewListComboBox;
    private javax.swing.JButton shuffleReset;
    private javax.swing.JButton startReviewButton;
    private javax.swing.JTextField studentTranslation;
    private javax.swing.JTextArea systemMessages;
    private javax.swing.JComboBox teacherSelect;
    private javax.swing.JTextArea welcomeMsg;
    // End of variables declaration//GEN-END:variables
}
