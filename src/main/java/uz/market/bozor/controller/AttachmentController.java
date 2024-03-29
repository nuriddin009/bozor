package uz.market.bozor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.market.bozor.payload.model.BaseResponse;
import uz.market.bozor.service.AttachmentService;

import static uz.market.bozor.component.ResponseGenerator.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
public class AttachmentController {

    private final AttachmentService service;

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        service.uploadFile(file);
        BaseResponse<String> response = generateSuccessResponse.apply("Imaged uploaded successfully");
        return ResponseEntity.ok("success");
    }


}
