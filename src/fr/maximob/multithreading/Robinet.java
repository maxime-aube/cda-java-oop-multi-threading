package fr.maximob.multithreading;

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
        while (!this.baignoire.isFull()) {
            final int newVolume = this.baignoire.getVolume() + this.volume;
            if (newVolume > this.baignoire.maxVolume) this.baignoire.setVolume(this.baignoire.maxVolume);
            else this.baignoire.setVolume(newVolume);
            System.out.println("débite : " + this.baignoire.toString());
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
