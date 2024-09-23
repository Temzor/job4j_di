package io.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    char[] process = new char[]{'|', '/', '|', '\\', '-'};

    /**
     * This method prints the progress bar in the console.
     * It consists of several symbols that are printed in turn.
     */
    @Override
    public void run() {
        try {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r Loading: " + process[i]);
                Thread.sleep(500);
                i++;
                if (i == process.length) {
                    i = 0;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(20000);
    }
}