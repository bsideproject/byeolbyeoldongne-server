package com.chdlsp.datapractice.service;

import com.chdlsp.datapractice.domain.entity.UserEntity;
import com.chdlsp.datapractice.domain.interfaces.request.CreateUserInfoRequestVO;
import com.chdlsp.datapractice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionDemoServiceTests {

    @InjectMocks
    private TransactionDemoService transactionDemoService;

    @Mock
    private UserRepository userRepository;
    private UserEntity userEntity;
    private CreateUserInfoRequestVO createUserInfoRequestVO;

    @BeforeEach
    public void setUp() {
        userEntity = UserEntity.builder()
                .email("tester@example.com")
                .password("1234qwer").build();

        createUserInfoRequestVO = CreateUserInfoRequestVO.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    @Test
    void saveSuccessWithTransactionalDefault() {

        // given
        given(userRepository.existsByEmail(any())).willReturn(false);
        given(userRepository.save(any())).willReturn(userEntity);

        // when
        final UserEntity userEntity = transactionDemoService.saveNewUserInfoWithDefault(createUserInfoRequestVO);

        // then
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getEmail()).isEqualTo(createUserInfoRequestVO.getEmail());
        assertThat(userEntity.getPassword()).isEqualTo(createUserInfoRequestVO.getPassword());

    }

    // TODO: 이건 어떻게 해야지 테스트가 가능한거지...? save 되면 안되는데 의도대로 테스트가 안됨
    // H2 를 이용해서 테스트가 불가능하여 다른 방법으로 찾을 필요가 있음.
    @Test
    void saveFailureWithTransactionalReadonly() {

        //given
        // given(userRepository.existsByEmail(any())).willReturn(false);
        given(userRepository.save(any())).willReturn(userEntity);

        // when
        final UserEntity userEntity = transactionDemoService.saveNewUserInfoWithReadOnlyTrue(createUserInfoRequestVO);

        //then
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getEmail()).isEqualTo(createUserInfoRequestVO.getEmail());
        assertThat(userEntity.getPassword()).isEqualTo(createUserInfoRequestVO.getPassword());

    }
}