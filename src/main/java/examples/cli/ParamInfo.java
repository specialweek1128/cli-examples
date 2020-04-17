package examples.cli;

import org.apache.commons.cli.CommandLine;

public class ParamInfo {

    public String key;

    public static ParamInfo create(CommandLine cl) {
        ParamInfo info = new ParamInfo();

        if (!cl.hasOption("k")) {
            System.err.println("データ検索時のキーが指定されていません。-kオプションを指定してください。");
            return null;
        }
        info.key = cl.getOptionValue("k");

        return info;
    }

}
