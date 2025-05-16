package com.snack.services;

import com.snack.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProductServiceTest {
    ProductService productService;
    Product duCheffImagemInvalida;
    Product xburger;
    Product batataFrita;

    @BeforeEach
    public void setUp() {
        batataFrita = new Product(1, "Batata-Frita", 10, "src\\test\\java\\com\\snack\\imagens\\batataFrita.jpg");
        xburger = new Product(1, "xburger", 20, "src\\test\\java\\com\\snack\\imagens\\xburger.jpg");
        duCheffImagemInvalida = new Product(3, "duCheff", 20, "caminho_inexistente.jpg");
        productService = new ProductService();
    }

    //    1. Salvar um produto com imagem válida
    @Test
    public void testarSalvarProdutoComImagemValida() {
        boolean result = productService.save(batataFrita);

        Assertions.assertTrue(result);
    }

    //    2. Salvar um produto com imagem inexistente
    @Test
    public void testarProdutoImagemInvalida() {
        boolean result = productService.save(duCheffImagemInvalida);

        Assertions.assertFalse(result);
    }

    //    3. Atualizar um produto existente
    @Test
    public void atualizarUmProdutoExistente() {
        productService.save(batataFrita);
        productService.update(xburger);

        Assertions.assertEquals("xburger", xburger.getDescription());
        Assertions.assertEquals(20, xburger.getPrice());
        Path path = Paths.get(xburger.getImage());
        Assertions.assertTrue(Files.exists(path));
    }

    //    4. Remover um produto existente
    @Test
    public void testarRemoverProdutoExistente() {
        productService.save(batataFrita);
        productService.remove(1);

        Assertions.assertFalse(Files.exists(Paths.get(batataFrita.getImage())));
    }

    //    5. Obter caminho da imagem por ID
    @Test
    public void testarCaminhoImagemId() {
        productService.save(batataFrita);

        String caminhoDaImagem = productService.getImagePathById(batataFrita.getId());
        Assertions.assertTrue(Files.exists(Paths.get(caminhoDaImagem)));
    }

}
