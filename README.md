# AME Challenge 

**Rest API to maintain planets from Star Wars**

Nossos associados são aficionados por Star Wars e com isso, queremos criar um jogo com algumas informações da franquia.
Para possibilitar a equipe de front criar essa aplicação, queremos desenvolver uma API que contenha os dados dos planetas.

Requisitos:

A API deve ser REST
Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, sendo inserido manualmente:

Nome
Clima
Terreno

Para cada planeta também devemos ter a quantidade de aparições em filmes, que podem ser obtidas pela API pública do Star Wars: https://swapi.co/.

Funcionalidades desejadas:

Adicionar um planeta (com nome, clima e terreno)
Listar planetas
Buscar por nome
Buscar por ID
Remover planeta

Linguagens que usamos: Kotlin, Scala, Java

Bancos que usamos: PostgreSQL-11 with JSONB

**Meus comentários**

Para desenvolver esse desafio, utilizei a linguagem Java, o Spring MVC, o Spring Boot, e o banco de dados PostgreSQL-11. Criei uma API com os seguintes métodos:

http://localhost:8080/starwars/planets/add - (POST) - Insere um novo planeta no banco (dados no formato JSON em Request Body);
http://localhost:8080/starwars/planets/all - (GET) - Obtém uma relação de planetas cadastrados no banco;
http://localhost:8080/starwars/planets/allplus - (GET) - Obtém uma relação de planetas cadastrados no banco, incluindo a quantidade de filmes em que cada um aparece, extraída do SWAPI;
http://localhost:8080/starwars/planets/one/{id} - (GET) - Obtém um planeta cadastrado no banco, dado seu ID;
http://localhost:8080/starwars/planets/oneplus/{id} - (GET) - Obtém um planeta cadastrado no banco, dado seu ID, incluindo a quantidade de filmes em que ele aparece, extraída do SWAPI;
http://localhost:8080/starwars/planets/name/{name} - (GET) - Obtém um planeta cadastrado no banco, dado seu nome;
http://localhost:8080/starwars/planets/update - (POST) - Atualiza um planeta cadastrado no banco (dados no formato JSON em Request Body);
http://localhost:8080/starwars/planets/updateplus/{id} - (GET) - Atualiza um planeta cadastrado no banco, dado seu ID, com base nas informações extraídas do SWAPI;
http://localhost:8080/starwars/planets/delete/{id} - (GET) - Exclui um planeta cadastrado no banco, dado seu ID.
http://localhost:8080/starwars/planets/importSlow - (GET) - Importa toda a base de dados de planetas do SWAPI para o banco local, com um algoritmo mais lento (trazendo um a um, sequencialmente).
http://localhost:8080/starwars/planets/importFast - (GET) - Importa toda a base de dados de planetas do SWAPI para o banco local, com um algoritmo mais rápido (trazendo de forma paginada, conforme oferecido pelo SWAPI, 10 registros por vez).

São exemplos de dados JSON utilizados:

a) Um planeta a ser adicionado (via add) ou alterado (via update ou updateplus), ou obtido (via one ou name):

{
    "id": 1,
    "name": "Tatooine",
    "climate": "arid",
    "terrain": "desert"
}

b) Um planeta obtido via oneplus:

{
    "planet": {
        "id": 1,
        "name": "Tatooine",
        "climate": "arid",
        "terrain": "desert"
    },
    "numberoffilms": 5
}

O endereço de consulta do SWAPI é:

https://swapi.co/api/planets/{id}

que obtém um JSON no seguinte formato:

{
	"name": "Tatooine",
	"rotation_period": "23",
	"orbital_period": "304",
	"diameter": "10465",
	"climate": "arid",
	"gravity": "1 standard",
	"terrain": "desert",
	"surface_water": "1",
	"population": "200000",
	"residents": [
		"https://swapi.co/api/people/1/",
		"https://swapi.co/api/people/2/",
		"https://swapi.co/api/people/4/",
		"https://swapi.co/api/people/6/",
		"https://swapi.co/api/people/7/",
		"https://swapi.co/api/people/8/",
		"https://swapi.co/api/people/9/",
		"https://swapi.co/api/people/11/",
		"https://swapi.co/api/people/43/",
		"https://swapi.co/api/people/62/"
	],
	"films": [
		"https://swapi.co/api/films/5/",
		"https://swapi.co/api/films/4/",
		"https://swapi.co/api/films/6/",
		"https://swapi.co/api/films/3/",
		"https://swapi.co/api/films/1/"
	],
	"created": "2014-12-09T13:50:49.641000Z",
	"edited": "2014-12-21T20:48:04.175778Z",
	"url": "https://swapi.co/api/planets/1/"
}
