package utils;

public class PatchFailedException extends DiffException {
    
    private static final long serialVersionUID = 1L;
    
    public PatchFailedException() {
    }
    
    public PatchFailedException(String msg) {
        super(msg);
    }
}
