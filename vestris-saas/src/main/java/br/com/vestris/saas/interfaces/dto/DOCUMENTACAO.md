## src\main\java\br\com\vestris\saas\interfaces\dto

### WebhookMP.java

```java
// src\main\java\br\com\vestris\saas\interfaces\dto\WebhookMP.java
package br.com.vestris.saas.interfaces.dto;

import lombok.Data;

@Data
public class WebhookMP {
    private String action; // payment.created, etc
    private String type;
    private DataObj data;

    @Data
    public static class DataObj {
        private String id;
    }
}

```

