package availity.enrollees;

public final class Enrollee {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final int     version;
    private final String insuranceCompany;

    public Enrollee(String csv) {
        String[] tokens = csv.split(",");
        this.userId = tokens[0];
        this.firstName = tokens[1];
        this.lastName = tokens[2];
        this.version = Integer.parseInt(tokens[3]);
        this.insuranceCompany = tokens[4];
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getVersion() {
        return version;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", version=" + version +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                '}';
    }

    public String toCsv() {
        return String.format("%s,%s,%s,%d,%s\n", userId, firstName, lastName, version,insuranceCompany);

    }
}
