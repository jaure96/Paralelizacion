package ForkJoinExample3;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

import javax.imageio.ImageIO;

public class Principal {

	public static void main(String[] args) throws IOException {
		ForkJoinPool pool = new ForkJoinPool(4);
		
		BufferedImage bufferedImage = ImageIO.read(new File("images/"+args[0]));
		int anchura = bufferedImage.getWidth();
		int altura = bufferedImage.getHeight();
		
		int[] origen = new int[anchura*altura];
		origen = bufferedImage.getRGB(0, 0, anchura, altura, origen, 0, anchura);
				
		int[] destino = new int[origen.length];
		BlurEffect action = new BlurEffect(origen,0,origen.length,destino);
		pool.invoke(action);
		
		
		BufferedImage destinoImage = new BufferedImage( bufferedImage.getWidth(), 
				bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB );
		final int[] a = ( (DataBufferInt) destinoImage.getRaster().getDataBuffer() ).getData();
		System.arraycopy(destino, 0, a, 0, destino.length);	
		ImageIO.write(destinoImage, "jpg", new File("images/"+args[1]));
	}

}
