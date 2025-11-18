/**
 * Represents an insurance policy and provides utilities
 * to compute BMI and the policy price.
 * Provides utilities to compute the policy price.
 */
/**
 * Represents an insurance policy and collaborates with a PolicyHolder.
 * Provides utilities to compute the policy price.
 */
public class Policy {

    // -------------------- Static field --------------------
    // Tracks how many Policy objects have been created.
    private static int policyCount = 0;

    // -------------------- Fields --------------------
    private String policyNumber;
    private String providerName;
    private PolicyHolder policyHolder;   // aggregate object

    // -------------------- Constructors --------------------

    /**
     * Constructs a Policy that "has-a" PolicyHolder.
     * Uses a copy of the PolicyHolder to prevent security issues.
     */
    public Policy(String policyNumber, String providerName,
                  PolicyHolder policyHolder) {
        this.policyNumber = policyNumber;
        this.providerName = providerName;
        // defensive copy
        this.policyHolder = new PolicyHolder(policyHolder);

        policyCount++;   // increment static counter
    }

    // -------------------- Static methods --------------------

    /**
     * @return number of Policy objects that have been created.
     */
    public static int getPolicyCount() {
        return policyCount;
    }

    // -------------------- Getters --------------------

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
     * Returns a copy of the PolicyHolder to avoid exposing the internal object.
     */
    public PolicyHolder getPolicyHolder() {
        return new PolicyHolder(policyHolder); // defensive copy
    }

    /**
     * Allows changing the PolicyHolder safely (using a copy).
     */
    public void setPolicyHolder(PolicyHolder policyHolder) {
        this.policyHolder = new PolicyHolder(policyHolder);
    }

    // -------------------- Derived values --------------------

    /**
     * Computes the policy price based on rules:
     * base $600, +$75 if age > 50, +$100 if smoker,
     * plus $20 per BMI point above 35 (if any).
     *
     * @return policy price as a double.
     */
    public double getPolicyPrice() {
        double basePrice = 600.0;
        double price = basePrice;

        int age = policyHolder.getPolicyholderAge();
        String smokingStatus = policyHolder.getPolicyholderSmokingStatus();
        double bmi = policyHolder.getBMI();

        if (age > 50) {
            price += 75.0;
        }
        if ("smoker".equalsIgnoreCase(smokingStatus)) {
            price += 100.0;
        }
        if (bmi > 35.0) {
            price += (bmi - 35.0) * 20.0;
        }

        return price;
    }

    // -------------------- toString --------------------

    /**
     * Returns a formatted description of the policy and the policyholder.
     */
    @Override
    public String toString() {
        String result = "";
        result += "Policy Number: " + policyNumber + "\n";
        result += "Provider Name: " + providerName + "\n";
        // delegate person details to PolicyHolder's toString
        result += policyHolder.toString();
        result += String.format("Policy Price: $%.2f%n", getPolicyPrice());
        return result;
    }
}
