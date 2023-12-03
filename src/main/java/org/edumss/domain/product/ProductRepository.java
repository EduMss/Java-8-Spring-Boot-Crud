package org.edumss.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByName(String name);

    List<Product> findByNameIgnoreCase(String name);

    List<Product> findByNameLikeIgnoreCase(String name);

    List<Product> findByNameStartsWithIgnoreCase(String name);
    //findByName ->  busca o nome que tenha o mesmo valor que a variavel name
    //StartsWith -> busca todos os valores que começa com o valor da variavel name
    //IgnoreCase -> ignora se a maiusculo ou minusculo

    //List<Product> findByNameLike(String name);


    //List<Product> findAllByOrderByNameDesc(String name);

    List<Product> findByPriceInCents(Integer priceInCents);

    // Buscar todos os itens com price_in_cents maior que o valor fornecido
    List<Product> findByPriceInCentsGreaterThan(int priceInCents);

    // Buscar todos os itens com price_in_cents menor que o valor fornecido
    List<Product> findByPriceInCentsLessThan(int priceInCents);

    // Buscar todos os itens com price_in_cents maior ou igual ao valor fornecido
    List<Product> findByPriceInCentsGreaterThanEqual(int priceInCents);

    // Buscar todos os itens com price_in_cents menor ou igual ao valor fornecido
    List<Product> findByPriceInCentsLessThanEqual(int priceInCents);
}
