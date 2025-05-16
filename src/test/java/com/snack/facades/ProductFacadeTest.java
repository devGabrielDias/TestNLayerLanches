package com.snack.facades;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.facade.ProductFacade;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductFacadeTest {
    ProductFacade productFacade;
    Product xburger;
    Product batataFrita;

    @BeforeEach
    public void setUp() {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService();
        ProductApplication productApplication = new ProductApplication(productRepository, productService);
        productFacade = new ProductFacade(productApplication);
        batataFrita = new Product(1, "Batata-Frita", 10, "src\\test\\java\\com\\snack\\imagens\\batataFrita.jpg");
        xburger = new Product(1, "xburger", 20, "src\\test\\java\\com\\snack\\imagens\\xburger.jpg");
    }

    //    1. Retornar a lista completa de produtos ao chamar o metodo getAll
    @Test
    public void testarListarTodosOsProdutos() {
        productFacade.append(batataFrita);

        List<Product> products = productFacade.getAll();

        Assertions.assertFalse(products.isEmpty());
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Batata-Frita", products.get(0).getDescription());
    }

    //    2. Retornar o produto correto ao fornecer um ID válido no metodo getById
    @Test
    public void testarRetornarProdutoCorretoPorIDValido() {
        productFacade.append(batataFrita);

        Assertions.assertNotNull(productFacade.getById(1));
        Assertions.assertEquals("Batata-Frita", productFacade.getById(1).getDescription());
        Assertions.assertEquals(10, productFacade.getById(1).getPrice());
    }

    //    3. Retornar true para um ID existente e false para um ID inexistente no metodo exists.
    @Test
    public void testarConfirmarExistenciaDoProduto() {
        productFacade.append(batataFrita);

        Assertions.assertTrue(productFacade.exists(1));
        Assertions.assertFalse(productFacade.exists(5));
    }

    //    4. Adicionar um novo produto corretamente ao chamar o metodo append.
    @Test
    public void testarAdicionarNovoProdutoCorretamente() {
        productFacade.append(batataFrita);

        Product produtoExistente = productFacade.getById(1);

        Assertions.assertNotNull(produtoExistente);
        Assertions.assertEquals("Batata-Frita", produtoExistente.getDescription());
        Assertions.assertEquals(10, produtoExistente.getPrice());
    }

    //    5. Remover um produto existente ao fornecer um ID válido no metodo remove.
    @Test
    public void testarRemoverProdutoExistente() {
        productFacade.append(batataFrita);

        productFacade.remove(1);

        Assertions.assertFalse(productFacade.exists(1));
        Path path = Paths.get(batataFrita.getImage());
        Assertions.assertFalse(Files.exists(path));
    }
}
