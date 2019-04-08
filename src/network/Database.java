package network;

public class Database {
    public static final String[] USER_NAME = {
        "GS1", "GS2", "GS3", "GS4", "GS7",
        "GS8", "GS9", "GS10", "GS12"
    };

    public static final String[] DB_FILE = {
        "LifeMap_GS1.db", "LifeMap_GS2.db", "LifeMap_GS3.db", "LifeMap_GS4.db", "LifeMap_GS7.db",
        "LifeMap_GS8.db", "LifeMap_GS9.db", "LifeMap_GS10.db", "LifeMap_GS12.db"
    };

    String[][] USER_SKIP_DAY = {
        // GS1
        {"20110308","20110309","20110312","20110313","20110314","20110322","20110325","20110329","20110330","20110331",
                "20110402","20110403","20110407","20110417","20110502","20110525","20110607","20110618","20110619","20110621",
                "20110630","20110701","20110711","20110719"},
        // GS2
        {"20110502","20110504","20110505","20110723","20110724","20110725","20110726","20110817","20111003","20111005",
                "20111015","20111016","20111027","20111115"},
        // GS3
        {"20101117","20101211","20101212","20101224","20110112","20110113","20110124","20110125","20110126","20110304",
                "20110305","20110306","20110307","20110615","20110620","20110629","20110630","20110701","20110702","20110703",
                "20110704","20110814"},
        // GS4
        {"20110507","20110619","20110927","20111009","20111012","20111013","20111014","20111015","20111016","20111017",
                "20111115"},
        // GS7
        {"20110310","20110311","20110318","20110319","20110401","20110402","20110620","20110719"},
        // GS8
        {"20110310","20110407","20110408","20110712","20110713","20110714","20110716","20110717","20110818","20110819",
                "20110824","20110825","20110826","20110910","20110911","20110915","20111114"},
        // GS9
        {"20110326","20110329","20110620","20110621","20110705","20110706","20110707","20110708","20110709","20110710",
                "20110711","20110712","20110714","20110827","20110828","20110829","20110830","20110831","20110901","20110902",
                "20110911","20110912","20111004"},
        // GS12
        {"20101114","20110125","20110126","20110305","20110306","20110324","20110325","20110411","20110412","20110413",
                "20110414","20110415","20110416","20110417","20110418","20110419","20110420","20110421","20110422","20110613",
                "20110614","20110616","20110617","20110627","20110628","20110629","20110630","20110701","20110702","20110703",
                "20111010","20111011","20111012","20111019","20111020","20111021","20111022","20111023","20111031","20111101",
                "20111102","20111103","20111104","20111105","20111106","20111111"},
    };

    String[] HOLIDAY = {
        "20101225", "20110101", "20110202", "20110203", "20110204", "20110301", "20110505", "20110510", "20110606", "20110815",
        "20110912", "20110913", "20111003"
    };

    public static final int DATABASE_VERSION = 30;

