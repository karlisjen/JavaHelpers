package utils;

import java.util.List;

/**
 * Describes the delete-delta between original and revised texts.
 * 
 * @author <a href="dm.naumenko@gmail.com">Dmitry Naumenko</a>
 * @param T The type of the compared elements in the 'lines'.
 */
public class DeleteDelta<T> extends Delta<T> {
    
	/**
	 * Creates a change delta with the two given chunks.
	 * 
	 * @param original
	 *            The original chunk. Must not be {@code null}.
	 * @param revised
	 *            The original chunk. Must not be {@code null}.
	 */
    public DeleteDelta(Chunk<T> original, Chunk<T> revised) {
        super(original, revised);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws PatchFailedException
     */
    @Override
    public void applyTo(List<T> target) throws PatchFailedException {
        verify(target);
        int position = getOriginal().getPosition();
        int size = getOriginal().size();
        for (int i = 0; i < size; i++) {
            target.remove(position);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void restore(List<T> target) {
        int position = this.getRevised().getPosition();
        List<T> lines = this.getOriginal().getLines();
        for (int i = 0; i < lines.size(); i++) {
            target.add(position + i, lines.get(i));
        }
    }
    
    @Override
    public TYPE getType() {
        return Delta.TYPE.DELETE;
    }
    
    @Override
    public void verify(List<T> target) throws PatchFailedException {
        getOriginal().verify(target);
    }
    
    @Override
    public String toString() {
        return "[DeleteDelta, position: " + getOriginal().getPosition() + ", lines: "
                + getOriginal().getLines() + "]";
    }
}
