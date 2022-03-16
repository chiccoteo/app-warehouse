package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;


    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadFile(request);
    }

    @GetMapping
    public Result getAllFileInfo(){
        return attachmentService.getAllFileInfo();
    }

    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id) {
        return attachmentService.getInfoById(id);
    }

    @GetMapping("download/{id}")
    public Result downloadFileById(@PathVariable Integer id, HttpServletResponse response) {
        return attachmentService.downloadFileById(id, response);
    }

    @PutMapping("/{id}")
    public Result editFileById(@PathVariable Integer id, MultipartHttpServletRequest request) {
        return attachmentService.editFileById(id, request);
    }

    @DeleteMapping("/{id}")
    public Result deleteFileById(@PathVariable Integer id) {
        return attachmentService.deleteFileById(id);
    }
}