    // location table
    public static final String LOCATION_TABLE = "locationTable";
    public static final String LOCATION_KEY_ID = "_node_id";
    public static final int LOCATION_COLUMN_ID = 1;
    public static final String LOCATION_KEY_LATITUDE = "_latitude";
    public static final int LOCATION_COLUMN_LATITUDE = 2;
    public static final String LOCATION_KEY_LONGITUDE = "_longitude";
    public static final int LOCATION_COLUMN_LONGITUDE = 3;
    public static final String LOCATION_KEY_LATITUDE_GPS = "_latitude_gps";
    public static final int LOCATION_COLUMN_LATITUDE_GPS = 4;
    public static final String LOCATION_KEY_LONGITUDE_GPS = "_longitude_gps";
    public static final int LOCATION_COLUMN_LONGITUDE_GPS = 5;
    public static final String LOCATION_KEY_LATITUDE_WIFI = "_latitude_wifi";
    public static final int LOCATION_COLUMN_LATITUDE_WIFI = 6;
    public static final String LOCATION_KEY_LONGITUDE_WIFI = "_longitude_wifi";
    public static final int LOCATION_COLUMN_LONGITUDE_WIFI = 7;
    public static final String LOCATION_KEY_ALTITUDE = "_altitude";
    public static final int LOCATION_COLUMN_ALTITUDE = 8;
    public static final String LOCATION_KEY_ACCURACY = "_accuracy";
    public static final int LOCATION_COLUMN_ACCURACY = 9;
    public static final String LOCATION_KEY_ACCURACY_GPS = "_accuracy_gps";
    public static final int LOCATION_COLUMN_ACCURACY_GPS = 10;
    public static final String LOCATION_KEY_ACCURACY_WIFI = "_accuracy_wifi";
    public static final int LOCATION_COLUMN_ACCURACY_WIFI = 11;
    public static final String LOCATION_KEY_ACTIVITY = "_activity";
    public static final int LOCATION_COLUMN_ACTIVITY = 12;
    public static final String LOCATION_KEY_PLACE_NAME = "_place_name";
    public static final int LOCATION_COLUMN_PLACE_NAME = 13;
    public static final String LOCATION_KEY_PLACE_COMMENT = "_place_comment";
    public static final int LOCATION_COLUMN_PLACE_COMMENT = 14;
    public static final String LOCATION_KEY_TIME = "_time_location";
    public static final int LOCATION_COLUMN_TIME = 15;

    // ap table
    public static final String AP_TABLE = "apTable";
    public static final String AP_KEY_ID = "_ap_id";
    public static final int AP_COLUMN_ID = 1;
    public static final String AP_KEY_NODE_ID = LOCATION_KEY_ID;
    public static final int AP_COLUMN_NODE_ID = 2;
    public static final String AP_KEY_BSSID = "_bssid";
    public static final int AP_COLUMN_BSSID = 3;
    public static final String AP_KEY_SSID = "_ssid";
    public static final int AP_COLUMN_SSID = 4;
    public static final String AP_KEY_OPEN = "_open";
    public static final int AP_COLUMN_OPEN = 5;
    public static final String AP_KEY_SIGNAL = "_signal";
    public static final int AP_COLUMN_SIGNAL = 6;
    public static final String AP_KEY_SIGNAL_DEVIATION = "_signal_deviation";
    public static final int AP_COLUMN_SIGNAL_DEVIATION = 7;
    public static final String AP_KEY_SAMPLE_COUNT = "_sample_count";
    public static final int AP_COLUMN_SAMPLE_COUNT = 8;
    public static final String AP_KEY_TIME = "_time_ap";
    public static final int AP_COLUMN_TIME = 9;

    // cell table
    public static final String CELL_TABLE = "cellTable";
    public static final String CELL_KEY_ID = "_cell_id";
    public static final int CELL_COLUMN_ID = 1;
    public static final String CELL_KEY_NODE_ID = LOCATION_KEY_ID;
    public static final int CELL_COLUMN_NODE_ID = 2;
    public static final String CELL_KEY_CELL_TYPE = "_cell_type";
    public static final int CELL_COLUMN_CELL_TYPE = 3;
    public static final String CELL_KEY_CID = "_cid";
    public static final int CELL_COLUMN_CID = 4;
    public static final String CELL_KEY_LAC = "_lac";
    public static final int CELL_COLUMN_LAC = 5;
    public static final String CELL_KEY_CONNECT_TIME = "_connect_time";
    public static final int CELL_COLUMN_CONNECT_TIME = 6;
    public static final String CELL_KEY_TIME = "_time_cell";
    public static final int CELL_COLUMN_TIME = 7;

    // node table
    public static final String CELL_NODE_TABLE = "cellNodeTable";
    public static final String EDGE_TABLE = "edgeTable";
    public static final String CELL_NODE_KEY_ID = "_cell_node_id";
    public static final int CELL_NODE_COLUMN_ID = 1;
    public static final String CELL_NODE_KEY_CELL_TYPE = "_cell_node_type";
    public static final int CELL_NODE_COLUMN_CELL_TYPE = 2;
    public static final String CELL_NODE_KEY_CID = "_node_cid";
    public static final int CELL_NODE_COLUMN_CID = 3;
    public static final String CELL_NODE_KEY_LAC = "_node_lac";
    public static final int CELL_NODE_COLUMN_LAC = 4;
    public static final String CELL_NODE_KEY_CONNECT_TIME = "_node_connect_time";
    public static final int CELL_NODE_COLUMN_CONNECT_TIME = 5;
    public static final String CELL_NODE_KEY_TIME = "_time_cell_node";
    public static final int CELL_NODE_COLUMN_TIME = 6;

