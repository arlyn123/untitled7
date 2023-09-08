public class Counter {
    private int count;

    public void increment() {
        count++;
    }

    public int getValue() {
        return count;
    }
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
                }
            }
        });
        thread1.start();
        thread2.start();

       try {
           thread1.join();
           thread2.join();
       } catch (InterruptedException e){
           throw new RuntimeException(e);
       }
        System.out.println(counter.getValue());
    }
}
//Два потока одновременно вызывают один метод, но им обоим не хватает ресурса и они работают по очереди, поэтому они могут
//прочитать одно и то же значение count, увеличат на один и запишут результат

