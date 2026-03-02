## src\main\java\br\com\vestris\pharmacology\domain\model\enums

### StatusSegurancaDose.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\enums\StatusSegurancaDose.java
package br.com.vestris.pharmacology.domain.model.enums;

public enum StatusSegurancaDose {
    SEGURO,
    SUBDOSE,
    SUPERDOSE,
    SEM_REFERENCIA,
    NAO_VALIDADO // Para unidades não suportadas ainda (ex: UI/kg)
}

```

### UnidadeDose.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\enums\UnidadeDose.java
package br.com.vestris.pharmacology.domain.model.enums;

public enum UnidadeDose {
    MG_KG, MCG_KG, UI_KG, MG_ANIMAL, ML_KG
}

```

### ViaAdministracao.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\enums\ViaAdministracao.java
package br.com.vestris.pharmacology.domain.model.enums;

public enum ViaAdministracao {
    ORAL, SC, IM, IV, IO, INALATORIA, TOPICA, OUTRA
}

```

