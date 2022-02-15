package kjd.linkedin.explorecali.common;

public enum Difficulty {
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    VARIES("Varies");

    public final String label;
    private Difficulty(String label) {
        this.label = label;    
    }

    public static Difficulty findByLabel(String label) {
        for (Difficulty d : Difficulty.values()) {
            if (d.label.equalsIgnoreCase(label)) {
                return d;
            }
        }

        return VARIES;
    }
}