package ics.phylosophiers;


public class PhilosopherHandler {
    private static class DieException extends Exception{

    }
    private static class Fork{
        public Philosopher philosopher;
    }
    private static class Philosopher implements Runnable{
        public Fork lefterFork, righterFork;
        public Philosopher lefterPhilosopher, righterPhilosopher;
        private long lastDinnerTime;
        private String name;
        private static final long diedLine = 1000;
        Philosopher(){}
        Philosopher(Fork lefterFork,Fork righterFork, String name){
            this.lefterFork = lefterFork;
            this.righterFork = righterFork;
            lastDinnerTime = System.currentTimeMillis();
            this.name = name;
        }
        protected void say(String text){
            System.out.print(new StringBuilder(name).append(": ").append(text).append("\n"));
        }
        protected void take(Fork fork) throws DieException{
            synchronized (fork){
                while(fork.philosopher != null)
                    isDie();
                fork.philosopher = this;
                if (fork == lefterFork) say("catch the left fork!");
                else say("right fork landed in my hand.");
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        protected void drop(Fork fork){
            synchronized (fork) {fork.philosopher = null;}
            if (fork == lefterFork) say("dropped the left fork.");
            else say("right fork jumped out of my hand.");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void run(){
            while (true){
                try{
                    take(lefterFork);
                    take(righterFork);
                    omNomNom();
                    drop(lefterFork);
                    drop(righterFork);
                }
                catch (DieException e){
                    break;
                }
            }
        }
        protected void omNomNom(){
            say("philosopher got 2 forks and eating the food");
            lastDinnerTime = System.currentTimeMillis();
        }
        protected void isDie() throws DieException{
            if((System.currentTimeMillis() - lastDinnerTime) > diedLine){
                say("philosopher die.");
                throw new DieException();
            }
        }
    }
    private static class SmarterPhilosopher extends Philosopher{
        SmarterPhilosopher(Fork lefterFork,Fork righterFork, String name){
            super(lefterFork,righterFork,name);
        }
        @Override
        public void run(){    //нет они так и не стали умнее
            while (true){
                try{
                    isDie();
                    if(lefterPhilosopher.lefterFork.philosopher == null){
                        take(lefterFork);
                        //if(righterPhilosopher.righterFork.philosopher == null){
                            take(righterFork);
                            omNomNom();
                            drop(lefterFork);
                            drop(righterFork);
                            try {Thread.sleep(200);} catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        //}
                    }
                    else say(".");
                }
                catch (DieException e){
                    break;
                }
            }
        }
    }
    private static Fork[] forks = new Fork[5];
    private static String[] names = {"Parmenid","Socrat","Kant","Decart","Leibniz"};
    private static Philosopher[] philosophers = new Philosopher[5];
    public static void main(String[] args){
        for(int i = 0; i < forks.length; i++)
            (forks[i] = new Fork()).philosopher = null;
        philosophers[0] = new Philosopher(forks[0], forks[1],names[0]);
        for(int i = 1; i < 5; i++){
            philosophers[i] = new Philosopher(forks[i], forks[((i+1)%5)],names[i]);
            philosophers[i].lefterPhilosopher = philosophers[i-1];
            philosophers[i-1].righterPhilosopher = philosophers[i];
        }
        philosophers[0].lefterPhilosopher = philosophers[4];
        philosophers[4].righterPhilosopher = philosophers[0];
        for(Philosopher philosopher: philosophers){
            new Thread(philosopher).start();
        }
    }
}
