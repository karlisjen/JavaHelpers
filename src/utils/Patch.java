package utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Patch<T> {
    private List<Delta<T>> deltas = new LinkedList<Delta<T>>();

    /**
     * Apply this patch to the given target
     * @return the patched text
     * @throws PatchFailedException if can't apply patch
     */
    public List<T> applyTo(List<T> target) throws PatchFailedException {
        List<T> result = new LinkedList<T>(target);
        ListIterator<Delta<T>> it = getDeltas().listIterator(deltas.size());
        while (it.hasPrevious()) {
            Delta<T> delta = (Delta<T>) it.previous();
            delta.applyTo(result);
        }
        return result;
    }
    
    /**
     * Restore the text to original. Opposite to applyTo() method.
     * @param target the given target
     * @return the restored text
     */
    public List<T> restore(List<T> target) {
        List<T> result = new LinkedList<T>(target);
        ListIterator<Delta<T>> it = getDeltas().listIterator(deltas.size());
        while (it.hasPrevious()) {
            Delta<T> delta = (Delta<T>) it.previous();
            delta.restore(result);
        }
        return result;
    }
    
    /**
     * Add the given delta to this patch
     * @param delta the given delta
     */
    public void addDelta(Delta<T> delta) {
        deltas.add(delta);
    }

    /**
     * Get the list of computed deltas
     * @return the deltas
     */
    public List<Delta<T>> getDeltas() {
        Collections.sort(deltas, DeltaComparator.INSTANCE);
        return deltas;
    }
}
