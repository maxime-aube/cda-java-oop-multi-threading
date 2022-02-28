package fr.maximob.multithreading;

import static java.lang.Thread.sleep;

public class Robinet implements Runnable {

    protected Baignoire baignoire;
    protected final int volume;

    public Robinet (Baignoire baignoire, int volume) {
        this.baignoire = baignoire;
        this.volume = Math.max(volume, 0);
    }

    @Override
    public void run() {
        debite();
    }

    public void debite() {
        if (this.volume == 0) return;
        while (!this.baignoire.isFull() && this.baignoire.getVolumeFuite() > 0) {
//            System.out.println(this.baignoire.isFull());
//            System.out.println(this.baignoire.getVolumeFuite());
            synchronized (this.baignoire) {
                final int newVolume = this.baignoire.getVolume() + this.volume;
                this.baignoire.setVolume(Math.min(newVolume, this.baignoire.maxVolume));
                System.out.println("débite ↗ " + this.volume + "L : " + this.baignoire.toString());
            }
            try {
                sleep(2);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public Baignoire getBaignoire() {
        return this.baignoire;
    }

    public int getVolume() {
        return this.volume;
    }

    public void afficher() {
        System.out.println(this.toString());
    }

    public String toString() {
        return "Robinet d'un débit de " + this.volume + "L, rattaché à : " + this.baignoire.toString();
    }
}
