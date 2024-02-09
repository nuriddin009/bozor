package uz.market.bozor.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.market.bozor.entity.Attachment;
import uz.market.bozor.repository.AttachmentRepository;
import uz.market.bozor.service.AttachmentService;
import uz.market.bozor.service.FileUploadService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final FileUploadService fileUploadService;

    @Override
    public void uploadFile(MultipartFile file) throws IllegalArgumentException {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Empty file");
            }
            Attachment attachment = fileUploadService.fileUpload(file, true);
            attachmentRepository.save(attachment);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
