package uz.market.bozor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uz.market.bozor.repository.impl.BaseRepositoryImpl;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BozorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BozorApplication.class, args);
    }

}
