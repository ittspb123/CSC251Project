import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project_Precious_Bikusa {

    public static void main(String[] args) {
        ArrayList<Policy> policies = new ArrayList<>();
        int smokerCount = 0;
        int nonSmokerCount = 0;

        File inputFile = new File("PolicyInformation.txt");

        try (Scanner file = new Scanner(inputFile)) {
            while (true) {
                String policyNumber  = nextNonEmptyLine(file);
                if (policyNumber == null) break; // reached EOF

                String providerName  = nextNonEmptyLine(file);
                String firstName     = nextNonEmptyLine(file);
                String lastName      = nextNonEmptyLine(file);
                String ageStr        = nextNonEmptyLine(file);
                String smokingStatus = nextNonEmptyLine(file);
                String heightStr     = nextNonEmptyLine(file);
                String weightStr     = nextNonEmptyLine(file);

                int age = Integer.parseInt(ageStr.trim());
                double heightInches = Double.parseDouble(heightStr.trim());
                double weightPounds = Double.parseDouble(weightStr.trim());

                Policy policy = new Policy(
                        policyNumber,
                        providerName,
                        firstName,
                        lastName,
                        age,
                        smokingStatus,
                        heightInches,
                        weightPounds
                );
                policies.add(policy);

                if ("smoker".equalsIgnoreCase(smokingStatus)) {
                    smokerCount++;
                } else {
                    nonSmokerCount++;
                }

                // consume optional blank separator line(s) between records
                // (nextNonEmptyLine will already skip blanks the next loop)
            }
        } catch (IOException e) {
            System.out.println("Could not read PolicyInformation.txt: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Input format error: " + e.getMessage());
            return;
        }

        // Print each policy (Project 1 format)
        for (Policy policy : policies) {
            System.out.println();
            System.out.println("Policy Number: " + policy.getPolicyNumber());
            System.out.println("Provider Name: " + policy.getProviderName());
            System.out.println("Policyholder's First Name: " + policy.getPolicyholderFirstName());
            System.out.println("Policyholder's Last Name: " + policy.getPolicyholderLastName());
            System.out.println("Policyholder's Age: " + policy.getPolicyholderAge());
            System.out.println("Policyholder's Smoking Status (smoker/non-smoker): " + policy.getPolicyholderSmokingStatus());
            System.out.println("Policyholder's Height: " + policy.getPolicyholderHeightInInches() + " inches");
            System.out.println("Policyholder's Weight: " + policy.getPolicyholderWeightInPounds() + " pounds");
            System.out.printf("Policyholder's BMI: %.2f%n", policy.getBMI());
            System.out.printf("Policy Price: $%.2f%n", policy.getPolicyPrice());
            System.out.println();
        }

        // Totals
        System.out.println("The number of policies with a smoker is: " + smokerCount);
        System.out.println("The number of policies with a non-smoker is: " + nonSmokerCount);
    }

    // Returns next non-empty trimmed line, or null if EOF reached before such a line.
    private static String nextNonEmptyLine(Scanner file) {
        while (file.hasNextLine()) {
            String line = file.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
        }
        return null;
    }
}
