package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductRepositoryTest {
    ProductRepository productRepository;
    Product xburger;
    Product batataFrita;

    @BeforeEach
    public void setup() {
        batataFrita = new Product(1, "Batata-Frita", 10, "src\\test\\java\\com\\snack\\imagens\\batataFrita.jpg");
        xburger = new Product(1, "xburger", 20, "src\\test\\java\\com\\snack\\imagens\\xburger.jpg");
        productRepository = new ProductRepository();

    }

    //    1. Verificar se um produto é adicionado corretamente ao repositório (List)
    @Test
    public void testarSeProdutoFoiAdicionadoCorretamente() {
        productRepository.append(batataFrita);

        Assertions.assertEquals("Batata-Frita", productRepository.getById(1).getDescription());
        Assertions.assertEquals(10, productRepository.getById(1).getPrice());
        Assertions.assertEquals("src\\test\\java\\com\\snack\\imagens\\batataFrita.jpg", productRepository.getById(1).getImage());
    }

    //    2. Verificar se é possível recuperar um produto usando seu ID
    @Test
    public void testarRecuperarProdutoPeloId() {
        productRepository.append(batataFrita);

        Assertions.assertEquals(1, productRepository.getById(1).getId());
    }

    //    3. Confirmar se um produto existe no repositório (List)
    @Test
    public void validarProdutoNoRepositorio() {
        productRepository.append(batataFrita);

        Assertions.assertTrue(productRepository.exists(1));

    }

    //    4. Testar se um produto é removido corretamente do repositório (List)
    @Test
    public void testarRemocaoDeProduto() {
        productRepository.append(batataFrita);

        productRepository.remove(1);

        Assertions.assertFalse(productRepository.exists(1));
    }

    //    5. Verificar se um produto é atualizado corretamente
    @Test
    public void testarAtualizarProduto() {
        productRepository.append(batataFrita);

        productRepository.update(1, xburger);

        Assertions.assertEquals("xburger", productRepository.getById(1).getDescription());
        Assertions.assertEquals(20, productRepository.getById(1).getPrice());
        Assertions.assertEquals("src\\test\\java\\com\\snack\\imagens\\xburger.jpg", productRepository.getById(1).getImage());
    }

    //    6. Testar se todos os produtos armazenados são recuperados corretamente
    @Test
    public void testarRecuperacaoDeTodosProdutos() {
        productRepository.append(batataFrita);
        productRepository.append(xburger);

        List<Product> products = productRepository.getAll();

        Assertions.assertEquals(2, products.size());
    }

    //    7. Verificar o comportamento ao tentar remover um produto que não existe
    @Test
    public void testarRemoverProdutoInexistente() {
        productRepository.remove(5);

        Assertions.assertEquals(0, productRepository.getAll().size());
    }

    //    8. Testar o que acontece ao tentar atualizar um produto que não está no repositório (List)
    @Test
    public void testarAtualizarProdutoInexistente() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            productRepository.update(5, xburger);
        });
    }

    //    9. Verificar se o repositório aceita a adição de produtos com IDs duplicados (espera-se que não)
    @Test
    public void testarAdicionarIDsDuplicados() {
        productRepository.append(batataFrita);

        Product produtoDuplicado = new Product(1, "X-Chicken 2", 15, "src\\test\\java\\com\\snack\\imagens\\xChicken.jpg");
        productRepository.append(produtoDuplicado);

        Assertions.assertEquals(2, productRepository.getAll().size());
        Assertions.assertEquals(1, productRepository.getAll().get(0).getId());
        Assertions.assertEquals(1, productRepository.getAll().get(1).getId());
    }

    //    10. Confirmar que o repositório retorna uma lista vazia ao ser inicializado (List)
    @Test
    public void testarRepositorioInicializadoVazio() {
        Assertions.assertTrue(productRepository.getAll().isEmpty());
    }







}
