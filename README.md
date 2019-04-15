# StarWars

Testes da API REST foram realizados via Postman.

Notas: 

1) A API REST foi produzida utilizando-se linguagem Java com Springframework, tendo como banco de dados o MongoDB;

2) O processo de carga no banco deverá ser por meio de inserções individuais apenas, uma vez que para o desenvolvimento da 
API levou-se em consideração o registro dos dados por meio de formulário simples;
      
3) O processo de listagem/remoção dos planetas por meio do nome é baseado em uma busca case-sensitive; 
assim, "Alderaan" se torna diferente de "alderaan" para o processo de busca;
      
4) A remoção de planetas no banco pode ser feita tanto por meio do nome quanto por meio do id criado na inserção do planeta
no banco de dados local;

5) A porta utilizada pelo Tomcat foi a Localhost:8093, o nome da database é "b2wswapi" (sem necessidade de senha) e o acesso
se dá pela url "localhost:8093/planets/<recurso>";


Processos realizados pela API:

I) Inserção de planetas no banco ("localhost:8093/planets/insert/")

II) Listagem de todos os planetas presentes no banco ("localhost:8093/planets/searchAll");

III) Listagem de planetas por id ("localhost:8093/planets/idSearch/<id>");

IV) Listagem de planetas por nome ("localhost:8093/planets/nameSearch/<nome>");

V) Remoção de planeta por id ("localhost:8093/planets/deleteById/<id>");

VI) Remoção de planeta por nome ("localhost:8093/planets/deleteByName/<nome>").
