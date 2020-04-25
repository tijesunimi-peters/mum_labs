package interfaces;

public interface CustomFunction<T, U,K,J, R> {
    R apply(T t, U u,K k,J j);
}
