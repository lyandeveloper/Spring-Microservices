package com.api.emailservice.dtos;

import java.io.Serializable;
import java.util.UUID;

public record EmailDTO(UUID userId,
                       String emailTo,
                       String subject,
                       String text)  {
}
