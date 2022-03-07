package hk.cityu.cs.AiRegistry.FIT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public abstract class BaseTest {
    
    

    @BeforeEach
    public void initData() throws Exception{
        String dataSqlFile = "src/test/resources/test_data.sql";
        String jdbcURL = "jdbc:mariadb://127.0.0.1/airegistry_fit";
        String dbUsername = "airegistry";
        String dbPassword = "password";


        var conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);

        var scriptRunner = new ScriptRunner(conn);

        var reader = new BufferedReader(new FileReader(dataSqlFile));

        scriptRunner.runScript(reader);
        
    }


}
