package examples.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class MainRunner implements CommandLineRunner {

    private static String HELP_MESSAGE = "java -jar cli-examples.jar -k <key> [option]";

    @Autowired
    ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        Options options = new Options();
        options.addOption("?", "help", false, "ヘルプメッセージを表示します。");
        options.addOption("h", "host", true, "DBのホスト名を指定してください。この値デフォルト値は\"localhost\"です。");
        options.addOption("p", "port", true, "DBのポート番号を指定してください。この値デフォルト値は\"5432\"です。");
        options.addOption("U", "dbUserId", true, "DBのユーザIDを指定してください。この値デフォルト値は\"postgres\"です。");
        options.addOption("P", "dbPassword", true, "DBのパスワードを指定してください。この値デフォルト値は\"postgres\"です。");
        options.addOption("d", "dbName", true, "データベース名を指定してください。この値デフォルト値は\"postgres\"です。");
        options.addOption("k", "key", true, "データ検索時のキーを指定してください。この値は必須です。");

        // オプションの解析
        CommandLine cl = null;
        try {
            cl = new DefaultParser().parse(options, args);
        } catch (UnrecognizedOptionException e) {
            System.err.println("不明なオプションが指定されています。[" + e.getMessage() + "]");
        } catch (MissingArgumentException e) {
            System.err.println("オプション引数が入力されていません。[" + e.getMessage() + "]");
        }
        if (cl == null || cl.hasOption("?")) {
            new HelpFormatter().printHelp(HELP_MESSAGE, options);
            return;
        }

        // データベースの接続情報を取得する
        DataBaseInfo.create(cl);
        if (DataBaseInfo.instance == null) {
            new HelpFormatter().printHelp(HELP_MESSAGE, options);
            return;
        }

        // データ検索用のデータを取得する
        ParamInfo paramInfo = ParamInfo.create(cl);
        if (paramInfo == null) {
            new HelpFormatter().printHelp(HELP_MESSAGE, options);
            return;
        }

        // サービスを実行する
        MainService service = context.getBean(MainService.class);
        long count = service.execute(paramInfo);
        System.out.println("キー（" + paramInfo.key + "）の検索結果：" + count + "件");
    }

}
