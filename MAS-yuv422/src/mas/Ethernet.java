package mas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ethernet {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("WINDOW");
		frame.setSize(640, 500);
	    frame.setVisible(true);
	    
	    JLabel slika = new JLabel();
	    slika.setSize(640, 480);
	    slika.setVisible(true);

	    JButton gumb = new JButton("DOHVATI");
	    gumb.setSize(640, 20);
	    
	    frame.add(gumb);
	    frame.add(slika);

	    gumb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				slika.setVisible(false);
				
				Socket socket = null;
				try {
					socket = new Socket("169.254.212.125", 7);
				} catch (IOException e1) {
					System.out.println("Exception!");
					e1.printStackTrace();
				}
				//Thread.sleep(7000);
				
				PrintWriter out = null;
				DataInputStream in = null;
				
				BufferedImage img = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
				
				List<Byte> brojeviRGB = new ArrayList<>();
				List<Integer> pixeliYUV = new ArrayList<>();
				List<Byte> brojeviYUV = new ArrayList<>();
		        try {
					out = new PrintWriter(socket.getOutputStream(), true);
			        in = new DataInputStream(socket.getInputStream());
				} catch (IOException e1) {
					System.out.println("Exception!");
					e1.printStackTrace();
				}
		        
		        
		        for(int i = 0; i < 113; i++){
		            out.println("ping");
		            for(int j = 0; j < 8192; j++){
		            	try {
							brojeviRGB.add((byte)in.readUnsignedByte());
						} catch (IOException e1) {
							System.out.println("Exception!");
							e1.printStackTrace();
						}
		            }
		        }
		        out.close();
		        try {
					in.close();
			        socket.close();
				} catch (IOException e1) {
					System.out.println("Exception!");
					e1.printStackTrace();
				}
		        
				
				int y,u,v;
				double r,g,b;
								
				List<Byte> dctArray = new ArrayList<>();
				
				float temp2;
				for (int i = 0; i < 480; i += 8) {
					for (int j = 0; j < 640; j += 8) {
						for (u = 0; u < 8; u++) {
							for (v = 0; v < 8; v++) {
								for (int k = 0; k < 3; k++) {
									temp2 = 0.0f;
									for (int x = 0; x < 8; x++) {
										for (y = 0; y < 8; y++) {
											temp2 += C(x) * C(y) * (float)brojeviRGB.get(k + 3*x + 3*i + 1920*y + 3*j) * (float)Math.cos((2.0 * (float)u + 1) * 3.14 * (float)x / 16) * (float)Math.cos((2.0 * (float)v + 1) * 3.14 * (float)y / 16);
										}
									}
									dctArray.set(k + 3*u + 3*i + 1920*v + 3*j, (byte)(0.25 * temp2));
								}
							}
						}
					}
				}
				
				
				//dctArray u brojeviRGB 
				for(int i = 0, n = dctArray.size(); i < n / 3; i++){
					y = dctArray.get(3 * i) + 128;
					u = dctArray.get(3 * i + 1) + 128;
					v = dctArray.get(3 * i + 2) + 128;
					if(y < 0){
						y += 256;
					}
					if(u < 0){
						u += 256;
					}
					if(v < 0){
						v += 256;
					}

					u-=128;
					v-=128;
					r = y + 1.14 * v;
					g = y - 0.395 * u - 0.581 * v;
					b = y + 2.032 * u;
					
//					r = 1.164 * (y - 16) + 1.596 * (v - 128);
//					g = 1.164 * (y - 16) - 0.813 * (v - 128) - 0.391 * (u - 128);
//					b = 1.164 * (y - 16) + 2.018 * (u - 128);

					
					r = normalize((int)Math.round(r));
					g = normalize((int)Math.round(g));
					b = normalize((int)Math.round(b));
					
					brojeviYUV.add((byte)r);
					brojeviYUV.add((byte)g);
					brojeviYUV.add((byte)b);

				}
				
				int i = 0;
				for (int k = 0; k < 480; k++) {
					for (int p = 0; p < 640; p++) {
						int col = new Color(brojeviYUV.get(i) < 0 ? brojeviYUV.get(i) + 256 : brojeviYUV.get(i), 
								brojeviYUV.get(i + 1) < 0 ? brojeviYUV.get(i + 1) + 256 : brojeviYUV.get(i + 1), 
								brojeviYUV.get(i + 2) < 0 ? brojeviYUV.get(i + 2) + 256 : brojeviYUV.get(i + 2))
								.getRGB();
						try{
							img.setRGB(p, k, col);
						}catch(Exception ex){
							System.out.println(k + " " + p);
						}
						i += 3;
					}
				}
				slika.setIcon(new ImageIcon(createFlipped(img)));
				
				slika.setVisible(true);
			}
		});
	    
	    //frame.pack();
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
    private static BufferedImage createFlipped(BufferedImage image)
    {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        return createTransformed(image, at);
    }
    
	private static BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.transform(at);
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return newImage;
	}
	
	private static int normalize(int component){
		if(component > 255){
			component = 255;
		}else if(component < 0){
			component = 0;
		}
		
		return component;
	}
	
	private static float C(int u) {
		if (u == 0) {
			return (float)Math.sqrt(1 / (float)2.0);
		}
		else {
			return (float)1.0;
		}
	}

}
