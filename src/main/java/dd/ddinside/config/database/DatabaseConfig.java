package dd.ddinside.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig
{
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    @Profile("driverManager")
    public DataSource drivermanagerDataSource()
    {
        return new DriverManagerDataSource(url, username, password);
    }

    @Bean
    @Profile("hikari")
    public DataSource hikiariDataSource()
    {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }

    @Bean
    @Profile("driverManager")
    public JdbcTransactionManager jdbcTransactionManager(DataSource dataSource)
    {
        return new JdbcTransactionManager(dataSource);
    }
}
