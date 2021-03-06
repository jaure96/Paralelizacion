package ForkJoinExample2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class Principal {

	
	public static void main(String[] args) throws IOException {
		ForkJoinPool pool = new ForkJoinPool(64);
		BufferedImage bufferedImage = ImageIO.read(new File("images/"+args[0]));
		for (int row = 0; row < bufferedImage.getHeight(); row++) {
			FiltroGrises action = new FiltroGrises(row, bufferedImage);
			pool.execute(action);
		}
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIO.write(bufferedImage, "jpg", new File("images/"+args[1]));
	}

}
