package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entites.concretes.Product;
import kodlamaio.northwind.entites.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer> {
	
	//select*from products where product_name; gibi bir sql sorgusu çalıştırır.
	Product getByProductName(String productName);
	
	Product getByProductNameAndCategory_CategoryId(String productName,int categoryId);
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName,int categoryId);
	
	List<Product> getByCategoryIn(List<Integer> categories);
	
	List<Product> getByProductNameContains(String productName);

	List<Product> getByProductNameStartsWith(String productName);
	
	
	//JPQ -- LINQ gibi
	//kod veri tabanındakine göre değil entitykilere göre isimlendirirlir
	@Query("From Product where productName=:productName and category=:categoryId")
	List<Product> getByNameAndCategory(String productName,int categoryId);
	
	@Query("Select new kodlamaio.northwind.entites.dtos.ProductWithCategoryDto(p.id,p.productName,c.categoryName) From Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
	
	
}
