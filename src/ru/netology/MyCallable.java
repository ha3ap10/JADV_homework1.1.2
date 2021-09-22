package ru.netology;

import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    private int count;
    private String name;
    private int maxCount = new Random().nextInt(10) + 10; /*10*/

    public MyCallable(String name) {
        this.name = name;
    }


    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < maxCount; i++) {
            count++;
            Thread.sleep(200);
            System.out.printf("Я %s. Вывод сообщения №%d\n", getName(), getCount());
        }
        return count;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
