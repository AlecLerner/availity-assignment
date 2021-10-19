package availity.enrollees;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Coding exercise:
    Availity receives enrollment files from various benefits management and enrollment solutions
     (I.e. HR platforms, payroll platforms).
     Most of these files are typically in EDI format.  However, there are some files in CSV format.
     For the files in CSV format, write a program that will read the content of the file
     and separate enrollees by insurance company in its own file.
     Additionally, sort the contents of each file by last and first name (ascending).
     Lastly, if there are duplicate User Ids for the same Insurance Company,
     then only the record with the highest version should be included.
     The following data points are included in the file:
        •	User Id (string)
        •	First Name (string)
        •	Last Name (string)
        •	Version (integer)
        •	Insurance Company (string)

 */

public class GroupEnrollees {
    private static final Map<String, TreeMap<String, Enrollee>> group = new HashMap<>();
    private static final String outputDirectory = "output";

    public static void main(String[] args) {
        System.out.println("Group Enrollees");
        try {
            List<Enrollee> enrollees = readEnrollees("enrollees.csv");
            enrollees.forEach(System.out::println);
            groupEnrolleesByInsurance(enrollees);
            System.out.println("---------------------------RESULTS---------------------");
            printGroup();
            generateFiles();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void generateFiles() throws IOException {
        createOrCleanDirectory();

        for (String insurance : group.keySet()) {

            final String fileName = outputDirectory + '/' + insurance + ".csv";
            // create file for each directory
            Files.createFile(Paths.get(fileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            TreeMap<String, Enrollee> map = group.get(insurance);
            // write to the file
            for (Enrollee enr : map.values()) {
                writer.append(enr.toCsv());
            }
            writer.close();
        }
    }

    private static void createOrCleanDirectory() {
        File dir = new File(outputDirectory);
        if (!dir.exists()) {
            dir.mkdir();
        } else {            // delete all files from this directory
            for (File file : dir.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        }
    }

    private static void printGroup() {
        for (String insurance : group.keySet()) {
            System.out.println("Insurance: " + insurance);
            TreeMap<String, Enrollee> map = group.get(insurance);
            map.values().stream().forEach(System.out::println);
        }

    }

    private static void groupEnrolleesByInsurance(List<Enrollee> enrollees) {
        for (Enrollee enr : enrollees) {
            String insurance = enr.getInsuranceCompany();
            TreeMap<String, Enrollee> enrMap = group.get(insurance);
            String key = enr.getLastName() + '_' + enr.getFirstName();
            if (enrMap != null) {
                Enrollee existingEnr = enrMap.get(key);
                if (existingEnr != null) {
                    // if there are duplicate User Ids for the same Insurance Company,
                    if (existingEnr.getUserId().equals(enr.getUserId())) {
                        if (enr.getVersion() > existingEnr.getVersion()) {
                            // then only the record with the highest version should be included
                            enrMap.put(key, enr);
                        }
                    }
                } else {
                    enrMap.put(key, enr);
                }
            } else {
                enrMap = new TreeMap<>();
                enrMap.put(key, enr);
                group.put(insurance, enrMap);
            }
        }
    }


    static List<Enrollee> readEnrollees(String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.map(Enrollee::new).collect(Collectors.toList());
        }
    }
}
