# Engenharia da Informação
----------------------------------------------------------------
## Elaborar um sistema de recuperação de informação
Tecnologia utilizada para buscar documentos, focalizando 
nas informações relacionadas a determinado tópico

### Processos de RI
- Dados
    - Acervo de documentos (itens de dados)
    - Expressão de busca do usuário
- Objetivo
    - Um conjunto (ordenado) de documentos que são relevantes para a consulta
- Modelo de Recuperação de Informação
    - Modelo Vetorial

### Etapas:
- [ x ] Aquisição (seleção) dos documentos
- [ x ] Preparação dos documentos
    - Objetivo:
        - Criar uma representção computacional do documento
        seguindo alguma visão lógica do documento
    - Etapas:
        - Operações sobre o texto
        - Criação de representação
            - Técnica utilizada: StopWords
- [ x ] Indexação dos documentos
    - Objetivo: 
        - Facilitar a busca dos documentos
    - Etapas:
        - Indexar os documentos a partir das palavras-chaves
            - Técnica utilizada: Arquivo invertido
- [] Armazenamento dos documentos
- [ x ] Recuperação dos documentos
    - Objetivo:
        - Seleção dos links dos documentos da base que satisfazem uma consulta
- [ x ] Casamento com a consulta do usuário
- [ x ] Ordenação dos documentos recuperados