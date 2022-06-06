package com.esgi.velib_commenter_api.kernel;

import java.time.LocalDateTime;

public interface Clock {
    LocalDateTime now();
}
