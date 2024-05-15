
package com.helpers;

import java.util.List;


public class ChangeDelta<T> extends Delta<T> {
    

    public ChangeDelta(Chunk<T> original, Chunk<T>revised) {
    	super(original, revised);
    }
    

    @Override
    public void applyTo(List<T> target) throws PatchFailedException {
        verify(target);
        int position = getOriginal().getPosition();
        int size = getOriginal().size();
        for (int i = 0; i < size; i++) {
            target.remove(position);
        }
        int i = 0;
        for (T line : getRevised().getLines()) {
            target.add(position + i, line);
            i++;
        }
    }
    

    @Override
    public void restore(List<T> target) {
        int position = getRevised().getPosition();
        int size = getRevised().size();
        for (int i = 0; i < size; i++) {
            target.remove(position);
        }
        int i = 0;
        for (T line : getOriginal().getLines()) {
            target.add(position + i, line);
            i++;
        }
    }
    

    public void verify(List<T> target) throws PatchFailedException {
        getOriginal().verify(target);
        if (getOriginal().getPosition() > target.size()) {
            throw new PatchFailedException("Incorrect patch for delta: "
                    + "delta original position > target size");
        }
    }
    
    @Override
    public String toString() {
        return "[ChangeDelta, position: " + getOriginal().getPosition() + ", lines: "
                + getOriginal().getLines() + " to " + getRevised().getLines() + "]";
    }

    @Override
    public TYPE getType() {
        return Delta.TYPE.CHANGE;
    }
}
