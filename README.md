# 📦 Sistema de Gerenciamento de Produtos (Atividade de Testes Unitários)

Este projeto foi desenvolvido como parte de uma atividade prática de testes unitários e de integração em Java, utilizando as ferramentas **JUnit 5** e **Jacoco**. A proposta era resolver casos de uso de teste em um sistema genérico pronto, aplicando boas práticas de testes, como o padrão **AAA (Arrange-Act-Assert)** e nomenclatura descritiva de métodos de teste.

---

## 📋 Proposta da Atividade

A atividade consistiu em desenvolver testes unitários e de integração para um sistema de gerenciamento de produtos com operações básicas de CRUD (Create, Read, Update, Delete), utilizando **JUnit 5**. Além disso, foram aplicadas boas práticas como:

- Padrão **Arrange/Act/Assert (AAA)** para organização dos testes.
- Nomes descritivos nos métodos de teste.
- Uso de `@BeforeEach` para preparar massas de dados comuns.
- Cobertura de código medida com **Jacoco**.

---

## 📑 Funcionalidades do Sistema

O sistema implementa um CRUD simples da entidade `Produto`, com as seguintes operações:

- **Adicionar produto**
- **Buscar produto por ID**
- **Atualizar produto**
- **Remover produto**
- **Listar todos os produtos**
- **Verificar existência de produto por ID**
- **Salvar e excluir imagens associadas aos produtos**

---

## 🧪 Casos de Uso Testados

### ✅ Testes Unitários

**ProductRepository**

1. Adicionar um produto.
2. Buscar produto por ID.
3. Verificar existência de produto.
4. Remover produto.
5. Atualizar produto.
6. Listar todos os produtos.
7. Remover produto inexistente.
8. Atualizar produto inexistente.
9. Adicionar produto com ID duplicado.
10. Confirmar lista vazia no início.

**ProductService**

1. Salvar produto com imagem válida.
2. Salvar produto com imagem inexistente.
3. Atualizar produto existente.
4. Remover produto existente.
5. Obter caminho da imagem por ID.

### 🔄 Testes de Integração

**ProductApplication**

1. Listar todos os produtos.
2. Buscar produto por ID válido.
3. Retornar nulo ou erro ao buscar ID inválido.
4. Verificar existência de produto com ID válido.
5. Retornar falso para ID inexistente.
6. Adicionar produto e salvar imagem.
7. Remover produto e imagem.
8. Tentar remover ID inexistente sem alterar o sistema.
9. Atualizar produto e substituir imagem.

**ProductFacade**

1. Listar todos os produtos.
2. Buscar produto por ID.
3. Verificar existência de produto.
4. Adicionar produto.
5. Remover produto.

---

## 💻 Tecnologias Utilizadas

- **Java 22**
- **JUnit 5**
- **Jacoco**
- **Maven** (para gerenciamento de dependências e build)

---

## 🛠️ Como Executar o Projeto Localmente

### Pré-requisitos

- **JDK 22**
- **IDE Java** (recomendado: IntelliJ IDEA ou Eclipse)
- **Maven**

### Passos para executar

1. Clone o repositório:
   ```bash
   git clone https:/github.com/devGabrielDias/TestNLayerLanches.git
