package ForkJoinExample2;

import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

public class FiltroGrises extends RecursiveAction {
	private int row;
	private BufferedImage bufferedImage;
	
	public FiltroGrises(int row, BufferedImage bufferedImage) {
		this.row = row;
		this.bufferedImage = bufferedImage;
	}
	@Override
	protected void compute() {
		for (int column = 0; column < bufferedImage.getWidth(); column++) {
			int rgb = bufferedImage.getRGB(column, row);
			int r = (rgb >> 16) & 0xFF;
			int g = (rgb >> 8) & 0xFF;
			int b = (rgb & 0xFF);
			int gray = (int) (0.2126 * (float) r + 0.7152 * (float) g + 0.0722 * (float) b);
			gray = (gray << 16) + (gray << 8) + gray;
			bufferedImage.setRGB(column, row, gray);
		}
	}
}