    // edge table
    public static final String CELL_EDGE_TABLE = "cellEdgeTable";
    public static final String CELL_EDGE_KEY_ID = "_cell_edge_id";
    public static final int CELL_EDGE_COLUMN_ID = 1;
    public static final String CELL_EDGE_KEY_CELL_NODE_ID = CELL_NODE_KEY_ID;
    public static final int CELL_EDGE_COLUMN_CELL_NODE_ID = 2;
    public static final String CELL_EDGE_KEY_CELL_DESTINATION_ID = "_cell_edge_destination_cell_id";
    public static final int CELL_EDGE_COLUMN_CELL_DESTINATION_ID = 3;
    public static final String CELL_EDGE_KEY_TIME = "_time_cell_edge";
    public static final int CELL_EDGE_COLUMN_TIME = 4;

    // edge table
    public static final String EDGE_KEY_ID = "_edge_id";
    public static final int EDGE_COLUMN_ID = 1;
    public static final String EDGE_KEY_NODE_ID = LOCATION_KEY_ID;
    public static final int EDGE_COLUMN_NODE_ID = 2;
    public static final String EDGE_KEY_DES_NODE_ID = "_destination_node_id";
    public static final int EDGE_COLUMN_DES_NODE_ID = 3;
    public static final String EDGE_KEY_TIME = "_time_edge";
    public static final int EDGE_COLUMN_TIME = 4;

    // category table
    public static final String CATEGORY_TABLE = "categoryTable";
    public static final String CATEGORY_KEY_ID = "_category_id";
    public static final int CATEGORY_COLUMN_ID = 1;
    public static final String CATEGORY_KEY_PLACE_NAME = "_category_place_name";
    public static final int CATEGORY_COLUMN_PLACE_NAME = 2;
    public static final String CATEGORY_KEY_ACTIVE = "_category_active";
    public static final int CATEGORY_COLUMN_ACTIVE = 3;
    public static final String CATEGORY_KEY_EDITABLE = "_category_editable";
    public static final int CATEGORY_COLUMN_EDITABLE = 4;
    public static final String CATEGORY_KEY_RES_ID = "_category_res_id";
    public static final int CATEGORY_COLUMN_RES_ID = 5;
    public static final String CATEGORY_KEY_TIME = "_time_category";
    public static final int CATEGORY_COLUMN_TIME = 6;

    // category set table
    public static final String CATEGORY_SET_TABLE = "categorySetTable";
    public static final String CATEGORY_SET_KEY_ID = "_category_set_id";
    public static final int CATEGORY_SET_COLUMN_ID = 1;
    public static final String CATEGORY_SET_KEY_CATEGORY_ID = CATEGORY_KEY_ID;
    public static final int CATEGORY_SET_COLUMN_CATEGORY_ID = 2;
    public static final String CATEGORY_SET_KEY_LOCATION_ID = LOCATION_KEY_ID;
    public static final int CATEGORY_SET_COLUMN_LOCATION_ID = 3;
    public static final String CATEGORY_SET_KEY_HUMAN_ID = "_category_set_human_id";
    public static final int CATEGORY_SET_COLUMN_HUMAN_ID = 4;
    public static final String CATEGORY_SET_KEY_TIME = "_time_category_set";
    public static final int CATEGORY_SET_COLUMN_TIME = 5;

