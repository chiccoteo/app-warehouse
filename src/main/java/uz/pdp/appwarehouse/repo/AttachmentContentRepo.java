package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, Integer> {

    Optional<AttachmentContent> findByAttachmentId(Integer id);

    void deleteByAttachmentId(Integer attachment_id);

}
