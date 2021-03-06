/* 
 * Copyright (c) 2015, George Shumakov
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package george.shumakov.imgtosong;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main dialog class.
 * 
 * @author George Shumakov <george.shumakov@gmail.com>
 */
public class Main extends javax.swing.JDialog {

    private static final JFileChooser FC = new JFileChooser();
    private static final String FFMPEG = "ffmpeg";
    private static final int PICTURE_PATH_ARG = 4;
    private static final int SONG_PATH_ARG = 6;
    private static final int SONG_TITLE_ARG = 16;
    private static final int OUTPUT_PATH = 23;
    private static final String CONVERTED_SONG_SUFFIX = ".pict.mp3";

    private final List<String> commandLine;
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Main dialog = new Main(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                    //TODO: handle exceptions
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    

    /**
     * Creates new form NewJDialog
     *
     * @param parent
     * @param modal
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    //TODO: handle exceptions
    public Main(java.awt.Frame parent, boolean modal) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        super(parent, modal);
        initComponents();
        commandLine = new ArrayList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SongPathTextField = new javax.swing.JTextField();
        BrowseSongButton = new javax.swing.JButton();
        PicturePathTextField = new javax.swing.JTextField();
        BrowsePictureButton = new javax.swing.JButton();
        TitleTextField = new javax.swing.JTextField();
        AddButton = new javax.swing.JButton();
        CloseButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BrowseSongButton.setText("Browse");
        BrowseSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseSongButtonActionPerformed(evt);
            }
        });

        BrowsePictureButton.setText("Browse");
        BrowsePictureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowsePictureButtonActionPerformed(evt);
            }
        });

        AddButton.setText("Add");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        CloseButton.setText("Close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Song");

        jLabel2.setText("Picture");

        jLabel3.setText("Song title");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 230, Short.MAX_VALUE)
                        .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SongPathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BrowseSongButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(PicturePathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(TitleTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BrowsePictureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(SongPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseSongButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PicturePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowsePictureButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CloseButton)
                    .addComponent(AddButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BrowseSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseSongButtonActionPerformed
        setPathToTextField(SongPathTextField);
    }//GEN-LAST:event_BrowseSongButtonActionPerformed

      private void setPathToTextField(JTextField pathField) {
        int returnVal = FC.showOpenDialog(Main.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = FC.getSelectedFile();
            pathField.setText(file.getAbsolutePath());
        }
    }
    
    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        Process p = null;
        
        //TODO: create input text filepath validator with baloon help.
        String song = SongPathTextField.getText().trim();
        String picture = PicturePathTextField.getText().trim();
        String title = TitleTextField.getText().trim();
        
        try {
            p = prepareCommandLine(song, picture, title).start();
        } catch (IOException | IllegalArgumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TODO: Create analyzer
        //Temporary prints process log
        if (null != p) {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //TODO: prepare for the next song
        commandLine.clear();
        CloseButtonActionPerformed(evt);
    }//GEN-LAST:event_AddButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        Main.this.dispose();
        Main.this.processWindowEvent(
                new WindowEvent(
                        this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void BrowsePictureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowsePictureButtonActionPerformed
        setPathToTextField(PicturePathTextField);
    }//GEN-LAST:event_BrowsePictureButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton AddButton;
    javax.swing.JButton BrowsePictureButton;
    javax.swing.JButton BrowseSongButton;
    javax.swing.JButton CloseButton;
    javax.swing.JTextField PicturePathTextField;
    javax.swing.JTextField SongPathTextField;
    javax.swing.JTextField TitleTextField;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    /**
     * Prepare ffmpeg process.
     * @param song
     * @param picture
     * @param title
     * @return
     * @throws IllegalArgumentException if song or picture paths are not valid
     */
    private ProcessBuilder prepareCommandLine(final String song, final String picture, final String title)  {
 
        if ((null == song || null == picture) || song.isEmpty() || picture.isEmpty()) {
            throw new IllegalArgumentException("Song or picture paths are not valid.");
        }
        final Path songPath = Paths.get(song);
        final String songFileName = songPath.getFileName().toString();
        final String titleArg = "title=" + (null == title || title.isEmpty() ? "unknown" : title);
        final String outputSongPath = songPath.getParent() + File.separator + songFileName + CONVERTED_SONG_SUFFIX;
        
        if (commandLine.isEmpty()) {
            commandLine.add(FFMPEG); //0
            commandLine.add("-loop"); //1
            commandLine.add("1"); //2
            commandLine.add("-i"); //3
            commandLine.add(picture); //4
            commandLine.add("-i"); //5
            commandLine.add(song); //6
            commandLine.add("-map"); //7
            commandLine.add("1"); //8
            commandLine.add("-map"); //9
            commandLine.add("0"); //10
            commandLine.add("-c"); //11
            commandLine.add("copy"); //12
            commandLine.add("-map_metadata"); //13
            commandLine.add("-1"); //14
            commandLine.add("-metadata"); //15
            commandLine.add(titleArg); //16
            commandLine.add("-shortest"); //17
            commandLine.add("-metadata:s:v:0");  //18
            commandLine.add("comment=Cover {front)"); //19  
            commandLine.add("-f"); //20
            commandLine.add("mp3"); //21
            commandLine.add("-y"); //22
            commandLine.add(outputSongPath); //23
        } else {
            commandLine.set(PICTURE_PATH_ARG, picture); // picture
            commandLine.set(SONG_PATH_ARG, song); //song
            commandLine.set(SONG_TITLE_ARG, titleArg); //song title
            commandLine.set(OUTPUT_PATH, outputSongPath); //converted song
        }

        final ProcessBuilder b = new ProcessBuilder(commandLine);
        b.redirectOutput(Redirect.INHERIT);
        b.redirectError(Redirect.INHERIT);
        return b;
    }
}
