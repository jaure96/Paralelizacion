package ForkJoinExample3;

import java.util.concurrent.RecursiveAction;

public class BlurEffect extends RecursiveAction{
	protected static int sThreshold = 1000;
	private int[] mSource;
    private int mStart;
    private int mLength;
    private int[] mDestination;
  
    // Processing window size; should be odd.
    private int mBlurWidth = 15;
  
    public BlurEffect(int[] src, int start, int length, int[] dst) {
        mSource = src;
        mStart = start;
        mLength = length;
        mDestination = dst;
    }

    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for (int index = mStart; index < mStart + mLength; index++) {
            // Calculate average.
            int rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0),
                                    mSource.length - 1);
                int pixel = mSource[mindex];
                rt += ((pixel & 0x00ff0000) >> 16)
                      / mBlurWidth;
                gt += ((pixel & 0x0000ff00) >>  8)
                      / mBlurWidth;
                bt += ((pixel & 0x000000ff) >>  0)
                      / mBlurWidth;
            }
          
            // Reassemble destination pixel.
            int dpixel = (0xff000000     ) |
                   ((rt) << 16) |
                   ((gt) <<  8) |
                   ((bt) <<  0);
            mDestination[index] = dpixel;
        }
    }

	@Override
	protected void compute() {
		if (mLength < sThreshold) {
	        computeDirectly();
	        return;
	    }
	    
	    int split = mLength / 2;
	    
	    invokeAll(new BlurEffect(mSource, mStart, split, mDestination),
	              new BlurEffect(mSource, mStart + split, mLength - split,
	                           mDestination));
	}

}
