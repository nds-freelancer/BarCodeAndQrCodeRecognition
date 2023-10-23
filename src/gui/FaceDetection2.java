//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// Author: Taha Emara
//// WebSite : www.Emaraic.com
//// E-mail  : taha@emaraic.com
////
////                   Realtime face detection using OpenCV with Java
////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//package gui;
//
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//import java.io.ByteArrayInputStream;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfByte;
//import org.opencv.core.MatOfRect;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.highgui.Highgui;
//import org.opencv.objdetect.CascadeClassifier;
//import org.opencv.videoio.VideoCapture;
//
//import com.googlecode.javacv.OpenCVFrameGrabber;
//
///**
// *
// * @author Taha Emara
// */
//public class FaceDetection2 extends javax.swing.JFrame {
/////
//    private DaemonThread myThread = null;
//    int count = 0;
//    VideoCapture webSource = null;
//	OpenCVFrameGrabber frameGrabber;
//    Mat frame = new Mat();
//    MatOfByte mem = new MatOfByte();
//    CascadeClassifier faceDetector = new CascadeClassifier(FaceDetection2.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
//    MatOfRect faceDetections = new MatOfRect();
/////    
//    JFrame jframe = new JFrame("Video Title");
//    VideoCapture camera;
//    JLabel vidPanel = new JLabel();
//    
//    class DaemonThread implements Runnable {
//
//        protected volatile boolean runnable = false;
//
//        @Override
//        public void run() {
//            synchronized (this) {
//                while (runnable) {
//                	
//                        if (camera.read(frame)) {
//                            ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
//                            vidPanel.setIcon(image);
//                            try {
//                				Thread.sleep(100);
//                			} catch (InterruptedException e) {
//                				e.printStackTrace();
//                			}
//                            vidPanel.repaint();
//
//                        }
//                	
//                	
//                    if (webSource.grab()) {
//                    	System.out.println("Error!!");
//                        try {
//                            
//                        	webSource.retrieve(frame);
//                        	System.out.println("Error!!");
//                            
//                            Graphics g = jPanel1.getGraphics();
//                            faceDetector.detectMultiScale(frame, faceDetections);
//                            for (Rect rect : faceDetections.toArray()) {
//                               // System.out.println("ttt");
////                                Core.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
////                                        new Scalar(0, 255,0));
//                            }
//                            Highgui.imencode(".bmp", frame, mem);
//                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
//                            BufferedImage buff = (BufferedImage) im;
//                            if (g.drawImage(buff, 0, 0, getWidth(), getHeight()-150 , 0, 0, buff.getWidth(), buff.getHeight(), null)) {
//                                if (runnable == false) {
//                                    System.out.println("Paused ..... ");
//                                    this.wait();
//                                }
//                            }
//                        } catch (Exception ex) {
//                            System.out.println("Error!!");
//                            ex.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
///////////
//    /**
//     * Creates new form FaceDetection
//     */
//    public FaceDetection2() {
//        initComponents();
//        System.out.println(FaceDetection2.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
//    }
//
//    /**
//     * This method is called from within the constructor to initialize the form.
//     * WARNING: Do NOT modify this code. The content of this method is always
//     * regenerated by the Form Editor.
//     */
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
//    private void initComponents() {
//
//        jPanel1 = new javax.swing.JPanel();
//        jButton1 = new javax.swing.JButton();
//        jButton2 = new javax.swing.JButton();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
//        jPanel1.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 0, Short.MAX_VALUE)
//        );
//        jPanel1Layout.setVerticalGroup(
//            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGap(0, 376, Short.MAX_VALUE)
//        );
//
//        jButton1.setText("Start");
//        jButton1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton1ActionPerformed(evt);
//            }
//        });
//
//        jButton2.setText("Pause");
//        jButton2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton2ActionPerformed(evt);
//            }
//        });
//
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(24, 24, 24)
//                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addContainerGap())
//            .addGroup(layout.createSequentialGroup()
//                .addGap(255, 255, 255)
//                .addComponent(jButton1)
//                .addGap(86, 86, 86)
//                .addComponent(jButton2)
//                .addContainerGap(258, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(jButton1)
//                    .addComponent(jButton2))
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//
//        pack();
//    }// </editor-fold>//GEN-END:initComponents
//
//    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        myThread.runnable = false;            // stop thread
//        jButton2.setEnabled(false);   // activate start button 
//        jButton1.setEnabled(true);     // deactivate stop button
//
//        webSource.release();  // stop caturing fron cam
//
//
//    }//GEN-LAST:event_jButton2ActionPerformed
//
//    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//
////      webSource = new VideoCapture();
////      webSource.open("E:/Data/TTT.mp4");
//      
//        VideoCapture camera = new VideoCapture();
//        camera.open("E:/Data/VBN.mp4");
//    	/////////////////////////////////////////////////////////////////////
//        
////        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        vidPanel.setText("sssssssssssssssss");
////        vidPanel.setBounds(10, 60, 50, 20);
////        jframe.setContentPane(vidPanel);
////        jframe.setSize(2000, 4000);
////        jframe.setVisible(true);
//////        jPanel1.add(jframe);
////        System.out.print("ssss");
////        while (true) {
//////        	System.out.print("ssss");
////            if (camera.read(frame)) {
////                ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
////                System.out.print("q");
////                vidPanel.setText(image.toString());
////                
////                try {
////    				Thread.sleep(100);
////    			} catch (InterruptedException e) {
////    				e.printStackTrace();
////    			}
////                vidPanel.repaint();
////
////            }
////        }
//        
//        //////////////////////////////////////////////////////////////////////
//        
//        
//        myThread = new DaemonThread(); //create object of threat class
//        Thread t = new Thread(myThread);
//        t.setDaemon(true);
//        myThread.runnable = true;
//        t.start();                 //start thrad
//        jButton1.setEnabled(false);  // deactivate start button
//        jButton2.setEnabled(true);  //  activate stop button
//
//
//    }//GEN-LAST:event_jButton1ActionPerformed
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FaceDetection2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FaceDetection2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FaceDetection2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FaceDetection2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FaceDetection2().setVisible(true);
//            }
//        });
//        
//        
//        
//        
//    }
//    // Variables declaration - do not modify//GEN-BEGIN:variables
//    private javax.swing.JButton jButton1;
//    private javax.swing.JButton jButton2;
//    private javax.swing.JPanel jPanel1;
//    // End of variables declaration//GEN-END:variables
//    
//    public static BufferedImage Mat2BufferedImage(Mat m) {
//        //Method converts a Mat to a Buffered Image
//        int type = BufferedImage.TYPE_BYTE_GRAY;
//         if ( m.channels() > 1 ) {
//             type = BufferedImage.TYPE_3BYTE_BGR;
//         }
//         int bufferSize = m.channels()*m.cols()*m.rows();
//         byte [] b = new byte[bufferSize];
//         m.get(0,0,b); // get all the pixels
//         BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
//         final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//         System.arraycopy(b, 0, targetPixels, 0, b.length);  
//         return image;
//        }
//}