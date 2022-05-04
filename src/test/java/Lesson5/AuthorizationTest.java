/**
 * Class AuthorizationTest
 *
 * @author Melnev Dmitriy
 * @version 2022-05-04
 **/
package Lesson5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationTest extends PrepareTest {

    @Test
    @DisplayName("Успешная авторизация")
    public void authorisation() throws InterruptedException {
        assertTrue(authorization(LOGIN, PASSWORD, USERNAME));
    }

    @ParameterizedTest
    @DisplayName("Не корректный логин")
    @ValueSource(strings = {
            " ",
            "/",
            "ewq",
            "123",
            "!@#$%^&*()_+",
            "<>?\":;''"
    })
    public void authorisationBadLogin(String string) throws InterruptedException {

        assertFalse(authorization(string, PASSWORD));
    }

    @ParameterizedTest
    @DisplayName("Не корректный пароль")
    @ValueSource(strings = {
            " ",
            "!@#$%^&*()?<>?+_)(*&^%$#@!\":;",
            "йцукен"
    })
    public void authorisationBadPass(String string) throws InterruptedException {

        assertFalse(authorization(LOGIN, string));
    }

}

