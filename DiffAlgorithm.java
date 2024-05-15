package utils;

import java.util.*;

/**
 * The general interface for computing diffs between two lists of elements of type T. 
 * 
 * @author <a href="dm.naumenko@gmail.com">Dmitry Naumenko</a>
 * @param T The type of the compared elements in the 'lines'.
 */
public interface DiffAlgorithm<T> {
    
    /**
     * Computes the difference between the original sequence and the revised
     * sequence and returns it as a {@link Patch} object.
     * 
     * @param original The original sequence. Must not be {@code null}.
     * @param revised The revised sequence. Must not be {@code null}.
     * @return The patch representing the diff of the given sequences. Never {@code null}.
     */
    public Patch<T> diff(T[] original, T[] revised);
    
    /**
     * Computes the difference between the original sequence and the revised
     * sequence and returns it as a {@link Patch} object.
     * 
     * @param original The original sequence. Must not be {@code null}.
     * @param revised The revised sequence. Must not be {@code null}.
     * @return The patch representing the diff of the given sequences. Never {@code null}.
     */
    public Patch<T> diff(List<T> original, List<T> revised);
}
