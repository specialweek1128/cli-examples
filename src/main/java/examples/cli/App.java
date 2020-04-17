package examples.cli;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@SpringBootApplication
@Configuration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public DataSource dataSource() {
        if (DataBaseInfo.instance == null) {
            throw new IllegalStateException("DB接続情報の初期化が完了していません。");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("jdbc:postgresql://");
        builder.append(DataBaseInfo.instance.host).append(":").append(DataBaseInfo.instance.port);
        builder.append("/").append(DataBaseInfo.instance.dbName);

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUrl(builder.toString());
        dataSource.setUsername(DataBaseInfo.instance.userId);
        dataSource.setPassword(DataBaseInfo.instance.password);
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("autoCommit", "false");
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

}
