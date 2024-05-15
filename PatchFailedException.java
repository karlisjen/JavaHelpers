package utils;

/**
 * Thrown whenever a delta cannot be applied as a patch to a given text.
 *
 * @author <a href="mailto:juanco@suigeneris.org">Juanco Anez</a>
 */
public class PatchFailedException extends DiffException {
    
    private static final long serialVersionUID = 1L;
    
    public PatchFailedException() {
    }
    
    public PatchFailedException(String msg) {
        super(msg);
    }
}
