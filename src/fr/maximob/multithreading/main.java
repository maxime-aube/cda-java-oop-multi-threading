package fr.maximob.multithreading;

public class main {

    public static void main(String[] args) {

        // création des objets baignoire et robinet
        Baignoire baignoire = new Baignoire(1000, 500, 10);
        Robinet robinet = new Robinet(baignoire, 15);

        // création des threads baiognoire et robinet
        Thread threadBaignoire = new Thread(baignoire);
        Thread threadRobinet = new Thread(robinet);

        // démarrage des threads
        threadRobinet.start();
        threadBaignoire.start();
    }
}