    // configure table
    public static final String CONFIGURE_TABLE = "configureTable";
    public static final String CONFIGURE_KEY_ID = "_configure_id";
    public static final int CONFIGURE_COLUMN_ID = 1;
    public static final String CONFIGURE_KEY_TIME_INTERVAL = "_configure_time_interval";
    public static final int CONFIGURE_COLUMN_TIME_INTERVAL = 2;
    public static final String CONFIGURE_KEY_UNCATEGORIZED_VIEW = "_configure_uncategorized_view";
    public static final int CONFIGURE_COLUMN_UNCATEGORIZED_VIEW = 3;
    public static final String CONFIGURE_KEY_SENSOR_USAGE_LOG = "_configure_sensor_usage_log";
    public static final int CONFIGURE_COLUMN_SENSOR_USAGE_LOG = 4;
    public static final String CONFIGURE_KEY_BATTERY_LOG = "_configure_battery_log";
    public static final int CONFIGURE_COLUMN_BATTERY_LOG = 5;
    public static final String CONFIGURE_KEY_CELL_LOG = "_configure_cell_log";
    public static final int CONFIGURE_COLUMN_CELL_LOG = 6;
    public static final String CONFIGURE_KEY_LAST_NODE_ID = "_configure_last_node_id";
    public static final int CONFIGURE_COLUMN_LAST_NODE_ID = 7;
    public static final String CONFIGURE_KEY_LAST_ACTIVITY = "_configure_last_activity";
    public static final int CONFIGURE_COLUMN_LAST_ACTIVITY = 8;
    public static final String CONFIGURE_KEY_NICKNAME = "_configure_nickname";
    public static final int CONFIGURE_COLUMN_NICKNAME = 9;
    public static final String CONFIGURE_KEY_TWITTER_TOKEN = "_configure_twitter_token";
    public static final int CONFIGURE_COLUMN_TWITTER_TOKEN = 10;
    public static final String CONFIGURE_KEY_TWITTER_TOKEN_SECRET = "_configure_twitter_token_secret";
    public static final int CONFIGURE_COLUMN_TWITTER_TOKEN_SECRET = 11;
    public static final String CONFIGURE_KEY_TIME = "_time_configure";
    public static final int CONFIGURE_COLUMN_TIME = 12;

    // stay table
    public static final String STAY_TABLE = "stayTable";
    public static final String STAY_KEY_ID = "_stay_id";
    public static final int STAY_COLUMN_ID = 1;
    public static final String STAY_KEY_NODE_ID = LOCATION_KEY_ID;
    public static final int STAY_COLUMN_NODE_ID = 2;
    public static final String STAY_KEY_STAY_TIME = "_stay_time";
    public static final int STAY_COLUMN_STAY_TIME = 4;
    public static final String STAY_KEY_STAY_START_TIME = "_stay_start_time";
    public static final int STAY_COLUMN_STAY_START_TIME = 3;
    public static final String STAY_KEY_TIME = "_time_stay";
    public static final int STAY_COLUMN_TIME = 5;

    // stay comment table
    public static final String STAY_COMMENT_TABLE = "stayCommentTable";
    public static final String STAY_COMMENT_KEY_ID = "_stay_comment_id";
    public static final int STAY_COMMENT_COLUMN_ID = 1;
    public static final String STAY_COMMENT_KEY_STAY_ID = STAY_KEY_ID;
    public static final int STAY_COMMENT_COLUMN_STAY_ID = 2;
    public static final String STAY_COMMENT_KEY_TYPE = "_stay_comment_type";
    public static final int STAY_COMMENT_COLUMN_TYPE = 3;
    public static final String STAY_COMMENT_KEY_CONTENT = "_stay_comment_content";
    public static final int STAY_COMMENT_COLUMN_CONTENT = 4;
    public static final String STAY_COMMENT_KEY_IMAGE = "_stay_comment_image";
    public static final int STAY_COMMENT_COLUMN_IMAGE = 5;
    public static final String STAY_COMMENT_KEY_TIME = "_time_stay_comment";
    public static final int STAY_COMMENT_COLUMN_TIME = 6;

