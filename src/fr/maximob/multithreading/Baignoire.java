package fr.maximob.multithreading;

import static java.lang.Thread.sleep;

public class Baignoire implements Runnable {

    protected final int maxVolume;
    protected int volume = 0;
    protected int volumeFuite = 0;

    public Baignoire(int maxVolume) {
        this.maxVolume = maxVolume;
    }

    public Baignoire(int maxVolume, int volume) {
        this.maxVolume = maxVolume;
        if (volume > maxVolume) this.volume = maxVolume;
        else this.volume = Math.max(volume, 0);
    }

    public Baignoire(int maxVolume, int volume, int volumeFuite) {
        this.maxVolume = maxVolume;
        if (volume > maxVolume) this.volume = maxVolume;
        else this.volume = Math.max(volume, 0);
        this.volumeFuite = Math.max(volumeFuite, 0);
    }

    @Override
    public void run() {
        fuite();
    }

    public void fuite() {
        if (this.volumeFuite == 0) return;
        while (this.volumeFuite > 0) {
            synchronized (this) {
                final int newVolume = this.getVolume() - this.volumeFuite;
                this.setVolume(Math.max(newVolume, 0));
                System.out.println("fuite ↘ " + this.volumeFuite + "L : " + this.toString());
                if (this.isEmpty()) this.colmate();
            }
            try {
                sleep(2);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void colmate() {
        this.volumeFuite--;
        System.out.println("\uD83D\uDEE0 Colmatage \uD83D\uDEE0 : fuite à " + this.volumeFuite + "L");
    }

    public boolean isFull() {
        return this.volume == this.maxVolume;
    }

    public boolean isEmpty() {
        return this.volume == 0;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        if (volume > this.maxVolume) this.volume = this.maxVolume;
        else this.volume = Math.max(volume, 0);
    }

    public int getMaxVolume() {
        return this.maxVolume;
    }

    public int getVolumeFuite() {
        return this.volumeFuite;
    }

    public String getFillingRatio() {
        return String.format("%.1f", (float) 100 / this.maxVolume * this.volume);
    }

    public void afficher() {
        System.out.println(this.toString());
    }

     public String toString() {
        if (this.volume == this.maxVolume) return "Baignoire pleine (" + this.volume + "/" + this.maxVolume + "L)";
        if (this.volume > 0) return "Baignoire remplie à " + this.getFillingRatio() + "% (" + this.volume + "/" + this.maxVolume + "L)";
        return "Baignoire vide (0/" + this.maxVolume + "L)";
     }
}
