package info.pinlab.schnitt.gui;

import info.pinlab.pinsound.WavClip;
import info.pinlab.snd.trs.Interval;
import info.pinlab.snd.trs.Tier;
import info.pinlab.snd.vad.BinaryHypoTier;
import info.pinlab.snd.vad.BinaryTargetTier;
import info.pinlab.snd.vad.VadError;
import info.pinlab.snd.vad.VadErrorTier;

public interface WavPanelModel {
	public void setSampleArray(int [] samples, int hz);
	public void setWav(WavClip wav);
	
	public int getSampleSize();
	
	public void setViewWidthInPx(int w);
	public void setViewHeightInPx(int h);
	
	public double[] getWaveCurvePointsCondensed();
	public double[] getWaveCurvePointsInterpolated();

	public int getSampleIxFromPx(int px);
	public double getSecFromPx(int px);
	
	public void setCursorPosToPx(int px);
	public void setCursorPosToMs(int ms);
	public void setCursorPosToSampleIx(int ix);

	public int getCursorPositionInSampleIx();
	public int getCursorPositionInPx();
	public double getCursorPositionInSec();
	public int getCursorPositionInMs();
	
	public double getSecFromSampleX(double x);
	public int getPxFromSampleIx(int x);

	//-- Intervals
	public GuiAdapterForTier<Boolean> addTier(BinaryHypoTier hypo);
	public GuiAdapterForTier<Boolean> addTier(BinaryTargetTier err);
	public GuiAdapterForTier<VadError> addTier(VadErrorTier err);
	
	
	/**
	 * Generic addTier method.  
	 * 
	 * @param tier the tier to add
	 * @param cls types of interval in the tier 
	 * @return an adapter around the tier
	 */
//	public <T> GuiAdapterForTier<T> addTier(IntervalTier<T> tier, Class<T> cls);
//	public int addTier(IntervalTier<?> t);
	public int getTierN();
	public Tier getTierByIx(int ix);
	
	/**
	 * Gets selection at given coordinates.
	 * 
	 * @param x x coordinate of query point 
	 * @param y y coordinate of query point
	 * @return null if no selection at the point
	 */
	public IntervalSelection getSelectionAt(int x, int y);
	
	
	//-- Active Selection getters
	public IntervalSelection getActiveIntervalSelection();
	public void addIntervalToActiveTier(Interval<?> interval);

//	public List<IntervalSelection> getInterValsFromActiveTier();
//	public List<IntervalSelection> getInterValsFromTierX(int tierIx);

	
	//-- Tiers
	public int getTierAdapterN();
	public GuiAdapterForTier<?> getTierAdapter(int ix);
	

	//-- ZOOM
	public void zoomTo(double start, double end);
	public void zoomOut();
}
