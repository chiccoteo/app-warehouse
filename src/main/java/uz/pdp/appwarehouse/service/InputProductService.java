package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.InputProductRepo;
import uz.pdp.appwarehouse.repo.InputRepo;
import uz.pdp.appwarehouse.repo.ProductRepo;

import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepo inputProductRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    InputRepo inputRepo;


    public Result add(InputProductDto inputProductDto) {
        Optional<Product> optionalProduct = productRepo.findById(inputProductDto.getProductId());
        Optional<Input> optionalInput = inputRepo.findById(inputProductDto.getInputId());
        Result checking = checking(optionalProduct, optionalInput);
        if (checking.isSuccess()) {
            InputProduct inputProduct = new InputProduct();
            inputProduct.setProduct(optionalProduct.orElse(null));
            inputProduct.setInput(optionalInput.orElse(null));
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(inputProductDto.getExpireDate());
            inputProductRepo.save(inputProduct);
            return new Result("Successfully saved", true);
        }
        return checking;
    }

    private Result checking(Optional<Product> optionalProduct, Optional<Input> optionalInput) {
        if (optionalProduct.isEmpty())
            return new Result("Such a product doesn't exist", false);

        if (optionalInput.isEmpty())
            return new Result("Such a input doesn't exist", false);

        return new Result("Success", true);

    }

    public Result getAll() {
        return new Result("Success", true, inputProductRepo.findAll());
    }

    public Result getById(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepo.findById(id);
        if (optionalInputProduct.isEmpty())
            return new Result("Such a input product doesn't exist", false);
        return new Result("Success", true, optionalInputProduct.get());
    }

    public Result editById(Integer id, InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepo.findById(id);
        if (optionalInputProduct.isEmpty())
            return new Result("Such a input product doesn't exist", false);
        Optional<Product> optionalProduct = productRepo.findById(inputProductDto.getProductId());
        Optional<Input> optionalInput = inputRepo.findById(inputProductDto.getInputId());
        Result checking = checking(optionalProduct, optionalInput);
        if (checking.isSuccess()) {
            InputProduct inputProduct = optionalInputProduct.get();
            inputProduct.setProduct(optionalProduct.orElse(null));
            inputProduct.setInput(optionalInput.orElse(null));
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(inputProductDto.getExpireDate());
            inputProductRepo.save(inputProduct);
            return new Result("Successfully edited", true);
        }
        return checking;
    }

    public Result deleteById(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepo.findById(id);
        if (optionalInputProduct.isEmpty())
            return new Result("Such a input product doesn't exist", false);
        inputProductRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
