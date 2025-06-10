package fgjcdmx.gob.sava.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "informix.datasource")
public class InformixJdbcConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Bean(name = "informixDataSource")
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName(this.driverClassName);
        return dataSource;
    }

    @Bean(name = "informixJdbcTemplate")
    public JdbcTemplate postgresJdbcTemplate(@Qualifier("informixDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // setters
    public void setUrl(String url) { this.url = url; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setDriverClassName(String driverClassName) { this.driverClassName = driverClassName; }
}
