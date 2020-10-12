package com.chdlsp.datapractice.repository;

import com.chdlsp.datapractice.domain.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.internal.util.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
// @SpringBootTest 를 사용한다면 모든 bean 을 읽어서 application.properties 에 등록된 DB를 사용한다.
class UserRepositoryTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void di() throws SQLException {
        // slicing test 를 위해 선언한 bean 이 잘 동작 하는가?
        // slicing test 에는 인메모리 DB를 쓰도록 설정이 되어있다.

        try(Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();

            logger.info(()-> {
                try {
                    return metaData.getURL() + " "
                            + metaData.getDriverName() + " "
                            + metaData.getUserName();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
        }
    }

    @Test
    public void save() {
        UserEntity userEntity = UserEntity.builder()
                .email("tester@example.com")
                .password("1234qwer")
                .build();

        UserEntity newUser = userRepository.save(userEntity);
        assertThat(newUser).isNotNull(); // value 확인

        Optional<UserEntity> existingUser = userRepository.findByEmail(newUser.getEmail());
        assertThat(existingUser).isNotEmpty();

        Optional<UserEntity> notExistingUser = userRepository.findByEmail("not-user@example.com");
        assertThat(notExistingUser).isEmpty();
    }


}