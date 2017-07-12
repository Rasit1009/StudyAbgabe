package pp2017.team10.client.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import pp2017.team10.client.engine.ClientEngineGUI;
import pp2017.team10.shared.Character;


/**
 *
 * @author Computer
 */
public class spielwelt extends javax.swing.JFrame {

    private final int screenWidth;
    private final int screenHeight;
    private final Dimension itemPanelSize;
    private final Dimension worldPanelSize;
    private final Dimension mainPanelSize;
    private ArrayList<JPanel> jp = new ArrayList<JPanel>();
    private ArrayList<JLabel> jl = new ArrayList<JLabel>();
    public boolean showChat = true;
    private int playerPosX;
    private int playerPosY;
    public int world[][] = new int[50][50];
    public JLabel playerOnField = new JLabel();
    private int delay = 8;
    private JTextArea chat1;
    private JTextField Msg;
    private JLayeredPane jlp = new JLayeredPane();
    
    /**
     * Creates new form spielwelt
     */
    
    private ImageIcon getImage(String name){
        BufferedImage bi = null;
        try{
            bi = ImageIO.read(getClass().getResource(name));
        }catch(Exception e){
            System.out.println(e);
        }
        Image dimg = bi.getScaledInstance(screenWidth/50, screenWidth/50,Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(dimg);
        return img;
    }
    
    public void movePlayer(String direction) throws InterruptedException{
    	System.out.println(playerPosX/(screenWidth/50)+  "," + playerPosY/(screenWidth/50));    	
    	ClientEngineGUI n = new ClientEngineGUI();
    	n.consistency(world, direction, playerPosX/(screenWidth/50), playerPosY/(screenWidth/50));
    	System.out.println(n.isPossible);   	
    	
//    	if (n.isPossible == true){	
          if(direction.equals("right")){  
          ActionListener taskPerformer = new ActionListener() {
              int count=0;
              public void actionPerformed(ActionEvent evt) {
                   if(count>((screenWidth/50)%1000)-2) {//we did the task 10 times
                         ((Timer)evt.getSource()).stop();
                    }
                    if(playerPosX < screenWidth-(screenWidth/50))
                        playerPosX++;
                    playerOnField.setLocation(playerPosX, playerPosY);
                    count++;
              }
          };
          new Timer(delay, taskPerformer).start();
          }
          if(direction.equals("up")){
          ActionListener taskPerformer = new ActionListener() {
              int count=0;
              public void actionPerformed(ActionEvent evt) {
                   if(count>((screenWidth/50)%1000)-2) {//we did the task 10 times
                         ((Timer)evt.getSource()).stop();
                    }
                    if(playerPosY > 0)
                        playerPosY--;
                    playerOnField.setLocation(playerPosX, playerPosY);
                    count++;
              }
          };
          new Timer(delay, taskPerformer).start();
          }
          if(direction.equals("left")){
          ActionListener taskPerformer = new ActionListener() {
              int count=0;
              public void actionPerformed(ActionEvent evt) {
                   if(count>((screenWidth/50)%1000)-2) {//we did the task 10 times
                         ((Timer)evt.getSource()).stop();
                    }
                    if(playerPosX > 0)
                        playerPosX--;
                    playerOnField.setLocation(playerPosX, playerPosY);
                    count++;
              }
          };
          new Timer(delay, taskPerformer).start();
          }
          if(direction.equals("down")){
          ActionListener taskPerformer = new ActionListener() {
              int count=0;
              public void actionPerformed(ActionEvent evt) {
                   if(count>((screenWidth/50)%1000)-2) {//we did the task 10 times
                         ((Timer)evt.getSource()).stop();
                    }
                    if(playerPosY < (screenWidth-(screenWidth/50)))
                        playerPosY++;
                    playerOnField.setLocation(playerPosX, playerPosY);
                    count++;
              }
          };
          new Timer(delay, taskPerformer).start();
          }
    	
    }
    
    public spielwelt() throws IOException {
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                world[i][j] = 0;
            }
            world[i][i] = 1;
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   
        int screen = (int) screenSize.getHeight() - 100;
        screenHeight = (screen - screen%50);
        screenWidth = screenHeight - 100;
        itemPanelSize = new Dimension(screenWidth, 100);
        worldPanelSize = new Dimension(screenWidth, screenWidth);
        mainPanelSize = new Dimension(screenWidth, screenHeight);
        initComponents();
        
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                try {

                        if(e.getKeyCode()==37 && playerPosX != 0){
                    movePlayer("left");
                }
                else
                    if(e.getKeyCode()==38 && playerPosY != 0){
                        movePlayer("up");
                    }
                    else
                        if(e.getKeyCode()==39 && playerPosX != screenWidth){
                    
                        movePlayer("right");
                    
                        }
                        else
                            if(e.getKeyCode()==40 && playerPosY != screenWidth){
                                movePlayer("down");
                            }
                
                } catch (InterruptedException ex) {
                        Logger.getLogger(spielwelt.class.getName()).log(Level.SEVERE, null, ex);
                    }
                playerOnField.setOpaque(false);
                jlp.repaint();
                showChat();
                if(e.getKeyCode()== 67){
                    chatInput.setEnabled(true);
                    chatInput.requestFocus();
                }
                if(e.getKeyChar() == 'x'){
                    if(chatButton.getText() == "Chat verbergen"){
                        chatWindow.setVisible(false);
                        chatButton.setText("Chat anzeigen");
                        showChat = false;


                    }
                    else{
                        chatButton.setText("Chat verbergen");
                        chatWindow.setVisible(true);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
        
        
        chatWindow.setOpaque(false);
        chatWindow.addKeyListener(kl);
        chatText.setEditable(false);
        chatText.setLineWrap(true);
        chatText.addKeyListener(kl);
        chatInput.setEnabled(false);
        chatInput.addActionListener(new tHandler());
        
        
        this.setSize(screenWidth + 6, screenHeight + 28);
        this.setLocationRelativeTo(null);
        
        ImageIcon playerIcon = getImage("frame-2.png");
        ImageIcon groundIcon = getImage("boden.jpg");
        ImageIcon wallIcon = getImage("stone.png");
        
        playerOnField.setSize(screenWidth/50, screenWidth/50);
        playerOnField.setIcon(playerIcon);
        
        
        jlp.setSize(screenWidth, screenWidth);
        worldPanel.add(jlp);
        
        playerPosX = 0;
        playerPosY = 0;
        playerOnField.setLocation(playerPosX, playerPosY);
    	System.out.println(playerPosX/(screenWidth/50)+  "," + playerPosY/(screenWidth/50)); 
        
        chatButton.addKeyListener(kl);
        mainPanel.setSize(screenWidth, screenHeight);
        mainPanel.setPreferredSize(mainPanelSize);
        worldPanel.setLocation(screenWidth, screenWidth);
        worldPanel.setPreferredSize(worldPanelSize);
        mainItemPanel.setSize(screenWidth, 100);
        mainItemPanel.setPreferredSize(itemPanelSize);
        JLabel groundImageLabel = new JLabel();
        
        for(int j = 0; j < 50; j++){    
            for(int i = 0; i < 50; i++){
                groundImageLabel = new JLabel();
                groundImageLabel.setSize(screenWidth/50, screenWidth/50);
                groundImageLabel.setOpaque(false);
                switch (world[i][j]){
                    case 0: groundImageLabel.setIcon(groundIcon);
                            break;
                    case 1: groundImageLabel.setIcon(wallIcon);
                }
                jlp.add(groundImageLabel, 1);
                groundImageLabel.setLocation(i*screenWidth/50, j*screenWidth/50);
            }
        }
        jlp.add(playerOnField, 0);
        healthBar.setMaximum(100);
        healthBar.setValue(50);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        worldPanel = new javax.swing.JPanel();
        chatWindow = new javax.swing.JPanel();
        chatScroll = new javax.swing.JScrollPane();
        chatText = new javax.swing.JTextArea();
        chatInput = new javax.swing.JTextField();
        mainItemPanel = new javax.swing.JPanel();
        chatButton = new javax.swing.JButton();
        healthBar = new javax.swing.JProgressBar();
        itemPanel = new javax.swing.JPanel();
        itemLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Study Dungeons");
        setMinimumSize(new java.awt.Dimension(700, 800));
        setName("mainFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 800));
        setResizable(false);
        setSize(new java.awt.Dimension(700, 800));

        mainPanel.setBackground(new java.awt.Color(102, 255, 51));
        mainPanel.setName("mainPanel"); // NOI18N

        worldPanel.setBackground(new java.awt.Color(255, 153, 153));
        worldPanel.setName("worldPanel"); // NOI18N

        chatScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        chatText.setColumns(20);
        chatText.setRows(5);
        chatScroll.setViewportView(chatText);

        chatInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatInputMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout chatWindowLayout = new javax.swing.GroupLayout(chatWindow);
        chatWindow.setLayout(chatWindowLayout);
        chatWindowLayout.setHorizontalGroup(
            chatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatWindowLayout.createSequentialGroup()
                .addGroup(chatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chatScroll, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chatInput, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        chatWindowLayout.setVerticalGroup(
            chatWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatWindowLayout.createSequentialGroup()
                .addComponent(chatScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout worldPanelLayout = new javax.swing.GroupLayout(worldPanel);
        worldPanel.setLayout(worldPanelLayout);
        worldPanelLayout.setHorizontalGroup(
            worldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(worldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        worldPanelLayout.setVerticalGroup(
            worldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, worldPanelLayout.createSequentialGroup()
                .addContainerGap(532, Short.MAX_VALUE)
                .addComponent(chatWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainItemPanel.setBackground(new java.awt.Color(0, 153, 255));
        mainItemPanel.setName("mainItemPanel"); // NOI18N
        mainItemPanel.setPreferredSize(new java.awt.Dimension(600, 100));

        chatButton.setFont(new java.awt.Font("Raven Song", 1, 14)); // NOI18N
        chatButton.setText("Chat verbergen");
        chatButton.setName("chatButton"); // NOI18N
        chatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatButtonActionPerformed(evt);
            }
        });

        healthBar.setBackground(new java.awt.Color(255, 51, 51));
        healthBar.setForeground(new java.awt.Color(51, 255, 0));
        healthBar.setName("healthBar"); // NOI18N

        itemPanel.setName("itemPanel"); // NOI18N

        itemLabel.setText("jLabel3");

        javax.swing.GroupLayout itemPanelLayout = new javax.swing.GroupLayout(itemPanel);
        itemPanel.setLayout(itemPanelLayout);
        itemPanelLayout.setHorizontalGroup(
            itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanelLayout.createSequentialGroup()
                .addComponent(itemLabel)
                .addGap(0, 181, Short.MAX_VALUE))
        );
        itemPanelLayout.setVerticalGroup(
            itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanelLayout.createSequentialGroup()
                .addComponent(itemLabel)
                .addGap(0, 46, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Raven Song", 1, 14)); // NOI18N
        jLabel1.setText("Items");
        jLabel1.setName("itemLabel"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Raven Song", 1, 18)); // NOI18N
        jLabel2.setText("Lebenspunkte");

        javax.swing.GroupLayout mainItemPanelLayout = new javax.swing.GroupLayout(mainItemPanel);
        mainItemPanel.setLayout(mainItemPanelLayout);
        mainItemPanelLayout.setHorizontalGroup(
            mainItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addGroup(mainItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(healthBar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainItemPanelLayout.createSequentialGroup()
                        .addComponent(itemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(mainItemPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(66, 66, 66))))
        );
        mainItemPanelLayout.setVerticalGroup(
            mainItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainItemPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(healthBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainItemPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(itemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(mainItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
            .addComponent(worldPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(worldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    private void chatButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
            
        if(chatButton.getText() == "Chat verbergen"){
            chatWindow.setVisible(false);
            chatButton.setText("Chat anzeigen");
            showChat = false;
            
            
        }
        else{
            chatButton.setText("Chat verbergen");
            chatWindow.setVisible(true);
        }
    }                                          

    private void chatInputMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        chatInput.setEnabled(true);
        chatInput.requestFocus();
    }                                      

    
    private void showChat(){
        if(showChat==true){
            chatWindow.setVisible(false);
            chatWindow.setVisible(true);
//            Msg.setVisible(false);
//            Msg.setVisible(true);
        }
    }
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
            java.util.logging.Logger.getLogger(spielwelt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(spielwelt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(spielwelt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(spielwelt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new spielwelt().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(spielwelt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public class tHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			chatText.append(chatInput.getText() + "\n");
			chatInput.setText("");
                        chatInput.setEnabled(false);

		}

	}

    // Variables declaration - do not modify                     
    private javax.swing.JButton chatButton;
    private javax.swing.JTextField chatInput;
    private javax.swing.JScrollPane chatScroll;
    private javax.swing.JTextArea chatText;
    private javax.swing.JPanel chatWindow;
    private javax.swing.JProgressBar healthBar;
    private javax.swing.JLabel itemLabel;
    private javax.swing.JPanel itemPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainItemPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel worldPanel;
    // End of variables declaration                   
}


