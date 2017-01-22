package pl.bigbook;

import rx.Observable;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        ex1();
        Observ example = new Observ();
        Observable<String> result = example.ex2();

        Observable nums;
        Integer[] ints = {1, 2, 3, 4, 54, 6, 6, 7, 34, 32, 423, 5423};
        nums = Observable.from(ints);
        example.filterNums(nums).subscribe(System.out::println);

    }

    private static class Observ{

        private Observable<String> ex2(){
            return Observable.just("Hello World!").map(t -> t.toLowerCase());
        }

        private Observable<String> filterNums(Observable<Integer> input){
            return input.filter(i -> i % 2 == 0).map(i -> String.format("%d - Even", i));
        }


    }




    private static void ex1() {
        System.out.println( "Hello World!" );

        Observable<String> mover1 = Observable.create(onSubscribe -> {

            List<String> lista = new ArrayList<>();
            for(int i=0; i< 100; i++) {
                lista.add("A"+i);
            }

            for(String item: lista) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    onSubscribe.onError(e);
                }
                onSubscribe.onNext(item);
            }
        });

        Observable<String> mover2 = mover1.map(String::toLowerCase);

        Subscription mover3 = mover2.subscribe(System.out::println);
    }
}
