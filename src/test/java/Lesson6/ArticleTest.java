/**
 * Class ArticleTest
 *
 * @author Melnev Dmitriy
 * @version 2022-05-26
 **/
package Lesson6;

import org.junit.jupiter.api.*;

import static Lesson6.DataAuthorisation.*;
import static Lesson6.DataSearch.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArticleTest extends PrepareTest {
    MainSearch searchPage;

    @BeforeEach
    public void createPage() {
        searchPage = new MainSearch(getDriver());
    }

    @Test
    @DisplayName("Поиск продукта нажатием Enter")
    public void findProductBySubmit() throws InterruptedException {
        searchPage.toSearchBySubmit(TEST_SEARCH);
        assertTrue(searchPage.takeNumberValidItems() > 0, "Hasn't found some search result");
    }

    @Test
    @DisplayName("Поиск продукта нажатием ЛКМ")
    public void findProductByClick() throws InterruptedException {
        searchPage.toSearchByClick(TEST_SEARCH);
        assertTrue(searchPage.takeNumberValidItems() > 0, "Hasn't found some search result");
    }

    @Test
    @DisplayName("Поиск отсутствующего товара")
    public void findNotPresentProduct() throws InterruptedException {
        searchPage.toSearchByClick(TEST_SEARCH_FAIL);
        assertEquals(0, searchPage.takeNumberInValidItems(), "Has found some search result but don't must");
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    public void checkArticle() throws InterruptedException {

        if (!authorization(LOGIN, PASSWORD, USERNAME)) fail("Cannot log in");
        searchPage
                .toSearchBySubmit(TEST_SEARCH)
                .clickResult()
                .goToNext()
                .clickBuy();
        assertEquals(SUCCESSFUL_ORDER_RESULT, searchPage.getTextResult());
    }
}
