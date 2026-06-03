package dataandplot.util;

public class SineWaveGenerator {
    public static void main(String[] args) {
        // Define signal parameters
        double samplingRate = 44100.0; // In Hz (CD quality)
        double frequency = 440.0;       // In Hz (A4 pitch)
        double amplitude = 1.0;         // Peak amplitude scale
        double initialPhase = Math.PI / 2.0; // Phase shift in radians (90 degrees)
        int numberOfSamples = 10;       // Number of points to generate

        // Generate the wave array
        double[] sineWave = generateSineWave(samplingRate, frequency, amplitude, initialPhase, numberOfSamples);

        // Display results
        System.out.printf("%-10s | %-12s%n", "Sample ID", "Signal Value");
        System.out.println("---------------------------");
        for (int i = 0; i < sineWave.length; i++) {
            System.out.printf("%-10d | %-12.6f%n", i, sineWave[i]);
        }
    }

    /**
     * Generates an array representing a discrete sine wave with a phase offset.
     */
    public static double[] generateSineWave(double sampleRate, double freq, double amp, double phase, int totalSamples) {
        double[] buffer = new double[totalSamples];
        double twoPi = 2.0 * Math.PI;

        for (int i = 0; i < totalSamples; i++) {
            // Calculate the angle based on time step and frequency
            double angle = (twoPi * freq * i) / sampleRate;

            // Calculate the sine value including the initial phase
            buffer[i] = amp * Math.sin(angle + phase);
        }
        return buffer;
    }
}
