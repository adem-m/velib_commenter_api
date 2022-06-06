package com.esgi.velib_commenter_api.modules.comments.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateCommentRequest {
    @NotBlank
    @NotNull
    public String content;
}
