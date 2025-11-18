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
                if (policyNumber == null) {
                    break; // reached EOF
                }

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

                // Create PolicyHolder object (class collaboration)
                PolicyHolder holder = new PolicyHolder(
                        firstName,
                        lastName,
                        age,
                        smokingStatus,
                        heightInches,
                        weightPounds
                );

                // Create Policy that has-a PolicyHolder
                Policy policy = new Policy(
                        policyNumber,
                        providerName,
                        holder
                );

                policies.add(policy);

                // Count smokers / non-smokers based on the PolicyHolder
                if ("smoker".equalsIgnoreCase(holder.getPolicyholderSmokingStatus())) {
                    smokerCount++;
                } else {
                    nonSmokerCount++;
                }

                // any blank line separator is handled by nextNonEmptyLine in the next loop
            }
        } catch (IOException e) {
            System.out.println("Could not read PolicyInformation.txt: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Input format error: " + e.getMessage());
            return;
        }

        // Print each policy using implicit toString
        for (Policy policy : policies) {
            System.out.println();
            System.out.print(policy);  // toString() is called implicitly
            System.out.println();
        }

        // Totals
        System.out.println("There were " + Policy.getPolicyCount() + " Policy objects created.");
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

