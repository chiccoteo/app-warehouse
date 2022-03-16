package uz.pdp.appwarehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.AttachmentContent;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.AttachmentContentRepo;
import uz.pdp.appwarehouse.repo.AttachmentRepo;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepo attachmentRepo;

    @Autowired
    AttachmentContentRepo attachmentContentRepo;


    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment savedFile = attachmentRepo.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(savedFile);
            attachmentContentRepo.save(attachmentContent);
            return new Result("Successfully saved", true, savedFile.getId());
        }
        return new Result("Such a file doesn't found", false);
    }

    public Result getAllFileInfo() {
        return new Result("Successfully", true, attachmentRepo.findAll());
    }

    public Result getInfoById(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(id);
        if (optionalAttachment.isEmpty())
            return new Result("Such a file doesn't found", false);
        return new Result("Successfully", true, optionalAttachment.get());
    }

    @SneakyThrows
    public Result downloadFileById(Integer id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepo.findByAttachmentId(id);
            if (optionalAttachmentContent.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
                return new Result("Successfully", true);
            }
        }
        return new Result("Such a file doesn't found", false);
    }

    @SneakyThrows
    public Result editFileById(Integer id, MultipartHttpServletRequest request) {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(id);
        if (optionalAttachment.isEmpty())
            return new Result("Such a file doesn't found", false);
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            Attachment attachment = optionalAttachment.get();
            attachment.setName(file.getOriginalFilename());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            Attachment savedFile = attachmentRepo.save(attachment);

            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepo.findByAttachmentId(id);
            if (optionalAttachmentContent.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                attachmentContent.setBytes(file.getBytes());
                attachmentContent.setAttachment(savedFile);
                attachmentContentRepo.save(attachmentContent);
                return new Result("Successfully edited", true);
            }
        }
        return new Result("File isn't come", false);
    }

    public Result deleteFileById(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(id);
        if (optionalAttachment.isEmpty())
            return new Result("Such a file doesn't found", false);
        Attachment attachment = optionalAttachment.get();
        attachmentContentRepo.deleteByAttachmentId(attachment.getId());
        attachmentRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
