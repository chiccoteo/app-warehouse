package uz.pdp.appwarehouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.Attachment;

public interface AttachmentRepo extends JpaRepository<Attachment, Integer> {
}
