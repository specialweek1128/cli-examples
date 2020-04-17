package examples.cli;

import org.apache.commons.cli.CommandLine;

public class DataBaseInfo {

    public static DataBaseInfo instance = null;

    public static void create(CommandLine cl) {
        instance = null;
        DataBaseInfo info = new DataBaseInfo();
        info.host = cl.getOptionValue("h", "localhost");
        String portStr = cl.getOptionValue("p", "5432");
        try {
            info.port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            System.err.println("ポート番号に数値以外の値が指定されています。");
            return;
        }
        info.userId = cl.getOptionValue("U", "postgres");
        info.password = cl.getOptionValue("P", "postgres");
        info.dbName = cl.getOptionValue("d", "postgres");
        instance = info;
    }

    public String host;

    public int port;

    public String userId;

    public String password;

    public String dbName;

}
