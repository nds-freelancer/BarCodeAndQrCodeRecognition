////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Author: Taha Emara
// WebSite : www.Emaraic.com
// E-mail  : taha@emaraic.com
//
//                   Realtime face detection using OpenCV with Java
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.opencv.core.Core;
//import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
//import org.opencv.highgui.Highgui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import boofcv.abst.fiducial.QrCodeDetector;
import boofcv.alg.fiducial.qrcode.QrCode;
import boofcv.factory.fiducial.ConfigQrCode;
import boofcv.factory.fiducial.FactoryFiducial;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;


/**
 *
 * @author Taha Emara
 */
public class FaceDetection3 extends javax.swing.JFrame {
///
    private DaemonThread myThread = null;
    int count = 0;
    VideoCapture webSource = null;
	OpenCVFrameGrabber frameGrabber;
	JLabel vidPanel = new JLabel();
    IplImage iPimg;

    Mat frame = new Mat();
    MatOfByte mem = new MatOfByte();
    CascadeClassifier faceDetector = new CascadeClassifier(FaceDetection3.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
    MatOfRect faceDetections = new MatOfRect();
///    

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {

                while (runnable) {

//                    if (webSource.read(frame)) {
                  	 try {
      				iPimg = frameGrabber.grab();
  					BufferedImage bufferedImage = iPimg.getBufferedImage();
                  		 
                 		GrayU8 gray = ConvertBufferedImage.convertFrom(bufferedImage, (GrayU8)null);

                		var config = new ConfigQrCode();
//                		config.considerTransposed = false; // by default, it will consider incorrectly encoded markers. Faster if false
                		QrCodeDetector<GrayU8> detector = FactoryFiducial.qrcode(config, GrayU8.class);
                		detector.process(gray);
                		// Gets a list of all the qr codes it could successfully detect and decode
                		List<QrCode> detections = detector.getDetections();	 
//                		System.out.println("messagezzz:");
                		for (QrCode qr : detections) {
                			System.out.println("message: '" + qr.message + "'");
                		}

                    	Graphics g = jPanel1.getGraphics();
//                    	  frame = BufferedImage2Mat(iPimg.getBufferedImage());
//                           BufferedImage buff = Mat2BufferedImage(frame);
                           if (g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight()-150 , 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null)) {
                               if (runnable == false) {
//                                   System.out.println("Paused ..... ");
                                   this.wait();
                               }
                           }
          				Thread.sleep(100);
          			
                   		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
                	    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                	    Reader reader = new MultiFormatReader();
                	    Result result = reader.decode(bitmap);
                	    System.out.println("Barcode text is " + result.getText());
                      		
                  	 
                  	 } catch (Exception | NotFoundException | ChecksumException | FormatException | InterruptedException    e) {
//          				e.printStackTrace();
          			}
//                  }
                    
                }
            }
        }
    }

/////////
    /**
     * Creates new form FaceDetection
     */
    public FaceDetection3() {
        initComponents();
        System.out.println(FaceDetection3.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        
        jPanel1.setBackground(Color.BLUE);

        BufferedImage img;

//	        String inFile = "E:/Data/EEEE.jpg";
//	        Mat image = Imgcodecs.imread(inFile); 
//	        ImageIcon image12 = new ImageIcon(Mat2BufferedImage(image));
	        
	        this.setContentPane(jPanel1);
	        this.setSize(1200, 1000);
	        this.setVisible(true);
	        
//	        String inFile = "E:/Data/EEEE.jpg";
//	        Mat image = Imgcodecs.imread(inFile); 
//	        ImageIcon image12 = new ImageIcon(Mat2BufferedImage(image));
//	        vidPanel.setIcon(image12);
	        

//        BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
        
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        
        
        jButton1 = new javax.swing.JButton();
        jButton1.setBounds(100, 20,120,20);
        jFrame = new javax.swing.JFrame();
        this.add(jButton1);
        
//        jframe.setContentPane(vidPanel);
//        jframe.setSize(1000, 2000);
//        jframe.setVisible(true);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

//        jButton2.setText("Pause");
//        jButton2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton2ActionPerformed(evt);
//            }
//        });

//        jButton3.setText("ZZZ");
//        jButton3.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
////                jButton2ActionPerformed(evt);
//            }
//        });
//        jFrame.setTitle("EEEEEEEEEEEE");
//        jFrame.setBackground(Color.blue);
//        jFrame.setBounds(0, 0, 10,10);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
//            .addGroup(layout.createSequentialGroup()
//                .addGap(255, 255, 255)
//                .addComponent(jButton1)
//                .addGap(36, 36, 36)
//                .addComponent(jButton2)
////                .addGap(56, 56, 56)
////                .addComponent(jFrame)
////                .addGap(86, 86, 86)
////                .addComponent(jFrame)
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
//                    .addComponent(jButton2)
////                    .addComponent(jFrame)
////                    .addComponent(jFrame)
//                    )
//                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
 
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        myThread.runnable = false;            // stop thread
        jButton2.setEnabled(false);   // activate start button 
        jButton1.setEnabled(true);     // deactivate stop button

        webSource.release();  // stop caturing fron cam


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

//        webSource = new VideoCapture(0); // video capture from default cam
//      webSource = new VideoCapture();
//      webSource.open("E:/Data/TTT.mp4");
//    	frameGrabber = new OpenCVFrameGrabber("rtsp://:@192.168.1.4:554/user=_password=_channel=1_stream=0.sdp"); 
//    	
//        webSource = new VideoCapture();
//        webSource.open("E:/Data/Trim.mp4");
//        webSource.open("rtsp://:@192.168.1.4:554/user=_password=_channel=1_stream=0.sdp");
    	
        
        frameGrabber = new OpenCVFrameGrabber("rtsp://:@192.168.1.4:554/user=_password=_channel=1_stream=0.sdp"); 
    	frameGrabber.setFormat("mjpeg");
        try {
			frameGrabber.start();
			
		} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        System.out.println("open");
        myThread = new DaemonThread(); //create object of threat class
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();                 //start thrad
        jButton1.setEnabled(false);  // deactivate start button
//        jButton2.setEnabled(true);  //  activate stop button

        jPanel1.setBackground(Color.YELLOW);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FaceDetection3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FaceDetection3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FaceDetection3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FaceDetection3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FaceDetection3().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFrame jFrame;
    
    
    // End of variables declaration//GEN-END:variables
    
    public static BufferedImage Mat2BufferedImage(Mat m) {
        //Method converts a Mat to a Buffered Image
        int type = BufferedImage.TYPE_BYTE_GRAY;
         if ( m.channels() > 1 ) {
             type = BufferedImage.TYPE_3BYTE_BGR;
         }
         int bufferSize = m.channels()*m.cols()*m.rows();
         byte [] b = new byte[bufferSize];
         m.get(0,0,b); // get all the pixels
         BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
         final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
         System.arraycopy(b, 0, targetPixels, 0, b.length);  
         return image;
        }
    
    public static Mat BufferedImage2Mat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }
    
    
}
