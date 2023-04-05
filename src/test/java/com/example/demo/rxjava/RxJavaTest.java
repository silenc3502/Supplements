package com.example.demo.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class RxJavaTest {

    @Test
    @SuppressWarnings("Depricated")
    public void simpleRxJavaWorkflow() {
        Observable<String> observable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("Hello, reactive world!");
                        emitter.onComplete();
                    }
                }
        );
    }

    @Test
    @SuppressWarnings("Depricated")
    public void simpleRxJavaWorkflowWithLambdas() {
        Observable.create(
                sub -> {
                    sub.onNext("Hello, reactive world!");
                    sub.onComplete();
                }
        ).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Done!")
        );
    }

    @Test
    public void creatingRxStreams() {
        Observable.just("1", "2", "3", "4");
        Observable.fromArray(new String[]{"A", "B", "C"});
        Observable.fromArray(Collections.<String>emptyList());

        Observable<String> hello = Observable.fromCallable(() -> "Hello ");
        Future<String> future =
                Executors.newCachedThreadPool().submit(() -> "World");
        Observable<String> world = Observable.fromFuture(future);

        Observable.concat(hello, world, Observable.just("!"))
                .forEach(System.out::print);
    }

    @Test
    public void zipOperatorExample() {
        Observable.zip(
                Observable.just("A", "B", "C"),
                Observable.just("1", "2", "3"),
                (x, y) -> x + y
        ).forEach(System.out::println);
    }

    @Test
    public void timeBasedSequenceExample() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(e -> System.out.println("Received: " + e));

        Thread.sleep(5000);
    }

    @Test
    public void managingSubscription() {
        AtomicReference<Disposable> subscription = new AtomicReference<>();
        subscription.set(Observable.interval(100, MILLISECONDS)
                .subscribe(e -> {
                    System.out.println("Received: " + e);
                    if (e >= 3) {
                        subscription.get().dispose();

                    }
                }));

        do {
            // executing something useful...
        } while (!subscription.get().isDisposed());
    }

    @Test
    public void managingSubscription2() throws InterruptedException {
        CountDownLatch externalSignal = new CountDownLatch(3);

        Disposable subscription = Observable
                .interval(100, MILLISECONDS)
                .subscribe(System.out::println);

        externalSignal.await(450, MILLISECONDS);
        subscription.dispose();
    }

    @Test
    public void deferSynchronousRequest() throws Exception {
        String query = "query";
        Observable.fromCallable(() -> doSlowSyncRequest(query))
                .subscribeOn(Schedulers.io())
                .subscribe(this::processResult);

        Thread.sleep(1000);
    }

    private String doSlowSyncRequest(String query) {
        return "result";
    }

    private void processResult(String result) {
        System.out.println(Thread.currentThread().getName() + ": " + result);
    }
}
