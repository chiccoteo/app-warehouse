package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.AttachmentRepo;
import uz.pdp.appwarehouse.repo.CategoryRepo;
import uz.pdp.appwarehouse.repo.MeasurementRepo;
import uz.pdp.appwarehouse.repo.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    AttachmentRepo attachmentRepo;

    @Autowired
    MeasurementRepo measurementRepo;


    public Result addProduct(ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(productDto.getPhotoId());
        Optional<Measurement> optionalMeasurement = measurementRepo.findById(productDto.getMeasurementId());
        Result checking = checking(productDto, optionalCategory, optionalAttachment, optionalMeasurement);
        if (checking.isSuccess()) {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setCode(getSpecialCode());
            product.setCategory(optionalCategory.orElse(null));
            product.setPhoto(optionalAttachment.orElse(null));
            product.setMeasurement(optionalMeasurement.orElse(null));
            productRepo.save(product);
            return new Result("Successfully saved", true);
        }
        return checking;
    }

    private String getSpecialCode() {
        List<Product> products = productRepo.findAll();
        if (products.isEmpty())
            return "1";
        return String.valueOf(Integer.parseInt(products.get(products.size() - 1).getCode()) + 1);
    }

    private Result checking(ProductDto productDto,
                            Optional<Category> optionalCategory,
                            Optional<Attachment> optionalAttachment,
                            Optional<Measurement> optionalMeasurement) {
        boolean existsByName = productRepo.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByName)
            return new Result("This product already exists", false);

        // Checking Category
        if (optionalCategory.isEmpty())
            return new Result("Such a category doesn't exist", false);

        // Checking photo
        if (optionalAttachment.isEmpty())
            return new Result("Such a photo doesn't exist", false);

        // Checking measurement
        if (optionalMeasurement.isEmpty())
            return new Result("Such a measurement doesn't exist", false);

        return new Result("Successfully", true);
    }

    public Result getProducts() {
        return new Result("Successfully", true, productRepo.findAll());
    }

    public Result getProductById(Integer id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isEmpty())
            return new Result("Such a product doesn't exist", false);
        return new Result("Successfully", true, optionalProduct.get());
    }

    public Result editProductById(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isEmpty())
            return new Result("Such a product doesn't exist", false);
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(productDto.getPhotoId());
        Optional<Measurement> optionalMeasurement = measurementRepo.findById(productDto.getMeasurementId());
        Result checking = checking(productDto, optionalCategory, optionalAttachment, optionalMeasurement);
        if (checking.isSuccess()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
//            product.setCode(getSpecialCode()); // Product code isn't changed
            product.setCategory(optionalCategory.orElse(null));
            product.setPhoto(optionalAttachment.orElse(null));
            product.setMeasurement(optionalMeasurement.orElse(null));
            productRepo.save(product);
            return new Result("Successfully edited", true);
        }
        return checking;
    }

    public Result deleteProductById(Integer id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isEmpty())
            return new Result("Such a product doesn't exist", false);
        productRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