    // sensor usage table
    public static final String SENSOR_USAGE_TABLE = "sensorUsageTable";
    public static final String SENSOR_USAGE_KEY_ID = "_sensor_usage_id";
    public static final int SENSOR_USAGE_COLUMN_ID = 1;
    public static final String SENSOR_USAGE_KEY_TYPE = "_sensor_type";
    public static final int SENSOR_USAGE_COLUMN_TYPE = 2;
    public static final String SENSOR_USAGE_KEY_CYCLE = "_sensor_cycle";
    public static final int SENSOR_USAGE_COLUMN_CYCLE = 3;
    public static final String SENSOR_USAGE_KEY_USAGE_TIME = "_sensor_usage_time";
    public static final int SENSOR_USAGE_COLUMN_USAGE_TIME = 4;
    public static final String SENSOR_USAGE_KEY_START_TIME = "_sensor_start_time";
    public static final int SENSOR_USAGE_COLUMN_START_TIME = 5;
    public static final String SENSOR_USAGE_KEY_TIME = "_time_sensor_usage";
    public static final int SENSOR_USAGE_COLUMN_TIME = 6;

    // no radio table
    public static final String NO_RADIO_TABLE = "noRadioTable";
    public static final String NO_RADIO_KEY_ID = "_no_radio_id";
    public static final int NO_RADIO_COLUMN_ID = 1;
    public static final String NO_RADIO_KEY_DURATION = "_no_radio_duration";
    public static final int NO_RADIO_COLUMN_DURATION = 2;
    public static final String NO_RADIO_KEY_START_TIME = "_no_radio_start_time";
    public static final int NO_RADIO_COLUMN_START_TIME = 3;
    public static final String NO_RADIO_KEY_TIME = "_time_no_radio";
    public static final int NO_RADIO_COLUMN_TIME = 4;

    // battery table
    public static final String BATTERY_TABLE = "batteryTable";
    public static final String BATTERY_KEY_ID = "_battery_id";
    public static final int BATTERY_COLUMN_ID = 1;
    public static final String BATTERY_KEY_LEVEL = "_battery_level";
    public static final int BATTERY_COLUMN_LEVEL = 2;
    public static final String BATTERY_KEY_STATUS = "_battery_status";
    public static final int BATTERY_COLUMN_STATUS = 3;
    public static final String BATTERY_KEY_VOLTAGE = "_battery_voltage";
    public static final int BATTERY_COLUMN_VOLTAGE = 4;
    public static final String BATTERY_KEY_TIME = "_time_battery";
    public static final int BATTERY_COLUMN_TIME = 5;

    // Deprecated
    public static final String EDGE_EXTRA_TABLE = "edgeExtraTable";
    public static final String USER_TABLE = "userTable";

    private static final String LOCATION_CREATE = "create table " + LOCATION_TABLE + " ("
            + LOCATION_KEY_ID + " integer primary key autoincrement, "	// 2
            + LOCATION_KEY_LATITUDE + " integer not null, " 			// 4
            + LOCATION_KEY_LONGITUDE + " integer not null, " 			// 4
            + LOCATION_KEY_LATITUDE_GPS + " integer, " 					// 4
            + LOCATION_KEY_LONGITUDE_GPS + " integer, " 				// 4
            + LOCATION_KEY_LATITUDE_WIFI + " integer, " 				// 4
            + LOCATION_KEY_LONGITUDE_WIFI + " integer, " 				// 4
            + LOCATION_KEY_ALTITUDE + " integer not null, " 			// 2
            + LOCATION_KEY_ACCURACY + " integer not null, "				// 2
            + LOCATION_KEY_ACCURACY_GPS + " integer, "					// 2
            + LOCATION_KEY_ACCURACY_WIFI + " integer, "					// 2
            + LOCATION_KEY_ACTIVITY + " integer not null, " 			// 1
            + LOCATION_KEY_PLACE_NAME + " text, "						// 0
            + LOCATION_KEY_PLACE_COMMENT + " text, "					// 0
            + LOCATION_KEY_TIME + " text not null); ";					// 17
    private static final int LOCATION_TABLE_ROW_BYTE = 52;

    private static final String AP_CREATE = "create table " + AP_TABLE + " ("
            + AP_KEY_ID + " integer primary key autoincrement, "	// 2
            + AP_KEY_NODE_ID + " integer not null, " 				// 2
            + AP_KEY_BSSID + " text not null, " 					// 17
            + AP_KEY_SSID + " text, " 								// 4
            + AP_KEY_OPEN + " text, " 								// 4
            + AP_KEY_SIGNAL + " real not null, "					// 8
            + AP_KEY_SIGNAL_DEVIATION + " real not null, "			// 8
            + AP_KEY_SAMPLE_COUNT + " integer not null, " 			// 4
            + AP_KEY_TIME + " text not null);";						// 17
    private static final int AP_TABLE_ROW_BYTE = 66;

