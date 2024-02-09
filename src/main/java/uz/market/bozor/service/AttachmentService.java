package uz.market.bozor.service;

import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    void uploadFile(MultipartFile file) throws IllegalArgumentException;

}
