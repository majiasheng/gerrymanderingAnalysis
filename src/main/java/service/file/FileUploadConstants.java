package service.file;

/**
 *
 * @author majiasheng
 */
public class FileUploadConstants { 
    public static final String CSV_FILE = "csv";
    // geo data: 
    // district_number, state, congress, geojson
    public static final int NUMBER_OF_FIELDS_IN_GEO = 4;
    
    public static final int INDEX_OF_DISTRICT_NUM = 0;
    public static final int INDEX_OF_STATE = 1;
    public static final int INDEX_OF_CONGRESS = 2;
    public static final int INDEX_OF_GEOJSON = 3;
    
    // demographic data
    // geography, state, total, white, black, native, asian, islander, other, mixed
    public static final int NUMBER_OF_FIELDS_IN_DEMO = 10;
    
    public static final int INDEX_OF_DIST_NUM_AND_CONGRESS = 0;
    public static final int INDEX_OF_TOTAL_POPULATION = 2;
    public static final int INDEX_OF_WHITE = 3;
    public static final int INDEX_OF_BLACK_AA = 4; // BLACK OR AFRICAN AMERICAN
    public static final int INDEX_OF_NATIVE = 5; // American Indian and Alaska Native
    public static final int INDEX_OF_ASIAN = 6;
    public static final int INDEX_OF_PACIFIC_ISLANDER = 7; // Native Hawaiian and Other Pacific Islander
    public static final int INDEX_OF_OTHER_RACE = 8;
    public static final int INDEX_OF_MIXED_RACE = 9;

    // election data: 
    // district_number, state, congress, RepVotes, RepStatus, DemVotes, DemStatus, Winner
    public static final int NUMBER_OF_FIELDS_IN_ELECTION = 8;
    public static final int INDEX_OF_REP_VOTE = 3;
    public static final int INDEX_OF_REP_STATUS = 4;
    public static final int INDEX_OF_DEM_VOTE = 5;
    public static final int INDEX_OF_DEM_STATUS = 6;
    public static final int INDEX_OF_WINNDER = 7;
    
}