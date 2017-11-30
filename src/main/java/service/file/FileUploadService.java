package service.file;

import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author majiasheng
 */
@Service
public class FileUploadService {

    public final String CSV_FILE = "csv";

    public boolean handleFileUpload(Map<String, File> files) {
        throw new UnsupportedOperationException();
//        return false;
    }

    public boolean isCSV(MultipartFile multipartFile) {
        if (CSV_FILE.equals(multipartFile.getOriginalFilename().split("\\.")[1])) {
            return true;
        }
        return false;
    }

    public File multipartFileToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

    public void uploadGeoData(String geoFolderPath, int congress) {

        int districtNum;
        String stateName;
        String geoText;

        List<Geo> list = new ArrayList<Geo>();

        try {
            File geoFolder = new File(geoFolderPath);
            //loop through all states
            for (final File stateFile : geoFolder.listFiles()) {
                if (stateFile.isDirectory()) {
                    stateName = stateFile.getName();

                    //loop through all districts
                    for (final File districtFile : stateFile.listFiles()) {
                        if (districtFile.isFile()) {

                            String temp = FilenameUtils.removeExtension(districtFile.getName());
                            if (!temp.contains("District Of Columbia")) {
                                districtNum = Integer.valueOf(temp.substring(temp.lastIndexOf("t") + 1));
                                if (districtNum == 0) {
                                    districtNum = 1;
                                }
                                geoText = FileUtils.readFileToString(districtFile, "utf-8");

                                list.add(new Geo(stateName, districtNum, geoText));
                            }
                        }
                    }
                }
            }
            //do insert
            doInsertGeo(list, congress);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doInsertGeo(List<Geo> list, int congress) throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        String jdbcUrl = "jdbc:mysql://mysql3.cs.stonybrook.edu:3306/aspen?useSSL=false";
        String username = "aspen";
        String password = "changeit";
        String sql = "{call INSERT_GEO(?,?,?,?)}";

        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            CallableStatement stmt = conn.prepareCall(sql);
            conn.setAutoCommit(false);
            int i = 0;
            for (Geo geo : list) {
                //Set IN parameter
                stmt.setString(1, geo.getStateName());
                stmt.setInt(2, congress);
                stmt.setInt(3, geo.getDistrictNum());
                stmt.setString(4, geo.getGeoText());

                stmt.addBatch();
                i++;
                if (i % 1000 == 0 || i == list.size()) {
                    System.out.println(i + " records being inserted");
                    int[] count = stmt.executeBatch(); // Execute every 1000 items.  
                    conn.commit();
                    System.out.println(count.length + " records inserted");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void uploadDemoData(String demoFilePath, int congress) {

        List<Demo> list = new ArrayList<Demo>();

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(demoFilePath));
            String[] line;
            String state;
            int districtNum;
            String temp;
            while ((line = reader.readNext()) != null) {
                if (line[0].contains("Congressional District")) {
                    state = line[0].substring(line[0].lastIndexOf(", ") + 2);
                    temp = between(line[0], ("Congressional District "), " (");
                    if (isInt(temp)) {
                        districtNum = Integer.valueOf(temp);
                    } else {
                        districtNum = 1;
                    }

                    Demo demo = new Demo()
                            .setState(state)
                            .setDistrictNum(districtNum)
                            .setTotal(Integer.valueOf(line[1]))
                            .setWhite(Integer.valueOf(line[2]))
                            .setBlack(Integer.valueOf(line[3]))
                            .setIndian(Integer.valueOf(line[4]))
                            .setAsian(Integer.valueOf(line[5]))
                            .setIslander(Integer.valueOf(line[6]))
                            .setOther(Integer.valueOf(line[7]))
                            .setMixed(Integer.valueOf(line[8]));
                    list.add(demo);
                }
            }
            doInsertDemo(list, congress);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doInsertDemo(List<Demo> list, int congress) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String connURL = "jdbc:mysql://mysql3.cs.stonybrook.edu:3306/aspen?useSSL=false";
        String connUsername = "aspen";
        String connPassword = "changeit";
        String sql = "{call INSERT_DEMO(?,?,?,?,?,?,?,?,?,?,?)}";

        try {
            Connection conn = DriverManager.getConnection(connURL, connUsername, connPassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            int i = 0;
            for (Demo data : list) {
                //Set IN parameter
                stmt.setString(1, data.getState());
                stmt.setInt(2, congress);
                stmt.setInt(3, data.getDistrictNum());
                stmt.setInt(4, data.getTotal());
                stmt.setInt(5, data.getWhite());
                stmt.setInt(6, data.getBlack());
                stmt.setInt(7, data.getIndian());
                stmt.setInt(8, data.getAsian());
                stmt.setInt(9, data.getIslander());
                stmt.setInt(10, data.getOther());
                stmt.setInt(11, data.getMixed());

                stmt.addBatch();
                i++;
                if (i % 1000 == 0 || i == list.size()) {
                    System.out.println(i + " records being inserted");
                    int[] count = stmt.executeBatch(); // Execute every 1000 items.  
                    conn.commit();
                    System.out.println(count.length + " records inserted");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //<editor-fold defaultstate="collapsed" desc=" helper methods ">
    private String between(String value, String a, String b) {
        //Return a substring between the two strings.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        int posB = value.lastIndexOf(b);
        if (posB == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= posB) {
            return "";
        }
        return value.substring(adjustedPosA, posB);
    }

    private static boolean isInt(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    //</editor-fold>
}
