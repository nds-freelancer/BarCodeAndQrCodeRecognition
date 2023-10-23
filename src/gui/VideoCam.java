package gui;
import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
//import org.opencv.highgui.VideoCapture;

import com.googlecode.javacv.cpp.opencv_videostab.IFrameSource;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.*;

public class VideoCam {

static { 
    try {
    } catch (UnsatisfiedLinkError e) {
        System.err.println("Native code library failed to load.\n" + e);
        System.exit(1);
    }
}

public static void main(String[] args)
{
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    //Create new MAT object
    Mat frame = new Mat();
    VideoCapture camera = new VideoCapture();
    camera.open("rtsp://:@192.168.1.4:554/user=_password=_channel=1_stream=0.sdp");
    JFrame jframe = new JFrame("Video Title");
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel vidPanel = new JLabel();
    jframe.setContentPane(vidPanel);
    jframe.setSize(1400, 2000);
    jframe.setVisible(true);

    while (true) {
        if (camera.read(frame)) {
            ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
            vidPanel.setIcon(image);
            try {
				Thread.sleep(130);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            vidPanel.repaint();

        }
        System.out.println("Native code\n");
    }
    
}


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
}