package fr.maximob.multithreading;

public class main {

    public static void main(String[] args) {

        // création des objets baignoire et robinet
        Baignoire baignoire = new Baignoire(1000, 0, 11);
        Robinet robinet = new Robinet(baignoire, 10);

        // création des threads baignoire et robinet
        Thread threadRobinet = new Thread(robinet);
        Thread threadBaignoire = new Thread(baignoire);

        // démarrage des threads
        threadRobinet.start();
        threadBaignoire.start();
    }
}