    private static final String CELL_CREATE = "create table " + CELL_TABLE + " ("
            + CELL_KEY_ID + " integer primary key autoincrement, "	// 2
            + CELL_KEY_NODE_ID + " integer not null, "				// 2
            + CELL_KEY_CELL_TYPE + " integer not null, "			// 1
            + CELL_KEY_CID + " integer, " 							// 4
            + CELL_KEY_LAC + " integer, "							// 2
            + CELL_KEY_CONNECT_TIME + " integer not null, "			// 4
            + CELL_KEY_TIME	+ " text not null);";					// 17
    private static final int CELL_TABLE_ROW_BYTE = 32;

    private static final String CELL_NODE_CREATE = "create table " + CELL_NODE_TABLE + " ("
            + CELL_NODE_KEY_ID + " integer primary key autoincrement, "	// 2
            + CELL_NODE_KEY_CELL_TYPE + " integer not null, "			// 1
            + CELL_NODE_KEY_CID + " integer, " 							// 4
            + CELL_NODE_KEY_LAC + " integer, "							// 2
            + CELL_NODE_KEY_CONNECT_TIME + " integer not null, "		// 4
            + CELL_NODE_KEY_TIME	+ " text not null);";				// 17
    private static final int CELL_NODE_TABLE_ROW_BYTE = 30;

    private static final String CELL_EDGE_CREATE = "create table " + CELL_EDGE_TABLE + " ("
            + CELL_EDGE_KEY_ID + " integer primary key autoincrement, "	// 2
            + CELL_EDGE_KEY_CELL_NODE_ID + " integer not null, "		// 2
            + CELL_EDGE_KEY_CELL_DESTINATION_ID + " integer not null, "	// 2
            + CELL_EDGE_KEY_TIME	+ " text not null);";				// 17
    private static final int CELL_EDGE_TABLE_ROW_BYTE = 23;

    private static final String EDGE_CREATE = "create table " + EDGE_TABLE + " ("
            + EDGE_KEY_ID + " integer primary key autoincrement, "	// 2
            + EDGE_KEY_NODE_ID + " integer not null, " 				// 2
            + EDGE_KEY_DES_NODE_ID + " integer not null, "			// 2
            + EDGE_KEY_TIME + " text not null); ";					// 17
    private static final int EDGE_TABLE_ROW_BYTE = 23;

    private static final String CATEGORY_CREATE = "create table " + CATEGORY_TABLE + " ("
            + CATEGORY_KEY_ID + " integer primary key autoincrement, "	// 2
            + CATEGORY_KEY_PLACE_NAME + " text, " 						// 8
            + CATEGORY_KEY_ACTIVE + " integer not null, "				// 1
            + CATEGORY_KEY_EDITABLE + " integer not null, "				// 1
            + CATEGORY_KEY_RES_ID + " integer not null, " 				// 4
            + CATEGORY_KEY_TIME + " text not null);";					// 17
    private static final int CATEGORY_TABLE_ROW_BYTE = 33;

    private static final String CATEGORY_SET_CREATE = "create table " + CATEGORY_SET_TABLE + " ("
            + CATEGORY_SET_KEY_ID + " integer primary key autoincrement, "	// 2
            + CATEGORY_SET_KEY_CATEGORY_ID + " integer not null, " 			// 2
            + CATEGORY_SET_KEY_LOCATION_ID + " integer, " 					// 2
            + CATEGORY_SET_KEY_HUMAN_ID + " text, " 						// 1
            + CATEGORY_SET_KEY_TIME + " text not null);";					// 17
    private static final int CATEGORY_SET_TABLE_ROW_BYTE = 24;

