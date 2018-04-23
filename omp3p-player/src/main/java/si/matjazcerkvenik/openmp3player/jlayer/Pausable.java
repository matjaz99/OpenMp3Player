package si.matjazcerkvenik.openmp3player.jlayer;

import java.net.URL;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Decoder.Params;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;


public class Pausable {
	// This class is loosely based on javazoom.jl.player.AdvancedPlayer.

	private URL urlToStreamFrom;
	private Bitstream bitstream;
	private Decoder decoder;
	private AudioDevice audioDevice;
	private boolean isClosed = false;
	private boolean isComplete = false;
	private PlaybackListener listener;
	private int frameIndexCurrent;

	public boolean isPaused;

	public Pausable(URL urlToStreamFrom, PlaybackListener listener)
			throws JavaLayerException {
		this.urlToStreamFrom = urlToStreamFrom;
		this.listener = listener;
	}

	public void pause() {
		this.isPaused = true;
		this.close();
	}

	public boolean play() throws JavaLayerException {
		return this.play(0);
	}

	public boolean play(int frameIndexStart) throws JavaLayerException {
		return this.play(frameIndexStart, -1, 52);
	}

	public boolean play(int frameIndexStart, int frameIndexFinal,
			int correctionFactorInFrames) throws JavaLayerException {
		try {
			this.bitstream = new Bitstream(this.urlToStreamFrom.openStream());
		} catch (java.io.IOException ex) {
		}

		this.audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
		this.decoder = new Decoder();
		this.audioDevice.open(decoder);

		boolean shouldContinueReadingFrames = true;

		this.isPaused = false;
		this.frameIndexCurrent = 0;

		while (shouldContinueReadingFrames == true
				&& this.frameIndexCurrent < frameIndexStart
						- correctionFactorInFrames) {
			shouldContinueReadingFrames = this.skipFrame();
			this.frameIndexCurrent++;
		}

		if (this.listener != null) {
			this.listener.playbackStarted(new PlaybackEvent(this,
					PlaybackEvent.EventType.Instances.Started, this.audioDevice
							.getPosition()));
		}

		if (frameIndexFinal < 0) {
			frameIndexFinal = Integer.MAX_VALUE;
		}

		while (shouldContinueReadingFrames == true
				&& this.frameIndexCurrent < frameIndexFinal) {
			if (this.isPaused == true) {
				shouldContinueReadingFrames = false;
				try {
					Thread.sleep(1);
				} catch (Exception ex) {
				}
			} else {
				shouldContinueReadingFrames = this.decodeFrame();
				this.frameIndexCurrent++;
			}
//			System.out.println("shouldContinueReadingFrames: " + shouldContinueReadingFrames);
//			System.out.println("Frames: " + frameIndexCurrent + "/" + frameIndexFinal);
		}

		// last frame, ensure all data flushed to the audio device.
		if (this.audioDevice != null) {
			
			// report to listener
			if (this.listener != null) {
				this.listener.playbackFinished(new PlaybackEvent(this, 
						PlaybackEvent.EventType.Instances.Stopped, 
						this.audioDevice.getPosition()));
			}
			
			if (this.audioDevice != null) {
				this.audioDevice.flush();
				synchronized (this) {
					this.isComplete = (this.isClosed == false);
					this.close();
				}
			}
			
		}

		return shouldContinueReadingFrames;
	}

	public boolean resume() throws JavaLayerException {
		return this.play(this.frameIndexCurrent);
	}

	public synchronized void close() {
		if (this.audioDevice != null) {
			this.isClosed = true;

			this.audioDevice.close();

			this.audioDevice = null;

			try {
				this.bitstream.close();
			} catch (Exception ex) {
			}
		}
	}

	protected boolean decodeFrame() throws JavaLayerException {
		boolean returnValue = false;

		try {
			if (this.audioDevice != null) {
				Header header = this.bitstream.readFrame();
				if (header != null) {
					// sample buffer set when decoder constructed
					SampleBuffer output = (SampleBuffer) decoder.decodeFrame(
							header, bitstream);

					synchronized (this) {
						if (this.audioDevice != null) {
							this.audioDevice.write(output.getBuffer(), 0,
									output.getBufferLength());
						}
					}

					this.bitstream.closeFrame();
				} else {
					return false;
				}
			}
		} catch (RuntimeException ex) {
			throw new JavaLayerException("Exception decoding audio frame", ex);
		}
		return true;
	}

	protected boolean skipFrame() throws JavaLayerException {
		boolean returnValue = false;

		Header header = bitstream.readFrame();

		if (header != null) {
			bitstream.closeFrame();
			returnValue = true;
		}

		return returnValue;
	}

	public void stop() {
		this.listener.playbackFinished(new PlaybackEvent(this,
				PlaybackEvent.EventType.Instances.Stopped, this.audioDevice
						.getPosition()));

		this.close();
	}

	// inner classes

	public static class PlaybackEvent {
		public Pausable source;
		public EventType eventType;
		public int frameIndex;

		public PlaybackEvent(Pausable source, EventType eventType,
				int frameIndex) {
			this.source = source;
			this.eventType = eventType;
			this.frameIndex = frameIndex;
		}

		public static class EventType {
			public String name;

			public EventType(String name) {
				this.name = name;
			}

			public static class Instances {
				public static EventType Started = new EventType("Started");
				public static EventType Stopped = new EventType("Stopped");
			}
		}
	}

	public static abstract class PlaybackListener {
		public void playbackStarted(PlaybackEvent event) {
		}

		public void playbackFinished(PlaybackEvent event) {
		}
	}
}
