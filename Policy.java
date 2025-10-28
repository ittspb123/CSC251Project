/**
 * Represents an insurance policy and provides utilities
 * to compute BMI and the policy price.
 */
public class Policy {

    // -------------------- Fields --------------------
    private String policyNumber;
    private String providerName;

    private String policyholderFirstName;
    private String policyholderLastName;
    private int policyholderAge;
    private String policyholderSmokingStatus; // "smoker" or "non-smoker"
    private double policyholderHeightInInches; // inches
    private double policyholderWeightInPounds; // pounds

    // -------------------- Constructors --------------------

    /**
     * Creates a Policy with all details specified.
      
     */
    public Policy(String policyNumber, String providerName,
                  String firstName, String lastName, int age,
                  String smokingStatus, double heightInches, double weightPounds) {
        this.policyNumber = policyNumber;
        this.providerName = providerName;
        this.policyholderFirstName = firstName;
        this.policyholderLastName = lastName;
        this.policyholderAge = age;
        this.policyholderSmokingStatus = smokingStatus;
        this.policyholderHeightInInches = heightInches;
        this.policyholderWeightInPounds = weightPounds;
    }

    // -------------------- Getters (accessors) --------------------

    /**
     * @return the policy number
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * @return the provider (insurance company) name
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * @return policyholder's first name
     */
    public String getPolicyholderFirstName() {
        return policyholderFirstName;
    }

    /**
     * @return policyholder's last name
     */
    public String getPolicyholderLastName() {
        return policyholderLastName;
    }

    /**
     * @return policyholder's age in years
     */
    public int getPolicyholderAge() {
        return policyholderAge;
    }

    /**
     * @return "smoker" or "non-smoker"
     */
    public String getPolicyholderSmokingStatus() {
        return policyholderSmokingStatus;
    }

    /**
     * @return policyholder's height in inches
     */
    public double getPolicyholderHeightInInches() {
        return policyholderHeightInInches;
    }

    /**
     * @return policyholder's weight in pounds
     */
    public double getPolicyholderWeightInPounds() {
        return policyholderWeightInPounds;
    }

    // -------------------- Derived values --------------------

    /**
     * Computes Body Mass Index (BMI).
     *
     * Formula: BMI = 703 * weight(lbs) / (height(in))^2
     *
     * @return BMI as a double
     */
    public double getBMI() {
        double h = policyholderHeightInInches;
        double w = policyholderWeightInPounds;
        if (h <= 0) return 0.0;
        return (703.0 * w) / (h * h);
    }

    /**
     * Computes the policy price based on rules:
     * base $600, +$75 if age > 50, +$100 if smoker,
     * plus $20 per BMI point above 35 (if any).
     *
     * @return policy price in dollars
     */
    public double getPolicyPrice() {
        double price = 600.0;

        if (policyholderAge > 50) {
            price += 75.0;
        }
        if ("smoker".equalsIgnoreCase(policyholderSmokingStatus)) {
            price += 100.0;
        }

        double bmi = getBMI();
        if (bmi > 35.0) {
            price += (bmi - 35.0) * 20.0;
        }

        return price;
    }
}
