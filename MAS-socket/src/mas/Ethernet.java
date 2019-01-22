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
				List<Integer> pixeliRGB = new ArrayList<>();
		        
		        try {
					out = new PrintWriter(socket.getOutputStream(), true);
			        in = new DataInputStream(socket.getInputStream());
				} catch (IOException e1) {
					System.out.println("Exception!");
					e1.printStackTrace();
				}
		        
		        
		        for(int i = 0; i < 75; i++){
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
		        
		        int tmp;
		        
		        for(int i = 0, n = brojeviRGB.size() / 2; i < n; i++){
		        	Byte b1 = brojeviRGB.get(2 * i);
		        	Byte b2 = brojeviRGB.get(2 * i + 1);
		        	if(b1 < 0){
		        		tmp = (b1 + 256) << 8;
		        	}else{
		        		tmp = b1 << 8;
		        	}
		        	if(b2 < 0){
		        		tmp |= (b2 + 256);
		        	}else{
		        		tmp |= b2;
		        	}
		        	pixeliRGB.add(tmp);
		        }
		        
		        brojeviRGB.clear();
		        
		        float tmp1;
				for(Integer i : pixeliRGB){
					tmp1 = (i >> 11) & 0x1F;
					tmp1 /= 32;
					tmp1 *= 255;
					brojeviRGB.add((byte) (Math.round(tmp1)));
					tmp1 = (i >> 5) & 0x3F;
					tmp1 /= 64;
					tmp1 *= 255;
					brojeviRGB.add((byte) (Math.round(tmp1)));
					tmp1 = i & 0x1F;
					tmp1 /= 32;
					tmp1 *= 255;
					brojeviRGB.add((byte) (Math.round(tmp1)));
				}
				
				int i = 0;
				for (int k = 0; k < 480; k++) {
					for (int p = 0; p < 640; p++) {
						int col = new Color(brojeviRGB.get(i) < 0 ? brojeviRGB.get(i) + 256 : brojeviRGB.get(i), 
								brojeviRGB.get(i + 1) < 0 ? brojeviRGB.get(i + 1) + 256 : brojeviRGB.get(i + 1), 
								brojeviRGB.get(i + 2) < 0 ? brojeviRGB.get(i + 2) + 256 : brojeviRGB.get(i + 2))
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

}
