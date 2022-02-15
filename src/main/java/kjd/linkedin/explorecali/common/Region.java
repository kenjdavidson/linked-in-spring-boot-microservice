package kjd.linkedin.explorecali.common;

public enum Region {
    NORTHERN_CALIFORNIA("Northern California"),
    CENTRAL_COAST("Central Coast"),
    SOUTHERN_CALIFORNIA("Southern California"),
    VARIES("Varies");

    public String label;
    private Region(String label) {
        this.label = label;
    }

    public static Region findByLabel(String label) {
        for (Region r : Region.values()) {
            if (r.label.equalsIgnoreCase(label)) {
                return r;
            }
        }

        return VARIES;
    }
}
