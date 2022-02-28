package fr.maximob.multithreading;

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
        while (!this.isEmpty()) {
            final int newVolume = this.getVolume() - this.volumeFuite;
            if (newVolume < 0) this.setVolume(0);
            else this.setVolume(newVolume);
            System.out.println("fuite : " + this.toString());
        }
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
