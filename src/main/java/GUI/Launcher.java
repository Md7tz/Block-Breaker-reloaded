package GUI;

import javax.swing.*;

import game.Application;

public class Launcher extends Window {

    public Launcher(int width, int height) {
        super("Brick Breaker Game Launcher", width, height, false, true, JFrame.EXIT_ON_CLOSE);
        setWindowProps();
        initComponents();
    }

    private void initComponents() {
        mainPanel = new javax.swing.JPanel();
        labelName = new javax.swing.JLabel();
        nickname = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        launchButton = new javax.swing.JButton();

        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
        labelName.setText("Nickname: ");
        launchButton.setText("Launch");

        launchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnLaunch(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelName)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nickname)
                .addGap(18, 18, 18)
                .addComponent(launchButton)
                .addGap(0, 65, Short.MAX_VALUE))
        ));
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(nickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(launchButton))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        frame.pack();
    }
    
    /**
     *   This is the called to perform an action onSubmit
     */
    private void OnLaunch(java.awt.event.ActionEvent evt) {
        String name = nickname.getText();
        if("".equals(name)){
            JOptionPane.showMessageDialog(frame, "Enter a nickname", "Missing player information", JOptionPane.ERROR_MESSAGE);
        }else{
            frame.dispose();
            // TODO store in config
            Application.start();
        }
    }

    private JButton launchButton;
    private JLabel jLabel1;
    private JLabel labelName;
    private JPanel mainPanel;
    private JTextField nickname;
    private int borderSize = 4;
}
