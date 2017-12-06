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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SessionConstant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.file.util.CSVParser;

/**
 *
 * @author majiasheng
 */
@Service
public class FileUploadService {

    public final String CSV_FILE = "csv";
    public final String DEM_PARTY = "Democratic";
    public final String REP_PARTY = "Republican";

    public boolean handleFileUpload(Map<String, MultipartFile> files) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String jdbcUrl = "jdbc:mysql://mysql3.cs.stonybrook.edu:3306/aspen?useSSL=false";
        String username = "aspen";
        String password = "changeit";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, username, password);

            if (uploadDemoData(files.get(SessionConstant.DEMOGRAPHIC_DATA_ATTRIBUTE), conn)
                    && uploadGeoData(files.get(SessionConstant.GEO_DATA_ATTRIBUTE), conn)
                    && uploadElectionData(files.get(SessionConstant.ELECTION_DATA_ATTRIBUTE), conn)) {
                return true;
            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(FileUploadService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FileUploadService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    /**
     * Geo csv file has the following format: district_number, state,
     * congress,geojson
     *
     * @param multipartFile
     * @return
     */
    public boolean uploadGeoData(MultipartFile multipartFile, Connection conn) {
        if (isCSV(multipartFile)) {

            List<Geo> geodata = new ArrayList<Geo>();

            try {
                String rows[] = CSVParser.getCSVRows(multipartFile);

                for (String row : rows) {

                    String fields[] = CSVParser.getRowData(row, FileUploadConstants.NUMBER_OF_FIELDS_IN_GEO);
                    if (fields == null) {
                        return false;
                    }
                    String distNum = fields[FileUploadConstants.INDEX_OF_DISTRICT_NUM];
                    String state = fields[FileUploadConstants.INDEX_OF_STATE];
                    String congress = fields[FileUploadConstants.INDEX_OF_CONGRESS];
                    String geojson = fields[FileUploadConstants.INDEX_OF_GEOJSON];

                    Geo geo = new Geo(state, Integer.parseInt(distNum), geojson, Integer.parseInt(congress));
                    geodata.add(geo);

                }

                 return doInsertGeo(geodata , conn);
            } catch (IOException ex) {
                Logger.getLogger(FileUploadService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    public boolean uploadDemoData(MultipartFile multipartFile, Connection conn) {

        if (isCSV(multipartFile)) {

            List<Demo> demodata = new ArrayList<Demo>();

            try {
                String rows[] = CSVParser.getCSVRows(multipartFile);

                for (String row : rows) {

                    String fields[] = CSVParser.getRowData(row, FileUploadConstants.NUMBER_OF_FIELDS_IN_DEMO);
                    if (fields == null) {
                        return false;
                    }

                    String dist_and_congress = fields[FileUploadConstants.INDEX_OF_DIST_NUM_AND_CONGRESS];
                    String state = fields[FileUploadConstants.INDEX_OF_STATE];
                    int totalPopulation = Integer.parseInt(fields[FileUploadConstants.INDEX_OF_TOTAL_POPULATION]);
                    int white = Integer.parseInt(fields[FileUploadConstants.INDEX_OF_WHITE]);
                    int black = Integer.parseInt(fields[FileUploadConstants.INDEX_OF_BLACK_AA]);
                    int nativeAmerican = Integer.parseInt(fields[FileUploadConstants.INDEX_OF_NATIVE]);
                    int asian = Integer.parseInt(fields[FileUploadConstants.INDEX_OF_ASIAN]);
                    int pacificIslander = Integer.parseInt(fields[FileUploadConstants.INDEX_OF_PACIFIC_ISLANDER]);
                    int other = Integer.parseInt(fields[FileUploadConstants.INDEX_OF_OTHER_RACE]);
                    int mixed = Integer.parseInt(fields[FileUploadConstants.INDEX_OF_MIXED_RACE]);

                    // dist_and_congress: e.g. "Congressional District 1 (115th Congress)"
                    int distNum = Integer.parseInt(between(dist_and_congress, ("Congressional District "), " ("));
                    int congress = Integer.parseInt(between(dist_and_congress, (" ("), "th"));

                    //TODO: race year and congress?
                    Demo demo = new Demo()
                            .setState(state)
                            .setCongress(congress)
                            .setDistrictNum(distNum)
                            .setTotal(totalPopulation)
                            .setWhite(white)
                            .setBlack(black)
                            .setIndian(nativeAmerican)
                            .setAsian(asian)
                            .setIslander(pacificIslander)
                            .setOther(other)
                            .setMixed(mixed);

                    demodata.add(demo);

                }

                return doInsertDemo(demodata, conn);

            } catch (IOException ex) {
                Logger.getLogger(FileUploadService.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;

    }

    public boolean uploadElectionData(MultipartFile multipartFile, Connection conn) {

        if (isCSV(multipartFile)) {

            List<Election> electionData = new ArrayList<Election>();

            try {
                String rows[] = CSVParser.getCSVRows(multipartFile);

                for (String row : rows) {

                    String fields[] = CSVParser.getRowData(row, FileUploadConstants.NUMBER_OF_FIELDS_IN_ELECTION);
                    if (fields == null) {
                        return false;
                    }
                    String distNum = fields[FileUploadConstants.INDEX_OF_DISTRICT_NUM];
                    String state = fields[FileUploadConstants.INDEX_OF_STATE];
                    String congress = fields[FileUploadConstants.INDEX_OF_CONGRESS];
                    String repVotes = fields[FileUploadConstants.INDEX_OF_REP_VOTE];
                    String repStatus = fields[FileUploadConstants.INDEX_OF_REP_STATUS];
                    String demVotes = fields[FileUploadConstants.INDEX_OF_DEM_VOTE];
                    String demStatus = fields[FileUploadConstants.INDEX_OF_DEM_STATUS];
                    String winner = fields[FileUploadConstants.INDEX_OF_WINNDER];

                    Election election = new Election(Integer.parseInt(distNum), 
                            state, 
                            Integer.parseInt(congress), 
                            Integer.parseInt(repVotes), 
                            repStatus, 
                            Integer.parseInt(demVotes),
                            demStatus, 
                            winner);

                    electionData.add(election);

                }

                return doInsertElection(electionData, conn);
            } catch (IOException ex) {
                Logger.getLogger(FileUploadService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    private boolean doInsertGeo(List<Geo> list, Connection conn) {
        String sql = "{call INSERT_GEO(?,?,?,?)}";
        try {

            CallableStatement stmt = conn.prepareCall(sql);
            conn.setAutoCommit(false);
            int i = 0;
            for (Geo geo : list) {
                //Set IN parameter
                stmt.setString(1, geo.getStateName());
                stmt.setInt(2, geo.getCongress());
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean doInsertDemo(List<Demo> list, Connection conn) {

        String sql = "{call INSERT_DEMO(?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            int i = 0;
            for (Demo data : list) {
                //Set IN parameter
                stmt.setString(1, data.getState());
                stmt.setInt(2, data.getCongress());
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

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean doInsertElection(List<Election> list, Connection conn) {
        
        // state name, congress, district number, party name, votes, election status, iswinner 
        String sql = "{call INSERT_ELECTION(?,?,?,?,?,?,?)}";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            int i = 0;
            for (Election data : list) {
                // insert dem
                stmt.setString(1, data.getState());
                stmt.setInt(2, data.getCongress());
                stmt.setInt(3, data.getDistrictNum());
                stmt.setString(4, DEM_PARTY);
                stmt.setInt(5, data.getDemVotes());
                stmt.setString(6, data.getDemStatus());
                stmt.setBoolean(7, isWinner(DEM_PARTY,data.getWinner()));
                
                //TODO: insert rep
                

                stmt.addBatch();
                i++;
                if (i % 1000 == 0 || i == list.size()) {
                    System.out.println(i + " records being inserted");
                    int[] count = stmt.executeBatch(); // Execute every 1000 items.  
                    conn.commit();
                    System.out.println(count.length + " records inserted");
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private boolean isWinner(String party, String winner) {
        return winner.equals("R") && party.equals(REP_PARTY);
    }

    //<editor-fold defaultstate="collapsed" desc=" helper methods ">
    
    public boolean isCSV(MultipartFile multipartFile) {
        return CSV_FILE.equals(multipartFile.getOriginalFilename().split("\\.")[1]);
    }

    public File multipartFileToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
    
    public String between(String value, String a, String b) {
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