    private static final String STAY_CREATE = "create table " + STAY_TABLE
            + " (" + STAY_KEY_ID + " integer primary key autoincrement, "	// 2
            + STAY_KEY_NODE_ID + " integer not null, " 						// 2
            + STAY_KEY_STAY_TIME + " integer not null,"						// 4
            + STAY_KEY_STAY_START_TIME + " text not null,"					// 17
            + STAY_KEY_TIME + " text not null);";							// 17
    private static final int STAY_TABLE_ROW_BYTE = 42;

    private static final String STAY_COMMENT_CREATE = "create table " + STAY_COMMENT_TABLE
            + " (" + STAY_COMMENT_KEY_ID + " integer primary key autoincrement, "	// 2
            + STAY_COMMENT_KEY_STAY_ID + " integer not null, " 						// 2
            + STAY_COMMENT_KEY_TYPE + " integer not null,"							// 1
            + STAY_COMMENT_KEY_CONTENT + " text,"									// 0
            + STAY_COMMENT_KEY_IMAGE + " text,"										// 0
            + STAY_COMMENT_KEY_TIME + " text not null);";							// 17
    private static final int STAY_COMMENT_TABLE_ROW_BYTE = 23;

    private static final String CONFIGURE_CREATE = "create table " + CONFIGURE_TABLE + " ("
            + CONFIGURE_KEY_ID + " integer primary key autoincrement, " 	// 2
            + CONFIGURE_KEY_TIME_INTERVAL + " integer not null, "			// 1
            + CONFIGURE_KEY_UNCATEGORIZED_VIEW + " integer not null, "		// 1
            + CONFIGURE_KEY_SENSOR_USAGE_LOG + " integer not null, "		// 1
            + CONFIGURE_KEY_BATTERY_LOG + " integer not null, "				// 1
            + CONFIGURE_KEY_CELL_LOG + " integer not null, "				// 1
            + CONFIGURE_KEY_LAST_NODE_ID + " integer, "						// 2
            + CONFIGURE_KEY_LAST_ACTIVITY + " integer, " 					// 1
            + CONFIGURE_KEY_NICKNAME + " text, "							// 0
            + CONFIGURE_KEY_TWITTER_TOKEN + " text, "						// 20
            + CONFIGURE_KEY_TWITTER_TOKEN_SECRET + " text, "				// 20
            + CONFIGURE_KEY_TIME + " text not null);";						// 17
    private static final int CONFIGURE_TABLE_ROW_BYTE = 67;

    private static final String SENSOR_USAGE_CREATE = "create table " + SENSOR_USAGE_TABLE + " ("
            + SENSOR_USAGE_KEY_ID + " integer primary key autoincrement, "	// 2
            + SENSOR_USAGE_KEY_TYPE + " integer not null, " 				// 1
            + SENSOR_USAGE_KEY_CYCLE + " integer, "							// 1
            + SENSOR_USAGE_KEY_USAGE_TIME + " integer, "					// 4
            + SENSOR_USAGE_KEY_START_TIME + " text, "						// 17
            + SENSOR_USAGE_KEY_TIME + " text not null);";					// 17
    private static final int SENSOR_USAGE_TABLE_ROW_BYTE = 42;

    private static final String BATTERY_CREATE = "create table " + BATTERY_TABLE 	+ " ("
            + BATTERY_KEY_ID + " integer primary key autoincrement, "	// 2
            + BATTERY_KEY_LEVEL + " integer not null, " 				// 1
            + BATTERY_KEY_STATUS + " integer, "							// 1
            + BATTERY_KEY_VOLTAGE + " integer, "						// 2
            + BATTERY_KEY_TIME + " text not null);";					// 17
    private static final int BATTERY_TABLE_ROW_BYTE = 23;

    private static final String NO_RADIO_CREATE = "create table " + NO_RADIO_TABLE 	+ " ("
            + NO_RADIO_KEY_ID + " integer primary key autoincrement, "	// 2
            + NO_RADIO_KEY_DURATION + " integer not null, " 			// 4
            + NO_RADIO_KEY_START_TIME + " text not null, "				// 17
            + NO_RADIO_KEY_TIME + " text not null);";					// 17
    private static final int NO_RADIO_TABLE_ROW_BYTE = 40;



























}
