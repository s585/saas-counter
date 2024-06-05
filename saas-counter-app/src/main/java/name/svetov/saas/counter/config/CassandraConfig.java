package name.svetov.saas.counter.config;

import com.datastax.oss.driver.api.core.CqlSession;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;


@Factory
public class CassandraConfig {
    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder().build();
    }
}
