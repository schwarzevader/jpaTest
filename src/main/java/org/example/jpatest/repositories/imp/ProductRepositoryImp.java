package org.example.jpatest.repositories.imp;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.jpatest.entity.Product;
import org.example.jpatest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
//@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class ProductRepositoryImp {

    @PersistenceContext
    private EntityManager entityManager;




    @Autowired
    //@NonNull
    private ProductRepository productRepository;
//
//    @Autowired
//    private CrudProductRepository crudProductRepository;
//
//    public ProductRepositoryImp(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
    public void saveProduct(Product product){
//        productRepository.save(product);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }

    }

    public void updateProduct(Product product){
//        productRepository.save(product);

        productRepository.findProductById(product.getId());
        try {
            Product prod =  entityManager.find(Product.class, product.getId());
            prod.setName(product.getName());
            prod.setDescription(product.getDescription());
            prod.setPrice(product.getPrice());
            prod.setProductCategory(product.getProductCategory());
            prod.setProductSpecItemList(product.getProductSpecItemList());
            entityManager.getTransaction().begin();
            entityManager.persist(prod);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }

    }
//
//    public void saveCategory(ProductCategory category){
//        entityManager.persist(category);
//    }
//
//    public long allProduct(){
//        return productRepository.count();
//    }
//
//    public Product findProductByName(String name){
//        return productRepository.findProductByName(name);
//    }
//
    public Product findById(Long id){
//      return productRepository.findProductByIdIs(id);
      return entityManager.find(Product.class,id);
    }
//
//
//
//    public List<ProductDTO> searchAndPaginationProductDTOOrderByPrice(String keyWord, int firstResult ,
//                                                                      int  maxResult  , String descOrAsc ){
//        @SuppressWarnings("unchecked")
//        List<ProductDTO> productDTOList =  entityManager
//                .createQuery(
//                        "select new " +
//                                "   com.example.productsservice.dto.ProductDTO( " +
//                                "       p.id, p.name, " +
//                                "       p.description, p.price, c.nameOfCategory " +
//                                "   ) " +
//                                "from Product p " +
//                                "join p.category c " +
//                               "where p.name like:name or p.category.nameOfCategory  LIKE:category "+
//                                "order by p.price "+descOrAsc)
//                .setParameter("category",keyWord.toLowerCase()+"%")
//                .setParameter("name",keyWord.toLowerCase()+"%")
//                .setFirstResult(firstResult)
//                .setMaxResults(maxResult)
//                .getResultList();
//
//        return productDTOList;
//    }
//
//    public List<Product> paginationProductOrderByPrice(String keyWord ,int firstResult ,int  maxResult  , String descOrAsc ){
//
//        @SuppressWarnings("unchecked")
//        List<Product> productDTOList =  entityManager
//                .createQuery(
//                        "select p "+
//                                "from Product p " +
//                                "join p.category c " +
//                                "where p.name like:name or p.category.nameOfCategory  LIKE:category "+
//                                "order by p.price "+descOrAsc)
//                .setParameter("category",keyWord+"%")
//                .setParameter("name",keyWord+"%")
//                .setFirstResult(firstResult)
//                .setMaxResults(maxResult)
//                .getResultList();
//
//        return productDTOList;
//    }
//
//
////    public List<ProductDTO> paginationProductDTO(int  maxResult , int firstResult ){
////        return entityManager
////                .createQuery(
////                        "select new " +
////                                "   com.example.productsservice.dto.ProductDTO( " +
////                                "       p.id, p.name, " +
////                                "       p.description, p.price, c.nameOfCategory " +
////                                "   ) " +
////                                "from Product p " +
////                                "join p.category c " +
////                                "order by p.id")
////                .setFirstResult(firstResult)
////                .setMaxResults(maxResult)
////                .getResultList();
////    }
//
//    public List<ProductDTO> getPageableProduct(int pageNum , int pageSize  , String keyWord){
//        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
//
//
//        Page<ProductDTO> page = productRepository.findProductByNameOrCategoryOrCompany(pageable,keyWord.toLowerCase()+"%" );
//        List<ProductDTO> products = page.getContent();
//        int totalPage= page.getTotalPages();
//        return products;
//    }
//
//    public List<Product> findAll(){
//        return productRepository.findAll();
//    }
//
//    public boolean existsByName(String name){
//
//        return productRepository.existsByName(name);
//    }
//
//    public boolean existsById(Long id){
//        return productRepository.existsProductById(id);
//    }
//



}
