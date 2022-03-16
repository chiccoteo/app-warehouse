package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.OutputProductRepo;
import uz.pdp.appwarehouse.repo.OutputRepo;
import uz.pdp.appwarehouse.repo.ProductRepo;

import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepo outputProductRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OutputRepo outputRepo;


    public Result add(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepo.findById(outputProductDto.getProductId());
        Optional<Output> optionalOutput = outputRepo.findById(outputProductDto.getOutputId());
        Result checking = checking(optionalProduct, optionalOutput);
        if (checking.isSuccess()) {
            OutputProduct outputProduct = new OutputProduct();
            outputProduct.setProduct(optionalProduct.orElse(null));
            outputProduct.setOutput(optionalOutput.orElse(null));
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());
            outputProductRepo.save(outputProduct);
            return new Result("Successfully saved", true);
        }
        return checking;
    }

    private Result checking(Optional<Product> optionalProduct, Optional<Output> optionalOutput) {
        if (optionalProduct.isEmpty())
            return new Result("Such a product doesn't exist", false);

        if (optionalOutput.isEmpty())
            return new Result("Such a output doesn't exist", false);

        return new Result("Success", true);

    }

    public Result getAll() {
        return new Result("Success", true, outputProductRepo.findAll());
    }

    public Result getById(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepo.findById(id);
        if (optionalOutputProduct.isEmpty())
            return new Result("Such a output product doesn't exist", false);
        return new Result("Success", true, optionalOutputProduct.get());
    }

    public Result editById(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepo.findById(id);
        if (optionalOutputProduct.isEmpty())
            return new Result("Such a output product doesn't exist", false);
        Optional<Product> optionalProduct = productRepo.findById(outputProductDto.getProductId());
        Optional<Output> optionalOutput = outputRepo.findById(outputProductDto.getOutputId());
        Result checking = checking(optionalProduct, optionalOutput);
        if (checking.isSuccess()) {
            OutputProduct outputProduct = optionalOutputProduct.get();
            outputProduct.setProduct(optionalProduct.orElse(null));
            outputProduct.setOutput(optionalOutput.orElse(null));
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());
            outputProductRepo.save(outputProduct);
            return new Result("Successfully edited", true);
        }
        return checking;
    }

    public Result deleteById(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepo.findById(id);
        if (optionalOutputProduct.isEmpty())
            return new Result("Such a output product doesn't exist", false);
        outputProductRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
