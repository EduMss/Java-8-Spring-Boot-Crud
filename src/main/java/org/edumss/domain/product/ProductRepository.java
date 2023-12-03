package org.edumss.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    //Buscar todos os itens
    List<Product> findByName(String name);

    //Buscar todos os itens ativos
    List<Product> findAllByActiveTrue();

    //Buscar todos os itens ignorando os caracteres maiusculos
    List<Product> findByNameIgnoreCase(String name);

    //Buscando todos os itens que o nome começa com ... sem diferenciar maiusculo e minusculo
    List<Product> findByNameStartsWithIgnoreCase(String name);
    //findByName ->  busca o nome que tenha o mesmo valor que a variavel name
    //StartsWith -> busca todos os valores que começa com o valor da variavel name
    //IgnoreCase -> ignora se a maiusculo ou minusculo

    //Buscando todos os itens que o nome começa com ... sem diferenciar maiusculo e minusculo e que esteja ativo
    List<Product> findByNameStartsWithIgnoreCaseAndActiveTrue(String name);
    //AndActiveTrue -> E todos que a coluna active for true

    //Buscando itens com um valor especifico
    List<Product> findByPriceInCents(Integer priceInCents);

    // Buscar todos os itens com price_in_cents maior que o valor fornecido
    List<Product> findByPriceInCentsGreaterThan(int priceInCents);

    // Buscar todos os itens com price_in_cents menor que o valor fornecido
    List<Product> findByPriceInCentsLessThan(int priceInCents);

    // Buscar todos os itens com price_in_cents maior ou igual ao valor fornecido
    List<Product> findByPriceInCentsGreaterThanEqual(int priceInCents);

    // Buscar todos os itens com price_in_cents maior ou igual ao valor fornecido, ordenando de forma ascendente
    List<Product> findByPriceInCentsGreaterThanEqualOrderByPriceInCentsAsc(int priceInCents);

    // Buscar todos os itens com price_in_cents maior ou igual ao valor fornecido, ordenando de forma descendente
    List<Product> findByPriceInCentsGreaterThanEqualOrderByPriceInCentsDesc(int priceInCents);

    // Buscar todos os itens com price_in_cents maior ou igual ao valor fornecido que esteja ativo
    List<Product> findByPriceInCentsGreaterThanEqualAndActiveTrue(int priceInCents);

    // Buscar todos os itens com price_in_cents menor ou igual ao valor fornecido
    List<Product> findByPriceInCentsLessThanEqual(int priceInCents);

    // Buscar todos os itens com price_in_cents menor ou igual ao valor fornecido que esteja ativo
    List<Product> findByPriceInCentsLessThanEqualAndActiveTrue(int priceInCents);



}
