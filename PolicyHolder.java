public class PolicyHolder {

    // -------------------- Fields --------------------
    private String policyholderFirstName;
    private String policyholderLastName;
    private int policyholderAge;
    private String policyholderSmokingStatus; // "smoker" or "non-smoker"
    private double policyholderHeightInInches; // inches
    private double policyholderWeightInPounds; // pounds

    // -------------------- Constructors --------------------

    /**
     * Constructor with all fields.
     */
    public PolicyHolder(String firstName, String lastName, int age,
                        String smokingStatus, double heightInches, double weightPounds) {
        policyholderFirstName = firstName;
        policyholderLastName = lastName;
        policyholderAge = age;
        policyholderSmokingStatus = smokingStatus;
        policyholderHeightInInches = heightInches;
        policyholderWeightInPounds = weightPounds;
    }

    /**
     * Copy constructor (for security of aggregate class usage).
     */
    public PolicyHolder(PolicyHolder other) {
        policyholderFirstName = other.policyholderFirstName;
        policyholderLastName = other.policyholderLastName;
        policyholderAge = other.policyholderAge;
        policyholderSmokingStatus = other.policyholderSmokingStatus;
        policyholderHeightInInches = other.policyholderHeightInInches;
        policyholderWeightInPounds = other.policyholderWeightInPounds;
    }

    // -------------------- Getters (accessors) --------------------

    public String getPolicyholderFirstName() {
        return policyholderFirstName;
    }

    public String getPolicyholderLastName() {
        return policyholderLastName;
    }

    public int getPolicyholderAge() {
        return policyholderAge;
    }

    public String getPolicyholderSmokingStatus() {
        return policyholderSmokingStatus;
    }

    public double getPolicyholderHeightInInches() {
        return policyholderHeightInInches;
    }

    public double getPolicyholderWeightInPounds() {
        return policyholderWeightInPounds;
    }

    // -------------------- Derived values --------------------

    /**
     * Computes Body Mass Index (BMI).
     * Formula: BMI = 703 * weight(lbs) / (height(in))^2
     */
    public double getBMI() {
        double h = policyholderHeightInInches;
        double w = policyholderWeightInPounds;
        if (h <= 0) {
            return 0.0;
        }
        return (703.0 * w) / (h * h);
    }

    // -------------------- toString --------------------

    /**
     * Returns a formatted description of the policyholder.
     */
    @Override
    public String toString() {
        String result = "";
        result += "Policyholder's First Name: " + policyholderFirstName + "\n";
        result += "Policyholder's Last Name: " + policyholderLastName + "\n";
        result += "Policyholder's Age: " + policyholderAge + "\n";
        result += "Policyholder's Smoking Status (Y/N): " + policyholderSmokingStatus + "\n";
        result += "Policyholder's Height: " + policyholderHeightInInches + " inches\n";
        result += "Policyholder's Weight: " + policyholderWeightInPounds + " pounds\n";
        result += String.format("Policyholder's BMI: %.2f%n", getBMI());
        return result;
    }
}
