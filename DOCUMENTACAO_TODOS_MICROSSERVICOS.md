# Documentação Centralizada de Todos os Microsserviços

## Índice de Projetos, Diretórios e Arquivos

- Projeto: infra
  - [.](#)
    - [init-schemas.sql](#init-schemassql)

- Projeto: vestris-clinical
  - [.](#)
    - [.openapi-generator-ignore](#openapi-generator-ignore)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\clinical\application](#srcmainjavabrcomvestrisclinicalapplication)
    - [ServiceCalculadora.java](#srcmainjavabrcomvestrisclinicalapplicationservicecalculadorajava)
    - [ServiceDoenca.java](#srcmainjavabrcomvestrisclinicalapplicationservicedoencajava)
    - [ServiceProtocolo.java](#srcmainjavabrcomvestrisclinicalapplicationserviceprotocolojava)
  - [src\main\java\br\com\vestris\clinical\domain\model](#srcmainjavabrcomvestrisclinicaldomainmodel)
    - [CalculoResultadoDTO.java](#srcmainjavabrcomvestrisclinicaldomainmodelcalculoresultadodtojava)
    - [Doenca.java](#srcmainjavabrcomvestrisclinicaldomainmodeldoencajava)
    - [Dosagem.java](#srcmainjavabrcomvestrisclinicaldomainmodeldosagemjava)
    - [Protocolo.java](#srcmainjavabrcomvestrisclinicaldomainmodelprotocolojava)
    - [ProtocoloCompletoDTO.java](#srcmainjavabrcomvestrisclinicaldomainmodelprotocolocompletodtojava)
  - [src\main\java\br\com\vestris\clinical\domain\repository](#srcmainjavabrcomvestrisclinicaldomainrepository)
    - [RepositorioDoenca.java](#srcmainjavabrcomvestrisclinicaldomainrepositoryrepositoriodoencajava)
    - [RepositorioProtocolo.java](#srcmainjavabrcomvestrisclinicaldomainrepositoryrepositorioprotocolojava)
  - [src\main\java\br\com\vestris\clinical\interfaces\delegate](#srcmainjavabrcomvestrisclinicalinterfacesdelegate)
    - [ApiDelegateCalculadora.java](#srcmainjavabrcomvestrisclinicalinterfacesdelegateapidelegatecalculadorajava)
    - [ApiDelegateDoencas.java](#srcmainjavabrcomvestrisclinicalinterfacesdelegateapidelegatedoencasjava)
    - [ApiDelegateProtocolos.java](#srcmainjavabrcomvestrisclinicalinterfacesdelegateapidelegateprotocolosjava)
  - [src\main\java\br\com\vestris\clinical\interfaces\dto](#srcmainjavabrcomvestrisclinicalinterfacesdto)
    - [CalculoCatalogoRequest.java](#srcmainjavabrcomvestrisclinicalinterfacesdtocalculocatalogorequestjava)
    - [CalculoLivreRequest.java](#srcmainjavabrcomvestrisclinicalinterfacesdtocalculolivrerequestjava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [calculadora.yml](#srcmainresourcesswaggerpathscalculadorayml)
    - [doencas.yml](#srcmainresourcesswaggerpathsdoencasyml)
    - [protocolos.yml](#srcmainresourcesswaggerpathsprotocolosyml)

- Projeto: vestris-feedbacks
  - [.](#)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\feedbacks\application](#srcmainjavabrcomvestrisfeedbacksapplication)
    - [ServiceSugestao.java](#srcmainjavabrcomvestrisfeedbacksapplicationservicesugestaojava)
  - [src\main\java\br\com\vestris\feedbacks\domain](#srcmainjavabrcomvestrisfeedbacksdomain)
    - [Sugestao.java](#srcmainjavabrcomvestrisfeedbacksdomainsugestaojava)
  - [src\main\java\br\com\vestris\feedbacks\domain\repository](#srcmainjavabrcomvestrisfeedbacksdomainrepository)
    - [RepositorioSugestao.java](#srcmainjavabrcomvestrisfeedbacksdomainrepositoryrepositoriosugestaojava)
  - [src\main\java\br\com\vestris\feedbacks\interfaces](#srcmainjavabrcomvestrisfeedbacksinterfaces)
    - [ApiDelegateSugestao.java](#srcmainjavabrcomvestrisfeedbacksinterfacesapidelegatesugestaojava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [sugestoes.yml](#srcmainresourcesswaggerpathssugestoesyml)

- Projeto: vestris-medical-record
  - [.](#)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\medicalrecord\application](#srcmainjavabrcomvestrismedicalrecordapplication)
    - [ServiceAtendimento.java](#srcmainjavabrcomvestrismedicalrecordapplicationserviceatendimentojava)
    - [ServiceExames.java](#srcmainjavabrcomvestrismedicalrecordapplicationserviceexamesjava)
    - [ServicePaciente.java](#srcmainjavabrcomvestrismedicalrecordapplicationservicepacientejava)
  - [src\main\java\br\com\vestris\medicalrecord\domain\model](#srcmainjavabrcomvestrismedicalrecorddomainmodel)
    - [Atendimento.java](#srcmainjavabrcomvestrismedicalrecorddomainmodelatendimentojava)
    - [ExameAnexo.java](#srcmainjavabrcomvestrismedicalrecorddomainmodelexameanexojava)
    - [Paciente.java](#srcmainjavabrcomvestrismedicalrecorddomainmodelpacientejava)
  - [src\main\java\br\com\vestris\medicalrecord\domain\repository](#srcmainjavabrcomvestrismedicalrecorddomainrepository)
    - [RepositorioAtendimento.java](#srcmainjavabrcomvestrismedicalrecorddomainrepositoryrepositorioatendimentojava)
    - [RepositorioExameAnexo.java](#srcmainjavabrcomvestrismedicalrecorddomainrepositoryrepositorioexameanexojava)
    - [RepositorioPaciente.java](#srcmainjavabrcomvestrismedicalrecorddomainrepositoryrepositoriopacientejava)
  - [src\main\java\br\com\vestris\medicalrecord\interfaces\delegate](#srcmainjavabrcomvestrismedicalrecordinterfacesdelegate)
    - [ApiDelegateAtendimentos.java](#srcmainjavabrcomvestrismedicalrecordinterfacesdelegateapidelegateatendimentosjava)
    - [ApiDelegateExame.java](#srcmainjavabrcomvestrismedicalrecordinterfacesdelegateapidelegateexamejava)
    - [ApiDelegatePacientes.java](#srcmainjavabrcomvestrismedicalrecordinterfacesdelegateapidelegatepacientesjava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [atendimentos.yml](#srcmainresourcesswaggerpathsatendimentosyml)
    - [exames-anexo.yml](#srcmainresourcesswaggerpathsexames-anexoyml)
    - [pacientes.yml](#srcmainresourcesswaggerpathspacientesyml)

- Projeto: vestris-pharmacology
  - [.](#)
    - [pom.xml](#pomxml)
    - [vestris-pharmacology.iml](#vestris-pharmacologyiml)
  - [src\main\java\br\com\vestris\pharmacology\application](#srcmainjavabrcomvestrispharmacologyapplication)
    - [ServiceContraindicacao.java](#srcmainjavabrcomvestrispharmacologyapplicationservicecontraindicacaojava)
    - [ServiceDoseReferencia.java](#srcmainjavabrcomvestrispharmacologyapplicationservicedosereferenciajava)
    - [ServiceFarmacologia.java](#srcmainjavabrcomvestrispharmacologyapplicationservicefarmacologiajava)
    - [ServiceSegurancaFarmacologica.java](#srcmainjavabrcomvestrispharmacologyapplicationservicesegurancafarmacologicajava)
  - [src\main\java\br\com\vestris\pharmacology\domain\model](#srcmainjavabrcomvestrispharmacologydomainmodel)
    - [Contraindicacao.java](#srcmainjavabrcomvestrispharmacologydomainmodelcontraindicacaojava)
    - [DoseReferencia.java](#srcmainjavabrcomvestrispharmacologydomainmodeldosereferenciajava)
    - [Medicamento.java](#srcmainjavabrcomvestrispharmacologydomainmodelmedicamentojava)
    - [PrincipioAtivo.java](#srcmainjavabrcomvestrispharmacologydomainmodelprincipioativojava)
  - [src\main\java\br\com\vestris\pharmacology\domain\model\enums](#srcmainjavabrcomvestrispharmacologydomainmodelenums)
    - [StatusSegurancaDose.java](#srcmainjavabrcomvestrispharmacologydomainmodelenumsstatussegurancadosejava)
    - [UnidadeDose.java](#srcmainjavabrcomvestrispharmacologydomainmodelenumsunidadedosejava)
    - [ViaAdministracao.java](#srcmainjavabrcomvestrispharmacologydomainmodelenumsviaadministracaojava)
  - [src\main\java\br\com\vestris\pharmacology\domain\repository](#srcmainjavabrcomvestrispharmacologydomainrepository)
    - [RepositorioContraindicacao.java](#srcmainjavabrcomvestrispharmacologydomainrepositoryrepositoriocontraindicacaojava)
    - [RepositorioDoseReferencia.java](#srcmainjavabrcomvestrispharmacologydomainrepositoryrepositoriodosereferenciajava)
    - [RepositorioMedicamento.java](#srcmainjavabrcomvestrispharmacologydomainrepositoryrepositoriomedicamentojava)
    - [RepositorioPrincipioAtivo.java](#srcmainjavabrcomvestrispharmacologydomainrepositoryrepositorioprincipioativojava)
  - [src\main\java\br\com\vestris\pharmacology\interfaces\delegate](#srcmainjavabrcomvestrispharmacologyinterfacesdelegate)
    - [ApiDelegateContraindicacoes.java](#srcmainjavabrcomvestrispharmacologyinterfacesdelegateapidelegatecontraindicacoesjava)
    - [ApiDelegateMedicamentos.java](#srcmainjavabrcomvestrispharmacologyinterfacesdelegateapidelegatemedicamentosjava)
    - [ApiDelegatePrincipioAtivo.java](#srcmainjavabrcomvestrispharmacologyinterfacesdelegateapidelegateprincipioativojava)
    - [ApiDelegateSegurancaClinica.java](#srcmainjavabrcomvestrispharmacologyinterfacesdelegateapidelegatesegurancaclinicajava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [contraindicacoes.yml](#srcmainresourcesswaggerpathscontraindicacoesyml)
    - [medicamentos.yml](#srcmainresourcesswaggerpathsmedicamentosyml)
    - [principios-ativos.yml](#srcmainresourcesswaggerpathsprincipios-ativosyml)

- Projeto: vestris-portal
  - [.](#)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\portal](#srcmainjavabrcomvestrisportal)
    - [VestrisApplication.java](#srcmainjavabrcomvestrisportalvestrisapplicationjava)
  - [src\main\java\br\com\vestris\portal\config](#srcmainjavabrcomvestrisportalconfig)
    - [JacksonConfiguration.java](#srcmainjavabrcomvestrisportalconfigjacksonconfigurationjava)
    - [SecurityConfig.java](#srcmainjavabrcomvestrisportalconfigsecurityconfigjava)
    - [WebConfig.java](#srcmainjavabrcomvestrisportalconfigwebconfigjava)
  - [src\main\resources](#srcmainresources)
    - [application.properties](#srcmainresourcesapplicationproperties)
    - [schema.sql](#srcmainresourcesschemasql)

- Projeto: vestris-reference
  - [.](#)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\reference\application](#srcmainjavabrcomvestrisreferenceapplication)
    - [ServiceReferencia.java](#srcmainjavabrcomvestrisreferenceapplicationservicereferenciajava)
  - [src\main\java\br\com\vestris\reference\domain\model](#srcmainjavabrcomvestrisreferencedomainmodel)
    - [ReferenciaBibliografica.java](#srcmainjavabrcomvestrisreferencedomainmodelreferenciabibliograficajava)
  - [src\main\java\br\com\vestris\reference\domain\repository](#srcmainjavabrcomvestrisreferencedomainrepository)
    - [RepositorioReferencia.java](#srcmainjavabrcomvestrisreferencedomainrepositoryrepositorioreferenciajava)
  - [src\main\java\br\com\vestris\reference\interfaces\delegate](#srcmainjavabrcomvestrisreferenceinterfacesdelegate)
    - [ApiDelegateReferencias.java](#srcmainjavabrcomvestrisreferenceinterfacesdelegateapidelegatereferenciasjava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [referencias.yml](#srcmainresourcesswaggerpathsreferenciasyml)

- Projeto: vestris-saas
  - [.](#)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\saas\application](#srcmainjavabrcomvestrissaasapplication)
    - [ServiceAssinatura.java](#srcmainjavabrcomvestrissaasapplicationserviceassinaturajava)
    - [ServiceCadastroSaas.java](#srcmainjavabrcomvestrissaasapplicationservicecadastrosaasjava)
    - [ServicePlano.java](#srcmainjavabrcomvestrissaasapplicationserviceplanojava)
  - [src\main\java\br\com\vestris\saas\domain\model](#srcmainjavabrcomvestrissaasdomainmodel)
    - [Assinatura.java](#srcmainjavabrcomvestrissaasdomainmodelassinaturajava)
    - [Plano.java](#srcmainjavabrcomvestrissaasdomainmodelplanojava)
  - [src\main\java\br\com\vestris\saas\domain\repository](#srcmainjavabrcomvestrissaasdomainrepository)
    - [RepositorioAssinatura.java](#srcmainjavabrcomvestrissaasdomainrepositoryrepositorioassinaturajava)
    - [RepositorioPlano.java](#srcmainjavabrcomvestrissaasdomainrepositoryrepositorioplanojava)
  - [src\main\java\br\com\vestris\saas\interfaces\api](#srcmainjavabrcomvestrissaasinterfacesapi)
    - [WebhookController.java](#srcmainjavabrcomvestrissaasinterfacesapiwebhookcontrollerjava)
  - [src\main\java\br\com\vestris\saas\interfaces\delegate](#srcmainjavabrcomvestrissaasinterfacesdelegate)
    - [ApiDelegateAssinaturas.java](#srcmainjavabrcomvestrissaasinterfacesdelegateapidelegateassinaturasjava)
    - [ApiDelegateCadastroSaas.java](#srcmainjavabrcomvestrissaasinterfacesdelegateapidelegatecadastrosaasjava)
    - [ApiDelegatePlanos.java](#srcmainjavabrcomvestrissaasinterfacesdelegateapidelegateplanosjava)
  - [src\main\java\br\com\vestris\saas\interfaces\dto](#srcmainjavabrcomvestrissaasinterfacesdto)
    - [WebhookMP.java](#srcmainjavabrcomvestrissaasinterfacesdtowebhookmpjava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [assinaturas.yml](#srcmainresourcesswaggerpathsassinaturasyml)
    - [auth-public.yml](#srcmainresourcesswaggerpathsauth-publicyml)
    - [planos.yml](#srcmainresourcesswaggerpathsplanosyml)

- Projeto: vestris-shared
  - [.](#)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\shared\domain\exceptions](#srcmainjavabrcomvestrisshareddomainexceptions)
    - [ExcecaoRegraNegocio.java](#srcmainjavabrcomvestrisshareddomainexceptionsexcecaoregranegociojava)
    - [ExceptionRecursoNaoEncontrado.java](#srcmainjavabrcomvestrisshareddomainexceptionsexceptionrecursonaoencontradojava)
  - [src\main\java\br\com\vestris\shared\domain\model](#srcmainjavabrcomvestrisshareddomainmodel)
    - [EntidadeBase.java](#srcmainjavabrcomvestrisshareddomainmodelentidadebasejava)
  - [src\main\java\br\com\vestris\shared\infrastructure\dto](#srcmainjavabrcomvestrissharedinfrastructuredto)
    - [RespostaApi.java](#srcmainjavabrcomvestrissharedinfrastructuredtorespostaapijava)
  - [src\main\java\br\com\vestris\shared\infrastructure\handler](#srcmainjavabrcomvestrissharedinfrastructurehandler)
    - [ManipuladorGlobalDeExceptions.java](#srcmainjavabrcomvestrissharedinfrastructurehandlermanipuladorglobaldeexceptionsjava)
  - [src\main\java\br\com\vestris\shared\infrastructure\helper](#srcmainjavabrcomvestrissharedinfrastructurehelper)
    - [HelperAuditoria.java](#srcmainjavabrcomvestrissharedinfrastructurehelperhelperauditoriajava)

- Projeto: vestris-species
  - [.](#)
    - [.openapi-generator-ignore](#openapi-generator-ignore)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\species\application](#srcmainjavabrcomvestrisspeciesapplication)
    - [ServiceEspecie.java](#srcmainjavabrcomvestrisspeciesapplicationserviceespeciejava)
    - [ServiceModeloExame.java](#srcmainjavabrcomvestrisspeciesapplicationservicemodeloexamejava)
  - [src\main\java\br\com\vestris\species\domain](#srcmainjavabrcomvestrisspeciesdomain)
    - [Especie.java](#srcmainjavabrcomvestrisspeciesdomainespeciejava)
    - [ModeloExameFisico.java](#srcmainjavabrcomvestrisspeciesdomainmodeloexamefisicojava)
  - [src\main\java\br\com\vestris\species\domain\repository](#srcmainjavabrcomvestrisspeciesdomainrepository)
    - [RepositorioEspecie.java](#srcmainjavabrcomvestrisspeciesdomainrepositoryrepositorioespeciejava)
    - [RepositorioModeloExame.java](#srcmainjavabrcomvestrisspeciesdomainrepositoryrepositoriomodeloexamejava)
  - [src\main\java\br\com\vestris\species\interfaces\delegate](#srcmainjavabrcomvestrisspeciesinterfacesdelegate)
    - [ApiDelegateEspecies.java](#srcmainjavabrcomvestrisspeciesinterfacesdelegateapidelegateespeciesjava)
    - [ApiDelegateModeloExame.java](#srcmainjavabrcomvestrisspeciesinterfacesdelegateapidelegatemodeloexamejava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [especies.yml](#srcmainresourcesswaggerpathsespeciesyml)
    - [exames-fisicos.yml](#srcmainresourcesswaggerpathsexames-fisicosyml)

- Projeto: vestris-user
  - [.](#)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\user\application](#srcmainjavabrcomvestrisuserapplication)
    - [ServiceAuditoria.java](#srcmainjavabrcomvestrisuserapplicationserviceauditoriajava)
    - [ServiceAuth.java](#srcmainjavabrcomvestrisuserapplicationserviceauthjava)
    - [ServiceClinica.java](#srcmainjavabrcomvestrisuserapplicationserviceclinicajava)
    - [ServiceUsuario.java](#srcmainjavabrcomvestrisuserapplicationserviceusuariojava)
    - [ValidadorPlanoService.java](#srcmainjavabrcomvestrisuserapplicationvalidadorplanoservicejava)
  - [src\main\java\br\com\vestris\user\domain\model](#srcmainjavabrcomvestrisuserdomainmodel)
    - [AcaoAuditoria.java](#srcmainjavabrcomvestrisuserdomainmodelacaoauditoriajava)
    - [AcaoAuditoriaConverter.java](#srcmainjavabrcomvestrisuserdomainmodelacaoauditoriaconverterjava)
    - [Auditoria.java](#srcmainjavabrcomvestrisuserdomainmodelauditoriajava)
    - [Clinica.java](#srcmainjavabrcomvestrisuserdomainmodelclinicajava)
    - [EntidadeAuditoria.java](#srcmainjavabrcomvestrisuserdomainmodelentidadeauditoriajava)
    - [Perfil.java](#srcmainjavabrcomvestrisuserdomainmodelperfiljava)
    - [Usuario.java](#srcmainjavabrcomvestrisuserdomainmodelusuariojava)
  - [src\main\java\br\com\vestris\user\domain\repository](#srcmainjavabrcomvestrisuserdomainrepository)
    - [RepositorioAuditoria.java](#srcmainjavabrcomvestrisuserdomainrepositoryrepositorioauditoriajava)
    - [RepositorioClinica.java](#srcmainjavabrcomvestrisuserdomainrepositoryrepositorioclinicajava)
    - [RepositorioUsuario.java](#srcmainjavabrcomvestrisuserdomainrepositoryrepositoriousuariojava)
  - [src\main\java\br\com\vestris\user\infrastructure\config](#srcmainjavabrcomvestrisuserinfrastructureconfig)
    - [ConfiguracaoSeguranca.java](#srcmainjavabrcomvestrisuserinfrastructureconfigconfiguracaosegurancajava)
  - [src\main\java\br\com\vestris\user\infrastructure\security](#srcmainjavabrcomvestrisuserinfrastructuresecurity)
    - [ServicoToken.java](#srcmainjavabrcomvestrisuserinfrastructuresecurityservicotokenjava)
  - [src\main\java\br\com\vestris\user\interfaces\delegate](#srcmainjavabrcomvestrisuserinterfacesdelegate)
    - [ApiDelegateAdminGlobal.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateadminglobaljava)
    - [ApiDelegateAuditoria.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateauditoriajava)
    - [ApiDelegateAuth.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateauthjava)
    - [ApiDelegateClinica.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateclinicajava)
    - [ApiDelegateUsuarios.java](#srcmainjavabrcomvestrisuserinterfacesdelegateapidelegateusuariosjava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [admin.yml](#srcmainresourcesswaggerpathsadminyml)
    - [auditoria.yml](#srcmainresourcesswaggerpathsauditoriayml)
    - [clinica.yml](#srcmainresourcesswaggerpathsclinicayml)
    - [login.yml](#srcmainresourcesswaggerpathsloginyml)
    - [registro.yml](#srcmainresourcesswaggerpathsregistroyml)
    - [usuarios.yml](#srcmainresourcesswaggerpathsusuariosyml)

- Projeto: vestris-vaccination
  - [.](#)
    - [pom.xml](#pomxml)
  - [src\main\java\br\com\vestris\vaccination\application](#srcmainjavabrcomvestrisvaccinationapplication)
    - [ServiceAplicacaoVacina.java](#srcmainjavabrcomvestrisvaccinationapplicationserviceaplicacaovacinajava)
    - [ServiceProtocoloVacinacao.java](#srcmainjavabrcomvestrisvaccinationapplicationserviceprotocolovacinacaojava)
    - [ServiceVacinacao.java](#srcmainjavabrcomvestrisvaccinationapplicationservicevacinacaojava)
  - [src\main\java\br\com\vestris\vaccination\domain\model](#srcmainjavabrcomvestrisvaccinationdomainmodel)
    - [AplicacaoVacina.java](#srcmainjavabrcomvestrisvaccinationdomainmodelaplicacaovacinajava)
    - [ProtocoloVacinacao.java](#srcmainjavabrcomvestrisvaccinationdomainmodelprotocolovacinacaojava)
    - [Vacina.java](#srcmainjavabrcomvestrisvaccinationdomainmodelvacinajava)
  - [src\main\java\br\com\vestris\vaccination\domain\repository](#srcmainjavabrcomvestrisvaccinationdomainrepository)
    - [RepositorioAplicacaoVacina.java](#srcmainjavabrcomvestrisvaccinationdomainrepositoryrepositorioaplicacaovacinajava)
    - [RepositorioProtocoloVacinacao.java](#srcmainjavabrcomvestrisvaccinationdomainrepositoryrepositorioprotocolovacinacaojava)
    - [RepositorioVacina.java](#srcmainjavabrcomvestrisvaccinationdomainrepositoryrepositoriovacinajava)
  - [src\main\java\br\com\vestris\vaccination\interfaces\delegate](#srcmainjavabrcomvestrisvaccinationinterfacesdelegate)
    - [ApiDelegateAplicacaoVacina.java](#srcmainjavabrcomvestrisvaccinationinterfacesdelegateapidelegateaplicacaovacinajava)
    - [ApiDelegateProtocoloVacinal.java](#srcmainjavabrcomvestrisvaccinationinterfacesdelegateapidelegateprotocolovacinaljava)
    - [ApiDelegateVacinas.java](#srcmainjavabrcomvestrisvaccinationinterfacesdelegateapidelegatevacinasjava)
  - [src\main\resources\swagger](#srcmainresourcesswagger)
    - [openapi.yml](#srcmainresourcesswaggeropenapiyml)
  - [src\main\resources\swagger\components](#srcmainresourcesswaggercomponents)
    - [schemas.yml](#srcmainresourcesswaggercomponentsschemasyml)
  - [src\main\resources\swagger\paths](#srcmainresourcesswaggerpaths)
    - [protocolos-vacinais.yml](#srcmainresourcesswaggerpathsprotocolos-vacinaisyml)
    - [vacinacao-paciente.yml](#srcmainresourcesswaggerpathsvacinacao-pacienteyml)
    - [vacinas.yml](#srcmainresourcesswaggerpathsvacinasyml)


# Projeto: infra

## .

### init-schemas.sql

```sql
-- init-schemas.sql

-- Conceder permissões (se necessário em prod, aqui é local)
GRANT ALL PRIVILEGES ON DATABASE vestris_db TO vestris_user;
```


---

# Projeto: vestris-clinical

## .

### .openapi-generator-ignore

```text
# .openapi-generator-ignore
# Ignora todos os testes gerados automaticamente
src/test/**

# Ignora arquivos de configuração inúteis
pom.xml
README.md
.gitignore
```

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-clinical</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-species</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-pharmacology</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Para validar Referências (se já não tiver) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-reference</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Swagger Dependencies -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <!-- Fix Test Generation Error -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin do OpenAPI Generator -->
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <!-- Seus caminhos (mantenha como estão) -->
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <ignoreFileOverride>${project.basedir}/.openapi-generator-ignore</ignoreFileOverride>

                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.clinical.interfaces.api</apiPackage> <!-- (Mude para 'clinical' no outro pom) -->
                            <modelPackage>br.com.vestris.clinical.interfaces.dto</modelPackage> <!-- (Mude para 'clinical' no outro pom) -->

                            <!-- Desliga testes de API e Modelos -->
                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>

                            <!-- O SEGREDO ESTÁ AQUI: -->
                            <!-- Dizemos explicitamente: "Gere APENAS o ApiUtil.java. Não gere Application.java nem Tests.java" -->
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## src\main\java\br\com\vestris\clinical\application

### ServiceCalculadora.java

```java
// src\main\java\br\com\vestris\clinical\application\ServiceCalculadora.java
package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.CalculoResultadoDTO;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.pharmacology.application.ServiceDoseReferencia;
import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ServiceCalculadora {
    private final ServiceProtocolo servicoProtocolo;
    private final ServiceFarmacologia servicoFarmacologia;
    private final ServiceReferencia servicoReferencia;
    private final ServiceDoseReferencia serviceDoseReferencia;
    private final ServiceAuditoria serviceAuditoria;

    // --- CÁLCULOS MATEMÁTICOS SIMPLES ---
    public CalculoResultadoDTO calcularMatematico(
            String nomeMedicamento, Double concentracao, Double pesoKg,
            Double doseInformada, String via, String frequencia, String duracao
    ) {
        CalculoResultadoDTO resultado = new CalculoResultadoDTO();
        resultado.setMedicamentoNome(nomeMedicamento != null ? nomeMedicamento : "Medicamento Manual");
        resultado.setPesoKg(pesoKg);
        resultado.setConcentracao(concentracao != null ? concentracao + " mg/ml" : "N/A");
        resultado.setVia(via);
        resultado.setFrequencia(frequencia);
        resultado.setDuracao(duracao);

        // Cálculo da Dose Total em MG
        Double doseTotalMg = (doseInformada != null && pesoKg != null) ? doseInformada * pesoKg : null;

        // Cálculo do Volume em ML
        Double volumeMl = null;
        if (doseTotalMg != null && concentracao != null && concentracao > 0) {
            volumeMl = doseTotalMg / concentracao;
        }

        // Popula DTO
        resultado.setDoseMinMg(arredondar(doseTotalMg)); // Nesse caso min=max
        resultado.setDoseMaxMg(arredondar(doseTotalMg));
        resultado.setVolMinMl(arredondar(volumeMl));
        resultado.setVolMaxMl(arredondar(volumeMl));

        resultado.setDoseCalculadaMg(arredondar(doseTotalMg));
        resultado.setVolumeCalculadoMl(arredondar(volumeMl));

        // Metadados
        resultado.setStatusSeguranca("NAO_VALIDADO");
        resultado.setMensagemSeguranca("Cálculo livre: responsabilidade do veterinário.");

        return resultado;
    }

    /**
     * Valida e calcula uma dose (Modo Híbrido: Catálogo ou Manual)
     */
    public CalculoResultadoDTO validarDose(
            UUID medicamentoId, UUID especieId, UUID doencaId, UUID clinicaId, UUID usuarioId,
            Double pesoKg, Double doseInformadaMgKg, String unidade, String via,
            Double concentracaoManual // <--- NOVO: Suporte a modo livre
    ) {
        CalculoResultadoDTO resultado = new CalculoResultadoDTO();
        resultado.setPesoKg(pesoKg);

        Double concentracaoValor = null;
        String nomeMedicamento = "Medicamento Manual";
        String textoConcentracao = null;

        // --- 1. RESOLVER MEDICAMENTO E CONCENTRAÇÃO ---

        // CENÁRIO A: MODO CATÁLOGO (Tem ID do medicamento)
        if (medicamentoId != null) {
            Medicamento med = servicoFarmacologia.buscarEntidadePorId(medicamentoId);
            nomeMedicamento = med.getNome();
            textoConcentracao = med.getConcentracao();
            concentracaoValor = extrairValorConcentracao(med.getConcentracao());
        }
        // CENÁRIO B: MODO MANUAL (Sem ID, usa o valor digitado)
        else if (concentracaoManual != null && concentracaoManual > 0) {
            concentracaoValor = concentracaoManual;
            textoConcentracao = concentracaoManual + " mg/ml";
        }

        resultado.setMedicamentoNome(nomeMedicamento);
        resultado.setConcentracao(textoConcentracao);

        // --- 2. CÁLCULO MATEMÁTICO (VOLUME) ---
        Double doseTotalMg = doseInformadaMgKg * pesoKg;
        Double volMl = null;

        if (concentracaoValor != null && concentracaoValor > 0) {
            volMl = doseTotalMg / concentracaoValor;
        }

        // Popula resultado matemático
        resultado.setDoseMinMg(arredondar(doseTotalMg));
        resultado.setDoseMaxMg(arredondar(doseTotalMg)); // É pontual
        resultado.setVolMinMl(arredondar(volMl));
        resultado.setVolMaxMl(arredondar(volMl));

        // --- 3. VALIDAÇÃO DE SEGURANÇA (APENAS MODO CATÁLOGO) ---

        // Se não tiver ID de medicamento e espécie, ou a unidade for estranha, não validamos segurança
        if (medicamentoId == null || especieId == null || !"MG_KG".equalsIgnoreCase(unidade)) {
            resultado.setStatusSeguranca(medicamentoId == null ? "SEM_REFERENCIA" : "NAO_VALIDADO");
            resultado.setMensagemSeguranca(medicamentoId == null
                    ? "Cálculo manual (sem validação de segurança)."
                    : "Validação indisponível para esta unidade/configuração.");
            return resultado;
        }

        // Busca Referência Científica no Banco
        DoseReferencia ref = serviceDoseReferencia.buscarMelhorReferencia(medicamentoId, especieId, doencaId, via, clinicaId);

        if (ref == null) {
            resultado.setStatusSeguranca("SEM_REFERENCIA");
            resultado.setMensagemSeguranca("Nenhuma referência encontrada para esta combinação.");
            return resultado;
        }

        // Comparação (Usando BigDecimal para precisão)
        BigDecimal doseUser = BigDecimal.valueOf(doseInformadaMgKg);
        String status = "SEGURO";
        String msg = "Dose dentro da faixa de referência.";

        if (ref.getDoseMin() != null && doseUser.compareTo(ref.getDoseMin()) < 0) {
            status = "SUBDOSE";
            msg = "A dose informada (" + doseInformadaMgKg + ") está ABAIXO do mínimo recomendado (" + ref.getDoseMin() + " mg/kg).";
        } else if (ref.getDoseMax() != null && doseUser.compareTo(ref.getDoseMax()) > 0) {
            status = "SUPERDOSE";
            msg = "A dose informada (" + doseInformadaMgKg + ") está ACIMA do máximo recomendado (" + ref.getDoseMax() + " mg/kg).";
        }

        // Preenche DTO de Retorno com dados de segurança
        resultado.setStatusSeguranca(status);
        resultado.setMensagemSeguranca(msg);
        resultado.setRefMin(ref.getDoseMin() != null ? ref.getDoseMin().doubleValue() : null);
        resultado.setRefMax(ref.getDoseMax() != null ? ref.getDoseMax().doubleValue() : null);
        resultado.setRefFonte(ref.getFonteBibliografica() + " (" + ref.getOrigem() + ")");

        // --- 4. AUDITORIA (Se houver risco e usuário identificado) ---
        if (usuarioId != null && ("SUBDOSE".equals(status) || "SUPERDOSE".equals(status))) {
            try {
                // Tenta gravar log de alerta
                // Nota: Usando 'RECURSO_CRIADO' ou similar caso o enum ALERTA_DOSE não tenha sido criado ainda.
                // Idealmente crie AcaoAuditoria.ALERTA_DOSE
                serviceAuditoria.registrar(
                        clinicaId, usuarioId,
                        AcaoAuditoria.RECURSO_CRIADO, // Ajuste para o Enum correto se tiver criado
                        EntidadeAuditoria.RECEITA, // Entidade contexto
                        medicamentoId,
                        "Alerta de " + status + " disparado: " + doseInformadaMgKg + " mg/kg"
                );
            } catch (Exception e) {
                // Log falhou, não trava o cálculo
            }
        }

        return resultado;
    }

    /**
     * Cálculo baseado em Protocolo (Legado/Padrão para a tela de Protocolos)
     */
    public CalculoResultadoDTO calcular(UUID protocoloId, UUID medicamentoId, Double pesoInput, String unidadePeso) {
        java.util.List<String> alertas = new java.util.ArrayList<>();
        Protocolo protocolo = servicoProtocolo.buscarPorId(protocoloId);

        Dosagem regra = protocolo.getDosagens().stream()
                .filter(d -> d.getMedicamentoId().equals(medicamentoId))
                .findFirst()
                .orElseThrow(() -> new ExcecaoRegraNegocio("Este medicamento não pertence ao protocolo selecionado."));

        Medicamento medicamento = servicoFarmacologia.buscarEntidadePorId(medicamentoId);

        String citacaoReferencia;
        if (protocolo.getReferenciaId() != null) {
            citacaoReferencia = servicoReferencia.buscarCitacaoPorId(protocolo.getReferenciaId());
        } else {
            citacaoReferencia = protocolo.getReferenciaTexto() != null ? protocolo.getReferenciaTexto() : "Referência não informada";
        }

        Double pesoKg = "G".equalsIgnoreCase(unidadePeso) ? pesoInput / 1000.0 : pesoInput;

        Double doseMinMg = regra.getDoseMinima() * pesoKg;
        Double doseMaxMg = (regra.getDoseMaxima() != null) ? regra.getDoseMaxima() * pesoKg : doseMinMg;

        Double volMinMl = null;
        Double volMaxMl = null;
        Double concentracaoValor = extrairValorConcentracao(medicamento.getConcentracao());

        if (concentracaoValor != null && concentracaoValor > 0) {
            volMinMl = doseMinMg / concentracaoValor;
            volMaxMl = doseMaxMg / concentracaoValor;
        }

        return CalculoResultadoDTO.builder()
                .protocoloTitulo(protocolo.getTitulo())
                .medicamentoNome(medicamento.getNome())
                .referencia(citacaoReferencia)
                .pesoKg(pesoKg)
                .doseMinMg(arredondar(doseMinMg))
                .doseMaxMg(arredondar(doseMaxMg))
                .volMinMl(arredondar(volMinMl))
                .volMaxMl(arredondar(volMaxMl))
                .concentracao(medicamento.getConcentracao())
                .frequencia(regra.getFrequencia())
                .via(regra.getVia())
                .duracao(regra.getDuracao())
                .alertas(alertas)
                .statusSeguranca("SEGURO") // Default p/ protocolo fixo
                .mensagemSeguranca("Conforme protocolo.")
                .build();
    }

    private Double arredondar(Double valor) {
        if (valor == null) return null;
        BigDecimal bd = BigDecimal.valueOf(valor);
        return bd.setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    private Double extrairValorConcentracao(String textoConcentracao) {
        if (textoConcentracao == null || textoConcentracao.isBlank()) return null;
        try {
            Matcher matcher = Pattern.compile("([0-9]+([.,][0-9]+)?)").matcher(textoConcentracao);
            if (matcher.find()) {
                String numeroStr = matcher.group(1).replace(",", ".");
                return Double.parseDouble(numeroStr);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}

```

### ServiceDoenca.java

```java
// src\main\java\br\com\vestris\clinical\application\ServiceDoenca.java
package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.repository.RepositorioDoenca;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceDoenca {

    private final RepositorioDoenca repositorio;
    private final ServiceEspecie serviceEspecie; // <--- INJEÇÃO DO MÓDULO VIZINHO

    public Doenca criar(Doenca novaDoenca) {
        // 1. VALIDAÇÃO DE INTEGRIDADE: A Espécie existe?
        if (!serviceEspecie.existePorId(novaDoenca.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Não foi possível cadastrar a doença. A espécie informada não existe.");
        }

        // 2. Validação de Duplicidade
        if (repositorio.existsByNomeAndEspecieId(novaDoenca.getNome(), novaDoenca.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Esta doença já está cadastrada para esta espécie.");
        }

        return repositorio.save(novaDoenca);
    }

    public List<Doenca> listarTodas() {
        return repositorio.findAll();
    }

    public List<Doenca> listarPorEspecie(UUID especieId) {
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }
        // 2. Se existe, busca as doenças
        return repositorio.findAllByEspecieId(especieId);
    }

    public Doenca buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", id.toString()));
    }

    public Doenca atualizar(UUID id, Doenca dados) {
        Doenca existente = buscarPorId(id);

        // Regra: Se mudar o nome, verificar duplicidade de novo
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) &&
                repositorio.existsByNomeAndEspecieId(dados.getNome(), existente.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Já existe outra doença com este nome para esta espécie.");
        }

        existente.setNome(dados.getNome());
        existente.setNomeCientifico(dados.getNomeCientifico());
        existente.setSintomas(dados.getSintomas());
        // Nota: Geralmente não deixamos mudar a Espécie (especieId) de uma doença existente,
        // pois quebraria os protocolos. Se quiser permitir, tem que validar se a nova espécie existe.

        return repositorio.save(existente);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover esta doença pois existem protocolos vinculados a ela.");
        }
    }
}

```

### ServiceProtocolo.java

```java
// src\main\java\br\com\vestris\clinical\application\ServiceProtocolo.java
package br.com.vestris.clinical.application;

import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.domain.model.ProtocoloCompletoDTO;
import br.com.vestris.clinical.domain.repository.RepositorioDoenca;
import br.com.vestris.clinical.domain.repository.RepositorioProtocolo;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceProtocolo {
    private final RepositorioProtocolo repoProtocolo;
    private final RepositorioDoenca repoDoenca;
    private final ServiceFarmacologia serviceFarmacologia;
    private final ServiceReferencia serviceReferencia;
    private final ServiceAuditoria servicoAuditoria;
    private final ServiceUsuario servicoUsuario;
    private final HelperAuditoria helperAuditoria;

    @Transactional
    public Protocolo criar(Protocolo protocolo, UUID doencaIdInput, List<Dosagem> itens) {

        // 1. Lógica Híbrida para Doença
        if (doencaIdInput != null) {
            Doenca doenca = repoDoenca.findById(doencaIdInput)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaIdInput.toString()));
            protocolo.setDoenca(doenca);
        } else if (protocolo.getDoencaTextoLivre() == null || protocolo.getDoencaTextoLivre().isBlank()) {
            throw new ExcecaoRegraNegocio("É necessário informar uma doença (selecione da lista ou digite o nome).");
        }

        // 2. Validação de Origem e Referência
        if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.OFICIAL) {
            if (protocolo.getReferenciaId() == null) {
                throw new ExcecaoRegraNegocio("Protocolos oficiais exigem ID de referência.");
            }
            if (!serviceReferencia.existePorId(protocolo.getReferenciaId())) {
                throw new ExcecaoRegraNegocio("Referência ID inválida.");
            }
        } else {
            // Se for próprio, exige autor
            if (protocolo.getAutorId() == null) {
                throw new ExcecaoRegraNegocio("Protocolos próprios exigem um autor vinculado.");
            }
            // Se não tem nem ID nem Texto de referência
            if (protocolo.getReferenciaId() == null && (protocolo.getReferenciaTexto() == null || protocolo.getReferenciaTexto().isBlank())) {
                protocolo.setReferenciaTexto("Autoria Própria / Experiência Clínica"); // Fallback
            }
        }

        // 3. Processar Dosagens Híbridas
        if (itens != null && !itens.isEmpty()) {
            for (Dosagem item : itens) {
                if (item.getMedicamentoId() != null) {
                    // Se mandou ID, valida
                    if (!serviceFarmacologia.existeMedicamentoPorId(item.getMedicamentoId())) {
                        throw new ExcecaoRegraNegocio("Medicamento ID inválido: " + item.getMedicamentoId());
                    }
                } else if (item.getMedicamentoTextoLivre() == null || item.getMedicamentoTextoLivre().isBlank()) {
                    throw new ExcecaoRegraNegocio("Todo item da prescrição precisa de um medicamento (ID ou Nome).");
                }
                protocolo.adicionarDosagem(item);
            }
        } else {
            throw new ExcecaoRegraNegocio("Adicione pelo menos um item ao protocolo.");
        }

        Protocolo salvo = repoProtocolo.save(protocolo);

        // --- LOG DE AUDITORIA ---
        try {
            UUID clinicaId = null;
            if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                protocolo.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                Usuario autor = servicoUsuario.buscarPorId(protocolo.getAutorId());
                if (autor.getClinica() != null) {
                    clinicaId = autor.getClinica().getId();
                }
            }

            if (clinicaId != null) {
                var metadados = helperAuditoria.montarMetadadosProtocolo(
                    salvo.getTitulo(),
                    protocolo.getOrigem().name(),
                    protocolo.getAutorId(),
                    "doenca", (protocolo.getDoenca() != null ? protocolo.getDoenca().getNome() : protocolo.getDoencaTextoLivre()),
                    "totalDosagens", String.valueOf(salvo.getDosagens().size())
                );
                servicoAuditoria.registrar(
                    clinicaId,
                    protocolo.getAutorId(),
                    AcaoAuditoria.PROTOCOLO_CRIADO,
                    EntidadeAuditoria.PROTOCOLO,
                    salvo.getId(),
                    "Protocolo criado: " + salvo.getTitulo(),
                    metadados
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao auditar criação de protocolo: " + e.getMessage());
        }
        // -------------------------

        return salvo;
    }

    public List<Protocolo> listarPorDoenca(UUID doencaId) {
        // Se vier ID, busca exato.
        // TODO: Futuramente implementar busca por texto livre da doença também
        if (!repoDoenca.existsById(doencaId)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString());
        }
        return repoProtocolo.findByDoencaId(doencaId);
    }

    // Lista TODOS (para o filtro "TODAS" funcionar no front, se desejar)
    public List<Protocolo> listarTodos() {
        return repoProtocolo.findAll();
    }

    public Protocolo buscarPorId(UUID id) {
        return repoProtocolo.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Protocolo", id.toString()));
    }

    public ProtocoloCompletoDTO montarProtocoloCompleto(UUID especieId, UUID doencaId) {
        Doenca doenca = repoDoenca.findById(doencaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString()));

        if (!doenca.getEspecieId().equals(especieId)) {
            throw new ExcecaoRegraNegocio("Doença não pertence à espécie.");
        }

        List<Protocolo> protocolos = repoProtocolo.findByDoencaId(doencaId);

        return ProtocoloCompletoDTO.builder()
                .doenca(doenca)
                .protocolos(protocolos)
                .build();
    }

    public List<Protocolo> listarAcessiveis(UUID doencaId, UUID clinicaId, UUID usuarioId) {
        if (!repoDoenca.existsById(doencaId)) {
            throw new ExceptionRecursoNaoEncontrado("Doença", doencaId.toString());
        }

        // Se não vier clinicaId ou usuarioId (ex: acesso público ou erro),
        // a query JPQL lida com null, mas idealmente deveríamos garantir que não venham nulos do controller.
        // No caso de null, a comparação (p.clinicaId = null) retornaria falso no SQL padrão para valores preenchidos.

        return repoProtocolo.listarAcessiveis(doencaId, clinicaId, usuarioId);
    }

    @Transactional
    public Protocolo atualizar(UUID id, Protocolo dados, List<Dosagem> novasDosagens) {
        Protocolo existente = buscarPorId(id);

        existente.setTitulo(dados.getTitulo());
        existente.setObservacoes(dados.getObservacoes());
        existente.setReferenciaTexto(dados.getReferenciaTexto());
        existente.setReferenciaId(dados.getReferenciaId());

        // Atualiza campos de doença se necessário (geralmente não muda, mas ok)
        if(dados.getDoenca() != null) existente.setDoenca(dados.getDoenca());
        if(dados.getDoencaTextoLivre() != null) existente.setDoencaTextoLivre(dados.getDoencaTextoLivre());

        existente.getDosagens().clear();
        if (novasDosagens != null) {
            for (Dosagem d : novasDosagens) {
                // Mesma validação híbrida
                if (d.getMedicamentoId() != null) {
                    if (!serviceFarmacologia.existeMedicamentoPorId(d.getMedicamentoId())) {
                        throw new ExcecaoRegraNegocio("Medicamento inválido na edição.");
                    }
                }
                existente.adicionarDosagem(d);
            }
        }
        Protocolo salvo = repoProtocolo.save(existente);

        // --- LOG DE AUDITORIA ---
        try {
            UUID clinicaId = null;
            if (existente.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                existente.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                Usuario autor = servicoUsuario.buscarPorId(existente.getAutorId());
                if (autor.getClinica() != null) {
                    clinicaId = autor.getClinica().getId();
                }
            }

            if (clinicaId != null) {
                var metadados = helperAuditoria.montarMetadadosProtocolo(
                    salvo.getTitulo(),
                    existente.getOrigem().name(),
                    existente.getAutorId(),
                    "totalDosagens", String.valueOf(salvo.getDosagens().size())
                );
                servicoAuditoria.registrar(
                    clinicaId,
                    existente.getAutorId(),
                    AcaoAuditoria.PROTOCOLO_EDITADO,
                    EntidadeAuditoria.PROTOCOLO,
                    salvo.getId(),
                    "Protocolo atualizado: " + salvo.getTitulo(),
                    metadados
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao auditar edição de protocolo: " + e.getMessage());
        }
        // -------------------------

        return salvo;
    }

    public void deletar(UUID id) {
        if (!repoProtocolo.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Protocolo", id.toString());
        }

        Protocolo protocolo = buscarPorId(id);

        try {
            repoProtocolo.deleteById(id);

            // --- LOG DE AUDITORIA ---
            try {
                UUID clinicaId = null;
                if (protocolo.getOrigem() == Protocolo.OrigemProtocolo.PROPRIO ||
                    protocolo.getOrigem() == Protocolo.OrigemProtocolo.INSTITUCIONAL) {
                    Usuario autor = servicoUsuario.buscarPorId(protocolo.getAutorId());
                    if (autor.getClinica() != null) {
                        clinicaId = autor.getClinica().getId();
                    }
                }

                if (clinicaId != null) {
                    var metadados = helperAuditoria.montarMetadadosProtocolo(
                        protocolo.getTitulo(),
                        protocolo.getOrigem().name(),
                        protocolo.getAutorId()
                    );
                    servicoAuditoria.registrar(
                        clinicaId,
                        protocolo.getAutorId(),
                        AcaoAuditoria.PROTOCOLO_REMOVIDO,
                        EntidadeAuditoria.PROTOCOLO,
                        id,
                        "Protocolo deletado: " + protocolo.getTitulo(),
                        metadados
                    );
                }
            } catch (Exception e) {
                System.err.println("Erro ao auditar deleção de protocolo: " + e.getMessage());
            }
            // -------------------------
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Protocolo em uso.");
        }
    }
}

```

---

## src\main\java\br\com\vestris\clinical\domain\model

### CalculoResultadoDTO.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\CalculoResultadoDTO.java
package br.com.vestris.clinical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Gera Getters, Setters, toString, etc.
@Builder // Permite criar o objeto de forma fluida (.builder()...build())
@NoArgsConstructor // Construtor vazio
@AllArgsConstructor // Construtor com todos os argumentos
public class CalculoResultadoDTO {
    // Contexto
    private String protocoloTitulo;
    private String medicamentoNome;
    private String referencia;

    // Dados de Entrada Normalizados
    private Double pesoKg;

    // Resultado Massa (Dose)
    private Double doseMinMg;
    private Double doseMaxMg;

    // Resultado Volume (Líquido)
    private Double volMinMl;
    private Double volMaxMl;

    // Metadados do Medicamento/Protocolo
    private String concentracao;
    private String frequencia;
    private String via;
    private String duracao;

    // Segurança
    private List<String> alertas;

    // --- NOVOS CAMPOS DE SEGURANÇA (VALIDAÇÃO) ---
    private String statusSeguranca; // SEGURO, SUBDOSE, etc.
    private String mensagemSeguranca;
    private Double refMin; // Dose de referência (mg/kg) mínima usada para validar
    private Double refMax; // Dose de referência (mg/kg) máxima usada para validar
    private String refFonte; // Fonte da referência (Ex: Carpenter)

    // --- CÁLCULO LIVRE ---
    private Double doseCalculadaMg;
    private Double volumeCalculadoMl;
}

```

### Doenca.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Doenca.java
package br.com.vestris.clinical.domain.model;


import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doencas", schema = "clinical_schema")
public class Doenca extends EntidadeBase {

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 150)
    private String nomeCientifico;

    @Column(columnDefinition = "TEXT")
    private String sintomas;

    // Foreign Key Lógica (Aponta para o ID da Espécie no outro módulo)
    @Column(nullable = false)
    private UUID especieId;
}

```

### Dosagem.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Dosagem.java
package br.com.vestris.clinical.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "protocolo") // Evita loop infinito no log
@Entity
@Table(name = "dosagens", schema = "clinical_schema")
public class Dosagem extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "protocolo_id", nullable = false)
    private Protocolo protocolo;

    // --- MEDICAMENTO (HÍBRIDO) ---
    @Column(name = "medicamento_id", nullable = true)
    private UUID medicamentoId;

    @Column(name = "medicamento_texto_livre")
    private String medicamentoTextoLivre;

    private Double doseMinima;
    private Double doseMaxima;
    private String unidade;
    private String frequencia;
    private String duracao;
    private String via;
}

```

### Protocolo.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\Protocolo.java
package br.com.vestris.clinical.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "protocolos", schema = "clinical_schema")
public class Protocolo extends EntidadeBase {
    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // --- DOENÇA (HÍBRIDO) ---
    @ManyToOne(optional = true)
    @JoinColumn(name = "doenca_id", nullable = true)
    private Doenca doenca;

    @Column(name = "doenca_texto_livre")
    private String doencaTextoLivre;

    // --- REFERÊNCIA (HÍBRIDO) ---
    @Column(name = "referencia_id")
    private UUID referenciaId;

    @Column(name = "referencia_texto")
    private String referenciaTexto;

    // --- ORIGEM ---
    @Enumerated(EnumType.STRING)
    private OrigemProtocolo origem; // OFICIAL, PROPRIO

    private UUID autorId;

    @Column(name = "clinica_id")
    private UUID clinicaId; // Novo campo

    public enum OrigemProtocolo {
        OFICIAL,
        PROPRIO,
        INSTITUCIONAL // Novo Enum
    }

    @OneToMany(mappedBy = "protocolo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dosagem> dosagens = new ArrayList<>();

    public void adicionarDosagem(Dosagem dosagem) {
        dosagens.add(dosagem);
        dosagem.setProtocolo(this);
    }
}

```

### ProtocoloCompletoDTO.java

```java
// src\main\java\br\com\vestris\clinical\domain\model\ProtocoloCompletoDTO.java
package br.com.vestris.clinical.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProtocoloCompletoDTO {
    private Doenca doenca;
    private List<Protocolo> protocolos;
}

```

---

## src\main\java\br\com\vestris\clinical\domain\repository

### RepositorioDoenca.java

```java
// src\main\java\br\com\vestris\clinical\domain\repository\RepositorioDoenca.java
package br.com.vestris.clinical.domain.repository;

import br.com.vestris.clinical.domain.model.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDoenca extends JpaRepository<Doenca, UUID> {
    // Busca todas as doenças de uma espécie específica
    List<Doenca> findAllByEspecieId(UUID especieId);

    // Evita cadastrar a mesma doença para a mesma espécie duas vezes
    boolean existsByNomeAndEspecieId(String nome, UUID especieId);
}

```

### RepositorioProtocolo.java

```java
// src\main\java\br\com\vestris\clinical\domain\repository\RepositorioProtocolo.java
package br.com.vestris.clinical.domain.repository;

import br.com.vestris.clinical.domain.model.Protocolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioProtocolo extends JpaRepository<Protocolo, UUID> {
    List<Protocolo> findByDoencaId(UUID doencaId);

    // --- NOVO MÉTODO DE GOVERNANÇA ---
    @Query("SELECT p FROM Protocolo p WHERE p.doenca.id = :doencaId AND (" +
            "p.origem = 'OFICIAL' OR " +
            "(p.origem = 'INSTITUCIONAL' AND p.clinicaId = :clinicaId) OR " +
            "(p.origem = 'PROPRIO' AND p.autorId = :usuarioId)" +
            ")")
    List<Protocolo> listarAcessiveis(@Param("doencaId") UUID doencaId,
                                     @Param("clinicaId") UUID clinicaId,
                                     @Param("usuarioId") UUID usuarioId);
}

```

---

## src\main\java\br\com\vestris\clinical\interfaces\delegate

### ApiDelegateCalculadora.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateCalculadora.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceCalculadora;
import br.com.vestris.clinical.domain.model.CalculoResultadoDTO;
import br.com.vestris.clinical.interfaces.api.CalculadoraApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiDelegateCalculadora implements CalculadoraApiDelegate {
    private final ServiceCalculadora serviceCalculadora;

    @Override
    public ResponseEntity<ApiResponseCalculo> calcularDosagemSegura(CalculoSeguroRequest request) {
        var resultado = serviceCalculadora.calcular(
                request.getProtocoloId(),
                request.getMedicamentoId(),
                request.getPeso(),
                request.getUnidadePeso().name()
        );
        return montarResposta(resultado);
    }

    // 1. CÁLCULO LIVRE (SIMPLES)
    @Override
    public ResponseEntity<ApiResponseCalculo> calcularDoseLivre(CalculoLivreRequest request) {
        Double pesoKg = request.getPeso();
        if (CalculoLivreRequest.UnidadePesoEnum.G.equals(request.getUnidadePeso())) {
            pesoKg = pesoKg / 1000.0;
        }

        // Chama método específico no Service (vou mostrar abaixo)
        var resultado = serviceCalculadora.calcularMatematico(
                request.getNomeMedicamento(),
                request.getConcentracao(),
                pesoKg,
                request.getDoseInformada(),
                request.getVia(),
                request.getFrequencia(), // Wrap in JsonNullable as per previous patterns if service expects it, checking service signature next step if error. Wait, snippet passed request.getFrequencia() directly. Assuming serviceCalculadora.calcularMatematico takes String/Double.
                request.getDuracao()
        );

        return montarResposta(resultado);
    }

    // 2. VALIDAÇÃO CATÁLOGO (COM SEGURANÇA)
    @Override
    public ResponseEntity<ApiResponseCalculo> validarDoseCatalogo(CalculoCatalogoRequest request) {
        Double pesoKg = request.getPeso();
        if (CalculoCatalogoRequest.UnidadePesoEnum.G.equals(request.getUnidadePeso())) {
            pesoKg = pesoKg / 1000.0;
        }

        var resultado = serviceCalculadora.validarDose(
                request.getMedicamentoId(),
                request.getEspecieId(),
                request.getDoencaId(),
                request.getClinicaId(),
                request.getUsuarioId(),
                pesoKg,
                request.getDoseInformada(),
                "MG_KG",
                request.getVia(),
                null // Concentração manual é null aqui, pois pega do banco
        );

        return montarResposta(resultado);
    }

    // --- HELPERS SEGUROS ---

    private <T> T unwrap(JsonNullable<T> nullable) {
        return (nullable != null && nullable.isPresent()) ? nullable.get() : null;
    }

    private String unwrapString(JsonNullable<String> nullable) {
        return (nullable != null && nullable.isPresent()) ? nullable.get() : null;
    }

    private ResponseEntity<ApiResponseCalculo> montarResposta(CalculoResultadoDTO resultado) {
        CalculoResponse response = new CalculoResponse();

        response.setProtocoloTitulo(resultado.getProtocoloTitulo());
        response.setMedicamentoNome(resultado.getMedicamentoNome());
        response.setReferenciaBibliografica(resultado.getReferencia());
        response.setPesoConsideradoKg(resultado.getPesoKg());
        response.setDoseMinimaMg(resultado.getDoseMinMg());
        response.setDoseMaximaMg(resultado.getDoseMaxMg());
        response.setVolumeMinimoMl(resultado.getVolMinMl());
        response.setVolumeMaximoMl(resultado.getVolMaxMl());
        response.setConcentracaoUtilizada(resultado.getConcentracao());

        // Mapeamento Seguro do Enum
        if (resultado.getStatusSeguranca() != null) {
            try {
                // Tenta converter string para Enum (Case sensitive pode ser problema)
                String statusStr = resultado.getStatusSeguranca().toUpperCase(); // Força upper
                response.setStatusSeguranca(StatusSegurancaEnum.fromValue(statusStr));
            } catch (Exception e) {
                System.err.println("Erro ao converter status: " + resultado.getStatusSeguranca());
                response.setStatusSeguranca(StatusSegurancaEnum.NAO_VALIDADO);
            }
        } else {
            response.setStatusSeguranca(StatusSegurancaEnum.SEGURO);
        }

        response.setMensagemSeguranca(resultado.getMensagemSeguranca());
        response.setRefMin(resultado.getRefMin());
        response.setRefMax(resultado.getRefMax());
        response.setRefFonte(resultado.getRefFonte());

        // Campos do cálculo matemático que podem vir no resultado
        response.setDoseCalculadaMg(resultado.getDoseCalculadaMg());
        response.setVolumeCalculadoMl(resultado.getVolumeCalculadoMl());

        ApiResponseCalculo wrapper = new ApiResponseCalculo();
        wrapper.setSucesso(true);
        wrapper.setDados(response);

        return ResponseEntity.ok(wrapper);
    }
}

```

### ApiDelegateDoencas.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateDoencas.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceDoenca;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.interfaces.api.DoencasApiDelegate;
import br.com.vestris.clinical.interfaces.dto.ApiResponseDoenca;
import br.com.vestris.clinical.interfaces.dto.ApiResponseListaDoenca;
import br.com.vestris.clinical.interfaces.dto.DoencaRequest;
import br.com.vestris.clinical.interfaces.dto.DoencaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateDoencas implements DoencasApiDelegate {
    private final ServiceDoenca servico;

    // --- CRIAÇÃO ---
    @Override
    public ResponseEntity<ApiResponseDoenca> criarDoenca(DoencaRequest request) {
        Doenca entidade = new Doenca();
        entidade.setNome(request.getNome());
        entidade.setNomeCientifico(request.getNomeCientifico());
        entidade.setSintomas(request.getSintomas());
        entidade.setEspecieId(request.getEspecieId());

        Doenca salva = servico.criar(entidade);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setMensagem("Doença cadastrada com sucesso.");
        response.setDados(converterParaDTO(salva));

        return ResponseEntity.ok(response);
    }

    // --- LISTAGEM ---
    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencas() {
        List<DoencaResponse> lista = servico.listarTodas().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca response = new ApiResponseListaDoenca();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaDoenca> listarDoencasPorEspecie(UUID especieId) {
        List<DoencaResponse> lista = servico.listarPorEspecie(especieId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());

        ApiResponseListaDoenca response = new ApiResponseListaDoenca();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    // --- NOVOS MÉTODOS (ID, PUT, DELETE) ---

    @Override
    public ResponseEntity<ApiResponseDoenca> buscarDoencaPorId(UUID id) {
        Doenca encontrada = servico.buscarPorId(id);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setDados(converterParaDTO(encontrada));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseDoenca> atualizarDoenca(UUID id, DoencaRequest request) {
        Doenca dadosParaAtualizar = new Doenca();
        dadosParaAtualizar.setNome(request.getNome());
        dadosParaAtualizar.setNomeCientifico(request.getNomeCientifico());
        dadosParaAtualizar.setSintomas(request.getSintomas());
        // Nota: Geralmente não atualizamos o especieId no PUT para não quebrar integridade,
        // mas se o serviço permitir, adicione: dadosParaAtualizar.setEspecieId(request.getEspecieId());

        Doenca atualizada = servico.atualizar(id, dadosParaAtualizar);

        ApiResponseDoenca response = new ApiResponseDoenca();
        response.setSucesso(true);
        response.setMensagem("Doença atualizada com sucesso.");
        response.setDados(converterParaDTO(atualizada));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarDoenca(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

    // --- CONVERSOR ---
    private DoencaResponse converterParaDTO(Doenca entidade) {
        DoencaResponse dto = new DoencaResponse();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setNomeCientifico(entidade.getNomeCientifico());
        dto.setSintomas(entidade.getSintomas());
        dto.setEspecieId(entidade.getEspecieId());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegateProtocolos.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\delegate\ApiDelegateProtocolos.java
package br.com.vestris.clinical.interfaces.delegate;

import br.com.vestris.clinical.application.ServiceProtocolo;
import br.com.vestris.clinical.domain.model.ProtocoloCompletoDTO;
import br.com.vestris.clinical.domain.model.Doenca;
import br.com.vestris.clinical.domain.model.Dosagem;
import br.com.vestris.clinical.domain.model.Protocolo;
import br.com.vestris.clinical.interfaces.api.ProtocolosApiDelegate;
import br.com.vestris.clinical.interfaces.dto.*;
import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.reference.application.ServiceReferencia;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateProtocolos implements ProtocolosApiDelegate {

    private final ServiceProtocolo servico;
    private final ServiceFarmacologia serviceFarmacologia;
    private final ServiceReferencia serviceReferencia;

    // --- CRIAÇÃO ---
    @Override
    public ResponseEntity<ApiResponseProtocolo> criarProtocolo(ProtocoloRequest request) {
        Protocolo protocolo = new Protocolo();
        protocolo.setTitulo(request.getTitulo());
        protocolo.setObservacoes(request.getObservacoes());

        protocolo.setReferenciaId(unwrap(request.getReferenciaId()));
        protocolo.setReferenciaTexto(request.getReferenciaTexto());
        protocolo.setDoencaTextoLivre(request.getDoencaTexto());

        if (request.getOrigem() != null) {
            protocolo.setOrigem(Protocolo.OrigemProtocolo.valueOf(request.getOrigem().name()));
        } else {
            protocolo.setOrigem(Protocolo.OrigemProtocolo.OFICIAL);
        }

        protocolo.setAutorId(request.getAutorId());

        // NOVO: Setar Clinica ID
        protocolo.setClinicaId(unwrap(request.getClinicaId()));

        List<Dosagem> listaDosagens = converterDosagensRequest(request.getDosagens());

        UUID doencaId = unwrap(request.getDoencaId());

        Protocolo salvo = servico.criar(protocolo, doencaId, listaDosagens);

        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setMensagem("Protocolo criado com sucesso.");
        response.setDados(converterParaDto(salvo));

        return ResponseEntity.ok(response);
    }

    // --- LISTAGEM (CORRIGIDA) ---
    @Override
    public ResponseEntity<ApiResponseListaProtocolo> listarProtocolosPorDoenca(UUID doencaId, UUID clinicaId, UUID usuarioId) {

        List<Protocolo> protocolosEncontrados;

        // Se vierem os filtros de segurança, usa a busca inteligente
        if (clinicaId != null && usuarioId != null) {
            protocolosEncontrados = servico.listarAcessiveis(doencaId, clinicaId, usuarioId);
        } else {
            // Fallback (Legado ou uso interno): Traz tudo da doença (Cuidado aqui em produção)
            // Se preferir segurança total, force retornar lista vazia se não tiver contexto.
            protocolosEncontrados = servico.listarPorDoenca(doencaId);
        }

        List<ProtocoloResponse> lista = protocolosEncontrados.stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());

        ApiResponseListaProtocolo response = new ApiResponseListaProtocolo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    // --- PROTOCOLO COMPLETO ---
    @Override
    public ResponseEntity<ApiResponseProtocoloCompleto> obterProtocoloCompleto(UUID especieId, UUID doencaId) {
        // Nota: Este método ainda retorna TODOS os protocolos da doença para compor a visão completa.
        // Se precisar filtrar aqui também, o ServiceProtocolo.montarProtocoloCompleto precisaria ser atualizado.
        // Por enquanto, mantemos o comportamento padrão do "Motor Clínico".

        ProtocoloCompletoDTO dtoCompleto = servico.montarProtocoloCompleto(especieId, doencaId);

        ProtocoloCompletoResponse response = new ProtocoloCompletoResponse();
        response.setDoenca(converterDoenca(dtoCompleto.getDoenca()));

        List<ProtocoloDetalhadoResponse> listaProtos = dtoCompleto.getProtocolos().stream()
                .map(p -> {
                    ProtocoloDetalhadoResponse detalhe = new ProtocoloDetalhadoResponse();
                    detalhe.setId(p.getId());
                    detalhe.setTitulo(p.getTitulo());
                    detalhe.setObservacoes(p.getObservacoes());

                    if (p.getReferenciaId() != null) {
                        String citacao = serviceReferencia.buscarCitacaoPorId(p.getReferenciaId());
                        detalhe.setReferenciaTexto(citacao);
                    } else {
                        detalhe.setReferenciaTexto(p.getReferenciaTexto());
                    }

                    if (p.getOrigem() != null) {
                        detalhe.setOrigem(OrigemProtocoloEnum.valueOf(p.getOrigem().name()));
                    }
                    detalhe.setAutorId(p.getAutorId());

                    List<DosagemResponse> dosesResponse = p.getDosagens().stream()
                            .map(this::converterDosagemUnica)
                            .collect(Collectors.toList());

                    detalhe.setDosagens(dosesResponse);
                    detalhe.setAlertasGerais(new ArrayList<>());
                    return detalhe;
                }).collect(Collectors.toList());

        response.setProtocolos(listaProtos);

        ApiResponseProtocoloCompleto wrapper = new ApiResponseProtocoloCompleto();
        wrapper.setSucesso(true);
        wrapper.setDados(response);
        return ResponseEntity.ok(wrapper);
    }

    // --- MÉTODOS CRUD PADRÃO ---

    @Override
    public ResponseEntity<ApiResponseProtocolo> buscarProtocoloPorId(UUID id) {
        Protocolo p = servico.buscarPorId(id);
        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setDados(converterParaDto(p));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseProtocolo> atualizarProtocolo(UUID id, ProtocoloRequest request) {
        Protocolo dadosProtocolo = new Protocolo();
        dadosProtocolo.setTitulo(request.getTitulo());
        dadosProtocolo.setObservacoes(request.getObservacoes());
        dadosProtocolo.setReferenciaTexto(request.getReferenciaTexto());

        dadosProtocolo.setReferenciaId(unwrap(request.getReferenciaId()));
        dadosProtocolo.setDoencaTextoLivre(request.getDoencaTexto());

        // Atualiza vínculo institucional se enviado
        dadosProtocolo.setClinicaId(unwrap(request.getClinicaId()));

        List<Dosagem> novasDosagens = converterDosagensRequest(request.getDosagens());

        Protocolo atualizado = servico.atualizar(id, dadosProtocolo, novasDosagens);

        ApiResponseProtocolo response = new ApiResponseProtocolo();
        response.setSucesso(true);
        response.setMensagem("Protocolo atualizado.");
        response.setDados(converterParaDto(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarProtocolo(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPERS ---
    private <T> T unwrap(JsonNullable<T> nullable) {
        if (nullable == null || !nullable.isPresent()) {
            return null;
        }
        return nullable.get();
    }

    private DoencaResponse converterDoenca(Doenca d) {
        DoencaResponse dto = new DoencaResponse();
        dto.setId(d.getId());
        dto.setNome(d.getNome());
        dto.setNomeCientifico(d.getNomeCientifico());
        dto.setSintomas(d.getSintomas());
        dto.setEspecieId(d.getEspecieId());
        if(d.getCriadoEm() != null) dto.setCriadoEm(d.getCriadoEm().atOffset(ZoneOffset.UTC));
        return dto;
    }

    private List<Dosagem> converterDosagensRequest(List<DosagemItemRequest> itensRequest) {
        List<Dosagem> lista = new ArrayList<>();
        if (itensRequest != null) {
            for (DosagemItemRequest item : itensRequest) {
                Dosagem d = new Dosagem();
                d.setMedicamentoId(unwrap(item.getMedicamentoId()));
                d.setDoseMinima(unwrap(item.getDoseMinima()));
                d.setDoseMaxima(unwrap(item.getDoseMaxima()));
                d.setMedicamentoTextoLivre(item.getMedicamentoTexto());
                d.setUnidade(item.getUnidade());
                d.setFrequencia(item.getFrequencia());
                d.setDuracao(item.getDuracao());
                d.setVia(item.getVia());
                lista.add(d);
            }
        }
        return lista;
    }

    private ProtocoloResponse converterParaDto(Protocolo entidade) {
        ProtocoloResponse dto = new ProtocoloResponse();
        dto.setId(entidade.getId());
        dto.setTitulo(entidade.getTitulo());
        dto.setObservacoes(entidade.getObservacoes());

        // NOVO CAMPO
        dto.setClinicaId(entidade.getClinicaId());

        if (entidade.getReferenciaTexto() != null) {
            dto.setReferenciaTexto(entidade.getReferenciaTexto());
        } else if (entidade.getReferenciaId() != null) {
            try {
                dto.setReferenciaTexto(serviceReferencia.buscarCitacaoPorId(entidade.getReferenciaId()));
            } catch (Exception e) {
                dto.setReferenciaTexto("Ref ID: " + entidade.getReferenciaId());
            }
        }

        if (entidade.getDoenca() != null) {
            dto.setDoencaId(entidade.getDoenca().getId());
        }
        dto.setDoencaTexto(entidade.getDoencaTextoLivre());

        if (entidade.getOrigem() != null) {
            dto.setOrigem(OrigemProtocoloEnum.valueOf(entidade.getOrigem().name()));
        }
        dto.setAutorId(entidade.getAutorId());

        if (entidade.getDosagens() != null) {
            List<DosagemResponse> dosagensDto = entidade.getDosagens().stream()
                    .map(this::converterDosagemUnica)
                    .collect(Collectors.toList());
            dto.setDosagens(dosagensDto);
        }
        return dto;
    }

    private DosagemResponse converterDosagemUnica(Dosagem d) {
        DosagemResponse dDto = new DosagemResponse();
        dDto.setId(d.getId());
        dDto.setMedicamentoId(d.getMedicamentoId());
        dDto.setMedicamentoTexto(d.getMedicamentoTextoLivre());

        if (d.getMedicamentoId() != null) {
            try {
                var med = serviceFarmacologia.buscarMedicamentoPorId(d.getMedicamentoId());
                dDto.setNomeMedicamento(med.getNome());
            } catch (Exception e) {
                dDto.setNomeMedicamento(d.getMedicamentoTextoLivre() != null ? d.getMedicamentoTextoLivre() : "Medicamento não encontrado");
            }
        } else {
            dDto.setNomeMedicamento(d.getMedicamentoTextoLivre());
        }

        String doseTexto = "";
        if (d.getDoseMinima() != null) {
            doseTexto = d.getDoseMinima() + (d.getUnidade() != null ? " " + d.getUnidade() : "");
        } else {
            doseTexto = "Dose a critério";
        }

        dDto.setDose(doseTexto);
        dDto.setDetalhes((d.getVia() != null ? d.getVia() : "") +
                (d.getFrequencia() != null ? ", " + d.getFrequencia() : "") +
                (d.getDuracao() != null ? " por " + d.getDuracao() : ""));
        return dDto;
    }
}
```

---

## src\main\java\br\com\vestris\clinical\interfaces\dto

### CalculoCatalogoRequest.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\dto\CalculoCatalogoRequest.java

```

### CalculoLivreRequest.java

```java
// src\main\java\br\com\vestris\clinical\interfaces\dto\CalculoLivreRequest.java

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Clínico
  description: Gestão de Doenças e Protocolos
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  /api/v1/doencas:
    $ref: './paths/doencas.yml#/paths/~1api~1v1~1doencas'

  /api/v1/doencas/{id}:
    $ref: './paths/doencas.yml#/paths/~1api~1v1~1doencas~1{id}'

  /api/v1/doencas/por-especie/{especieId}:
    $ref: './paths/doencas.yml#/paths/~1api~1v1~1doencas~1por-especie~1{especieId}'

  /api/v1/protocolos:
    $ref: './paths/protocolos.yml#/paths/~1api~1v1~1protocolos'

  /api/v1/protocolos/{id}:
    $ref: './paths/protocolos.yml#/paths/~1api~1v1~1protocolos~1{id}'

  /api/v1/doencas/{doencaId}/protocolos:
    $ref: './paths/protocolos.yml#/paths/~1api~1v1~1doencas~1{doencaId}~1protocolos'

  /api/v1/especies/{especieId}/doencas/{doencaId}/protocolo-completo:
    $ref: './paths/protocolos.yml#/paths/~1api~1v1~1especies~1{especieId}~1doencas~1{doencaId}~1protocolo-completo'

  /api/v1/calculadora/dosagem:
    $ref: './paths/calculadora.yml#/calculadora_item'
    # ADICIONE ESTA LINHA:
  /api/v1/calculadora/validar:
    $ref: './paths/calculadora.yml#/calculadora_validar'

  /api/v1/calculadora/livre:
    $ref: './paths/calculadora.yml#/calculadora_livre'


# Importando os componentes (necessário declarar aqui também para o parser raiz entender)
components:
  schemas:
    DoencaRequest:
      $ref: "./components/schemas.yml#/DoencaRequest"
    DoencaResponse:
      $ref: "./components/schemas.yml#/DoencaResponse"
    ApiResponseDoenca:
      $ref: "./components/schemas.yml#/ApiResponseDoenca"
    ApiResponseListaDoenca:
      $ref: "./components/schemas.yml#/ApiResponseListaDoenca"
    DosagemItemRequest:
      $ref: "./components/schemas.yml#/DosagemItemRequest"
    DosagemResponse:
      $ref: "./components/schemas.yml#/DosagemResponse"
    ProtocoloRequest:
      $ref: "./components/schemas.yml#/ProtocoloRequest"
    ProtocoloResponse:
      $ref: "./components/schemas.yml#/ProtocoloResponse"
    CalculoSeguroRequest:
      $ref: "./components/schemas.yml#/CalculoSeguroRequest"
    CalculoResponse:
      $ref: "./components/schemas.yml#/CalculoResponse"
    ApiResponseProtocolo:
      $ref: "./components/schemas.yml#/ApiResponseProtocolo"
    ApiResponseListaProtocolo:
      $ref: "./components/schemas.yml#/ApiResponseListaProtocolo"
    ProtocoloDetalhadoResponse:
      $ref: "./components/schemas.yml#/ProtocoloDetalhadoResponse"
    ProtocoloCompletoResponse:
      $ref: "./components/schemas.yml#/ProtocoloCompletoResponse"
    ApiResponseProtocoloCompleto:
      $ref: "./components/schemas.yml#/ApiResponseProtocoloCompleto"
    ApiResponseCalculo:
      $ref: "./components/schemas.yml#/ApiResponseCalculo"
    # ENUMS ATUALIZADOS PARA O GERADOR ENCONTRAR
    OrigemProtocoloEnum:
      $ref: "./components/schemas.yml#/OrigemProtocoloEnum"
    CalculoValidacaoRequest:
      $ref: "./components/schemas.yml#/CalculoValidacaoRequest"





```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# src/main/resources/swagger/components/schemas.yml

# --- ENUMS ---
OrigemProtocoloEnum:
  type: string
  enum: [OFICIAL, PROPRIO, INSTITUCIONAL]

StatusSegurancaEnum:
  type: string
  enum: [SEGURO, SUBDOSE, SUPERDOSE, SEM_REFERENCIA, NAO_VALIDADO]

# --- DOENÇAS ---
DoencaRequest:
  type: object
  required: [nome, especieId]
  properties:
    nome: { type: string }
    nomeCientifico: { type: string }
    sintomas: { type: string }
    especieId: { type: string, format: uuid }

DoencaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    nomeCientifico: { type: string }
    sintomas: { type: string }
    especieId: { type: string, format: uuid }
    criadoEm: { type: string, format: date-time }

# --- PROTOCOLOS ---
DosagemItemRequest:
  type: object
  properties:
    medicamentoId: { type: string, format: uuid, nullable: true }
    medicamentoTexto: { type: string }
    doseMinima: { type: number, format: double, nullable: true }
    doseMaxima: { type: number, format: double, nullable: true }
    unidade: { type: string }
    frequencia: { type: string }
    duracao: { type: string }
    via: { type: string }

ProtocoloRequest:
  type: object
  required: [ titulo, dosagens ]
  properties:
    titulo: { type: string }
    doencaId: { type: string, format: uuid, nullable: true }
    doencaTexto: { type: string }
    referenciaId: { type: string, format: uuid, nullable: true }
    referenciaTexto: { type: string }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid, nullable: true }
    dosagens:
      type: array
      items: { $ref: '#/DosagemItemRequest' }

DosagemResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    medicamentoId: { type: string, format: uuid }
    medicamentoTexto: { type: string }
    nomeMedicamento: { type: string }
    dose: { type: string }
    detalhes: { type: string }

ProtocoloResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    titulo: { type: string }
    doencaId: { type: string, format: uuid }
    doencaTexto: { type: string }
    referenciaId: { type: string, format: uuid }
    referenciaTexto: { type: string }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid }
    dosagens:
      type: array
      items: { $ref: '#/DosagemResponse' }

ProtocoloDetalhadoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    titulo: { type: string }
    referenciaTexto: { type: string }
    referenciaId: { type: string, format: uuid }
    observacoes: { type: string }
    origem: { $ref: '#/OrigemProtocoloEnum' }
    autorId: { type: string, format: uuid }
    dosagens:
      type: array
      items: { $ref: '#/DosagemResponse' }
    alertasGerais:
      type: array
      items: { type: string }

ProtocoloCompletoResponse:
  type: object
  properties:
    doenca: { $ref: '#/DoencaResponse' }
    protocolos:
      type: array
      items: { $ref: '#/ProtocoloDetalhadoResponse' }

# --- CALCULADORA ---

CalculoLivreRequest:
  type: object
  required: [peso, doseInformada, concentracao, unidadePeso]
  properties:
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double }
    concentracao: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }
    nomeMedicamento: { type: string } # Opcional, só para devolver no DTO
    via: { type: string }
    frequencia: { type: string }
    duracao: { type: string }

# REQUEST PARA VALIDAÇÃO (CATÁLOGO)
CalculoCatalogoRequest:
  type: object
  required: [medicamentoId, especieId, peso, doseInformada, unidadePeso]
  properties:
    medicamentoId: { type: string, format: uuid }
    especieId: { type: string, format: uuid }
    doencaId: { type: string, format: uuid } # Mantém nullable aqui pois é ID
    clinicaId: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid}
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }
    via: { type: string }

CalculoSeguroRequest:
  type: object
  required: [protocoloId, medicamentoId, peso, unidadePeso]
  properties:
    protocoloId: { type: string, format: uuid }
    medicamentoId: { type: string, format: uuid }
    peso: { type: number, format: double }
    unidadePeso: { type: string, enum: [KG, G] }

CalculoResponse:
  type: object
  properties:
    protocoloTitulo: { type: string }
    medicamentoNome: { type: string }
    referenciaBibliografica: { type: string }
    pesoConsideradoKg: { type: number, format: double }
    doseMinimaMg: { type: number, format: double }
    doseMaximaMg: { type: number, format: double }
    volumeMinimoMl: { type: number, format: double }
    volumeMaximoMl: { type: number, format: double }
    concentracaoUtilizada: { type: string }
    frequencia: { type: string }
    doseCalculadaMg: { type: number, format: double }
    volumeCalculadoMl: { type: number, format: double }
    via: { type: string }
    duracao: { type: string }
    alertas:
      type: array
      items: { type: string }
    # NOVOS CAMPOS DE SEGURANÇA
    statusSeguranca: { $ref: '#/StatusSegurancaEnum' }
    mensagemSeguranca: { type: string }
    refMin: { type: number, format: double }
    refMax: { type: number, format: double }
    refFonte: { type: string }

CalculoValidacaoRequest:
  type: object
  required: [ peso, doseInformada, unidadePeso]
  properties:
    medicamentoId: { type: string, format: uuid, nullable: true }
    especieId: { type: string, format: uuid, nullable: true }
    peso: { type: number, format: double }
    doseInformada: { type: number, format: double, description: "A dose que o vet quer usar (mg/kg)" }
    unidadePeso: { type: string, enum: [KG, G] }
    concentracaoInformada: { type: number, format: double, nullable: true, description: "mg/ml (usado se não houver ID)" }
    # Opcionais para refinar a busca
    doencaId: { type: string, format: uuid, nullable: true }
    via: { type: string, nullable: true }
    clinicaId: { type: string, format: uuid, nullable: true }
    usuarioId: { type: string, format: uuid, nullable: true }

# --- WRAPPERS ---
ApiResponseProtocolo:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/ProtocoloResponse' }

ApiResponseListaProtocolo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ProtocoloResponse' }

ApiResponseProtocoloCompleto:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ProtocoloCompletoResponse' }

ApiResponseDoenca:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/DoencaResponse' }

ApiResponseListaDoenca:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/DoencaResponse' }

ApiResponseCalculo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/CalculoResponse' }
```

---

## src\main\resources\swagger\paths

### calculadora.yml

```yaml
# src\main\resources\swagger\paths\calculadora.yml
# ROTA 1: CÁLCULO BASEADO EM PROTOCOLO (JÁ EXISTENTE, MANTÉM)
calculadora_item:
  post:
    tags: [Calculadora]
    summary: Calcular via Protocolo
    operationId: calcularDosagemSegura
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoSeguroRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }

# ROTA 2: CÁLCULO LIVRE (MATEMÁTICA PURA) - NOVO
calculadora_livre:
  post:
    tags: [Calculadora]
    summary: Calculadora Livre (Sem Validação)
    description: "Faz apenas o cálculo matemático de volume com base na dose e concentração informadas."
    operationId: calcularDoseLivre
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoLivreRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }

# ROTA 3: VALIDAÇÃO DE SEGURANÇA (CATÁLOGO) - NOVO
calculadora_validar:
  post:
    tags: [Calculadora]
    summary: Validar Dose (Catálogo)
    description: "Verifica se a dose está segura cruzando com a base científica."
    operationId: validarDoseCatalogo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CalculoCatalogoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseCalculo' }
```

### doencas.yml

```yaml
# src\main\resources\swagger\paths\doencas.yml
paths:
  /api/v1/doencas:
    post:
      tags:
        - Doencas
      summary: Cadastrar nova doença
      operationId: criarDoenca
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/DoencaRequest'
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'

    get:
      tags:
        - Doencas
      summary: Listar todas as doenças
      operationId: listarDoencas
      responses:
        '200':
          description: Lista recuperada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaDoenca'

  /api/v1/doencas/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    get:
      tags:
        - Doencas
      summary: Buscar doença por ID
      operationId: buscarDoencaPorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'
    put:
      tags:
        - Doencas
      summary: Atualizar doença
      operationId: atualizarDoenca
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/DoencaRequest'
      responses:
        '200':
          description: Atualizado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseDoenca'
    delete:
      tags:
        - Doencas
      summary: Remover doença
      operationId: deletarDoenca
      responses:
        '204':
          description: Removido

  /api/v1/doencas/por-especie/{especieId}:
    get:
      tags:
        - Doencas
      summary: Listar doenças de uma espécie específica
      operationId: listarDoencasPorEspecie
      parameters:
        - name: especieId
          in: path
          required: true
          schema:
            type: string
            format: uuid
      responses:
        '200':
          description: Lista filtrada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaDoenca'

```

### protocolos.yml

```yaml
# src\main\resources\swagger\paths\protocolos.yml
# vestris-clinical/src/main/resources/swagger/paths/protocolos.yml
paths:
  /api/v1/protocolos:
    post:
      tags:
        - Protocolos
      summary: Criar protocolo terapêutico
      operationId: criarProtocolo
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ProtocoloRequest'
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'

  /api/v1/protocolos/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    get:
      tags:
        - Protocolos
      summary: Buscar protocolo por ID
      operationId: buscarProtocoloPorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'
    put:
      tags:
        - Protocolos
      summary: Atualizar protocolo
      operationId: atualizarProtocolo
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ProtocoloRequest'
      responses:
        '200':
          description: Atualizado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseProtocolo'
    delete:
      tags:
        - Protocolos
      summary: Remover protocolo
      operationId: deletarProtocolo
      responses:
        '204':
          description: Removido

  /api/v1/doencas/{doencaId}/protocolos:
    get:
      tags:
        - Protocolos
      summary: Listar protocolos de uma doença
      description: "Retorna protocolos Oficiais, Institucionais (da clínica) e Próprios (do usuário)."
      operationId: listarProtocolosPorDoenca
      parameters:
        - name: doencaId
          in: path
          required: true
          schema:
            type: string
            format: uuid
        # NOVOS PARÂMETROS PARA FILTRAGEM INTELIGENTE
        - name: clinicaId
          in: query
          required: false
          schema: { type: string, format: uuid }
        - name: usuarioId
          in: query
          required: false
          schema: { type: string, format: uuid }
      responses:
        '200':
          description: Lista
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaProtocolo'

  /api/v1/especies/{especieId}/doencas/{doencaId}/protocolo-completo:
    get:
      tags: [ Protocolos ]
      summary: Obter visão completa do tratamento
      description: "Retorna doença, protocolo, dosagens e contraindicações combinadas"
      operationId: obterProtocoloCompleto
      parameters:
        - name: especieId
          in: path
          required: true
          schema: { type: string, format: uuid }
        - name: doencaId
          in: path
          required: true
          schema: { type: string, format: uuid }
      responses:
        '200':
          description: Sucesso
          content:
            application/json:
              schema: { $ref: '../components/schemas.yml#/ApiResponseProtocoloCompleto' }
```


---

# Projeto: vestris-feedbacks

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-feedback</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Dependências Padrão Swagger/JPA -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.feedback.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.feedback.interfaces.dto</modelPackage>

                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

---

## src\main\java\br\com\vestris\feedbacks\application

### ServiceSugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\application\ServiceSugestao.java
package br.com.vestris.feedbacks.application;

import br.com.vestris.feedbacks.domain.Sugestao;
import br.com.vestris.feedbacks.domain.repository.RepositorioSugestao;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.application.ServiceUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceSugestao {
    private final RepositorioSugestao repositorio;
    private final ServiceUsuario servicoUsuario;

    public void registrar(UUID usuarioId, Sugestao.TipoSugestao tipo, String conteudo) {
        // Valida se quem sugeriu existe
        if (!servicoUsuario.existePorId(usuarioId)) {
            throw new ExcecaoRegraNegocio("Usuário não encontrado.");
        }

        Sugestao s = new Sugestao();
        s.setUsuarioId(usuarioId);
        s.setTipo(tipo);
        s.setConteudo(conteudo);
        s.setStatus(Sugestao.StatusSugestao.PENDENTE); // Sempre nasce pendente

        repositorio.save(s);
    }

    public List<Sugestao> listar(Sugestao.TipoSugestao tipo, Sugestao.StatusSugestao status) {
        if (tipo != null && status != null) {
            return repositorio.findByTipoAndStatus(tipo, status);
        } else if (tipo != null) {
            return repositorio.findByTipo(tipo);
        } else if (status != null) {
            return repositorio.findByStatus(status);
        } else {
            return repositorio.findAll();
        }
    }

    public void atualizarStatus(UUID id, Sugestao.StatusSugestao novoStatus) {
        Sugestao sugestao = repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Sugestão", id.toString()));

        sugestao.setStatus(novoStatus);
        repositorio.save(sugestao);
    }
}

```

---

## src\main\java\br\com\vestris\feedbacks\domain

### Sugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\domain\Sugestao.java
package br.com.vestris.feedbacks.domain;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sugestoes", schema = "feedback_schema")
public class Sugestao extends EntidadeBase {
    @Column(nullable = false)
    private UUID usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSugestao tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSugestao status;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String conteudo; // Aqui guardamos o JSON ou Texto do que foi sugerido

    public enum TipoSugestao {
        ESPECIE, DOENCA, PROTOCOLO, CALCULO, OUTRO
    }

    public enum StatusSugestao {
        PENDENTE, EM_ANALISE, APROVADA, REJEITADA
    }
}

```

---

## src\main\java\br\com\vestris\feedbacks\domain\repository

### RepositorioSugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\domain\repository\RepositorioSugestao.java
package br.com.vestris.feedbacks.domain.repository;

import br.com.vestris.feedbacks.domain.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioSugestao extends JpaRepository<Sugestao, UUID> {
    List<Sugestao> findByTipo(Sugestao.TipoSugestao tipo);
    List<Sugestao> findByStatus(Sugestao.StatusSugestao status);
    List<Sugestao> findByTipoAndStatus(Sugestao.TipoSugestao tipo, Sugestao.StatusSugestao status);
}

```

---

## src\main\java\br\com\vestris\feedbacks\interfaces

### ApiDelegateSugestao.java

```java
// src\main\java\br\com\vestris\feedbacks\interfaces\ApiDelegateSugestao.java
package br.com.vestris.feedbacks.interfaces;

import br.com.vestris.feedback.interfaces.api.SugestoesApiDelegate;
import br.com.vestris.feedback.interfaces.dto.*;
import br.com.vestris.feedbacks.application.ServiceSugestao;
import br.com.vestris.feedbacks.domain.Sugestao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateSugestao implements SugestoesApiDelegate {

    private final ServiceSugestao servico;

    @Override
    public ResponseEntity<ApiResponseSugestao> sugerirEspecie(SugestaoRequest request) {
        return processar(request, Sugestao.TipoSugestao.ESPECIE);
    }

    @Override
    public ResponseEntity<ApiResponseSugestao> sugerirDoenca(SugestaoRequest request) {
        return processar(request, Sugestao.TipoSugestao.DOENCA);
    }

    @Override
    public ResponseEntity<ApiResponseSugestao> sugerirProtocolo(SugestaoRequest request) {
        return processar(request, Sugestao.TipoSugestao.PROTOCOLO);
    }

    @Override
    public ResponseEntity<ApiResponseSugestao> sugerirCalculo(SugestaoRequest request) {
        return processar(request, Sugestao.TipoSugestao.CALCULO);
    }

    // Método auxiliar para evitar repetição
    private ResponseEntity<ApiResponseSugestao> processar(SugestaoRequest req, Sugestao.TipoSugestao tipoForcado) {
        servico.registrar(req.getUsuarioId(), tipoForcado, req.getConteudo());

        ApiResponseSugestao response = new ApiResponseSugestao();
        response.setSucesso(true);
        response.setMensagem("Sugestão recebida! Nossa equipe científica irá analisar.");

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaSugestao> listarSugestoes(TipoSugestaoEnum tipoDTO, StatusSugestaoEnum statusDTO) {
        // Converte Enums do DTO para Enums do Domínio (Java)
        Sugestao.TipoSugestao tipoDomain = tipoDTO != null ? Sugestao.TipoSugestao.valueOf(tipoDTO.name()) : null;
        Sugestao.StatusSugestao statusDomain = statusDTO != null ? Sugestao.StatusSugestao.valueOf(statusDTO.name()) : null;

        List<SugestaoResponse> lista = servico.listar(tipoDomain, statusDomain).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaSugestao response = new ApiResponseListaSugestao();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private SugestaoResponse converter(Sugestao s) {
        SugestaoResponse dto = new SugestaoResponse();
        dto.setId(s.getId());
        dto.setUsuarioId(s.getUsuarioId());
        dto.setConteudo(s.getConteudo());
        dto.setTipo(TipoSugestaoEnum.valueOf(s.getTipo().name()));
        dto.setStatus(StatusSugestaoEnum.valueOf(s.getStatus().name()));
        if (s.getCriadoEm() != null) {
            dto.setCriadoEm(s.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseSugestao> atualizarStatusSugestao(UUID id, AtualizarStatusRequest request) {
        // Converter Enum DTO -> Domain
        Sugestao.StatusSugestao statusDomain = Sugestao.StatusSugestao.valueOf(request.getStatus().name());

        servico.atualizarStatus(id, statusDomain);

        ApiResponseSugestao response = new ApiResponseSugestao();
        response.setSucesso(true);
        response.setMensagem("Status atualizado para " + statusDomain.name());

        return ResponseEntity.ok(response);
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Feedback
  description: Gestão de Sugestões e Melhorias
  version: 1.0.0
servers:
  - url: http://localhost:8080

paths:
  /api/v1/sugestoes/especies:
    $ref: './paths/sugestoes.yml#/sugestoes_especies'
  /api/v1/sugestoes/doencas:
    $ref: './paths/sugestoes.yml#/sugestoes_doencas'
  /api/v1/sugestoes/protocolos:
    $ref: './paths/sugestoes.yml#/sugestoes_protocolos'
  /api/v1/sugestoes/calculos:
    $ref: './paths/sugestoes.yml#/sugestoes_calculos'
  /api/v1/sugestoes:
    $ref: './paths/sugestoes.yml#/sugestoes_global'
  /api/v1/sugestoes/{id}/status:
    $ref: './paths/sugestoes.yml#/sugestoes_atualizar_status'

components:
  schemas:
    TipoSugestaoEnum:
      $ref: "./components/schemas.yml#/TipoSugestaoEnum"
    SugestaoRequest:
      $ref: "./components/schemas.yml#/SugestaoRequest"
    ApiResponseSugestao:
      $ref: "./components/schemas.yml#/ApiResponseSugestao"
    StatusSugestaoEnum:
      $ref: "./components/schemas.yml#/StatusSugestaoEnum"
    SugestaoResponse:
      $ref: "./components/schemas.yml#/SugestaoResponse"
    AtualizarStatusRequest:
      $ref: "./components/schemas.yml#/AtualizarStatusRequest"
    ApiResponseListaSugestao:
      $ref: "./components/schemas.yml#/ApiResponseListaSugestao"





```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
TipoSugestaoEnum:
  type: string
  enum: [ESPECIE, DOENCA, PROTOCOLO, CALCULO, OUTRO]

# Request Genérico
SugestaoRequest:
  type: object
  required: [usuarioId, conteudo]
  properties:
    usuarioId:
      type: string
      format: uuid
    tipo:
      $ref: '#/TipoSugestaoEnum'
    conteudo:
      type: string
      description: "JSON stringify ou Texto descrevendo a sugestão (Nome, obs, contexto)"
      example: "{ 'nome': 'Papagaio-verdadeiro', 'obs': 'Comum em resgates' }"

StatusSugestaoEnum:
  type: string
  enum: [PENDENTE, EM_ANALISE, APROVADA, REJEITADA]

SugestaoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid }
    tipo: { $ref: '#/TipoSugestaoEnum' }
    status: { $ref: '#/StatusSugestaoEnum' }
    conteudo: { type: string }
    criadoEm: { type: string, format: date-time }

# Wrappers
ApiResponseSugestao:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }

ApiResponseListaSugestao:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/SugestaoResponse' }

AtualizarStatusRequest:
  type: object
  required: [status]
  properties:
    status:
      $ref: '#/StatusSugestaoEnum'
```

---

## src\main\resources\swagger\paths

### sugestoes.yml

```yaml
# src\main\resources\swagger\paths\sugestoes.yml
# ROTA: /api/v1/sugestoes/especies
sugestoes_especies:
  post:
    tags: [Sugestoes]
    summary: Sugerir nova espécie
    operationId: sugerirEspecie
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

# ROTA: /api/v1/sugestoes/doencas
sugestoes_doencas:
  post:
    tags: [Sugestoes]
    summary: Sugerir doença
    operationId: sugerirDoenca
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

# ROTA: /api/v1/sugestoes/protocolos
sugestoes_protocolos:  
  post:
    tags: [Sugestoes]
    summary: Sugerir melhoria em protocolo
    operationId: sugerirProtocolo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

#ROTA: /api/v1/sugestoes/calculos
sugestoes_calculos:
  post:
    tags: [Sugestoes]
    summary: Sugerir novo tipo de cálculo
    operationId: sugerirCalculo
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/SugestaoRequest' }
    responses:
      '200':
        description: Recebido
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSugestao' }

#ROTA: /api/v1/sugestoes
sugestoes_global:
  get:
    tags: [Sugestoes]
    summary: Listar sugestões recebidas
    description: "Permite filtrar por tipo (ESPECIE, DOENCA, etc) e status"
    operationId: listarSugestoes
    parameters:
      - name: tipo
        in: query
        required: false
        schema:
          $ref: '../components/schemas.yml#/TipoSugestaoEnum'
      - name: status
        in: query
        required: false
        schema:
          $ref: '../components/schemas.yml#/StatusSugestaoEnum'
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaSugestao'

# ROTA: /api/v1/sugestoes/{id}/status
sugestoes_atualizar_status:
  patch:
    tags: [Sugestoes]
    summary: Alterar status da sugestão (Admin)
    operationId: atualizarStatusSugestao
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/AtualizarStatusRequest'
    responses:
      '200':
        description: Status atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseSugestao'

```


---

# Projeto: vestris-medical-record

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-medical-record</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 1. Para vincular ao Veterinário -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 2. Para vincular a Espécie -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-species</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 3. Para vincular Protocolos (Opcional) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-clinical</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Dependências Padrão -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.medicalrecord.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.medicalrecord.interfaces.dto</modelPackage>

                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

---

## src\main\java\br\com\vestris\medicalrecord\application

### ServiceAtendimento.java

```java
// src\main\java\br\com\vestris\medicalrecord\application\ServiceAtendimento.java
package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioAtendimento;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceAtendimento {
    private final RepositorioAtendimento repositorio;
    private final ServicePaciente servicoPaciente;
    private final ServiceUsuario servicoUsuario;
    private final ServiceAuditoria servicoAuditoria;
    private final HelperAuditoria helperAuditoria;

    // --- LISTAGEM COM FILTROS DINÂMICOS E COMPARTILHAMENTO ---
    public List<Atendimento> listar(UUID usuarioLogadoId, Atendimento.StatusAtendimento status, UUID pacienteId, LocalDate dataInicio, LocalDate dataFim) {
        Usuario usuario = servicoUsuario.buscarPorId(usuarioLogadoId);
        List<UUID> idsPermitidos = new ArrayList<>();

        if (usuario.getClinica() != null) {
            List<Usuario> equipe = servicoUsuario.listarPorClinica(usuario.getClinica().getId());
            idsPermitidos = equipe.stream().map(Usuario::getId).collect(Collectors.toList());
        } else {
            idsPermitidos.add(usuarioLogadoId);
        }

        List<UUID> finalIdsPermitidos = idsPermitidos;
        Specification<Atendimento> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(root.get("veterinarioId").in(finalIdsPermitidos));

            if (status != null) predicates.add(cb.equal(root.get("status"), status));
            if (pacienteId != null) predicates.add(cb.equal(root.get("paciente").get("id"), pacienteId));
            if (dataInicio != null) predicates.add(cb.greaterThanOrEqualTo(root.get("dataHora"), dataInicio.atStartOfDay()));
            if (dataFim != null) predicates.add(cb.lessThanOrEqualTo(root.get("dataHora"), dataFim.atTime(LocalTime.MAX)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return repositorio.findAll(spec, Sort.by(Sort.Direction.ASC, "dataHora"));
    }

    public Atendimento criar(Atendimento novo, UUID pacienteId) {
        Paciente paciente = servicoPaciente.buscarPorId(pacienteId);

        if (!servicoUsuario.existePorId(novo.getVeterinarioId())) {
            throw new ExcecaoRegraNegocio("Veterinário responsável não encontrado.");
        }

        novo.setPaciente(paciente);
        if (novo.getStatus() == null) novo.setStatus(Atendimento.StatusAtendimento.AGENDADO);
        if (novo.getTitulo() == null) novo.setTitulo("Atendimento Clínico");
        if (novo.getDataHora() == null) novo.setDataHora(LocalDateTime.now());

        Atendimento salvo = repositorio.save(novo);

        try {
            Usuario vet = servicoUsuario.buscarPorId(novo.getVeterinarioId());
            if (vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.ATENDIMENTO_AGENDADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Agendamento criado: " + novo.getTitulo(),
                        helperAuditoria.montarMetadadosAtendimento(paciente.getNome(), "AGENDADO", vet.getId())
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salvo;
    }

    public List<Atendimento> listarPorPaciente(UUID pacienteId) {
        if (!servicoPaciente.existePorId(pacienteId)) {
            throw new ExceptionRecursoNaoEncontrado("Paciente", pacienteId.toString());
        }
        return repositorio.findByPacienteIdOrderByCriadoEmDesc(pacienteId);
    }

    public Atendimento buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Atendimento", id.toString()));
    }

    public Atendimento atualizar(UUID id, Atendimento dados) {
        Atendimento existente = buscarPorId(id);
        existente.setTitulo(dados.getTitulo());
        existente.setQueixaPrincipal(dados.getQueixaPrincipal());
        existente.setHistoricoClinico(dados.getHistoricoClinico());
        existente.setExameFisico(dados.getExameFisico());
        existente.setDiagnostico(dados.getDiagnostico());
        existente.setCondutaClinica(dados.getCondutaClinica());
        existente.setObservacoes(dados.getObservacoes());
        existente.setProtocoloId(dados.getProtocoloId());
        existente.setOrientacoesManejo(dados.getOrientacoesManejo());

        if (dados.getStatus() != null) {
            existente.setStatus(dados.getStatus());
        }
        Atendimento salvo = repositorio.save(existente);

        try {
            Usuario vet = servicoUsuario.buscarPorId(existente.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.PRONTUARIO_EDITADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Prontuário editado",
                        helperAuditoria.montarMetadados("campos", "dados_clinicos")
                );
            }
        } catch (Exception e) {}

        return salvo;
    }

    public Atendimento atualizarStatus(UUID id, Atendimento.StatusAtendimento novoStatus) {
        Atendimento a = buscarPorId(id);
        a.setStatus(novoStatus);
        Atendimento salvo = repositorio.save(a);

        try {
            Usuario vet = servicoUsuario.buscarPorId(salvo.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                AcaoAuditoria acao = novoStatus == Atendimento.StatusAtendimento.CANCELADO ?
                        AcaoAuditoria.ATENDIMENTO_CANCELADO : AcaoAuditoria.ATENDIMENTO_EDITADO;

                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        acao,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Mudança de status para " + novoStatus,
                        null
                );
            }
        } catch (Exception e) {}

        return salvo;
    }

    public Atendimento finalizar(UUID id, Atendimento dadosClinicos) {
        Atendimento existente = buscarPorId(id);
        existente.setTitulo(dadosClinicos.getTitulo() != null ? dadosClinicos.getTitulo() : existente.getTitulo());
        existente.setQueixaPrincipal(dadosClinicos.getQueixaPrincipal());
        existente.setHistoricoClinico(dadosClinicos.getHistoricoClinico());
        existente.setExameFisico(dadosClinicos.getExameFisico());
        existente.setDiagnostico(dadosClinicos.getDiagnostico());
        existente.setCondutaClinica(dadosClinicos.getCondutaClinica());
        existente.setObservacoes(dadosClinicos.getObservacoes());
        existente.setProtocoloId(dadosClinicos.getProtocoloId());
        existente.setOrientacoesManejo(dadosClinicos.getOrientacoesManejo());

        existente.setStatus(Atendimento.StatusAtendimento.REALIZADO);
        Atendimento salvo = repositorio.save(existente);

        try {
            Usuario vet = servicoUsuario.buscarPorId(salvo.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.ATENDIMENTO_FINALIZADO,
                        EntidadeAuditoria.ATENDIMENTO,
                        salvo.getId(),
                        "Atendimento finalizado",
                        null
                );
            }
        } catch (Exception e) {}

        return salvo;
    }
}

```

### ServiceExames.java

```java
// src\main\java\br\com\vestris\medicalrecord\application\ServiceExames.java
package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import br.com.vestris.medicalrecord.domain.repository.RepositorioExameAnexo;
import br.com.vestris.medicalrecord.domain.repository.RepositorioAtendimento;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceExames {
    private final RepositorioExameAnexo repositorio;
    private final RepositorioAtendimento repositorioAtendimento;
    private final ServiceAuditoria servicoAuditoria;
    private final ServiceUsuario servicoUsuario;
    private final HelperAuditoria helperAuditoria;

    public ExameAnexo anexar(UUID atendimentoId, String nome, String tipo, String url, String obs) {
        ExameAnexo e = new ExameAnexo();
        e.setAtendimentoId(atendimentoId);
        e.setNomeArquivo(nome);
        e.setTipoArquivo(tipo);
        e.setUrlArquivo(url);
        e.setObservacoes(obs);

        ExameAnexo salvo = repositorio.save(e);

        try {
            var atendimento = repositorioAtendimento.findById(atendimentoId).orElse(null);
            if (atendimento != null) {
                Usuario vet = servicoUsuario.buscarPorId(atendimento.getVeterinarioId());
                if (vet != null && vet.getClinica() != null) {
                    var metadados = helperAuditoria.montarMetadados(
                            "nomeArquivo", nome,
                            "tipoArquivo", tipo,
                            "paciente", atendimento.getPaciente().getNome(),
                            "observacoes", obs
                    );
                    servicoAuditoria.registrar(
                            vet.getClinica().getId(),
                            vet.getId(),
                            AcaoAuditoria.ANEXO_ADICIONADO,
                            EntidadeAuditoria.ANEXO,
                            salvo.getId(),
                            "Anexo/Exame adicionado: " + nome,
                            metadados
                    );
                }
            }
        } catch (Exception ex) {
            System.err.println("Erro ao auditar adição de anexo: " + ex.getMessage());
        }

        return salvo;
    }

    public List<ExameAnexo> listarPorAtendimento(UUID atendimentoId) {
        return repositorio.findByAtendimentoId(atendimentoId);
    }

    public void deletar(UUID id) {
        var anexo = repositorio.findById(id).orElse(null);

        if (anexo != null) {
            repositorio.deleteById(id);

            try {
                var atendimento = repositorioAtendimento.findById(anexo.getAtendimentoId()).orElse(null);
                if (atendimento != null) {
                    Usuario vet = servicoUsuario.buscarPorId(atendimento.getVeterinarioId());
                    if (vet != null && vet.getClinica() != null) {
                        var metadados = helperAuditoria.montarMetadados(
                                "nomeArquivo", anexo.getNomeArquivo(),
                                "tipoArquivo", anexo.getTipoArquivo(),
                                "paciente", atendimento.getPaciente().getNome()
                        );
                        servicoAuditoria.registrar(
                                vet.getClinica().getId(),
                                vet.getId(),
                                AcaoAuditoria.ANEXO_REMOVIDO,
                                EntidadeAuditoria.ANEXO,
                                id,
                                "Anexo/Exame removido: " + anexo.getNomeArquivo(),
                                metadados
                        );
                    }
                }
            } catch (Exception ex) {
                System.err.println("Erro ao auditar remoção de anexo: " + ex.getMessage());
            }
        }
    }
}

```

### ServicePaciente.java

```java
// src\main\java\br\com\vestris\medicalrecord\application\ServicePaciente.java
package br.com.vestris.medicalrecord.application;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioPaciente;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.helper.HelperAuditoria;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicePaciente {
    private final RepositorioPaciente repositorio;
    private final ServiceEspecie serviceEspecie;
    private final ServiceUsuario servicoUsuario;
    private final ServiceAuditoria servicoAuditoria;
    private final HelperAuditoria helperAuditoria;

    public Paciente criar(Paciente novo) {
        if (!serviceEspecie.existePorId(novo.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Espécie informada não existe.");
        }
        if (!servicoUsuario.existePorId(novo.getVeterinarioId())) {
            throw new ExcecaoRegraNegocio("Veterinário informado não encontrado.");
        }

        Paciente salvo = repositorio.save(novo);

        // --- LOG DE AUDITORIA ---
        try {
            Usuario vet = servicoUsuario.buscarPorId(novo.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                var metadados = helperAuditoria.montarMetadadosPaciente(
                        salvo.getNome(),
                        "Espécie ID: " + salvo.getEspecieId(),
                        salvo.getDadosTutor()
                );
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.PACIENTE_CRIADO,
                        EntidadeAuditoria.PACIENTE,
                        salvo.getId(),
                        "Novo paciente criado: " + salvo.getNome(),
                        metadados
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao auditar criação de paciente: " + e.getMessage());
        }

        return salvo;
    }

    public List<Paciente> listarPorVeterinario(UUID usuarioLogadoId) {
        Usuario usuario = servicoUsuario.buscarPorId(usuarioLogadoId);
        if (usuario.getClinica() != null) {
            List<Usuario> equipe = servicoUsuario.listarPorClinica(usuario.getClinica().getId());
            List<UUID> idsEquipe = equipe.stream().map(Usuario::getId).collect(Collectors.toList());
            return repositorio.findByVeterinarioIdIn(idsEquipe);
        }
        return repositorio.findByVeterinarioId(usuarioLogadoId);
    }

    public Paciente buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Paciente", id.toString()));
    }

    public Paciente atualizar(UUID id, Paciente dados) {
        Paciente existente = buscarPorId(id);
        existente.setNome(dados.getNome());
        existente.setDadosTutor(dados.getDadosTutor());
        existente.setIdentificacaoAnimal(dados.getIdentificacaoAnimal());
        existente.setSexo(dados.getSexo());
        existente.setDataNascimento(dados.getDataNascimento());
        existente.setPesoAtualGramas(dados.getPesoAtualGramas());
        existente.setPelagemCor(dados.getPelagemCor());
        existente.setMicrochip(dados.getMicrochip());

        Paciente salvo = repositorio.save(existente);

        try {
            Usuario vet = servicoUsuario.buscarPorId(existente.getVeterinarioId());
            if (vet != null && vet.getClinica() != null) {
                var metadados = helperAuditoria.montarMetadadosPaciente(
                        salvo.getNome(),
                        "Espécie ID: " + salvo.getEspecieId(),
                        salvo.getDadosTutor()
                );
                servicoAuditoria.registrar(
                        vet.getClinica().getId(),
                        vet.getId(),
                        AcaoAuditoria.PACIENTE_EDITADO,
                        EntidadeAuditoria.PACIENTE,
                        salvo.getId(),
                        "Dados do paciente atualizados",
                        metadados
                );
            }
        } catch (Exception e) {}

        return salvo;
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Paciente", id.toString());
        }

        Paciente paciente = buscarPorId(id);

        try {
            repositorio.deleteById(id);

            try {
                Usuario vet = servicoUsuario.buscarPorId(paciente.getVeterinarioId());
                if (vet != null && vet.getClinica() != null) {
                    var metadados = helperAuditoria.montarMetadadosPaciente(
                            paciente.getNome(),
                            "Espécie ID: " + paciente.getEspecieId(),
                            paciente.getDadosTutor()
                    );
                    servicoAuditoria.registrar(
                            vet.getClinica().getId(),
                            vet.getId(),
                            AcaoAuditoria.PACIENTE_CANCELADO,
                            EntidadeAuditoria.PACIENTE,
                            id,
                            "Paciente deletado: " + paciente.getNome(),
                            metadados
                    );
                }
            } catch (Exception e) {}

        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover este paciente pois ele possui atendimentos registrados.");
        }
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }
}

```

---

## src\main\java\br\com\vestris\medicalrecord\domain\model

### Atendimento.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\Atendimento.java
package br.com.vestris.medicalrecord.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atendimentos", schema = "medical_record_schema")
public class Atendimento extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private UUID veterinarioId;

    private UUID protocoloId;

    // --- NOVOS CAMPOS ---
    @Column(nullable = false)
    private String titulo; // Ex: "Consulta Inicial", "Retorno", "Vacinação"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAtendimento status; // AGENDADO, REALIZADO, CANCELADO
    // --------------------

    // --- CORREÇÃO: ADICIONE ESTE CAMPO ---
    @Column(nullable = false)
    private LocalDateTime dataHora; // Data do agendamento ou da consulta
    // -------------------------------------

    @Column(columnDefinition = "TEXT") private String queixaPrincipal;
    @Column(columnDefinition = "TEXT") private String historicoClinico;
    @Column(columnDefinition = "TEXT") private String exameFisico;
    @Column(columnDefinition = "TEXT") private String diagnostico;
    @Column(columnDefinition = "TEXT") private String condutaClinica;
    @Column(columnDefinition = "TEXT") private String orientacoesManejo;
    @Column(columnDefinition = "TEXT") private String observacoes;

    // ADICIONE ESTE ENUM:
    public enum StatusAtendimento {
        AGENDADO,
        EM_ANDAMENTO,
        REALIZADO,
        CANCELADO
    }

}

```

### ExameAnexo.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\ExameAnexo.java
package br.com.vestris.medicalrecord.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exames_anexos", schema = "medical_record_schema")
public class ExameAnexo extends EntidadeBase {
    @Column(nullable = false)
    private UUID atendimentoId; // Vínculo com o atendimento

    @Column(nullable = false)
    private String nomeArquivo;

    private String tipoArquivo; // pdf, jpg, png

    @Column(columnDefinition = "TEXT")
    private String urlArquivo; // URL S3 ou Path Local

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

```

### Paciente.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\model\Paciente.java
package br.com.vestris.medicalrecord.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pacientes", schema = "medical_record_schema")
public class Paciente extends EntidadeBase {

    @Column(nullable = false)
    private UUID veterinarioId;

    @Column(nullable = false)
    private UUID especieId;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String dadosTutor;

    private String identificacaoAnimal; // Ex: Anilha, Marcação

    private String microchip;

    @Column(length = 50)
    private String pelagemCor;

    private String sexo;

    private LocalDate dataNascimento;

    private Integer pesoAtualGramas;

}

```

---

## src\main\java\br\com\vestris\medicalrecord\domain\repository

### RepositorioAtendimento.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioAtendimento.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAtendimento extends JpaRepository<Atendimento, UUID>, JpaSpecificationExecutor<Atendimento> {
    List<Atendimento> findByPacienteIdOrderByCriadoEmDesc(UUID pacienteId);
}

```

### RepositorioExameAnexo.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioExameAnexo.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioExameAnexo extends JpaRepository<ExameAnexo, UUID> {
    List<ExameAnexo> findByAtendimentoId(UUID atendimentoId);
}

```

### RepositorioPaciente.java

```java
// src\main\java\br\com\vestris\medicalrecord\domain\repository\RepositorioPaciente.java
package br.com.vestris.medicalrecord.domain.repository;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioPaciente extends JpaRepository<Paciente, UUID> {
    // Busca os pacientes de um determinado veterinário
    List<Paciente> findByVeterinarioId(UUID veterinarioId);

    // --- NOVO: BUSCA INSTITUCIONAL ---
    // Traz pacientes de qualquer veterinário que esteja na lista (Equipe)
    List<Paciente> findByVeterinarioIdIn(List<UUID> veterinarioIds);
}

```

---

## src\main\java\br\com\vestris\medicalrecord\interfaces\delegate

### ApiDelegateAtendimentos.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegateAtendimentos.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceAtendimento;
import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.interfaces.api.AtendimentosApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.*;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAtendimentos implements AtendimentosApiDelegate {

    private final ServiceAtendimento servico;
    private final ServiceUsuario servicoUsuario;
    private final ServiceEspecie servicoEspecie;

    // --- AGENDAR ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> agendarAtendimento(AgendamentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setTitulo(request.getTitulo());
        a.setProtocoloId(request.getProtocoloId());

        if (request.getDataHora() != null) {
            a.setDataHora(request.getDataHora().toLocalDateTime());
        } else {
            a.setDataHora(LocalDateTime.now());
        }

        a.setStatus(Atendimento.StatusAtendimento.AGENDADO);

        Atendimento salvo = servico.criar(a, request.getPacienteId());
        return ResponseEntity.ok(criarResponse(salvo));
    }

    // --- CRIAR (LEGADO/COMPLETO) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> criarAtendimento(AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setProtocoloId(request.getProtocoloId());
        a.setTitulo(request.getTitulo());

        if (request.getStatus() != null) {
            try {
                a.setStatus(Atendimento.StatusAtendimento.valueOf(request.getStatus().name()));
            } catch (Exception e) { }
        }

        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());
        a.setObservacoes(request.getObservacoes());

        if (request.getDataHora() != null) {
            a.setDataHora(request.getDataHora().toLocalDateTime());
        }

        Atendimento salvo = servico.criar(a, request.getPacienteId());
        return ResponseEntity.ok(criarResponse(salvo));
    }

    // --- LISTAR ---
    @Override
    public ResponseEntity<ApiResponseListaAtendimento> listarMeusAtendimentos(
            UUID veterinarioId,
            StatusAtendimentoEnum status,
            UUID pacienteId,
            java.time.LocalDate dataInicio,
            java.time.LocalDate dataFim
    ) {
        Atendimento.StatusAtendimento statusDomain = status != null
                ? Atendimento.StatusAtendimento.valueOf(status.name())
                : null;

        List<AtendimentoResponse> lista = servico.listar(veterinarioId, statusDomain, pacienteId, dataInicio, dataFim)
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAtendimento r = new ApiResponseListaAtendimento();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseListaAtendimento> listarAtendimentosPorPaciente(UUID pacienteId) {
        List<AtendimentoResponse> lista = servico.listarPorPaciente(pacienteId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAtendimento r = new ApiResponseListaAtendimento();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> buscarAtendimentoPorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    // --- ATUALIZAR (RASCUNHO/PUT) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarAtendimento(UUID id, AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setTitulo(request.getTitulo());

        if (request.getStatus() != null) {
            try {
                a.setStatus(Atendimento.StatusAtendimento.valueOf(request.getStatus().name()));
            } catch (Exception e) {}
        }

        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());

        // CORREÇÃO: Garantindo que Observações sejam passadas
        a.setObservacoes(request.getObservacoes());

        a.setOrientacoesManejo(request.getOrientacoesManejo());

        a.setProtocoloId(request.getProtocoloId());

        Atendimento atualizado = servico.atualizar(id, a);
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    // --- FINALIZAR ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> finalizarAtendimento(UUID id, FinalizacaoAtendimentoRequest request) {
        Atendimento dadosClinicos = new Atendimento();
        dadosClinicos.setTitulo(request.getTitulo());
        dadosClinicos.setQueixaPrincipal(request.getQueixaPrincipal());
        dadosClinicos.setHistoricoClinico(request.getHistoricoClinico());
        dadosClinicos.setExameFisico(request.getExameFisico());
        dadosClinicos.setDiagnostico(request.getDiagnostico());
        dadosClinicos.setCondutaClinica(request.getCondutaClinica());

        // CORREÇÃO: Garantindo observações na finalização
        dadosClinicos.setObservacoes(request.getObservacoes());

        dadosClinicos.setProtocoloId(request.getProtocoloId());

        Atendimento finalizado = servico.finalizar(id, dadosClinicos);
        return ResponseEntity.ok(criarResponse(finalizado));
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarStatusAtendimento(UUID id, AtualizarStatusAtendimentoRequest request) {
        Atendimento.StatusAtendimento novoStatus = Atendimento.StatusAtendimento.valueOf(request.getStatus().name());
        Atendimento atualizado = servico.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    // --- CONVERSORES ---
    private ApiResponseAtendimento criarResponse(Atendimento a) {
        ApiResponseAtendimento r = new ApiResponseAtendimento();
        r.setSucesso(true);
        r.setDados(converter(a));
        return r;
    }

    private AtendimentoResponse converter(Atendimento a) {
        AtendimentoResponse dto = new AtendimentoResponse();
        dto.setId(a.getId());

        // Datas
        if(a.getCriadoEm() != null) dto.setDataHora(a.getCriadoEm().atOffset(ZoneOffset.UTC));
        if(a.getDataHora() != null) dto.setDataHora(a.getDataHora().atOffset(ZoneOffset.UTC));

        dto.setTitulo(a.getTitulo());

        if (a.getStatus() != null) {
            dto.setStatus(StatusAtendimentoEnum.valueOf(a.getStatus().name()));
        }

        // --- LÓGICA DE GOVERNANÇA E PROTEÇÃO (NOVO) ---
        // Na prática, teríamos o contexto de segurança aqui.
        // Como o SecurityContext ainda não foi totalmente integrado neste método (Drop 1),
        // preparamos a estrutura.
        // O FrontEnd já faz o bloqueio visual para 'ADMIN_GESTOR'.
        // No futuro (Drop 2), adicionaremos:
        // if (SecurityUtils.temPerfil("ADMIN_GESTOR")) { censurar() }

        // Por enquanto, enviamos os dados, pois o front do Admin Gestor não deve chamar esta rota de detalhe,
        // ou se chamar, o front esconde. Mas para segurança em profundidade, deixamos o hook pronto:

        boolean censurarDadosClinicos = false; // Mudar para lógica real futuramente

        if (censurarDadosClinicos) {
            dto.setQueixaPrincipal("[DADO PROTEGIDO]");
            dto.setHistoricoClinico("[DADO PROTEGIDO]");
            dto.setExameFisico("[DADO PROTEGIDO]");
            dto.setDiagnostico("[DADO PROTEGIDO]");
            dto.setCondutaClinica("[DADO PROTEGIDO]");
            dto.setObservacoes("[DADO PROTEGIDO]");
            dto.setOrientacoesManejo("[DADO PROTEGIDO]");
        } else {
            dto.setQueixaPrincipal(a.getQueixaPrincipal());
            dto.setHistoricoClinico(a.getHistoricoClinico());
            dto.setExameFisico(a.getExameFisico());
            dto.setDiagnostico(a.getDiagnostico());
            dto.setCondutaClinica(a.getCondutaClinica());
            dto.setOrientacoesManejo(a.getOrientacoesManejo());
            dto.setObservacoes(a.getObservacoes());
        }

        dto.setProtocoloId(a.getProtocoloId());
        dto.setVeterinarioId(a.getVeterinarioId());

        // Dados Relacionados (Enriquecimento)
        try {
            Usuario veterinario = servicoUsuario.buscarPorId(a.getVeterinarioId());
            dto.setVeterinarioNome(veterinario.getNome());
            dto.setVeterinarioCrmv(veterinario.getCrmv());
        } catch (Exception e) {
            dto.setVeterinarioNome("Veterinário não encontrado");
        }

        if (a.getPaciente() != null) {
            dto.setPacienteId(a.getPaciente().getId());
            dto.setPacienteNome(a.getPaciente().getNome());
            try {
                var especie = servicoEspecie.buscarPorId(a.getPaciente().getEspecieId());
                dto.setPacienteEspecie(especie.getNomePopular());
            } catch (Exception e) {
                dto.setPacienteEspecie("Espécie não identificada");
            }
        }
        return dto;
    }
}
```

### ApiDelegateExame.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegateExame.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceExames;
import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import br.com.vestris.medicalrecord.interfaces.api.ExamesApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseExameAnexo;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaExameAnexo;
import br.com.vestris.medicalrecord.interfaces.dto.ExameAnexoResponse;
import br.com.vestris.user.application.ServiceAuditoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateExame implements ExamesApiDelegate {

    private final ServiceExames servico;
    private final ServiceAuditoria serviceAuditoria;

    @Override
    public ResponseEntity<ApiResponseExameAnexo> uploadExame(UUID atendimentoId, MultipartFile arquivo, String observacoes) {
        // Validação básica de arquivo
        if (arquivo == null || arquivo.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Ou lance uma ExcecaoRegraNegocio
        }

        // Lógica de "Upload" (Passamos a responsabilidade pro Service lidar com bytes/S3)
        // Aqui simulamos que o Service vai salvar e devolver a entidade com a URL gerada

        String nomeOriginal = arquivo.getOriginalFilename();
        String tipoConteudo = arquivo.getContentType();

        // Dica: Em um cenário real, você passaria 'arquivo.getInputStream()' para o service.
        // Como combinamos que o Service receberia Strings no passo anterior,
        // vamos simular a URL aqui ou ajustar o Service para aceitar MultipartFile (recomendado).

        // Assumindo que o ServiceExames foi ajustado para receber os metadados e gerar a URL:
        // urlSimulada = "https://s3.amazon..." ou "/uploads/..."
        String urlSimulada = "https://storage.vestris.com/" + UUID.randomUUID() + "_" + nomeOriginal;

        ExameAnexo salvo = servico.anexar(
                atendimentoId,
                nomeOriginal,
                tipoConteudo,
                urlSimulada,
                observacoes
        );

        ApiResponseExameAnexo response = new ApiResponseExameAnexo();
        response.setSucesso(true);
        response.setMensagem("Arquivo anexado com sucesso.");
        response.setDados(converter(salvo));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaExameAnexo> listarExamesPorAtendimento(UUID atendimentoId) {
        List<ExameAnexoResponse> lista = servico.listarPorAtendimento(atendimentoId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaExameAnexo response = new ApiResponseListaExameAnexo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarExame(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ---
    private ExameAnexoResponse converter(ExameAnexo entidade) {
        ExameAnexoResponse dto = new ExameAnexoResponse();
        dto.setId(entidade.getId());
        dto.setAtendimentoId(entidade.getAtendimentoId());
        dto.setNomeArquivo(entidade.getNomeArquivo());
        dto.setTipoArquivo(entidade.getTipoArquivo());
        dto.setUrlArquivo(entidade.getUrlArquivo());
        dto.setObservacoes(entidade.getObservacoes());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegatePacientes.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegatePacientes.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServicePaciente;
import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.interfaces.api.PacientesApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaPaciente;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponsePaciente;
import br.com.vestris.medicalrecord.interfaces.dto.PacienteRequest;
import br.com.vestris.medicalrecord.interfaces.dto.PacienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePacientes implements PacientesApiDelegate {

    private final ServicePaciente servico;

    @Override
    public ResponseEntity<ApiResponsePaciente> criarPaciente(PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setEspecieId(request.getEspecieId());
        p.setVeterinarioId(request.getVeterinarioId());
        p.setDadosTutor(request.getDadosTutor());
        p.setIdentificacaoAnimal(request.getIdentificacaoAnimal());
        p.setDataNascimento(request.getDataNascimento());
        p.setPesoAtualGramas(request.getPesoAtualGramas());

        // Novos campos
        p.setMicrochip(request.getMicrochip());
        p.setPelagemCor(request.getPelagemCor());

        // Enum (Agora converte bonito)
        if(request.getSexo() != null) {
            p.setSexo(request.getSexo().name());
        }

        Paciente salvo = servico.criar(p);

        ApiResponsePaciente response = new ApiResponsePaciente();
        response.setSucesso(true);
        response.setDados(converter(salvo));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaPaciente> listarPacientes(UUID veterinarioId) {
        // O serviço já valida se o vet existe. Se não existir, lança exceção tratada.
        List<PacienteResponse> lista = servico.listarPorVeterinario(veterinarioId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPaciente response = new ApiResponseListaPaciente();
        response.setSucesso(true);
        // Se a lista estiver vazia, o JSON será { "sucesso": true, "dados": [] } -> PERFEITO
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePaciente> buscarPacientePorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ApiResponsePaciente> atualizarPaciente(UUID id, PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setDadosTutor(request.getDadosTutor());
        p.setIdentificacaoAnimal(request.getIdentificacaoAnimal());
        p.setDataNascimento(request.getDataNascimento());
        p.setPesoAtualGramas(request.getPesoAtualGramas());
        p.setMicrochip(request.getMicrochip());
        p.setPelagemCor(request.getPelagemCor());
        if(request.getSexo() != null) p.setSexo(request.getSexo().name());

        return ResponseEntity.ok(criarResponse(servico.atualizar(id, p)));
    }

    @Override
    public ResponseEntity<Void> deletarPaciente(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPERS ---
    private ApiResponsePaciente criarResponse(Paciente p) {
        ApiResponsePaciente r = new ApiResponsePaciente();
        r.setSucesso(true);
        r.setDados(converter(p));
        return r;
    }

    private PacienteResponse converter(Paciente p) {
        PacienteResponse dto = new PacienteResponse();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEspecieId(p.getEspecieId());
        dto.setVeterinarioId(p.getVeterinarioId()); // Agora existe no DTO
        dto.setDadosTutor(p.getDadosTutor());
        dto.setIdentificacaoAnimal(p.getIdentificacaoAnimal());

        // Novos campos no DTO
        dto.setDataNascimento(p.getDataNascimento());
        dto.setMicrochip(p.getMicrochip());
        dto.setPelagemCor(p.getPelagemCor());
        dto.setPesoAtualGramas(p.getPesoAtualGramas());

        if (p.getCriadoEm() != null) dto.setCriadoEm(p.getCriadoEm().atOffset(ZoneOffset.UTC));

        // Conversão do Enum (Agora o DTO espera SexoEnum)
        try {
            if(p.getSexo() != null) {
                // Importe o SexoEnum do pacote interfaces.dto
                dto.setSexo(br.com.vestris.medicalrecord.interfaces.dto.SexoEnum.valueOf(p.getSexo()));
            }
        } catch (Exception e) {
            // Se o valor no banco for inválido, deixa nulo ou trata
        }

        return dto;
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Prontuário Eletrônico
  version: 1.0.0
servers:
  - url: http://localhost:8080

paths:
  # --- PACIENTES ---
  /api/v1/pacientes:
    $ref: './paths/pacientes.yml#/pacientes_item'
  /api/v1/pacientes/{id}:
    $ref: './paths/pacientes.yml#/paciente_por_id'

  # --- ATENDIMENTOS ---

  # CORREÇÃO: Adicionada rota de Agendamento
  /api/v1/atendimentos/agendar:
    $ref: './paths/atendimentos.yml#/agendamento_item'

  /api/v1/atendimentos:
    $ref: './paths/atendimentos.yml#/atendimentos_item'

  /api/v1/atendimentos/{id}:
    $ref: './paths/atendimentos.yml#/atendimentos_por_id'

  /api/v1/atendimentos/{id}/status:
    $ref: './paths/atendimentos.yml#/atendimentos_status'

  /api/v1/pacientes/{pacienteId}/atendimentos:
    $ref: './paths/atendimentos.yml#/atendimentos_por_paciente'

  /api/v1/atendimentos/{id}/finalizar:
    $ref: './paths/atendimentos.yml#/atendimentos_finalizados'

    # --- NOVAS ROTAS DE EXAMES ---
  /api/v1/atendimentos/{atendimentoId}/exames:
    $ref: './paths/exames-anexo.yml#/exames_por_atendimento'

  /api/v1/exames/{id}:
    $ref: './paths/exames-anexo.yml#/exame_item'

components:
  schemas:
    PacienteRequest:
      $ref: "./components/schemas.yml#/PacienteRequest"
    PacienteResponse:
      $ref: "./components/schemas.yml#/PacienteResponse"

    # CORREÇÃO: Adicionados os DTOs novos para o gerador criar o Java
    AgendamentoRequest:
      $ref: "./components/schemas.yml#/AgendamentoRequest"
    FinalizacaoAtendimentoRequest:
      $ref: "./components/schemas.yml#/FinalizacaoAtendimentoRequest"

    AtendimentoRequest:
      $ref: "./components/schemas.yml#/AtendimentoRequest"
    AtendimentoResponse:
      $ref: "./components/schemas.yml#/AtendimentoResponse"
    ApiResponsePaciente:
      $ref: "./components/schemas.yml#/ApiResponsePaciente"
    ApiResponseListaPaciente:
      $ref: "./components/schemas.yml#/ApiResponseListaPaciente"
    ApiResponseAtendimento:
      $ref: "./components/schemas.yml#/ApiResponseAtendimento"
    ApiResponseListaAtendimento:
      $ref: "./components/schemas.yml#/ApiResponseListaAtendimento"
    StatusAtendimentoEnum:
      $ref: "./components/schemas.yml#/StatusAtendimentoEnum"
    SexoEnum:
      $ref: "./components/schemas.yml#/SexoEnum"
    ExameAnexoResponse:
      $ref: "./components/schemas.yml#/ExameAnexoResponse"
    ApiResponseExameAnexo:
      $ref: "./components/schemas.yml#/ApiResponseExameAnexo"
    ApiResponseListaExameAnexo:
      $ref: "./components/schemas.yml#/ApiResponseListaExameAnexo"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# --- ENUM COMPARTILHADO ---
SexoEnum:
  type: string
  enum: [MACHO, FEMEA, INDEFINIDO]

StatusAtendimentoEnum:
  type: string
  enum: [AGENDADO, EM_ANDAMENTO, REALIZADO, CANCELADO]

# --- PACIENTE ---
PacienteRequest:
  type: object
  required: [veterinarioId, especieId, nome, dadosTutor]
  properties:
    veterinarioId: { type: string, format: uuid }
    especieId: { type: string, format: uuid }
    nome: { type: string }
    dadosTutor: { type: string }
    identificacaoAnimal: { type: string }
    microchip: { type: string }
    pelagemCor: { type: string }
    sexo: { $ref: '#/SexoEnum' }
    dataNascimento: { type: string, format: date }
    pesoAtualGramas: { type: integer }

PacienteResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    especieId: { type: string, format: uuid }
    dadosTutor: { type: string }
    identificacaoAnimal: { type: string }
    pesoAtualGramas: { type: integer }
    criadoEm: { type: string, format: date-time }
    veterinarioId: { type: string, format: uuid }
    dataNascimento: { type: string, format: date }
    microchip: { type: string }
    pelagemCor: { type: string }
    sexo: { $ref: '#/SexoEnum' }

# --- DTO DE AGENDAMENTO (Criação Leve) ---
AgendamentoRequest:
  type: object
  required: [pacienteId, veterinarioId, titulo, dataHora]
  properties:
    pacienteId: { type: string, format: uuid }
    veterinarioId: { type: string, format: uuid }
    titulo: { type: string, example: "Consulta de Rotina" }
    dataHora: { type: string, format: date-time }
    protocoloId: { type: string, format: uuid }

# --- DTO DE PRONTUÁRIO (Finalização Rigorosa) ---
FinalizacaoAtendimentoRequest:
  type: object
  required: [queixaPrincipal, condutaClinica, diagnostico]
  properties:
    titulo: { type: string }
    protocoloId: { type: string, format: uuid }
    queixaPrincipal: { type: string }
    historicoClinico: { type: string }
    exameFisico: { type: string }
    diagnostico: { type: string }
    condutaClinica: { type: string }
    observacoes: { type: string }

# --- DTO GENÉRICO (Atualização/Legado) ---
AtendimentoRequest:
  type: object
  required: [pacienteId, veterinarioId, titulo]
  properties:
    pacienteId: { type: string, format: uuid }
    veterinarioId: { type: string, format: uuid }
    protocoloId: { type: string, format: uuid }
    dataHora: { type: string, format: date-time }
    titulo: { type: string }
    status: { $ref: '#/StatusAtendimentoEnum' }
    queixaPrincipal: { type: string }
    historicoClinico: { type: string }
    exameFisico: { type: string }
    diagnostico: { type: string }
    condutaClinica: { type: string }
    observacoes: { type: string }
    orientacoesManejo:
      type: string
      description: "JSON stringificado contendo os 8 pilares de manejo"

AtendimentoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    pacienteId: { type: string, format: uuid }
    dataHora: { type: string, format: date-time }
    titulo: { type: string }
    status: { $ref: '#/StatusAtendimentoEnum' }
    queixaPrincipal: { type: string }
    historicoClinico: { type: string }
    exameFisico: { type: string }
    diagnostico: { type: string }
    condutaClinica: { type: string }
    observacoes: { type: string }
    protocoloId: { type: string, format: uuid }
    veterinarioId: { type: string, format: uuid }
    veterinarioNome: { type: string }
    veterinarioCrmv: { type: string }
    pacienteNome: { type: string }
    pacienteEspecie: { type: string }
    orientacoesManejo: { type: string }

ExameAnexoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    atendimentoId:
      type: string
      format: uuid
    nomeArquivo:
      type: string
      example: "hemograma_thor.pdf"
    tipoArquivo:
      type: string
      example: "application/pdf"
    urlArquivo:
      type: string
      description: "URL assinada ou caminho público do arquivo"
    observacoes:
      type: string
    criadoEm:
      type: string
      format: date-time

# Wrappers
ApiResponseExameAnexo:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/ExameAnexoResponse' }

# --- WRAPPERS ---

ApiResponseListaExameAnexo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ExameAnexoResponse' }

ApiResponsePaciente:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/PacienteResponse' }
ApiResponseListaPaciente:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/PacienteResponse' } }
ApiResponseAtendimento:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/AtendimentoResponse' }
ApiResponseListaAtendimento:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/AtendimentoResponse' } }
```

---

## src\main\resources\swagger\paths

### atendimentos.yml

```yaml
# src\main\resources\swagger\paths\atendimentos.yml
# Rota: /api/v1/atendimentos/agendar (NOVA ROTA DE AGENDAMENTO)
agendamento_item:
  post:
    tags: [Atendimentos]
    summary: Agendar novo atendimento
    description: "Cria um registro com status AGENDADO. Dados clínicos não são permitidos aqui."
    operationId: agendarAtendimento
    requestBody:
      content:
        application/json:
          # CORREÇÃO: Aponta para AgendamentoRequest
          schema: { $ref: '../components/schemas.yml#/AgendamentoRequest' }
    responses:
      '200':
        description: Agendado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# Rota: /api/v1/atendimentos (Rota Legada/Genérica - Mantida por compatibilidade ou criação direta completa)
atendimentos_item:
  post:
    tags: [Atendimentos]
    summary: Registrar atendimento completo (Legado)
    operationId: criarAtendimento
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtendimentoRequest' }
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
  get:
    tags: [Atendimentos]
    summary: Listar meus atendimentos (Agenda)
    operationId: listarMeusAtendimentos
    parameters:
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: status
        in: query
        required: false
        schema: { $ref: '../components/schemas.yml#/StatusAtendimentoEnum' }
      - name: pacienteId
        in: query
        required: false
        schema: { type: string, format: uuid }
      - name: dataInicio
        in: query
        required: false
        schema: { type: string, format: date }
      - name: dataFim
        in: query
        required: false
        schema: { type: string, format: date }
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAtendimento' }

# Rota: /api/v1/atendimentos/{id}/finalizar (FINALIZAÇÃO)
atendimentos_finalizados:
  put:
    tags: [Atendimentos]
    summary: Finalizar atendimento (Preencher Prontuário)
    description: "Recebe os dados clínicos e muda status para REALIZADO"
    operationId: finalizarAtendimento
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          # CORREÇÃO: Aponta para FinalizacaoAtendimentoRequest (Valida campos obrigatórios)
          schema: { $ref: '../components/schemas.yml#/FinalizacaoAtendimentoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# Rota: /api/v1/pacientes/{pacienteId}/atendimentos
atendimentos_por_paciente:
  get:
    tags: [Atendimentos]
    summary: Histórico clínico do paciente
    operationId: listarAtendimentosPorPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Histórico
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAtendimento' }

# ROTA: /api/v1/atendimentos/{id}
atendimentos_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [Atendimentos]
    summary: Ver detalhes
    operationId: buscarAtendimentoPorId
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
  put:
    tags: [Atendimentos]
    summary: Atualizar (Evoluir status, adicionar obs)
    operationId: atualizarAtendimento
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtendimentoRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }

# ROTA: /api/v1/atendimentos/{id}/status:
atendimentos_status:
  patch:
    tags: [Atendimentos]
    summary: Alterar status do atendimento
    operationId: atualizarStatusAtendimento
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema:
            type: object
            properties:
              status: { $ref: '../components/schemas.yml#/StatusAtendimentoEnum' }
    responses:
      '200':
        description: Status atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAtendimento' }
```

### exames-anexo.yml

```yaml
# src\main\resources\swagger\paths\exames-anexo.yml
# vestris-medical-record/src/main/resources/swagger/paths/exames-anexos.yml

# ROTA: /api/v1/atendimentos/{atendimentoId}/exames
exames_por_atendimento:
  get:
    tags: [Exames]
    summary: Listar exames de um atendimento
    operationId: listarExamesPorAtendimento
    parameters:
      - name: atendimentoId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaExameAnexo' }

  post:
    tags: [Exames]
    summary: Anexar novo exame/arquivo
    operationId: uploadExame
    parameters:
      - name: atendimentoId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        multipart/form-data:
          schema:
            type: object
            required: [ arquivo ]
            properties:
              arquivo:
                type: string
                format: binary
                description: "Arquivo PDF, JPG ou PNG"
              observacoes:
                type: string
                description: "Notas sobre o exame (opcional)"
    responses:
      '201':
        description: Upload realizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseExameAnexo' }

# ROTA: /api/v1/exames/{id}
exame_item:
  delete:
    tags: [Exames]
    summary: Remover anexo
    operationId: deletarExame
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso
```

### pacientes.yml

```yaml
# src\main\resources\swagger\paths\pacientes.yml
# Rota: /api/v1/pacientes
pacientes_item:
  post:
    tags: [Pacientes]
    summary: Cadastrar novo paciente
    operationId: criarPaciente
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PacienteRequest' }
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }
  get:
    tags: [Pacientes]
    summary: Listar meus pacientes
    description: "Filtra pelo veterinário logado (ou ID passado)"
    operationId: listarPacientes
    parameters:
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaPaciente' }

# Rota: /api/v1/pacientes/{id}
paciente_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [Pacientes]
    summary: Buscar paciente por ID
    operationId: buscarPacientePorId
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }
  put:
    tags: [Pacientes]
    summary: Atualizar dados do paciente
    operationId: atualizarPaciente
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PacienteRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePaciente' }

  # --- BLOCO ADICIONADO ---
  delete:
    tags: [Pacientes]
    summary: Remover paciente
    description: "Remove o paciente apenas se não houver atendimentos vinculados"
    operationId: deletarPaciente
    responses:
      '204':
        description: Removido com sucesso (No Content)
```


---

# Projeto: vestris-pharmacology

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-pharmacology</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-species</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Para validar se a Referência existe -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-reference</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Dependências do Swagger/JPA iguais aos outros módulos -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.pharmacology.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.pharmacology.interfaces.dto</modelPackage>

                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

### vestris-pharmacology.iml

```iml
# vestris-pharmacology.iml
<?xml version="1.0" encoding="UTF-8"?>
<module version="4">
  <component name="AdditionalModuleElements">
    <content url="file://$MODULE_DIR$/../vestris-pharmacology" dumb="true">
      <sourceFolder url="file://$MODULE_DIR$/../vestris-pharmacology/target/generated-sources/openapi/src/main/java" isTestSource="false" generated="true" />
    </content>
  </component>
</module>
```

---

## src\main\java\br\com\vestris\pharmacology\application

### ServiceContraindicacao.java

```java
// src\main\java\br\com\vestris\pharmacology\application\ServiceContraindicacao.java
package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.domain.repository.RepositorioContraindicacao;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioPrincipioAtivo;
import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceContraindicacao {
    private final RepositorioContraindicacao repoContraindicacao;
    private final RepositorioMedicamento repoMedicamento;
    private final RepositorioPrincipioAtivo repoPrincipio;
    private final ServiceEspecie serviceEspecie;

    @Transactional
    public Contraindicacao criar(UUID medicamentoId, UUID principioAtivoId, UUID especieId, String referenciaTexto,
                                 Contraindicacao.Gravidade gravidade, String descricao) {

        PrincipioAtivo principio = null;

        // 1. Tenta pelo Princípio Ativo (Prioridade para o Admin)
        if (principioAtivoId != null) {
            principio = repoPrincipio.findById(principioAtivoId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Princípio Ativo", principioAtivoId.toString()));
        }
        // 2. Fallback: Tenta pelo Medicamento (Legado)
        else if (medicamentoId != null) {
            Medicamento med = repoMedicamento.findById(medicamentoId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));
            principio = med.getPrincipioAtivo();
        } else {
            throw new ExcecaoRegraNegocio("Informe o Medicamento ou o Princípio Ativo.");
        }

        // 2. Validar Espécie
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExcecaoRegraNegocio("Espécie não encontrada.");
        }

        // 3. Validar Duplicidade
        if (repoContraindicacao.existsByPrincipioAtivoIdAndEspecieId(principio.getId(), especieId)) {
            throw new ExcecaoRegraNegocio("Já existe uma contraindicação deste princípio ativo para esta espécie.");
        }

        Contraindicacao nova = new Contraindicacao();
        nova.setPrincipioAtivo(principio);
        nova.setEspecieId(especieId);
        nova.setReferenciaTexto(referenciaTexto);
        nova.setGravidade(gravidade);
        nova.setDescricao(descricao);

        return repoContraindicacao.save(nova);
    }

    public List<Contraindicacao> listarPorMedicamento(UUID medicamentoId) {
        Medicamento med = repoMedicamento.findById(medicamentoId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));

        // Busca contraindicações ligadas ao princípio ativo deste medicamento
        return repoContraindicacao.findByPrincipioAtivoId(med.getPrincipioAtivo().getId());
    }

    public Contraindicacao buscarPorId(UUID id) {
        return repoContraindicacao.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Contraindicação", id.toString()));
    }

    @Transactional
    public Contraindicacao atualizar(UUID id, UUID novoEspecieId, String novaReferencia,
                                     Contraindicacao.Gravidade novaGravidade, String novaDescricao) {

        Contraindicacao existente = buscarPorId(id);

        // 1. Se mudou a Espécie
        if (!existente.getEspecieId().equals(novoEspecieId)) {
            if (!serviceEspecie.existePorId(novoEspecieId)) {
                throw new ExcecaoRegraNegocio("A nova Espécie informada não existe.");
            }
            if (repoContraindicacao.existsByPrincipioAtivoIdAndEspecieId(existente.getPrincipioAtivo().getId(), novoEspecieId)) {
                throw new ExcecaoRegraNegocio("Já existe uma contraindicação para a nova espécie selecionada.");
            }
            existente.setEspecieId(novoEspecieId);
        }

        // 2. Atualiza dados simples
        existente.setReferenciaTexto(novaReferencia);
        existente.setGravidade(novaGravidade);
        existente.setDescricao(novaDescricao);

        return repoContraindicacao.save(existente);
    }

    public void deletar(UUID id) {
        if (!repoContraindicacao.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Contraindicação", id.toString());
        }
        repoContraindicacao.deleteById(id);
    }
}

```

### ServiceDoseReferencia.java

```java
// src\main\java\br\com\vestris\pharmacology\application\ServiceDoseReferencia.java
package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import br.com.vestris.pharmacology.domain.repository.RepositorioDoseReferencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ServiceDoseReferencia {
    private final RepositorioDoseReferencia repositorio;

    public DoseReferencia buscarMelhorReferencia(UUID medicamentoId, UUID especieId, UUID doencaId, String viaString, UUID clinicaId) {

        // 1. Resolve o Enum da Via de forma segura
        ViaAdministracao viaAlvo = null;
        if (viaString != null && !viaString.isBlank()) {
            try {
                // Remove espaços e converte para maiúsculo (ex: "Oral " -> "ORAL")
                viaAlvo = ViaAdministracao.valueOf(viaString.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                // Se o usuário digitou algo não mapeado, ignoramos a via na busca específica
                System.out.println("Via ignorada na validação (não mapeada): " + viaString);
            }
        }

        // 2. Busca TUDO do banco para este remédio (Query simples, sem erro de SQL)
        List<DoseReferencia> referencias = repositorio.findByMedicamentoId(medicamentoId);

        if (referencias.isEmpty()) return null;

        // 3. Filtra e Pontua em Memória (Java)
        ViaAdministracao finalViaAlvo = viaAlvo;

        return referencias.stream()
                // --- FILTROS DE ELEGIBILIDADE (Match ou Genérico) ---
                .filter(ref -> {
                    // Clínica: Ou é oficial (null) ou é da minha clínica
                    boolean clinicaMatch = ref.getClinicaId() == null || (clinicaId != null && ref.getClinicaId().equals(clinicaId));

                    // Espécie: Ou é genérica (null) ou é a minha espécie exata
                    boolean especieMatch = ref.getEspecieId() == null || (especieId != null && ref.getEspecieId().equals(especieId));

                    // Doença: Ou é genérica (null) ou é a minha doença exata
                    boolean doencaMatch = ref.getDoencaId() == null || (doencaId != null && ref.getDoencaId().equals(doencaId));

                    // Via: Ou é genérica (null) ou é a via que eu pedi
                    boolean viaMatch = ref.getVia() == null || (finalViaAlvo != null && ref.getVia() == finalViaAlvo);

                    return clinicaMatch && especieMatch && doencaMatch && viaMatch;
                })
                // --- ORDENAÇÃO POR SCORE (O Melhor Match vence) ---
                .max(Comparator.comparingInt(ref -> calcularScore(ref, especieId, doencaId, finalViaAlvo, clinicaId)))
                .orElse(null);
    }

    private int calcularScore(DoseReferencia ref, UUID especieId, UUID doencaId, ViaAdministracao viaAlvo, UUID clinicaId) {
        int score = 0;

        // Doença bateu exato? (Peso Máximo)
        if (ref.getDoencaId() != null && ref.getDoencaId().equals(doencaId)) score += 1000;

        // Espécie bateu exato? (Peso Alto)
        if (ref.getEspecieId() != null && ref.getEspecieId().equals(especieId)) score += 100;

        // Via bateu exata? (Peso Médio)
        if (ref.getVia() != null && ref.getVia() == viaAlvo) score += 10;

        // É Institucional? (Desempate - Prefere regra da casa sobre oficial se for específica igual)
        if (ref.getClinicaId() != null && ref.getClinicaId().equals(clinicaId)) score += 1;

        return score;
    }
}

```

### ServiceFarmacologia.java

```java
// src\main\java\br\com\vestris\pharmacology\application\ServiceFarmacologia.java
package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioPrincipioAtivo;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceFarmacologia {
    private final RepositorioPrincipioAtivo repoPrincipio;
    private final RepositorioMedicamento repoMedicamento;


    // PRINCÍPIOS ATIVOS
    public PrincipioAtivo criarPrincipio(PrincipioAtivo novo) {
        if (repoPrincipio.existsByNome(novo.getNome())) {
            throw new ExcecaoRegraNegocio("Princípio ativo já cadastrado: " + novo.getNome());
        }
        return repoPrincipio.save(novo);
    }

    public List<PrincipioAtivo> listarPrincipios() {
        return repoPrincipio.findAll();
    }

    public PrincipioAtivo buscarPrincipioPorId(UUID id) {
        return repoPrincipio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Princípio Ativo", id.toString()));
    }

    public PrincipioAtivo atualizarPrincipio(UUID id, PrincipioAtivo dados) {
        PrincipioAtivo existente = buscarPrincipioPorId(id);

        // Se mudar o nome, verifica duplicidade
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) && repoPrincipio.existsByNome(dados.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe outro princípio ativo com este nome.");
        }

        existente.setNome(dados.getNome());
        existente.setDescricao(dados.getDescricao());
        existente.setGrupoFarmacologico(dados.getGrupoFarmacologico());

        return repoPrincipio.save(existente);
    }

    public void deletarPrincipio(UUID id) {
        if (!repoPrincipio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Princípio Ativo", id.toString());
        }
        try {
            repoPrincipio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // O banco travou porque tem medicamento usando este princípio
            throw new ExcecaoRegraNegocio("Não é possível remover este princípio ativo pois existem medicamentos vinculados a ele.");
        }
    }


    // MEDICAMENTOS

    public Medicamento criarMedicamento(Medicamento novo, UUID principioAtivoId) {
        PrincipioAtivo pa = buscarPrincipioPorId(principioAtivoId);
        novo.setPrincipioAtivo(pa);
        return repoMedicamento.save(novo);
    }

    public List<Medicamento> listarMedicamentos() {
        return repoMedicamento.findAll();
    }

    public Medicamento buscarMedicamentoPorId(UUID id) {
        return repoMedicamento.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", id.toString()));
    }

    public Medicamento atualizarMedicamento(UUID id, Medicamento dados, UUID novoPrincipioId) {
        Medicamento existente = buscarMedicamentoPorId(id);

        // Se o ID do princípio ativo mudou, buscamos o novo
        if (!existente.getPrincipioAtivo().getId().equals(novoPrincipioId)) {
            PrincipioAtivo novoPa = buscarPrincipioPorId(novoPrincipioId);
            existente.setPrincipioAtivo(novoPa);
        }

        existente.setNome(dados.getNome());
        existente.setConcentracao(dados.getConcentracao());
        existente.setFabricante(dados.getFabricante());
        existente.setFormaFarmaceutica(dados.getFormaFarmaceutica());

        return repoMedicamento.save(existente);
    }

    public void deletarMedicamento(UUID id) {
        if (!repoMedicamento.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Medicamento", id.toString());
        }
        try {
            repoMedicamento.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // O banco travou porque tem Contraindicação ou Protocolo (em outro módulo se houver FK real, mas aqui é provavel Contraindicação)
            throw new ExcecaoRegraNegocio("Não é possível remover este medicamento pois ele está sendo usado em contraindicações ou protocolos.");
        }
    }

    // Método auxiliar para outros módulos
    public boolean existeMedicamentoPorId(UUID id) {
        return repoMedicamento.existsById(id);
    }

    public Medicamento buscarEntidadePorId(UUID id) {
        return buscarMedicamentoPorId(id); // Reusa o método que já lança exceção se não achar
    }
}

```

### ServiceSegurancaFarmacologica.java

```java
// src\main\java\br\com\vestris\pharmacology\application\ServiceSegurancaFarmacologica.java
package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.domain.repository.RepositorioContraindicacao;
import br.com.vestris.pharmacology.domain.repository.RepositorioMedicamento;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceSegurancaFarmacologica {
    private final RepositorioContraindicacao repoContra;
    private final RepositorioMedicamento repoMedicamento;

    public List<Contraindicacao> validarMedicamentoParaEspecie(UUID medicamentoId, UUID especieId) {
        // 1. Achar o medicamento
        Medicamento med = repoMedicamento.findById(medicamentoId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Medicamento", medicamentoId.toString()));

        // 2. Descobrir o Princípio Ativo (A regra é ligada à molécula)
        if (med.getPrincipioAtivo() == null) {
            return List.of(); // Sem princípio cadastrado, sem validação automática
        }

        UUID principioId = med.getPrincipioAtivo().getId();

        // 3. Buscar riscos no banco
        return repoContra.encontrarRiscos(principioId, especieId);
    }
}

```

---

## src\main\java\br\com\vestris\pharmacology\domain\model

### Contraindicacao.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\Contraindicacao.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.pharmacology.interfaces.dto.MedicamentoRequest;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contraindicacoes", schema = "pharmacology_schema")
public class Contraindicacao extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "principio_ativo_id", nullable = false)
    private PrincipioAtivo principioAtivo;

    @Column(name = "especie_id", nullable = false)
    private UUID especieId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gravidade gravidade;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    private String referenciaTexto;

    public enum Gravidade {
        LEVE, MODERADA, GRAVE, FATAL
    }
}

```

### DoseReferencia.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\DoseReferencia.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.pharmacology.domain.model.enums.UnidadeDose;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "doses_referencia", schema = "pharmacology_schema")
public class DoseReferencia extends EntidadeBase {

    @Column(nullable = false)
    private UUID medicamentoId;
    private UUID especieId;
    private UUID doencaId;
    private UUID clinicaId;

    @Enumerated(EnumType.STRING)
    private ViaAdministracao via;

    @Enumerated(EnumType.STRING)
    private UnidadeDose unidade;

    // NUMERIC no banco -> BigDecimal no Java
    private BigDecimal doseMin;
    private BigDecimal doseMax;

    private String origem; // OFICIAL ou INSTITUCIONAL (String pra simplificar persistencia com Check)
    private String nivelConfianca;
    private String fonteBibliografica;
    private String observacoes;

}

```

### Medicamento.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\Medicamento.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medicamentos", schema = "pharmacology_schema")
public class Medicamento extends EntidadeBase {
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 50)
    private String concentracao; // Ex: 10mg/ml

    private String fabricante;

    private String formaFarmaceutica; // Ex: Comprimido, Injetável

    // Relacionamento forte: Medicamento PRECISA de um Princípio Ativo
    @ManyToOne(optional = false)
    @JoinColumn(name = "principio_ativo_id", nullable = false)
    private PrincipioAtivo principioAtivo;

}

```

### PrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\model\PrincipioAtivo.java
package br.com.vestris.pharmacology.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "principios_ativos", schema = "pharmacology_schema")
public class PrincipioAtivo extends EntidadeBase {

    @Column(nullable = false, unique = true, length = 150)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 100)
    private String grupoFarmacologico;

}

```

---

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

---

## src\main\java\br\com\vestris\pharmacology\domain\repository

### RepositorioContraindicacao.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioContraindicacao.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioContraindicacao extends JpaRepository<Contraindicacao, UUID> {
    // CORREÇÃO: Busca por Princípio Ativo
    List<Contraindicacao> findByPrincipioAtivoId(UUID principioAtivoId);

    boolean existsByPrincipioAtivoIdAndEspecieId(UUID principioAtivoId, UUID especieId);

    // Para a validação de segurança
    @Query("SELECT c FROM Contraindicacao c WHERE c.principioAtivo.id = :principioId AND c.especieId = :especieId")
    List<Contraindicacao> encontrarRiscos(UUID principioId, UUID especieId);
}

```

### RepositorioDoseReferencia.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioDoseReferencia.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioDoseReferencia extends JpaRepository<DoseReferencia, UUID> {
    List<DoseReferencia> findByMedicamentoId(UUID medicamentoId);
}

```

### RepositorioMedicamento.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioMedicamento.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioMedicamento extends JpaRepository<Medicamento, UUID> {

}

```

### RepositorioPrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\domain\repository\RepositorioPrincipioAtivo.java
package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioPrincipioAtivo extends JpaRepository<PrincipioAtivo, UUID> {
    boolean existsByNome(String nome);
}


```

---

## src\main\java\br\com\vestris\pharmacology\interfaces\delegate

### ApiDelegateContraindicacoes.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateContraindicacoes.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceContraindicacao;
import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.interfaces.api.ContraindicacoesApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateContraindicacoes implements ContraindicacoesApiDelegate {

    private final ServiceContraindicacao servico;

    @Override
    public ResponseEntity<ApiResponseContraindicacao> criarContraindicacao(ContraindicacaoRequest request) {
        Contraindicacao.Gravidade gravidade = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        // Captura a referência ou define um padrão caso venha nulo
        String referencia = request.getReferenciaTexto() != null ? request.getReferenciaTexto() : "Referência interna";

        // Chama o serviço passando tanto Medicamento quanto Princípio.
        // O Serviço deve ter a assinatura: criar(UUID medId, UUID principioId, UUID espId, String ref, Gravidade g, String desc)
        // Nota: request.getPrincipioAtivoId() só funcionará se você adicionou este campo no YAML do Swagger.
        // Se ainda não adicionou, o Java não encontrará o método getter.

        UUID principioId = null;
        // Tenta obter o ID do princípio se o DTO gerado tiver o método (depende da sua atualização no YAML)
        try {
            // principioId = request.getPrincipioAtivoId(); // Descomente se o método existir
        } catch (Exception e) {
            // Ignora se não existir no DTO ainda
        }

        Contraindicacao salvo = servico.criar(
                request.getMedicamentoId().get(), // Pode ser null se vier pelo admin de principios
                principioId,                // Novo campo (prioritário)
                request.getEspecieId(),
                referencia,
                gravidade,
                request.getDescricao()
        );

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaContraindicacao> listarContraindicacoesPorMedicamento(UUID medicamentoId) {
        List<ContraindicacaoResponse> lista = servico.listarPorMedicamento(medicamentoId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaContraindicacao response = new ApiResponseListaContraindicacao();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseContraindicacao> buscarContraindicacaoPorId(UUID id) {
        Contraindicacao encontrada = servico.buscarPorId(id);

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setDados(converter(encontrada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseContraindicacao> atualizarContraindicacao(UUID id, ContraindicacaoRequest request) {
        Contraindicacao.Gravidade gravidadeDominio = Contraindicacao.Gravidade.valueOf(request.getGravidade().name());

        String referencia = request.getReferenciaTexto() != null ? request.getReferenciaTexto() : "Referência não informada";

        // O método atualizar no serviço precisa aceitar a referência String agora
        Contraindicacao atualizada = servico.atualizar(
                id,
                request.getEspecieId(),
                referencia,
                gravidadeDominio,
                request.getDescricao()
        );

        ApiResponseContraindicacao response = new ApiResponseContraindicacao();
        response.setSucesso(true);
        response.setMensagem("Contraindicação atualizada.");
        response.setDados(converter(atualizada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarContraindicacao(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ---
    private ContraindicacaoResponse converter(Contraindicacao c) {
        ContraindicacaoResponse dto = new ContraindicacaoResponse();
        dto.setId(c.getId());

        // IMPORTANTE: O Frontend espera um ID no campo "medicamentoId" para links.
        // Como a contraindicação agora é baseada em princípio ativo, retornamos o ID do princípio
        // neste campo para manter a compatibilidade visual, ou null se preferir.
        if (c.getPrincipioAtivo() != null) {
            dto.setMedicamentoId(c.getPrincipioAtivo().getId());
        }

        dto.setEspecieId(c.getEspecieId());

        // Se você atualizou o response DTO para ter referenciaTexto, use:
        // dto.setReferenciaTexto(c.getReferenciaTexto());
        // Caso contrário, mantemos referenciaId como null pois mudamos para texto
        dto.setReferenciaId(null);

        dto.setDescricao(c.getDescricao());
        dto.setGravidade(GravidadeEnum.valueOf(c.getGravidade().name()));
        return dto;
    }
}

```

### ApiDelegateMedicamentos.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateMedicamentos.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.Medicamento;
import br.com.vestris.pharmacology.interfaces.api.MedicamentosApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponseListaMedicamento;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponseMedicamento;
import br.com.vestris.pharmacology.interfaces.dto.MedicamentoRequest;
import br.com.vestris.pharmacology.interfaces.dto.MedicamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateMedicamentos implements MedicamentosApiDelegate {
    private final ServiceFarmacologia servico;

    @Override
    public ResponseEntity<ApiResponseMedicamento> criarMedicamento(MedicamentoRequest request) {
        Medicamento entidade = new Medicamento();
        entidade.setNome(request.getNome());
        entidade.setConcentracao(request.getConcentracao());
        entidade.setFabricante(request.getFabricante());
        entidade.setFormaFarmaceutica(request.getFormaFarmaceutica());

        Medicamento salvo = servico.criarMedicamento(entidade, request.getPrincipioAtivoId());

        ApiResponseMedicamento response = new ApiResponseMedicamento();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaMedicamento> listarMedicamentos() {
        List<MedicamentoResponse> lista = servico.listarMedicamentos().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaMedicamento response = new ApiResponseListaMedicamento();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseMedicamento> buscarMedicamentoPorId(UUID id) {
        Medicamento med = servico.buscarMedicamentoPorId(id);

        ApiResponseMedicamento response = new ApiResponseMedicamento();
        response.setSucesso(true);
        response.setDados(converter(med));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseMedicamento> atualizarMedicamento(UUID id, MedicamentoRequest request) {
        Medicamento dados = new Medicamento();
        dados.setNome(request.getNome());
        dados.setConcentracao(request.getConcentracao());
        dados.setFabricante(request.getFabricante());
        dados.setFormaFarmaceutica(request.getFormaFarmaceutica());

        Medicamento atualizado = servico.atualizarMedicamento(id, dados, request.getPrincipioAtivoId());

        ApiResponseMedicamento response = new ApiResponseMedicamento();
        response.setSucesso(true);
        response.setMensagem("Medicamento atualizado.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarMedicamento(UUID id) {
        servico.deletarMedicamento(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ATUALIZADO ---
    private MedicamentoResponse converter(Medicamento med) {
        MedicamentoResponse dto = new MedicamentoResponse();
        dto.setId(med.getId());
        dto.setNome(med.getNome());
        dto.setConcentracao(med.getConcentracao());
        dto.setFabricante(med.getFabricante());
        dto.setFormaFarmaceutica(med.getFormaFarmaceutica());

        // Mapeamento do Princípio Ativo
        if (med.getPrincipioAtivo() != null) {
            dto.setPrincipioAtivoId(med.getPrincipioAtivo().getId());
            // CORREÇÃO: Preenchendo o nome que faltava
            dto.setPrincipioAtivoNome(med.getPrincipioAtivo().getNome());
        }

        return dto;
    }
}

```

### ApiDelegatePrincipioAtivo.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegatePrincipioAtivo.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceFarmacologia;
import br.com.vestris.pharmacology.domain.model.PrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.api.PrincipiosAtivosApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponseListaPrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.dto.ApiResponsePrincipioAtivo;
import br.com.vestris.pharmacology.interfaces.dto.PrincipioAtivoRequest;
import br.com.vestris.pharmacology.interfaces.dto.PrincipioAtivoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePrincipioAtivo implements PrincipiosAtivosApiDelegate {
    private final ServiceFarmacologia servico;

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> criarPrincipioAtivo(PrincipioAtivoRequest request) {
        PrincipioAtivo entidade = new PrincipioAtivo();
        entidade.setNome(request.getNome());
        entidade.setDescricao(request.getDescricao());
        entidade.setGrupoFarmacologico(request.getGrupoFarmacologico());

        PrincipioAtivo salvo = servico.criarPrincipio(entidade);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaPrincipioAtivo> listarPrincipiosAtivos() {
        List<PrincipioAtivoResponse> lista = servico.listarPrincipios().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPrincipioAtivo response = new ApiResponseListaPrincipioAtivo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> buscarPrincipioAtivoPorId(UUID id) {
        PrincipioAtivo pa = servico.buscarPrincipioPorId(id);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setDados(converter(pa));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePrincipioAtivo> atualizarPrincipioAtivo(UUID id, PrincipioAtivoRequest request) {
        PrincipioAtivo dados = new PrincipioAtivo();
        dados.setNome(request.getNome());
        dados.setDescricao(request.getDescricao());
        dados.setGrupoFarmacologico(request.getGrupoFarmacologico());

        PrincipioAtivo atualizado = servico.atualizarPrincipio(id, dados);

        ApiResponsePrincipioAtivo response = new ApiResponsePrincipioAtivo();
        response.setSucesso(true);
        response.setMensagem("Atualizado com sucesso.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarPrincipioAtivo(UUID id) {
        servico.deletarPrincipio(id);
        return ResponseEntity.noContent().build();
    }

    // ... converter ...
    private PrincipioAtivoResponse converter(PrincipioAtivo pa) {
        PrincipioAtivoResponse dto = new PrincipioAtivoResponse();
        dto.setId(pa.getId());
        dto.setNome(pa.getNome());
        dto.setDescricao(pa.getDescricao());
        dto.setGrupoFarmacologico(pa.getGrupoFarmacologico());
        return dto;
    }


}

```

### ApiDelegateSegurancaClinica.java

```java
// src\main\java\br\com\vestris\pharmacology\interfaces\delegate\ApiDelegateSegurancaClinica.java
package br.com.vestris.pharmacology.interfaces.delegate;

import br.com.vestris.pharmacology.application.ServiceSegurancaFarmacologica;
import br.com.vestris.pharmacology.domain.model.Contraindicacao;
import br.com.vestris.pharmacology.interfaces.api.SegurancaClinicaApiDelegate;
import br.com.vestris.pharmacology.interfaces.dto.AlertaSeguranca;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateSegurancaClinica implements SegurancaClinicaApiDelegate {

    private final ServiceSegurancaFarmacologica servico;

    @Override
    public ResponseEntity<List<AlertaSeguranca>> validarSeguranca(UUID medicamentoId, UUID especieId) {

        List<Contraindicacao> riscos = servico.validarMedicamentoParaEspecie(medicamentoId, especieId);

        List<AlertaSeguranca> alertas = riscos.stream().map(c -> {
            AlertaSeguranca dto = new AlertaSeguranca();

            // Convertendo Enum do Domínio para String do DTO
            dto.setNivel(AlertaSeguranca.NivelEnum.fromValue(c.getGravidade().name()));
            dto.setDescricao(c.getDescricao());

            // CORREÇÃO: Como não temos o medicamento específico na entidade Contraindicacao,
            // informamos que o risco é global para aquele princípio.
            dto.setMedicamento("Todos contendo " + c.getPrincipioAtivo().getNome());

            dto.setPrincipioAtivo(c.getPrincipioAtivo().getNome());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(alertas);
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Farmacologia
  description: Gestão de Medicamentos e Princípios Ativos
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  # --- PRINCÍPIOS ATIVOS ---
  /api/v1/principios-ativos:
    $ref: './paths/principios-ativos.yml#/principios_colecao'

  /api/v1/principios-ativos/{id}:
    $ref: './paths/principios-ativos.yml#/principios_item'

  # --- MEDICAMENTOS ---
  /api/v1/medicamentos:
    $ref: './paths/medicamentos.yml#/medicamentos_colecao'

  /api/v1/medicamentos/{id}:
    $ref: './paths/medicamentos.yml#/medicamentos_item'

  # --- CONTRAINDICAÇÕES ---
  /api/v1/contraindicacoes:
    $ref: './paths/contraindicacoes.yml#/contraindicacoes_colecao'

  /api/v1/medicamentos/{medicamentoId}/contraindicacoes:
    $ref: './paths/contraindicacoes.yml#/contraindicacoes_por_medicamento'

  /api/v1/contraindicacoes/{id}:
    $ref: './paths/contraindicacoes.yml#/contraindicacoes_por_id'

  /api/v1/seguranca/validar:
    $ref: './paths/contraindicacoes.yml#/validar_seguranca'

# Components (apenas referenciando o arquivo de schemas que você já tem)
components:
  schemas:
    PrincipioAtivoRequest:
      $ref: "./components/schemas.yml#/PrincipioAtivoRequest"
    # ... O Swagger vai resolver os outros automaticamente através das refs nos paths
    # Mas se quiser garantir que apareçam na home do Swagger UI, liste todos aqui:
    PrincipioAtivoResponse:
      $ref: "./components/schemas.yml#/PrincipioAtivoResponse"
    MedicamentoRequest:
      $ref: "./components/schemas.yml#/MedicamentoRequest"
    MedicamentoResponse:
      $ref: "./components/schemas.yml#/MedicamentoResponse"
    ContraindicacaoRequest:
      $ref: "./components/schemas.yml#/ContraindicacaoRequest"
    ContraindicacaoResponse:
      $ref: "./components/schemas.yml#/ContraindicacaoResponse"
    AlertaSeguranca:
      $ref: "./components/schemas.yml#/AlertaSeguranca"
    ApiResponsePrincipioAtivo:
      $ref: "./components/schemas.yml#/ApiResponsePrincipioAtivo"
    ApiResponseListaPrincipioAtivo:
      $ref: "./components/schemas.yml#/ApiResponseListaPrincipioAtivo"
    ApiResponseMedicamento:
      $ref: "./components/schemas.yml#/ApiResponseMedicamento"
    ApiResponseListaMedicamento:
      $ref: "./components/schemas.yml#/ApiResponseListaMedicamento"
    ApiResponseContraindicacao:
      $ref: "./components/schemas.yml#/ApiResponseContraindicacao"
    ApiResponseListaContraindicacao:
      $ref: "./components/schemas.yml#/ApiResponseListaContraindicacao"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
PrincipioAtivoRequest:
  type: object
  required: [nome]
  properties:
    nome:
      type: string
      example: "Enrofloxacina"
    descricao:
      type: string
    grupoFarmacologico:
      type: string
      example: "Antibiótico / Fluoroquinolona"

PrincipioAtivoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    descricao:
      type: string
    grupoFarmacologico:
      type: string

# --- DTOs Medicamento ---
MedicamentoRequest:
  type: object
  required: [nome, principioAtivoId]
  properties:
    nome:
      type: string
      example: "Baytril 10%"
    principioAtivoId:
      type: string
      format: uuid
    principioAtivoNome:
      type: string
    concentracao:
      type: string
      example: "100 mg/ml"
    fabricante:
      type: string
    formaFarmaceutica:
      type: string
      example: "Injetável / Oral"

MedicamentoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    principioAtivoId:
      type: string
      format: uuid
    principioAtivoNome:
      type: string
    concentracao:
      type: string
    fabricante:
      type: string
    formaFarmaceutica:
      type: string
GravidadeEnum:
  type: string
  enum: [LEVE, MODERADA, GRAVE, FATAL]

ContraindicacaoRequest:
  type: object
  required: [ especieId, gravidade, descricao ] # MedicamentoId não é mais obrigatório se tiver Principio
  properties:
    medicamentoId:
      type: string
      format: uuid
      nullable: true
    principioAtivoId:   # <--- CAMPO NOVO
      type: string
      format: uuid
      nullable: true
    especieId:
      type: string
      format: uuid
    referenciaId:       # <--- PODE REMOVER OU DEIXAR COMO LEGADO
      type: string
      format: uuid
      nullable: true
    referenciaTexto:    # <--- CAMPO NOVO PREFERENCIAL
      type: string
    gravidade:
      $ref: '#/GravidadeEnum'
    descricao:
      type: string
      example: "Causa toxicidade neurológica severa."

ContraindicacaoResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    medicamentoId:
      type: string
      format: uuid
    especieId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
    gravidade:
      $ref: '#/GravidadeEnum'
    descricao:
      type: string

AlertaSeguranca:
  type: object
  properties:
    nivel: { type: string, enum: [LEVE, MODERADA, GRAVE, FATAL] }
    descricao: { type: string }
    medicamento: { type: string }
    principioAtivo: { type: string }

  # Wrappers
ApiResponseContraindicacao:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/ContraindicacaoResponse' }

ApiResponseListaContraindicacao:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ContraindicacaoResponse' }

ApiResponsePrincipioAtivo:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/PrincipioAtivoResponse' }

ApiResponseListaPrincipioAtivo:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/PrincipioAtivoResponse' }

ApiResponseMedicamento:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type:  string
    dados: { $ref: '#/MedicamentoResponse' }

ApiResponseListaMedicamento:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/MedicamentoResponse' }
```

---

## src\main\resources\swagger\paths

### contraindicacoes.yml

```yaml
# src\main\resources\swagger\paths\contraindicacoes.yml
# Rota: /api/v1/contraindicacoes
contraindicacoes_colecao:
  post:
    tags:
      - Contraindicacoes
    summary: Cadastrar contraindicação
    operationId: criarContraindicacao
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ContraindicacaoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseContraindicacao'

# Rota: /api/v1/medicamentos/{medicamentoId}/contraindicacoes
contraindicacoes_por_medicamento:
  get:
    tags:
      - Contraindicacoes
    summary: Listar contraindicações de um medicamento
    operationId: listarContraindicacoesPorMedicamento
    parameters:
      - name: medicamentoId
        in: path
        required: true
        schema:
          type: string
          format: uuid
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaContraindicacao'

# Rota: /api/v1/contraindicacoes/{id}
contraindicacoes_por_id:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags: [ Contraindicacoes ]
    summary: Buscar por ID
    operationId: buscarContraindicacaoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseContraindicacao' }
  put:
    tags: [ Contraindicacoes ]
    summary: Atualizar
    operationId: atualizarContraindicacao
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ContraindicacaoRequest' }
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseContraindicacao' }
  delete:
    tags: [ Contraindicacoes ]
    summary: Remover
    operationId: deletarContraindicacao
    responses:
      '204': { description: Removido }

# ROTA: /api/v1/seguranca/validar
validar_seguranca:
  get:
    tags: [SegurancaClinica]
    summary: Validar medicamento para espécie
    operationId: validarSeguranca
    parameters:
      - name: medicamentoId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: especieId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Lista de alertas (vazio se seguro)
        content:
          application/json:
            schema:
              type: array
              items: { $ref: '../components/schemas.yml#/AlertaSeguranca' }



```

### medicamentos.yml

```yaml
# src\main\resources\swagger\paths\medicamentos.yml
# Rota: /api/v1/medicamentos
medicamentos_colecao:
  post:
    tags:
      - Medicamentos
    summary: Cadastrar medicamento
    operationId: criarMedicamento
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/MedicamentoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  get:
    tags:
      - Medicamentos
    summary: Listar medicamentos
    operationId: listarMedicamentos
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaMedicamento'

# Rota: /api/v1/medicamentos/{id}
medicamentos_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - Medicamentos
    summary: Buscar por ID
    operationId: buscarMedicamentoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  put:
    tags:
      - Medicamentos
    summary: Atualizar
    operationId: atualizarMedicamento
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/MedicamentoRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseMedicamento'
  delete:
    tags:
      - Medicamentos
    summary: Remover
    operationId: deletarMedicamento
    responses:
      '204':
        description: Removido
```

### principios-ativos.yml

```yaml
# src\main\resources\swagger\paths\principios-ativos.yml
# Rota: /api/v1/principios-ativos
principios_colecao:
  post:
    tags:
      - PrincipiosAtivos
    summary: Cadastrar princípio ativo
    operationId: criarPrincipioAtivo
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/PrincipioAtivoRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'

  get:
    tags:
      - PrincipiosAtivos
    summary: Listar todos
    operationId: listarPrincipiosAtivos
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaPrincipioAtivo'

# Rota: /api/v1/principios-ativos/{id}
principios_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - PrincipiosAtivos
    summary: Buscar por ID
    operationId: buscarPrincipioAtivoPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'
  put:
    tags:
      - PrincipiosAtivos
    summary: Atualizar
    operationId: atualizarPrincipioAtivo
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/PrincipioAtivoRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponsePrincipioAtivo'
  delete:
    tags:
      - PrincipiosAtivos
    summary: Remover
    description: "Não permite se houver medicamentos vinculados"
    operationId: deletarPrincipioAtivo
    responses:
      '204':
        description: Removido
```


---

# Projeto: vestris-portal

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-portal</artifactId>

    <properties>
        <maven.compiler.source>22</maven.compiler.source>
        <maven.compiler.target>22</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- 1. Módulos de Domínio -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-species</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-clinical</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-pharmacology</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-vaccination</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-reference</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-medical-record</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-feedback</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-saas</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 2. Banco de Dados (PostgreSQL) -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- 3. Swagger UI (Para visualizar a API no navegador) -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.3.0</version>
        </dependency>

        <!-- 4. Spring Web (Obrigatório para subir o servidor) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin para rodar via 'mvn spring-boot:run' -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    
</project>
```

---

## src\main\java\br\com\vestris\portal

### VestrisApplication.java

```java
// src\main\java\br\com\vestris\portal\VestrisApplication.java
package br.com.vestris.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.vestris")
// 1. Força a busca por @Entity em todos os módulos
@EntityScan(basePackages = "br.com.vestris")
// 2. Força a busca por Interfaces Repository em todos os módulos
@EnableJpaRepositories(basePackages = "br.com.vestris")
public class VestrisApplication {
    public static void main(String[] args) {
        SpringApplication.run(VestrisApplication.class, args);
    }
}

```

---

## src\main\java\br\com\vestris\portal\config

### JacksonConfiguration.java

```java
// src\main\java\br\com\vestris\portal\config\JacksonConfiguration.java
package br.com.vestris.portal.config;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {
    @Bean
    public JsonNullableModule jsonNullableModule() {
        return new JsonNullableModule();
    }
}

```

### SecurityConfig.java

```java
// src\main\java\br\com\vestris\portal\config\SecurityConfig.java
package br.com.vestris.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desabilita CSRF para API
                .authorizeHttpRequests(auth -> auth
                        // Libera Swagger e rotas de Auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/v1/auth/**" // Login e Registro livres
                        ).permitAll()
                        // Por enquanto, libera tudo para facilitar os testes das outras fases
                        // Depois vamos bloquear: .anyRequest().authenticated()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}

```

### WebConfig.java

```java
// src\main\java\br\com\vestris\portal\config\WebConfig.java
package br.com.vestris.portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:4200", "http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

```

---

## src\main\resources

### application.properties

```properties
# src\main\resources\application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/vestris_db
spring.datasource.username=vestris_user
spring.datasource.password=vestris_password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.port=8080


springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

### schema.sql

```sql
-- src\main\resources\schema.sql
-- Recriar Schemas
CREATE SCHEMA IF NOT EXISTS species_schema;
CREATE SCHEMA IF NOT EXISTS pharmacology_schema;
CREATE SCHEMA IF NOT EXISTS clinical_schema;
CREATE SCHEMA IF NOT EXISTS vaccination_schema;
CREATE SCHEMA IF NOT EXISTS reference_schema;
CREATE SCHEMA IF NOT EXISTS medical_record_schema;
CREATE SCHEMA IF NOT EXISTS feedback_schema;
CREATE SCHEMA IF NOT EXISTS saas_schema;
CREATE SCHEMA IF NOT EXISTS users_schema; -- Garante que existe


-- =============================================================================
-- 2. CRIAÇÃO DAS TABELAS (ESTRUTURA LIMPA)
-- =============================================================================

-- --- SAAS & USERS (Base) ---
CREATE TABLE IF NOT EXISTS users_schema.clinicas (
                                                     id UUID PRIMARY KEY,
                                                     nome_fantasia VARCHAR(200) NOT NULL,
    razao_social VARCHAR(200),
    cnpj VARCHAR(20),
    endereco TEXT,
    telefone VARCHAR(50),
    email_contato VARCHAR(150),
    logo_base64 TEXT,
    criado_em TIMESTAMP DEFAULT NOW(),
    atualizado_em TIMESTAMP DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS saas_schema.planos (
                                    id UUID PRIMARY KEY,
                                    nome VARCHAR(100) NOT NULL,
                                    descricao VARCHAR(255),
                                    preco_mensal DECIMAL(10, 2),
                                    preco_anual DECIMAL(10, 2),
                                    url_pagamento TEXT,
                                    max_veterinarios INTEGER NOT NULL,
                                    is_custom BOOLEAN DEFAULT FALSE,
                                    features_json TEXT,
                                    criado_em TIMESTAMP DEFAULT NOW(),
                                    atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS users_schema.usuarios (
                                                     id UUID PRIMARY KEY,
                                                     clinica_id UUID REFERENCES users_schema.clinicas(id),
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    crmv VARCHAR(20),
    perfil VARCHAR(20) NOT NULL,
    scope VARCHAR(20) DEFAULT 'TENANT',
    criado_em TIMESTAMP DEFAULT NOW(),
    atualizado_em TIMESTAMP DEFAULT NOW()
    );

-- Auditoria (Com correção do tipo TEXT)
CREATE TABLE IF NOT EXISTS users_schema.auditoria (
                                                      id UUID PRIMARY KEY,
                                                      clinica_id UUID NOT NULL,
                                                      usuario_id UUID NOT NULL,
                                                      acao VARCHAR(50) NOT NULL,
    entidade VARCHAR(50) NOT NULL,
    id_alvo UUID NOT NULL,
    descricao_curta TEXT,
    metadados TEXT,
    data_hora TIMESTAMP DEFAULT NOW()
    );

-- Assinaturas (Depende de Clínica e Plano)
CREATE TABLE IF NOT EXISTS saas_schema.assinaturas (
                                         id UUID PRIMARY KEY,
                                         clinica_id UUID NOT NULL REFERENCES users_schema.clinicas(id),
                                         plano_id UUID NOT NULL REFERENCES saas_schema.planos(id),
                                         id_externo_assinatura  VARCHAR(100),
                                         status VARCHAR(20) NOT NULL,
                                         tipo_faturamento VARCHAR(20) NOT NULL,
                                         data_inicio TIMESTAMP NOT NULL,
                                         data_fim TIMESTAMP,
                                         data_trial_fim TIMESTAMP,
                                         override_limits_json TEXT,
                                         criado_em TIMESTAMP DEFAULT NOW(),
                                         atualizado_em TIMESTAMP DEFAULT NOW()
);

-- --- SPECIES ---
CREATE TABLE IF NOT EXISTS species_schema.especies (
                                         id UUID PRIMARY KEY,
                                         nome_popular VARCHAR(150) NOT NULL,
                                         nome_cientifico VARCHAR(150) NOT NULL UNIQUE,
                                         familia_taxonomica VARCHAR(100),
                                         grupo VARCHAR(50),
                                         resumo_clinico TEXT,
                                         parametros_fisiologicos TEXT,
                                         expectativa_vida VARCHAR(100),
                                         peso_adulto VARCHAR(100),
                                         tipo_dieta VARCHAR(50),
                                         manejo_infos TEXT,
                                         alertas_clinicos TEXT,
                                         pontos_exame_fisico TEXT,
                                         habitat TEXT,
                                         distribuicao_geografica TEXT,
                                         status_conservacao VARCHAR(100),
                                         bibliografia_base TEXT,
                                         receita_manejo_padrao TEXT,
                                         criado_em TIMESTAMP DEFAULT NOW(),
                                         atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS species_schema.modelos_exame_fisico (
                                                     id UUID PRIMARY KEY,
                                                     especie_id UUID REFERENCES species_schema.especies(id),
                                                     texto_base TEXT NOT NULL,
                                                     criado_em TIMESTAMP DEFAULT NOW()
);

-- --- PHARMACOLOGY ---
CREATE TABLE IF NOT EXISTS pharmacology_schema.principios_ativos (
                                                       id UUID PRIMARY KEY,
                                                       nome VARCHAR(150) NOT NULL UNIQUE,
                                                       grupo_farmacologico VARCHAR(100),
                                                       descricao TEXT,
                                                       criado_em TIMESTAMP DEFAULT NOW(),
                                                       atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS pharmacology_schema.medicamentos (
                                                  id UUID PRIMARY KEY,
                                                  principio_ativo_id UUID REFERENCES pharmacology_schema.principios_ativos(id),
                                                  nome VARCHAR(150) NOT NULL,
                                                  concentracao VARCHAR(50),
                                                  fabricante VARCHAR(100),
                                                  forma_farmaceutica VARCHAR(100),
                                                  criado_em TIMESTAMP DEFAULT NOW(),
                                                  atualizado_em TIMESTAMP DEFAULT NOW()
);

-- Tabela de Doses (Com Constraints de Segurança)
CREATE TABLE IF NOT EXISTS pharmacology_schema.doses_referencia (
                                                      id UUID PRIMARY KEY,
                                                      medicamento_id UUID NOT NULL REFERENCES pharmacology_schema.medicamentos(id),
                                                      especie_id UUID REFERENCES species_schema.especies(id),
                                                      doenca_id UUID, -- FK será adicionada depois pois clinical ainda não existe
                                                      clinica_id UUID REFERENCES users_schema.clinicas(id),
                                                      via VARCHAR(50),
                                                      unidade VARCHAR(50) NOT NULL DEFAULT 'MG_KG',
                                                      dose_min NUMERIC(10,4),
                                                      dose_max NUMERIC(10,4),
                                                      origem VARCHAR(20) NOT NULL,
                                                      nivel_confianca VARCHAR(20) DEFAULT 'MEDIA',
                                                      fonte_bibliografica TEXT,
                                                      observacoes TEXT,
                                                      criado_em TIMESTAMP DEFAULT NOW(),
                                                      atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS pharmacology_schema.contraindicacoes (
                                                      id UUID PRIMARY KEY,
                                                      principio_ativo_id UUID REFERENCES pharmacology_schema.principios_ativos(id),
                                                      especie_id UUID REFERENCES species_schema.especies(id),
                                                      condicao_clinica VARCHAR(200),
                                                      gravidade VARCHAR(20) NOT NULL,
                                                      descricao TEXT NOT NULL,
                                                      referencia_texto VARCHAR(255),
                                                      criado_em TIMESTAMP DEFAULT NOW()
);

-- --- CLINICAL ---
CREATE TABLE IF NOT EXISTS clinical_schema.doencas (
                                         id UUID PRIMARY KEY,
                                         especie_id UUID REFERENCES species_schema.especies(id),
                                         nome VARCHAR(150) NOT NULL,
                                         nome_cientifico VARCHAR(150),
                                         sintomas TEXT,
                                         criado_em TIMESTAMP DEFAULT NOW(),
                                         atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS clinical_schema.protocolos (
                                            id UUID PRIMARY KEY,
                                            doenca_id UUID REFERENCES clinical_schema.doencas(id),
                                            doenca_texto_livre VARCHAR(255),
                                            referencia_id UUID,
                                            referencia_texto VARCHAR(255),
                                            titulo VARCHAR(200) NOT NULL,
                                            observacoes TEXT,
                                            origem VARCHAR(20) DEFAULT 'OFICIAL',
                                            autor_id UUID,
                                            clinica_id UUID,
                                            criado_em TIMESTAMP DEFAULT NOW(),
                                            atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS clinical_schema.dosagens (
                                          id UUID PRIMARY KEY,
                                          protocolo_id UUID REFERENCES clinical_schema.protocolos(id) ON DELETE CASCADE,
                                          medicamento_id UUID REFERENCES pharmacology_schema.medicamentos(id),
                                          medicamento_texto_livre VARCHAR(255),
                                          dose_minima DOUBLE PRECISION,
                                          dose_maxima DOUBLE PRECISION,
                                          unidade VARCHAR(20),
                                          frequencia VARCHAR(50),
                                          duracao VARCHAR(50),
                                          via VARCHAR(50),
                                          criado_em TIMESTAMP DEFAULT NOW(),
                                          atualizado_em TIMESTAMP DEFAULT NOW()
);

-- --- VACCINATION ---
CREATE TABLE IF NOT EXISTS vaccination_schema.vacinas (
                                            id UUID PRIMARY KEY,
                                            nome VARCHAR(150) NOT NULL,
                                            fabricante VARCHAR(100),
                                            tipo_vacina VARCHAR(100),
                                            descricao TEXT,
                                            doenca_alvo VARCHAR(150),
                                            criado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS vaccination_schema.protocolos_vacinacao (
                                                         id UUID PRIMARY KEY,
                                                         especie_id UUID NOT NULL REFERENCES species_schema.especies(id),
                                                         vacina_id UUID REFERENCES vaccination_schema.vacinas(id),
                                                         referencia_id UUID,
                                                         idade_minima_dias INTEGER,
                                                         dias_para_reforco INTEGER,
                                                         obrigatoria BOOLEAN DEFAULT FALSE,
                                                         observacoes TEXT,
                                                         criado_em TIMESTAMP DEFAULT NOW()
);

-- --- REFERENCE ---
CREATE TABLE IF NOT EXISTS reference_schema.referencias (
                                              id UUID PRIMARY KEY,
                                              titulo VARCHAR(255) NOT NULL,
                                              autores VARCHAR(255) NOT NULL,
                                              ano INTEGER,
                                              fonte VARCHAR(150),
                                              url VARCHAR(255),
                                              criado_em TIMESTAMP DEFAULT NOW(),
                                              atualizado_em TIMESTAMP DEFAULT NOW()
);

-- --- MEDICAL RECORD ---
CREATE TABLE IF NOT EXISTS medical_record_schema.pacientes (
                                                 id UUID PRIMARY KEY,
                                                 veterinario_id UUID NOT NULL,
                                                 especie_id UUID NOT NULL REFERENCES species_schema.especies(id),
                                                 nome VARCHAR(100) NOT NULL,
                                                 dados_tutor TEXT NOT NULL,
                                                 identificacao_animal VARCHAR(50),
                                                 microchip VARCHAR(50),
                                                 pelagem_cor VARCHAR(50),
                                                 sexo VARCHAR(20),
                                                 data_nascimento DATE,
                                                 peso_atual_gramas INTEGER,
                                                 criado_em TIMESTAMP DEFAULT NOW(),
                                                 atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS medical_record_schema.atendimentos (
                                                    id UUID PRIMARY KEY,
                                                    paciente_id UUID REFERENCES medical_record_schema.pacientes(id),
                                                    veterinario_id UUID NOT NULL,
                                                    protocolo_id UUID,
                                                    titulo VARCHAR(150) NOT NULL,
                                                    data_hora TIMESTAMP NOT NULL,
                                                    status VARCHAR(20) NOT NULL,
                                                    queixa_principal TEXT,
                                                    historico_clinico TEXT,
                                                    exame_fisico TEXT,
                                                    diagnostico TEXT,
                                                    conduta_clinica TEXT,
                                                    orientacoes_manejo TEXT,
                                                    observacoes TEXT,
                                                    criado_em TIMESTAMP DEFAULT NOW(),
                                                    atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS medical_record_schema.exames_anexos (
                                                     id UUID PRIMARY KEY,
                                                     atendimento_id UUID NOT NULL REFERENCES medical_record_schema.atendimentos(id),
                                                     nome_arquivo VARCHAR(255) NOT NULL,
                                                     tipo_arquivo VARCHAR(50),
                                                     url_arquivo TEXT,
                                                     observacoes TEXT,
                                                     criado_em TIMESTAMP DEFAULT NOW(),
                                                     atualizado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS medical_record_schema.aplicacoes_vacinas (
                                                          id UUID PRIMARY KEY,
                                                          paciente_id UUID REFERENCES medical_record_schema.pacientes(id),
                                                          vacina_id UUID NOT NULL REFERENCES vaccination_schema.vacinas(id),
                                                          data_aplicacao DATE NOT NULL,
                                                          data_proxima_dose DATE,
                                                          lote VARCHAR(50),
                                                          veterinario_id UUID,
                                                          observacoes TEXT,
                                                          criado_em TIMESTAMP DEFAULT NOW(),
                                                          atualizado_em TIMESTAMP DEFAULT NOW()
);

-- --- FEEDBACK ---
CREATE TABLE IF NOT EXISTS feedback_schema.sugestoes (
                                           id UUID PRIMARY KEY,
                                           usuario_id UUID NOT NULL,
                                           tipo VARCHAR(20) NOT NULL,
                                           conteudo TEXT NOT NULL,
                                           status VARCHAR(20) NOT NULL,
                                           criado_em TIMESTAMP DEFAULT NOW()
);


-- =============================================================================
-- 3. AJUSTES DE MIGRAÇÃO (SEM ERROS)
-- =============================================================================
ALTER TABLE users_schema.usuarios DROP CONSTRAINT IF EXISTS usuarios_perfil_check;


-- =============================================================================
-- 4. CARGA DE DADOS (SEEDS COM UUIDS VÁLIDOS)
-- =============================================================================

-- Planos
INSERT INTO saas_schema.planos (id, nome, preco_mensal, max_veterinarios, is_custom) VALUES
                                                                                         ('90000000-0000-0000-0000-000000000001', 'Veterinário Autônomo', 79.00, 1, false),
                                                                                         ('90000000-0000-0000-0000-000000000002', 'Clínica Pequena', 249.00, 3, false),
                                                                                         ('90000000-0000-0000-0000-000000000003', 'Clínica Média', 449.00, 6, false),
                                                                                         ('90000000-0000-0000-0000-000000000004', 'Enterprise', 0.00, 999, true)
    ON CONFLICT (id) DO NOTHING;

-- Espécies
INSERT INTO species_schema.especies (id, nome_popular, nome_cientifico, grupo, criado_em) VALUES
                                                                                              ('c1000000-0000-0000-0000-000000000001', 'Calopsita', 'Nymphicus hollandicus', 'Ave', NOW()),
                                                                                              ('c1000000-0000-0000-0000-000000000002', 'Jabuti-piranga', 'Chelonoidis carbonaria', 'Réptil', NOW())
    ON CONFLICT (nome_cientifico) DO NOTHING;

-- Princípios Ativos
INSERT INTO pharmacology_schema.principios_ativos (id, nome, grupo_farmacologico, criado_em) VALUES
                                                                                                 ('a1000000-0000-0000-0000-000000000001', 'Enrofloxacina', 'Antibiótico', NOW()),
                                                                                                 ('a1000000-0000-0000-0000-000000000002', 'Dipirona', 'Analgésico', NOW())
    ON CONFLICT (nome) DO NOTHING;

-- Medicamentos
INSERT INTO pharmacology_schema.medicamentos (id, principio_ativo_id, nome, concentracao, fabricante, forma_farmaceutica, criado_em) VALUES
                                                                                                                                         ('d1000000-0000-0000-0000-000000000001', 'a1000000-0000-0000-0000-000000000001', 'Baytril 10%', '100 mg/ml', 'Bayer', 'Injetável', NOW()),
                                                                                                                                         ('d1000000-0000-0000-0000-000000000002', 'a1000000-0000-0000-0000-000000000002', 'Dipirona Gotas', '500 mg/ml', 'Genérico', 'Oral', NOW())
    ON CONFLICT (id) DO NOTHING;

-- Doses de Referência (Seed)
INSERT INTO pharmacology_schema.doses_referencia
(id, medicamento_id, especie_id, dose_min, dose_max, unidade, via, origem, nivel_confianca, fonte_bibliografica, criado_em)
VALUES
-- Baytril Calopsita
(gen_random_uuid(), 'd1000000-0000-0000-0000-000000000001', 'c1000000-0000-0000-0000-000000000001', 15.0, 30.0, 'MG_KG', 'ORAL', 'OFICIAL', 'ALTA', 'Carpenter, 2018', NOW()),
-- Baytril Jabuti
(gen_random_uuid(), 'd1000000-0000-0000-0000-000000000001', 'c1000000-0000-0000-0000-000000000002', 5.0, 10.0, 'MG_KG', 'IM', 'OFICIAL', 'ALTA', 'Carpenter, 2018', NOW()),
-- Dipirona Geral
(gen_random_uuid(), 'd1000000-0000-0000-0000-000000000002', NULL, 25.0, 50.0, 'MG_KG', NULL, 'OFICIAL', 'MEDIA', 'Formulário Veterinário', NOW());

-- Clínica Teste e Vínculos
INSERT INTO users_schema.clinicas (id, nome_fantasia, razao_social, criado_em)
VALUES ('31101310-0000-0000-0000-000000000001', 'Clínica BPS', 'BPS Serviços Veterinários Ltda', NOW())
    ON CONFLICT (id) DO NOTHING;

-- Atualizar Usuários (Se existirem)
UPDATE users_schema.usuarios SET clinica_id = '31101310-0000-0000-0000-000000000001' WHERE email LIKE '%@clinicabps.com';
UPDATE users_schema.usuarios SET perfil = 'ADMIN_CLINICO' WHERE email = 'diretor@clinicabps.com';
UPDATE users_schema.usuarios SET perfil = 'ADMIN_GESTOR' WHERE email = 'gestor@clinicabps.com';
UPDATE users_schema.usuarios SET perfil = 'VETERINARIO' WHERE email = 'vet@clinicabps.com';
UPDATE users_schema.usuarios SET perfil = 'ADMIN_GLOBAL', scope = 'GLOBAL' WHERE email = 'admin@vestris.com';
```


---

# Projeto: vestris-reference

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-reference</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Swagger e Web -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.reference.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.reference.interfaces.dto</modelPackage>

                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

---

## src\main\java\br\com\vestris\reference\application

### ServiceReferencia.java

```java
// src\main\java\br\com\vestris\reference\application\ServiceReferencia.java
package br.com.vestris.reference.application;

import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import br.com.vestris.reference.domain.repository.RepositorioReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceReferencia {

    private final RepositorioReferencia repositorio;

    public ReferenciaBibliografica criar(ReferenciaBibliografica nova) {
        if (repositorio.existsByTituloAndAutores(nova.getTitulo(), nova.getAutores())) {
            throw new ExcecaoRegraNegocio("Esta referência já está cadastrada.");
        }
        return repositorio.save(nova);
    }

    public List<ReferenciaBibliografica> listarTodas() {
        return repositorio.findAll();
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    public ReferenciaBibliografica buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Referência", id.toString()));
    }

    public ReferenciaBibliografica atualizar(UUID id, ReferenciaBibliografica dados) {
        ReferenciaBibliografica existente = buscarPorId(id);

        existente.setTitulo(dados.getTitulo());
        existente.setAutores(dados.getAutores());
        existente.setAno(dados.getAno());
        existente.setFonte(dados.getFonte());
        existente.setUrl(dados.getUrl());

        return repositorio.save(existente);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Referência", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Esta referência não pode ser removida pois embasa protocolos ou vacinas no sistema.");
        }
    }

    public String buscarCitacaoPorId(UUID id) {
        return repositorio.findById(id)
                .map(r -> r.getAutores() + " (" + r.getAno() + ") - " + r.getTitulo())
                .orElse("Referência não identificada");
    }
}

```

---

## src\main\java\br\com\vestris\reference\domain\model

### ReferenciaBibliografica.java

```java
// src\main\java\br\com\vestris\reference\domain\model\ReferenciaBibliografica.java
package br.com.vestris.reference.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "referencias", schema = "reference_schema")

public class ReferenciaBibliografica extends EntidadeBase {
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autores;

    private Integer ano;

    private String fonte; // Editora ou Revista

    private String url;

}

```

---

## src\main\java\br\com\vestris\reference\domain\repository

### RepositorioReferencia.java

```java
// src\main\java\br\com\vestris\reference\domain\repository\RepositorioReferencia.java
package br.com.vestris.reference.domain.repository;

import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioReferencia extends JpaRepository<ReferenciaBibliografica, UUID> {
    boolean existsByTituloAndAutores(String titulo, String autores);
}

```

---

## src\main\java\br\com\vestris\reference\interfaces\delegate

### ApiDelegateReferencias.java

```java
// src\main\java\br\com\vestris\reference\interfaces\delegate\ApiDelegateReferencias.java
package br.com.vestris.reference.interfaces.delegate;

import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.reference.domain.model.ReferenciaBibliografica;
import br.com.vestris.reference.interfaces.api.ReferenciasApiDelegate;
import br.com.vestris.reference.interfaces.dto.ApiResponseListaReferencia;
import br.com.vestris.reference.interfaces.dto.ApiResponseReferencia;
import br.com.vestris.reference.interfaces.dto.ReferenciaRequest;
import br.com.vestris.reference.interfaces.dto.ReferenciaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateReferencias implements ReferenciasApiDelegate {

    private final ServiceReferencia servico;

    @Override
    public ResponseEntity<ApiResponseReferencia> criarReferencia(ReferenciaRequest request) {
        ReferenciaBibliografica entidade = new ReferenciaBibliografica();
        entidade.setTitulo(request.getTitulo());
        entidade.setAutores(request.getAutores());
        entidade.setAno(request.getAno());
        entidade.setFonte(request.getFonte());
        entidade.setUrl(request.getUrl());

        ReferenciaBibliografica salva = servico.criar(entidade);

        ApiResponseReferencia response = new ApiResponseReferencia();
        response.setSucesso(true);
        response.setDados(converter(salva));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaReferencia> listarReferencias() {
        List<ReferenciaResponse> lista = servico.listarTodas().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaReferencia response = new ApiResponseListaReferencia();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private ReferenciaResponse converter(ReferenciaBibliografica ref) {
        ReferenciaResponse dto = new ReferenciaResponse();
        dto.setId(ref.getId());
        dto.setTitulo(ref.getTitulo());
        dto.setAutores(ref.getAutores());
        dto.setAno(ref.getAno());
        dto.setFonte(ref.getFonte());
        dto.setUrl(ref.getUrl());

        if (ref.getCriadoEm() != null) {
            dto.setCriadoEm(ref.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseReferencia> buscarReferenciaPorId(UUID id) {
        // 1. Busca a entidade
        var entidade = servico.buscarPorId(id);

        // 2. Instancia a resposta (Sem Builder)
        ApiResponseReferencia response = new ApiResponseReferencia();
        response.setSucesso(true);
        response.setDados(converter(entidade));

        // 3. Retorna
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseReferencia> atualizarReferencia(UUID id, ReferenciaRequest request) {
        ReferenciaBibliografica dados = new ReferenciaBibliografica();
        dados.setTitulo(request.getTitulo());
        dados.setAutores(request.getAutores());
        dados.setAno(request.getAno());
        dados.setFonte(request.getFonte());
        dados.setUrl(request.getUrl());

        ReferenciaBibliografica atualizada = servico.atualizar(id, dados);

        ApiResponseReferencia response = new ApiResponseReferencia();
        response.setSucesso(true);
        response.setDados(converter(atualizada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarReferencia(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Referências
  description: Gestão de Bibliografia Científica
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  /api/v1/referencias:
    $ref: './paths/referencias.yml#/referencias_colecao'

  /api/v1/referencias/{id}:
    $ref: './paths/referencias.yml#/referencias_item'

components:
  schemas:
    ReferenciaRequest:
      $ref: "./components/schemas.yml#/ReferenciaRequest"
    ReferenciaResponse:
      $ref: "./components/schemas.yml#/ReferenciaResponse"
    ApiResponseReferencia:
      $ref: "./components/schemas.yml#/ApiResponseReferencia"
    ApiResponseListaReferencia:
      $ref: "./components/schemas.yml#/ApiResponseListaReferencia"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
ReferenciaRequest:
  type: object
  required: [titulo, autores, ano]
  properties:
    titulo:
      type: string
      example: "Exotic Animal Formulary"
    autores:
      type: string
      example: "James W. Carpenter"
    ano:
      type: integer
      example: 2018
    fonte:
      type: string
      description: "Nome do Jornal, Editora ou Revista"
      example: "Elsevier"
    url:
      type: string
      description: "Link para o DOI ou PDF (opcional)"

ReferenciaResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    titulo:
      type: string
    autores:
      type: string
    ano:
      type: integer
    fonte:
      type: string
    url:
      type: string
    criadoEm:
      type: string
      format: date-time

# Wrappers
ApiResponseReferencia:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ReferenciaResponse' }

ApiResponseListaReferencia:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ReferenciaResponse' }
```

---

## src\main\resources\swagger\paths

### referencias.yml

```yaml
# src\main\resources\swagger\paths\referencias.yml
# Rota: /api/v1/referencias
referencias_colecao:
  post:
    tags: [Referencias]
    summary: Cadastrar referência
    operationId: criarReferencia
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ReferenciaRequest' }
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseReferencia' }
  get:
    tags: [Referencias]
    summary: Listar todas
    operationId: listarReferencias
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaReferencia' }

# Rota: /api/v1/referencias/{id}
referencias_item:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [Referencias]
    summary: Buscar por ID
    operationId: buscarReferenciaPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseReferencia' }
  put:
    tags: [Referencias]
    summary: Atualizar referência
    operationId: atualizarReferencia
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ReferenciaRequest' }
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseReferencia' }
  delete:
    tags: [Referencias]
    summary: Remover referência
    description: "Bloqueia se estiver em uso (Protocolos, Vacinas, etc)"
    operationId: deletarReferencia
    responses:
      '204': { description: Removido }
```


---

# Projeto: vestris-saas

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-saas</artifactId>

    <dependencies>
        <!-- 1. Shared (Padrão) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- 2. IMPORTANTE: Dependência de Usuário (Para vincular Assinatura -> Clínica) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-user</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Swagger e Web -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>

        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>

                            <!-- CORREÇÃO AQUI: Mudado de 'reference' para 'saas' -->
                            <apiPackage>br.com.vestris.saas.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.saas.interfaces.dto</modelPackage>

                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

---

## src\main\java\br\com\vestris\saas\application

### ServiceAssinatura.java

```java
// src\main\java\br\com\vestris\saas\application\ServiceAssinatura.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.domain.repository.RepositorioAssinatura;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.application.ValidadorPlanoService;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceAssinatura implements ValidadorPlanoService {
    private final RepositorioAssinatura repoAssinatura;
    private final RepositorioClinica repoClinica;
    private final RepositorioUsuario repoUsuario;
    private final ServicePlano servicePlano;

    public Assinatura buscarPorClinica(UUID clinicaId) {
        return repoAssinatura.findByClinicaId(clinicaId).orElse(null);
    }

    @Transactional
    public Assinatura assinarPlano(UUID clinicaId, UUID planoId, String ciclo) {
        // ... (código igual ao anterior) ...
        Clinica clinica = repoClinica.findById(clinicaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Clínica", clinicaId.toString()));
        Plano novoPlano = servicePlano.buscarPorId(planoId);
        Assinatura assinatura = repoAssinatura.findByClinicaId(clinicaId).orElse(new Assinatura());
        assinatura.setClinica(clinica);
        assinatura.setPlano(novoPlano);
        assinatura.setStatus(Assinatura.StatusAssinatura.ATIVO);
        assinatura.setTipoFaturamento(novoPlano.isCustom() ? Assinatura.TipoFaturamento.MANUAL : Assinatura.TipoFaturamento.AUTO);
        assinatura.setDataInicio(LocalDateTime.now());
        if ("ANUAL".equalsIgnoreCase(ciclo)) {
            assinatura.setDataFim(LocalDateTime.now().plusYears(1));
        } else {
            assinatura.setDataFim(LocalDateTime.now().plusMonths(1));
        }
        return repoAssinatura.save(assinatura);
    }

    // --- IMPLEMENTAÇÃO DO CONTRATO ---
    @Override
    public void validarLimiteVeterinarios(UUID clinicaId) {
        // 1. Busca Assinatura
        Assinatura assinatura = buscarPorClinica(clinicaId);

        // 2. Valida Status (Ativo ou Trial)
        if (assinatura == null ||
                (assinatura.getStatus() != Assinatura.StatusAssinatura.ATIVO &&
                        assinatura.getStatus() != Assinatura.StatusAssinatura.TRIAL)) {

            throw new ExcecaoRegraNegocio("Sua clínica não possui uma assinatura ativa. Regularize o plano para gerenciar a equipe.");
        }

        // 3. Verifica Limites
        int limiteMaximo = assinatura.getPlano().getMaxVeterinarios();
        long totalAtual = repoUsuario.countByClinicaId(clinicaId);

        if (totalAtual >= limiteMaximo) {
            throw new ExcecaoRegraNegocio(
                    String.format("Limite do plano atingido (%d/%d). Faça upgrade para adicionar mais veterinários.",
                            totalAtual, limiteMaximo)
            );
        }
    }
}

```

### ServiceCadastroSaas.java

```java
// src\main\java\br\com\vestris\saas\application\ServiceCadastroSaas.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.domain.repository.RepositorioAssinatura;
import br.com.vestris.saas.domain.repository.RepositorioPlano;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import br.com.vestris.user.infrastructure.security.ServicoToken;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceCadastroSaas {
    private final RepositorioUsuario repoUsuario;
    private final RepositorioClinica repoClinica;
    private final RepositorioAssinatura repoAssinatura;
    private final RepositorioPlano repoPlano;
    private final PasswordEncoder passwordEncoder;
    private final ServicoToken servicoToken;

    @Transactional
    public String registrarCliente(String nome, String email, String senha, String crmv, String nomeClinica, UUID planoId) {
        // 1. Valida E-mail
        if (repoUsuario.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("Este e-mail já está em uso.");
        }

        // 2. Cria Clínica
        Clinica clinica = new Clinica();
        clinica.setNomeFantasia(nomeClinica);
        clinica = repoClinica.save(clinica);

        // 3. Cria Usuário Admin
        Usuario admin = new Usuario();
        admin.setNome(nome);
        admin.setEmail(email);
        admin.setSenha(passwordEncoder.encode(senha));
        admin.setCrmv(crmv);
        admin.setPerfil(Perfil.ADMIN_GESTOR); // Dono da clínica
        admin.setScope(Usuario.UserScope.TENANT);
        admin.setClinica(clinica);
        admin = repoUsuario.save(admin);

        // 4. Cria Assinatura (JÁ ATIVA PARA TESTE, depois mudamos para TRIAL ou PENDENTE)
        var plano = repoPlano.findById(planoId)
                .orElseThrow(() -> new ExcecaoRegraNegocio("Plano inválido"));

        Assinatura assinatura = new Assinatura();
        assinatura.setClinica(clinica);
        assinatura.setPlano(plano);
        assinatura.setStatus(Assinatura.StatusAssinatura.ATIVO); // <--- ATIVO DIRETO
        assinatura.setTipoFaturamento(Assinatura.TipoFaturamento.AUTO);
        assinatura.setDataInicio(LocalDateTime.now());
        assinatura.setDataFim(LocalDateTime.now().plusMonths(1)); // 1 mês grátis/teste

        repoAssinatura.save(assinatura);

        // 5. Retorna Token de Login Imediato
        return servicoToken.gerarToken(admin);
    }
}

```

### ServicePlano.java

```java
// src\main\java\br\com\vestris\saas\application\ServicePlano.java
package br.com.vestris.saas.application;

import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.domain.repository.RepositorioPlano;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServicePlano {
    private final RepositorioPlano repositorio;

    public List<Plano> listarTodos() {
        return repositorio.findAll();
    }

    public Plano buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Plano", id.toString()));
    }

    // Método para Admin criar planos (futuro)
    public Plano criar(Plano plano) {
        return repositorio.save(plano);
    }
}

```

---

## src\main\java\br\com\vestris\saas\domain\model

### Assinatura.java

```java
// src\main\java\br\com\vestris\saas\domain\model\Assinatura.java
package br.com.vestris.saas.domain.model;

import br.com.vestris.user.domain.model.Clinica;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import br.com.vestris.shared.domain.model.EntidadeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "assinaturas", schema = "saas_schema")
public class Assinatura extends EntidadeBase {
    @OneToOne(optional = false)
    @JoinColumn(name = "clinica_id", unique = true) // 1 Assinatura ativa por clínica
    private Clinica clinica;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plano_id")
    private Plano plano;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssinatura status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFaturamento tipoFaturamento;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;      // Renovação
    private LocalDateTime dataTrialFim; // Expiração do Trial

    @Column(columnDefinition = "TEXT")
    private String overrideLimitsJson;

    public enum StatusAssinatura {
        TRIAL, ATIVO, BLOQUEADO, INADIMPLENTE, CANCELADO
    }

    public enum TipoFaturamento {
        AUTO, MANUAL
    }
}

```

### Plano.java

```java
// src\main\java\br\com\vestris\saas\domain\model\Plano.java
package br.com.vestris.saas.domain.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import jakarta.persistence.*;
import br.com.vestris.shared.domain.model.EntidadeBase;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "planos", schema = "saas_schema")
public class Plano extends EntidadeBase{
    @Column(nullable = false)
    private String nome;

    private String descricao;

    private BigDecimal precoMensal;
    private BigDecimal precoAnual;

    @Column(nullable = false)
    private Integer maxVeterinarios;

    @Column(nullable = false)
    private boolean isCustom; // True = Enterprise

    @Column(columnDefinition = "TEXT")
    private String featuresJson;
}

```

---

## src\main\java\br\com\vestris\saas\domain\repository

### RepositorioAssinatura.java

```java
// src\main\java\br\com\vestris\saas\domain\repository\RepositorioAssinatura.java
package br.com.vestris.saas.domain.repository;

import br.com.vestris.saas.domain.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioAssinatura extends JpaRepository<Assinatura, UUID> {
    // Busca a assinatura ativa da clínica
    Optional<Assinatura> findByClinicaId(UUID clinicaId);
}

```

### RepositorioPlano.java

```java
// src\main\java\br\com\vestris\saas\domain\repository\RepositorioPlano.java
package br.com.vestris.saas.domain.repository;

import br.com.vestris.saas.domain.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RepositorioPlano extends JpaRepository<Plano, UUID> {
}

```

---

## src\main\java\br\com\vestris\saas\interfaces\api

### WebhookController.java

```java
// src\main\java\br\com\vestris\saas\interfaces\api\WebhookController.java
package br.com.vestris.saas.interfaces.api;

import br.com.vestris.saas.application.ServiceAssinatura;
import br.com.vestris.saas.interfaces.dto.WebhookMP;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/webhooks")
@RequiredArgsConstructor
public class WebhookController {
    private final ServiceAssinatura serviceAssinatura;

    @PostMapping("/mercadopago")
    public ResponseEntity<Void> receberNotificacao(@RequestBody WebhookMP payload) {
        System.out.println("🔔 Webhook Recebido: " + payload.getAction() + " | ID: " + payload.getData().getId());

        // Lógica simplificada: Se for 'payment.created' ou 'subscription_preapproval', processamos
        if ("payment.created".equals(payload.getAction()) || "created".equals(payload.getAction())) {
            // Aqui você chamaria a API do Mercado Pago usando o ID recebido para saber quem pagou (email)
            // E atualizaria a assinatura no banco.
            // serviceAssinatura.processarPagamento(payload.getData().getId());
        }

        return ResponseEntity.ok().build(); // Sempre retornar 200 pro MP não ficar tentando de novo
    }
}

```

---

## src\main\java\br\com\vestris\saas\interfaces\delegate

### ApiDelegateAssinaturas.java

```java
// src\main\java\br\com\vestris\saas\interfaces\delegate\ApiDelegateAssinaturas.java
package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServiceAssinatura;
import br.com.vestris.saas.domain.model.Assinatura;
import br.com.vestris.saas.interfaces.api.AssinaturasApiDelegate;
import br.com.vestris.saas.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAssinaturas implements AssinaturasApiDelegate {

    private final ServiceAssinatura servico;

    @Override
    public ResponseEntity<ApiResponseAssinatura> obterMinhaAssinatura(UUID clinicaId) {
        Assinatura assinatura = servico.buscarPorClinica(clinicaId);

        ApiResponseAssinatura response = new ApiResponseAssinatura();

        if (assinatura == null) {
            // Retorna sucesso mas sem dados (Clínica sem plano ainda)
            response.setSucesso(true);
            response.setDados(null);
        } else {
            response.setSucesso(true);
            response.setDados(converter(assinatura));
        }

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseAssinatura> assinarPlano(UUID clinicaId, AssinarPlanoRequest request) {
        Assinatura nova = servico.assinarPlano(clinicaId, request.getPlanoId(), request.getCiclo().name());

        ApiResponseAssinatura response = new ApiResponseAssinatura();
        response.setSucesso(true);
        response.setDados(converter(nova));

        return ResponseEntity.ok(response);
    }

    private AssinaturaResponse converter(Assinatura a) {
        AssinaturaResponse dto = new AssinaturaResponse();
        dto.setId(a.getId());
        dto.setClinicaId(a.getClinica().getId());

        // Mapeia o Plano simplificado dentro da resposta
        PlanoResponse planoDto = new PlanoResponse();
        planoDto.setId(a.getPlano().getId());
        planoDto.setNome(a.getPlano().getNome());
        planoDto.setMaxVeterinarios(a.getPlano().getMaxVeterinarios());
        dto.setPlano(planoDto);

        dto.setStatus(StatusAssinaturaEnum.valueOf(a.getStatus().name()));
        dto.setTipoFaturamento(TipoFaturamentoEnum.valueOf(a.getTipoFaturamento().name()));

        if(a.getDataInicio() != null) dto.setDataInicio(a.getDataInicio().atOffset(ZoneOffset.UTC));
        if(a.getDataFim() != null) dto.setDataFim(a.getDataFim().atOffset(ZoneOffset.UTC));
        if(a.getDataTrialFim() != null) dto.setDataTrialFim(a.getDataTrialFim().atOffset(ZoneOffset.UTC));

        return dto;
    }
}

```

### ApiDelegateCadastroSaas.java

```java
// src\main\java\br\com\vestris\saas\interfaces\delegate\ApiDelegateCadastroSaas.java
package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServiceCadastroSaas;
import br.com.vestris.saas.interfaces.api.PublicoApiDelegate;
import br.com.vestris.saas.interfaces.dto.ApiResponseToken;
import br.com.vestris.saas.interfaces.dto.CadastroSaasRequest;
import br.com.vestris.saas.interfaces.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiDelegateCadastroSaas implements PublicoApiDelegate {

    private final ServiceCadastroSaas serviceCadastro;

    @Override
    public ResponseEntity<ApiResponseToken> cadastrarClienteSaas(CadastroSaasRequest request) {

        // 1. Chama o serviço "Combo" que cria tudo e retorna o JWT
        String tokenJwt = serviceCadastro.registrarCliente(
                request.getNomeUsuario(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv(),
                request.getNomeClinica(),
                request.getPlanoId()
        );

        // 2. Monta o DTO de Token (específico do módulo SaaS)
        TokenResponse tokenDto = new TokenResponse();
        tokenDto.setToken(tokenJwt);
        tokenDto.setTipo("Bearer");
        tokenDto.setExpiraEm("24h"); // Ou pegar da config se preferir

        // 3. Monta o Wrapper de Resposta
        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setMensagem("Conta criada e assinatura ativada com sucesso.");
        response.setDados(tokenDto);

        // 4. Retorna 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

```

### ApiDelegatePlanos.java

```java
// src\main\java\br\com\vestris\saas\interfaces\delegate\ApiDelegatePlanos.java
package br.com.vestris.saas.interfaces.delegate;

import br.com.vestris.saas.application.ServicePlano;
import br.com.vestris.saas.domain.model.Plano;
import br.com.vestris.saas.interfaces.api.PlanosApiDelegate;
import br.com.vestris.saas.interfaces.dto.ApiResponseListaPlano;
import br.com.vestris.saas.interfaces.dto.ApiResponsePlano;
import br.com.vestris.saas.interfaces.dto.PlanoRequest;
import br.com.vestris.saas.interfaces.dto.PlanoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePlanos implements PlanosApiDelegate {

    private final ServicePlano servico;

    @Override
    public ResponseEntity<ApiResponseListaPlano> listarPlanos() {
        List<PlanoResponse> lista = servico.listarTodos().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPlano response = new ApiResponseListaPlano();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePlano> buscarPlanoPorId(UUID id) {
        Plano plano = servico.buscarPorId(id);

        ApiResponsePlano response = new ApiResponsePlano();
        response.setSucesso(true);
        response.setDados(converter(plano));

        return ResponseEntity.ok(response);
    }

    // Método POST (Admin) - Se você definiu no YAML
    @Override
    public ResponseEntity<ApiResponsePlano> criarPlano(PlanoRequest request) {
        Plano p = new Plano();
        p.setNome(request.getNome());
        p.setDescricao(request.getDescricao());
        p.setPrecoMensal(BigDecimal.valueOf(request.getPrecoMensal()));
        p.setPrecoAnual(BigDecimal.valueOf(request.getPrecoAnual()));
        p.setMaxVeterinarios(request.getMaxVeterinarios());
        p.setCustom(request.getIsCustom());
        p.setFeaturesJson(request.getFeaturesJson());

        Plano salvo = servico.criar(p);

        ApiResponsePlano response = new ApiResponsePlano();
        response.setSucesso(true);
        response.setDados(converter(salvo));
        return ResponseEntity.ok(response);
    }

    private PlanoResponse converter(Plano p) {
        PlanoResponse dto = new PlanoResponse();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setDescricao(p.getDescricao());
        if(p.getPrecoMensal() != null) dto.setPrecoMensal(p.getPrecoMensal().doubleValue());
        if(p.getPrecoAnual() != null) dto.setPrecoAnual(p.getPrecoAnual().doubleValue());
        dto.setMaxVeterinarios(p.getMaxVeterinarios());
        dto.setIsCustom(p.isCustom());
        dto.setFeaturesJson(p.getFeaturesJson());
        return dto;
    }
}

```

---

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

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo SaaS
  description: Gestão de Planos, Assinaturas e Limites
  version: 1.0.0
servers:
  - url: http://localhost:8080

paths:
  # --- PLANOS ---
  /api/v1/saas/planos:
    $ref: './paths/planos.yml#/planos_colecao'

  /api/v1/saas/planos/{id}:
    $ref: './paths/planos.yml#/planos_item'

  # --- ASSINATURAS ---
  /api/v1/saas/minha-assinatura:
    $ref: './paths/assinaturas.yml#/assinatura_atual'

  /api/v1/saas/assinar:
    $ref: './paths/assinaturas.yml#/assinar_plano'

  /api/v1/public/cadastro-saas:
    $ref: './paths/auth-public.yml#/cadastro_saas'


components:
  schemas:
    # Requests
    PlanoRequest:
      $ref: "./components/schemas.yml#/PlanoRequest"
    AssinarPlanoRequest:
      $ref: "./components/schemas.yml#/AssinarPlanoRequest"

    # Responses
    PlanoResponse:
      $ref: "./components/schemas.yml#/PlanoResponse"
    AssinaturaResponse:
      $ref: "./components/schemas.yml#/AssinaturaResponse"

    # Enums
    StatusAssinaturaEnum:
      $ref: "./components/schemas.yml#/StatusAssinaturaEnum"
    TipoFaturamentoEnum:
      $ref: "./components/schemas.yml#/TipoFaturamentoEnum"

    CadastroSaasRequest:
      $ref: "./components//schemas.yml#/CadastroSaasRequest"
    ApiResponseToken:
      $ref: "./components/schemas.yml#/ApiResponseToken"
    TokenResponse:
      $ref: "./components/schemas.yml#/TokenResponse"
    # Wrappers
    ApiResponsePlano:
      $ref: "./components/schemas.yml#/ApiResponsePlano"
    ApiResponseListaPlano:
      $ref: "./components/schemas.yml#/ApiResponseListaPlano"
    ApiResponseAssinatura:
      $ref: "./components/schemas.yml#/ApiResponseAssinatura"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# --- ENUMS ---
StatusAssinaturaEnum:
  type: string
  enum: [TRIAL, ATIVO, BLOQUEADO, INADIMPLENTE, CANCELADO]

TipoFaturamentoEnum:
  type: string
  enum: [AUTO, MANUAL]

# --- PLANO (CATÁLOGO) ---
PlanoRequest:
  type: object
  required: [ nome, maxVeterinarios ]
  properties:
    nome:
      type: string
      example: "Clínica Pequena"
    descricao:
      type: string
    precoMensal:
      type: number
      format: double
      example: 249.00
    precoAnual:
      type: number
      format: double
      example: 2490.00
    maxVeterinarios:
      type: integer
      example: 3
    isCustom:
      type: boolean
      default: false
    featuresJson:
      type: string
      description: "JSON contendo flags (ex: branding, api, export)"

PlanoResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    descricao: { type: string }
    precoMensal: { type: number, format: double }
    precoAnual: { type: number, format: double }
    maxVeterinarios: { type: integer }
    isCustom: { type: boolean }
    featuresJson: { type: string }

# --- ASSINATURA (CONTRATO) ---
AssinaturaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid }
    plano: { $ref: '#/PlanoResponse' }
    status: { $ref: '#/StatusAssinaturaEnum' }
    tipoFaturamento: { $ref: '#/TipoFaturamentoEnum' }
    dataInicio: { type: string, format: date-time }
    dataFim: { type: string, format: date-time }
    dataTrialFim: { type: string, format: date-time }

# Request para iniciar/trocar plano
AssinarPlanoRequest:
  type: object
  required: [ planoId, ciclo ]
  properties:
    planoId:
      type: string
      format: uuid
    ciclo:
      type: string
      enum: [MENSAL, ANUAL]

CadastroSaasRequest:
  type: object
  required: [ nomeUsuario, email, senha, crmv, nomeClinica, planoId ]
  properties:
    nomeUsuario:
      type: string
    email:
      type: string
      format: email
    senha:
      type: string
    crmv:
      type: string
    nomeClinica:
      type: string
    planoId:
      type: string
      format: uuid

TokenResponse:
  type: object
  properties:
    token:
      type: string
    tipo:
      type: string
      example: "Bearer"
    expiraEm:
      type: string


ApiResponseToken:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/TokenResponse' }

# --- WRAPPERS ---
ApiResponsePlano:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/PlanoResponse' }

ApiResponseListaPlano:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/PlanoResponse' }

ApiResponseAssinatura:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/AssinaturaResponse' }
```

---

## src\main\resources\swagger\paths

### assinaturas.yml

```yaml
# src\main\resources\swagger\paths\assinaturas.yml
# ROTA: /api/v1/saas/minha-assinatura
assinatura_atual:
  get:
    tags: [Assinaturas]
    summary: Obter status da assinatura da clínica logada
    operationId: obterMinhaAssinatura
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Detalhes da assinatura vigente
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAssinatura' }

# ROTA: /api/v1/saas/assinar
assinar_plano:
  post:
    tags: [Assinaturas]
    summary: Contratar ou mudar de plano
    description: "Inicia o fluxo de checkout ou altera o plano imediatamente se for upgrade gratuito/trial"
    operationId: assinarPlano
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AssinarPlanoRequest' }
    responses:
      '200':
        description: Sucesso (ou Link de Pagamento no futuro)
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAssinatura' }
```

### auth-public.yml

```yaml
# src\main\resources\swagger\paths\auth-public.yml
# ROTA: /api/v1/public/cadastro-saas
cadastro_saas:
  post:
    tags: [Publico]
    summary: Criar conta com plano e clínica
    operationId: cadastrarClienteSaas
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/CadastroSaasRequest' }
    responses:
      '201':
        description: Conta criada e logada
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseToken' }
```

### planos.yml

```yaml
# src\main\resources\swagger\paths\planos.yml
# ROTA: /api/v1/saas/planos
planos_colecao:
  get:
    tags: [Planos]
    summary: Listar planos disponíveis
    operationId: listarPlanos
    responses:
      '200':
        description: Lista de planos
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaPlano' }

  post:
    tags: [Planos]
    summary: Criar novo plano (Super Admin)
    operationId: criarPlano
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/PlanoRequest' }
    responses:
      '201':
        description: Plano criado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePlano' }

# ROTA: /api/v1/saas/planos/{id}
planos_item:
  get:
    tags: [Planos]
    summary: Buscar plano por ID
    operationId: buscarPlanoPorId
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponsePlano' }
```


---

# Projeto: vestris-shared

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-shared</artifactId>

    <dependencies>
        <!-- JPA: Para usar @Entity, @Id, @MappedSuperclass -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- WEB: Para usar anotações JSON e ResponseEntity (Corrigido de webmvc para web) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- VALIDATION: Para usar @NotNull, @NotBlank nos DTOs -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- LOMBOK: Para getters/setters automáticos -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- TEST: Dependência única para testes (substitui todas aquelas separadas) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

---

## src\main\java\br\com\vestris\shared\domain\exceptions

### ExcecaoRegraNegocio.java

```java
// src\main\java\br\com\vestris\shared\domain\exceptions\ExcecaoRegraNegocio.java
package br.com.vestris.shared.domain.exceptions;

public class ExcecaoRegraNegocio extends RuntimeException {
    public ExcecaoRegraNegocio(String message) {
        super(message);
    }
}

```

### ExceptionRecursoNaoEncontrado.java

```java
// src\main\java\br\com\vestris\shared\domain\exceptions\ExceptionRecursoNaoEncontrado.java
package br.com.vestris.shared.domain.exceptions;

public class ExceptionRecursoNaoEncontrado extends RuntimeException {
    public ExceptionRecursoNaoEncontrado(String nomeDoRecurso, String id) {
        super(String.format("%s não encontrado com id: %s", nomeDoRecurso, id));
    }
}

```

---

## src\main\java\br\com\vestris\shared\domain\model

### EntidadeBase.java

```java
// src\main\java\br\com\vestris\shared\domain\model\EntidadeBase.java
package br.com.vestris.shared.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class EntidadeBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    // Equals e HashCode baseados apenas no ID (Boas práticas do Hibernate)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntidadeBase)) return false;
        EntidadeBase that = (EntidadeBase) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

```

---

## src\main\java\br\com\vestris\shared\infrastructure\dto

### RespostaApi.java

```java
// src\main\java\br\com\vestris\shared\infrastructure\dto\RespostaApi.java
package br.com.vestris.shared.infrastructure.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespostaApi<T>{
    private boolean sucesso;
    private String mensagem;
    private T dados; // O objeto que você quer retornar (ex: Especie)
    private LocalDateTime dataHora;

    // Método estático para facilitar o sucesso
    public static <T> RespostaApi<T> sucesso(T dados) {
        return RespostaApi.<T>builder()
                .sucesso(true)
                .dados(dados)
                .dataHora(LocalDateTime.now())
                .build();
    }

    // Método estático para facilitar o erro
    public static <T> RespostaApi<T> erro(String mensagem) {
        return RespostaApi.<T>builder()
                .sucesso(false)
                .mensagem(mensagem)
                .dataHora(LocalDateTime.now())
                .build();
    }
}

```

---

## src\main\java\br\com\vestris\shared\infrastructure\handler

### ManipuladorGlobalDeExceptions.java

```java
// src\main\java\br\com\vestris\shared\infrastructure\handler\ManipuladorGlobalDeExceptions.java
package br.com.vestris.shared.infrastructure.handler;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.shared.infrastructure.dto.RespostaApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipuladorGlobalDeExceptions {

    // Trata erros de Regra de Negócio (ex: Espécie não existe, Duplicidade)
    // Retorna 400 Bad Request
    @ExceptionHandler(ExcecaoRegraNegocio.class)
    public ResponseEntity<RespostaApi<Void>> tratarRegraNegocio(ExcecaoRegraNegocio ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RespostaApi.erro(ex.getMessage()));
    }

    // Trata erros de Recurso Não Encontrado (ex: ID inexistente no banco)
    // Retorna 404 Not Found
    @ExceptionHandler(ExceptionRecursoNaoEncontrado.class)
    public ResponseEntity<RespostaApi<Void>> tratarRecursoNaoEncontrado(ExceptionRecursoNaoEncontrado ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RespostaApi.erro(ex.getMessage()));
    }

    // Trata qualquer outro erro não previsto (NullPointer, Banco fora do ar, etc)
    // Retorna 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaApi<Void>> tratarErroGenerico(Exception ex) {
        ex.printStackTrace(); // Imprime no terminal para o dev ver
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RespostaApi.erro("Ocorreu um erro interno no servidor. Contate o suporte."));
    }

}

```

---

## src\main\java\br\com\vestris\shared\infrastructure\helper

### HelperAuditoria.java

```java
// src\main\java\br\com\vestris\shared\infrastructure\helper\HelperAuditoria.java
package br.com.vestris.shared.infrastructure.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Helper centralizado para operações de auditoria.
 * Responsável por:
 * - Extrair clinicaId de diferentes contextos
 * - Montar metadados estruturados
 * - Serializar dados para auditoria
 */
@Component
@RequiredArgsConstructor
public class HelperAuditoria {
    public Map<String, Object> montarMetadados(Object... args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 < args.length) {
                map.put(String.valueOf(args[i]), args[i + 1]);
            }
        }
        return map;
    }

    public Map<String, Object> montarMetadadosPaciente(String nome, String especie, String tutor) {
        return montarMetadados(
                "nomePaciente", nome,
                "especie", especie,
                "tutor", tutor
        );
    }

    public Map<String, Object> montarMetadadosAtendimento(String pacienteNome, String status, UUID vetId, Object... extras) {
        Map<String, Object> map = montarMetadados(
                "paciente", pacienteNome,
                "status", status,
                "veterinarioId", vetId
        );
        // Adiciona extras
        if (extras != null) {
            map.putAll(montarMetadados(extras));
        }
        return map;
    }

    public Map<String, Object> montarMetadadosProtocolo(String titulo, String origem, UUID autorId, Object... extras) {
        Map<String, Object> map = montarMetadados(
                "titulo", titulo,
                "origem", origem,
                "autorId", autorId
        );
        if (extras != null) map.putAll(montarMetadados(extras));
        return map;
    }
}


```


---

# Projeto: vestris-species

## .

### .openapi-generator-ignore

```text
# .openapi-generator-ignore
# Ignora todos os testes gerados automaticamente
src/test/**

# Ignora arquivos de configuração inúteis
pom.xml
README.md
.gitignore
```

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-species</artifactId>

    <dependencies>
        <!-- Módulo Compartilhado (EntidadeBase, RespostaApi) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Dependências OBRIGATÓRIAS para o código gerado pelo OpenAPI -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>

        <!-- SWAGGER: Models (ESSA ERA A QUE FALTAVA! Para classe OpenAPI) -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>

        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>

        <!-- LOMBOK: Para getters/setters automáticos -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- JPA: Para usar @Entity, @Id, @MappedSuperclass -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Web Starter (Necessário para as anotações Spring Web geradas) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin do OpenAPI Generator -->
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <!-- Seus caminhos (mantenha como estão) -->
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <ignoreFileOverride>${project.basedir}/.openapi-generator-ignore</ignoreFileOverride>

                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.species.interfaces.api</apiPackage> <!-- (Mude para 'clinical' no outro pom) -->
                            <modelPackage>br.com.vestris.species.interfaces.dto</modelPackage> <!-- (Mude para 'clinical' no outro pom) -->

                            <!-- Desliga testes de API e Modelos -->
                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>

                            <!-- O SEGREDO ESTÁ AQUI: -->
                            <!-- Dizemos explicitamente: "Gere APENAS o ApiUtil.java. Não gere Application.java nem Tests.java" -->
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## src\main\java\br\com\vestris\species\application

### ServiceEspecie.java

```java
// src\main\java\br\com\vestris\species\application\ServiceEspecie.java
package br.com.vestris.species.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.domain.Especie;
import br.com.vestris.species.domain.repository.RepositorioEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceEspecie {

    private final RepositorioEspecie repositorio;

    public Especie criar(Especie novaEspecie) {
        // Regra: Nome científico deve ser único
        if (repositorio.existsByNomeCientifico(novaEspecie.getNomeCientifico())) {
            throw new ExcecaoRegraNegocio("Já existe uma espécie cadastrada com o nome científico: " + novaEspecie.getNomeCientifico());
        }
        return repositorio.save(novaEspecie);
    }

    public List<Especie> listarTodas(){
        return repositorio.findAll();
    }

    public Especie buscarPorId(UUID id){
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Especie", id.toString()));
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    // ATUALIZAR (Mapeamento Completo)
    public Especie atualizar(UUID id, Especie dados) {
        Especie existente = buscarPorId(id);

        existente.setNomePopular(dados.getNomePopular());
        existente.setNomeCientifico(dados.getNomeCientifico());
        existente.setFamiliaTaxonomica(dados.getFamiliaTaxonomica());
        existente.setGrupo(dados.getGrupo());

        existente.setResumoClinico(dados.getResumoClinico());
        existente.setParametrosFisiologicos(dados.getParametrosFisiologicos());
        existente.setExpectativaVida(dados.getExpectativaVida());
        existente.setPesoAdulto(dados.getPesoAdulto());

        existente.setTipoDieta(dados.getTipoDieta());
        existente.setManejoInfos(dados.getManejoInfos());

        existente.setAlertasClinicos(dados.getAlertasClinicos());
        existente.setPontosExameFisico(dados.getPontosExameFisico());

        existente.setHabitat(dados.getHabitat());
        existente.setDistribuicaoGeografica(dados.getDistribuicaoGeografica());
        existente.setStatusConservacao(dados.getStatusConservacao());

        existente.setBibliografiaBase(dados.getBibliografiaBase());

        return repositorio.save(existente);
    }

    // DELETAR
    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover esta espécie pois ela possui registros vinculados.");
        }
    }
}

```

### ServiceModeloExame.java

```java
// src\main\java\br\com\vestris\species\application\ServiceModeloExame.java
package br.com.vestris.species.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.domain.ModeloExameFisico;
import br.com.vestris.species.domain.repository.RepositorioModeloExame;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceModeloExame {
    private final RepositorioModeloExame repositorio;
    private final ServiceEspecie serviceEspecie; // Para validar se a espécie existe

    // --- BUSCAR ---
    public ModeloExameFisico buscarPorEspecie(UUID especieId) {
        // Valida se a espécie existe antes de buscar o modelo
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        return repositorio.findByEspecieId(especieId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Modelo de Exame Físico para a espécie", especieId.toString()));
    }

    // --- CRIAR ---
    @Transactional
    public ModeloExameFisico criar(UUID especieId, String textoBase) {
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        // Regra: Uma espécie só pode ter UM modelo ativo
        if (repositorio.findByEspecieId(especieId).isPresent()) {
            throw new ExcecaoRegraNegocio("Já existe um modelo de exame físico cadastrado para esta espécie. Use a atualização (PUT).");
        }

        ModeloExameFisico novo = new ModeloExameFisico();
        novo.setEspecieId(especieId);
        novo.setTextoBase(textoBase);

        return repositorio.save(novo);
    }

    // --- ATUALIZAR ---
    @Transactional
    public ModeloExameFisico atualizar(UUID especieId, String novoTextoBase) {
        ModeloExameFisico existente = buscarPorEspecie(especieId); // Já valida existência

        existente.setTextoBase(novoTextoBase);

        return repositorio.save(existente);
    }

    // --- DELETAR ---
    @Transactional
    public void deletar(UUID especieId) {
        ModeloExameFisico existente = buscarPorEspecie(especieId);
        repositorio.delete(existente);
    }

    // --- LISTAR TODOS ---
    public List<ModeloExameFisico> listarTodos() {
        return repositorio.findAll();
    }
}

```

---

## src\main\java\br\com\vestris\species\domain

### Especie.java

```java
// src\main\java\br\com\vestris\species\domain\Especie.java
package br.com.vestris.species.domain;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "especies", schema = "species_schema")
public class Especie extends EntidadeBase {
    // 1. Identificação
    @Column(nullable = false, length = 150)
    private String nomePopular;

    @Column(nullable = false, unique = true, length = 150)
    private String nomeCientifico;

    @Column(length = 100)
    private String familiaTaxonomica;

    @Column(length = 50)
    private String grupo; // Ave, Réptil, etc.

    // 2. Visão Geral
    @Column(columnDefinition = "TEXT")
    private String resumoClinico;

    // 3. Fisiologia
    @Column(columnDefinition = "TEXT")
    private String parametrosFisiologicos;

    private String expectativaVida;
    private String pesoAdulto;

    // 4. Manejo
    private String tipoDieta;
    @Column(columnDefinition = "TEXT")
    private String manejoInfos;

    // 5. Alertas
    @Column(columnDefinition = "TEXT")
    private String alertasClinicos;

    // 6. Exame Físico
    @Column(columnDefinition = "TEXT")
    private String pontosExameFisico;
    @Column(columnDefinition = "TEXT")
    private String receitaManejoPadrao;

    // 8. Biologia
    @Column(columnDefinition = "TEXT")
    private String habitat;
    @Column(columnDefinition = "TEXT")
    private String distribuicaoGeografica;
    private String statusConservacao;

    // 9. Referência
    private String bibliografiaBase;
}

```

### ModeloExameFisico.java

```java
// src\main\java\br\com\vestris\species\domain\ModeloExameFisico.java
package br.com.vestris.species.domain;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "modelos_exame_fisico", schema = "species_schema")
public class ModeloExameFisico extends EntidadeBase {
    @Column(nullable = false)
    private UUID especieId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String textoBase; // Guardará o JSON stringificado
}

```

---

## src\main\java\br\com\vestris\species\domain\repository

### RepositorioEspecie.java

```java
// src\main\java\br\com\vestris\species\domain\repository\RepositorioEspecie.java
package br.com.vestris.species.domain.repository;

import br.com.vestris.species.domain.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioEspecie extends JpaRepository<Especie, UUID> {
    // Verifica se já existe pelo nome científico
    boolean existsByNomeCientifico(String nomeCientifico);
}

```

### RepositorioModeloExame.java

```java
// src\main\java\br\com\vestris\species\domain\repository\RepositorioModeloExame.java
package br.com.vestris.species.domain.repository;

import br.com.vestris.species.domain.ModeloExameFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioModeloExame extends JpaRepository<ModeloExameFisico, UUID> {
    Optional<ModeloExameFisico> findByEspecieId(UUID especieId);
}

```

---

## src\main\java\br\com\vestris\species\interfaces\delegate

### ApiDelegateEspecies.java

```java
// src\main\java\br\com\vestris\species\interfaces\delegate\ApiDelegateEspecies.java
package br.com.vestris.species.interfaces.delegate;

import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.species.domain.Especie;
import br.com.vestris.species.interfaces.api.EspeciesApiDelegate;
import br.com.vestris.species.interfaces.dto.ApiResponseEspecie;
import br.com.vestris.species.interfaces.dto.EspecieRequest;
import br.com.vestris.species.interfaces.dto.EspecieResponse;
import br.com.vestris.species.interfaces.dto.ApiResponseListaEspecie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component // O Delegate deve ser um Componente Spring
@RequiredArgsConstructor
public class ApiDelegateEspecies implements EspeciesApiDelegate {
    private final ServiceEspecie servico;

    @Override
    public ResponseEntity<ApiResponseEspecie> criarEspecie(EspecieRequest request) {
        Especie entidade = converterParaEntidade(request);
        Especie salva = servico.criar(entidade);

        return ResponseEntity.ok(criarResponse(salva, "Espécie criada com sucesso."));
    }

    @Override
    public ResponseEntity<ApiResponseListaEspecie> listarEspecies(){
        List<EspecieResponse> listaDto = servico.listarTodas().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());

        ApiResponseListaEspecie wrapper = new ApiResponseListaEspecie();
        wrapper.setSucesso(true);
        wrapper.setDados(listaDto);

        return ResponseEntity.ok(wrapper);
    }

    @Override
    public ResponseEntity<ApiResponseEspecie> buscarEspeciePorId(UUID id) {
        Especie encontrada = servico.buscarPorId(id);
        return ResponseEntity.ok(criarResponse(encontrada, null));
    }

    @Override
    public ResponseEntity<ApiResponseEspecie> atualizarEspecie(UUID id, EspecieRequest request) {
        Especie dados = converterParaEntidade(request);
        Especie atualizada = servico.atualizar(id, dados);
        return ResponseEntity.ok(criarResponse(atualizada, "Atualizado com sucesso."));
    }

    @Override
    public ResponseEntity<Void> deletarEspecie(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSORES ---

    private ApiResponseEspecie criarResponse(Especie e, String msg) {
        ApiResponseEspecie r = new ApiResponseEspecie();
        r.setSucesso(true);
        r.setMensagem(msg);
        r.setDados(converterParaResponse(e));
        return r;
    }

    // DTO -> Entidade
    private Especie converterParaEntidade(EspecieRequest r) {
        Especie e = new Especie();
        e.setNomePopular(r.getNomePopular());
        e.setNomeCientifico(r.getNomeCientifico());
        e.setFamiliaTaxonomica(r.getFamiliaTaxonomica());
        e.setGrupo(r.getGrupo());

        e.setResumoClinico(r.getResumoClinico());
        e.setParametrosFisiologicos(r.getParametrosFisiologicos());
        e.setExpectativaVida(r.getExpectativaVida());
        e.setPesoAdulto(r.getPesoAdulto());

        e.setTipoDieta(r.getTipoDieta());
        e.setManejoInfos(r.getManejoInfos());
        e.setReceitaManejoPadrao(r.getReceitaManejoPadrao());
        e.setAlertasClinicos(r.getAlertasClinicos());
        e.setPontosExameFisico(r.getPontosExameFisico());

        e.setHabitat(r.getHabitat());
        e.setDistribuicaoGeografica(r.getDistribuicaoGeografica());
        e.setStatusConservacao(r.getStatusConservacao());
        e.setBibliografiaBase(r.getBibliografiaBase());

        return e;
    }

    // Entidade -> DTO
    private EspecieResponse converterParaResponse(Especie e) {
        EspecieResponse dto = new EspecieResponse();
        dto.setId(e.getId());

        dto.setNomePopular(e.getNomePopular());
        dto.setNomeCientifico(e.getNomeCientifico());
        dto.setFamiliaTaxonomica(e.getFamiliaTaxonomica());
        dto.setGrupo(e.getGrupo());

        dto.setResumoClinico(e.getResumoClinico());
        dto.setParametrosFisiologicos(e.getParametrosFisiologicos());
        dto.setExpectativaVida(e.getExpectativaVida());
        dto.setPesoAdulto(e.getPesoAdulto());

        dto.setTipoDieta(e.getTipoDieta());
        dto.setManejoInfos(e.getManejoInfos());
        dto.setReceitaManejoPadrao(e.getReceitaManejoPadrao());
        dto.setAlertasClinicos(e.getAlertasClinicos());
        dto.setPontosExameFisico(e.getPontosExameFisico());

        dto.setHabitat(e.getHabitat());
        dto.setDistribuicaoGeografica(e.getDistribuicaoGeografica());
        dto.setStatusConservacao(e.getStatusConservacao());
        dto.setBibliografiaBase(e.getBibliografiaBase());

        if (e.getCriadoEm() != null) {
            dto.setCriadoEm(e.getCriadoEm().atOffset(ZoneOffset.UTC));
        }

        return dto;
    }
}

```

### ApiDelegateModeloExame.java

```java
// src\main\java\br\com\vestris\species\interfaces\delegate\ApiDelegateModeloExame.java
package br.com.vestris.species.interfaces.delegate;

import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceModeloExame;
import br.com.vestris.species.domain.ModeloExameFisico;
import br.com.vestris.species.interfaces.api.ExamesFisicosApiDelegate;
import br.com.vestris.species.interfaces.dto.ApiResponseListaModeloExame;
import br.com.vestris.species.interfaces.dto.ApiResponseModeloExame;
import br.com.vestris.species.interfaces.dto.ModeloExameRequest;
import br.com.vestris.species.interfaces.dto.ModeloExameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateModeloExame implements ExamesFisicosApiDelegate {
    private final ServiceModeloExame servico;

    // --- GET (Buscar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> obterModeloExame(UUID especieId) {
        try {
            ModeloExameFisico modelo = servico.buscarPorEspecie(especieId);
            return ResponseEntity.ok(criarResponse(modelo, "Modelo encontrado com sucesso."));
        } catch (ExceptionRecursoNaoEncontrado e) {
            // Se não encontrar o modelo, retornamos um 200 com dados nulos ou 404.
            // Para facilitar o front (evitar erro vermelho no console), vamos retornar 200 com dados null
            // e o front decide mostrar o "template genérico".
            ApiResponseModeloExame response = new ApiResponseModeloExame();
            response.setSucesso(false);
            response.setMensagem("Nenhum modelo específico encontrado. Use o padrão.");
            return ResponseEntity.ok(response);
        }
    }

    // --- POST (Criar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> criarModeloExame(UUID especieId, ModeloExameRequest request) {
        ModeloExameFisico salvo = servico.criar(especieId, request.getTextoBase());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(criarResponse(salvo, "Modelo de exame físico criado com sucesso."));
    }

    // --- PUT (Atualizar) ---
    @Override
    public ResponseEntity<ApiResponseModeloExame> atualizarModeloExame(UUID especieId, ModeloExameRequest request) {
        ModeloExameFisico atualizado = servico.atualizar(especieId, request.getTextoBase());
        return ResponseEntity.ok(criarResponse(atualizado, "Modelo atualizado com sucesso."));
    }

    // --- DELETE (Remover) ---
    @Override
    public ResponseEntity<Void> deletarModeloExame(UUID especieId) {
        servico.deletar(especieId);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR AUXILIAR ---
    private ApiResponseModeloExame criarResponse(ModeloExameFisico entidade, String mensagem) {
        ModeloExameResponse dto = new ModeloExameResponse();
        dto.setId(entidade.getId());
        dto.setEspecieId(entidade.getEspecieId());
        dto.setTextoBase(entidade.getTextoBase());

        ApiResponseModeloExame wrapper = new ApiResponseModeloExame();
        wrapper.setSucesso(true);
        wrapper.setMensagem(mensagem);
        wrapper.setDados(dto);

        return wrapper;
    }

    @Override
    public ResponseEntity<ApiResponseListaModeloExame> listarTodosModelosExame() {
        List<ModeloExameResponse> listaDto = servico.listarTodos().stream()
                .map(entidade -> {
                    ModeloExameResponse dto = new ModeloExameResponse();
                    dto.setId(entidade.getId());
                    dto.setEspecieId(entidade.getEspecieId());
                    dto.setTextoBase(entidade.getTextoBase());
                    return dto;
                })
                .collect(Collectors.toList());

        ApiResponseListaModeloExame response = new ApiResponseListaModeloExame();
        response.setSucesso(true);
        response.setDados(listaDto);

        return ResponseEntity.ok(response);
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Espécies
  description: API de Taxonomia do Silvet
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

# Importando os caminhos
paths:
  /api/v1/especies:
    $ref: './paths/especies.yml#/paths/~1api~1v1~1especies'

  /api/v1/especies/{id}:
    $ref: './paths/especies.yml#/paths/~1api~1v1~1especies~1{id}'

  # ADICIONE ESTA LINHA:
  /api/v1/especies/{especieId}/exame-fisico:
    $ref: './paths/exames-fisicos.yml#/exame_fisico_por_especie'

  /api/v1/exames-fisicos:
    $ref: './paths/exames-fisicos.yml#/exames_fisicos_colecao'

components:
  schemas:
    EspecieRequest:
      $ref: "./components/schemas.yml#/EspecieRequest"
    EspecieResponse:
      $ref: "./components/schemas.yml#/EspecieResponse"
    ModeloExameRequest:
      $ref: "./components/schemas.yml#/ModeloExameRequest"
    ModeloExameResponse:
      $ref: "./components/schemas.yml#/ModeloExameResponse"
    ApiResponseModeloExame:
      $ref: "./components/schemas.yml#/ApiResponseModeloExame"
    ApiResponseEspecie:
      $ref: "./components/schemas.yml#/ApiResponseEspecie"
    ApiResponseListaEspecie:
      $ref: "./components/schemas.yml#/ApiResponseListaEspecie"
```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
EspecieRequest:
  type: object
  required:
    - nomePopular
    - nomeCientifico
  properties:
    nomePopular:
      type: string
      example: "Calopsita"
    nomeCientifico:
      type: string
      example: "Nymphicus hollandicus"
    familiaTaxonomica:
      type: string
      example: "Psittacidae"
    grupo:
      type: string
      example: "Ave"
    resumoClinico:
      type: string
      description: "Visão geral rápida para o clínico."
    parametrosFisiologicos:
      type: string
      description: "FC, FR, Temp (Markdown)"
    expectativaVida:
      type: string
    pesoAdulto:
      type: string
    tipoDieta:
      type: string
    manejoInfos:
      type: string
      description: "Erros comuns de manejo"
    receitaManejoPadrao:
      type: string
      description: "8 pilares de manejo pré-preenchidos"
    alertasClinicos:
      type: string
      description: "Sensibilidades a medicamentos"
    pontosExameFisico:
      type: string
      description: "O que não esquecer no exame"
    habitat: { type: string }
    distribuicaoGeografica: { type: string }
    statusConservacao: { type: string }
    bibliografiaBase: { type: string }


EspecieResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nomePopular: { type: string }
    nomeCientifico: { type: string }
    familiaTaxonomica: { type: string }
    grupo: { type: string }
    resumoClinico: { type: string }
    parametrosFisiologicos: { type: string }
    expectativaVida: { type: string }
    pesoAdulto: { type: string }
    tipoDieta: { type: string }
    manejoInfos: { type: string }
    receitaManejoPadrao: { type: string }
    alertasClinicos: { type: string }
    pontosExameFisico: { type: string }
    habitat: { type: string }
    distribuicaoGeografica: { type: string }
    statusConservacao: { type: string }
    bibliografiaBase: { type: string }
    criadoEm: { type: string, format: date-time }

# --- MODELOS DE EXAME FÍSICO ---

# Request (O que o Front envia para criar/editar)
ModeloExameRequest:
  type: object
  required: [ textoBase ]
  properties:
    textoBase:
      type: string
      description: "JSON Stringificado contendo as seções do exame. Ex: [{'titulo': 'Geral', 'conteudo': '...'}]"
      example: '[{"titulo": "Avaliação Geral", "conteudo": "Postura, alerta..."}]'

# Response (O que o Back devolve)
ModeloExameResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    especieId:
      type: string
      format: uuid
    textoBase:
      type: string
      description: "JSON Stringificado com as seções"

# Wrapper da API
ApiResponseModeloExame:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/ModeloExameResponse' }

ApiResponseEspecie:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
    dados: { $ref: '#/EspecieResponse' }
    dataHora: { type: string, format: date-time }

ApiResponseListaEspecie:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/EspecieResponse' }

# Wrapper de Lista para Exames
ApiResponseListaModeloExame:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/ModeloExameResponse' }
```

---

## src\main\resources\swagger\paths

### especies.yml

```yaml
# src\main\resources\swagger\paths\especies.yml
paths:
  # ---------------------------------------------------------
  # ROTA 1: Coleção (Listar / Criar)
  # ---------------------------------------------------------
  /api/v1/especies:
    get:
      tags:
        - Especies
      summary: Listar todas as espécies
      operationId: listarEspecies
      responses:
        '200':
          description: Lista recuperada
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseListaEspecie'
    post:
      tags:
        - Especies
      summary: Cadastrar nova espécie
      operationId: criarEspecie
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/EspecieRequest'
      responses:
        '200':
          description: Espécie criada com sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

  # ---------------------------------------------------------
  # ROTA 2: Item Específico (Buscar / Editar / Deletar)
  # ---------------------------------------------------------
  /api/v1/especies/{id}:
    parameters:
      - name: id
        in: path
        required: true
        schema:
          type: string
          format: uuid

    get:
      tags:
        - Especies
      summary: Buscar espécie por ID
      operationId: buscarEspeciePorId
      responses:
        '200':
          description: Encontrado
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

    put:
      tags:
        - Especies
      summary: Atualizar dados da espécie
      operationId: atualizarEspecie
      requestBody:
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/EspecieRequest'
      responses:
        '200':
          description: Atualizado com sucesso
          content:
            application/json:
              schema:
                $ref: '../components/schemas.yml#/ApiResponseEspecie'

    delete:
      tags:
        - Especies
      summary: Remover espécie
      operationId: deletarEspecie
      responses:
        '204':
          description: Removido com sucesso

```

### exames-fisicos.yml

```yaml
# src\main\resources\swagger\paths\exames-fisicos.yml
# ROTA: /api/v1/especies/{especieId}/exame-fisico
exame_fisico_por_especie:

  # 1. GET - Buscar o modelo (Já existia, agora isolado)
  get:
    tags: [ExamesFisicos]
    summary: Obter modelo de exame físico da espécie
    operationId: obterModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Modelo encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }
      '404':
        description: Modelo não definido para esta espécie

  # 2. POST - Criar um modelo novo para a espécie
  post:
    tags: [ExamesFisicos]
    summary: Criar modelo de exame físico
    description: "Define o template padrão para uma espécie específica."
    operationId: criarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ModeloExameRequest' }
    responses:
      '201':
        description: Criado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }

  # 3. PUT - Atualizar o texto do modelo existente
  put:
    tags: [ExamesFisicos]
    summary: Atualizar modelo de exame físico
    operationId: atualizarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ModeloExameRequest' }
    responses:
      '200':
        description: Atualizado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseModeloExame' }

  # 4. DELETE - Remover o modelo (voltar ao padrão genérico)
  delete:
    tags: [ExamesFisicos]
    summary: Remover modelo de exame físico
    operationId: deletarModeloExame
    parameters:
      - name: especieId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso

# ROTA: /api/v1/exames-fisicos (Lista Geral)
exames_fisicos_colecao:
  get:
    tags: [ExamesFisicos]
    summary: Listar todos os modelos cadastrados
    description: "Retorna todos os templates de exame físico de todas as espécies."
    operationId: listarTodosModelosExame
    responses:
      '200':
        description: Lista recuperada com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaModeloExame' }

```


---

# Projeto: vestris-user

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-user</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- SECURITY -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- JWT (JSON Web Token) -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Swagger & Web & JPA (Padrão) -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Plugin OpenAPI (Mesma config de sempre) -->
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.user.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.user.interfaces.dto</modelPackage>

                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    
</project>
```

---

## src\main\java\br\com\vestris\user\application

### ServiceAuditoria.java

```java
// src\main\java\br\com\vestris\user\application\ServiceAuditoria.java
package br.com.vestris.user.application;

import br.com.vestris.user.domain.model.Auditoria;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioAuditoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ServiceAuditoria {
    private final RepositorioAuditoria repositorio;
    private final ObjectMapper objectMapper; // Para converter metadados em JSON
    private final ServiceUsuario serviceUsuario;

    /**
     * Registra um evento de auditoria.
     * Usa transação REQUIRES_NEW para garantir que o log seja salvo mesmo se a operação principal falhar (opcional, mas bom para logs de erro),
     * ou MANDATORY se quiser que faça parte da mesma transação. Vamos usar o padrão (REQUIRED) para simplicidade.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void registrar(
            UUID clinicaId,
            UUID usuarioId,
            AcaoAuditoria acao,
            EntidadeAuditoria entidade,
            UUID idAlvo,
            String descricaoCurta,
            Map<String, Object> metadados
    )
    {
        if (usuarioId == null) {
            System.err.println("[AUDIT ERROR] Tentativa de log sem usuário.");
            return;
        }

        // Tenta recuperar ClinicaID se vier nulo (fallback)
        if (clinicaId == null) {
            try {
                Usuario u = serviceUsuario.buscarPorId(usuarioId);
                if (u.getClinica() != null) {
                    clinicaId = u.getClinica().getId();
                }
            } catch (Exception e) {
                // Ignora, vai salvar sem clinicaId (pode quebrar se for NOT NULL no banco, então cuidado)
            }
        }

        // Se ainda for nulo e o banco exige, aborta ou usa um ID de sistema
        if (clinicaId == null) {
            System.err.println("[AUDIT ERROR] Auditoria ignorada: Usuário " + usuarioId + " não tem clínica vinculada.");
            return;
        }

        try {
            Auditoria log = new Auditoria();
            log.setId(UUID.randomUUID());
            log.setClinicaId(clinicaId);
            log.setUsuarioId(usuarioId);
            log.setAcao(acao);
            log.setEntidade(entidade);
            log.setIdAlvo(idAlvo);
            log.setDescricaoCurta(descricaoCurta);
            log.setDataHora(LocalDateTime.now());

            if (metadados != null && !metadados.isEmpty()) {
                log.setMetadados(objectMapper.writeValueAsString(metadados));
            }

            repositorio.saveAndFlush(log);

        } catch (Exception e) {
            // Auditoria não deve quebrar o fluxo de negócio, apenas logar o erro no console
            e.printStackTrace();
        }
    }

    // Sobrecarga simples
    public void registrar(UUID clinicaId, UUID usuarioId, AcaoAuditoria acao, EntidadeAuditoria entidade, UUID idAlvo, String descricao) {
        registrar(clinicaId, usuarioId, acao, entidade, idAlvo, descricao, null);
    }
}

```

### ServiceAuth.java

```java
// src\main\java\br\com\vestris\user\application\ServiceAuth.java
package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import br.com.vestris.user.infrastructure.security.ServicoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceAuth {
    private final RepositorioUsuario repositorio;
    private final PasswordEncoder passwordEncoder;
    private final ServicoToken servicoToken;

    public Usuario registrar(String nome, String email, String senhaAberta, String crmv) {
        if (repositorio.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("E-mail já cadastrado.");
        }
        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setCrmv(crmv);
        // Default para cadastro comum
        novo.setPerfil(crmv != null && !crmv.isBlank() ? Perfil.VETERINARIO : Perfil.ESTUDANTE);
        novo.setScope(Usuario.UserScope.TENANT); // Padrão é Tenant
        novo.setSenha(passwordEncoder.encode(senhaAberta));

        return repositorio.save(novo);
    }

    public String login(String email, String senha) {
        Usuario usuario = repositorio.findByEmail(email)
                .orElseThrow(() -> new ExcecaoRegraNegocio("Usuário ou senha inválidos."));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new ExcecaoRegraNegocio("Usuário ou senha inválidos.");
        }
        return servicoToken.gerarToken(usuario);
    }

    // --- GOD MODE (IMPERSONATE) ---
    public String impersonate(UUID adminId, UUID targetUserId) {
        System.out.println("--- INICIANDO IMPERSONATE ---");
        System.out.println("Admin ID recebido: " + adminId);

        Usuario admin = repositorio.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        System.out.println("Admin encontrado: " + admin.getEmail());
        System.out.println("Scope do Admin no Objeto: " + admin.getScope());

        // VALIDAÇÃO COM LOG
        if (admin.getScope() == null) {
            System.out.println("ERRO: Scope é NULL");
            throw new ExcecaoRegraNegocio("Seu usuário não possui escopo definido.");
        }

        if (admin.getScope() != Usuario.UserScope.GLOBAL) {
            System.out.println("ERRO: Scope não é GLOBAL. É: " + admin.getScope());
            throw new ExcecaoRegraNegocio("Acesso negado: Requer privilégio GLOBAL.");
        }

        Usuario alvo = repositorio.findById(targetUserId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário alvo", targetUserId.toString()));

        System.out.println("Alvo encontrado: " + alvo.getEmail());

        return servicoToken.gerarToken(alvo);
    }
}

```

### ServiceClinica.java

```java
// src\main\java\br\com\vestris\user\application\ServiceClinica.java
package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioClinica;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceClinica {
    private final RepositorioClinica repoClinica;
    private final RepositorioUsuario repoUsuario;
    private final PasswordEncoder passwordEncoder;

    // Injeta a INTERFACE, não a classe concreta do outro módulo
    private final ValidadorPlanoService validadorPlano;

    public Clinica buscarPorUsuario(UUID usuarioId) {
        Usuario user = repoUsuario.findById(usuarioId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", usuarioId.toString()));

        return user.getClinica();
    }

    @Transactional
    public Clinica salvar(UUID usuarioId, Clinica dados) {
        Usuario user = repoUsuario.findById(usuarioId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", usuarioId.toString()));

        Clinica clinica = user.getClinica();

        if (clinica == null) {
            clinica = new Clinica();
        }

        clinica.setNomeFantasia(dados.getNomeFantasia());
        clinica.setRazaoSocial(dados.getRazaoSocial());
        clinica.setCnpj(dados.getCnpj());
        clinica.setEndereco(dados.getEndereco());
        clinica.setTelefone(dados.getTelefone());
        clinica.setEmailContato(dados.getEmailContato());
        clinica.setLogoBase64(dados.getLogoBase64());

        clinica = repoClinica.save(clinica);

        if (user.getClinica() == null) {
            user.setClinica(clinica);
            repoUsuario.save(user);
        }

        return clinica;
    }

    // --- GESTÃO DE EQUIPE ---

    public List<Usuario> listarMembros(UUID adminId) {
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        if (admin.getClinica() == null) return List.of();

        return repoUsuario.findByClinicaId(admin.getClinica().getId());
    }

    @Transactional
    public Usuario adicionarMembro(UUID adminId, String nome, String email, String senha, String crmv) {
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));

        if (admin.getClinica() == null) {
            throw new ExcecaoRegraNegocio("Você precisa salvar os dados da clínica antes de adicionar equipe.");
        }

        // --- GUARDIÃO VIA INTERFACE ---
        // O Spring vai procurar quem implementa essa interface (no caso, o módulo SaaS)
        validadorPlano.validarLimiteVeterinarios(admin.getClinica().getId());
        // ------------------------------

        if (repoUsuario.existsByEmail(email)) {
            throw new ExcecaoRegraNegocio("E-mail já cadastrado no sistema.");
        }

        Usuario novo = new Usuario();
        novo.setNome(nome);
        novo.setEmail(email);
        novo.setSenha(passwordEncoder.encode(senha));
        novo.setCrmv(crmv);
        novo.setPerfil(Perfil.VETERINARIO);
        novo.setScope(Usuario.UserScope.TENANT);
        novo.setClinica(admin.getClinica());

        return repoUsuario.save(novo);
    }

    // ... (método removerMembro mantido igual) ...
    @Transactional
    public void removerMembro(UUID adminId, UUID membroId) {
        if (adminId.equals(membroId)) {
            throw new ExcecaoRegraNegocio("Você não pode remover a si mesmo da equipe.");
        }
        Usuario admin = repoUsuario.findById(adminId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Admin", adminId.toString()));
        Usuario membro = repoUsuario.findById(membroId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Veterinário", membroId.toString()));

        if (admin.getClinica() == null) {
            throw new ExcecaoRegraNegocio("Admin não possui clínica vinculada.");
        }

        if (membro.getClinica() == null || !membro.getClinica().getId().equals(admin.getClinica().getId())) {
            throw new ExcecaoRegraNegocio("Este veterinário não pertence à sua equipe.");
        }

        membro.setClinica(null);
        repoUsuario.save(membro);
    }
}

```

### ServiceUsuario.java

```java
// src\main\java\br\com\vestris\user\application\ServiceUsuario.java
package br.com.vestris.user.application;

import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceUsuario {

    private final RepositorioUsuario repositorio;

    public List<Usuario> listar(String perfilStr, Boolean apenasComCrmv) {
        // 1. Filtro por CRMV
        if (Boolean.TRUE.equals(apenasComCrmv)) {
            return repositorio.findAllComCrmv();
        }

        // 2. Filtro por Perfil
        if (perfilStr != null) {
            try {
                Perfil perfil = Perfil.valueOf(perfilStr.toUpperCase());
                return repositorio.findByPerfil(perfil);
            } catch (IllegalArgumentException e) {
                // Se mandar perfil errado, retorna vazio ou erro (decisão sua)
                return List.of();
            }
        }

        // 3. Sem filtros: retorna tudo
        return repositorio.findAll();
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    public Usuario buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Usuário", id.toString()));
    }

    public Usuario atualizar(UUID id, String novoNome, String novoCrmv) {
        Usuario usuario = buscarPorId(id);

        if (novoNome != null) usuario.setNome(novoNome);
        if (novoCrmv != null) usuario.setCrmv(novoCrmv);

        // Regra de negócio: Se ganhou CRMV, vira Veterinário automaticamente?
        if (novoCrmv != null && !novoCrmv.isBlank()) {
            usuario.setPerfil(Perfil.VETERINARIO);
        }

        return repositorio.save(usuario);
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Usuário", id.toString());
        }
        repositorio.deleteById(id);
    }

    public List<Usuario> listarPorClinica(UUID clinicaId) {
        return repositorio.findByClinicaId(clinicaId);
    }
}

```

### ValidadorPlanoService.java

```java
// src\main\java\br\com\vestris\user\application\ValidadorPlanoService.java
package br.com.vestris.user.application;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ValidadorPlanoService {
    void validarLimiteVeterinarios(UUID clinicaId);
}

```

---

## src\main\java\br\com\vestris\user\domain\model

### AcaoAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\AcaoAuditoria.java
package br.com.vestris.user.domain.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum para padronizar as ações de auditoria no sistema.
 * Garante type-safety e facilita buscas/filtros por ação específica.
 */
public enum AcaoAuditoria {
    // Paciente
    PACIENTE_CRIADO("Paciente criado"),
    PACIENTE_EDITADO("Paciente editado"),
    PACIENTE_CANCELADO("Paciente cancelado"),

    // Atendimento
    ATENDIMENTO_AGENDADO("Atendimento agendado"),
    ATENDIMENTO_INICIADO("Atendimento iniciado"),
    ATENDIMENTO_FINALIZADO("Atendimento finalizado"),
    ATENDIMENTO_CANCELADO("Atendimento cancelado"),
    ATENDIMENTO_EDITADO("Atendimento editado"),

    // Prontuário
    PRONTUARIO_EDITADO("Prontuário editado"),
    PRONTUARIO_SALVO("Prontuário salvo"),

    // Anexos e Exames
    ANEXO_ADICIONADO("Anexo/Exame adicionado"),
    ANEXO_REMOVIDO("Anexo/Exame removido"),

    // PDFs
    PDF_RECEITA_GERADO("PDF de receita médica gerado"),
    PDF_MANEJO_GERADO("PDF de manejo gerado"),
    PDF_PRONTUARIO_GERADO("PDF de prontuário gerado"),

    // Protocolos
    PROTOCOLO_CRIADO("Protocolo criado"),
    PROTOCOLO_EDITADO("Protocolo editado"),
    PROTOCOLO_REMOVIDO("Protocolo removido"),
    PROTOCOLO_CANCELADO("Protocolo cancelado"),

    // Genéricos
    RECURSO_CRIADO("Recurso criado"),
    RECURSO_EDITADO("Recurso editado"),
    RECURSO_DELETADO("Recurso deletado");

    private final String descricao;

    AcaoAuditoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Map of legacy names to enums for tolerant conversion from DB
    private static final Map<String, AcaoAuditoria> LEGACY_MAP = new HashMap<>();

    static {
        // Common legacy tokens that appeared in the project history
        LEGACY_MAP.put("CRIOU", RECURSO_CRIADO);
        LEGACY_MAP.put("FINALIZOU", ATENDIMENTO_FINALIZADO);
        LEGACY_MAP.put("STATUS_UPDATE", ATENDIMENTO_EDITADO);
        LEGACY_MAP.put("FINALIZADO", ATENDIMENTO_FINALIZADO);
        LEGACY_MAP.put("CRIADO", RECURSO_CRIADO);
        LEGACY_MAP.put("EDITOU", RECURSO_EDITADO);
        LEGACY_MAP.put("REMOVIDO", ANEXO_REMOVIDO);
        LEGACY_MAP.put("DELETADO", RECURSO_DELETADO);
        LEGACY_MAP.put("ATUALIZOU", RECURSO_EDITADO);
        // Add any other observed legacy tokens here
    }

    /**
     * Tolerant mapping from a database string to the enum.
     * Attempts in order: exact name match, description match, legacy aliases (case-insensitive).
     */
    public static AcaoAuditoria fromString(String s) {
        if (s == null) return null;
        String trimmed = s.trim();
        // 1) Try exact enum name
        try {
            return AcaoAuditoria.valueOf(trimmed);
        } catch (IllegalArgumentException ignored) {
        }

        // 2) Try matching by description
        for (AcaoAuditoria a : values()) {
            if (a.getDescricao().equalsIgnoreCase(trimmed)) return a;
        }

        // 3) Try legacy map (case-insensitive)
        AcaoAuditoria fromLegacy = LEGACY_MAP.get(trimmed.toUpperCase());
        if (fromLegacy != null) return fromLegacy;

        // 4) Fallback: try to match by transform (replace spaces/accents/underscores)
        String normalized = trimmed.toUpperCase().replaceAll("[^A-Z0-9]", "_");
        for (AcaoAuditoria a : values()) {
            if (a.name().equalsIgnoreCase(normalized)) return a;
        }

        // 5) Last resort: return null so caller can handle it
        return null;
    }
}

```

### AcaoAuditoriaConverter.java

```java
// src\main\java\br\com\vestris\user\domain\model\AcaoAuditoriaConverter.java
package br.com.vestris.user.domain.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class AcaoAuditoriaConverter implements AttributeConverter<AcaoAuditoria, String> {

    @Override
    public String convertToDatabaseColumn(AcaoAuditoria attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public AcaoAuditoria convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        AcaoAuditoria v = AcaoAuditoria.fromString(dbData);
        if (v != null) return v;
        // If mapping failed, try direct enum name (case-insensitive)
        try {
            return AcaoAuditoria.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            // fallback to null
            return null;
        }
    }
}


```

### Auditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\Auditoria.java
package br.com.vestris.user.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "auditoria", schema = "users_schema")
public class Auditoria {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID clinicaId;

    @Column(nullable = false)
    private UUID usuarioId;

    @Convert(converter = AcaoAuditoriaConverter.class)
    @Column(nullable = false)
    private AcaoAuditoria acao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntidadeAuditoria entidade;

    @Column(nullable = false)
    private UUID idAlvo;

    @Column(columnDefinition = "TEXT")
    private String descricaoCurta;

    // --- CORREÇÃO AQUI ---
    // Usamos TEXT simples para evitar conflito de tipo com o driver do Postgres
    @Column(columnDefinition = "TEXT", name = "metadados")
    private String metadados;

    @CreationTimestamp
    private LocalDateTime dataHora;
}

```

### Clinica.java

```java
// src\main\java\br\com\vestris\user\domain\model\Clinica.java
package br.com.vestris.user.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clinicas", schema = "users_schema")
public class Clinica extends EntidadeBase {

    @Column(nullable = false)
    private String nomeFantasia;

    private String razaoSocial;
    private String cnpj;

    @Column(columnDefinition = "TEXT")
    private String endereco;

    private String telefone;
    private String emailContato;

    @Column(columnDefinition = "TEXT")
    private String logoBase64;
}

```

### EntidadeAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\model\EntidadeAuditoria.java
package br.com.vestris.user.domain.model;

/**
 * Enum para padronizar as entidades que podem ser auditadas no sistema.
 * Facilita categorização e filtros de logs por tipo de entidade.
 */
public enum EntidadeAuditoria {
    PACIENTE("Paciente"),
    ATENDIMENTO("Atendimento"),
    PRONTUARIO("Prontuário"),
    ANEXO("Anexo/Exame"),
    EXAME("Exame"),
    PDF("PDF/Documento"),
    RECEITA("Receita Médica"),
    MANEJO("Manejo/Orientação"),
    PROTOCOLO("Protocolo"),
    PROTOCOLO_INSTITUCIONAL("Protocolo Institucional"),
    PROTOCOLO_PROPRIO("Protocolo Próprio"),
    USUARIO("Usuário"),
    CLINICA("Clínica"),
    VACINACAO("Vacinação"),
    APLICACAO_VACINA("Aplicação de Vacina");

    private final String descricao;

    EntidadeAuditoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}


```

### Perfil.java

```java
// src\main\java\br\com\vestris\user\domain\model\Perfil.java
package br.com.vestris.user.domain.model;

public enum Perfil {
    ADMIN_GLOBAL,   // Dono do SaaS
    ADMIN_GESTOR,   // Gestor da Clínica (Não vê dados clínicos)
    ADMIN_CLINICO,  // Dono Técnico (Vê tudo + alterna modo)
    VETERINARIO,    // Funcionário (Vê pacientes da clínica + seus protocolos)
    ESTUDANTE
}

```

### Usuario.java

```java
// src\main\java\br\com\vestris\user\domain\model\Usuario.java
package br.com.vestris.user.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuarios", schema = "users_schema")
public class Usuario extends EntidadeBase {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String crmv;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    // --- NOVO VÍNCULO ---
    @ManyToOne
    @JoinColumn(name = "clinica_id")
    private Clinica clinica;

    @Enumerated(EnumType.STRING)
    private UserScope scope; // GLOBAL ou TENANT

    public enum UserScope {
        GLOBAL, // Você (Vestris)
        TENANT  // Clientes
    }

}

```

---

## src\main\java\br\com\vestris\user\domain\repository

### RepositorioAuditoria.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioAuditoria.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAuditoria extends JpaRepository<Auditoria, UUID> {
    // Método Mágico do Spring Data JPA (Funciona perfeitamente com Between)
    // O Delegate se encarregará de garantir que 'inicio' e 'fim' nunca sejam nulos
    List<Auditoria> findByClinicaIdAndDataHoraBetweenOrderByDataHoraDesc(
            UUID clinicaId,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    // Método para quando não houver filtro de data (busca tudo da clínica)
    List<Auditoria> findByClinicaIdOrderByDataHoraDesc(UUID clinicaId);
}

```

### RepositorioClinica.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioClinica.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioClinica extends JpaRepository<Clinica, UUID> {
}

```

### RepositorioUsuario.java

```java
// src\main\java\br\com\vestris\user\domain\repository\RepositorioUsuario.java
package br.com.vestris.user.domain.repository;

import br.com.vestris.user.domain.model.Perfil;
import br.com.vestris.user.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByPerfil(Perfil perfil);

    // Busca quem tem CRMV preenchido
    @Query("SELECT u FROM Usuario u WHERE u.crmv IS NOT NULL AND u.crmv <> ''")
    List<Usuario> findAllComCrmv();

    // Busca por Clínica (para listar equipe)
    List<Usuario> findByClinicaId(UUID clinicaId);

    // --- NOVO: CONTAGEM PARA VALIDAÇÃO DE PLANO ---
    long countByClinicaId(UUID clinicaId);;

}

```

---

## src\main\java\br\com\vestris\user\infrastructure\config

### ConfiguracaoSeguranca.java

```java
// src\main\java\br\com\vestris\user\infrastructure\config\ConfiguracaoSeguranca.java
package br.com.vestris.user.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfiguracaoSeguranca {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

```

---

## src\main\java\br\com\vestris\user\infrastructure\security

### ServicoToken.java

```java
// src\main\java\br\com\vestris\user\infrastructure\security\ServicoToken.java
package br.com.vestris.user.infrastructure.security;

import br.com.vestris.user.domain.model.Usuario;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class ServicoToken {
    // Em produção, use application.properties. Para MVP, chave fixa.
    private static final Key CHAVE_SECRETA = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRACAO_MS = 86400000; // 24h

    public String gerarToken(Usuario usuario) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + EXPIRACAO_MS);

        JwtBuilder builder = Jwts.builder()
                .setSubject(usuario.getId().toString())
                .claim("email", usuario.getEmail())
                .claim("perfil", usuario.getPerfil().name())
                .claim("scope", usuario.getScope() != null ? usuario.getScope().name() : "TENANT");

        // --- CORREÇÃO: EMBUTIR A CLÍNICA NO TOKEN PARA O FRONTEND VER ---
        if (usuario.getClinica() != null) {
            builder.claim("clinicaId", usuario.getClinica().getId().toString());
        }

        return builder
                .setIssuedAt(agora)
                .setExpiration(dataExpiracao)
                .signWith(CHAVE_SECRETA)
                .compact();
    }
}

```

---

## src\main\java\br\com\vestris\user\interfaces\delegate

### ApiDelegateAdminGlobal.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAdminGlobal.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuth;
import br.com.vestris.user.interfaces.api.AdminGlobalApiDelegate;
import br.com.vestris.user.interfaces.dto.ApiResponseToken;
import br.com.vestris.user.interfaces.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAdminGlobal implements AdminGlobalApiDelegate {

    private final ServiceAuth servicoAuth;

    @Override
    public ResponseEntity<ApiResponseToken> impersonateUser(UUID adminId, UUID targetUserId) {
        System.out.println(">>> DELEGATE ADMIN ALCANÇADO! <<<"); // Log de prova de vida

        String token = servicoAuth.impersonate(adminId, targetUserId);

        TokenResponse dto = new TokenResponse();
        dto.setToken(token);
        dto.setTipo("Bearer");
        dto.setExpiraEm("Impersonated Session");

        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setDados(dto);

        return ResponseEntity.ok(response);
    }
}

```

### ApiDelegateAuditoria.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAuditoria.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuditoria;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.AcaoAuditoria;
import br.com.vestris.user.domain.model.Auditoria;
import br.com.vestris.user.domain.model.EntidadeAuditoria;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.domain.repository.RepositorioAuditoria;
import br.com.vestris.user.interfaces.api.AuditoriaApiDelegate;
import br.com.vestris.user.interfaces.dto.ApiResponseListaAuditoria;
import br.com.vestris.user.interfaces.dto.ApiResponseSucesso;
import br.com.vestris.user.interfaces.dto.AuditoriaResponse;
import br.com.vestris.user.interfaces.dto.EventoAuditoriaRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAuditoria implements AuditoriaApiDelegate {
    private final RepositorioAuditoria repositorio;
    private final ServiceAuditoria serviceAuditoria;
    private final ServiceUsuario serviceUsuario;
    private final HttpServletRequest httpRequest;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseEntity<ApiResponseListaAuditoria> listarLogsAuditoria(UUID clinicaId, LocalDate dataInicio, LocalDate dataFim) {

        List<Auditoria> logs;

        // ESTRATÉGIA: Se o usuário filtrar data, usamos o BETWEEN.
        // Se não filtrar, usamos o método simples.

        if (dataInicio != null || dataFim != null) {
            // Se início for nulo, pega desde o começo dos tempos
            LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : LocalDateTime.of(1970, 1, 1, 0, 0);

            // Se fim for nulo, pega até o final dos tempos (hoje + folga)
            LocalDateTime fim = (dataFim != null) ? dataFim.atTime(LocalTime.MAX) : LocalDateTime.now().plusDays(1);

            logs = repositorio.findByClinicaIdAndDataHoraBetweenOrderByDataHoraDesc(clinicaId, inicio, fim);
        } else {
            // Sem filtros: traz tudo
            logs = repositorio.findByClinicaIdOrderByDataHoraDesc(clinicaId);
        }

        List<AuditoriaResponse> listaDTO = logs.stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAuditoria response = new ApiResponseListaAuditoria();
        response.setSucesso(true);
        response.setDados(listaDTO);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseSucesso> registrarEventoAuditoria(EventoAuditoriaRequest request) {
        try {
            UUID usuarioId = extrairUsuarioIdDoToken();

            if (usuarioId == null) {
                ApiResponseSucesso erro = new ApiResponseSucesso();
                erro.setSucesso(false);
                erro.setMensagem("Token inválido ou expirado.");
                return ResponseEntity.badRequest().body(erro);
            }

            Usuario usuario = serviceUsuario.buscarPorId(usuarioId);
            if (usuario.getClinica() == null) {
                ApiResponseSucesso r = new ApiResponseSucesso();
                r.setSucesso(true);
                r.setMensagem("Usuário sem clínica.");
                return ResponseEntity.ok(r);
            }

            AcaoAuditoria acao = AcaoAuditoria.fromString(request.getAcao().toString());
            EntidadeAuditoria entidade = EntidadeAuditoria.PDF;
            try {
                entidade = EntidadeAuditoria.valueOf(request.getEntidade());
            } catch (Exception e) {}

            serviceAuditoria.registrar(
                    usuario.getClinica().getId(),
                    usuario.getId(),
                    acao,
                    entidade,
                    request.getIdAlvo(),
                    request.getDescricao()
            );

            ApiResponseSucesso response = new ApiResponseSucesso();
            response.setSucesso(true);
            response.setMensagem("Evento registrado.");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            ApiResponseSucesso erro = new ApiResponseSucesso();
            erro.setSucesso(false);
            erro.setMensagem("Erro interno: " + e.getMessage());
            return ResponseEntity.internalServerError().body(erro);
        }
    }

    private UUID extrairUsuarioIdDoToken() {
        try {
            String header = httpRequest.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) return null;
            String token = header.substring(7);
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            JsonNode node = objectMapper.readTree(payloadJson);
            if (node.has("sub")) return UUID.fromString(node.get("sub").asText());
        } catch (Exception e) {}
        return null;
    }

    private AuditoriaResponse converter(Auditoria log) {
        AuditoriaResponse dto = new AuditoriaResponse();
        dto.setId(log.getId());
        dto.setClinicaId(log.getClinicaId());
        dto.setUsuarioId(log.getUsuarioId());
        dto.setAcao(log.getAcao() != null ? log.getAcao().name() : "DESCONHECIDO");
        dto.setEntidade(log.getEntidade() != null ? log.getEntidade().name() : "DESCONHECIDO");
        dto.setIdAlvo(log.getIdAlvo());
        dto.setDetalhes(log.getDescricaoCurta());
        dto.setMetadados(log.getMetadados());
        if (log.getDataHora() != null) {
            // Enviamos o LocalDateTime com Offset UTC para o front ajustar
            dto.setCriadoEm(log.getDataHora().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegateAuth.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateAuth.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceAuth;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.AdminGlobalApiDelegate;
import br.com.vestris.user.interfaces.api.AutenticacaoApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApiDelegateAuth implements AutenticacaoApiDelegate{

    private final ServiceAuth servicoAuth;

    @Override
    public ResponseEntity<ApiResponseUsuario> registrarUsuario(RegistroRequest request) {
        Usuario salvo = servicoAuth.registrar(
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv()
        );
        return ResponseEntity.ok(createResponse(salvo));
    }

    @Override
    public ResponseEntity<ApiResponseToken> login(LoginRequest request) {
        String tokenJwt = servicoAuth.login(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(createTokenResponse(tokenJwt));
    }

    // Helpers
    private ApiResponseUsuario createResponse(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());

        // CORREÇÃO: Usando o Enum externo gerado
        if (u.getPerfil() != null) {
            dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
        }

        if (u.getScope() != null) {
            dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
        }

        if (u.getCrmv() != null) {
            dto.setCrmv(u.getCrmv());
        }

        ApiResponseUsuario response = new ApiResponseUsuario();
        response.setSucesso(true);
        response.setDados(dto);
        return response;
    }

    private ApiResponseToken createTokenResponse(String token) {
        TokenResponse dto = new TokenResponse();
        dto.setToken(token);
        dto.setTipo("Bearer");
        dto.setExpiraEm("24h");

        ApiResponseToken response = new ApiResponseToken();
        response.setSucesso(true);
        response.setDados(dto);
        return response;
    }
}

```

### ApiDelegateClinica.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateClinica.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceClinica;
import br.com.vestris.user.domain.model.Clinica;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.ClinicaApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateClinica implements ClinicaApiDelegate {
    private final ServiceClinica servico;

    @Override
    public ResponseEntity<ApiResponseClinica> obterMinhaClinica(UUID usuarioId) {
        Clinica c = servico.buscarPorUsuario(usuarioId);
        if (c == null) {
            ApiResponseClinica r = new ApiResponseClinica();
            r.setSucesso(true);
            return ResponseEntity.ok(r);
        }
        return ResponseEntity.ok(criarResponse(c));
    }

    @Override
    public ResponseEntity<ApiResponseClinica> salvarMinhaClinica(UUID usuarioId, ClinicaRequest request) {
        Clinica entidade = new Clinica();
        entidade.setNomeFantasia(request.getNomeFantasia());
        entidade.setRazaoSocial(request.getRazaoSocial());
        entidade.setCnpj(request.getCnpj());
        entidade.setEndereco(request.getEndereco());
        entidade.setTelefone(request.getTelefone());
        entidade.setEmailContato(request.getEmailContato());
        entidade.setLogoBase64(request.getLogoBase64());

        Clinica salva = servico.salvar(usuarioId, entidade);
        return ResponseEntity.ok(criarResponse(salva));
    }

    private ApiResponseClinica criarResponse(Clinica c) {
        ClinicaResponse dto = new ClinicaResponse();
        dto.setId(c.getId());
        dto.setNomeFantasia(c.getNomeFantasia());
        dto.setRazaoSocial(c.getRazaoSocial());
        dto.setCnpj(c.getCnpj());
        dto.setEndereco(c.getEndereco());
        dto.setTelefone(c.getTelefone());
        dto.setEmailContato(c.getEmailContato());
        dto.setLogoBase64(c.getLogoBase64());

        ApiResponseClinica r = new ApiResponseClinica();
        r.setSucesso(true);
        r.setDados(dto);
        return r;
    }

    @Override
    public ResponseEntity<ApiResponseListaUsuario> listarEquipe(UUID usuarioId) {
        List<UsuarioResponse> lista = servico.listarMembros(usuarioId).stream()
                .map(this::converterUsuario)
                .collect(Collectors.toList());

        ApiResponseListaUsuario r = new ApiResponseListaUsuario();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> adicionarMembroEquipe(UUID usuarioId, NovoMembroRequest request) {
        Usuario novo = servico.adicionarMembro(
                usuarioId,
                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getCrmv()
        );

        ApiResponseUsuario r = new ApiResponseUsuario();
        r.setSucesso(true);
        r.setDados(converterUsuario(novo));
        return ResponseEntity.status(201).body(r);
    }

    // Helper
    private UsuarioResponse converterUsuario(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setCrmv(u.getCrmv());

        // CORREÇÃO: Usando o Enum externo gerado
        if (u.getPerfil() != null) {
            dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
        }
        // Scope não é obrigatório mostrar na listagem de equipe, mas se quiser:
        if (u.getScope() != null) {
            dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
        }

        return dto;
    }

    @Override
    public ResponseEntity<Void> removerMembroEquipe(UUID usuarioId, UUID membroId) {
        servico.removerMembro(usuarioId, membroId);
        return ResponseEntity.noContent().build();
    }
}

```

### ApiDelegateUsuarios.java

```java
// src\main\java\br\com\vestris\user\interfaces\delegate\ApiDelegateUsuarios.java
package br.com.vestris.user.interfaces.delegate;

import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.Usuario;
import br.com.vestris.user.interfaces.api.GestaoUsuariosApiDelegate;
import br.com.vestris.user.interfaces.dto.*;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateUsuarios implements GestaoUsuariosApiDelegate {
    private final ServiceUsuario servico;

    @Override
    public ResponseEntity<ApiResponseListaUsuario> listarUsuarios(String perfil, Boolean apenasComCrmv) {
        // O ServiceUsuario.listar espera uma String ou um Enum interno do domínio.
        // Vamos passar as strings e deixar o service resolver ou converter aqui se necessário.

        List<UsuarioResponse> lista = servico.listar(perfil, apenasComCrmv).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaUsuario response = new ApiResponseListaUsuario();
        response.setSucesso(true);
        response.setDados(lista);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> buscarUsuarioPorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ApiResponseUsuario> atualizarUsuario(UUID id, AtualizacaoUsuarioRequest request) {
        Usuario atualizado = servico.atualizar(id, request.getNome(), request.getCrmv());
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    @Override
    public ResponseEntity<Void> deletarUsuario(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- Helpers ---
    private ApiResponseUsuario criarResponse(Usuario u) {
        ApiResponseUsuario r = new ApiResponseUsuario();
        r.setSucesso(true);
        r.setDados(converter(u));
        return r;
    }

    private UsuarioResponse converter(Usuario u) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(u.getId());
        dto.setNome(u.getNome());
        dto.setEmail(u.getEmail());
        dto.setCrmv(u.getCrmv());

        // Conversão segura dos Enums do Domínio para os Enums do DTO
        if (u.getPerfil() != null) {
            try {
                // Tenta converter o nome do enum do domínio (ex: ADMIN_GLOBAL) para o DTO
                dto.setPerfil(UsuarioResponsePerfilEnum.valueOf(u.getPerfil().name()));
            } catch (Exception e) {
                // Fallback seguro caso o banco tenha algo estranho
                dto.setPerfil(UsuarioResponsePerfilEnum.VETERINARIO);
            }
        }

        if (u.getScope() != null) {
            try {
                dto.setScope(UsuarioResponseScopeEnum.valueOf(u.getScope().name()));
            } catch (Exception e) {
                dto.setScope(UsuarioResponseScopeEnum.TENANT);
            }
        }

        return dto;
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Usuários
  description: Autenticação e Gestão de Contas
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  /api/v1/auth/registro:
    $ref: "./paths/registro.yml"
  /api/v1/auth/login:
    $ref: "./paths/login.yml"
  /api/v1/usuarios:
    $ref: './paths/usuarios.yml#/usuarios_colecao'
  /api/v1/usuarios/{id}:
    $ref: './paths/usuarios.yml#/usuarios_item'
  /api/v1/minha-clinica:
    $ref: './paths/clinica.yml#/clinica_geral'
  /api/v1/minha-clinica/equipe:
    $ref: './paths/clinica.yml#/clinica_equipe'
  /api/v1/admin/impersonate:
    $ref: './paths/admin.yml#/impersonate'

  # NOVO
  /api/v1/auditoria:
    $ref: './paths/auditoria.yml#/auditoria_colecao'
  /api/v1/auditoria/evento:
    $ref: './paths/auditoria.yml#/auditoria_evento'

components:
  schemas:
    RegistroRequest:
      $ref: "./components/schemas.yml#/RegistroRequest"
    LoginRequest:
      $ref: "./components/schemas.yml#/LoginRequest"
    UsuarioResponse:
      $ref: "./components/schemas.yml#/UsuarioResponse"
    TokenResponse:
      $ref: "./components/schemas.yml#/TokenResponse"
    ApiResponseUsuario:
      $ref: "./components/schemas.yml#/ApiResponseUsuario"
    ApiResponseToken:
      $ref: "./components/schemas.yml#/ApiResponseToken"

    # NOVOS E ATUALIZADOS
    EventoAuditoriaRequest:
      $ref: "./components/schemas.yml#/EventoAuditoriaRequest"
    AuditoriaResponse:
      $ref: "./components/schemas.yml#/AuditoriaResponse"
    ApiResponseListaAuditoria:
      $ref: "./components/schemas.yml#/ApiResponseListaAuditoria"
    UsuarioResponsePerfilEnum:
      $ref: "./components/schemas.yml#/UsuarioResponsePerfilEnum"

```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
# vestris-user/src/main/resources/swagger/components/schemas.yml

# --- ENUMS ---
UsuarioResponsePerfilEnum:
  type: string
  enum: [ADMIN_GLOBAL, ADMIN_GESTOR, ADMIN_CLINICO, VETERINARIO, ESTUDANTE]

UsuarioResponseScopeEnum:
  type: string
  enum: [ GLOBAL, TENANT ]

# --- AUTH & USER ---
RegistroRequest:
  type: object
  required: [nome, email, senha]
  properties:
    nome: { type: string }
    email: { type: string, format: email }
    senha: { type: string, format: password }
    crmv: { type: string }

LoginRequest:
  type: object
  required: [email, senha]
  properties:
    email: { type: string }
    senha: { type: string }

UsuarioResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nome: { type: string }
    email: { type: string }
    perfil: { $ref: '#/UsuarioResponsePerfilEnum' }
    crmv: { type: string }
    scope: { $ref: '#/UsuarioResponseScopeEnum' }

TokenResponse:
  type: object
  properties:
    token: { type: string }
    tipo: { type: string, example: "Bearer" }
    expiraEm: { type: string }

AtualizacaoUsuarioRequest:
  type: object
  properties:
    nome: { type: string }
    crmv: { type: string }

# --- CLÍNICA ---
ClinicaRequest:
  type: object
  required: [ nomeFantasia ]
  properties:
    nomeFantasia: { type: string }
    razaoSocial: { type: string }
    cnpj: { type: string }
    endereco: { type: string }
    telefone: { type: string }
    emailContato: { type: string }
    logoBase64: { type: string }

ClinicaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    nomeFantasia: { type: string }
    razaoSocial: { type: string }
    cnpj: { type: string }
    endereco: { type: string }
    telefone: { type: string }
    emailContato: { type: string }
    logoBase64: { type: string }

NovoMembroRequest:
  type: object
  required: [ nome, email, senha, crmv ]
  properties:
    nome: { type: string }
    email: { type: string, format: email }
    senha: { type: string }
    crmv: { type: string }

# Enums para o Frontend saber o que mandar
AcaoAuditoriaEnum:
  type: string
  enum:
    - PDF_RECEITA_GERADO
    - PDF_MANEJO_GERADO
    - PDF_PRONTUARIO_GERADO
    - VISUALIZOU_PRONTUARIO

EventoAuditoriaRequest:
  type: object
  required: [ acao, entidade, idAlvo, descricao ]
  properties:
    acao: { $ref: '#/AcaoAuditoriaEnum' }
    entidade: { type: string, example: "PDF" }
    idAlvo: { type: string, format: uuid }
    descricao: { type: string }
    metadados: { type: string, description: "JSON stringificado opcional" }

AuditoriaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    clinicaId: { type: string, format: uuid }
    usuarioId: { type: string, format: uuid }
    acao: { type: string }
    entidade: { type: string }
    idAlvo: { type: string, format: uuid }
    detalhes: { type: string }
    metadados: { type: string } # Adicionado
    criadoEm: { type: string, format: date-time }

# --- WRAPPERS ---
ApiResponseUsuario:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/UsuarioResponse' }

ApiResponseToken:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/TokenResponse' }

ApiResponseListaUsuario:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/UsuarioResponse' } }

ApiResponseClinica:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/ClinicaResponse' }

ApiResponseListaAuditoria: # NOVO
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/AuditoriaResponse' } }

ApiResponseSucesso:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem: { type: string }
```

---

## src\main\resources\swagger\paths

### admin.yml

```yaml
# src\main\resources\swagger\paths\admin.yml
# /api/v1/admin/impersonate
impersonate:
  post:
    tags: [AdminGlobal]
    summary: Gerar token de acesso para qualquer usuário
    operationId: impersonateUser
    parameters:
      - name: adminId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: targetUserId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Token gerado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseToken' }
```

### auditoria.yml

```yaml
# src\main\resources\swagger\paths\auditoria.yml
# ROTA: /api/v1/auditoria (Listagem)
auditoria_colecao:
  get:
    tags: [Auditoria]
    summary: Listar logs de auditoria da clínica
    operationId: listarLogsAuditoria
    parameters:
      - name: clinicaId
        in: query
        required: true
        schema: { type: string, format: uuid }
      - name: dataInicio
        in: query
        schema: { type: string, format: date }
      - name: dataFim
        in: query
        schema: { type: string, format: date }
    responses:
      '200':
        description: Lista de eventos
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAuditoria' }

# NOVA ROTA: /api/v1/auditoria/evento (Registro manual do Front)
auditoria_evento:
  post:
    tags: [Auditoria]
    summary: Registrar evento de frontend
    operationId: registrarEventoAuditoria
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/EventoAuditoriaRequest' }
    responses:
      '200':
        description: Evento registrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseSucesso' }
```

### clinica.yml

```yaml
# src\main\resources\swagger\paths\clinica.yml
clinica_geral:
  get:
    tags: [Clinica]
    summary: Obter dados da minha clínica
    operationId: obterMinhaClinica
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseClinica' }

  post:
    tags: [Clinica]
    summary: Criar ou Atualizar minha clínica
    operationId: salvarMinhaClinica
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/ClinicaRequest' }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseClinica' }

clinica_equipe:
  get:
    tags: [ Clinica ]
    summary: Listar veterinários da minha clínica
    operationId: listarEquipe
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaUsuario' }

  post:
    tags: [ Clinica ]
    summary: Adicionar veterinário à equipe
    operationId: adicionarMembroEquipe
    parameters:
      - name: usuarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/NovoMembroRequest' }
    responses:
      '201':
        description: Membro criado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  delete:
    tags: [ Clinica ]
    summary: Remover veterinário da equipe
    description: "Desvincula o veterinário da clínica. Ele perde acesso aos dados, mas o histórico é mantido."
    operationId: removerMembroEquipe
    parameters:
      - name: usuarioId
        in: query
        description: "ID do Admin que está removendo"
        required: true
        schema: { type: string, format: uuid }
      - name: membroId
        in: query
        description: "ID do Veterinário a ser removido"
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204':
        description: Removido com sucesso

```

### login.yml

```yaml
# src\main\resources\swagger\paths\login.yml
post:
  tags:
    - Autenticacao
  summary: Fazer login e obter Token
  operationId: login
  requestBody:
    content:
      application/json:
        schema:
          $ref: '../components/schemas.yml#/LoginRequest'
  responses:
    '200':
      description: Login com sucesso
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ApiResponseToken'
```

### registro.yml

```yaml
# src\main\resources\swagger\paths\registro.yml
post:
  tags:
    - Autenticacao
  summary: Criar nova conta
  operationId: registrarUsuario
  requestBody:
    content:
      application/json:
        schema:
          $ref: '../components/schemas.yml#/RegistroRequest'
  responses:
    '200':
      description: Conta criada
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ApiResponseUsuario'
```

### usuarios.yml

```yaml
# src\main\resources\swagger\paths\usuarios.yml
# vestris-user/src/main/resources/swagger/paths/usuarios.yml
# Rota: /api/v1/usuarios
usuarios_colecao:
  get:
    tags: [GestaoUsuarios]
    summary: Listar usuários com filtros
    operationId: listarUsuarios
    parameters:
      - name: perfil
        in: query
        description: "Filtrar por perfil"
        required: false
        schema:
          type: string
          enum: [ADMIN_GLOBAL, ADMIN_GESTOR, ADMIN_CLINICO, VETERINARIO, ESTUDANTE]
      - name: apenasComCrmv
        in: query
        description: "Se true, traz apenas quem tem CRMV preenchido"
        required: false
        schema:
          type: boolean
    responses:
      '200':
        description: Lista recuperada
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaUsuario'

# Rota: /api/v1/usuarios/{id}
usuarios_item:
  parameters:
    - name: id
      in: path
      required: true
      schema: { type: string, format: uuid }
  get:
    tags: [GestaoUsuarios]
    summary: Buscar usuário por ID
    operationId: buscarUsuarioPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  put:
    tags: [GestaoUsuarios]
    summary: Atualizar dados cadastrais
    description: "Atualiza nome e CRMV. Não atualiza senha/email por aqui."
    operationId: atualizarUsuario
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AtualizacaoUsuarioRequest' }
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseUsuario' }
  delete:
    tags: [GestaoUsuarios]
    summary: Remover usuário
    operationId: deletarUsuario
    responses:
      '204': { description: Removido }
```


---

# Projeto: vestris-vaccination

## .

### pom.xml

```xml
<!-- pom.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>br.com.vestris</groupId>
        <artifactId>vestris-backend</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <artifactId>vestris-vaccination</artifactId>

    <dependencies>
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-shared</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Para validar a Espécie -->
        <dependency>
            <groupId>br.com.vestris</groupId> <!-- ou br.com.vestris -->
            <artifactId>vestris-species</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Para validar a Fonte/Referência (CRÍTICO) -->
        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-reference</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>br.com.vestris</groupId>
            <artifactId>vestris-medical-record</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <!-- Swagger e Web -->
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>io.swagger.core.v3</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.19</version>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.openapitools</groupId>
            <artifactId>jackson-databind-nullable</artifactId>
            <version>0.2.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.openapitools</groupId>
                <artifactId>openapi-generator-maven-plugin</artifactId>
                <version>7.1.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <inputSpec>${project.basedir}/src/main/resources/swagger/openapi.yml</inputSpec>
                            <generatorName>spring</generatorName>
                            <output>${project.build.directory}/generated-sources/openapi</output>
                            <apiPackage>br.com.vestris.vaccination.interfaces.api</apiPackage>
                            <modelPackage>br.com.vestris.vaccination.interfaces.dto</modelPackage>

                            <!-- Configurações para evitar erros de teste -->
                            <generateApiTests>false</generateApiTests>
                            <generateModelTests>false</generateModelTests>
                            <generateSupportingFiles>true</generateSupportingFiles>
                            <supportingFilesToGenerate>ApiUtil.java</supportingFilesToGenerate>

                            <configOptions>
                                <delegatePattern>true</delegatePattern>
                                <interfaceOnly>false</interfaceOnly>
                                <useSpringBoot3>true</useSpringBoot3>
                                <useTags>true</useTags>
                            </configOptions>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## src\main\java\br\com\vestris\vaccination\application

### ServiceAplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\application\ServiceAplicacaoVacina.java
package br.com.vestris.vaccination.application;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.domain.repository.RepositorioPaciente;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import br.com.vestris.vaccination.domain.repository.RepositorioAplicacaoVacina;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceAplicacaoVacina {
    private final RepositorioAplicacaoVacina repositorio;
    private final RepositorioPaciente repoPaciente;
    private final ServiceVacinacao serviceVacinacao; // Valida se vacina existe

    public AplicacaoVacina registrar(AplicacaoVacina app, UUID pacienteId) {
        Paciente paciente = repoPaciente.findById(pacienteId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Paciente", pacienteId.toString()));

        // Valida Vacina no outro módulo
        if (!serviceVacinacao.existePorId(app.getVacinaId())) { // Precisa criar este método no ServiceVacinacao se não tiver
            throw new ExcecaoRegraNegocio("Vacina informada não existe no catálogo.");
        }

        app.setPaciente(paciente);
        return repositorio.save(app);
    }

    public List<AplicacaoVacina> listarPorPaciente(UUID pacienteId) {
        return repositorio.findByPacienteIdOrderByDataAplicacaoDesc(pacienteId);
    }

    public void deletar(UUID id) {
        repositorio.deleteById(id);
    }
}

```

### ServiceProtocoloVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\application\ServiceProtocoloVacinacao.java
package br.com.vestris.vaccination.application;

import br.com.vestris.reference.application.ServiceReferencia;
import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.domain.repository.RepositorioProtocoloVacinacao;
import br.com.vestris.vaccination.domain.repository.RepositorioVacina;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceProtocoloVacinacao {
    private final RepositorioProtocoloVacinacao repoProtocolo;
    private final RepositorioVacina repoVacina;

    // Serviços Externos
    private final ServiceEspecie serviceEspecie;
    private final ServiceReferencia serviceReferencia;

    public ProtocoloVacinacao criar(ProtocoloVacinacao novo, UUID vacinaId) {
        // 1. Validar Vacina (Interno)
        Vacina vacina = repoVacina.findById(vacinaId)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", vacinaId.toString()));

        // 2. Validar Espécie (Externo)
        if (!serviceEspecie.existePorId(novo.getEspecieId())) {
            throw new ExcecaoRegraNegocio("Espécie não encontrada.");
        }

        // 3. Validar Referência (Externo - OBRIGATÓRIO)
        if (!serviceReferencia.existePorId(novo.getReferenciaId())) {
            throw new ExcecaoRegraNegocio("Referência Bibliográfica/Legal é obrigatória e não foi encontrada. O sistema exige respaldo científico para vacinação.");
        }

        // 4. Validar Duplicidade
        if (repoProtocolo.existsByEspecieIdAndVacinaId(novo.getEspecieId(), vacinaId)) {
            throw new ExcecaoRegraNegocio("Esta vacina já consta no protocolo desta espécie.");
        }

        novo.setVacina(vacina);
        return repoProtocolo.save(novo);
    }

    public List<ProtocoloVacinacao> listarPorEspecie(UUID especieId) {
        // 1. Validar se a espécie existe antes de buscar
        if (!serviceEspecie.existePorId(especieId)) {
            throw new ExceptionRecursoNaoEncontrado("Espécie", especieId.toString());
        }

        List<ProtocoloVacinacao> protocolos = repoProtocolo.findByEspecieId(especieId);

        // 2. REGRA DE NEGÓCIO: Mensagem clara se não houver vacinas
        if (protocolos.isEmpty()) {
            throw new ExcecaoRegraNegocio(
                    "Não constam vacinas obrigatórias ou recomendadas para esta espécie na base de dados atual. " +
                            "Verifique a legislação local e sanitária."
            );
        }

        return protocolos;
    }

    public ProtocoloVacinacao buscarPorId(UUID id) {
        return repoProtocolo.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Protocolo Vacinal", id.toString()));
    }

    public ProtocoloVacinacao atualizar(UUID id, UUID especieId, UUID vacinaId, UUID refId,
                                        Integer idade, Integer reforco, boolean obrig, String obs) {

        ProtocoloVacinacao existente = buscarPorId(id);

        // Se mudar Espécie, valida
        if (!existente.getEspecieId().equals(especieId)) {
            if (!serviceEspecie.existePorId(especieId)) {
                throw new ExcecaoRegraNegocio("Nova espécie não encontrada.");
            }
            existente.setEspecieId(especieId);
        }

        // Se mudar Vacina, valida e checa duplicidade
        if (!existente.getVacina().getId().equals(vacinaId)) {
            // Valida Vacina
            Vacina novaVacina = repoVacina.findById(vacinaId)
                    .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", vacinaId.toString()));

            // Checa se já não existe essa vacina para essa espécie (evitar duplicidade)
            if (repoProtocolo.existsByEspecieIdAndVacinaId(especieId, vacinaId)) {
                throw new ExcecaoRegraNegocio("Esta vacina já existe no protocolo desta espécie.");
            }
            existente.setVacina(novaVacina);
        }

        // Se mudar Referência, valida
        if (!existente.getReferenciaId().equals(refId)) {
            if (!serviceReferencia.existePorId(refId)) {
                throw new ExcecaoRegraNegocio("Referência bibliográfica inválida.");
            }
            existente.setReferenciaId(refId);
        }

        existente.setIdadeMinimaDias(idade);
        existente.setDiasParaReforco(reforco);
        existente.setObrigatoria(obrig);
        existente.setObservacoes(obs);

        return repoProtocolo.save(existente);
    }

    public void deletar(UUID id) {
        if (!repoProtocolo.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Protocolo Vacinal", id.toString());
        }
        repoProtocolo.deleteById(id);
    }
}

```

### ServiceVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\application\ServiceVacinacao.java
package br.com.vestris.vaccination.application;

import br.com.vestris.shared.domain.exceptions.ExcecaoRegraNegocio;
import br.com.vestris.shared.domain.exceptions.ExceptionRecursoNaoEncontrado;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.domain.repository.RepositorioVacina;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceVacinacao {
    private final RepositorioVacina repositorio;

    public Vacina criar(Vacina novaVacina) {
        if (repositorio.existsByNome(novaVacina.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe uma vacina cadastrada com este nome: " + novaVacina.getNome());
        }
        return repositorio.save(novaVacina);
    }

    public List<Vacina> listarTodas() {
        return repositorio.findAll();
    }

    public Vacina buscarPorId(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExceptionRecursoNaoEncontrado("Vacina", id.toString()));
    }

    public Vacina atualizar(UUID id, Vacina dados) {
        Vacina existente = buscarPorId(id);

        // Verifica duplicidade de nome apenas se o nome mudou
        if (!existente.getNome().equalsIgnoreCase(dados.getNome()) && repositorio.existsByNome(dados.getNome())) {
            throw new ExcecaoRegraNegocio("Já existe outra vacina com este nome.");
        }

        existente.setNome(dados.getNome());
        existente.setFabricante(dados.getFabricante());
        existente.setTipoVacina(dados.getTipoVacina());
        existente.setDescricao(dados.getDescricao());
        existente.setDoencaAlvo(dados.getDoencaAlvo());

        return repositorio.save(existente);
    }

    public boolean existePorId(UUID id) {
        return repositorio.existsById(id);
    }

    // Adicione também para buscar o nome (para o DTO)
    public String buscarNomePorId(UUID id) {
        return repositorio.findById(id).map(v -> v.getNome()).orElse("Vacina Desconhecida");
    }

    public void deletar(UUID id) {
        if (!repositorio.existsById(id)) {
            throw new ExceptionRecursoNaoEncontrado("Vacina", id.toString());
        }
        try {
            repositorio.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcecaoRegraNegocio("Não é possível remover esta vacina pois ela está sendo usada em protocolos vacinais.");
        }
    }
}

```

---

## src\main\java\br\com\vestris\vaccination\domain\model

### AplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\model\AplicacaoVacina.java
package br.com.vestris.vaccination.domain.model;

import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "aplicacoes_vacinas", schema = "medical_record_schema")
public class AplicacaoVacina extends EntidadeBase {
    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private UUID vacinaId; // Referência ao módulo vaccination

    @Column(nullable = false)
    private LocalDate dataAplicacao;

    private LocalDate dataProximaDose;

    private String lote;

    private UUID veterinarioId; // Quem aplicou

    @Column(columnDefinition = "TEXT")
    private String observacoes;

}

```

### ProtocoloVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\domain\model\ProtocoloVacinacao.java
package br.com.vestris.vaccination.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "protocolos_vacinacao", schema = "vaccination_schema")
public class ProtocoloVacinacao extends EntidadeBase {
    @Column(nullable = false)
    private UUID especieId; // FK Lógica

    @ManyToOne(optional = false)
    @JoinColumn(name = "vacina_id", nullable = false)
    private Vacina vacina; // FK Real (Mesmo módulo)

    @Column(nullable = false)
    private UUID referenciaId; // FK Lógica (CRUCIAL para respaldo legal)

    private Integer idadeMinimaDias;

    private Integer diasParaReforco;

    @Column(nullable = false)
    private boolean obrigatoria;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

```

### Vacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\model\Vacina.java
package br.com.vestris.vaccination.domain.model;

import br.com.vestris.shared.domain.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vacinas", schema = "vaccination_schema")
public class Vacina extends EntidadeBase {
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(length = 100)
    private String fabricante;

    @Column(length = 100)
    private String tipoVacina; // Ex: "Vírus Vivo", "Inativada"

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 150)
    private String doencaAlvo; // Ex: "Doença de Newcastle"

}

```

---

## src\main\java\br\com\vestris\vaccination\domain\repository

### RepositorioAplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioAplicacaoVacina.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioAplicacaoVacina extends JpaRepository<AplicacaoVacina, UUID> {
    List<AplicacaoVacina> findByPacienteIdOrderByDataAplicacaoDesc(UUID pacienteId);
}

```

### RepositorioProtocoloVacinacao.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioProtocoloVacinacao.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RepositorioProtocoloVacinacao extends JpaRepository<ProtocoloVacinacao, UUID> {
    List<ProtocoloVacinacao> findByEspecieId(UUID especieId);
    // Evitar cadastrar a mesma vacina duas vezes para a mesma espécie
    boolean existsByEspecieIdAndVacinaId(UUID especieId, UUID vacinaId);
}

```

### RepositorioVacina.java

```java
// src\main\java\br\com\vestris\vaccination\domain\repository\RepositorioVacina.java
package br.com.vestris.vaccination.domain.repository;

import br.com.vestris.vaccination.domain.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioVacina extends JpaRepository<Vacina, UUID> {
    boolean existsByNome(String nome);
}

```

---

## src\main\java\br\com\vestris\vaccination\interfaces\delegate

### ApiDelegateAplicacaoVacina.java

```java
// src\main\java\br\com\vestris\vaccination\interfaces\delegate\ApiDelegateAplicacaoVacina.java
package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.vaccination.application.ServiceAplicacaoVacina;
import br.com.vestris.vaccination.application.ServiceVacinacao;
import br.com.vestris.vaccination.domain.model.AplicacaoVacina;
import br.com.vestris.vaccination.interfaces.api.VacinacaoPacienteApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseAplicacaoVacina;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaAplicacaoVacina;
import br.com.vestris.vaccination.interfaces.dto.AplicacaoVacinaRequest;
import br.com.vestris.vaccination.interfaces.dto.AplicacaoVacinaResponse;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAplicacaoVacina implements VacinacaoPacienteApiDelegate {
    private final ServiceAplicacaoVacina servico;
    private final ServiceVacinacao serviceVacinacao;
    private final ServiceUsuario serviceUsuario;

    @Override
    public ResponseEntity<ApiResponseAplicacaoVacina> registrarVacinaPaciente(UUID pacienteId, UUID veterinarioId, AplicacaoVacinaRequest request) {
        AplicacaoVacina app = new AplicacaoVacina();
        app.setVacinaId(request.getVacinaId());

        // Data de Aplicação é obrigatória, então vem direto
        app.setDataAplicacao(request.getDataAplicacao());

        // --- CORREÇÃO: Usando unwrap para Data Próxima Dose (que é opcional/nullable) ---
        app.setDataProximaDose(unwrap(request.getDataProximaDose()));
        // -------------------------------------------------------------------------------

        app.setLote(request.getLote());
        app.setObservacoes(request.getObservacoes());
        app.setVeterinarioId(veterinarioId);

        AplicacaoVacina salva = servico.registrar(app, pacienteId);

        return ResponseEntity.status(201).body(criarResponse(salva));
    }

    @Override
    public ResponseEntity<ApiResponseListaAplicacaoVacina> listarVacinasDoPaciente(UUID pacienteId) {
        List<AplicacaoVacinaResponse> lista = servico.listarPorPaciente(pacienteId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAplicacaoVacina response = new ApiResponseListaAplicacaoVacina();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarVacinaAplicada(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPER PARA DESEMBRULHAR JSONNULLABLE ---
    private <T> T unwrap(JsonNullable<T> nullable) {
        if (nullable == null || !nullable.isPresent()) {
            return null;
        }
        return nullable.get();
    }

    // --- CONVERSORES ---

    private ApiResponseAplicacaoVacina criarResponse(AplicacaoVacina app) {
        ApiResponseAplicacaoVacina r = new ApiResponseAplicacaoVacina();
        r.setSucesso(true);
        r.setDados(converter(app));
        return r;
    }

    private AplicacaoVacinaResponse converter(AplicacaoVacina app) {
        AplicacaoVacinaResponse dto = new AplicacaoVacinaResponse();
        dto.setId(app.getId());
        dto.setPacienteId(app.getPaciente().getId());
        dto.setVacinaId(app.getVacinaId());
        dto.setDataAplicacao(app.getDataAplicacao());
        dto.setDataProximaDose(app.getDataProximaDose());
        dto.setLote(app.getLote());
        dto.setObservacoes(app.getObservacoes());

        // Enriquecimento com nome da vacina
        try {
            dto.setVacinaNome(serviceVacinacao.buscarNomePorId(app.getVacinaId()));
        } catch (Exception e) {
            dto.setVacinaNome("Vacina não identificada");
        }

        // Enriquecimento com nome do vet
        try {
            var vet = serviceUsuario.buscarPorId(app.getVeterinarioId());
            dto.setVeterinarioNome(vet.getNome());
        } catch (Exception e) {
            dto.setVeterinarioNome("Veterinário");
        }

        return dto;
    }

}

```

### ApiDelegateProtocoloVacinal.java

```java
// src\main\java\br\com\vestris\vaccination\interfaces\delegate\ApiDelegateProtocoloVacinal.java
package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.vaccination.application.ServiceProtocoloVacinacao;
import br.com.vestris.vaccination.domain.model.ProtocoloVacinacao;
import br.com.vestris.vaccination.interfaces.api.ProtocolosVacinaisApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaProtocoloVacinal;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseProtocoloVacinal;
import br.com.vestris.vaccination.interfaces.dto.ProtocoloVacinalRequest;
import br.com.vestris.vaccination.interfaces.dto.ProtocoloVacinalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateProtocoloVacinal implements ProtocolosVacinaisApiDelegate {
    private final ServiceProtocoloVacinacao servico;

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> criarProtocoloVacinal(ProtocoloVacinalRequest request) {
        ProtocoloVacinacao entidade = new ProtocoloVacinacao();
        entidade.setEspecieId(request.getEspecieId());
        entidade.setReferenciaId(request.getReferenciaId());
        entidade.setIdadeMinimaDias(request.getIdadeMinimaDias());
        entidade.setDiasParaReforco(request.getDiasParaReforco());
        entidade.setObrigatoria(request.getObrigatoria());
        entidade.setObservacoes(request.getObservacoes());

        ProtocoloVacinacao salvo = servico.criar(entidade, request.getVacinaId());

        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(converter(salvo));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaProtocoloVacinal> listarProtocolosPorEspecie(UUID especieId) {
        List<ProtocoloVacinalResponse> lista = servico.listarPorEspecie(especieId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaProtocoloVacinal response = new ApiResponseListaProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private ProtocoloVacinalResponse converter(ProtocoloVacinacao p) {
        ProtocoloVacinalResponse dto = new ProtocoloVacinalResponse();
        dto.setId(p.getId());
        dto.setEspecieId(p.getEspecieId());
        dto.setReferenciaId(p.getReferenciaId());
        dto.setVacinaId(p.getVacina().getId());
        dto.setNomeVacina(p.getVacina().getNome()); // Informação útil para o front
        dto.setIdadeMinimaDias(p.getIdadeMinimaDias());
        dto.setDiasParaReforco(p.getDiasParaReforco());
        dto.setObrigatoria(p.isObrigatoria());
        dto.setObservacoes(p.getObservacoes());
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> buscarProtocoloVacinalPorId(UUID id) {
        ProtocoloVacinacao p = servico.buscarPorId(id);
        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setDados(converter(p));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseProtocoloVacinal> atualizarProtocoloVacinal(UUID id, ProtocoloVacinalRequest request) {
        ProtocoloVacinacao atualizado = servico.atualizar(
                id,
                request.getEspecieId(),
                request.getVacinaId(),
                request.getReferenciaId(),
                request.getIdadeMinimaDias(),
                request.getDiasParaReforco(),
                request.getObrigatoria(),
                request.getObservacoes()
        );

        ApiResponseProtocoloVacinal response = new ApiResponseProtocoloVacinal();
        response.setSucesso(true);
        response.setMensagem("Protocolo atualizado.");
        response.setDados(converter(atualizado));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarProtocoloVacinal(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

```

### ApiDelegateVacinas.java

```java
// src\main\java\br\com\vestris\vaccination\interfaces\delegate\ApiDelegateVacinas.java
package br.com.vestris.vaccination.interfaces.delegate;

import br.com.vestris.vaccination.application.ServiceVacinacao;
import br.com.vestris.vaccination.domain.model.Vacina;
import br.com.vestris.vaccination.interfaces.api.VacinasApiDelegate;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseListaVacina;
import br.com.vestris.vaccination.interfaces.dto.ApiResponseVacina;
import br.com.vestris.vaccination.interfaces.dto.VacinaRequest;
import br.com.vestris.vaccination.interfaces.dto.VacinaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateVacinas implements VacinasApiDelegate {

    private final ServiceVacinacao servico;

    @Override
    public ResponseEntity<ApiResponseVacina> criarVacina(VacinaRequest request) {
        Vacina entidade = new Vacina();
        entidade.setNome(request.getNome());
        entidade.setFabricante(request.getFabricante());
        entidade.setTipoVacina(request.getTipoVacina());
        entidade.setDescricao(request.getDescricao());
        entidade.setDoencaAlvo(request.getDoencaAlvo());

        Vacina salva = servico.criar(entidade);

        ApiResponseVacina response = new ApiResponseVacina();
        response.setSucesso(true);
        response.setDados(converter(salva));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaVacina> listarVacinas() {
        List<VacinaResponse> lista = servico.listarTodas().stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaVacina response = new ApiResponseListaVacina();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    private VacinaResponse converter(Vacina v) {
        VacinaResponse dto = new VacinaResponse();
        dto.setId(v.getId());
        dto.setNome(v.getNome());
        dto.setFabricante(v.getFabricante());
        dto.setTipoVacina(v.getTipoVacina());
        dto.setDescricao(v.getDescricao());
        dto.setDoencaAlvo(v.getDoencaAlvo());

        if (v.getCriadoEm() != null) {
            dto.setCriadoEm(v.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }

    @Override
    public ResponseEntity<ApiResponseVacina> buscarVacinaPorId(UUID id) {
        Vacina v = servico.buscarPorId(id);
        ApiResponseVacina response = new ApiResponseVacina();
        response.setSucesso(true);
        response.setDados(converter(v));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseVacina> atualizarVacina(UUID id, VacinaRequest request) {
        Vacina dados = new Vacina();
        dados.setNome(request.getNome());
        dados.setFabricante(request.getFabricante());
        dados.setTipoVacina(request.getTipoVacina());
        dados.setDescricao(request.getDescricao());
        dados.setDoencaAlvo(request.getDoencaAlvo());

        Vacina atualizada = servico.atualizar(id, dados);

        ApiResponseVacina response = new ApiResponseVacina();
        response.setSucesso(true);
        response.setMensagem("Vacina atualizada.");
        response.setDados(converter(atualizada));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarVacina(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

```

---

## src\main\resources\swagger

### openapi.yml

```yaml
# src\main\resources\swagger\openapi.yml
openapi: 3.0.3
info:
  title: Vestris - Módulo Vacinação
  description: Gestão de Imunobiológicos
  version: 1.0.0
servers:
  - url: http://localhost:8080
    description: Servidor Local

paths:
  # --- VACINAS ---
  /api/v1/vacinas:
    $ref: './paths/vacinas.yml#/vacinas_colecao'

  /api/v1/vacinas/{id}:
    $ref: './paths/vacinas.yml#/vacinas_item'

  # --- PROTOCOLOS VACINAIS ---
  /api/v1/protocolos-vacinais:
    $ref: './paths/protocolos-vacinais.yml#/protocolos_colecao'

  /api/v1/protocolos-vacinais/{id}:
    $ref: './paths/protocolos-vacinais.yml#/protocolos_item'

  /api/v1/especies/{especieId}/protocolos-vacinais:
    $ref: './paths/protocolos-vacinais.yml#/protocolos_por_especie'

  /api/v1/pacientes/{pacienteId}/vacinas:
    $ref: './paths/vacinacao-paciente.yml#/vacinacao_paciente'
  /api/v1/vacinas-aplicadas/{id}:
    $ref: './paths/vacinacao-paciente.yml#/vacinacao_item'

components:
  schemas:
    VacinaRequest:
      $ref: "./components/schemas.yml#/VacinaRequest"
    VacinaResponse:
      $ref: "./components/schemas.yml#/VacinaResponse"
    ApiResponseVacina:
      $ref: "./components/schemas.yml#/ApiResponseVacina"
    ApiResponseListaVacina:
      $ref: "./components/schemas.yml#/ApiResponseListaVacina"
    ProtocoloVacinalRequest:
      $ref: "./components/schemas.yml#/ProtocoloVacinalRequest"
    ProtocoloVacinalResponse:
      $ref: "./components/schemas.yml#/ProtocoloVacinalResponse"
    ApiResponseProtocoloVacinal:
      $ref: "./components/schemas.yml#/ApiResponseVacina"
    ApiResponseListaProtocoloVacinal:
      $ref: "./components/schemas.yml#/ApiResponseListaVacina"

```

---

## src\main\resources\swagger\components

### schemas.yml

```yaml
# src\main\resources\swagger\components\schemas.yml
VacinaRequest:
  type: object
  required: [nome]
  properties:
    nome:
      type: string
    fabricante:
      type: string
    tipoVacina:
      type: string
    descricao:
      type: string
    doencaAlvo:
      type: string

VacinaResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    nome:
      type: string
    fabricante:
      type: string
    tipoVacina:
      type: string
    descricao:
      type: string
    doencaAlvo:
      type: string
    criadoEm:
      type: string
      format: date-time

# --- VACINAÇÃO DO PACIENTE ---
AplicacaoVacinaRequest:
  type: object
  required: [ vacinaId, dataAplicacao ]
  properties:
    vacinaId:
      type: string
      format: uuid
    dataAplicacao:
      type: string
      format: date
    dataProximaDose:
      type: string
      format: date
      nullable: true
    lote:
      type: string
    observacoes:
      type: string

AplicacaoVacinaResponse:
  type: object
  properties:
    id: { type: string, format: uuid }
    pacienteId: { type: string, format: uuid }
    vacinaId: { type: string, format: uuid }
    vacinaNome: { type: string } # Nome enriquecido
    dataAplicacao: { type: string, format: date }
    dataProximaDose: { type: string, format: date }
    lote: { type: string }
    observacoes: { type: string }
    veterinarioNome: { type: string } # Quem aplicou

ApiResponseAplicacaoVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { $ref: '#/AplicacaoVacinaResponse' }

ApiResponseListaAplicacaoVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados:
      type: array
      items: { $ref: '#/AplicacaoVacinaResponse' }

# --- PROTOCOLO VACINAL (NOVO) ---
ProtocoloVacinalRequest:
  type: object
  required: [especieId, vacinaId, referenciaId, idadeMinimaDias]
  properties:
    especieId:
      type: string
      format: uuid
    vacinaId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
      description: "Fonte científica ou legal que exige/recomenda esta vacina"
    idadeMinimaDias:
      type: integer
      example: 45
    diasParaReforco:
      type: integer
      description: "Se nulo ou zero, é dose única"
      example: 21
    obrigatoria:
      type: boolean
      description: "Se é exigida por lei (IBAMA/MAPA)"
    observacoes:
      type: string
      example: "Apenas para animais que terão contato com aves de produção."

ProtocoloVacinalResponse:
  type: object
  properties:
    id:
      type: string
      format: uuid
    especieId:
      type: string
      format: uuid
    vacinaId:
      type: string
      format: uuid
    referenciaId:
      type: string
      format: uuid
    nomeVacina:
      type: string
    idadeMinimaDias:
      type: integer
    diasParaReforco:
      type: integer
    obrigatoria:
      type: boolean
    observacoes:
      type: string

# Wrappers
ApiResponseVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/VacinaResponse' }
ApiResponseListaVacina:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/VacinaResponse' } }

ApiResponseProtocoloVacinal:
  type: object
  properties:
    sucesso: { type: boolean }
    mensagem:
      type: string
    dados: { $ref: '#/ProtocoloVacinalResponse' }
ApiResponseListaProtocoloVacinal:
  type: object
  properties:
    sucesso: { type: boolean }
    dados: { type: array, items: { $ref: '#/ProtocoloVacinalResponse' } }
```

---

## src\main\resources\swagger\paths

### protocolos-vacinais.yml

```yaml
# src\main\resources\swagger\paths\protocolos-vacinais.yml
# Rota: /api/v1/protocolos-vacinais
protocolos_colecao:
  post:
    tags:
      - ProtocolosVacinais
    summary: Criar protocolo
    operationId: criarProtocoloVacinal
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ProtocoloVacinalRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'

# Rota: /api/v1/especies/{especieId}/protocolos-vacinais
protocolos_por_especie:
  get:
    tags:
      - ProtocolosVacinais
    summary: Listar por espécie
    operationId: listarProtocolosPorEspecie
    parameters:
      - name: especieId
        in: path
        required: true
        schema:
          type: string
          format: uuid
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaProtocoloVacinal'

# Rota: /api/v1/protocolos-vacinais/{id}
protocolos_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - ProtocolosVacinais
    summary: Buscar protocolo por ID
    operationId: buscarProtocoloVacinalPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'
  put:
    tags:
      - ProtocolosVacinais
    summary: Atualizar protocolo
    operationId: atualizarProtocoloVacinal
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/ProtocoloVacinalRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseProtocoloVacinal'
  delete:
    tags:
      - ProtocolosVacinais
    summary: Remover protocolo
    operationId: deletarProtocoloVacinal
    responses:
      '204':
        description: Removido
```

### vacinacao-paciente.yml

```yaml
# src\main\resources\swagger\paths\vacinacao-paciente.yml
# ROTA: /api/v1/pacientes/{pacienteId}/vacinas
vacinacao_paciente:
  get:
    tags: [VacinacaoPaciente]
    summary: Listar vacinas aplicadas no paciente
    operationId: listarVacinasDoPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '200':
        description: Histórico vacinal
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseListaAplicacaoVacina' }

  post:
    tags: [VacinacaoPaciente]
    summary: Registrar aplicação de vacina
    operationId: registrarVacinaPaciente
    parameters:
      - name: pacienteId
        in: path
        required: true
        schema: { type: string, format: uuid }
      - name: veterinarioId
        in: query
        required: true
        schema: { type: string, format: uuid }
    requestBody:
      content:
        application/json:
          schema: { $ref: '../components/schemas.yml#/AplicacaoVacinaRequest' }
    responses:
      '201':
        description: Registrado com sucesso
        content:
          application/json:
            schema: { $ref: '../components/schemas.yml#/ApiResponseAplicacaoVacina' }

# ROTA: /api/v1/vacinas-aplicadas/{id}
vacinacao_item:
  delete:
    tags: [VacinacaoPaciente]
    summary: Remover registro de vacina (se erro)
    operationId: deletarVacinaAplicada
    parameters:
      - name: id
        in: path
        required: true
        schema: { type: string, format: uuid }
    responses:
      '204': { description: Removido }
```

### vacinas.yml

```yaml
# src\main\resources\swagger\paths\vacinas.yml
# Rota: /api/v1/vacinas
vacinas_colecao:
  post:
    tags:
      - Vacinas
    summary: Cadastrar vacina
    operationId: criarVacina
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/VacinaRequest'
    responses:
      '200':
        description: Sucesso
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  get:
    tags:
      - Vacinas
    summary: Listar todas
    operationId: listarVacinas
    responses:
      '200':
        description: Lista
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseListaVacina'

# Rota: /api/v1/vacinas/{id}
vacinas_item:
  parameters:
    - name: id
      in: path
      required: true
      schema:
        type: string
        format: uuid
  get:
    tags:
      - Vacinas
    summary: Buscar por ID
    operationId: buscarVacinaPorId
    responses:
      '200':
        description: Encontrado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  put:
    tags:
      - Vacinas
    summary: Atualizar vacina
    operationId: atualizarVacina
    requestBody:
      content:
        application/json:
          schema:
            $ref: '../components/schemas.yml#/VacinaRequest'
    responses:
      '200':
        description: Atualizado
        content:
          application/json:
            schema:
              $ref: '../components/schemas.yml#/ApiResponseVacina'
  delete:
    tags:
      - Vacinas
    summary: Remover vacina
    description: "Não permite remover se estiver em uso num protocolo"
    operationId: deletarVacina
    responses:
      '204':
        description: Removido
```


---

