package utils;

import java.util.*;


public interface DiffAlgorithm<T> {
    

    public Patch<T> diff(T[] original, T[] revised);
    

    public Patch<T> diff(List<T> original, List<T> revised);
}
