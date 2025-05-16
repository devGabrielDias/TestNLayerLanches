package com.snack.applications;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductApplicationTest {
    ProductApplication productApplication;
    Product xburger;
    Product batataFrita;


    @BeforeEach
    void setUp() {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService();
        batataFrita = new Product(1, "Batata-Frita", 10, "src\\test\\java\\com\\snack\\imagens\\batataFrita.jpg");
        xburger = new Product(1, "xburger", 20, "src\\test\\java\\com\\snack\\imagens\\xburger.jpg");

        productApplication = new ProductApplication(productRepository,productService);
    }

    //    1. Listar todos os produtos do repositório
    @Test
    public void testarListarProdutos() {
        productApplication.append(batataFrita);
        productApplication.append(xburger);

        List<Product> products = productApplication.getAll();

        Assertions.assertEquals(2, products.size());
    }

    //    2. Obter um produto por ID válido
    @Test
    public void testarObterProdutoIDValido() {
        productApplication.append(batataFrita);

        Product productPurchased = productApplication.getById(1);

        Assertions.assertEquals(1, productPurchased.getId());
        Assertions.assertEquals("Batata-Frita", productPurchased.getDescription());
        Assertions.assertEquals(10, productPurchased.getPrice());
    }

    //    3. Retornar nulo ou erro ao tentar obter produto por ID inválido
    @Test
    public void testarObterProdutoIDInvalido() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            productApplication.getById(5);
        });
    }

    //    4. Verificar se um produto existe por ID válido
    @Test
    public void verificarSeProdutoExisteIDValido() {
        productApplication.append(batataFrita);

        Assertions.assertTrue(productApplication.exists(1));
    }

    //    5. Retornar falso ao verificar a existência de um produto com ID inválido
    @Test
    public void verificarSeProdutoExisteIDInvalido () {
        Assertions.assertFalse(productApplication.exists(3));
    }

    //    6. Adicionar um novo produto e salvar sua imagem corretamente
    @Test
    public void testarAdicionarProdutoESalvarImagem() {
        productApplication.append(batataFrita);


        Assertions.assertTrue(new File(batataFrita.getImage()).exists());
    }

    //    7. Remover um produto existente e deletar sua imagem
    @Test
    public void testarRemoverProdutoEDeletarImagem() {
        productApplication.append(batataFrita);

        productApplication.remove(1);
        Path path = Paths.get(batataFrita.getImage());

        Assertions.assertFalse(productApplication.exists(1));
        Assertions.assertFalse(Files.exists(path));
    }

    //    8. Não alterar o sistema ao tentar remover um produto com ID inexistente
    @Test
    public void testarRemoverProdutoComIDInexistente() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            productApplication.remove(5);
        });
    }

    //    9. Atualizar um produto existente e substituir sua imagem
    @Test
    public void testarAtualizarProdutoESubstituirImagem() {
        productApplication.append(batataFrita);
        String primeiraImagem = batataFrita.getImage();

        productApplication.update(1, xburger);

        Assertions.assertEquals("xburger", productApplication.getById(1).getDescription());
        Assertions.assertEquals(20, productApplication.getById(1).getPrice());

        Path pathChiken = Paths.get(primeiraImagem);
        Assertions.assertTrue(Files.exists(pathChiken));

        Path pathFish = Paths.get(xburger.getImage());
        Assertions.assertTrue(Files.exists(pathFish));
    }
}
