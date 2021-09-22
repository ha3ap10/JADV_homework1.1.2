package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    static final int THREADS_COUNT = 4;

    public static void main(String[] args){

        final ExecutorService threadPool = Executors.newFixedThreadPool(THREADS_COUNT);

        List<MyCallable> callableList = new ArrayList<>();

        for (int i = 1; i <= THREADS_COUNT; i++) {
            callableList.add(new MyCallable("'Callable " + i + "'"));
        }
        resultAny(threadPool);

        System.out.println();

        resultAll(threadPool);

        threadPool.shutdown();
    }

    public static void resultAny(ExecutorService executorService) {

        List<MyCallable> callableList = new ArrayList<>();

        for (int i = 1; i <= THREADS_COUNT; i++) {
            callableList.add(new MyCallable("'Callable " + i + "'"));
        }

        int result = 0;

        try {
            result = executorService.invokeAny(callableList);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Результат: " + result);
    }

    public static void resultAll(ExecutorService executorService) {

        List<MyCallable> callableList = new ArrayList<>();

        for (int i = 1; i <= THREADS_COUNT; i++) {
            callableList.add(new MyCallable("'Callable " + i + "'"));
        }

        try {
            List<Future<Integer>> resultList = executorService.invokeAll(callableList);
            for (Future<Integer> future : resultList) {
                System.out.printf("Результат 'Callable %d': %d\n", resultList.indexOf(future) + 1, future.get());
            }
        } catch (InterruptedException  | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
