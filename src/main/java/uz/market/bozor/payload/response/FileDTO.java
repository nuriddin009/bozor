package uz.market.bozor.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.market.bozor.entity.Attachment;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class FileDTO {
    UUID id;
    String url;
    String objectName;

    public FileDTO(UUID id) {
        this.id = id;
    }

    public FileDTO(Attachment fileUpload) {
        this.url = fileUpload.getUrl();
        this.objectName = fileUpload.getObjectName();
    }
}